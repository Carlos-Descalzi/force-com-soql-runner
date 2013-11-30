// Generated from /home/carlos/Proyectos/force-com-soql-runner/SOQL.g4 by ANTLR 4.1
package soql.parser;
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
		"<INVALID>", "'select'", "'in'", "','", "'.'", "'and'", "'or'", "'not'", 
		"'from'", "'where'", "'order'", "'by'", "ID", "STRING", "'('", "')'", 
		"BOOLEAN", "NUMERIC", "OPERATOR", "WS"
	};
	public static final int
		RULE_query = 0, RULE_fields = 1, RULE_field = 2, RULE_obj = 3, RULE_conditions = 4, 
		RULE_boolExp = 5, RULE_condition = 6, RULE_simpleCondition = 7, RULE_value = 8, 
		RULE_literal = 9;
	public static final String[] ruleNames = {
		"query", "fields", "field", "obj", "conditions", "boolExp", "condition", 
		"simpleCondition", "value", "literal"
	};

	@Override
	public String getGrammarFileName() { return "SOQL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

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
			setState(20); match(SELECT);
			setState(21); fields();
			setState(22); match(FROM);
			setState(23); obj();
			setState(26);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(24); match(WHERE);
				setState(25); conditions();
				}
			}

			setState(31);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(28); match(ORDER);
				setState(29); match(BY);
				setState(30); fields();
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
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SOQLParser.COMMA); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(SOQLParser.COMMA, i);
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
			setState(33); field();
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(34); match(COMMA);
				setState(35); field();
				}
				}
				setState(40);
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
		enterRule(_localctx, 4, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); match(ID);
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(42); match(DOT);
				setState(43); match(ID);
				}
				}
				setState(48);
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
		enterRule(_localctx, 6, RULE_obj);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); match(ID);
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
		enterRule(_localctx, 8, RULE_conditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51); condition();
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND || _la==OR) {
				{
				{
				setState(52); boolExp();
				setState(53); condition();
				}
				}
				setState(59);
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
		enterRule(_localctx, 10, RULE_boolExp);
		int _la;
		try {
			setState(65);
			switch (_input.LA(1)) {
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(60); match(OR);
				}
				break;
			case AND:
				enterOuterAlt(_localctx, 2);
				{
				setState(61); match(AND);
				setState(63);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(62); match(NOT);
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
		enterRule(_localctx, 12, RULE_condition);
		try {
			setState(72);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(67); simpleCondition();
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(68); match(LPAR);
				setState(69); simpleCondition();
				setState(70); match(RPAR);
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
		enterRule(_localctx, 14, RULE_simpleCondition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74); field();
			setState(89);
			switch (_input.LA(1)) {
			case OPERATOR:
				{
				setState(75); match(OPERATOR);
				setState(76); value();
				}
				break;
			case IN:
				{
				setState(77); match(IN);
				setState(78); match(LPAR);
				setState(79); value();
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(80); match(COMMA);
					setState(81); value();
					}
					}
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(87); match(RPAR);
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
		enterRule(_localctx, 16, RULE_value);
		try {
			setState(93);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(91); field();
				}
				break;
			case STRING:
			case BOOLEAN:
			case NUMERIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(92); literal();
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
		enterRule(_localctx, 18, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\25d\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\5\2\35\n\2\3\2\3\2\3\2\5\2\"\n\2\3\3\3\3\3\3\7"+
		"\3\'\n\3\f\3\16\3*\13\3\3\4\3\4\3\4\7\4/\n\4\f\4\16\4\62\13\4\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\7\6:\n\6\f\6\16\6=\13\6\3\7\3\7\3\7\5\7B\n\7\5\7D\n\7"+
		"\3\b\3\b\3\b\3\b\3\b\5\bK\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\tU\n\t"+
		"\f\t\16\tX\13\t\3\t\3\t\5\t\\\n\t\3\n\3\n\5\n`\n\n\3\13\3\13\3\13\2\f"+
		"\2\4\6\b\n\f\16\20\22\24\2\3\4\2\17\17\22\23d\2\26\3\2\2\2\4#\3\2\2\2"+
		"\6+\3\2\2\2\b\63\3\2\2\2\n\65\3\2\2\2\fC\3\2\2\2\16J\3\2\2\2\20L\3\2\2"+
		"\2\22_\3\2\2\2\24a\3\2\2\2\26\27\7\3\2\2\27\30\5\4\3\2\30\31\7\n\2\2\31"+
		"\34\5\b\5\2\32\33\7\13\2\2\33\35\5\n\6\2\34\32\3\2\2\2\34\35\3\2\2\2\35"+
		"!\3\2\2\2\36\37\7\f\2\2\37 \7\r\2\2 \"\5\4\3\2!\36\3\2\2\2!\"\3\2\2\2"+
		"\"\3\3\2\2\2#(\5\6\4\2$%\7\5\2\2%\'\5\6\4\2&$\3\2\2\2\'*\3\2\2\2(&\3\2"+
		"\2\2()\3\2\2\2)\5\3\2\2\2*(\3\2\2\2+\60\7\16\2\2,-\7\6\2\2-/\7\16\2\2"+
		".,\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\7\3\2\2\2\62\60\3"+
		"\2\2\2\63\64\7\16\2\2\64\t\3\2\2\2\65;\5\16\b\2\66\67\5\f\7\2\678\5\16"+
		"\b\28:\3\2\2\29\66\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\13\3\2\2\2="+
		";\3\2\2\2>D\7\b\2\2?A\7\7\2\2@B\7\t\2\2A@\3\2\2\2AB\3\2\2\2BD\3\2\2\2"+
		"C>\3\2\2\2C?\3\2\2\2D\r\3\2\2\2EK\5\20\t\2FG\7\20\2\2GH\5\20\t\2HI\7\21"+
		"\2\2IK\3\2\2\2JE\3\2\2\2JF\3\2\2\2K\17\3\2\2\2L[\5\6\4\2MN\7\24\2\2N\\"+
		"\5\22\n\2OP\7\4\2\2PQ\7\20\2\2QV\5\22\n\2RS\7\5\2\2SU\5\22\n\2TR\3\2\2"+
		"\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2XV\3\2\2\2YZ\7\21\2\2Z\\\3\2"+
		"\2\2[M\3\2\2\2[O\3\2\2\2\\\21\3\2\2\2]`\5\6\4\2^`\5\24\13\2_]\3\2\2\2"+
		"_^\3\2\2\2`\23\3\2\2\2ab\t\2\2\2b\25\3\2\2\2\r\34!(\60;ACJV[_";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}