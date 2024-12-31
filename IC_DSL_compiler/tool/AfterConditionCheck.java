package tool;

import ast.Node;
import ast.Type;
import org.web3j.protocol.core.methods.request.Transaction;

import java.math.BigDecimal;
import java.math.BigInteger;

public class AfterConditionCheck {
    private final Node.Statement statement;
    private final Transaction transaction;
    private final String routerAddress;

    private BigInteger gasLimit;
    private BigDecimal slippage;

    public AfterConditionCheck(Node.Statement statement, Transaction transaction, String routerAddress) {
        this.statement = statement;
        this.transaction = transaction;
        this.routerAddress = routerAddress;
        this.gasLimit = null;
    }

    public BigInteger getGasLimit() {
        return gasLimit;
    }

    public boolean checkAfterCondition(Node.Condition condition) throws Exception {
        return checkOrExpression((Node.OrExpression) condition);
    }

    private boolean checkOrExpression(Node.OrExpression orExpression) throws Exception {
        if (orExpression == null) {
            return true;
        }

        for (Node.AndExpression andExpression : orExpression.getAndExpressions()) {
            if (checkAndExpression(andExpression)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAndExpression(Node.AndExpression andExpression) throws Exception {
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

    private boolean checkCmpExpression(Node.ComparisonExpression comparisonExpression) throws Exception {
        Node.ComparisonElement leftEle = comparisonExpression.getLeftExp();
        Node.ComparisonElement rightEle = comparisonExpression.getRightExp();
        Type cmpOperator = comparisonExpression.getComparisonOperator();

        if (leftEle instanceof Node.WalletBalance) {
            if (rightEle instanceof Node.NumberAsset) {
                return ConditionCheckAPI.balanceCheckAPI(((Node.WalletBalance) leftEle).getWallet().getKey().getContent(),
                        new BigDecimal(((Node.NumberAsset) rightEle).getNumber().getContent()),
                        ((Node.NumberAsset) rightEle).getAsset(), cmpOperator);
            } else {
                throw new Exception("Invalid comparison element for check wallet balance condition.");
            }
        } else if (leftEle instanceof Node.AssetPrice) {
            if (rightEle instanceof Node.Number) {
                return ConditionCheckAPI.assetPriceCheckAPI(((Node.AssetPrice) leftEle).getAsset(),
                        cmpOperator, ((Node.Number) rightEle).getNumber());
            } else {
                throw new Exception("Invalid comparison element for check token price condition.");
            }
        } else if (leftEle instanceof Node.Fee) {
            if (rightEle instanceof Node.Number) {
                if (cmpOperator.equals(Type.LE) || cmpOperator.equals(Type.LT)) {
                    this.gasLimit = new BigInteger(((Node.Number) rightEle).getNumber().getContent());
                }
                return ConditionCheckAPI.checkEstimateFee(transaction,
                        new BigDecimal(((Node.Number) rightEle).getNumber().getContent()), cmpOperator);
            } else {
                throw new Exception("Invalid comparison element for check fee condition.");
            }
        } else if (leftEle instanceof Node.Slippage) {
            if (rightEle instanceof Node.Number) {
                if (statement instanceof Node.SwapStatement) {
                    if (cmpOperator.equals(Type.LT) || cmpOperator.equals(Type.LE)) {
                        this.slippage = new BigDecimal(((Node.Number) rightEle).getNumber().getContent());
                    }
                    return ConditionCheckAPI.checkSlippage((Node.SwapStatement) statement, routerAddress,
                            ((Node.Number) rightEle).getNumber(), cmpOperator);
                } else {
                    return true;
                }
            } else {
                throw new Exception("Invalid comparison element for check slippage condition.");
            }
        } else if (leftEle instanceof Node.NumberAsset) {
            if (rightEle instanceof Node.WalletBalance) {
                return ConditionCheckAPI.balanceCheckAPI(((Node.WalletBalance) rightEle).getWallet().getKey().getContent(),
                        new BigDecimal(((Node.NumberAsset) leftEle).getNumber().getContent()),
                        ((Node.NumberAsset) leftEle).getAsset(), getContaryCmpOp(cmpOperator));
            } else {
                throw new Exception("Invalid comparison element to compare with number asset.");
            }
        } else if (leftEle instanceof Node.Number) {
            if (rightEle instanceof Node.AssetPrice) {
                return ConditionCheckAPI.assetPriceCheckAPI(((Node.AssetPrice) rightEle).getAsset(),
                        getContaryCmpOp(cmpOperator), ((Node.Number) leftEle).getNumber());
            } else if (rightEle instanceof Node.Fee) {
                if (cmpOperator.equals(Type.GT) || cmpOperator.equals(Type.GE)) {
                    this.gasLimit = new BigInteger(((Node.Number) leftEle).getNumber().getContent());
                }
                return ConditionCheckAPI.checkEstimateFee(transaction,
                        new BigDecimal(((Node.Number) leftEle).getNumber().getContent()), getContaryCmpOp(cmpOperator));
            } else if (rightEle instanceof Node.Slippage) {
                if (statement instanceof Node.SwapStatement) {
                    if (cmpOperator.equals(Type.GT) || cmpOperator.equals(Type.GE)) {
                        this.slippage = new BigDecimal(((Node.Number) leftEle).getNumber().getContent());
                    }
                    return ConditionCheckAPI.checkSlippage((Node.SwapStatement) statement, routerAddress,
                            ((Node.Number) leftEle).getNumber(), getContaryCmpOp(cmpOperator));
                } else {
                    return true;
                }
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
        return ConditionCheckAPI.timeCheckAPI(timeCondition.getTime1(), timeCondition.getTime1(), timeCondition.getTimeOperator());
    }


}
