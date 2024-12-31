package tool;

import ast.Node;
import ast.Type;
import org.web3j.abi.datatypes.generated.Bytes32;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Calculator {
    public static BigDecimal calBinaryExp(Node.BinaryExpression binaryExpression) throws Exception {
        ArrayList<Node.LowBinaryExpression> lowBinaryExpressions = binaryExpression.getLowBinaryExpressions();
        ArrayList<Type> highBinaryOperators = binaryExpression.getHighBinaryOperators();

        BigDecimal sum = calLowBinaryExp(lowBinaryExpressions.get(0));

        for (int i = 1; i < lowBinaryExpressions.size(); i++) {
            BigDecimal ele = calLowBinaryExp(lowBinaryExpressions.get(i));
            Type op = highBinaryOperators.get(i - 1);
            if (op.equals(Type.MUL)) {
                sum = sum.multiply(ele);
            } else if (op.equals(Type.DIV)) {
                if (ele.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("Division by zero is not allowed");
                }
                sum = sum.divide(ele, 18, RoundingMode.DOWN);
            } else if (op.equals(Type.MOD)) {
                if (ele.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("MOD by zero is not allowed");
                }
                sum = sum.remainder(ele);
            } else {
                throw new Exception("Invalid high binary operator: " + op);
            }
        }
        return sum;
    }

    private static BigDecimal calLowBinaryExp(Node.LowBinaryExpression lowBinaryExpression) throws Exception {
        ArrayList<Node.UnaryExpression> unaryExpressions = lowBinaryExpression.getUnaryExpressions();
        ArrayList<Type> lowBinaryOperators = lowBinaryExpression.getLowBinaryOperators();

        BigDecimal sum = calUnaryExp(unaryExpressions.get(0));

        for (int i = 1; i < unaryExpressions.size(); i++) {
            BigDecimal ele = calUnaryExp(unaryExpressions.get(i));
            Type op = lowBinaryOperators.get(i - 1);

            if (op.equals(Type.ADD)) {
                sum = sum.add(ele);
            } else if (op.equals(Type.SUB)) {
                sum = sum.subtract(ele);
            } else {
                throw new Exception("Invalid low binary operator: " + op);
            }
        }

        return sum;
    }

    private static BigDecimal calUnaryExp(Node.UnaryExpression unaryExpression) throws Exception {
        ArrayList<Type> unaryOperators = unaryExpression.getUnaryOperators();
        BigDecimal sum = calPrimaryExp(unaryExpression.getPrimaryExpression());

        int sub_cnt = 0;
        for (Type op : unaryOperators) {
            if (op.equals(Type.SUB) || op.equals(Type.LOGIC_NOT)) {
                sub_cnt += 1;
            } else if (op.equals(Type.ADD)) {
                sub_cnt += 0;
            } else {
                throw new Exception("Invalid low unary operator: " + op);
            }
        }
        if (sub_cnt % 2 == 0) {
            return sum;
        } else {
            return BigDecimal.ZERO.subtract(sum);
        }
    }

    private static BigDecimal calPrimaryExp(Node.PrimaryExpression primaryExpression) throws Exception {
        if (primaryExpression instanceof Node.Number) {
            return new BigDecimal(((Node.Number) primaryExpression).getNumber().getContent());
        } else if (primaryExpression instanceof Node.BinaryExpression) {
            return calBinaryExp((Node.BinaryExpression) primaryExpression);
        } else if (primaryExpression instanceof Node.UnaryExpression) {
            return calUnaryExp((Node.UnaryExpression) primaryExpression);
        } else {
            throw new Exception("Invalid primary expression");
        }
    }

//    public static Bytes32 stringToBytes32(String string) {
//        byte[] byteValue = string.getBytes();
//        byte[] byteValueNew = new byte[byteValue.length];
//        System.arraycopy(byteValue, 0, byteValueNew, 0, byteValue.length);
//        return new Bytes32(byteValueNew);
//    }

}
