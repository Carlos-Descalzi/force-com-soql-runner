// Generated from com/soql/parser/SOQL.g4 by ANTLR 4.2
package com.soql.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SOQLLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SELECT=1, IN=2, COMMA=3, DOT=4, AND=5, OR=6, NOT=7, FROM=8, WHERE=9, ORDER=10, 
		BY=11, ID=12, STRING=13, LPAR=14, RPAR=15, BOOLEAN=16, NUMERIC=17, OPERATOR=18, 
		WS=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"SELECT", "IN", "','", "'.'", "AND", "OR", "NOT", "FROM", "WHERE", "ORDER", 
		"BY", "ID", "STRING", "'('", "')'", "BOOLEAN", "NUMERIC", "OPERATOR", 
		"WS"
	};
	public static final String[] ruleNames = {
		"SELECT", "IN", "COMMA", "DOT", "AND", "OR", "NOT", "FROM", "WHERE", "ORDER", 
		"BY", "ID", "STRING", "LPAR", "RPAR", "BOOLEAN", "NUMERIC", "OPERATOR", 
		"WS"
	};


	public SOQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SOQL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\25\u0092\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\r\6\rX\n\r\r\r\16\rY\3\16\3\16\7\16^\n\16\f\16\16\16a\13\16\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\5\21r\n\21\3\22\3\22\7\22v\n\22\f\22\16\22y\13\22\3\22\3\22\7\22}\n\22"+
		"\f\22\16\22\u0080\13\22\5\22\u0082\n\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\5\23\u008a\n\23\3\24\6\24\u008d\n\24\r\24\16\24\u008e\3\24\3\24\2\2\25"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25\3\2\27\4\2UUuu\4\2GGgg\4\2NNnn\4\2EEee\4\2VVv"+
		"v\4\2KKkk\4\2PPpp\4\2CCcc\4\2FFff\4\2QQqq\4\2TTtt\4\2HHhh\4\2OOoo\4\2"+
		"YYyy\4\2JJjj\4\2DDdd\4\2[[{{\5\2C\\aac|\4\2))^^\4\2WWww\5\2\13\f\17\17"+
		"\"\"\u009b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2\5\60\3\2\2\2\7\63\3\2"+
		"\2\2\t\65\3\2\2\2\13\67\3\2\2\2\r;\3\2\2\2\17>\3\2\2\2\21B\3\2\2\2\23"+
		"G\3\2\2\2\25M\3\2\2\2\27S\3\2\2\2\31W\3\2\2\2\33[\3\2\2\2\35d\3\2\2\2"+
		"\37f\3\2\2\2!q\3\2\2\2#s\3\2\2\2%\u0089\3\2\2\2\'\u008c\3\2\2\2)*\t\2"+
		"\2\2*+\t\3\2\2+,\t\4\2\2,-\t\3\2\2-.\t\5\2\2./\t\6\2\2/\4\3\2\2\2\60\61"+
		"\t\7\2\2\61\62\t\b\2\2\62\6\3\2\2\2\63\64\7.\2\2\64\b\3\2\2\2\65\66\7"+
		"\60\2\2\66\n\3\2\2\2\678\t\t\2\289\t\b\2\29:\t\n\2\2:\f\3\2\2\2;<\t\13"+
		"\2\2<=\t\f\2\2=\16\3\2\2\2>?\t\b\2\2?@\t\13\2\2@A\t\6\2\2A\20\3\2\2\2"+
		"BC\t\r\2\2CD\t\f\2\2DE\t\13\2\2EF\t\16\2\2F\22\3\2\2\2GH\t\17\2\2HI\t"+
		"\20\2\2IJ\t\3\2\2JK\t\f\2\2KL\t\3\2\2L\24\3\2\2\2MN\t\13\2\2NO\t\f\2\2"+
		"OP\t\n\2\2PQ\t\3\2\2QR\t\f\2\2R\26\3\2\2\2ST\t\21\2\2TU\t\22\2\2U\30\3"+
		"\2\2\2VX\t\23\2\2WV\3\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\32\3\2\2\2"+
		"[_\7)\2\2\\^\n\24\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`b\3\2\2"+
		"\2a_\3\2\2\2bc\7)\2\2c\34\3\2\2\2de\7*\2\2e\36\3\2\2\2fg\7+\2\2g \3\2"+
		"\2\2hi\t\6\2\2ij\t\f\2\2jk\t\25\2\2kr\t\3\2\2lm\t\r\2\2mn\t\t\2\2no\t"+
		"\4\2\2op\t\2\2\2pr\t\3\2\2qh\3\2\2\2ql\3\2\2\2r\"\3\2\2\2sw\4\63;\2tv"+
		"\4\62;\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x\u0081\3\2\2\2yw\3\2"+
		"\2\2z~\7\60\2\2{}\4\62;\2|{\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2"+
		"\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0081z\3\2\2\2\u0081\u0082\3\2\2"+
		"\2\u0082$\3\2\2\2\u0083\u008a\4>?\2\u0084\u0085\7>\2\2\u0085\u008a\7?"+
		"\2\2\u0086\u008a\7@\2\2\u0087\u0088\7@\2\2\u0088\u008a\7?\2\2\u0089\u0083"+
		"\3\2\2\2\u0089\u0084\3\2\2\2\u0089\u0086\3\2\2\2\u0089\u0087\3\2\2\2\u008a"+
		"&\3\2\2\2\u008b\u008d\t\26\2\2\u008c\u008b\3\2\2\2\u008d\u008e\3\2\2\2"+
		"\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091"+
		"\b\24\2\2\u0091(\3\2\2\2\13\2Y_qw~\u0081\u0089\u008e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}