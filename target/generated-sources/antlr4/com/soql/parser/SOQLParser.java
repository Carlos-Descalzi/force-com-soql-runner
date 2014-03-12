// Generated from com/soql/parser/SOQL.g4 by ANTLR 4.2
package com.soql.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SOQLParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SELECT=1, IN=2, COMMA=3, DOT=4, AND=5, OR=6, NOT=7, FROM=8, WHERE=9, ORDER=10, 
		BY=11, ID=12, STRING=13, LPAR=14, RPAR=15, BOOLEAN=16, NUMERIC=17, OPERATOR=18, 
		WS=19;
	public static final String[] tokenNames = {
		"<INVALID>", "SELECT", "IN", "','", "'.'", "AND", "OR", "NOT", "FROM", 
		"WHERE", "ORDER", "BY", "ID", "STRING", "'('", "')'", "BOOLEAN", "NUMERIC", 
		"OPERATOR", "WS"
	};
	public static final int
		RULE_query = 0, RULE_fields = 1, RULE_term = 2, RULE_field = 3, RULE_subquery = 4, 
		RULE_obj = 5, RULE_conditions = 6, RULE_boolExp = 7, RULE_condition = 8, 
		RULE_simpleCondition = 9, RULE_value = 10, RULE_literal = 11;
	public static final String[] ruleNames = {
		"query", "fields", "term", "field", "subquery", "obj", "conditions", "boolExp", 
		"condition", "simpleCondition", "value", "literal"
	};

	@Override
	public String getGrammarFileName() { return "SOQL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SOQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(SOQLParser.WHERE, 0); }
		public List<FieldsContext> fields() {
			return getRuleContexts(FieldsContext.class);
		}
		public ConditionsContext conditions() {
			return getRuleContext(ConditionsContext.class,0);
		}
		public TerminalNode ORDER() { return getToken(SOQLParser.ORDER, 0); }
		public FieldsContext fields(int i) {
			return getRuleContext(FieldsContext.class,i);
		}
		public TerminalNode BY() { return getToken(SOQLParser.BY, 0); }
		public TerminalNode SELECT() { return getToken(SOQLParser.SELECT, 0); }
		public TerminalNode FROM() { return getToken(SOQLParser.FROM, 0); }
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24); match(SELECT);
			setState(25); fields();
			setState(26); match(FROM);
			setState(27); obj();
			setState(30);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(28); match(WHERE);
				setState(29); conditions();
				}
			}

			setState(35);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(32); match(ORDER);
				setState(33); match(BY);
				setState(34); fields();
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

	public static class FieldsContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(SOQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SOQLParser.COMMA, i);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public FieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterFields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitFields(this);
		}
	}

	public final FieldsContext fields() throws RecognitionException {
		FieldsContext _localctx = new FieldsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_fields);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); term();
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(38); match(COMMA);
				setState(39); term();
				}
				}
				setState(44);
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

	public static class TermContext extends ParserRuleContext {
		public SubqueryContext subquery() {
			return getRuleContext(SubqueryContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_term);
		try {
			setState(47);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(45); field();
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(46); subquery();
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

	public static class FieldContext extends ParserRuleContext {
		public List<TerminalNode> DOT() { return getTokens(SOQLParser.DOT); }
		public List<TerminalNode> ID() { return getTokens(SOQLParser.ID); }
		public TerminalNode DOT(int i) {
			return getToken(SOQLParser.DOT, i);
		}
		public TerminalNode ID(int i) {
			return getToken(SOQLParser.ID, i);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); match(ID);
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(50); match(DOT);
				setState(51); match(ID);
				}
				}
				setState(56);
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

	public static class SubqueryContext extends ParserRuleContext {
		public TerminalNode RPAR() { return getToken(SOQLParser.RPAR, 0); }
		public TerminalNode LPAR() { return getToken(SOQLParser.LPAR, 0); }
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public SubqueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subquery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterSubquery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitSubquery(this);
		}
	}

	public final SubqueryContext subquery() throws RecognitionException {
		SubqueryContext _localctx = new SubqueryContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_subquery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57); match(LPAR);
			setState(58); query();
			setState(59); match(RPAR);
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

	public static class ObjContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SOQLParser.ID, 0); }
		public ObjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obj; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterObj(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitObj(this);
		}
	}

	public final ObjContext obj() throws RecognitionException {
		ObjContext _localctx = new ObjContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_obj);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); match(ID);
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

	public static class ConditionsContext extends ParserRuleContext {
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public BoolExpContext boolExp(int i) {
			return getRuleContext(BoolExpContext.class,i);
		}
		public List<BoolExpContext> boolExp() {
			return getRuleContexts(BoolExpContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public ConditionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterConditions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitConditions(this);
		}
	}

	public final ConditionsContext conditions() throws RecognitionException {
		ConditionsContext _localctx = new ConditionsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_conditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63); condition();
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND || _la==OR) {
				{
				{
				setState(64); boolExp();
				setState(65); condition();
				}
				}
				setState(71);
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

	public static class BoolExpContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(SOQLParser.AND, 0); }
		public TerminalNode OR() { return getToken(SOQLParser.OR, 0); }
		public TerminalNode NOT() { return getToken(SOQLParser.NOT, 0); }
		public BoolExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterBoolExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitBoolExp(this);
		}
	}

	public final BoolExpContext boolExp() throws RecognitionException {
		BoolExpContext _localctx = new BoolExpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_boolExp);
		int _la;
		try {
			setState(77);
			switch (_input.LA(1)) {
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(72); match(OR);
				}
				break;
			case AND:
				enterOuterAlt(_localctx, 2);
				{
				setState(73); match(AND);
				setState(75);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(74); match(NOT);
					}
				}

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

	public static class ConditionContext extends ParserRuleContext {
		public SimpleConditionContext simpleCondition() {
			return getRuleContext(SimpleConditionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(SOQLParser.RPAR, 0); }
		public TerminalNode LPAR() { return getToken(SOQLParser.LPAR, 0); }
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_condition);
		try {
			setState(84);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(79); simpleCondition();
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(80); match(LPAR);
				setState(81); simpleCondition();
				setState(82); match(RPAR);
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

	public static class SimpleConditionContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(SOQLParser.IN, 0); }
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode RPAR() { return getToken(SOQLParser.RPAR, 0); }
		public TerminalNode LPAR() { return getToken(SOQLParser.LPAR, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(SOQLParser.COMMA); }
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public TerminalNode COMMA(int i) {
			return getToken(SOQLParser.COMMA, i);
		}
		public TerminalNode OPERATOR() { return getToken(SOQLParser.OPERATOR, 0); }
		public SimpleConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterSimpleCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitSimpleCondition(this);
		}
	}

	public final SimpleConditionContext simpleCondition() throws RecognitionException {
		SimpleConditionContext _localctx = new SimpleConditionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_simpleCondition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); field();
			setState(101);
			switch (_input.LA(1)) {
			case OPERATOR:
				{
				setState(87); match(OPERATOR);
				setState(88); value();
				}
				break;
			case IN:
				{
				setState(89); match(IN);
				setState(90); match(LPAR);
				setState(91); value();
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(92); match(COMMA);
					setState(93); value();
					}
					}
					setState(98);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(99); match(RPAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ValueContext extends ParserRuleContext {
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_value);
		try {
			setState(105);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(103); field();
				}
				break;
			case STRING:
			case BOOLEAN:
			case NUMERIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(104); literal();
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

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NUMERIC() { return getToken(SOQLParser.NUMERIC, 0); }
		public TerminalNode BOOLEAN() { return getToken(SOQLParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(SOQLParser.STRING, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << BOOLEAN) | (1L << NUMERIC))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\25p\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\5\2!\n\2\3\2\3\2\3\2\5\2&\n\2\3"+
		"\3\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\4\3\4\5\4\62\n\4\3\5\3\5\3\5\7\5"+
		"\67\n\5\f\5\16\5:\13\5\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\7\bF\n"+
		"\b\f\b\16\bI\13\b\3\t\3\t\3\t\5\tN\n\t\5\tP\n\t\3\n\3\n\3\n\3\n\3\n\5"+
		"\nW\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13a\n\13\f\13\16\13"+
		"d\13\13\3\13\3\13\5\13h\n\13\3\f\3\f\5\fl\n\f\3\r\3\r\3\r\2\2\16\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\2\3\4\2\17\17\22\23o\2\32\3\2\2\2\4\'\3\2\2\2"+
		"\6\61\3\2\2\2\b\63\3\2\2\2\n;\3\2\2\2\f?\3\2\2\2\16A\3\2\2\2\20O\3\2\2"+
		"\2\22V\3\2\2\2\24X\3\2\2\2\26k\3\2\2\2\30m\3\2\2\2\32\33\7\3\2\2\33\34"+
		"\5\4\3\2\34\35\7\n\2\2\35 \5\f\7\2\36\37\7\13\2\2\37!\5\16\b\2 \36\3\2"+
		"\2\2 !\3\2\2\2!%\3\2\2\2\"#\7\f\2\2#$\7\r\2\2$&\5\4\3\2%\"\3\2\2\2%&\3"+
		"\2\2\2&\3\3\2\2\2\',\5\6\4\2()\7\5\2\2)+\5\6\4\2*(\3\2\2\2+.\3\2\2\2,"+
		"*\3\2\2\2,-\3\2\2\2-\5\3\2\2\2.,\3\2\2\2/\62\5\b\5\2\60\62\5\n\6\2\61"+
		"/\3\2\2\2\61\60\3\2\2\2\62\7\3\2\2\2\638\7\16\2\2\64\65\7\6\2\2\65\67"+
		"\7\16\2\2\66\64\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29\t\3\2\2\2:"+
		"8\3\2\2\2;<\7\20\2\2<=\5\2\2\2=>\7\21\2\2>\13\3\2\2\2?@\7\16\2\2@\r\3"+
		"\2\2\2AG\5\22\n\2BC\5\20\t\2CD\5\22\n\2DF\3\2\2\2EB\3\2\2\2FI\3\2\2\2"+
		"GE\3\2\2\2GH\3\2\2\2H\17\3\2\2\2IG\3\2\2\2JP\7\b\2\2KM\7\7\2\2LN\7\t\2"+
		"\2ML\3\2\2\2MN\3\2\2\2NP\3\2\2\2OJ\3\2\2\2OK\3\2\2\2P\21\3\2\2\2QW\5\24"+
		"\13\2RS\7\20\2\2ST\5\24\13\2TU\7\21\2\2UW\3\2\2\2VQ\3\2\2\2VR\3\2\2\2"+
		"W\23\3\2\2\2Xg\5\b\5\2YZ\7\24\2\2Zh\5\26\f\2[\\\7\4\2\2\\]\7\20\2\2]b"+
		"\5\26\f\2^_\7\5\2\2_a\5\26\f\2`^\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2"+
		"ce\3\2\2\2db\3\2\2\2ef\7\21\2\2fh\3\2\2\2gY\3\2\2\2g[\3\2\2\2h\25\3\2"+
		"\2\2il\5\b\5\2jl\5\30\r\2ki\3\2\2\2kj\3\2\2\2l\27\3\2\2\2mn\t\2\2\2n\31"+
		"\3\2\2\2\16 %,\618GMOVbgk";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}