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
		BY=11, HAVING=12, GROUP=13, ASC=14, DESC=15, LIMIT=16, ID=17, ALIAS=18, 
		STRING=19, LPAR=20, RPAR=21, BOOLEAN=22, NUMERIC=23, INTEGER=24, OPERATOR=25, 
		WS=26;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"SELECT", "IN", "','", "'.'", "AND", "OR", "NOT", "FROM", "WHERE", "ORDER", 
		"BY", "HAVING", "GROUP", "ASC", "DESC", "LIMIT", "ID", "ALIAS", "STRING", 
		"'('", "')'", "BOOLEAN", "NUMERIC", "INTEGER", "OPERATOR", "WS"
	};
	public static final String[] ruleNames = {
		"SELECT", "IN", "COMMA", "DOT", "AND", "OR", "NOT", "FROM", "WHERE", "ORDER", 
		"BY", "HAVING", "GROUP", "ASC", "DESC", "LIMIT", "ID", "ALIAS", "STRING", 
		"LPAR", "RPAR", "BOOLEAN", "NUMERIC", "INTEGER", "OPERATOR", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\34\u00c8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\6\22\u0082\n\22\r\22\16\22\u0083\3\23\6\23\u0087\n\23\r\23\16\23\u0088"+
		"\3\24\3\24\7\24\u008d\n\24\f\24\16\24\u0090\13\24\3\24\3\24\3\25\3\25"+
		"\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u00a1\n\27"+
		"\3\30\3\30\7\30\u00a5\n\30\f\30\16\30\u00a8\13\30\3\30\3\30\7\30\u00ac"+
		"\n\30\f\30\16\30\u00af\13\30\5\30\u00b1\n\30\3\31\3\31\7\31\u00b5\n\31"+
		"\f\31\16\31\u00b8\13\31\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u00c0\n\32"+
		"\3\33\6\33\u00c3\n\33\r\33\16\33\u00c4\3\33\3\33\2\2\34\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\3\2\32\4\2UUuu\4\2GGgg\4\2"+
		"NNnn\4\2EEee\4\2VVvv\4\2KKkk\4\2PPpp\4\2CCcc\4\2FFff\4\2QQqq\4\2TTtt\4"+
		"\2HHhh\4\2OOoo\4\2YYyy\4\2JJjj\4\2DDdd\4\2[[{{\4\2XXxx\4\2IIii\4\2WWw"+
		"w\4\2RRrr\5\2C\\aac|\4\2))^^\5\2\13\f\17\17\"\"\u00d3\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\3\67\3\2\2\2\5>\3\2\2\2\7A\3\2\2\2\tC\3\2\2\2"+
		"\13E\3\2\2\2\rI\3\2\2\2\17L\3\2\2\2\21P\3\2\2\2\23U\3\2\2\2\25[\3\2\2"+
		"\2\27a\3\2\2\2\31d\3\2\2\2\33k\3\2\2\2\35q\3\2\2\2\37u\3\2\2\2!z\3\2\2"+
		"\2#\u0081\3\2\2\2%\u0086\3\2\2\2\'\u008a\3\2\2\2)\u0093\3\2\2\2+\u0095"+
		"\3\2\2\2-\u00a0\3\2\2\2/\u00a2\3\2\2\2\61\u00b2\3\2\2\2\63\u00bf\3\2\2"+
		"\2\65\u00c2\3\2\2\2\678\t\2\2\289\t\3\2\29:\t\4\2\2:;\t\3\2\2;<\t\5\2"+
		"\2<=\t\6\2\2=\4\3\2\2\2>?\t\7\2\2?@\t\b\2\2@\6\3\2\2\2AB\7.\2\2B\b\3\2"+
		"\2\2CD\7\60\2\2D\n\3\2\2\2EF\t\t\2\2FG\t\b\2\2GH\t\n\2\2H\f\3\2\2\2IJ"+
		"\t\13\2\2JK\t\f\2\2K\16\3\2\2\2LM\t\b\2\2MN\t\13\2\2NO\t\6\2\2O\20\3\2"+
		"\2\2PQ\t\r\2\2QR\t\f\2\2RS\t\13\2\2ST\t\16\2\2T\22\3\2\2\2UV\t\17\2\2"+
		"VW\t\20\2\2WX\t\3\2\2XY\t\f\2\2YZ\t\3\2\2Z\24\3\2\2\2[\\\t\13\2\2\\]\t"+
		"\f\2\2]^\t\n\2\2^_\t\3\2\2_`\t\f\2\2`\26\3\2\2\2ab\t\21\2\2bc\t\22\2\2"+
		"c\30\3\2\2\2de\t\20\2\2ef\t\t\2\2fg\t\23\2\2gh\t\7\2\2hi\t\b\2\2ij\t\24"+
		"\2\2j\32\3\2\2\2kl\t\24\2\2lm\t\f\2\2mn\t\13\2\2no\t\25\2\2op\t\26\2\2"+
		"p\34\3\2\2\2qr\t\t\2\2rs\t\2\2\2st\t\5\2\2t\36\3\2\2\2uv\t\n\2\2vw\t\3"+
		"\2\2wx\t\2\2\2xy\t\5\2\2y \3\2\2\2z{\t\4\2\2{|\t\7\2\2|}\t\16\2\2}~\t"+
		"\7\2\2~\177\t\6\2\2\177\"\3\2\2\2\u0080\u0082\t\27\2\2\u0081\u0080\3\2"+
		"\2\2\u0082\u0083\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"$\3\2\2\2\u0085\u0087\t\27\2\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2"+
		"\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089&\3\2\2\2\u008a\u008e\7"+
		")\2\2\u008b\u008d\n\30\2\2\u008c\u008b\3\2\2\2\u008d\u0090\3\2\2\2\u008e"+
		"\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0091\3\2\2\2\u0090\u008e\3\2"+
		"\2\2\u0091\u0092\7)\2\2\u0092(\3\2\2\2\u0093\u0094\7*\2\2\u0094*\3\2\2"+
		"\2\u0095\u0096\7+\2\2\u0096,\3\2\2\2\u0097\u0098\t\6\2\2\u0098\u0099\t"+
		"\f\2\2\u0099\u009a\t\25\2\2\u009a\u00a1\t\3\2\2\u009b\u009c\t\r\2\2\u009c"+
		"\u009d\t\t\2\2\u009d\u009e\t\4\2\2\u009e\u009f\t\2\2\2\u009f\u00a1\t\3"+
		"\2\2\u00a0\u0097\3\2\2\2\u00a0\u009b\3\2\2\2\u00a1.\3\2\2\2\u00a2\u00a6"+
		"\4\63;\2\u00a3\u00a5\4\62;\2\u00a4\u00a3\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6"+
		"\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00b0\3\2\2\2\u00a8\u00a6\3\2"+
		"\2\2\u00a9\u00ad\7\60\2\2\u00aa\u00ac\4\62;\2\u00ab\u00aa\3\2\2\2\u00ac"+
		"\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b1\3\2"+
		"\2\2\u00af\u00ad\3\2\2\2\u00b0\u00a9\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\60\3\2\2\2\u00b2\u00b6\4\63;\2\u00b3\u00b5\4\62;\2\u00b4\u00b3\3\2\2"+
		"\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\62"+
		"\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00c0\4>?\2\u00ba\u00bb\7>\2\2\u00bb"+
		"\u00c0\7?\2\2\u00bc\u00c0\7@\2\2\u00bd\u00be\7@\2\2\u00be\u00c0\7?\2\2"+
		"\u00bf\u00b9\3\2\2\2\u00bf\u00ba\3\2\2\2\u00bf\u00bc\3\2\2\2\u00bf\u00bd"+
		"\3\2\2\2\u00c0\64\3\2\2\2\u00c1\u00c3\t\31\2\2\u00c2\u00c1\3\2\2\2\u00c3"+
		"\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2"+
		"\2\2\u00c6\u00c7\b\33\2\2\u00c7\66\3\2\2\2\r\2\u0083\u0088\u008e\u00a0"+
		"\u00a6\u00ad\u00b0\u00b6\u00bf\u00c4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}