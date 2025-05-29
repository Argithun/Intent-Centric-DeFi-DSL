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
		T__38=39, T__39=40, LPARENT=41, RPARENT=42, LBRACK=43, RBRACK=44, LOGIC_AND=45, 
		LOGIC_OR=46, LOGIC_NOT=47, EQ=48, NEQ=49, LT=50, GT=51, LE=52, GE=53, 
		ADD=54, SUB=55, MUL=56, DIV=57, MOD=58, BALANCE=59, PRICE=60, SLIPPAGE=61, 
		FEE=62, WALLET=63, USDT=64, USDC=65, ETH=66, DAI=67, BTC=68, WBTC=69, 
		WETH=70, UNI=71, SUSHI=72, AAVE_token=73, MATIC=74, COMP=75, AAVE=76, 
		UNISWAP=77, COMPOUND=78, YEARN=79, SUSHISWAP=80, CURVE=81, ONEINCH=82, 
		POLYGON=83, AVAX=84, DEC_INT=85, DEC_FLOAT=86, PRIVATE_KEY=87, KEY=88, 
		TIME=89, SEMI=90, LINE_COMMENT=91, COMMENT=92, BLANK=93, NFTQualifiers=94, 
		NFTPlatform=95;
	public static final int
		RULE_program = 0, RULE_condition = 1, RULE_timeCondition = 2, RULE_orExpression = 3, 
		RULE_andExpression = 4, RULE_comparisonExpression = 5, RULE_comparisonElement = 6, 
		RULE_binaryExpression = 7, RULE_lowBinaryExpression = 8, RULE_unaryExpression = 9, 
		RULE_primaryExpression = 10, RULE_logicalOperator = 11, RULE_comparisonOperator = 12, 
		RULE_highBinaryOperator = 13, RULE_lowBinaryOperator = 14, RULE_unaryOperator = 15, 
		RULE_number = 16, RULE_triggerStatement = 17, RULE_statement = 18, RULE_accountStatement = 19, 
		RULE_transferStatement = 20, RULE_borrowStatement = 21, RULE_repayBorrowStatement = 22, 
		RULE_swapStatement = 23, RULE_addLiquidityStatement = 24, RULE_removeLiquidityStatement = 25, 
		RULE_simpleStakeStatement = 26, RULE_simpleBuyNFTStatement = 27, RULE_simpleSellNFTStatement = 28, 
		RULE_stakeStatement = 29, RULE_stakeStrategy = 30, RULE_stakeStrategyQualifiers = 31, 
		RULE_buyNFTStatement = 32, RULE_sellNFTStatement = 33, RULE_sellNFTStartegy = 34, 
		RULE_sellNFTStrategyQualifiers = 35, RULE_walletBalance = 36, RULE_assetPrice = 37, 
		RULE_amount = 38, RULE_asset = 39, RULE_wallet = 40, RULE_platform = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "condition", "timeCondition", "orExpression", "andExpression", 
			"comparisonExpression", "comparisonElement", "binaryExpression", "lowBinaryExpression", 
			"unaryExpression", "primaryExpression", "logicalOperator", "comparisonOperator", 
			"highBinaryOperator", "lowBinaryOperator", "unaryOperator", "number", 
			"triggerStatement", "statement", "accountStatement", "transferStatement", 
			"borrowStatement", "repayBorrowStatement", "swapStatement", "addLiquidityStatement", 
			"removeLiquidityStatement", "simpleStakeStatement", "simpleBuyNFTStatement", 
			"simpleSellNFTStatement", "stakeStatement", "stakeStrategy", "stakeStrategyQualifiers", 
			"buyNFTStatement", "sellNFTStatement", "sellNFTStartegy", "sellNFTStrategyQualifiers", 
			"walletBalance", "assetPrice", "amount", "asset", "wallet", "platform"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'time before'", "'time after'", "'time during'", "'to'", "'trigger'", 
			"'then'", "'checking'", "'switch to account'", "'transfer'", "'from'", 
			"'borrow'", "'for'", "'repay'", "'swap'", "'on'", "'add'", "','", "'receiving liquidity token to'", 
			"'remove'", "'returning'", "'liquidity'", "'of token'", "'stake'", "'buy NFT'", 
			"'in collection'", "'using at most'", "'sell NFT'", "'for at least'", 
			"'using'", "'strategy'", "'low-risk'", "'middle-risk'", "'high-risk'", 
			"'short-term'", "'middle-term'", "'long-term'", "'buy'", "'NFT using at most'", 
			"'time-saving'", "'profitable'", "'('", "')'", "'['", "']'", "'and'", 
			"'or'", "'not'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'+'", 
			"'-'", "'*'", "'/'", "'%'", "'balance'", "'price'", "'slippage'", "'fee'", 
			"'wallet'", "'USDT'", "'USDC'", "'ETH'", "'DAI'", "'BTC'", "'WBTC'", 
			"'WETH'", "'UNI'", "'SUSHI'", "'AAVE'", "'MATIC'", "'COMAP'", "'Aave'", 
			"'Uniswap'", "'Compound'", "'Yearn'", "'Sushiswap'", "'Curve'", "'1inch'", 
			"'Polygon'", "'Avax'", null, null, null, null, null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "LPARENT", "RPARENT", "LBRACK", "RBRACK", 
			"LOGIC_AND", "LOGIC_OR", "LOGIC_NOT", "EQ", "NEQ", "LT", "GT", "LE", 
			"GE", "ADD", "SUB", "MUL", "DIV", "MOD", "BALANCE", "PRICE", "SLIPPAGE", 
			"FEE", "WALLET", "USDT", "USDC", "ETH", "DAI", "BTC", "WBTC", "WETH", 
			"UNI", "SUSHI", "AAVE_token", "MATIC", "COMP", "AAVE", "UNISWAP", "COMPOUND", 
			"YEARN", "SUSHISWAP", "CURVE", "ONEINCH", "POLYGON", "AVAX", "DEC_INT", 
			"DEC_FLOAT", "PRIVATE_KEY", "KEY", "TIME", "SEMI", "LINE_COMMENT", "COMMENT", 
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
			setState(85); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(84);
				triggerStatement();
				}
				}
				setState(87); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 137598954272L) != 0) );
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
			setState(89);
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
			setState(99);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				match(T__0);
				setState(92);
				match(TIME);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				match(T__1);
				setState(94);
				match(TIME);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(95);
				match(T__2);
				setState(96);
				match(TIME);
				setState(97);
				match(T__3);
				setState(98);
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
			setState(101);
			andExpression();
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LOGIC_OR) {
				{
				{
				setState(102);
				match(LOGIC_OR);
				setState(103);
				andExpression();
				}
				}
				setState(108);
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
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BALANCE:
			case PRICE:
			case SLIPPAGE:
			case FEE:
			case DEC_INT:
			case DEC_FLOAT:
				{
				setState(109);
				comparisonExpression();
				}
				break;
			case T__0:
			case T__1:
			case T__2:
				{
				setState(110);
				timeCondition();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LOGIC_AND) {
				{
				{
				setState(113);
				match(LOGIC_AND);
				setState(116);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BALANCE:
				case PRICE:
				case SLIPPAGE:
				case FEE:
				case DEC_INT:
				case DEC_FLOAT:
					{
					setState(114);
					comparisonExpression();
					}
					break;
				case T__0:
				case T__1:
				case T__2:
					{
					setState(115);
					timeCondition();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(122);
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
			setState(123);
			comparisonElement();
			setState(124);
			comparisonOperator();
			setState(125);
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
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				walletBalance();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				assetPrice();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(129);
				number();
				setState(130);
				asset();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(132);
				number();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(133);
				match(SLIPPAGE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(134);
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
			setState(137);
			lowBinaryExpression();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 504403158265495552L) != 0)) {
				{
				{
				setState(138);
				highBinaryOperator();
				setState(139);
				lowBinaryExpression();
				}
				}
				setState(145);
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
			setState(146);
			unaryExpression();
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD || _la==SUB) {
				{
				{
				setState(147);
				lowBinaryOperator();
				setState(148);
				unaryExpression();
				}
				}
				setState(154);
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
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 54183933016801280L) != 0)) {
				{
				{
				setState(155);
				unaryOperator();
				}
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(161);
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
			setState(171);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEC_INT:
			case DEC_FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				number();
				}
				break;
			case LPARENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				match(LPARENT);
				setState(167);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(165);
					binaryExpression();
					}
					break;
				case 2:
					{
					setState(166);
					unaryExpression();
					}
					break;
				}
				setState(169);
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
			setState(173);
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
			setState(175);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 17732923532771328L) != 0)) ) {
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
			setState(177);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 504403158265495552L) != 0)) ) {
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
			setState(179);
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
			setState(181);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 54183933016801280L) != 0)) ) {
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
			setState(183);
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
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(185);
				match(T__4);
				setState(186);
				condition();
				setState(187);
				match(T__5);
				}
			}

			setState(191);
			statement();
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(192);
				match(T__6);
				setState(193);
				condition();
				}
			}

			setState(196);
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
		public SimpleStakeStatementContext simpleStakeStatement() {
			return getRuleContext(SimpleStakeStatementContext.class,0);
		}
		public SimpleBuyNFTStatementContext simpleBuyNFTStatement() {
			return getRuleContext(SimpleBuyNFTStatementContext.class,0);
		}
		public SimpleSellNFTStatementContext simpleSellNFTStatement() {
			return getRuleContext(SimpleSellNFTStatementContext.class,0);
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
			setState(211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(198);
				accountStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(199);
				transferStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(200);
				borrowStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(201);
				repayBorrowStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(202);
				swapStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(203);
				addLiquidityStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(204);
				removeLiquidityStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(205);
				stakeStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(206);
				buyNFTStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(207);
				sellNFTStatement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(208);
				simpleStakeStatement();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(209);
				simpleBuyNFTStatement();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(210);
				simpleSellNFTStatement();
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
			setState(213);
			match(T__7);
			setState(214);
			match(LBRACK);
			setState(215);
			match(PRIVATE_KEY);
			setState(216);
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
			setState(218);
			match(T__8);
			setState(219);
			amount();
			setState(220);
			match(T__9);
			setState(221);
			wallet();
			setState(222);
			match(T__3);
			setState(223);
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
		public AmountContext amount() {
			return getRuleContext(AmountContext.class,0);
		}
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
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
			setState(225);
			match(T__10);
			setState(226);
			amount();
			setState(227);
			match(T__11);
			setState(228);
			wallet();
			setState(229);
			match(T__9);
			setState(230);
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
			setState(232);
			match(T__12);
			setState(233);
			amount();
			setState(234);
			match(T__9);
			setState(235);
			wallet();
			setState(236);
			match(T__3);
			setState(237);
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
			setState(239);
			match(T__13);
			setState(240);
			amount();
			setState(241);
			match(T__9);
			setState(242);
			wallet();
			setState(243);
			match(T__11);
			setState(244);
			asset();
			setState(245);
			match(T__14);
			setState(246);
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
			setState(248);
			match(T__15);
			setState(249);
			amount();
			setState(250);
			match(T__16);
			setState(251);
			amount();
			setState(252);
			match(T__3);
			setState(253);
			platform();
			setState(254);
			match(T__17);
			setState(255);
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
			setState(257);
			match(T__18);
			setState(258);
			amount();
			setState(259);
			match(T__16);
			setState(260);
			amount();
			setState(261);
			match(T__9);
			setState(262);
			platform();
			setState(263);
			match(T__19);
			setState(264);
			match(DEC_INT);
			setState(265);
			match(T__20);
			setState(270);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(266);
				match(T__21);
				setState(267);
				match(LBRACK);
				setState(268);
				match(KEY);
				setState(269);
				match(RBRACK);
				}
			}

			setState(272);
			match(T__9);
			setState(273);
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
	public static class SimpleStakeStatementContext extends ParserRuleContext {
		public AmountContext amount() {
			return getRuleContext(AmountContext.class,0);
		}
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
		}
		public PlatformContext platform() {
			return getRuleContext(PlatformContext.class,0);
		}
		public SimpleStakeStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleStakeStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterSimpleStakeStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitSimpleStakeStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitSimpleStakeStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleStakeStatementContext simpleStakeStatement() throws RecognitionException {
		SimpleStakeStatementContext _localctx = new SimpleStakeStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_simpleStakeStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(T__22);
			setState(276);
			amount();
			setState(277);
			match(T__9);
			setState(278);
			wallet();
			setState(279);
			match(T__14);
			setState(280);
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
	public static class SimpleBuyNFTStatementContext extends ParserRuleContext {
		public List<TerminalNode> LBRACK() { return getTokens(IntentDSLParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(IntentDSLParser.LBRACK, i);
		}
		public TerminalNode DEC_INT() { return getToken(IntentDSLParser.DEC_INT, 0); }
		public List<TerminalNode> RBRACK() { return getTokens(IntentDSLParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(IntentDSLParser.RBRACK, i);
		}
		public TerminalNode KEY() { return getToken(IntentDSLParser.KEY, 0); }
		public AmountContext amount() {
			return getRuleContext(AmountContext.class,0);
		}
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
		}
		public SimpleBuyNFTStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleBuyNFTStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterSimpleBuyNFTStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitSimpleBuyNFTStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitSimpleBuyNFTStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleBuyNFTStatementContext simpleBuyNFTStatement() throws RecognitionException {
		SimpleBuyNFTStatementContext _localctx = new SimpleBuyNFTStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_simpleBuyNFTStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(T__23);
			setState(283);
			match(LBRACK);
			setState(284);
			match(DEC_INT);
			setState(285);
			match(RBRACK);
			setState(286);
			match(T__24);
			setState(287);
			match(LBRACK);
			setState(288);
			match(KEY);
			setState(289);
			match(RBRACK);
			setState(290);
			match(T__25);
			setState(291);
			amount();
			setState(292);
			match(T__9);
			setState(293);
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
	public static class SimpleSellNFTStatementContext extends ParserRuleContext {
		public List<TerminalNode> LBRACK() { return getTokens(IntentDSLParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(IntentDSLParser.LBRACK, i);
		}
		public TerminalNode DEC_INT() { return getToken(IntentDSLParser.DEC_INT, 0); }
		public List<TerminalNode> RBRACK() { return getTokens(IntentDSLParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(IntentDSLParser.RBRACK, i);
		}
		public TerminalNode KEY() { return getToken(IntentDSLParser.KEY, 0); }
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
		}
		public AmountContext amount() {
			return getRuleContext(AmountContext.class,0);
		}
		public SimpleSellNFTStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleSellNFTStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).enterSimpleSellNFTStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntentDSLListener ) ((IntentDSLListener)listener).exitSimpleSellNFTStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IntentDSLVisitor ) return ((IntentDSLVisitor<? extends T>)visitor).visitSimpleSellNFTStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleSellNFTStatementContext simpleSellNFTStatement() throws RecognitionException {
		SimpleSellNFTStatementContext _localctx = new SimpleSellNFTStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_simpleSellNFTStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			match(T__26);
			setState(296);
			match(LBRACK);
			setState(297);
			match(DEC_INT);
			setState(298);
			match(RBRACK);
			setState(299);
			match(T__24);
			setState(300);
			match(LBRACK);
			setState(301);
			match(KEY);
			setState(302);
			match(RBRACK);
			setState(303);
			match(T__9);
			setState(304);
			wallet();
			setState(305);
			match(T__27);
			setState(306);
			amount();
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
		enterRule(_localctx, 58, RULE_stakeStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(T__22);
			setState(309);
			amount();
			setState(310);
			match(T__9);
			setState(311);
			wallet();
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(312);
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
		enterRule(_localctx, 60, RULE_stakeStrategy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			match(T__28);
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 135291469824L) != 0)) {
				{
				{
				setState(316);
				stakeStrategyQualifiers();
				}
				}
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(322);
			match(T__29);
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
		enterRule(_localctx, 62, RULE_stakeStrategyQualifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 135291469824L) != 0)) ) {
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
		enterRule(_localctx, 64, RULE_buyNFTStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(T__36);
			setState(330);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NFTQualifiers) {
				{
				{
				setState(327);
				match(NFTQualifiers);
				}
				}
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(333);
			match(T__37);
			setState(334);
			amount();
			setState(335);
			match(T__9);
			setState(336);
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
		public TerminalNode DEC_INT() { return getToken(IntentDSLParser.DEC_INT, 0); }
		public List<TerminalNode> RBRACK() { return getTokens(IntentDSLParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(IntentDSLParser.RBRACK, i);
		}
		public TerminalNode KEY() { return getToken(IntentDSLParser.KEY, 0); }
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
		enterRule(_localctx, 66, RULE_sellNFTStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			match(T__26);
			setState(339);
			match(LBRACK);
			setState(340);
			match(DEC_INT);
			setState(341);
			match(RBRACK);
			setState(342);
			match(T__24);
			setState(343);
			match(LBRACK);
			setState(344);
			match(KEY);
			setState(345);
			match(RBRACK);
			setState(346);
			match(T__9);
			setState(347);
			wallet();
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(348);
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
		enterRule(_localctx, 68, RULE_sellNFTStartegy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			match(T__28);
			setState(355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__38 || _la==T__39) {
				{
				{
				setState(352);
				sellNFTStrategyQualifiers();
				}
				}
				setState(357);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(358);
			match(T__29);
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
		enterRule(_localctx, 70, RULE_sellNFTStrategyQualifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			_la = _input.LA(1);
			if ( !(_la==T__38 || _la==T__39) ) {
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
		enterRule(_localctx, 72, RULE_walletBalance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			match(BALANCE);
			setState(363);
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
		enterRule(_localctx, 74, RULE_assetPrice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			match(PRICE);
			setState(366);
			asset();
			setState(367);
			match(T__14);
			setState(368);
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
		enterRule(_localctx, 76, RULE_amount);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			binaryExpression();
			setState(371);
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
		enterRule(_localctx, 78, RULE_asset);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			_la = _input.LA(1);
			if ( !(((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 4095L) != 0)) ) {
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
		enterRule(_localctx, 80, RULE_wallet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			match(WALLET);
			setState(376);
			match(LBRACK);
			setState(377);
			match(KEY);
			setState(378);
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
		enterRule(_localctx, 82, RULE_platform);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			_la = _input.LA(1);
			if ( !(((((_la - 76)) & ~0x3f) == 0 && ((1L << (_la - 76)) & 511L) != 0)) ) {
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
		"\u0004\u0001_\u017f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0001\u0000\u0004\u0000V\b\u0000\u000b\u0000\f"+
		"\u0000W\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002d\b"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003i\b\u0003\n\u0003"+
		"\f\u0003l\t\u0003\u0001\u0004\u0001\u0004\u0003\u0004p\b\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004u\b\u0004\u0005\u0004w\b\u0004\n\u0004"+
		"\f\u0004z\t\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u0088\b\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0005\u0007\u008e\b\u0007\n\u0007\f\u0007\u0091\t\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u0097\b\b\n\b\f\b\u009a\t\b\u0001"+
		"\t\u0005\t\u009d\b\t\n\t\f\t\u00a0\t\t\u0001\t\u0001\t\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0003\n\u00a8\b\n\u0001\n\u0001\n\u0003\n\u00ac\b\n\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0003\u0011\u00be\b\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u00c3\b\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0003\u0012\u00d4\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019"+
		"\u010f\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0003\u001d\u013a\b\u001d\u0001\u001e\u0001\u001e\u0005\u001e\u013e\b"+
		"\u001e\n\u001e\f\u001e\u0141\t\u001e\u0001\u001e\u0001\u001e\u0001\u001f"+
		"\u0001\u001f\u0001 \u0001 \u0005 \u0149\b \n \f \u014c\t \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0003!\u015e\b!\u0001\"\u0001\"\u0005\""+
		"\u0162\b\"\n\"\f\"\u0165\t\"\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0001"+
		"$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001"+
		"\'\u0001\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001)\u0001)\u0001)\u0000"+
		"\u0000*\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPR\u0000\n\u0001\u0000-.\u0001"+
		"\u000005\u0001\u00008:\u0001\u000067\u0002\u0000//67\u0001\u0000UV\u0001"+
		"\u0000\u001f$\u0001\u0000\'(\u0001\u0000@K\u0001\u0000LT\u0179\u0000U"+
		"\u0001\u0000\u0000\u0000\u0002Y\u0001\u0000\u0000\u0000\u0004c\u0001\u0000"+
		"\u0000\u0000\u0006e\u0001\u0000\u0000\u0000\bo\u0001\u0000\u0000\u0000"+
		"\n{\u0001\u0000\u0000\u0000\f\u0087\u0001\u0000\u0000\u0000\u000e\u0089"+
		"\u0001\u0000\u0000\u0000\u0010\u0092\u0001\u0000\u0000\u0000\u0012\u009e"+
		"\u0001\u0000\u0000\u0000\u0014\u00ab\u0001\u0000\u0000\u0000\u0016\u00ad"+
		"\u0001\u0000\u0000\u0000\u0018\u00af\u0001\u0000\u0000\u0000\u001a\u00b1"+
		"\u0001\u0000\u0000\u0000\u001c\u00b3\u0001\u0000\u0000\u0000\u001e\u00b5"+
		"\u0001\u0000\u0000\u0000 \u00b7\u0001\u0000\u0000\u0000\"\u00bd\u0001"+
		"\u0000\u0000\u0000$\u00d3\u0001\u0000\u0000\u0000&\u00d5\u0001\u0000\u0000"+
		"\u0000(\u00da\u0001\u0000\u0000\u0000*\u00e1\u0001\u0000\u0000\u0000,"+
		"\u00e8\u0001\u0000\u0000\u0000.\u00ef\u0001\u0000\u0000\u00000\u00f8\u0001"+
		"\u0000\u0000\u00002\u0101\u0001\u0000\u0000\u00004\u0113\u0001\u0000\u0000"+
		"\u00006\u011a\u0001\u0000\u0000\u00008\u0127\u0001\u0000\u0000\u0000:"+
		"\u0134\u0001\u0000\u0000\u0000<\u013b\u0001\u0000\u0000\u0000>\u0144\u0001"+
		"\u0000\u0000\u0000@\u0146\u0001\u0000\u0000\u0000B\u0152\u0001\u0000\u0000"+
		"\u0000D\u015f\u0001\u0000\u0000\u0000F\u0168\u0001\u0000\u0000\u0000H"+
		"\u016a\u0001\u0000\u0000\u0000J\u016d\u0001\u0000\u0000\u0000L\u0172\u0001"+
		"\u0000\u0000\u0000N\u0175\u0001\u0000\u0000\u0000P\u0177\u0001\u0000\u0000"+
		"\u0000R\u017c\u0001\u0000\u0000\u0000TV\u0003\"\u0011\u0000UT\u0001\u0000"+
		"\u0000\u0000VW\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000WX\u0001"+
		"\u0000\u0000\u0000X\u0001\u0001\u0000\u0000\u0000YZ\u0003\u0006\u0003"+
		"\u0000Z\u0003\u0001\u0000\u0000\u0000[\\\u0005\u0001\u0000\u0000\\d\u0005"+
		"Y\u0000\u0000]^\u0005\u0002\u0000\u0000^d\u0005Y\u0000\u0000_`\u0005\u0003"+
		"\u0000\u0000`a\u0005Y\u0000\u0000ab\u0005\u0004\u0000\u0000bd\u0005Y\u0000"+
		"\u0000c[\u0001\u0000\u0000\u0000c]\u0001\u0000\u0000\u0000c_\u0001\u0000"+
		"\u0000\u0000d\u0005\u0001\u0000\u0000\u0000ej\u0003\b\u0004\u0000fg\u0005"+
		".\u0000\u0000gi\u0003\b\u0004\u0000hf\u0001\u0000\u0000\u0000il\u0001"+
		"\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000"+
		"k\u0007\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000mp\u0003\n\u0005"+
		"\u0000np\u0003\u0004\u0002\u0000om\u0001\u0000\u0000\u0000on\u0001\u0000"+
		"\u0000\u0000px\u0001\u0000\u0000\u0000qt\u0005-\u0000\u0000ru\u0003\n"+
		"\u0005\u0000su\u0003\u0004\u0002\u0000tr\u0001\u0000\u0000\u0000ts\u0001"+
		"\u0000\u0000\u0000uw\u0001\u0000\u0000\u0000vq\u0001\u0000\u0000\u0000"+
		"wz\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000"+
		"\u0000y\t\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000{|\u0003\f"+
		"\u0006\u0000|}\u0003\u0018\f\u0000}~\u0003\f\u0006\u0000~\u000b\u0001"+
		"\u0000\u0000\u0000\u007f\u0088\u0003H$\u0000\u0080\u0088\u0003J%\u0000"+
		"\u0081\u0082\u0003 \u0010\u0000\u0082\u0083\u0003N\'\u0000\u0083\u0088"+
		"\u0001\u0000\u0000\u0000\u0084\u0088\u0003 \u0010\u0000\u0085\u0088\u0005"+
		"=\u0000\u0000\u0086\u0088\u0005>\u0000\u0000\u0087\u007f\u0001\u0000\u0000"+
		"\u0000\u0087\u0080\u0001\u0000\u0000\u0000\u0087\u0081\u0001\u0000\u0000"+
		"\u0000\u0087\u0084\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000"+
		"\u0000\u0087\u0086\u0001\u0000\u0000\u0000\u0088\r\u0001\u0000\u0000\u0000"+
		"\u0089\u008f\u0003\u0010\b\u0000\u008a\u008b\u0003\u001a\r\u0000\u008b"+
		"\u008c\u0003\u0010\b\u0000\u008c\u008e\u0001\u0000\u0000\u0000\u008d\u008a"+
		"\u0001\u0000\u0000\u0000\u008e\u0091\u0001\u0000\u0000\u0000\u008f\u008d"+
		"\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u000f"+
		"\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0092\u0098"+
		"\u0003\u0012\t\u0000\u0093\u0094\u0003\u001c\u000e\u0000\u0094\u0095\u0003"+
		"\u0012\t\u0000\u0095\u0097\u0001\u0000\u0000\u0000\u0096\u0093\u0001\u0000"+
		"\u0000\u0000\u0097\u009a\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000"+
		"\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u0011\u0001\u0000"+
		"\u0000\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009b\u009d\u0003\u001e"+
		"\u000f\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009d\u00a0\u0001\u0000"+
		"\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000"+
		"\u0000\u0000\u009f\u00a1\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000"+
		"\u0000\u0000\u00a1\u00a2\u0003\u0014\n\u0000\u00a2\u0013\u0001\u0000\u0000"+
		"\u0000\u00a3\u00ac\u0003 \u0010\u0000\u00a4\u00a7\u0005)\u0000\u0000\u00a5"+
		"\u00a8\u0003\u000e\u0007\u0000\u00a6\u00a8\u0003\u0012\t\u0000\u00a7\u00a5"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9"+
		"\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005*\u0000\u0000\u00aa\u00ac\u0001"+
		"\u0000\u0000\u0000\u00ab\u00a3\u0001\u0000\u0000\u0000\u00ab\u00a4\u0001"+
		"\u0000\u0000\u0000\u00ac\u0015\u0001\u0000\u0000\u0000\u00ad\u00ae\u0007"+
		"\u0000\u0000\u0000\u00ae\u0017\u0001\u0000\u0000\u0000\u00af\u00b0\u0007"+
		"\u0001\u0000\u0000\u00b0\u0019\u0001\u0000\u0000\u0000\u00b1\u00b2\u0007"+
		"\u0002\u0000\u0000\u00b2\u001b\u0001\u0000\u0000\u0000\u00b3\u00b4\u0007"+
		"\u0003\u0000\u0000\u00b4\u001d\u0001\u0000\u0000\u0000\u00b5\u00b6\u0007"+
		"\u0004\u0000\u0000\u00b6\u001f\u0001\u0000\u0000\u0000\u00b7\u00b8\u0007"+
		"\u0005\u0000\u0000\u00b8!\u0001\u0000\u0000\u0000\u00b9\u00ba\u0005\u0005"+
		"\u0000\u0000\u00ba\u00bb\u0003\u0002\u0001\u0000\u00bb\u00bc\u0005\u0006"+
		"\u0000\u0000\u00bc\u00be\u0001\u0000\u0000\u0000\u00bd\u00b9\u0001\u0000"+
		"\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000"+
		"\u0000\u0000\u00bf\u00c2\u0003$\u0012\u0000\u00c0\u00c1\u0005\u0007\u0000"+
		"\u0000\u00c1\u00c3\u0003\u0002\u0001\u0000\u00c2\u00c0\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000"+
		"\u0000\u00c4\u00c5\u0005Z\u0000\u0000\u00c5#\u0001\u0000\u0000\u0000\u00c6"+
		"\u00d4\u0003&\u0013\u0000\u00c7\u00d4\u0003(\u0014\u0000\u00c8\u00d4\u0003"+
		"*\u0015\u0000\u00c9\u00d4\u0003,\u0016\u0000\u00ca\u00d4\u0003.\u0017"+
		"\u0000\u00cb\u00d4\u00030\u0018\u0000\u00cc\u00d4\u00032\u0019\u0000\u00cd"+
		"\u00d4\u0003:\u001d\u0000\u00ce\u00d4\u0003@ \u0000\u00cf\u00d4\u0003"+
		"B!\u0000\u00d0\u00d4\u00034\u001a\u0000\u00d1\u00d4\u00036\u001b\u0000"+
		"\u00d2\u00d4\u00038\u001c\u0000\u00d3\u00c6\u0001\u0000\u0000\u0000\u00d3"+
		"\u00c7\u0001\u0000\u0000\u0000\u00d3\u00c8\u0001\u0000\u0000\u0000\u00d3"+
		"\u00c9\u0001\u0000\u0000\u0000\u00d3\u00ca\u0001\u0000\u0000\u0000\u00d3"+
		"\u00cb\u0001\u0000\u0000\u0000\u00d3\u00cc\u0001\u0000\u0000\u0000\u00d3"+
		"\u00cd\u0001\u0000\u0000\u0000\u00d3\u00ce\u0001\u0000\u0000\u0000\u00d3"+
		"\u00cf\u0001\u0000\u0000\u0000\u00d3\u00d0\u0001\u0000\u0000\u0000\u00d3"+
		"\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d2\u0001\u0000\u0000\u0000\u00d4"+
		"%\u0001\u0000\u0000\u0000\u00d5\u00d6\u0005\b\u0000\u0000\u00d6\u00d7"+
		"\u0005+\u0000\u0000\u00d7\u00d8\u0005W\u0000\u0000\u00d8\u00d9\u0005,"+
		"\u0000\u0000\u00d9\'\u0001\u0000\u0000\u0000\u00da\u00db\u0005\t\u0000"+
		"\u0000\u00db\u00dc\u0003L&\u0000\u00dc\u00dd\u0005\n\u0000\u0000\u00dd"+
		"\u00de\u0003P(\u0000\u00de\u00df\u0005\u0004\u0000\u0000\u00df\u00e0\u0003"+
		"P(\u0000\u00e0)\u0001\u0000\u0000\u0000\u00e1\u00e2\u0005\u000b\u0000"+
		"\u0000\u00e2\u00e3\u0003L&\u0000\u00e3\u00e4\u0005\f\u0000\u0000\u00e4"+
		"\u00e5\u0003P(\u0000\u00e5\u00e6\u0005\n\u0000\u0000\u00e6\u00e7\u0003"+
		"R)\u0000\u00e7+\u0001\u0000\u0000\u0000\u00e8\u00e9\u0005\r\u0000\u0000"+
		"\u00e9\u00ea\u0003L&\u0000\u00ea\u00eb\u0005\n\u0000\u0000\u00eb\u00ec"+
		"\u0003P(\u0000\u00ec\u00ed\u0005\u0004\u0000\u0000\u00ed\u00ee\u0003R"+
		")\u0000\u00ee-\u0001\u0000\u0000\u0000\u00ef\u00f0\u0005\u000e\u0000\u0000"+
		"\u00f0\u00f1\u0003L&\u0000\u00f1\u00f2\u0005\n\u0000\u0000\u00f2\u00f3"+
		"\u0003P(\u0000\u00f3\u00f4\u0005\f\u0000\u0000\u00f4\u00f5\u0003N\'\u0000"+
		"\u00f5\u00f6\u0005\u000f\u0000\u0000\u00f6\u00f7\u0003R)\u0000\u00f7/"+
		"\u0001\u0000\u0000\u0000\u00f8\u00f9\u0005\u0010\u0000\u0000\u00f9\u00fa"+
		"\u0003L&\u0000\u00fa\u00fb\u0005\u0011\u0000\u0000\u00fb\u00fc\u0003L"+
		"&\u0000\u00fc\u00fd\u0005\u0004\u0000\u0000\u00fd\u00fe\u0003R)\u0000"+
		"\u00fe\u00ff\u0005\u0012\u0000\u0000\u00ff\u0100\u0003P(\u0000\u01001"+
		"\u0001\u0000\u0000\u0000\u0101\u0102\u0005\u0013\u0000\u0000\u0102\u0103"+
		"\u0003L&\u0000\u0103\u0104\u0005\u0011\u0000\u0000\u0104\u0105\u0003L"+
		"&\u0000\u0105\u0106\u0005\n\u0000\u0000\u0106\u0107\u0003R)\u0000\u0107"+
		"\u0108\u0005\u0014\u0000\u0000\u0108\u0109\u0005U\u0000\u0000\u0109\u010e"+
		"\u0005\u0015\u0000\u0000\u010a\u010b\u0005\u0016\u0000\u0000\u010b\u010c"+
		"\u0005+\u0000\u0000\u010c\u010d\u0005X\u0000\u0000\u010d\u010f\u0005,"+
		"\u0000\u0000\u010e\u010a\u0001\u0000\u0000\u0000\u010e\u010f\u0001\u0000"+
		"\u0000\u0000\u010f\u0110\u0001\u0000\u0000\u0000\u0110\u0111\u0005\n\u0000"+
		"\u0000\u0111\u0112\u0003P(\u0000\u01123\u0001\u0000\u0000\u0000\u0113"+
		"\u0114\u0005\u0017\u0000\u0000\u0114\u0115\u0003L&\u0000\u0115\u0116\u0005"+
		"\n\u0000\u0000\u0116\u0117\u0003P(\u0000\u0117\u0118\u0005\u000f\u0000"+
		"\u0000\u0118\u0119\u0003R)\u0000\u01195\u0001\u0000\u0000\u0000\u011a"+
		"\u011b\u0005\u0018\u0000\u0000\u011b\u011c\u0005+\u0000\u0000\u011c\u011d"+
		"\u0005U\u0000\u0000\u011d\u011e\u0005,\u0000\u0000\u011e\u011f\u0005\u0019"+
		"\u0000\u0000\u011f\u0120\u0005+\u0000\u0000\u0120\u0121\u0005X\u0000\u0000"+
		"\u0121\u0122\u0005,\u0000\u0000\u0122\u0123\u0005\u001a\u0000\u0000\u0123"+
		"\u0124\u0003L&\u0000\u0124\u0125\u0005\n\u0000\u0000\u0125\u0126\u0003"+
		"P(\u0000\u01267\u0001\u0000\u0000\u0000\u0127\u0128\u0005\u001b\u0000"+
		"\u0000\u0128\u0129\u0005+\u0000\u0000\u0129\u012a\u0005U\u0000\u0000\u012a"+
		"\u012b\u0005,\u0000\u0000\u012b\u012c\u0005\u0019\u0000\u0000\u012c\u012d"+
		"\u0005+\u0000\u0000\u012d\u012e\u0005X\u0000\u0000\u012e\u012f\u0005,"+
		"\u0000\u0000\u012f\u0130\u0005\n\u0000\u0000\u0130\u0131\u0003P(\u0000"+
		"\u0131\u0132\u0005\u001c\u0000\u0000\u0132\u0133\u0003L&\u0000\u01339"+
		"\u0001\u0000\u0000\u0000\u0134\u0135\u0005\u0017\u0000\u0000\u0135\u0136"+
		"\u0003L&\u0000\u0136\u0137\u0005\n\u0000\u0000\u0137\u0139\u0003P(\u0000"+
		"\u0138\u013a\u0003<\u001e\u0000\u0139\u0138\u0001\u0000\u0000\u0000\u0139"+
		"\u013a\u0001\u0000\u0000\u0000\u013a;\u0001\u0000\u0000\u0000\u013b\u013f"+
		"\u0005\u001d\u0000\u0000\u013c\u013e\u0003>\u001f\u0000\u013d\u013c\u0001"+
		"\u0000\u0000\u0000\u013e\u0141\u0001\u0000\u0000\u0000\u013f\u013d\u0001"+
		"\u0000\u0000\u0000\u013f\u0140\u0001\u0000\u0000\u0000\u0140\u0142\u0001"+
		"\u0000\u0000\u0000\u0141\u013f\u0001\u0000\u0000\u0000\u0142\u0143\u0005"+
		"\u001e\u0000\u0000\u0143=\u0001\u0000\u0000\u0000\u0144\u0145\u0007\u0006"+
		"\u0000\u0000\u0145?\u0001\u0000\u0000\u0000\u0146\u014a\u0005%\u0000\u0000"+
		"\u0147\u0149\u0005^\u0000\u0000\u0148\u0147\u0001\u0000\u0000\u0000\u0149"+
		"\u014c\u0001\u0000\u0000\u0000\u014a\u0148\u0001\u0000\u0000\u0000\u014a"+
		"\u014b\u0001\u0000\u0000\u0000\u014b\u014d\u0001\u0000\u0000\u0000\u014c"+
		"\u014a\u0001\u0000\u0000\u0000\u014d\u014e\u0005&\u0000\u0000\u014e\u014f"+
		"\u0003L&\u0000\u014f\u0150\u0005\n\u0000\u0000\u0150\u0151\u0003P(\u0000"+
		"\u0151A\u0001\u0000\u0000\u0000\u0152\u0153\u0005\u001b\u0000\u0000\u0153"+
		"\u0154\u0005+\u0000\u0000\u0154\u0155\u0005U\u0000\u0000\u0155\u0156\u0005"+
		",\u0000\u0000\u0156\u0157\u0005\u0019\u0000\u0000\u0157\u0158\u0005+\u0000"+
		"\u0000\u0158\u0159\u0005X\u0000\u0000\u0159\u015a\u0005,\u0000\u0000\u015a"+
		"\u015b\u0005\n\u0000\u0000\u015b\u015d\u0003P(\u0000\u015c\u015e\u0003"+
		"D\"\u0000\u015d\u015c\u0001\u0000\u0000\u0000\u015d\u015e\u0001\u0000"+
		"\u0000\u0000\u015eC\u0001\u0000\u0000\u0000\u015f\u0163\u0005\u001d\u0000"+
		"\u0000\u0160\u0162\u0003F#\u0000\u0161\u0160\u0001\u0000\u0000\u0000\u0162"+
		"\u0165\u0001\u0000\u0000\u0000\u0163\u0161\u0001\u0000\u0000\u0000\u0163"+
		"\u0164\u0001\u0000\u0000\u0000\u0164\u0166\u0001\u0000\u0000\u0000\u0165"+
		"\u0163\u0001\u0000\u0000\u0000\u0166\u0167\u0005\u001e\u0000\u0000\u0167"+
		"E\u0001\u0000\u0000\u0000\u0168\u0169\u0007\u0007\u0000\u0000\u0169G\u0001"+
		"\u0000\u0000\u0000\u016a\u016b\u0005;\u0000\u0000\u016b\u016c\u0003P("+
		"\u0000\u016cI\u0001\u0000\u0000\u0000\u016d\u016e\u0005<\u0000\u0000\u016e"+
		"\u016f\u0003N\'\u0000\u016f\u0170\u0005\u000f\u0000\u0000\u0170\u0171"+
		"\u0003R)\u0000\u0171K\u0001\u0000\u0000\u0000\u0172\u0173\u0003\u000e"+
		"\u0007\u0000\u0173\u0174\u0003N\'\u0000\u0174M\u0001\u0000\u0000\u0000"+
		"\u0175\u0176\u0007\b\u0000\u0000\u0176O\u0001\u0000\u0000\u0000\u0177"+
		"\u0178\u0005?\u0000\u0000\u0178\u0179\u0005+\u0000\u0000\u0179\u017a\u0005"+
		"X\u0000\u0000\u017a\u017b\u0005,\u0000\u0000\u017bQ\u0001\u0000\u0000"+
		"\u0000\u017c\u017d\u0007\t\u0000\u0000\u017dS\u0001\u0000\u0000\u0000"+
		"\u0015Wcjotx\u0087\u008f\u0098\u009e\u00a7\u00ab\u00bd\u00c2\u00d3\u010e"+
		"\u0139\u013f\u014a\u015d\u0163";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}