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
		T__17=18, T__18=19, T__19=20, LPARENT=21, RPARENT=22, LBRACK=23, RBRACK=24, 
		LOGIC_AND=25, LOGIC_OR=26, LOGIC_NOT=27, EQ=28, NEQ=29, LT=30, GT=31, 
		LE=32, GE=33, ADD=34, SUB=35, MUL=36, DIV=37, MOD=38, BALANCE=39, PRICE=40, 
		SLIPPAGE=41, FEE=42, WALLET=43, USDT=44, USDC=45, ETH=46, DAI=47, BTC=48, 
		WBTC=49, UNI=50, SUSHI=51, AAVE_token=52, MATIC=53, COMP=54, AAVE=55, 
		UNISWAP=56, COMPOUND=57, YEARN=58, SUSHISWAP=59, CURVE=60, ONEINCH=61, 
		POLYGON=62, AVAX=63, IDENTIFIER=64, KEY=65, DEC_INT=66, DEC_FLOAT=67, 
		TIME=68, SEMI=69, LINE_COMMENT=70, COMMENT=71, BLANK=72;
	public static final int
		RULE_program = 0, RULE_condition = 1, RULE_timeCondition = 2, RULE_orExpression = 3, 
		RULE_andExpression = 4, RULE_comparisonExpression = 5, RULE_comparisonElement = 6, 
		RULE_binaryExpression = 7, RULE_lowBinaryExpression = 8, RULE_unaryExpression = 9, 
		RULE_primaryExpression = 10, RULE_logicalOperator = 11, RULE_comparisonOperator = 12, 
		RULE_highBinaryOperator = 13, RULE_lowBinaryOperator = 14, RULE_unaryOperator = 15, 
		RULE_number = 16, RULE_triggerStatement = 17, RULE_statement = 18, RULE_transferStatement = 19, 
		RULE_borrowStatement = 20, RULE_repayBorrowStatement = 21, RULE_stakeStatement = 22, 
		RULE_swapStatement = 23, RULE_addLiquidityStatement = 24, RULE_removeLiquidityStatement = 25, 
		RULE_walletBalance = 26, RULE_assetPrice = 27, RULE_amount = 28, RULE_asset = 29, 
		RULE_pair = 30, RULE_wallet = 31, RULE_platform = 32;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "condition", "timeCondition", "orExpression", "andExpression", 
			"comparisonExpression", "comparisonElement", "binaryExpression", "lowBinaryExpression", 
			"unaryExpression", "primaryExpression", "logicalOperator", "comparisonOperator", 
			"highBinaryOperator", "lowBinaryOperator", "unaryOperator", "number", 
			"triggerStatement", "statement", "transferStatement", "borrowStatement", 
			"repayBorrowStatement", "stakeStatement", "swapStatement", "addLiquidityStatement", 
			"removeLiquidityStatement", "walletBalance", "assetPrice", "amount", 
			"asset", "pair", "wallet", "platform"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'time before'", "'time after'", "'time during'", "'to'", "'trigger'", 
			"'then'", "'checking'", "'transfer'", "'from'", "'borrow'", "'for'", 
			"'using'", "'as collateral'", "'repay'", "'stake'", "'swap'", "'on'", 
			"'add liquidity'", "','", "'remove liquidity'", "'('", "')'", "'['", 
			"']'", "'and'", "'or'", "'not'", "'=='", "'!='", "'<'", "'>'", "'<='", 
			"'>='", "'+'", "'-'", "'*'", "'/'", "'%'", "'balance'", "'price'", "'slippage'", 
			"'fee'", "'wallet'", "'USDT'", "'USDC'", "'ETH'", "'DAI'", "'BTC'", "'WBTC'", 
			"'UNI'", "'SUSHI'", "'AAVE'", "'MATIC'", "'COMAP'", "'Aave'", "'Uniswap'", 
			"'Compound'", "'Yearn'", "'Sushiswap'", "'Curve'", "'1inch'", "'Polygon'", 
			"'Avax'", null, null, null, null, null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "LPARENT", "RPARENT", 
			"LBRACK", "RBRACK", "LOGIC_AND", "LOGIC_OR", "LOGIC_NOT", "EQ", "NEQ", 
			"LT", "GT", "LE", "GE", "ADD", "SUB", "MUL", "DIV", "MOD", "BALANCE", 
			"PRICE", "SLIPPAGE", "FEE", "WALLET", "USDT", "USDC", "ETH", "DAI", "BTC", 
			"WBTC", "UNI", "SUSHI", "AAVE_token", "MATIC", "COMP", "AAVE", "UNISWAP", 
			"COMPOUND", "YEARN", "SUSHISWAP", "CURVE", "ONEINCH", "POLYGON", "AVAX", 
			"IDENTIFIER", "KEY", "DEC_INT", "DEC_FLOAT", "TIME", "SEMI", "LINE_COMMENT", 
			"COMMENT", "BLANK"
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
			setState(67); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(66);
				triggerStatement();
				}
				}
				setState(69); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1426720L) != 0) );
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
			setState(71);
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
			setState(81);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				match(T__0);
				setState(74);
				match(TIME);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				match(T__1);
				setState(76);
				match(TIME);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
				match(T__2);
				setState(78);
				match(TIME);
				setState(79);
				match(T__3);
				setState(80);
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
			setState(83);
			andExpression();
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LOGIC_OR) {
				{
				{
				setState(84);
				match(LOGIC_OR);
				setState(85);
				andExpression();
				}
				}
				setState(90);
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
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPARENT:
			case BALANCE:
			case PRICE:
			case SLIPPAGE:
			case FEE:
			case DEC_INT:
			case DEC_FLOAT:
				{
				setState(91);
				comparisonExpression();
				}
				break;
			case T__0:
			case T__1:
			case T__2:
				{
				setState(92);
				timeCondition();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LOGIC_AND) {
				{
				{
				setState(95);
				match(LOGIC_AND);
				setState(98);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LPARENT:
				case BALANCE:
				case PRICE:
				case SLIPPAGE:
				case FEE:
				case DEC_INT:
				case DEC_FLOAT:
					{
					setState(96);
					comparisonExpression();
					}
					break;
				case T__0:
				case T__1:
				case T__2:
					{
					setState(97);
					timeCondition();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
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
			setState(105);
			comparisonElement();
			setState(106);
			comparisonOperator();
			setState(107);
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
		public TerminalNode LPARENT() { return getToken(IntentDSLParser.LPARENT, 0); }
		public OrExpressionContext orExpression() {
			return getRuleContext(OrExpressionContext.class,0);
		}
		public TerminalNode RPARENT() { return getToken(IntentDSLParser.RPARENT, 0); }
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
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				walletBalance();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				assetPrice();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				number();
				setState(112);
				asset();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(114);
				number();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(115);
				match(SLIPPAGE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(116);
				match(FEE);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(117);
				match(LPARENT);
				setState(118);
				orExpression();
				setState(119);
				match(RPARENT);
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
			setState(123);
			lowBinaryExpression();
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 481036337152L) != 0)) {
				{
				{
				setState(124);
				highBinaryOperator();
				setState(125);
				lowBinaryExpression();
				}
				}
				setState(131);
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
			setState(132);
			unaryExpression();
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD || _la==SUB) {
				{
				{
				setState(133);
				lowBinaryOperator();
				setState(134);
				unaryExpression();
				}
				}
				setState(140);
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
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 51673825280L) != 0)) {
				{
				{
				setState(141);
				unaryOperator();
				}
				}
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(147);
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
			setState(157);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEC_INT:
			case DEC_FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				number();
				}
				break;
			case LPARENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(150);
				match(LPARENT);
				setState(153);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(151);
					binaryExpression();
					}
					break;
				case 2:
					{
					setState(152);
					unaryExpression();
					}
					break;
				}
				setState(155);
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
			setState(159);
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
			setState(161);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16911433728L) != 0)) ) {
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
			setState(163);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 481036337152L) != 0)) ) {
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
			setState(165);
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
			setState(167);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 51673825280L) != 0)) ) {
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
			setState(169);
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
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(171);
				match(T__4);
				setState(172);
				condition();
				setState(173);
				match(T__5);
				}
			}

			setState(177);
			statement();
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(178);
				match(T__6);
				setState(179);
				condition();
				}
			}

			setState(182);
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
		public TransferStatementContext transferStatement() {
			return getRuleContext(TransferStatementContext.class,0);
		}
		public BorrowStatementContext borrowStatement() {
			return getRuleContext(BorrowStatementContext.class,0);
		}
		public RepayBorrowStatementContext repayBorrowStatement() {
			return getRuleContext(RepayBorrowStatementContext.class,0);
		}
		public StakeStatementContext stakeStatement() {
			return getRuleContext(StakeStatementContext.class,0);
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
			setState(191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(184);
				transferStatement();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				borrowStatement();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 3);
				{
				setState(186);
				repayBorrowStatement();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 4);
				{
				setState(187);
				stakeStatement();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 5);
				{
				setState(188);
				swapStatement();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 6);
				{
				setState(189);
				addLiquidityStatement();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 7);
				{
				setState(190);
				removeLiquidityStatement();
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
		enterRule(_localctx, 38, RULE_transferStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(T__7);
			setState(194);
			amount();
			setState(195);
			match(T__8);
			setState(196);
			wallet();
			setState(197);
			match(T__3);
			setState(198);
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
		enterRule(_localctx, 40, RULE_borrowStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(T__9);
			setState(201);
			amount();
			setState(202);
			match(T__10);
			setState(203);
			wallet();
			setState(204);
			match(T__8);
			setState(205);
			platform();
			setState(206);
			match(T__11);
			setState(207);
			amount();
			setState(208);
			match(T__8);
			setState(209);
			wallet();
			setState(210);
			match(T__12);
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
		enterRule(_localctx, 42, RULE_repayBorrowStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(T__13);
			setState(213);
			amount();
			setState(214);
			match(T__8);
			setState(215);
			wallet();
			setState(216);
			match(T__3);
			setState(217);
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
	public static class StakeStatementContext extends ParserRuleContext {
		public AmountContext amount() {
			return getRuleContext(AmountContext.class,0);
		}
		public WalletContext wallet() {
			return getRuleContext(WalletContext.class,0);
		}
		public PlatformContext platform() {
			return getRuleContext(PlatformContext.class,0);
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
		enterRule(_localctx, 44, RULE_stakeStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(T__14);
			setState(220);
			amount();
			setState(221);
			match(T__8);
			setState(222);
			wallet();
			setState(223);
			match(T__3);
			setState(224);
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
			setState(226);
			match(T__15);
			setState(227);
			amount();
			setState(228);
			match(T__8);
			setState(229);
			wallet();
			setState(230);
			match(T__10);
			setState(231);
			asset();
			setState(232);
			match(T__16);
			setState(233);
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
		public List<WalletContext> wallet() {
			return getRuleContexts(WalletContext.class);
		}
		public WalletContext wallet(int i) {
			return getRuleContext(WalletContext.class,i);
		}
		public PlatformContext platform() {
			return getRuleContext(PlatformContext.class,0);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(T__17);
			setState(236);
			amount();
			setState(237);
			match(T__8);
			setState(238);
			wallet();
			setState(246);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__18) {
				{
				{
				setState(239);
				match(T__18);
				setState(240);
				amount();
				setState(241);
				match(T__8);
				setState(242);
				wallet();
				}
				}
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(249);
			match(T__3);
			setState(250);
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
	public static class RemoveLiquidityStatementContext extends ParserRuleContext {
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
			setState(252);
			match(T__19);
			setState(253);
			amount();
			setState(254);
			match(T__3);
			setState(255);
			wallet();
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__18) {
				{
				{
				setState(256);
				match(T__18);
				setState(257);
				amount();
				setState(258);
				match(T__3);
				setState(259);
				wallet();
				}
				}
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(266);
			match(T__8);
			setState(267);
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
		enterRule(_localctx, 52, RULE_walletBalance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(BALANCE);
			setState(270);
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
		enterRule(_localctx, 54, RULE_assetPrice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(PRICE);
			setState(273);
			asset();
			setState(274);
			match(T__16);
			setState(275);
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
		enterRule(_localctx, 56, RULE_amount);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			binaryExpression();
			setState(278);
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
		enterRule(_localctx, 58, RULE_asset);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 36011204832919552L) != 0)) ) {
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
		enterRule(_localctx, 60, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			asset();
			setState(283);
			match(LOGIC_AND);
			setState(284);
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
		enterRule(_localctx, 62, RULE_wallet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(WALLET);
			setState(287);
			match(LBRACK);
			setState(288);
			match(KEY);
			setState(289);
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
		enterRule(_localctx, 64, RULE_platform);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & -36028797018963968L) != 0)) ) {
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
		"\u0004\u0001H\u0126\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0001\u0000\u0004\u0000D\b\u0000"+
		"\u000b\u0000\f\u0000E\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002R\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"W\b\u0003\n\u0003\f\u0003Z\t\u0003\u0001\u0004\u0001\u0004\u0003\u0004"+
		"^\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004c\b\u0004\u0005"+
		"\u0004e\b\u0004\n\u0004\f\u0004h\t\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006z\b\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0005\u0007\u0080\b\u0007\n\u0007\f\u0007\u0083\t\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u0089\b\b\n\b\f\b\u008c\t\b\u0001"+
		"\t\u0005\t\u008f\b\t\n\t\f\t\u0092\t\t\u0001\t\u0001\t\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0003\n\u009a\b\n\u0001\n\u0001\n\u0003\n\u009e\b\n\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0003\u0011\u00b0\b\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u00b5\b\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0003\u0012\u00c0\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018"+
		"\u00f5\b\u0018\n\u0018\f\u0018\u00f8\t\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u0106\b\u0019\n"+
		"\u0019\f\u0019\u0109\t\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0000"+
		"\u0000!\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.02468:<>@\u0000\b\u0001\u0000\u0019\u001a\u0001"+
		"\u0000\u001c!\u0001\u0000$&\u0001\u0000\"#\u0002\u0000\u001b\u001b\"#"+
		"\u0001\u0000BC\u0001\u0000,6\u0001\u00007?\u0120\u0000C\u0001\u0000\u0000"+
		"\u0000\u0002G\u0001\u0000\u0000\u0000\u0004Q\u0001\u0000\u0000\u0000\u0006"+
		"S\u0001\u0000\u0000\u0000\b]\u0001\u0000\u0000\u0000\ni\u0001\u0000\u0000"+
		"\u0000\fy\u0001\u0000\u0000\u0000\u000e{\u0001\u0000\u0000\u0000\u0010"+
		"\u0084\u0001\u0000\u0000\u0000\u0012\u0090\u0001\u0000\u0000\u0000\u0014"+
		"\u009d\u0001\u0000\u0000\u0000\u0016\u009f\u0001\u0000\u0000\u0000\u0018"+
		"\u00a1\u0001\u0000\u0000\u0000\u001a\u00a3\u0001\u0000\u0000\u0000\u001c"+
		"\u00a5\u0001\u0000\u0000\u0000\u001e\u00a7\u0001\u0000\u0000\u0000 \u00a9"+
		"\u0001\u0000\u0000\u0000\"\u00af\u0001\u0000\u0000\u0000$\u00bf\u0001"+
		"\u0000\u0000\u0000&\u00c1\u0001\u0000\u0000\u0000(\u00c8\u0001\u0000\u0000"+
		"\u0000*\u00d4\u0001\u0000\u0000\u0000,\u00db\u0001\u0000\u0000\u0000."+
		"\u00e2\u0001\u0000\u0000\u00000\u00eb\u0001\u0000\u0000\u00002\u00fc\u0001"+
		"\u0000\u0000\u00004\u010d\u0001\u0000\u0000\u00006\u0110\u0001\u0000\u0000"+
		"\u00008\u0115\u0001\u0000\u0000\u0000:\u0118\u0001\u0000\u0000\u0000<"+
		"\u011a\u0001\u0000\u0000\u0000>\u011e\u0001\u0000\u0000\u0000@\u0123\u0001"+
		"\u0000\u0000\u0000BD\u0003\"\u0011\u0000CB\u0001\u0000\u0000\u0000DE\u0001"+
		"\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000"+
		"F\u0001\u0001\u0000\u0000\u0000GH\u0003\u0006\u0003\u0000H\u0003\u0001"+
		"\u0000\u0000\u0000IJ\u0005\u0001\u0000\u0000JR\u0005D\u0000\u0000KL\u0005"+
		"\u0002\u0000\u0000LR\u0005D\u0000\u0000MN\u0005\u0003\u0000\u0000NO\u0005"+
		"D\u0000\u0000OP\u0005\u0004\u0000\u0000PR\u0005D\u0000\u0000QI\u0001\u0000"+
		"\u0000\u0000QK\u0001\u0000\u0000\u0000QM\u0001\u0000\u0000\u0000R\u0005"+
		"\u0001\u0000\u0000\u0000SX\u0003\b\u0004\u0000TU\u0005\u001a\u0000\u0000"+
		"UW\u0003\b\u0004\u0000VT\u0001\u0000\u0000\u0000WZ\u0001\u0000\u0000\u0000"+
		"XV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000Y\u0007\u0001\u0000"+
		"\u0000\u0000ZX\u0001\u0000\u0000\u0000[^\u0003\n\u0005\u0000\\^\u0003"+
		"\u0004\u0002\u0000][\u0001\u0000\u0000\u0000]\\\u0001\u0000\u0000\u0000"+
		"^f\u0001\u0000\u0000\u0000_b\u0005\u0019\u0000\u0000`c\u0003\n\u0005\u0000"+
		"ac\u0003\u0004\u0002\u0000b`\u0001\u0000\u0000\u0000ba\u0001\u0000\u0000"+
		"\u0000ce\u0001\u0000\u0000\u0000d_\u0001\u0000\u0000\u0000eh\u0001\u0000"+
		"\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000g\t\u0001"+
		"\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000ij\u0003\f\u0006\u0000jk\u0003"+
		"\u0018\f\u0000kl\u0003\f\u0006\u0000l\u000b\u0001\u0000\u0000\u0000mz"+
		"\u00034\u001a\u0000nz\u00036\u001b\u0000op\u0003 \u0010\u0000pq\u0003"+
		":\u001d\u0000qz\u0001\u0000\u0000\u0000rz\u0003 \u0010\u0000sz\u0005)"+
		"\u0000\u0000tz\u0005*\u0000\u0000uv\u0005\u0015\u0000\u0000vw\u0003\u0006"+
		"\u0003\u0000wx\u0005\u0016\u0000\u0000xz\u0001\u0000\u0000\u0000ym\u0001"+
		"\u0000\u0000\u0000yn\u0001\u0000\u0000\u0000yo\u0001\u0000\u0000\u0000"+
		"yr\u0001\u0000\u0000\u0000ys\u0001\u0000\u0000\u0000yt\u0001\u0000\u0000"+
		"\u0000yu\u0001\u0000\u0000\u0000z\r\u0001\u0000\u0000\u0000{\u0081\u0003"+
		"\u0010\b\u0000|}\u0003\u001a\r\u0000}~\u0003\u0010\b\u0000~\u0080\u0001"+
		"\u0000\u0000\u0000\u007f|\u0001\u0000\u0000\u0000\u0080\u0083\u0001\u0000"+
		"\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000"+
		"\u0000\u0000\u0082\u000f\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000"+
		"\u0000\u0000\u0084\u008a\u0003\u0012\t\u0000\u0085\u0086\u0003\u001c\u000e"+
		"\u0000\u0086\u0087\u0003\u0012\t\u0000\u0087\u0089\u0001\u0000\u0000\u0000"+
		"\u0088\u0085\u0001\u0000\u0000\u0000\u0089\u008c\u0001\u0000\u0000\u0000"+
		"\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000"+
		"\u008b\u0011\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000"+
		"\u008d\u008f\u0003\u001e\u000f\u0000\u008e\u008d\u0001\u0000\u0000\u0000"+
		"\u008f\u0092\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000"+
		"\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0093\u0001\u0000\u0000\u0000"+
		"\u0092\u0090\u0001\u0000\u0000\u0000\u0093\u0094\u0003\u0014\n\u0000\u0094"+
		"\u0013\u0001\u0000\u0000\u0000\u0095\u009e\u0003 \u0010\u0000\u0096\u0099"+
		"\u0005\u0015\u0000\u0000\u0097\u009a\u0003\u000e\u0007\u0000\u0098\u009a"+
		"\u0003\u0012\t\u0000\u0099\u0097\u0001\u0000\u0000\u0000\u0099\u0098\u0001"+
		"\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u009c\u0005"+
		"\u0016\u0000\u0000\u009c\u009e\u0001\u0000\u0000\u0000\u009d\u0095\u0001"+
		"\u0000\u0000\u0000\u009d\u0096\u0001\u0000\u0000\u0000\u009e\u0015\u0001"+
		"\u0000\u0000\u0000\u009f\u00a0\u0007\u0000\u0000\u0000\u00a0\u0017\u0001"+
		"\u0000\u0000\u0000\u00a1\u00a2\u0007\u0001\u0000\u0000\u00a2\u0019\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a4\u0007\u0002\u0000\u0000\u00a4\u001b\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a6\u0007\u0003\u0000\u0000\u00a6\u001d\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a8\u0007\u0004\u0000\u0000\u00a8\u001f\u0001"+
		"\u0000\u0000\u0000\u00a9\u00aa\u0007\u0005\u0000\u0000\u00aa!\u0001\u0000"+
		"\u0000\u0000\u00ab\u00ac\u0005\u0005\u0000\u0000\u00ac\u00ad\u0003\u0002"+
		"\u0001\u0000\u00ad\u00ae\u0005\u0006\u0000\u0000\u00ae\u00b0\u0001\u0000"+
		"\u0000\u0000\u00af\u00ab\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000"+
		"\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1\u00b4\u0003$\u0012"+
		"\u0000\u00b2\u00b3\u0005\u0007\u0000\u0000\u00b3\u00b5\u0003\u0002\u0001"+
		"\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b7\u0005E\u0000\u0000"+
		"\u00b7#\u0001\u0000\u0000\u0000\u00b8\u00c0\u0003&\u0013\u0000\u00b9\u00c0"+
		"\u0003(\u0014\u0000\u00ba\u00c0\u0003*\u0015\u0000\u00bb\u00c0\u0003,"+
		"\u0016\u0000\u00bc\u00c0\u0003.\u0017\u0000\u00bd\u00c0\u00030\u0018\u0000"+
		"\u00be\u00c0\u00032\u0019\u0000\u00bf\u00b8\u0001\u0000\u0000\u0000\u00bf"+
		"\u00b9\u0001\u0000\u0000\u0000\u00bf\u00ba\u0001\u0000\u0000\u0000\u00bf"+
		"\u00bb\u0001\u0000\u0000\u0000\u00bf\u00bc\u0001\u0000\u0000\u0000\u00bf"+
		"\u00bd\u0001\u0000\u0000\u0000\u00bf\u00be\u0001\u0000\u0000\u0000\u00c0"+
		"%\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005\b\u0000\u0000\u00c2\u00c3"+
		"\u00038\u001c\u0000\u00c3\u00c4\u0005\t\u0000\u0000\u00c4\u00c5\u0003"+
		">\u001f\u0000\u00c5\u00c6\u0005\u0004\u0000\u0000\u00c6\u00c7\u0003>\u001f"+
		"\u0000\u00c7\'\u0001\u0000\u0000\u0000\u00c8\u00c9\u0005\n\u0000\u0000"+
		"\u00c9\u00ca\u00038\u001c\u0000\u00ca\u00cb\u0005\u000b\u0000\u0000\u00cb"+
		"\u00cc\u0003>\u001f\u0000\u00cc\u00cd\u0005\t\u0000\u0000\u00cd\u00ce"+
		"\u0003@ \u0000\u00ce\u00cf\u0005\f\u0000\u0000\u00cf\u00d0\u00038\u001c"+
		"\u0000\u00d0\u00d1\u0005\t\u0000\u0000\u00d1\u00d2\u0003>\u001f\u0000"+
		"\u00d2\u00d3\u0005\r\u0000\u0000\u00d3)\u0001\u0000\u0000\u0000\u00d4"+
		"\u00d5\u0005\u000e\u0000\u0000\u00d5\u00d6\u00038\u001c\u0000\u00d6\u00d7"+
		"\u0005\t\u0000\u0000\u00d7\u00d8\u0003>\u001f\u0000\u00d8\u00d9\u0005"+
		"\u0004\u0000\u0000\u00d9\u00da\u0003@ \u0000\u00da+\u0001\u0000\u0000"+
		"\u0000\u00db\u00dc\u0005\u000f\u0000\u0000\u00dc\u00dd\u00038\u001c\u0000"+
		"\u00dd\u00de\u0005\t\u0000\u0000\u00de\u00df\u0003>\u001f\u0000\u00df"+
		"\u00e0\u0005\u0004\u0000\u0000\u00e0\u00e1\u0003@ \u0000\u00e1-\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e3\u0005\u0010\u0000\u0000\u00e3\u00e4\u0003"+
		"8\u001c\u0000\u00e4\u00e5\u0005\t\u0000\u0000\u00e5\u00e6\u0003>\u001f"+
		"\u0000\u00e6\u00e7\u0005\u000b\u0000\u0000\u00e7\u00e8\u0003:\u001d\u0000"+
		"\u00e8\u00e9\u0005\u0011\u0000\u0000\u00e9\u00ea\u0003@ \u0000\u00ea/"+
		"\u0001\u0000\u0000\u0000\u00eb\u00ec\u0005\u0012\u0000\u0000\u00ec\u00ed"+
		"\u00038\u001c\u0000\u00ed\u00ee\u0005\t\u0000\u0000\u00ee\u00f6\u0003"+
		">\u001f\u0000\u00ef\u00f0\u0005\u0013\u0000\u0000\u00f0\u00f1\u00038\u001c"+
		"\u0000\u00f1\u00f2\u0005\t\u0000\u0000\u00f2\u00f3\u0003>\u001f\u0000"+
		"\u00f3\u00f5\u0001\u0000\u0000\u0000\u00f4\u00ef\u0001\u0000\u0000\u0000"+
		"\u00f5\u00f8\u0001\u0000\u0000\u0000\u00f6\u00f4\u0001\u0000\u0000\u0000"+
		"\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7\u00f9\u0001\u0000\u0000\u0000"+
		"\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005\u0004\u0000\u0000"+
		"\u00fa\u00fb\u0003@ \u0000\u00fb1\u0001\u0000\u0000\u0000\u00fc\u00fd"+
		"\u0005\u0014\u0000\u0000\u00fd\u00fe\u00038\u001c\u0000\u00fe\u00ff\u0005"+
		"\u0004\u0000\u0000\u00ff\u0107\u0003>\u001f\u0000\u0100\u0101\u0005\u0013"+
		"\u0000\u0000\u0101\u0102\u00038\u001c\u0000\u0102\u0103\u0005\u0004\u0000"+
		"\u0000\u0103\u0104\u0003>\u001f\u0000\u0104\u0106\u0001\u0000\u0000\u0000"+
		"\u0105\u0100\u0001\u0000\u0000\u0000\u0106\u0109\u0001\u0000\u0000\u0000"+
		"\u0107\u0105\u0001\u0000\u0000\u0000\u0107\u0108\u0001\u0000\u0000\u0000"+
		"\u0108\u010a\u0001\u0000\u0000\u0000\u0109\u0107\u0001\u0000\u0000\u0000"+
		"\u010a\u010b\u0005\t\u0000\u0000\u010b\u010c\u0003@ \u0000\u010c3\u0001"+
		"\u0000\u0000\u0000\u010d\u010e\u0005\'\u0000\u0000\u010e\u010f\u0003>"+
		"\u001f\u0000\u010f5\u0001\u0000\u0000\u0000\u0110\u0111\u0005(\u0000\u0000"+
		"\u0111\u0112\u0003:\u001d\u0000\u0112\u0113\u0005\u0011\u0000\u0000\u0113"+
		"\u0114\u0003@ \u0000\u01147\u0001\u0000\u0000\u0000\u0115\u0116\u0003"+
		"\u000e\u0007\u0000\u0116\u0117\u0003:\u001d\u0000\u01179\u0001\u0000\u0000"+
		"\u0000\u0118\u0119\u0007\u0006\u0000\u0000\u0119;\u0001\u0000\u0000\u0000"+
		"\u011a\u011b\u0003:\u001d\u0000\u011b\u011c\u0005\u0019\u0000\u0000\u011c"+
		"\u011d\u0003:\u001d\u0000\u011d=\u0001\u0000\u0000\u0000\u011e\u011f\u0005"+
		"+\u0000\u0000\u011f\u0120\u0005\u0017\u0000\u0000\u0120\u0121\u0005A\u0000"+
		"\u0000\u0121\u0122\u0005\u0018\u0000\u0000\u0122?\u0001\u0000\u0000\u0000"+
		"\u0123\u0124\u0007\u0007\u0000\u0000\u0124A\u0001\u0000\u0000\u0000\u0011"+
		"EQX]bfy\u0081\u008a\u0090\u0099\u009d\u00af\u00b4\u00bf\u00f6\u0107";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}