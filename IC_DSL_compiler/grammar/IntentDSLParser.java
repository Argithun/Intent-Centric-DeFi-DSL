// Generated from C:/IntentCentricDSL/Intent-Centric-DeFi-DSL/IC_DSL_compiler/grammar/grammar_design\IntentDSL.g4 by ANTLR 4.12.0
package grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class IntentDSLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		LPARENT=39, RPARENT=40, LBRACK=41, RBRACK=42, LOGIC_AND=43, LOGIC_OR=44, 
		LOGIC_NOT=45, EQ=46, NEQ=47, LT=48, GT=49, LE=50, GE=51, ADD=52, SUB=53, 
		MUL=54, DIV=55, MOD=56, BALANCE=57, PRICE=58, SLIPPAGE=59, FEE=60, WALLET=61, 
		USDT=62, USDC=63, ETH=64, DAI=65, BTC=66, WBTC=67, WETH=68, UNI=69, SUSHI=70, 
		AAVE_token=71, MATIC=72, COMP=73, AAVE=74, UNISWAP=75, COMPOUND=76, YEARN=77, 
		SUSHISWAP=78, CURVE=79, ONEINCH=80, POLYGON=81, AVAX=82, IDENTIFIER=83, 
		PRIVATE_KEY=84, KEY=85, DEC_INT=86, DEC_FLOAT=87, TIME=88, SEMI=89, LINE_COMMENT=90, 
		COMMENT=91, BLANK=92, NFTQualifiers=93, NFTPlatform=94;
	public static final int
		RULE_program = 0, RULE_condition = 1, RULE_timeCondition = 2, RULE_orExpression = 3, 
		RULE_andExpression = 4, RULE_comparisonExpression = 5, RULE_comparisonElement = 6, 
		RULE_binaryExpression = 7, RULE_lowBinaryExpression = 8, RULE_unaryExpression = 9, 
		RULE_primaryExpression = 10, RULE_logicalOperator = 11, RULE_comparisonOperator = 12, 
		RULE_highBinaryOperator = 13, RULE_lowBinaryOperator = 14, RULE_unaryOperator = 15, 
		RULE_number = 16, RULE_triggerStatement = 17, RULE_statement = 18, RULE_accountStatement = 19, 
		RULE_transferStatement = 20, RULE_borrowStatement = 21, RULE_repayBorrowStatement = 22, 
		RULE_swapStatement = 23, RULE_addLiquidityStatement = 24, RULE_removeLiquidityStatement = 25, 
		RULE_stakeStatement = 26, RULE_stakeStrategy = 27, RULE_stakeStrategyQualifiers = 28, 
		RULE_buyNFTStatement = 29, RULE_sellNFTStatement = 30, RULE_sellNFTStartegy = 31, 
		RULE_sellNFTStrategyQualifiers = 32, RULE_walletBalance = 33, RULE_assetPrice = 34, 
		RULE_amount = 35, RULE_asset = 36, RULE_pair = 37, RULE_wallet = 38, RULE_platform = 39;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "condition", "timeCondition", "orExpression", "andExpression", 
			"comparisonExpression", "comparisonElement", "binaryExpression", "lowBinaryExpression", 
			"unaryExpression", "primaryExpression", "logicalOperator", "comparisonOperator", 
			"highBinaryOperator", "lowBinaryOperator", "unaryOperator", "number", 
			"triggerStatement", "statement", "accountStatement", "transferStatement", 
			"borrowStatement", "repayBorrowStatement", "swapStatement", "addLiquidityStatement", 
			"removeLiquidityStatement", "stakeStatement", "stakeStrategy", "stakeStrategyQualifiers", 
			"buyNFTStatement", "sellNFTStatement", "sellNFTStartegy", "sellNFTStrategyQualifiers", 
			"walletBalance", "assetPrice", "amount", "asset", "pair", "wallet", "platform"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'time before'", "'time after'", "'time during'", "'to'", "'trigger'", 
			"'then'", "'checking'", "'switch to account'", "'transfer'", "'from'", 
			"'borrow'", "'for'", "'using'", "'as collateral'", "'repay'", "'swap'", 
			"'on'", "'add'", "','", "'receiving liquidity token to'", "'remove'", 
			"'returning'", "'liquidity'", "'of token'", "'stake'", "'strategy'", 
			"'low-risk'", "'middle-risk'", "'high-risk'", "'short-term'", "'middle-term'", 
			"'long-term'", "'buy'", "'NFT using at most'", "'sell NFT'", "'in collection'", 
			"'time-saving'", "'profitable'", "'('", "')'", "'['", "']'", "'and'", 
			"'or'", "'not'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'+'", 
			"'-'", "'*'", "'/'", "'%'", "'balance'", "'price'", "'slippage'", "'fee'", 
			"'wallet'", "'USDT'", "'USDC'", "'ETH'", "'DAI'", "'BTC'", "'WBTC'", 
			"'WETH'", "'UNI'", "'SUSHI'", "'AAVE'", "'MATIC'", "'COMAP'", "'Aave'", 
			"'Uniswap'", "'Compound'", "'Yearn'", "'Sushiswap'", "'Curve'", "'1inch'", 
			"'Polygon'", "'Avax'", null, null, null, null, null, null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "LPARENT", "RPARENT", "LBRACK", "RBRACK", "LOGIC_AND", 
			"LOGIC_OR", "LOGIC_NOT", "EQ", "NEQ", "LT", "GT", "LE", "GE", "ADD", 
			"SUB", "MUL", "DIV", "MOD", "BALANCE", "PRICE", "SLIPPAGE", "FEE", "WALLET", 
			"USDT", "USDC", "ETH", "DAI", "BTC", "WBTC", "WETH", "UNI", "SUSHI", 
			"AAVE_token", "MATIC", "COMP", "AAVE", "UNISWAP", "COMPOUND", "YEARN", 
			"SUSHISWAP", "CURVE", "ONEINCH", "POLYGON", "AVAX", "IDENTIFIER", "PRIVATE_KEY", 
			"KEY", "DEC_INT", "DEC_FLOAT", "TIME", "SEMI", "LINE_COMMENT", "COMMENT", 
			"BLANK", "NFTQualifiers", "NFTPlatform"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IntentDSL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public IntentDSLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<TriggerStatementContext> triggerStatement() {
			return getRuleContexts(TriggerStatementContext.class);
		}
		public TriggerStatementContext triggerStatement(int i) {
			return getRuleContext(TriggerStatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(80);
				triggerStatement();
				}
				}
				setState(83); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 42985687840L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionContext extends ParserRuleContext {
		public OrExpressionContext orExpression() {
			return getRuleContext(OrExpressionContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			orExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TimeConditionContext extends ParserRuleContext {
		public List<TerminalNode> TIME() { return getTokens(IntentDSLParser.TIME); }
		public TerminalNode TIME(int i) {
			return getToken(IntentDSLParser.TIME, i);
		}
		public TimeConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timeCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterTimeCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitTimeCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitTimeCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TimeConditionContext timeCondition() throws RecognitionException {
		TimeConditionContext _localctx = new TimeConditionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_timeCondition);
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				match(T__0);
				setState(88);
				match(TIME);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				match(T__1);
				setState(90);
				match(TIME);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(91);
				match(T__2);
				setState(92);
				match(TIME);
				setState(93);
				match(T__3);
				setState(94);
				match(TIME);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrExpressionContext extends ParserRuleContext {
		public List<AndExpressionContext> andExpression() {
			return getRuleContexts(AndExpressionContext.class);
		}
		public AndExpressionContext andExpression(int i) {
			return getRuleContext(AndExpressionContext.class,i);
		}
		public List<TerminalNode> LOGIC_OR() { return getTokens(IntentDSLParser.LOGIC_OR); }
		public TerminalNode LOGIC_OR(int i) {
			return getToken(IntentDSLParser.LOGIC_OR, i);
		}
		public OrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExpressionContext orExpression() throws RecognitionException {
		OrExpressionContext _localctx = new OrExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_orExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			andExpression();
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LOGIC_OR) {
				{
				{
				setState(98);
				match(LOGIC_OR);
				setState(99);
				andExpression();
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AndExpressionContext extends ParserRuleContext {
		public List<ComparisonExpressionContext> comparisonExpression() {
			return getRuleContexts(ComparisonExpressionContext.class);
		}
		public ComparisonExpressionContext comparisonExpression(int i) {
			return getRuleContext(ComparisonExpressionContext.class,i);
		}
		public List<TimeConditionContext> timeCondition() {
			return getRuleContexts(TimeConditionContext.class);
		}
		public TimeConditionContext timeCondition(int i) {
			return getRuleContext(TimeConditionContext.class,i);
		}
		public List<TerminalNode> LOGIC_AND() { return getTokens(IntentDSLParser.LOGIC_AND); }
		public TerminalNode LOGIC_AND(int i) {
			return getToken(IntentDSLParser.LOGIC_AND, i);
		}
		public AndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExpressionContext andExpression() throws RecognitionException {
		AndExpressionContext _localctx = new AndExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_andExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BALANCE:
			case PRICE:
			case SLIPPAGE:
			case FEE:
			case DEC_INT:
			case DEC_FLOAT:
				{
				setState(105);
				comparisonExpression();
				}
				break;
			case T__0:
			case T__1:
			case T__2:
				{
				setState(106);
				timeCondition();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LOGIC_AND) {
				{
				{
				setState(109);
				match(LOGIC_AND);
				setState(112);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BALANCE:
				case PRICE:
				case SLIPPAGE:
				case FEE:
				case DEC_INT:
				case DEC_FLOAT:
					{
					setState(110);
					comparisonExpression();
					}
					break;
				case T__0:
				case T__1:
				case T__2:
					{
					setState(111);
					timeCondition();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonExpressionContext extends ParserRuleContext {
		public List<ComparisonElementContext> comparisonElement() {
			return getRuleContexts(ComparisonElementContext.class);
		}
		public ComparisonElementContext comparisonElement(int i) {
			return getRuleContext(ComparisonElementContext.class,i);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public ComparisonExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterComparisonExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitComparisonExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitComparisonExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonExpressionContext comparisonExpression() throws RecognitionException {
		ComparisonExpressionContext _localctx = new ComparisonExpressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_comparisonExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			comparisonElement();
			setState(120);
			comparisonOperator();
			setState(121);
			comparisonElement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonElementContext extends ParserRuleContext {
		public WalletBalanceContext walletBalance() {
			return getRuleContext(WalletBalanceContext.class,0);
		}
		public AssetPriceContext assetPrice() {
			return getRuleContext(AssetPriceContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public AssetContext asset() {
			return getRuleContext(AssetContext.class,0);
		}
		public TerminalNode SLIPPAGE() { return getToken(IntentDSLParser.SLIPPAGE, 0); }
		public TerminalNode FEE() { return getToken(IntentDSLParser.FEE, 0); }
		public ComparisonElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterComparisonElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitComparisonElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitComparisonElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonElementContext comparisonElement() throws RecognitionException {
		ComparisonElementContext _localctx = new ComparisonElementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_comparisonElement);
		try {
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				walletBalance();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				assetPrice();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(125);
				number();
				setState(126);
				asset();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(128);
				number();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(129);
				match(SLIPPAGE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(130);
				match(FEE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BinaryExpressionContext extends ParserRuleContext {
		public List<LowBinaryExpressionContext> lowBinaryExpression() {
			return getRuleContexts(LowBinaryExpressionContext.class);
		}
		public LowBinaryExpressionContext lowBinaryExpression(int i) {
			return getRuleContext(LowBinaryExpressionContext.class,i);
		}
		public List<HighBinaryOperatorContext> highBinaryOperator() {
			return getRuleContexts(HighBinaryOperatorContext.class);
		}
		public HighBinaryOperatorContext highBinaryOperator(int i) {
			return getRuleContext(HighBinaryOperatorContext.class,i);
		}
		public BinaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterBinaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitBinaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitBinaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryExpressionContext binaryExpression() throws RecognitionException {
		BinaryExpressionContext _localctx = new BinaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_binaryExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			lowBinaryExpression();
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 126100789566373888L) != 0)) {
				{
				{
				setState(134);
				highBinaryOperator();
				setState(135);
				lowBinaryExpression();
				}
				}
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LowBinaryExpressionContext extends ParserRuleContext {
		public List<UnaryExpressionContext> unaryExpression() {
			return getRuleContexts(UnaryExpressionContext.class);
		}
		public UnaryExpressionContext unaryExpression(int i) {
			return getRuleContext(UnaryExpressionContext.class,i);
		}
		public List<LowBinaryOperatorContext> lowBinaryOperator() {
			return getRuleContexts(LowBinaryOperatorContext.class);
		}
		public LowBinaryOperatorContext lowBinaryOperator(int i) {
			return getRuleContext(LowBinaryOperatorContext.class,i);
		}
		public LowBinaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lowBinaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterLowBinaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitLowBinaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitLowBinaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LowBinaryExpressionContext lowBinaryExpression() throws RecognitionException {
		LowBinaryExpressionContext _localctx = new LowBinaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_lowBinaryExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			unaryExpression();
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD || _la==SUB) {
				{
				{
				setState(143);
				lowBinaryOperator();
				setState(144);
				unaryExpression();
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public List<UnaryOperatorContext> unaryOperator() {
			return getRuleContexts(UnaryOperatorContext.class);
		}
		public UnaryOperatorContext unaryOperator(int i) {
			return getRuleContext(UnaryOperatorContext.class,i);
		}
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitUnaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_unaryExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 13545983254200320L) != 0)) {
				{
				{
				setState(151);
				unaryOperator();
				}
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(157);
			primaryExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryExpressionContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode LPARENT() { return getToken(IntentDSLParser.LPARENT, 0); }
		public TerminalNode RPARENT() { return getToken(IntentDSLParser.RPARENT, 0); }
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitPrimaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitPrimaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_primaryExpression);
		try {
			setState(167);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEC_INT:
			case DEC_FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				number();
				}
				break;
			case LPARENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(160);
				match(LPARENT);
				setState(163);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(161);
					binaryExpression();
					}
					break;
				case 2:
					{
					setState(162);
					unaryExpression();
					}
					break;
				}
				setState(165);
				match(RPARENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOperatorContext extends ParserRuleContext {
		public TerminalNode LOGIC_AND() { return getToken(IntentDSLParser.LOGIC_AND, 0); }
		public TerminalNode LOGIC_OR() { return getToken(IntentDSLParser.LOGIC_OR, 0); }
		public LogicalOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterLogicalOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitLogicalOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitLogicalOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOperatorContext logicalOperator() throws RecognitionException {
		LogicalOperatorContext _localctx = new LogicalOperatorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_logicalOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			_la = _input.LA(1);
			if ( !(_la==LOGIC_AND || _la==LOGIC_OR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonOperatorContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(IntentDSLParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(IntentDSLParser.NEQ, 0); }
		public TerminalNode LT() { return getToken(IntentDSLParser.LT, 0); }
		public TerminalNode GT() { return getToken(IntentDSLParser.GT, 0); }
		public TerminalNode LE() { return getToken(IntentDSLParser.LE, 0); }
		public TerminalNode GE() { return getToken(IntentDSLParser.GE, 0); }
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitComparisonOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4433230883192832L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HighBinaryOperatorContext extends ParserRuleContext {
		public TerminalNode MUL() { return getToken(IntentDSLParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(IntentDSLParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(IntentDSLParser.MOD, 0); }
		public HighBinaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_highBinaryOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterHighBinaryOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitHighBinaryOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitHighBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HighBinaryOperatorContext highBinaryOperator() throws RecognitionException {
		HighBinaryOperatorContext _localctx = new HighBinaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_highBinaryOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 126100789566373888L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LowBinaryOperatorContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(IntentDSLParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(IntentDSLParser.SUB, 0); }
		public LowBinaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lowBinaryOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterLowBinaryOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitLowBinaryOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitLowBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LowBinaryOperatorContext lowBinaryOperator() throws RecognitionException {
		LowBinaryOperatorContext _localctx = new LowBinaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_lowBinaryOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			_la = _input.LA(1);
			if ( !(_la==ADD || _la==SUB) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryOperatorContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(IntentDSLParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(IntentDSLParser.SUB, 0); }
		public TerminalNode LOGIC_NOT() { return getToken(IntentDSLParser.LOGIC_NOT, 0); }
		public UnaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterUnaryOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitUnaryOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOperatorContext unaryOperator() throws RecognitionException {
		UnaryOperatorContext _localctx = new UnaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_unaryOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 13545983254200320L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode DEC_INT() { return getToken(IntentDSLParser.DEC_INT, 0); }
		public TerminalNode DEC_FLOAT() { return getToken(IntentDSLParser.DEC_FLOAT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_la = _input.LA(1);
			if ( !(_la==DEC_INT || _la==DEC_FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TriggerStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(IntentDSLParser.SEMI, 0); }
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public TriggerStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triggerStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterTriggerStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitTriggerStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitTriggerStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TriggerStatementContext triggerStatement() throws RecognitionException {
		TriggerStatementContext _localctx = new TriggerStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_triggerStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(181);
				match(T__4);
				setState(182);
				condition();
				setState(183);
				match(T__5);
				}
			}

			setState(187);
			statement();
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(188);
				match(T__6);
				setState(189);
				condition();
				}
			}

			setState(192);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public AccountStatementContext accountStatement() {
			return getRuleContext(AccountStatementContext.class,0);
		}
		public TransferStatementContext transferStatement() {
			return getRuleContext(TransferStatementContext.class,0);
		}
		public BorrowStatementContext borrowStatement() {
			return getRuleContext(BorrowStatementContext.class,0);
		}
		public RepayBorrowStatementContext repayBorrowStatement() {
			return getRuleContext(RepayBorrowStatementContext.class,0);
		}
		public SwapStatementContext swapStatement() {
			return getRuleContext(SwapStatementContext.class,0);
		}
		public AddLiquidityStatementContext addLiquidityStatement() {
			return getRuleContext(AddLiquidityStatementContext.class,0);
		}
		public RemoveLiquidityStatementContext removeLiquidityStatement() {
			return getRuleContext(RemoveLiquidityStatementContext.class,0);
		}
		public StakeStatementContext stakeStatement() {
			return getRuleContext(StakeStatementContext.class,0);
		}
		public BuyNFTStatementContext buyNFTStatement() {
			return getRuleContext(BuyNFTStatementContext.class,0);
		}
		public SellNFTStatementContext sellNFTStatement() {
			return getRuleContext(SellNFTStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_statement);
		try {
			setState(204);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(194);
				accountStatement();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(195);
				transferStatement();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 3);
				{
				setState(196);
				borrowStatement();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 4);
				{
				setState(197);
				repayBorrowStatement();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 5);
				{
				setState(198);
				swapStatement();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 6);
				{
				setState(199);
				addLiquidityStatement();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 7);
				{
				setState(200);
				removeLiquidityStatement();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 8);
				{
				setState(201);
				stakeStatement();
				}
				break;
			case T__32:
				enterOuterAlt(_localctx, 9);
				{
				setState(202);
				buyNFTStatement();
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 10);
				{
				setState(203);
				sellNFTStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AccountStatementContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(IntentDSLParser.LBRACK, 0); }
		public TerminalNode PRIVATE_KEY() { return getToken(IntentDSLParser.PRIVATE_KEY, 0); }
		public TerminalNode RBRACK() { return getToken(IntentDSLParser.RBRACK, 0); }
		public AccountStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accountStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterAccountStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitAccountStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitAccountStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccountStatementContext accountStatement() throws RecognitionException {
		AccountStatementContext _localctx = new AccountStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_accountStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(T__7);
			setState(207);
			match(LBRACK);
			setState(208);
			match(PRIVATE_KEY);
			setState(209);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TransferStatementContext extends ParserRuleContext {
		public AmountContext amount() {
			return getRuleContext(AmountContext.class,0);
		}
		public List<WalletContext> wallet() {
			return getRuleContexts(WalletContext.class);
		}
		public WalletContext wallet(int i) {
			return getRuleContext(WalletContext.class,i);
		}
		public TransferStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transferStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterTransferStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitTransferStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitTransferStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransferStatementContext transferStatement() throws RecognitionException {
		TransferStatementContext _localctx = new TransferStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_transferStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(T__8);
			setState(212);
			amount();
			setState(213);
			match(T__9);
			setState(214);
			wallet();
			setState(215);
			match(T__3);
			setState(216);
			wallet();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BorrowStatementContext extends ParserRuleContext {
		public List<AmountContext> amount() {
			return getRuleContexts(AmountContext.class);
		}
		public AmountContext amount(int i) {
			return getRuleContext(AmountContext.class,i);
		}
		public List<WalletContext> wallet() {
			return getRuleContexts(WalletContext.class);
		}
		public WalletContext wallet(int i) {
			return getRuleContext(WalletContext.class,i);
		}
		public PlatformContext platform() {
			return getRuleContext(PlatformContext.class,0);
		}
		public BorrowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_borrowStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterBorrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitBorrowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitBorrowStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BorrowStatementContext borrowStatement() throws RecognitionException {
		BorrowStatementContext _localctx = new BorrowStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_borrowStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(T__10);
			setState(219);
			amount();
			setState(220);
			match(T__11);
			setState(221);
			wallet();
			setState(222);
			match(T__9);
			setState(223);
			platform();
			setState(224);
			match(T__12);
			setState(225);
			amount();
			setState(226);
			match(T__9);
			setState(227);
			wallet();
			setState(228);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RepayBorrowStatementContext extends ParserRuleContext {
		public AmountContext amount() {
			return getRuleContext(AmountContext.class,0);
		}
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
		}
		public PlatformContext platform() {
			return getRuleContext(PlatformContext.class,0);
		}
		public RepayBorrowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repayBorrowStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterRepayBorrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitRepayBorrowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitRepayBorrowStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepayBorrowStatementContext repayBorrowStatement() throws RecognitionException {
		RepayBorrowStatementContext _localctx = new RepayBorrowStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_repayBorrowStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(T__14);
			setState(231);
			amount();
			setState(232);
			match(T__9);
			setState(233);
			wallet();
			setState(234);
			match(T__3);
			setState(235);
			platform();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwapStatementContext extends ParserRuleContext {
		public AmountContext amount() {
			return getRuleContext(AmountContext.class,0);
		}
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
		}
		public AssetContext asset() {
			return getRuleContext(AssetContext.class,0);
		}
		public PlatformContext platform() {
			return getRuleContext(PlatformContext.class,0);
		}
		public SwapStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_swapStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterSwapStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitSwapStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitSwapStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwapStatementContext swapStatement() throws RecognitionException {
		SwapStatementContext _localctx = new SwapStatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_swapStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(T__15);
			setState(238);
			amount();
			setState(239);
			match(T__9);
			setState(240);
			wallet();
			setState(241);
			match(T__11);
			setState(242);
			asset();
			setState(243);
			match(T__16);
			setState(244);
			platform();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AddLiquidityStatementContext extends ParserRuleContext {
		public List<AmountContext> amount() {
			return getRuleContexts(AmountContext.class);
		}
		public AmountContext amount(int i) {
			return getRuleContext(AmountContext.class,i);
		}
		public PlatformContext platform() {
			return getRuleContext(PlatformContext.class,0);
		}
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
		}
		public AddLiquidityStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addLiquidityStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterAddLiquidityStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitAddLiquidityStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitAddLiquidityStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddLiquidityStatementContext addLiquidityStatement() throws RecognitionException {
		AddLiquidityStatementContext _localctx = new AddLiquidityStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_addLiquidityStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(T__17);
			setState(247);
			amount();
			setState(248);
			match(T__18);
			setState(249);
			amount();
			setState(250);
			match(T__3);
			setState(251);
			platform();
			setState(252);
			match(T__19);
			setState(253);
			wallet();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RemoveLiquidityStatementContext extends ParserRuleContext {
		public List<AmountContext> amount() {
			return getRuleContexts(AmountContext.class);
		}
		public AmountContext amount(int i) {
			return getRuleContext(AmountContext.class,i);
		}
		public PlatformContext platform() {
			return getRuleContext(PlatformContext.class,0);
		}
		public TerminalNode DEC_INT() { return getToken(IntentDSLParser.DEC_INT, 0); }
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(IntentDSLParser.LBRACK, 0); }
		public TerminalNode KEY() { return getToken(IntentDSLParser.KEY, 0); }
		public TerminalNode RBRACK() { return getToken(IntentDSLParser.RBRACK, 0); }
		public RemoveLiquidityStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_removeLiquidityStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterRemoveLiquidityStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitRemoveLiquidityStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitRemoveLiquidityStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoveLiquidityStatementContext removeLiquidityStatement() throws RecognitionException {
		RemoveLiquidityStatementContext _localctx = new RemoveLiquidityStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_removeLiquidityStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(T__20);
			setState(256);
			amount();
			setState(257);
			match(T__18);
			setState(258);
			amount();
			setState(259);
			match(T__9);
			setState(260);
			platform();
			setState(261);
			match(T__21);
			setState(262);
			match(DEC_INT);
			setState(263);
			match(T__22);
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(264);
				match(T__23);
				setState(265);
				match(LBRACK);
				setState(266);
				match(KEY);
				setState(267);
				match(RBRACK);
				}
			}

			setState(270);
			match(T__9);
			setState(271);
			wallet();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StakeStatementContext extends ParserRuleContext {
		public AmountContext amount() {
			return getRuleContext(AmountContext.class,0);
		}
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
		}
		public StakeStrategyContext stakeStrategy() {
			return getRuleContext(StakeStrategyContext.class,0);
		}
		public StakeStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stakeStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterStakeStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitStakeStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitStakeStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StakeStatementContext stakeStatement() throws RecognitionException {
		StakeStatementContext _localctx = new StakeStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_stakeStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(T__24);
			setState(274);
			amount();
			setState(275);
			match(T__9);
			setState(276);
			wallet();
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(277);
				stakeStrategy();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StakeStrategyContext extends ParserRuleContext {
		public List<StakeStrategyQualifiersContext> stakeStrategyQualifiers() {
			return getRuleContexts(StakeStrategyQualifiersContext.class);
		}
		public StakeStrategyQualifiersContext stakeStrategyQualifiers(int i) {
			return getRuleContext(StakeStrategyQualifiersContext.class,i);
		}
		public StakeStrategyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stakeStrategy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterStakeStrategy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitStakeStrategy(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitStakeStrategy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StakeStrategyContext stakeStrategy() throws RecognitionException {
		StakeStrategyContext _localctx = new StakeStrategyContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_stakeStrategy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(T__12);
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8455716864L) != 0)) {
				{
				{
				setState(281);
				stakeStrategyQualifiers();
				}
				}
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(287);
			match(T__25);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StakeStrategyQualifiersContext extends ParserRuleContext {
		public StakeStrategyQualifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stakeStrategyQualifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterStakeStrategyQualifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitStakeStrategyQualifiers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitStakeStrategyQualifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StakeStrategyQualifiersContext stakeStrategyQualifiers() throws RecognitionException {
		StakeStrategyQualifiersContext _localctx = new StakeStrategyQualifiersContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_stakeStrategyQualifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8455716864L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BuyNFTStatementContext extends ParserRuleContext {
		public AmountContext amount() {
			return getRuleContext(AmountContext.class,0);
		}
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
		}
		public List<TerminalNode> NFTQualifiers() { return getTokens(IntentDSLParser.NFTQualifiers); }
		public TerminalNode NFTQualifiers(int i) {
			return getToken(IntentDSLParser.NFTQualifiers, i);
		}
		public BuyNFTStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_buyNFTStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterBuyNFTStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitBuyNFTStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitBuyNFTStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BuyNFTStatementContext buyNFTStatement() throws RecognitionException {
		BuyNFTStatementContext _localctx = new BuyNFTStatementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_buyNFTStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(T__32);
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NFTQualifiers) {
				{
				{
				setState(292);
				match(NFTQualifiers);
				}
				}
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(298);
			match(T__33);
			setState(299);
			amount();
			setState(300);
			match(T__9);
			setState(301);
			wallet();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SellNFTStatementContext extends ParserRuleContext {
		public List<TerminalNode> LBRACK() { return getTokens(IntentDSLParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(IntentDSLParser.LBRACK, i);
		}
		public List<TerminalNode> KEY() { return getTokens(IntentDSLParser.KEY); }
		public TerminalNode KEY(int i) {
			return getToken(IntentDSLParser.KEY, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(IntentDSLParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(IntentDSLParser.RBRACK, i);
		}
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
		}
		public SellNFTStartegyContext sellNFTStartegy() {
			return getRuleContext(SellNFTStartegyContext.class,0);
		}
		public SellNFTStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sellNFTStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterSellNFTStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitSellNFTStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitSellNFTStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SellNFTStatementContext sellNFTStatement() throws RecognitionException {
		SellNFTStatementContext _localctx = new SellNFTStatementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_sellNFTStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			match(T__34);
			setState(304);
			match(LBRACK);
			setState(305);
			match(KEY);
			setState(306);
			match(RBRACK);
			setState(307);
			match(T__35);
			setState(308);
			match(LBRACK);
			setState(309);
			match(KEY);
			setState(310);
			match(RBRACK);
			setState(311);
			match(T__9);
			setState(312);
			wallet();
			setState(314);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(313);
				sellNFTStartegy();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SellNFTStartegyContext extends ParserRuleContext {
		public List<SellNFTStrategyQualifiersContext> sellNFTStrategyQualifiers() {
			return getRuleContexts(SellNFTStrategyQualifiersContext.class);
		}
		public SellNFTStrategyQualifiersContext sellNFTStrategyQualifiers(int i) {
			return getRuleContext(SellNFTStrategyQualifiersContext.class,i);
		}
		public SellNFTStartegyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sellNFTStartegy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterSellNFTStartegy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitSellNFTStartegy(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitSellNFTStartegy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SellNFTStartegyContext sellNFTStartegy() throws RecognitionException {
		SellNFTStartegyContext _localctx = new SellNFTStartegyContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_sellNFTStartegy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			match(T__12);
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__36 || _la==T__37) {
				{
				{
				setState(317);
				sellNFTStrategyQualifiers();
				}
				}
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(323);
			match(T__25);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SellNFTStrategyQualifiersContext extends ParserRuleContext {
		public SellNFTStrategyQualifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sellNFTStrategyQualifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterSellNFTStrategyQualifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitSellNFTStrategyQualifiers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitSellNFTStrategyQualifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SellNFTStrategyQualifiersContext sellNFTStrategyQualifiers() throws RecognitionException {
		SellNFTStrategyQualifiersContext _localctx = new SellNFTStrategyQualifiersContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_sellNFTStrategyQualifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			_la = _input.LA(1);
			if ( !(_la==T__36 || _la==T__37) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WalletBalanceContext extends ParserRuleContext {
		public TerminalNode BALANCE() { return getToken(IntentDSLParser.BALANCE, 0); }
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
		}
		public WalletBalanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_walletBalance; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterWalletBalance(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitWalletBalance(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitWalletBalance(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WalletBalanceContext walletBalance() throws RecognitionException {
		WalletBalanceContext _localctx = new WalletBalanceContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_walletBalance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			match(BALANCE);
			setState(328);
			wallet();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssetPriceContext extends ParserRuleContext {
		public TerminalNode PRICE() { return getToken(IntentDSLParser.PRICE, 0); }
		public AssetContext asset() {
			return getRuleContext(AssetContext.class,0);
		}
		public PlatformContext platform() {
			return getRuleContext(PlatformContext.class,0);
		}
		public AssetPriceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assetPrice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterAssetPrice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitAssetPrice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitAssetPrice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssetPriceContext assetPrice() throws RecognitionException {
		AssetPriceContext _localctx = new AssetPriceContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_assetPrice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(PRICE);
			setState(331);
			asset();
			setState(332);
			match(T__16);
			setState(333);
			platform();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AmountContext extends ParserRuleContext {
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public AssetContext asset() {
			return getRuleContext(AssetContext.class,0);
		}
		public AmountContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_amount; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterAmount(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitAmount(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitAmount(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AmountContext amount() throws RecognitionException {
		AmountContext _localctx = new AmountContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_amount);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
			binaryExpression();
			setState(336);
			asset();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssetContext extends ParserRuleContext {
		public TerminalNode USDT() { return getToken(IntentDSLParser.USDT, 0); }
		public TerminalNode USDC() { return getToken(IntentDSLParser.USDC, 0); }
		public TerminalNode ETH() { return getToken(IntentDSLParser.ETH, 0); }
		public TerminalNode DAI() { return getToken(IntentDSLParser.DAI, 0); }
		public TerminalNode BTC() { return getToken(IntentDSLParser.BTC, 0); }
		public TerminalNode WBTC() { return getToken(IntentDSLParser.WBTC, 0); }
		public TerminalNode UNI() { return getToken(IntentDSLParser.UNI, 0); }
		public TerminalNode SUSHI() { return getToken(IntentDSLParser.SUSHI, 0); }
		public TerminalNode AAVE_token() { return getToken(IntentDSLParser.AAVE_token, 0); }
		public TerminalNode MATIC() { return getToken(IntentDSLParser.MATIC, 0); }
		public TerminalNode COMP() { return getToken(IntentDSLParser.COMP, 0); }
		public TerminalNode WETH() { return getToken(IntentDSLParser.WETH, 0); }
		public AssetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asset; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterAsset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitAsset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitAsset(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssetContext asset() throws RecognitionException {
		AssetContext _localctx = new AssetContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_asset);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			_la = _input.LA(1);
			if ( !(((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & 4095L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PairContext extends ParserRuleContext {
		public List<AssetContext> asset() {
			return getRuleContexts(AssetContext.class);
		}
		public AssetContext asset(int i) {
			return getRuleContext(AssetContext.class,i);
		}
		public TerminalNode LOGIC_AND() { return getToken(IntentDSLParser.LOGIC_AND, 0); }
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			asset();
			setState(341);
			match(LOGIC_AND);
			setState(342);
			asset();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WalletContext extends ParserRuleContext {
		public TerminalNode WALLET() { return getToken(IntentDSLParser.WALLET, 0); }
		public TerminalNode LBRACK() { return getToken(IntentDSLParser.LBRACK, 0); }
		public TerminalNode KEY() { return getToken(IntentDSLParser.KEY, 0); }
		public TerminalNode RBRACK() { return getToken(IntentDSLParser.RBRACK, 0); }
		public WalletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wallet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterWallet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitWallet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitWallet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WalletContext wallet() throws RecognitionException {
		WalletContext _localctx = new WalletContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_wallet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			match(WALLET);
			setState(345);
			match(LBRACK);
			setState(346);
			match(KEY);
			setState(347);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PlatformContext extends ParserRuleContext {
		public TerminalNode AAVE() { return getToken(IntentDSLParser.AAVE, 0); }
		public TerminalNode UNISWAP() { return getToken(IntentDSLParser.UNISWAP, 0); }
		public TerminalNode COMPOUND() { return getToken(IntentDSLParser.COMPOUND, 0); }
		public TerminalNode YEARN() { return getToken(IntentDSLParser.YEARN, 0); }
		public TerminalNode SUSHISWAP() { return getToken(IntentDSLParser.SUSHISWAP, 0); }
		public TerminalNode CURVE() { return getToken(IntentDSLParser.CURVE, 0); }
		public TerminalNode ONEINCH() { return getToken(IntentDSLParser.ONEINCH, 0); }
		public TerminalNode POLYGON() { return getToken(IntentDSLParser.POLYGON, 0); }
		public TerminalNode AVAX() { return getToken(IntentDSLParser.AVAX, 0); }
		public PlatformContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_platform; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterPlatform(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitPlatform(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitPlatform(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlatformContext platform() throws RecognitionException {
		PlatformContext _localctx = new PlatformContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_platform);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			_la = _input.LA(1);
			if ( !(((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & 511L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001^\u0160\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0001"+
		"\u0000\u0004\u0000R\b\u0000\u000b\u0000\f\u0000S\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002`\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003e\b\u0003\n\u0003\f\u0003h\t\u0003\u0001\u0004"+
		"\u0001\u0004\u0003\u0004l\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004q\b\u0004\u0005\u0004s\b\u0004\n\u0004\f\u0004v\t\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006\u0084\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005"+
		"\u0007\u008a\b\u0007\n\u0007\f\u0007\u008d\t\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0005\b\u0093\b\b\n\b\f\b\u0096\t\b\u0001\t\u0005\t\u0099\b"+
		"\t\n\t\f\t\u009c\t\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u00a4\b\n\u0001\n\u0001\n\u0003\n\u00a8\b\n\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0003\u0011\u00ba\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u00bf\b\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0003\u0012\u00cd\b\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003"+
		"\u0019\u010d\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0117\b\u001a\u0001"+
		"\u001b\u0001\u001b\u0005\u001b\u011b\b\u001b\n\u001b\f\u001b\u011e\t\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d"+
		"\u0005\u001d\u0126\b\u001d\n\u001d\f\u001d\u0129\t\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u013b\b\u001e\u0001\u001f\u0001"+
		"\u001f\u0005\u001f\u013f\b\u001f\n\u001f\f\u001f\u0142\t\u001f\u0001\u001f"+
		"\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001$\u0001$\u0001%\u0001%\u0001"+
		"%\u0001%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0000"+
		"\u0000(\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLN\u0000\n\u0001\u0000+,\u0001"+
		"\u0000.3\u0001\u000068\u0001\u000045\u0002\u0000--45\u0001\u0000VW\u0001"+
		"\u0000\u001b \u0001\u0000%&\u0001\u0000>I\u0001\u0000JR\u0159\u0000Q\u0001"+
		"\u0000\u0000\u0000\u0002U\u0001\u0000\u0000\u0000\u0004_\u0001\u0000\u0000"+
		"\u0000\u0006a\u0001\u0000\u0000\u0000\bk\u0001\u0000\u0000\u0000\nw\u0001"+
		"\u0000\u0000\u0000\f\u0083\u0001\u0000\u0000\u0000\u000e\u0085\u0001\u0000"+
		"\u0000\u0000\u0010\u008e\u0001\u0000\u0000\u0000\u0012\u009a\u0001\u0000"+
		"\u0000\u0000\u0014\u00a7\u0001\u0000\u0000\u0000\u0016\u00a9\u0001\u0000"+
		"\u0000\u0000\u0018\u00ab\u0001\u0000\u0000\u0000\u001a\u00ad\u0001\u0000"+
		"\u0000\u0000\u001c\u00af\u0001\u0000\u0000\u0000\u001e\u00b1\u0001\u0000"+
		"\u0000\u0000 \u00b3\u0001\u0000\u0000\u0000\"\u00b9\u0001\u0000\u0000"+
		"\u0000$\u00cc\u0001\u0000\u0000\u0000&\u00ce\u0001\u0000\u0000\u0000("+
		"\u00d3\u0001\u0000\u0000\u0000*\u00da\u0001\u0000\u0000\u0000,\u00e6\u0001"+
		"\u0000\u0000\u0000.\u00ed\u0001\u0000\u0000\u00000\u00f6\u0001\u0000\u0000"+
		"\u00002\u00ff\u0001\u0000\u0000\u00004\u0111\u0001\u0000\u0000\u00006"+
		"\u0118\u0001\u0000\u0000\u00008\u0121\u0001\u0000\u0000\u0000:\u0123\u0001"+
		"\u0000\u0000\u0000<\u012f\u0001\u0000\u0000\u0000>\u013c\u0001\u0000\u0000"+
		"\u0000@\u0145\u0001\u0000\u0000\u0000B\u0147\u0001\u0000\u0000\u0000D"+
		"\u014a\u0001\u0000\u0000\u0000F\u014f\u0001\u0000\u0000\u0000H\u0152\u0001"+
		"\u0000\u0000\u0000J\u0154\u0001\u0000\u0000\u0000L\u0158\u0001\u0000\u0000"+
		"\u0000N\u015d\u0001\u0000\u0000\u0000PR\u0003\"\u0011\u0000QP\u0001\u0000"+
		"\u0000\u0000RS\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001"+
		"\u0000\u0000\u0000T\u0001\u0001\u0000\u0000\u0000UV\u0003\u0006\u0003"+
		"\u0000V\u0003\u0001\u0000\u0000\u0000WX\u0005\u0001\u0000\u0000X`\u0005"+
		"X\u0000\u0000YZ\u0005\u0002\u0000\u0000Z`\u0005X\u0000\u0000[\\\u0005"+
		"\u0003\u0000\u0000\\]\u0005X\u0000\u0000]^\u0005\u0004\u0000\u0000^`\u0005"+
		"X\u0000\u0000_W\u0001\u0000\u0000\u0000_Y\u0001\u0000\u0000\u0000_[\u0001"+
		"\u0000\u0000\u0000`\u0005\u0001\u0000\u0000\u0000af\u0003\b\u0004\u0000"+
		"bc\u0005,\u0000\u0000ce\u0003\b\u0004\u0000db\u0001\u0000\u0000\u0000"+
		"eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000"+
		"\u0000g\u0007\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000il\u0003"+
		"\n\u0005\u0000jl\u0003\u0004\u0002\u0000ki\u0001\u0000\u0000\u0000kj\u0001"+
		"\u0000\u0000\u0000lt\u0001\u0000\u0000\u0000mp\u0005+\u0000\u0000nq\u0003"+
		"\n\u0005\u0000oq\u0003\u0004\u0002\u0000pn\u0001\u0000\u0000\u0000po\u0001"+
		"\u0000\u0000\u0000qs\u0001\u0000\u0000\u0000rm\u0001\u0000\u0000\u0000"+
		"sv\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000"+
		"\u0000u\t\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000wx\u0003\f"+
		"\u0006\u0000xy\u0003\u0018\f\u0000yz\u0003\f\u0006\u0000z\u000b\u0001"+
		"\u0000\u0000\u0000{\u0084\u0003B!\u0000|\u0084\u0003D\"\u0000}~\u0003"+
		" \u0010\u0000~\u007f\u0003H$\u0000\u007f\u0084\u0001\u0000\u0000\u0000"+
		"\u0080\u0084\u0003 \u0010\u0000\u0081\u0084\u0005;\u0000\u0000\u0082\u0084"+
		"\u0005<\u0000\u0000\u0083{\u0001\u0000\u0000\u0000\u0083|\u0001\u0000"+
		"\u0000\u0000\u0083}\u0001\u0000\u0000\u0000\u0083\u0080\u0001\u0000\u0000"+
		"\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0083\u0082\u0001\u0000\u0000"+
		"\u0000\u0084\r\u0001\u0000\u0000\u0000\u0085\u008b\u0003\u0010\b\u0000"+
		"\u0086\u0087\u0003\u001a\r\u0000\u0087\u0088\u0003\u0010\b\u0000\u0088"+
		"\u008a\u0001\u0000\u0000\u0000\u0089\u0086\u0001\u0000\u0000\u0000\u008a"+
		"\u008d\u0001\u0000\u0000\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008b"+
		"\u008c\u0001\u0000\u0000\u0000\u008c\u000f\u0001\u0000\u0000\u0000\u008d"+
		"\u008b\u0001\u0000\u0000\u0000\u008e\u0094\u0003\u0012\t\u0000\u008f\u0090"+
		"\u0003\u001c\u000e\u0000\u0090\u0091\u0003\u0012\t\u0000\u0091\u0093\u0001"+
		"\u0000\u0000\u0000\u0092\u008f\u0001\u0000\u0000\u0000\u0093\u0096\u0001"+
		"\u0000\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095\u0001"+
		"\u0000\u0000\u0000\u0095\u0011\u0001\u0000\u0000\u0000\u0096\u0094\u0001"+
		"\u0000\u0000\u0000\u0097\u0099\u0003\u001e\u000f\u0000\u0098\u0097\u0001"+
		"\u0000\u0000\u0000\u0099\u009c\u0001\u0000\u0000\u0000\u009a\u0098\u0001"+
		"\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u009d\u0001"+
		"\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009d\u009e\u0003"+
		"\u0014\n\u0000\u009e\u0013\u0001\u0000\u0000\u0000\u009f\u00a8\u0003 "+
		"\u0010\u0000\u00a0\u00a3\u0005\'\u0000\u0000\u00a1\u00a4\u0003\u000e\u0007"+
		"\u0000\u00a2\u00a4\u0003\u0012\t\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000"+
		"\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a6\u0005(\u0000\u0000\u00a6\u00a8\u0001\u0000\u0000\u0000\u00a7"+
		"\u009f\u0001\u0000\u0000\u0000\u00a7\u00a0\u0001\u0000\u0000\u0000\u00a8"+
		"\u0015\u0001\u0000\u0000\u0000\u00a9\u00aa\u0007\u0000\u0000\u0000\u00aa"+
		"\u0017\u0001\u0000\u0000\u0000\u00ab\u00ac\u0007\u0001\u0000\u0000\u00ac"+
		"\u0019\u0001\u0000\u0000\u0000\u00ad\u00ae\u0007\u0002\u0000\u0000\u00ae"+
		"\u001b\u0001\u0000\u0000\u0000\u00af\u00b0\u0007\u0003\u0000\u0000\u00b0"+
		"\u001d\u0001\u0000\u0000\u0000\u00b1\u00b2\u0007\u0004\u0000\u0000\u00b2"+
		"\u001f\u0001\u0000\u0000\u0000\u00b3\u00b4\u0007\u0005\u0000\u0000\u00b4"+
		"!\u0001\u0000\u0000\u0000\u00b5\u00b6\u0005\u0005\u0000\u0000\u00b6\u00b7"+
		"\u0003\u0002\u0001\u0000\u00b7\u00b8\u0005\u0006\u0000\u0000\u00b8\u00ba"+
		"\u0001\u0000\u0000\u0000\u00b9\u00b5\u0001\u0000\u0000\u0000\u00b9\u00ba"+
		"\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb\u00be"+
		"\u0003$\u0012\u0000\u00bc\u00bd\u0005\u0007\u0000\u0000\u00bd\u00bf\u0003"+
		"\u0002\u0001\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001"+
		"\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c1\u0005"+
		"Y\u0000\u0000\u00c1#\u0001\u0000\u0000\u0000\u00c2\u00cd\u0003&\u0013"+
		"\u0000\u00c3\u00cd\u0003(\u0014\u0000\u00c4\u00cd\u0003*\u0015\u0000\u00c5"+
		"\u00cd\u0003,\u0016\u0000\u00c6\u00cd\u0003.\u0017\u0000\u00c7\u00cd\u0003"+
		"0\u0018\u0000\u00c8\u00cd\u00032\u0019\u0000\u00c9\u00cd\u00034\u001a"+
		"\u0000\u00ca\u00cd\u0003:\u001d\u0000\u00cb\u00cd\u0003<\u001e\u0000\u00cc"+
		"\u00c2\u0001\u0000\u0000\u0000\u00cc\u00c3\u0001\u0000\u0000\u0000\u00cc"+
		"\u00c4\u0001\u0000\u0000\u0000\u00cc\u00c5\u0001\u0000\u0000\u0000\u00cc"+
		"\u00c6\u0001\u0000\u0000\u0000\u00cc\u00c7\u0001\u0000\u0000\u0000\u00cc"+
		"\u00c8\u0001\u0000\u0000\u0000\u00cc\u00c9\u0001\u0000\u0000\u0000\u00cc"+
		"\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cb\u0001\u0000\u0000\u0000\u00cd"+
		"%\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005\b\u0000\u0000\u00cf\u00d0"+
		"\u0005)\u0000\u0000\u00d0\u00d1\u0005T\u0000\u0000\u00d1\u00d2\u0005*"+
		"\u0000\u0000\u00d2\'\u0001\u0000\u0000\u0000\u00d3\u00d4\u0005\t\u0000"+
		"\u0000\u00d4\u00d5\u0003F#\u0000\u00d5\u00d6\u0005\n\u0000\u0000\u00d6"+
		"\u00d7\u0003L&\u0000\u00d7\u00d8\u0005\u0004\u0000\u0000\u00d8\u00d9\u0003"+
		"L&\u0000\u00d9)\u0001\u0000\u0000\u0000\u00da\u00db\u0005\u000b\u0000"+
		"\u0000\u00db\u00dc\u0003F#\u0000\u00dc\u00dd\u0005\f\u0000\u0000\u00dd"+
		"\u00de\u0003L&\u0000\u00de\u00df\u0005\n\u0000\u0000\u00df\u00e0\u0003"+
		"N\'\u0000\u00e0\u00e1\u0005\r\u0000\u0000\u00e1\u00e2\u0003F#\u0000\u00e2"+
		"\u00e3\u0005\n\u0000\u0000\u00e3\u00e4\u0003L&\u0000\u00e4\u00e5\u0005"+
		"\u000e\u0000\u0000\u00e5+\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005\u000f"+
		"\u0000\u0000\u00e7\u00e8\u0003F#\u0000\u00e8\u00e9\u0005\n\u0000\u0000"+
		"\u00e9\u00ea\u0003L&\u0000\u00ea\u00eb\u0005\u0004\u0000\u0000\u00eb\u00ec"+
		"\u0003N\'\u0000\u00ec-\u0001\u0000\u0000\u0000\u00ed\u00ee\u0005\u0010"+
		"\u0000\u0000\u00ee\u00ef\u0003F#\u0000\u00ef\u00f0\u0005\n\u0000\u0000"+
		"\u00f0\u00f1\u0003L&\u0000\u00f1\u00f2\u0005\f\u0000\u0000\u00f2\u00f3"+
		"\u0003H$\u0000\u00f3\u00f4\u0005\u0011\u0000\u0000\u00f4\u00f5\u0003N"+
		"\'\u0000\u00f5/\u0001\u0000\u0000\u0000\u00f6\u00f7\u0005\u0012\u0000"+
		"\u0000\u00f7\u00f8\u0003F#\u0000\u00f8\u00f9\u0005\u0013\u0000\u0000\u00f9"+
		"\u00fa\u0003F#\u0000\u00fa\u00fb\u0005\u0004\u0000\u0000\u00fb\u00fc\u0003"+
		"N\'\u0000\u00fc\u00fd\u0005\u0014\u0000\u0000\u00fd\u00fe\u0003L&\u0000"+
		"\u00fe1\u0001\u0000\u0000\u0000\u00ff\u0100\u0005\u0015\u0000\u0000\u0100"+
		"\u0101\u0003F#\u0000\u0101\u0102\u0005\u0013\u0000\u0000\u0102\u0103\u0003"+
		"F#\u0000\u0103\u0104\u0005\n\u0000\u0000\u0104\u0105\u0003N\'\u0000\u0105"+
		"\u0106\u0005\u0016\u0000\u0000\u0106\u0107\u0005V\u0000\u0000\u0107\u010c"+
		"\u0005\u0017\u0000\u0000\u0108\u0109\u0005\u0018\u0000\u0000\u0109\u010a"+
		"\u0005)\u0000\u0000\u010a\u010b\u0005U\u0000\u0000\u010b\u010d\u0005*"+
		"\u0000\u0000\u010c\u0108\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000"+
		"\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u010f\u0005\n\u0000"+
		"\u0000\u010f\u0110\u0003L&\u0000\u01103\u0001\u0000\u0000\u0000\u0111"+
		"\u0112\u0005\u0019\u0000\u0000\u0112\u0113\u0003F#\u0000\u0113\u0114\u0005"+
		"\n\u0000\u0000\u0114\u0116\u0003L&\u0000\u0115\u0117\u00036\u001b\u0000"+
		"\u0116\u0115\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000"+
		"\u01175\u0001\u0000\u0000\u0000\u0118\u011c\u0005\r\u0000\u0000\u0119"+
		"\u011b\u00038\u001c\u0000\u011a\u0119\u0001\u0000\u0000\u0000\u011b\u011e"+
		"\u0001\u0000\u0000\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011c\u011d"+
		"\u0001\u0000\u0000\u0000\u011d\u011f\u0001\u0000\u0000\u0000\u011e\u011c"+
		"\u0001\u0000\u0000\u0000\u011f\u0120\u0005\u001a\u0000\u0000\u01207\u0001"+
		"\u0000\u0000\u0000\u0121\u0122\u0007\u0006\u0000\u0000\u01229\u0001\u0000"+
		"\u0000\u0000\u0123\u0127\u0005!\u0000\u0000\u0124\u0126\u0005]\u0000\u0000"+
		"\u0125\u0124\u0001\u0000\u0000\u0000\u0126\u0129\u0001\u0000\u0000\u0000"+
		"\u0127\u0125\u0001\u0000\u0000\u0000\u0127\u0128\u0001\u0000\u0000\u0000"+
		"\u0128\u012a\u0001\u0000\u0000\u0000\u0129\u0127\u0001\u0000\u0000\u0000"+
		"\u012a\u012b\u0005\"\u0000\u0000\u012b\u012c\u0003F#\u0000\u012c\u012d"+
		"\u0005\n\u0000\u0000\u012d\u012e\u0003L&\u0000\u012e;\u0001\u0000\u0000"+
		"\u0000\u012f\u0130\u0005#\u0000\u0000\u0130\u0131\u0005)\u0000\u0000\u0131"+
		"\u0132\u0005U\u0000\u0000\u0132\u0133\u0005*\u0000\u0000\u0133\u0134\u0005"+
		"$\u0000\u0000\u0134\u0135\u0005)\u0000\u0000\u0135\u0136\u0005U\u0000"+
		"\u0000\u0136\u0137\u0005*\u0000\u0000\u0137\u0138\u0005\n\u0000\u0000"+
		"\u0138\u013a\u0003L&\u0000\u0139\u013b\u0003>\u001f\u0000\u013a\u0139"+
		"\u0001\u0000\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b=\u0001"+
		"\u0000\u0000\u0000\u013c\u0140\u0005\r\u0000\u0000\u013d\u013f\u0003@"+
		" \u0000\u013e\u013d\u0001\u0000\u0000\u0000\u013f\u0142\u0001\u0000\u0000"+
		"\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0140\u0141\u0001\u0000\u0000"+
		"\u0000\u0141\u0143\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000\u0000"+
		"\u0000\u0143\u0144\u0005\u001a\u0000\u0000\u0144?\u0001\u0000\u0000\u0000"+
		"\u0145\u0146\u0007\u0007\u0000\u0000\u0146A\u0001\u0000\u0000\u0000\u0147"+
		"\u0148\u00059\u0000\u0000\u0148\u0149\u0003L&\u0000\u0149C\u0001\u0000"+
		"\u0000\u0000\u014a\u014b\u0005:\u0000\u0000\u014b\u014c\u0003H$\u0000"+
		"\u014c\u014d\u0005\u0011\u0000\u0000\u014d\u014e\u0003N\'\u0000\u014e"+
		"E\u0001\u0000\u0000\u0000\u014f\u0150\u0003\u000e\u0007\u0000\u0150\u0151"+
		"\u0003H$\u0000\u0151G\u0001\u0000\u0000\u0000\u0152\u0153\u0007\b\u0000"+
		"\u0000\u0153I\u0001\u0000\u0000\u0000\u0154\u0155\u0003H$\u0000\u0155"+
		"\u0156\u0005+\u0000\u0000\u0156\u0157\u0003H$\u0000\u0157K\u0001\u0000"+
		"\u0000\u0000\u0158\u0159\u0005=\u0000\u0000\u0159\u015a\u0005)\u0000\u0000"+
		"\u015a\u015b\u0005U\u0000\u0000\u015b\u015c\u0005*\u0000\u0000\u015cM"+
		"\u0001\u0000\u0000\u0000\u015d\u015e\u0007\t\u0000\u0000\u015eO\u0001"+
		"\u0000\u0000\u0000\u0015S_fkpt\u0083\u008b\u0094\u009a\u00a3\u00a7\u00b9"+
		"\u00be\u00cc\u010c\u0116\u011c\u0127\u013a\u0140";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}