package ast;

import java.util.ArrayList;

public class Node {

    private ArrayList<TriggerStatement> triggerStatements;

    public Node(ArrayList<TriggerStatement> triggerStatements) {
        this.triggerStatements = triggerStatements;
    }

    public ArrayList<TriggerStatement> getTriggerStatements() {
        return triggerStatements;
    }


    public static class TriggerStatement {
        private Condition triggerCondition;
        private boolean hasTriggerCondition;

        private Statement statement;

        private Condition checkCondition;
        private boolean hasCheckCondition;

        public TriggerStatement(boolean hasTriggerCondition, Condition triggerCondition,
                                Statement action,
                                boolean hasCheckCondition, Condition checkCondition) {
            this.statement = action;
            this.triggerCondition = triggerCondition;
            this.hasTriggerCondition = hasTriggerCondition;
            this.checkCondition = checkCondition;
            this.hasCheckCondition = hasCheckCondition;
        }

        public Condition getTriggerCondition() {
            if (!hasTriggerCondition) {
                return null;
            }
            return triggerCondition;
        }

        public Statement getStatement() {
            return statement;
        }

        public Condition getCheckCondition() {
            if (!hasCheckCondition) {
                return null;
            }
            return checkCondition;
        }

        public boolean isHasCheckCondition() {
            return hasCheckCondition;
        }

        public boolean isHasTriggerCondition() {
            return hasTriggerCondition;
        }

        @Override
        public String toString() {
            String ret = "";
            if (hasTriggerCondition) {
                ret = "trigger " + triggerCondition.toString() + " then\n";
            }
            ret += statement.toString();
            if (hasCheckCondition) {
                ret += "\nchecking " + checkCondition.toString();
            }
            ret += ";\n";
            return ret;
        }
    }

    public interface Condition {
    }

    public interface Statement {
    }

    public static class TransferStatement implements Statement {
        private Amount amount;
        private Wallet fromWallet;
        private Wallet toWallet;

        public TransferStatement(Amount amount, Wallet fromWallet, Wallet toWallet) {
            this.amount = amount;
            this.fromWallet = fromWallet;
            this.toWallet = toWallet;
        }

        public Amount getAmount() {
            return amount;
        }

        public Wallet getFromWallet() {
            return fromWallet;
        }

        public Wallet getToWallet() {
            return toWallet;
        }

        @Override
        public String toString() {
            return "transfer " + amount.toString() + " from " + fromWallet.toString() + " to " + toWallet.toString();
        }
    }

    public static class BorrowStatement implements Statement {
        private Amount borrowAmount;
        private Wallet forWallet;
        private Word platform;
        private Amount collateralAmount;
        private Wallet collateralWallet;

        public BorrowStatement(Amount borrowAmount, Wallet forWallet, Word platform, Amount collateralAmount, Wallet collateralWallet) {
            this.borrowAmount = borrowAmount;
            this.forWallet = forWallet;
            this.platform = platform;
            this.collateralAmount = collateralAmount;
            this.collateralWallet = collateralWallet;
        }

        public Amount getBorrowAmount() {
            return borrowAmount;
        }

        public Wallet getForWallet() {
            return forWallet;
        }

        public Word getPlatform() {
            return platform;
        }

        public Amount getCollateralAmount() {
            return collateralAmount;
        }

        public Wallet getCollateralWallet() {
            return collateralWallet;
        }

        @Override
        public String toString() {
            return "borrow " + borrowAmount.toString() + " for " + forWallet.toString() + " from " + platform.toString()
                    + " using " + collateralAmount.toString() + " from " + collateralWallet.toString() + " as collateral";
        }
    }

    public static class RepayBorrowStatement implements Statement {
        private Amount amount;
        private Wallet wallet;
        private Word platform;

        public RepayBorrowStatement(Amount amount, Wallet wallet, Word platform) {
            this.amount = amount;
            this.wallet = wallet;
            this.platform = platform;
        }

        public Amount getAmount() {
            return amount;
        }

        public Wallet getWallet() {
            return wallet;
        }

        public Word getPlatform() {
            return platform;
        }

        @Override
        public String toString() {
            return "repay " + amount.toString() + " from " + wallet.toString() + " to " + platform.toString();
        }
    }

    public static class SwapStatement implements Statement {
        private Amount amount;
        private Wallet wallet;
        private Word asset;
        private Word platform;

        public SwapStatement(Amount amount, Wallet wallet, Word asset, Word platform) {
            this.amount = amount;
            this.wallet = wallet;
            this.asset = asset;
            this.platform = platform;
        }

        public Amount getAmount() {
            return amount;
        }

        public Wallet getWallet() {
            return wallet;
        }

        public Word getAsset() {
            return asset;
        }

        public Word getPlatform() {
            return platform;
        }

        @Override
        public String toString() {
            return "swap " + amount.toString() + " from " + wallet.toString() + " for " + asset.getContent() + " on " + platform.toString();
        }
    }

    public static class AddLiquidityStatement implements Statement {
        private ArrayList<Amount> amounts;
        private ArrayList<Wallet> wallets;
        private Word platform;

        public AddLiquidityStatement(ArrayList<Amount> amounts, ArrayList<Wallet> wallets, Word platform) {
            this.amounts = amounts;
            this.wallets = wallets;
            this.platform = platform;
        }

        public ArrayList<Amount> getAmounts() {
            return amounts;
        }

        public ArrayList<Wallet> getWallets() {
            return wallets;
        }

        public Word getPlatform() {
            return platform;
        }

        @Override
        public String toString() {
            return "add liquidity " + amounts.get(0).toString() + ", " + amounts.get(1).toString() +
                    " to " + platform.toString() + " receiving liquidity token to " + wallets.get(0).toString();
        }
    }

    public static class RemoveLiquidityStatement implements Statement {
        private ArrayList<Amount> amounts;
        private ArrayList<Wallet> wallets;
        private Word platform;

        public RemoveLiquidityStatement(ArrayList<Amount> amounts, ArrayList<Wallet> wallets, Word platform) {
            this.amounts = amounts;
            this.wallets = wallets;
            this.platform = platform;
        }

        public ArrayList<Amount> getAmounts() {
            return amounts;
        }

        public ArrayList<Wallet> getWallets() {
            return wallets;
        }

        public Word getPlatform() {
            return platform;
        }

        public String toString() {
            return "remove liquidity " + amounts.get(0).toString() + ", " + amounts.get(1).toString() +
                    " from " + platform.toString() + " returning liquidity token from " + wallets.get(0).toString();

        }
    }

    public static class StakeStatement implements Statement {
        private Amount amount;
        private Wallet wallet;

        private ArrayList<String> strategy;

        public StakeStatement(Amount amount, Wallet wallet, ArrayList<String> strategy) {
            this.amount = amount;
            this.wallet = wallet;
            this.strategy = strategy;
        }

        public Amount getAmount() {
            return amount;
        }

        public Wallet getWallet() {
            return wallet;
        }

        public ArrayList<String> getStrategy() {
            return strategy;
        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder("stake " + amount.toString() + " from " + wallet.toString());
            if (strategy != null && strategy.size() > 0) {
                ret.append(" using ");
                for (String s : strategy) {
                    ret.append(s).append(" ");
                }
                ret.append("strategy");
            }
            return ret.toString();
        }
    }

    public static class BuyNFTStatement implements Statement {
        private ArrayList<String> NFTQualifiers;
        private Word NFTPlatform;
        private Amount budgetAmount;
        private Wallet budgetWallet;

        public BuyNFTStatement(ArrayList<String> NFTQualifiers, Word NFTPlatform, Amount budgetAmount, Wallet budgetWallet) {
            this.NFTQualifiers = NFTQualifiers;
            this.NFTPlatform = NFTPlatform;
            this.budgetAmount = budgetAmount;
            this.budgetWallet = budgetWallet;
        }

        public ArrayList<String> getNFTQualifiers() {
            return NFTQualifiers;
        }

        public Word getNFTPlatform() {
            return NFTPlatform;
        }

        public Amount getBudgetAmount() {
            return budgetAmount;
        }

        public Wallet getBudgetWallet() {
            return budgetWallet;
        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder("buy ");
            if (NFTQualifiers != null && NFTQualifiers.size() > 0) {
                for (String s : NFTQualifiers) {
                    ret.append(s).append(" ");
                }
            }
            ret.append("NFT on ").append(NFTPlatform.getContent()).append(" using at most ").
                    append(budgetAmount.toString()).append(" from ").append(budgetWallet.toString());
            return ret.toString();
        }
    }

    public static class SellNFTStatement implements Statement {
        private Word NFTKey;
        private ArrayList<String> strategy;

        public SellNFTStatement(Word NFTKey, ArrayList<String> startegy) {
            this.NFTKey = NFTKey;
            this.strategy = startegy;
        }

        public Word getNFTKey() {
            return NFTKey;
        }

        public ArrayList<String> getStrategy() {
            return strategy;
        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder("sell NFT [" + NFTKey.getContent() + "] ");
            if (strategy != null && strategy.size() > 0) {
                ret.append("using ");

                for (String s : strategy) {
                    ret.append(s).append(" ");
                }
                ret.append("strategy");
            }
            return ret.toString();
        }
    }

    public static class Amount {
        private BinaryExpression binaryExpression;
        private Word asset;

        public Amount(BinaryExpression binaryExpression, Word asset) {
            this.binaryExpression = binaryExpression;
            this.asset = asset;
        }

        public BinaryExpression getBinaryExpression() {
            return binaryExpression;
        }

        public Word getAsset() {
            return asset;
        }

        @Override
        public String toString() {
            return binaryExpression.toString() + " " + asset.getContent();
        }
    }

    public static class OrExpression implements Condition, ComparisonElement {
        private ArrayList<AndExpression> andExpressions;

        public OrExpression(ArrayList<AndExpression> andExpressions) {
            this.andExpressions = andExpressions;
        }

        public ArrayList<AndExpression> getAndExpressions() {
            return andExpressions;
        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder(andExpressions.get(0).toString());
            for (int i = 1; i < andExpressions.size(); i++) {
                ret.append(" or ").append(andExpressions.get(i).toString());
            }
            return ret.toString();
        }
    }

    public static class AndExpression implements Condition {
        private ArrayList<CmpOrTimeExpression> cmpOrTimeExpressions;

        public AndExpression(ArrayList<CmpOrTimeExpression> cmpOrTimeExpressions) {
            this.cmpOrTimeExpressions = cmpOrTimeExpressions;
        }

        public ArrayList<CmpOrTimeExpression> getCmpOrTimeExpressions() {
            return cmpOrTimeExpressions;
        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder(cmpOrTimeExpressions.get(0).toString());
            for (int i = 1; i < cmpOrTimeExpressions.size(); i++) {
                ret.append(" and ").append(cmpOrTimeExpressions.get(i).toString());
            }
            return ret.toString();
        }
    }

    public static class CmpOrTimeExpression implements Condition {
    }

    public static class ComparisonExpression extends CmpOrTimeExpression {
        private ComparisonElement leftExp;
        private ComparisonElement rightExp;

        private Type comparisonOperator;

        public ComparisonExpression(ComparisonElement leftExp, ComparisonElement rightExp, Type comparisonOperator) {
            this.leftExp = leftExp;
            this.rightExp = rightExp;
            this.comparisonOperator = comparisonOperator;
        }

        public ComparisonElement getLeftExp() {
            return leftExp;
        }

        public ComparisonElement getRightExp() {
            return rightExp;
        }

        public Type getComparisonOperator() {
            return comparisonOperator;
        }

        @Override
        public String toString() {
            return leftExp.toString() + " " + comparisonOperator.toString() + " " + rightExp.toString();
        }
    }

    public static class TimeCondition extends CmpOrTimeExpression {
        private Type timeOperator;
        private Word time1;
        private Word time2;

        public TimeCondition(Type timeOperator, Word time1, Word time2) {
            this.timeOperator = timeOperator;
            this.time1 = time1;
            this.time2 = time2;
        }

        public Type getTimeOperator() {
            return timeOperator;
        }

        public Word getTime1() {
            return time1;
        }

        public Word getTime2() {
            if (this.timeOperator.ordinal() == Type.DURING.ordinal()) {
                return time2;
            }
            return null;
        }

        @Override
        public String toString() {
            if (timeOperator.equals(Type.AFTER)) {
                return "time after " + time1.getContent();
            } else if (timeOperator.equals(Type.BEFORE)) {
                return "time before " + time1.getContent();
            } else {
                return "time during " + time1.getContent() + " to " + time2.toString();
            }
        }
    }

    public static class BinaryExpression implements PrimaryExpression {
        private ArrayList<LowBinaryExpression> lowBinaryExpressions;
        private ArrayList<Type> highBinaryOperators;

        public BinaryExpression(ArrayList<LowBinaryExpression> lowBinaryExpressions, ArrayList<Type> highBinaryOperators) {
            this.lowBinaryExpressions = lowBinaryExpressions;
            this.highBinaryOperators = highBinaryOperators;
        }

        public ArrayList<LowBinaryExpression> getLowBinaryExpressions() {
            return lowBinaryExpressions;
        }

        public ArrayList<Type> getHighBinaryOperators() {
            return highBinaryOperators;
        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder("(" + lowBinaryExpressions.get(0).toString() + ")");
            for (int i = 1; i < lowBinaryExpressions.size(); i++) {
                ret.append(" ").append(highBinaryOperators.get(i - 1).toString()).append(" (").append(lowBinaryExpressions.get(i).toString()).append(")");
            }
            return ret.toString();
        }
    }

    public static class LowBinaryExpression {
        private ArrayList<UnaryExpression> unaryExpressions;
        private ArrayList<Type> lowBinaryOperators;

        public LowBinaryExpression(ArrayList<UnaryExpression> unaryExpressions, ArrayList<Type> lowBinaryOperators) {
            this.unaryExpressions = unaryExpressions;
            this.lowBinaryOperators = lowBinaryOperators;
        }

        public ArrayList<UnaryExpression> getUnaryExpressions() {
            return unaryExpressions;
        }

        public ArrayList<Type> getLowBinaryOperators() {
            return lowBinaryOperators;
        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder("(" + unaryExpressions.get(0).toString() + ")");
            for (int i = 1; i < unaryExpressions.size(); i++) {
                ret.append(" ").append(lowBinaryOperators.get(i - 1).toString()).append(" (").append(unaryExpressions.get(i).toString()).append(")");
            }
            return ret.toString();
        }
    }

    public static class UnaryExpression implements PrimaryExpression {
        private ArrayList<Type> unaryOperators;
        private PrimaryExpression primaryExpression;

        public UnaryExpression(ArrayList<Type> unaryOperators, PrimaryExpression primaryExpression) {
            this.unaryOperators = unaryOperators;
            this.primaryExpression = primaryExpression;
        }

        public ArrayList<Type> getUnaryOperators() {
            return unaryOperators;
        }

        public PrimaryExpression getPrimaryExpression() {
            return primaryExpression;
        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder();
            for (Type op : unaryOperators) {
                ret.append(unaryOperators.toString());
            }
            ret.append("(").append(primaryExpression.toString()).append(")");
            return ret.toString();
        }
    }

    public interface PrimaryExpression {
    }

    public interface ComparisonElement extends Condition {
    }

    public static class WalletBalance implements ComparisonElement {
        private Wallet wallet;

        public WalletBalance(Wallet wallet) {
            this.wallet = wallet;
        }

        public Wallet getWallet() {
            return wallet;
        }

        @Override
        public String toString() {
            return "balance " + wallet.toString();
        }
    }

    public static class Wallet {
        private Word key;

        public Wallet(Word key) {
            this.key = key;
        }

        public Word getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "wallet[" + key.getContent() + "]";
        }
    }

    public static class AssetPrice implements ComparisonElement {
        private Word asset;
        private Word platform;

        public AssetPrice(Word asset, Word platform) {
            this.asset = asset;
            this.platform = platform;
        }

        public Word getAsset() {
            return asset;
        }

        public Word getPlatform() {
            return platform;
        }

        @Override
        public String toString() {
            return "price " + asset.getContent() + " on " + platform.toString();
        }
    }

    public static class NumberAsset implements ComparisonElement {
        private Word number;
        private Word asset;

        public NumberAsset(Word number, Word asset) {
            this.number = number;
            this.asset = asset;
        }

        public Word getNumber() {
            return number;
        }

        public Word getAsset() {
            return asset;
        }

        @Override
        public String toString() {
            return number.getContent() + " " + asset.getContent();
        }
    }

    public static class Number implements ComparisonElement, PrimaryExpression {
        private Word number;

        public Number(Word number) {
            this.number = number;
        }

        public Word getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return number.getContent();
        }
    }

    public static class Slippage implements ComparisonElement {
        private final Type slippage = Type.SLIPPAGE;

        public Slippage() {
        }

        public Type getSlippage() {
            return slippage;
        }

        @Override
        public String toString() {
            return "slippage";
        }
    }

    public static class Fee implements ComparisonElement {
        private final Type fee = Type.FEE;

        public Fee() {
        }

        public Type getFee() {
            return fee;
        }

        @Override
        public String toString() {
            return "fee";
        }
    }

}
