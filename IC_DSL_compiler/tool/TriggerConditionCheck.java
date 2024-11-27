package tool;

import ast.Node;
import ast.Type;

import java.math.BigDecimal;

public class TriggerConditionCheck {
    public static boolean checkTriggerCondition(Node.Condition condition) throws Exception {
        return checkOrExpression((Node.OrExpression) condition);
    }

    private static boolean checkOrExpression(Node.OrExpression orExpression) throws Exception {
        for (Node.AndExpression andExpression : orExpression.getAndExpressions()) {
            if (checkAndExpression(andExpression)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkAndExpression(Node.AndExpression andExpression) throws Exception {
        for (Node.CmpOrTimeExpression cmpOrTimeExpression : andExpression.getCmpOrTimeExpressions()) {
            if (cmpOrTimeExpression instanceof Node.ComparisonExpression) {
                if (!checkCmpExpression((Node.ComparisonExpression) cmpOrTimeExpression)) {
                    return false;
                }
            } else if (cmpOrTimeExpression instanceof Node.TimeCondition) {
                if (!checkTimeCondition((Node.TimeCondition) cmpOrTimeExpression)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkCmpExpression(Node.ComparisonExpression comparisonExpression) throws Exception {
        Node.ComparisonElement leftEle = comparisonExpression.getLeftExp();
        Node.ComparisonElement rightEle = comparisonExpression.getRightExp();
        Type cmpOperator = comparisonExpression.getComparisonOperator();

        if (leftEle instanceof Node.WalletBalance) {
            if (rightEle instanceof Node.NumberAsset) {
                return ConditionCheck.balanceCheckAPI(((Node.WalletBalance) leftEle).getWallet().getKey().getContent(),
                        new BigDecimal(((Node.NumberAsset) rightEle).getNumber().getContent()),
                        ((Node.NumberAsset) rightEle).getAsset(), cmpOperator);
            } else {
                throw new Exception("Invalid comparison element for check wallet balance condition.");
            }
        } else if (leftEle instanceof Node.AssetPrice) {
            if (rightEle instanceof Node.Number) {
                return ConditionCheck.assetPriceCheckAPI(((Node.AssetPrice) leftEle).getAsset(),
                        cmpOperator, ((Node.Number) rightEle).getNumber());
            } else {
                throw new Exception("Invalid comparison element for check token price condition.");
            }
        } else if (leftEle instanceof Node.NumberAsset) {
            if (rightEle instanceof Node.WalletBalance) {
                return ConditionCheck.balanceCheckAPI(((Node.WalletBalance) rightEle).getWallet().getKey().getContent(),
                        new BigDecimal(((Node.NumberAsset) leftEle).getNumber().getContent()),
                        ((Node.NumberAsset) leftEle).getAsset(), getContaryCmpOp(cmpOperator));
            } else {
                throw new Exception("Invalid comparison element to compare with number asset.");
            }
        } else if (leftEle instanceof Node.Number) {
            if (rightEle instanceof Node.AssetPrice) {
                return ConditionCheck.assetPriceCheckAPI(((Node.AssetPrice) rightEle).getAsset(),
                        getContaryCmpOp(cmpOperator), ((Node.Number) leftEle).getNumber());
            } else if (rightEle instanceof Node.Number) {
                BigDecimal leftNum = new BigDecimal(((Node.Number) leftEle).getNumber().getContent());
                BigDecimal rightNum = new BigDecimal(((Node.Number) rightEle).getNumber().getContent());

                switch (cmpOperator) {
                    case EQ:
                        return leftNum.compareTo(rightNum) == 0;
                    case NEQ:
                        return leftNum.compareTo(rightNum) != 0;
                    case LT:
                        return leftNum.compareTo(rightNum) < 0;
                    case GT:
                        return leftNum.compareTo(rightNum) > 0;
                    case LE:
                        return leftNum.compareTo(rightNum) <= 0;
                    case GE:
                        return leftNum.compareTo(rightNum) >= 0;
                    default:
                        throw new Exception("Invalid comparison operator: " + cmpOperator);
                }
            } else {
                throw new Exception("Invalid comparison element to compare with Number.");
            }
        } else {
            throw new Exception("Invalid comparison element for trigger condition before transaction.");
        }
    }

    private static Type getContaryCmpOp(Type op) throws Exception {
        switch (op) {
            case EQ:
                return Type.EQ;
            case NEQ:
                return Type.NEQ;
            case LT:
                return Type.GT;
            case GT:
                return Type.LT;
            case LE:
                return Type.GE;
            case GE:
                return Type.LE;
            default:
                throw new Exception("Invalid comparison operator: " + op);
        }
    }

    private static boolean checkTimeCondition(Node.TimeCondition timeCondition) {
        return ConditionCheck.timeCheckAPI(timeCondition.getTime1(), timeCondition.getTime1(), timeCondition.getTimeOperator());
    }


}
