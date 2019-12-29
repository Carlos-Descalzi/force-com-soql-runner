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
		BY=11, HAVING=12, GROUP=13, ASC=14, DESC=15, LIMIT=16, ID=17, ALIAS=18, 
		STRING=19, LPAR=20, RPAR=21, BOOLEAN=22, NUMERIC=23, INTEGER=24, OPERATOR=25, 
		WS=26;
	public static final String[] tokenNames = {
		"<INVALID>", "SELECT", "IN", "','", "'.'", "AND", "OR", "NOT", "FROM", 
		"WHERE", "ORDER", "BY", "HAVING", "GROUP", "ASC", "DESC", "LIMIT", "ID", 
		"ALIAS", "STRING", "'('", "')'", "BOOLEAN", "NUMERIC", "INTEGER", "OPERATOR", 
		"WS"
	};
	public static final int
		RULE_query = 0, RULE_terms = 1, RULE_fields = 2, RULE_ordering = 3, RULE_orderingTerm = 4, 
		RULE_term = 5, RULE_fieldTerm = 6, RULE_funcRef = 7, RULE_field = 8, RULE_subquery = 9, 
		RULE_obj = 10, RULE_conditions = 11, RULE_boolExp = 12, RULE_condition = 13, 
		RULE_simpleCondition = 14, RULE_value = 15, RULE_literal = 16, RULE_nRows = 17;
	public static final String[] ruleNames = {
		"query", "terms", "fields", "ordering", "orderingTerm", "term", "fieldTerm", 
		"funcRef", "field", "subquery", "obj", "conditions", "boolExp", "condition", 
		"simpleCondition", "value", "literal", "nRows"
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
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public TerminalNode LIMIT() { return getToken(SOQLParser.LIMIT, 0); }
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public TerminalNode FROM() { return getToken(SOQLParser.FROM, 0); }
		public OrderingContext ordering() {
			return getRuleContext(OrderingContext.class,0);
		}
		public TerminalNode GROUP() { return getToken(SOQLParser.GROUP, 0); }
		public ConditionsContext conditions(int i) {
			return getRuleContext(ConditionsContext.class,i);
		}
		public List<ConditionsContext> conditions() {
			return getRuleContexts(ConditionsContext.class);
		}
		public NRowsContext nRows() {
			return getRuleContext(NRowsContext.class,0);
		}
		public TerminalNode ORDER() { return getToken(SOQLParser.ORDER, 0); }
		public TerminalNode WHERE() { return getToken(SOQLParser.WHERE, 0); }
		public FieldsContext fields() {
			return getRuleContext(FieldsContext.class,0);
		}
		public TerminalNode SELECT() { return getToken(SOQLParser.SELECT, 0); }
		public TerminalNode HAVING() { return getToken(SOQLParser.HAVING, 0); }
		public List<TerminalNode> BY() { return getTokens(SOQLParser.BY); }
		public TerminalNode BY(int i) {
			return getToken(SOQLParser.BY, i);
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
			setState(36); match(SELECT);
			setState(37); terms();
			setState(38); match(FROM);
			setState(39); obj();
			setState(42);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(40); match(WHERE);
				setState(41); conditions();
				}
			}

			setState(47);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(44); match(GROUP);
				setState(45); match(BY);
				setState(46); fields();
				}
			}

			setState(51);
			_la = _input.LA(1);
			if (_la==HAVING) {
				{
				setState(49); match(HAVING);
				setState(50); conditions();
				}
			}

			setState(56);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(53); match(ORDER);
				setState(54); match(BY);
				setState(55); ordering();
				}
			}

			setState(60);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(58); match(LIMIT);
				setState(59); nRows();
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

	public static class TermsContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SOQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SOQLParser.COMMA, i);
		}
		public TermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterTerms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitTerms(this);
		}
	}

	public final TermsContext terms() throws RecognitionException {
		TermsContext _localctx = new TermsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_terms);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); term();
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(63); match(COMMA);
				setState(64); term();
				}
				}
				setState(69);
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

	public static class FieldsContext extends ParserRuleContext {
		public List<TerminalNode> COMMA() { return getTokens(SOQLParser.COMMA); }
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(SOQLParser.COMMA, i);
		}
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
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
		enterRule(_localctx, 4, RULE_fields);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); field();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(71); match(COMMA);
				setState(72); field();
				}
				}
				setState(77);
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

	public static class OrderingContext extends ParserRuleContext {
		public List<OrderingTermContext> orderingTerm() {
			return getRuleContexts(OrderingTermContext.class);
		}
		public TerminalNode COMMA() { return getToken(SOQLParser.COMMA, 0); }
		public OrderingTermContext orderingTerm(int i) {
			return getRuleContext(OrderingTermContext.class,i);
		}
		public OrderingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ordering; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterOrdering(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitOrdering(this);
		}
	}

	public final OrderingContext ordering() throws RecognitionException {
		OrderingContext _localctx = new OrderingContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ordering);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78); orderingTerm();
			{
			setState(79); match(COMMA);
			setState(80); orderingTerm();
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

	public static class OrderingTermContext extends ParserRuleContext {
		public TerminalNode ASC() { return getToken(SOQLParser.ASC, 0); }
		public TerminalNode DESC() { return getToken(SOQLParser.DESC, 0); }
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public OrderingTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderingTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterOrderingTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitOrderingTerm(this);
		}
	}

	public final OrderingTermContext orderingTerm() throws RecognitionException {
		OrderingTermContext _localctx = new OrderingTermContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_orderingTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); field();
			setState(83);
			_la = _input.LA(1);
			if ( !(_la==ASC || _la==DESC) ) {
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

	public static class TermContext extends ParserRuleContext {
		public SubqueryContext subquery() {
			return getRuleContext(SubqueryContext.class,0);
		}
		public FieldTermContext fieldTerm() {
			return getRuleContext(FieldTermContext.class,0);
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
		enterRule(_localctx, 10, RULE_term);
		try {
			setState(87);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(85); fieldTerm();
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(86); subquery();
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

	public static class FieldTermContext extends ParserRuleContext {
		public FuncRefContext funcRef() {
			return getRuleContext(FuncRefContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public FieldTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterFieldTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitFieldTerm(this);
		}
	}

	public final FieldTermContext fieldTerm() throws RecognitionException {
		FieldTermContext _localctx = new FieldTermContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fieldTerm);
		int _la;
		try {
			setState(94);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(89); field();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(90); funcRef();
				setState(92);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(91); field();
					}
				}

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

	public static class FuncRefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SOQLParser.ID, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(SOQLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SOQLParser.RPAR, 0); }
		public FuncRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterFuncRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitFuncRef(this);
		}
	}

	public final FuncRefContext funcRef() throws RecognitionException {
		FuncRefContext _localctx = new FuncRefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(ID);
			setState(97); match(LPAR);
			setState(98); term();
			setState(99); match(RPAR);
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
		public List<TerminalNode> ID() { return getTokens(SOQLParser.ID); }
		public List<TerminalNode> DOT() { return getTokens(SOQLParser.DOT); }
		public TerminalNode ID(int i) {
			return getToken(SOQLParser.ID, i);
		}
		public TerminalNode DOT(int i) {
			return getToken(SOQLParser.DOT, i);
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
		enterRule(_localctx, 16, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); match(ID);
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(102); match(DOT);
				setState(103); match(ID);
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

	public static class SubqueryContext extends ParserRuleContext {
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(SOQLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SOQLParser.RPAR, 0); }
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
		enterRule(_localctx, 18, RULE_subquery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109); match(LPAR);
			setState(110); query();
			setState(111); match(RPAR);
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
		enterRule(_localctx, 20, RULE_obj);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113); match(ID);
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
		public List<BoolExpContext> boolExp() {
			return getRuleContexts(BoolExpContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public BoolExpContext boolExp(int i) {
			return getRuleContext(BoolExpContext.class,i);
		}
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
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
		enterRule(_localctx, 22, RULE_conditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); condition();
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND || _la==OR) {
				{
				{
				setState(116); boolExp();
				setState(117); condition();
				}
				}
				setState(123);
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
		public TerminalNode NOT() { return getToken(SOQLParser.NOT, 0); }
		public TerminalNode AND() { return getToken(SOQLParser.AND, 0); }
		public TerminalNode OR() { return getToken(SOQLParser.OR, 0); }
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
		enterRule(_localctx, 24, RULE_boolExp);
		int _la;
		try {
			setState(129);
			switch (_input.LA(1)) {
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(124); match(OR);
				}
				break;
			case AND:
				enterOuterAlt(_localctx, 2);
				{
				setState(125); match(AND);
				setState(127);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(126); match(NOT);
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
		public TerminalNode LPAR() { return getToken(SOQLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SOQLParser.RPAR, 0); }
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
		enterRule(_localctx, 26, RULE_condition);
		try {
			setState(136);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(131); simpleCondition();
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(132); match(LPAR);
				setState(133); simpleCondition();
				setState(134); match(RPAR);
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
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SOQLParser.COMMA); }
		public TerminalNode LPAR() { return getToken(SOQLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SOQLParser.RPAR, 0); }
		public TerminalNode OPERATOR() { return getToken(SOQLParser.OPERATOR, 0); }
		public TerminalNode IN() { return getToken(SOQLParser.IN, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(SOQLParser.COMMA, i);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
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
		enterRule(_localctx, 28, RULE_simpleCondition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138); field();
			setState(153);
			switch (_input.LA(1)) {
			case OPERATOR:
				{
				setState(139); match(OPERATOR);
				setState(140); value();
				}
				break;
			case IN:
				{
				setState(141); match(IN);
				setState(142); match(LPAR);
				setState(143); value();
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(144); match(COMMA);
					setState(145); value();
					}
					}
					setState(150);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(151); match(RPAR);
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
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
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
		enterRule(_localctx, 30, RULE_value);
		try {
			setState(157);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(155); field();
				}
				break;
			case STRING:
			case BOOLEAN:
			case NUMERIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(156); literal();
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
		public TerminalNode STRING() { return getToken(SOQLParser.STRING, 0); }
		public TerminalNode BOOLEAN() { return getToken(SOQLParser.BOOLEAN, 0); }
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
		enterRule(_localctx, 32, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
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

	public static class NRowsContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(SOQLParser.INTEGER, 0); }
		public NRowsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nRows; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).enterNRows(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SOQLListener ) ((SOQLListener)listener).exitNRows(this);
		}
	}

	public final NRowsContext nRows() throws RecognitionException {
		NRowsContext _localctx = new NRowsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_nRows);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161); match(INTEGER);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\34\u00a6\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\5\2-\n\2\3\2\3\2\3\2\5\2\62\n\2\3\2"+
		"\3\2\5\2\66\n\2\3\2\3\2\3\2\5\2;\n\2\3\2\3\2\5\2?\n\2\3\3\3\3\3\3\7\3"+
		"D\n\3\f\3\16\3G\13\3\3\4\3\4\3\4\7\4L\n\4\f\4\16\4O\13\4\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\3\7\3\7\5\7Z\n\7\3\b\3\b\3\b\5\b_\n\b\5\ba\n\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\7\nk\n\n\f\n\16\nn\13\n\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\7\rz\n\r\f\r\16\r}\13\r\3\16\3\16\3\16\5\16\u0082"+
		"\n\16\5\16\u0084\n\16\3\17\3\17\3\17\3\17\3\17\5\17\u008b\n\17\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0095\n\20\f\20\16\20\u0098\13"+
		"\20\3\20\3\20\5\20\u009c\n\20\3\21\3\21\5\21\u00a0\n\21\3\22\3\22\3\23"+
		"\3\23\3\23\2\2\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\4\3\2\20"+
		"\21\4\2\25\25\30\31\u00a5\2&\3\2\2\2\4@\3\2\2\2\6H\3\2\2\2\bP\3\2\2\2"+
		"\nT\3\2\2\2\fY\3\2\2\2\16`\3\2\2\2\20b\3\2\2\2\22g\3\2\2\2\24o\3\2\2\2"+
		"\26s\3\2\2\2\30u\3\2\2\2\32\u0083\3\2\2\2\34\u008a\3\2\2\2\36\u008c\3"+
		"\2\2\2 \u009f\3\2\2\2\"\u00a1\3\2\2\2$\u00a3\3\2\2\2&\'\7\3\2\2\'(\5\4"+
		"\3\2()\7\n\2\2),\5\26\f\2*+\7\13\2\2+-\5\30\r\2,*\3\2\2\2,-\3\2\2\2-\61"+
		"\3\2\2\2./\7\17\2\2/\60\7\r\2\2\60\62\5\6\4\2\61.\3\2\2\2\61\62\3\2\2"+
		"\2\62\65\3\2\2\2\63\64\7\16\2\2\64\66\5\30\r\2\65\63\3\2\2\2\65\66\3\2"+
		"\2\2\66:\3\2\2\2\678\7\f\2\289\7\r\2\29;\5\b\5\2:\67\3\2\2\2:;\3\2\2\2"+
		";>\3\2\2\2<=\7\22\2\2=?\5$\23\2><\3\2\2\2>?\3\2\2\2?\3\3\2\2\2@E\5\f\7"+
		"\2AB\7\5\2\2BD\5\f\7\2CA\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\5\3\2"+
		"\2\2GE\3\2\2\2HM\5\22\n\2IJ\7\5\2\2JL\5\22\n\2KI\3\2\2\2LO\3\2\2\2MK\3"+
		"\2\2\2MN\3\2\2\2N\7\3\2\2\2OM\3\2\2\2PQ\5\n\6\2QR\7\5\2\2RS\5\n\6\2S\t"+
		"\3\2\2\2TU\5\22\n\2UV\t\2\2\2V\13\3\2\2\2WZ\5\16\b\2XZ\5\24\13\2YW\3\2"+
		"\2\2YX\3\2\2\2Z\r\3\2\2\2[a\5\22\n\2\\^\5\20\t\2]_\5\22\n\2^]\3\2\2\2"+
		"^_\3\2\2\2_a\3\2\2\2`[\3\2\2\2`\\\3\2\2\2a\17\3\2\2\2bc\7\23\2\2cd\7\26"+
		"\2\2de\5\f\7\2ef\7\27\2\2f\21\3\2\2\2gl\7\23\2\2hi\7\6\2\2ik\7\23\2\2"+
		"jh\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\23\3\2\2\2nl\3\2\2\2op\7\26"+
		"\2\2pq\5\2\2\2qr\7\27\2\2r\25\3\2\2\2st\7\23\2\2t\27\3\2\2\2u{\5\34\17"+
		"\2vw\5\32\16\2wx\5\34\17\2xz\3\2\2\2yv\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3"+
		"\2\2\2|\31\3\2\2\2}{\3\2\2\2~\u0084\7\b\2\2\177\u0081\7\7\2\2\u0080\u0082"+
		"\7\t\2\2\u0081\u0080\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083"+
		"~\3\2\2\2\u0083\177\3\2\2\2\u0084\33\3\2\2\2\u0085\u008b\5\36\20\2\u0086"+
		"\u0087\7\26\2\2\u0087\u0088\5\36\20\2\u0088\u0089\7\27\2\2\u0089\u008b"+
		"\3\2\2\2\u008a\u0085\3\2\2\2\u008a\u0086\3\2\2\2\u008b\35\3\2\2\2\u008c"+
		"\u009b\5\22\n\2\u008d\u008e\7\33\2\2\u008e\u009c\5 \21\2\u008f\u0090\7"+
		"\4\2\2\u0090\u0091\7\26\2\2\u0091\u0096\5 \21\2\u0092\u0093\7\5\2\2\u0093"+
		"\u0095\5 \21\2\u0094\u0092\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0096\u0097\3\2\2\2\u0097\u0099\3\2\2\2\u0098\u0096\3\2\2\2\u0099"+
		"\u009a\7\27\2\2\u009a\u009c\3\2\2\2\u009b\u008d\3\2\2\2\u009b\u008f\3"+
		"\2\2\2\u009c\37\3\2\2\2\u009d\u00a0\5\22\n\2\u009e\u00a0\5\"\22\2\u009f"+
		"\u009d\3\2\2\2\u009f\u009e\3\2\2\2\u00a0!\3\2\2\2\u00a1\u00a2\t\3\2\2"+
		"\u00a2#\3\2\2\2\u00a3\u00a4\7\32\2\2\u00a4%\3\2\2\2\24,\61\65:>EMY^`l"+
		"{\u0081\u0083\u008a\u0096\u009b\u009f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}