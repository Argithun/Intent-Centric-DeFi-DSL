package ast;

import grammar.IntentDSLParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class Listener {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void enterProgram(IntentDSLParser.ProgramContext ctx) {
        ArrayList<Node.TriggerStatement> triggerStatements = new ArrayList<>();
        for (IntentDSLParser.TriggerStatementContext tsc : ctx.triggerStatement()) {
            triggerStatements.add(enterTriggerStatement(tsc));
        }
        root = new Node(triggerStatements);
    }

    public Node.TriggerStatement enterTriggerStatement(IntentDSLParser.TriggerStatementContext ctx) {
        List<IntentDSLParser.ConditionContext> conditionContexts = ctx.condition();
        IntentDSLParser.StatementContext statementContext = ctx.statement();

        Node.Condition triggerCondition = null;
        boolean hasTriggerCondition = false;
        Node.Condition checkCondition = null;
        boolean hasCheckCondition = false;

        if (conditionContexts != null) {
            if (conditionContexts.size() == 1) {
                if (ctx.getText().contains("trigger") && ctx.getText().contains("then")) {
                    triggerCondition = enterCondition(conditionContexts.get(0));
                    hasTriggerCondition = true;
                } else {
                    checkCondition = enterCondition(conditionContexts.get(0));
                    hasCheckCondition = true;
                }
            } else if (conditionContexts.size() == 2) {
                triggerCondition = enterCondition(conditionContexts.get(0));
                hasTriggerCondition = true;
                checkCondition = enterCondition(conditionContexts.get(1));
                hasCheckCondition = true;
            }
        }

        Node.Statement statement = enterStatement(statementContext);

        return new Node.TriggerStatement(hasTriggerCondition, triggerCondition, statement, hasCheckCondition, checkCondition);
    }

    public Node.Statement enterStatement(IntentDSLParser.StatementContext ctx) {
        if (ctx.accountStatement() != null) {
            return enterAccountStatement(ctx.accountStatement());
        } else if (ctx.transferStatement() != null) {
            return enterTransferStatement(ctx.transferStatement());
        } else if (ctx.borrowStatement() != null) {
            return enterBorrowStatement(ctx.borrowStatement());
        } else if (ctx.repayBorrowStatement() != null) {
            return enterRepayBorrowStatement(ctx.repayBorrowStatement());
        } else if (ctx.stakeStatement() != null) {
            return enterStakeStatement(ctx.stakeStatement());
        } else if (ctx.swapStatement() != null) {
            return enterSwapStatement(ctx.swapStatement());
        } else if (ctx.addLiquidityStatement() != null) {
            return enterAddLiquidityStatement(ctx.addLiquidityStatement());
        } else if (ctx.removeLiquidityStatement() != null) {
            return enterRemoveLiquidityStatement(ctx.removeLiquidityStatement());
        } else if (ctx.buyNFTStatement() != null) {
            return enterBuyNFTStatement(ctx.buyNFTStatement());
        } else if (ctx.sellNFTStatement() != null) {
            return enterSellNFTStatement(ctx.sellNFTStatement());
        }

        return null;
    }

    public Node.Statement enterAccountStatement(IntentDSLParser.AccountStatementContext ctx) {
        Word privateKey = new Word(ctx.PRIVATE_KEY().getText(), Type.PRIVATE_KEY);

        return new Node.AccountStatement(privateKey);
    }

    public Node.Statement enterTransferStatement(IntentDSLParser.TransferStatementContext ctx) {
        Node.Amount amount = enterAmount(ctx.amount());
        Node.Wallet fromWallet = enterWallet(ctx.wallet(0));
        Node.Wallet toWallet = enterWallet(ctx.wallet(1));

        return new Node.TransferStatement(amount, fromWallet, toWallet);
    }

    public Node.Statement enterBorrowStatement(IntentDSLParser.BorrowStatementContext ctx) {
        Node.Amount borrowAmount = enterAmount(ctx.amount());
        Node.Wallet forWallet = enterWallet(ctx.wallet());
        Word platform = new Word(ctx.platform().getText(), Type.PLATFORM);

        return new Node.BorrowStatement(borrowAmount, forWallet, platform);
    }

    public Node.Statement enterRepayBorrowStatement(IntentDSLParser.RepayBorrowStatementContext ctx) {
        Node.Amount amount = enterAmount(ctx.amount());
        Node.Wallet wallet = enterWallet(ctx.wallet());
        Word platform = new Word(ctx.platform().getText(), Type.PLATFORM);

        return new Node.RepayBorrowStatement(amount, wallet, platform);
    }

    public Node.Statement enterStakeStatement(IntentDSLParser.StakeStatementContext ctx) {
        Node.Amount amount = enterAmount(ctx.amount());
        Node.Wallet wallet = enterWallet(ctx.wallet());
        ArrayList<String> strategyQualifiers = new ArrayList<>();

        if (ctx.stakeStrategy() != null) {
            for (IntentDSLParser.StakeStrategyQualifiersContext stakeStrategyQualifiersContext :
                    ctx.stakeStrategy().stakeStrategyQualifiers()) {
                strategyQualifiers.add(stakeStrategyQualifiersContext.getText());
            }
        }

        return new Node.StakeStatement(amount, wallet, strategyQualifiers);
    }

    public Node.Statement enterSwapStatement(IntentDSLParser.SwapStatementContext ctx) {
        Node.Amount amount = enterAmount(ctx.amount());
        Node.Wallet wallet = enterWallet(ctx.wallet());
        Word asset = new Word(ctx.asset().getText(), Type.ASSET);
        Word platform = new Word(ctx.platform().getText(), Type.PLATFORM);

        return new Node.SwapStatement(amount, wallet, asset, platform);
    }

    public Node.Statement enterAddLiquidityStatement(IntentDSLParser.AddLiquidityStatementContext ctx) {
        ArrayList<Node.Amount> amounts = new ArrayList<>();
        ArrayList<Node.Wallet> wallets = new ArrayList<>();
        Word platform = new Word(ctx.platform().getText(), Type.PLATFORM);

        for (IntentDSLParser.AmountContext amountContext : ctx.amount()) {
            amounts.add(enterAmount(amountContext));
        }

//        for (IntentDSLParser.WalletContext walletContext : ctx.wallet()) {
//            wallets.add(enterWallet(walletContext));
//        }
        wallets.add(enterWallet(ctx.wallet()));

        return new Node.AddLiquidityStatement(amounts, wallets, platform);
    }

    public Node.Statement enterRemoveLiquidityStatement(IntentDSLParser.RemoveLiquidityStatementContext ctx) {
        ArrayList<Node.Amount> amounts = new ArrayList<>();
        ArrayList<Node.Wallet> wallets = new ArrayList<>();
        Word platform = new Word(ctx.platform().getText(), Type.PLATFORM);

        for (IntentDSLParser.AmountContext amountContext : ctx.amount()) {
            amounts.add(enterAmount(amountContext));
        }

//        for (IntentDSLParser.WalletContext walletContext : ctx.wallet()) {
//            wallets.add(enterWallet(walletContext));
//        }
        wallets.add(enterWallet(ctx.wallet()));

        String tokenId = ctx.DEC_INT() == null ? null : ctx.DEC_INT().getText();
        String liquidityNum = ctx.KEY().getText();

        return new Node.RemoveLiquidityStatement(amounts, wallets, platform, tokenId, liquidityNum);
    }

    public Node.Statement enterBuyNFTStatement(IntentDSLParser.BuyNFTStatementContext ctx) {
//        Word NFTPlatform = new Word(ctx.NFTPlatform().getText(), Type.NFTPLATFORM);
        Node.Amount budgetAmount = enterAmount(ctx.amount());
        Node.Wallet budgetWallet = enterWallet(ctx.wallet());
        ArrayList<String> NFTQualifiers = new ArrayList<>();

        for (TerminalNode s : ctx.NFTQualifiers()) {
            NFTQualifiers.add(s.getText());
        }

        return new Node.BuyNFTStatement(NFTQualifiers, budgetAmount, budgetWallet);
    }

    public Node.Statement enterSellNFTStatement(IntentDSLParser.SellNFTStatementContext ctx) {
        Word NFTTokenID = new Word(ctx.KEY().get(0).getText(), Type.KEY);
        Word NFTCollectionID = new Word(ctx.KEY().get(1).getText(), Type.KEY);
        Node.Wallet wallet = enterWallet(ctx.wallet());
        ArrayList<String> strategy = new ArrayList<>();

        for (IntentDSLParser.SellNFTStrategyQualifiersContext sellNFTStrategyQualifiersContext :
                ctx.sellNFTStartegy().sellNFTStrategyQualifiers()) {
            strategy.add(sellNFTStrategyQualifiersContext.getText());
        }

        return new Node.SellNFTStatement(NFTTokenID, NFTCollectionID, wallet, strategy);
    }

    public Node.Amount enterAmount(IntentDSLParser.AmountContext ctx) {
        Node.BinaryExpression binaryExpression = enterBinaryExpression(ctx.binaryExpression());
        Word asset = new Word(ctx.asset().getText(), Type.ASSET);
        return new Node.Amount(binaryExpression, asset);
    }

    public Node.Wallet enterWallet(IntentDSLParser.WalletContext ctx) {
        Word key = new Word(ctx.KEY().getText(), Type.KEY);

        return new Node.Wallet(key);
    }


    public Node.Condition enterCondition(IntentDSLParser.ConditionContext ctx) {
        return enterOrExpression(ctx.orExpression());
    }

    public Node.OrExpression enterOrExpression(IntentDSLParser.OrExpressionContext ctx) {
        ArrayList<Node.AndExpression> andExpressions = new ArrayList<>();

        for (IntentDSLParser.AndExpressionContext andExpressionContext : ctx.andExpression()) {
            andExpressions.add(enterAndExpression(andExpressionContext));
        }

        return new Node.OrExpression(andExpressions);
    }

    public Node.AndExpression enterAndExpression(IntentDSLParser.AndExpressionContext ctx) {
        ArrayList<Node.CmpOrTimeExpression> cmpOrTimeExpressions = new ArrayList<>();

        for (IntentDSLParser.ComparisonExpressionContext comparisonExpressionContext : ctx.comparisonExpression()) {
            cmpOrTimeExpressions.add(enterComparisonExpression(comparisonExpressionContext));
        }

        for (IntentDSLParser.TimeConditionContext timeConditionContext : ctx.timeCondition()) {
            cmpOrTimeExpressions.add(enterTimeCondition(timeConditionContext));
        }

        return new Node.AndExpression(cmpOrTimeExpressions);
    }

    public Node.ComparisonExpression enterComparisonExpression(IntentDSLParser.ComparisonExpressionContext ctx) {
        Node.ComparisonElement leftExp = enterComparisonElement(ctx.comparisonElement(0));
        Node.ComparisonElement rightExp = enterComparisonElement(ctx.comparisonElement(1));

        Type comparisonOperator = null;

        if (ctx.comparisonOperator().getText().equals("==")) {
            comparisonOperator = Type.EQ;
        } else if (ctx.comparisonOperator().getText().equals("!=")) {
            comparisonOperator = Type.NEQ;
        } else if (ctx.comparisonOperator().getText().equals("<")) {
            comparisonOperator = Type.LT;
        } else if (ctx.comparisonOperator().getText().equals(">")) {
            comparisonOperator = Type.GT;
        } else if (ctx.comparisonOperator().getText().equals("<=")) {
            comparisonOperator = Type.LE;
        } else if (ctx.comparisonOperator().getText().equals(">=")) {
            comparisonOperator = Type.GE;
        }

        return new Node.ComparisonExpression(leftExp, rightExp, comparisonOperator);
    }

    public Node.TimeCondition enterTimeCondition(IntentDSLParser.TimeConditionContext ctx) {
        Type timeOperator = null;
        Word time1 = null;
        Word time2 = null;

        if (ctx.TIME(1) == null) {
            if (ctx.getText().contains("before")) {
                timeOperator = Type.BEFORE;
            } else if (ctx.getText().contains("after")) {
                timeOperator = Type.AFTER;
            }
            time1 = new Word(ctx.TIME(0).getText(), Type.TIME);
        } else {
            timeOperator = Type.DURING;
            time1 = new Word(ctx.TIME(0).getText(), Type.TIME);
            time2 = new Word(ctx.TIME(1).getText(), Type.TIME);
        }

        return new Node.TimeCondition(timeOperator, time1, time2);
    }

    public Node.ComparisonElement enterComparisonElement(IntentDSLParser.ComparisonElementContext ctx) {
        if (ctx.walletBalance() != null) {
            return enterWalletBalance(ctx.walletBalance());
        } else if (ctx.assetPrice() != null) {
            return enterAssetPrice(ctx.assetPrice());
        } else if (ctx.number() != null) {
            Word number = enterNumber(ctx.number());
            if (ctx.asset() != null) {
                Word asset = new Word(ctx.asset().getText(), Type.ASSET);
                return new Node.NumberAsset(number, asset);
            }
            return new Node.Number(number);
        } else if (ctx.SLIPPAGE() != null) {
            return new Node.Slippage();
        } else if (ctx.FEE() != null) {
            return new Node.Fee();
        }
//        else if (ctx.orExpression() != null) {
//            return enterOrExpression(ctx.orExpression());
//        }

        return null;
    }

    public Node.BinaryExpression enterBinaryExpression(IntentDSLParser.BinaryExpressionContext ctx) {
        ArrayList<Node.LowBinaryExpression> lowBinaryExpressions = new ArrayList<>();
        ArrayList<Type> highBinaryOperators = new ArrayList<>();

        for (IntentDSLParser.LowBinaryExpressionContext lowBinaryExpressionContext : ctx.lowBinaryExpression()) {
            lowBinaryExpressions.add(enterLowBinaryExpression(lowBinaryExpressionContext));
        }

        for (IntentDSLParser.HighBinaryOperatorContext highBinaryOperatorContext : ctx.highBinaryOperator()) {
            Type highBinaryOperator = null;
            if (highBinaryOperatorContext.getText().equals("*")) {
                highBinaryOperator = Type.MUL;
            } else if (highBinaryOperatorContext.getText().equals("-")) {
                highBinaryOperator = Type.SUB;
            } else if (highBinaryOperatorContext.getText().equals("%")) {
                highBinaryOperator = Type.MOD;
            }

            highBinaryOperators.add(highBinaryOperator);
        }

        return new Node.BinaryExpression(lowBinaryExpressions, highBinaryOperators);
    }

    public Node.LowBinaryExpression enterLowBinaryExpression(IntentDSLParser.LowBinaryExpressionContext ctx) {
        ArrayList<Node.UnaryExpression> unaryExpressions = new ArrayList<>();
        ArrayList<Type> lowBinaryOperators = new ArrayList<>();

        for (IntentDSLParser.UnaryExpressionContext unaryExpressionContext : ctx.unaryExpression()) {
            unaryExpressions.add(enterUnaryExpression(unaryExpressionContext));
        }

        for (IntentDSLParser.LowBinaryOperatorContext lowBinaryOperatorContext : ctx.lowBinaryOperator()) {
            Type lowBinaryOperator = null;
            if (lowBinaryOperatorContext.getText().equals("+")) {
                lowBinaryOperator = Type.ADD;
            } else if (lowBinaryOperatorContext.getText().equals("-")) {
                lowBinaryOperator = Type.SUB;
            }

            lowBinaryOperators.add(lowBinaryOperator);
        }

        return new Node.LowBinaryExpression(unaryExpressions, lowBinaryOperators);
    }

    public Node.UnaryExpression enterUnaryExpression(IntentDSLParser.UnaryExpressionContext ctx) {
        ArrayList<Type> unaryOperators = new ArrayList<>();
        Node.PrimaryExpression primaryExpression = enterPrimaryExpression(ctx.primaryExpression());

        for (IntentDSLParser.UnaryOperatorContext unaryOperatorContext : ctx.unaryOperator()) {
            Type unaryOperator = null;
            if (unaryOperatorContext.getText().equals("+")) {
                unaryOperator = Type.ADD;
            } else if (unaryOperatorContext.getText().equals("-")) {
                unaryOperator = Type.SUB;
            } else if (unaryOperatorContext.getText().equals("not")) {
                unaryOperator = Type.LOGIC_NOT;
            }

            unaryOperators.add(unaryOperator);
        }

        return new Node.UnaryExpression(unaryOperators, primaryExpression);
    }

    public Node.PrimaryExpression enterPrimaryExpression(IntentDSLParser.PrimaryExpressionContext ctx) {
        if (ctx.number() != null) {
            Word number = enterNumber(ctx.number());
            return new Node.Number(number);
        } else if (ctx.binaryExpression() != null) {
            return enterBinaryExpression(ctx.binaryExpression());
        } else if (ctx.unaryExpression() != null) {
            return enterUnaryExpression(ctx.unaryExpression());
        }

        return null;
    }

    public Node.WalletBalance enterWalletBalance(IntentDSLParser.WalletBalanceContext ctx) {
        Node.Wallet wallet = enterWallet(ctx.wallet());

        return new Node.WalletBalance(wallet);
    }

    public Node.AssetPrice enterAssetPrice(IntentDSLParser.AssetPriceContext ctx) {
        Word asset = new Word(ctx.asset().getText(), Type.ASSET);
        Word platform = new Word(ctx.platform().getText(), Type.ASSET);

        return new Node.AssetPrice(asset, platform);
    }

    public Word enterNumber(IntentDSLParser.NumberContext ctx) {
        if (ctx.DEC_INT() != null) {
            return new Word(ctx.DEC_INT().getText(), Type.DEC_INT);
        } else {
            return new Word(ctx.DEC_FLOAT().getText(), Type.DEC_FLOAT);
        }
    }

}
