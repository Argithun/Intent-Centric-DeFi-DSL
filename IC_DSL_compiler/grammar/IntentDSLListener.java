// Generated from C:/IntentCentricDSL/Intent-Centric-DeFi-DSL/IC_DSL_compiler/grammar/grammar_design\IntentDSL.g4 by ANTLR 4.12.0
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IntentDSLParser}.
 */
public interface IntentDSLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(IntentDSLParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(IntentDSLParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(IntentDSLParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(IntentDSLParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#timeCondition}.
	 * @param ctx the parse tree
	 */
	void enterTimeCondition(IntentDSLParser.TimeConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#timeCondition}.
	 * @param ctx the parse tree
	 */
	void exitTimeCondition(IntentDSLParser.TimeConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(IntentDSLParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(IntentDSLParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(IntentDSLParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(IntentDSLParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(IntentDSLParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(IntentDSLParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#comparisonElement}.
	 * @param ctx the parse tree
	 */
	void enterComparisonElement(IntentDSLParser.ComparisonElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#comparisonElement}.
	 * @param ctx the parse tree
	 */
	void exitComparisonElement(IntentDSLParser.ComparisonElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#binaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpression(IntentDSLParser.BinaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#binaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpression(IntentDSLParser.BinaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#lowBinaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterLowBinaryExpression(IntentDSLParser.LowBinaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#lowBinaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitLowBinaryExpression(IntentDSLParser.LowBinaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(IntentDSLParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(IntentDSLParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(IntentDSLParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(IntentDSLParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperator(IntentDSLParser.LogicalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperator(IntentDSLParser.LogicalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(IntentDSLParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(IntentDSLParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#highBinaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterHighBinaryOperator(IntentDSLParser.HighBinaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#highBinaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitHighBinaryOperator(IntentDSLParser.HighBinaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#lowBinaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterLowBinaryOperator(IntentDSLParser.LowBinaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#lowBinaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitLowBinaryOperator(IntentDSLParser.LowBinaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperator(IntentDSLParser.UnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperator(IntentDSLParser.UnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(IntentDSLParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(IntentDSLParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#triggerStatement}.
	 * @param ctx the parse tree
	 */
	void enterTriggerStatement(IntentDSLParser.TriggerStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#triggerStatement}.
	 * @param ctx the parse tree
	 */
	void exitTriggerStatement(IntentDSLParser.TriggerStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(IntentDSLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(IntentDSLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#accountStatement}.
	 * @param ctx the parse tree
	 */
	void enterAccountStatement(IntentDSLParser.AccountStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#accountStatement}.
	 * @param ctx the parse tree
	 */
	void exitAccountStatement(IntentDSLParser.AccountStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#transferStatement}.
	 * @param ctx the parse tree
	 */
	void enterTransferStatement(IntentDSLParser.TransferStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#transferStatement}.
	 * @param ctx the parse tree
	 */
	void exitTransferStatement(IntentDSLParser.TransferStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#borrowStatement}.
	 * @param ctx the parse tree
	 */
	void enterBorrowStatement(IntentDSLParser.BorrowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#borrowStatement}.
	 * @param ctx the parse tree
	 */
	void exitBorrowStatement(IntentDSLParser.BorrowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#repayBorrowStatement}.
	 * @param ctx the parse tree
	 */
	void enterRepayBorrowStatement(IntentDSLParser.RepayBorrowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#repayBorrowStatement}.
	 * @param ctx the parse tree
	 */
	void exitRepayBorrowStatement(IntentDSLParser.RepayBorrowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#swapStatement}.
	 * @param ctx the parse tree
	 */
	void enterSwapStatement(IntentDSLParser.SwapStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#swapStatement}.
	 * @param ctx the parse tree
	 */
	void exitSwapStatement(IntentDSLParser.SwapStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#addLiquidityStatement}.
	 * @param ctx the parse tree
	 */
	void enterAddLiquidityStatement(IntentDSLParser.AddLiquidityStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#addLiquidityStatement}.
	 * @param ctx the parse tree
	 */
	void exitAddLiquidityStatement(IntentDSLParser.AddLiquidityStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#removeLiquidityStatement}.
	 * @param ctx the parse tree
	 */
	void enterRemoveLiquidityStatement(IntentDSLParser.RemoveLiquidityStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#removeLiquidityStatement}.
	 * @param ctx the parse tree
	 */
	void exitRemoveLiquidityStatement(IntentDSLParser.RemoveLiquidityStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#stakeStatement}.
	 * @param ctx the parse tree
	 */
	void enterStakeStatement(IntentDSLParser.StakeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#stakeStatement}.
	 * @param ctx the parse tree
	 */
	void exitStakeStatement(IntentDSLParser.StakeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#stakeStrategy}.
	 * @param ctx the parse tree
	 */
	void enterStakeStrategy(IntentDSLParser.StakeStrategyContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#stakeStrategy}.
	 * @param ctx the parse tree
	 */
	void exitStakeStrategy(IntentDSLParser.StakeStrategyContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#stakeStrategyQualifiers}.
	 * @param ctx the parse tree
	 */
	void enterStakeStrategyQualifiers(IntentDSLParser.StakeStrategyQualifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#stakeStrategyQualifiers}.
	 * @param ctx the parse tree
	 */
	void exitStakeStrategyQualifiers(IntentDSLParser.StakeStrategyQualifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#buyNFTStatement}.
	 * @param ctx the parse tree
	 */
	void enterBuyNFTStatement(IntentDSLParser.BuyNFTStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#buyNFTStatement}.
	 * @param ctx the parse tree
	 */
	void exitBuyNFTStatement(IntentDSLParser.BuyNFTStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#sellNFTStatement}.
	 * @param ctx the parse tree
	 */
	void enterSellNFTStatement(IntentDSLParser.SellNFTStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#sellNFTStatement}.
	 * @param ctx the parse tree
	 */
	void exitSellNFTStatement(IntentDSLParser.SellNFTStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#sellNFTStartegy}.
	 * @param ctx the parse tree
	 */
	void enterSellNFTStartegy(IntentDSLParser.SellNFTStartegyContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#sellNFTStartegy}.
	 * @param ctx the parse tree
	 */
	void exitSellNFTStartegy(IntentDSLParser.SellNFTStartegyContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#sellNFTStrategyQualifiers}.
	 * @param ctx the parse tree
	 */
	void enterSellNFTStrategyQualifiers(IntentDSLParser.SellNFTStrategyQualifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#sellNFTStrategyQualifiers}.
	 * @param ctx the parse tree
	 */
	void exitSellNFTStrategyQualifiers(IntentDSLParser.SellNFTStrategyQualifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#walletBalance}.
	 * @param ctx the parse tree
	 */
	void enterWalletBalance(IntentDSLParser.WalletBalanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#walletBalance}.
	 * @param ctx the parse tree
	 */
	void exitWalletBalance(IntentDSLParser.WalletBalanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#assetPrice}.
	 * @param ctx the parse tree
	 */
	void enterAssetPrice(IntentDSLParser.AssetPriceContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#assetPrice}.
	 * @param ctx the parse tree
	 */
	void exitAssetPrice(IntentDSLParser.AssetPriceContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#amount}.
	 * @param ctx the parse tree
	 */
	void enterAmount(IntentDSLParser.AmountContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#amount}.
	 * @param ctx the parse tree
	 */
	void exitAmount(IntentDSLParser.AmountContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#asset}.
	 * @param ctx the parse tree
	 */
	void enterAsset(IntentDSLParser.AssetContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#asset}.
	 * @param ctx the parse tree
	 */
	void exitAsset(IntentDSLParser.AssetContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(IntentDSLParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(IntentDSLParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#wallet}.
	 * @param ctx the parse tree
	 */
	void enterWallet(IntentDSLParser.WalletContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#wallet}.
	 * @param ctx the parse tree
	 */
	void exitWallet(IntentDSLParser.WalletContext ctx);
	/**
	 * Enter a parse tree produced by {@link IntentDSLParser#platform}.
	 * @param ctx the parse tree
	 */
	void enterPlatform(IntentDSLParser.PlatformContext ctx);
	/**
	 * Exit a parse tree produced by {@link IntentDSLParser#platform}.
	 * @param ctx the parse tree
	 */
	void exitPlatform(IntentDSLParser.PlatformContext ctx);
}