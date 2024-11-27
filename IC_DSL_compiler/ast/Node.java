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
    }

    public static class StakeStatement implements Statement {
        private Amount amount;
        private Wallet wallet;
        private Word platform;

        public StakeStatement(Amount amount, Wallet wallet, Word platform) {
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
    }

    public static class OrExpression implements Condition, ComparisonElement {
        private ArrayList<AndExpression> andExpressions;

        public OrExpression(ArrayList<AndExpression> andExpressions) {
            this.andExpressions = andExpressions;
        }

        public ArrayList<AndExpression> getAndExpressions() {
            return andExpressions;
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
    }

    public static class Wallet {
        private Word key;

        public Wallet(Word key) {
            this.key = key;
        }

        public Word getKey() {
            return key;
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
    }

    public static class Number implements ComparisonElement, PrimaryExpression {
        private Word number;

        public Number(Word number) {
            this.number = number;
        }

        public Word getNumber() {
            return number;
        }
    }

    public static class Slippage implements ComparisonElement {
        private final Type slippage = Type.SLIPPAGE;

        public Slippage() {
        }

        public Type getSlippage() {
            return slippage;
        }
    }

    public static class Fee implements ComparisonElement {
        private final Type fee = Type.FEE;

        public Fee() {
        }

        public Type getFee() {
            return fee;
        }
    }

}
