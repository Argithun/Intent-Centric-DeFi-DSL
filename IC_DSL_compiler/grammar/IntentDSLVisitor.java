// Generated from C:/IntentCentricDSL/Intent-Centric-DeFi-DSL/IC_DSL_compiler/grammar/grammar_design\IntentDSL.g4 by ANTLR 4.12.0
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link IntentDSLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface IntentDSLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(IntentDSLParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(IntentDSLParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#timeCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimeCondition(IntentDSLParser.TimeConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#orExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(IntentDSLParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(IntentDSLParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(IntentDSLParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#comparisonElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonElement(IntentDSLParser.ComparisonElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#binaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpression(IntentDSLParser.BinaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#lowBinaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLowBinaryExpression(IntentDSLParser.LowBinaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(IntentDSLParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(IntentDSLParser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#logicalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOperator(IntentDSLParser.LogicalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(IntentDSLParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#highBinaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHighBinaryOperator(IntentDSLParser.HighBinaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#lowBinaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLowBinaryOperator(IntentDSLParser.LowBinaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#unaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(IntentDSLParser.UnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(IntentDSLParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#triggerStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTriggerStatement(IntentDSLParser.TriggerStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(IntentDSLParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#transferStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransferStatement(IntentDSLParser.TransferStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#borrowStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBorrowStatement(IntentDSLParser.BorrowStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#repayBorrowStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepayBorrowStatement(IntentDSLParser.RepayBorrowStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#stakeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStakeStatement(IntentDSLParser.StakeStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#swapStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwapStatement(IntentDSLParser.SwapStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#addLiquidityStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddLiquidityStatement(IntentDSLParser.AddLiquidityStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#removeLiquidityStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveLiquidityStatement(IntentDSLParser.RemoveLiquidityStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#walletBalance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWalletBalance(IntentDSLParser.WalletBalanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#assetPrice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssetPrice(IntentDSLParser.AssetPriceContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#amount}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAmount(IntentDSLParser.AmountContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#asset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsset(IntentDSLParser.AssetContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#pair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(IntentDSLParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#wallet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWallet(IntentDSLParser.WalletContext ctx);
	/**
	 * Visit a parse tree produced by {@link IntentDSLParser#platform}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlatform(IntentDSLParser.PlatformContext ctx);
}