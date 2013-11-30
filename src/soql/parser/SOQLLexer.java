// Generated from /home/carlos/Proyectos/force-com-sql-runner/SOQL.g4 by ANTLR 4.1
package soql.parser;
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
		"'select'", "'in'", "','", "'.'", "'and'", "'or'", "'not'", "'from'", 
		"'where'", "'order'", "'by'", "ID", "STRING", "'('", "')'", "BOOLEAN", 
		"NUMERIC", "OPERATOR", "WS"
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
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 18: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\25\u0092\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\r\6\rX\n\r\r\r\16\rY\3\16\3\16\7\16^\n\16\f\16\16\16a\13\16\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\5\21r\n\21\3\22\3\22\7\22v\n\22\f\22\16\22y\13\22\3\22\3\22\7\22}\n\22"+
		"\f\22\16\22\u0080\13\22\5\22\u0082\n\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\5\23\u008a\n\23\3\24\6\24\u008d\n\24\r\24\16\24\u008e\3\24\3\24\2\25"+
		"\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27"+
		"\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\2\3\2\5\5"+
		"\2C\\aac|\4\2))^^\5\2\13\f\17\17\"\"\u009b\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\3)\3\2\2\2\5\60\3\2\2\2\7\63\3\2\2\2\t\65\3\2\2\2\13\67\3\2\2\2\r;\3"+
		"\2\2\2\17>\3\2\2\2\21B\3\2\2\2\23G\3\2\2\2\25M\3\2\2\2\27S\3\2\2\2\31"+
		"W\3\2\2\2\33[\3\2\2\2\35d\3\2\2\2\37f\3\2\2\2!q\3\2\2\2#s\3\2\2\2%\u0089"+
		"\3\2\2\2\'\u008c\3\2\2\2)*\7u\2\2*+\7g\2\2+,\7n\2\2,-\7g\2\2-.\7e\2\2"+
		"./\7v\2\2/\4\3\2\2\2\60\61\7k\2\2\61\62\7p\2\2\62\6\3\2\2\2\63\64\7.\2"+
		"\2\64\b\3\2\2\2\65\66\7\60\2\2\66\n\3\2\2\2\678\7c\2\289\7p\2\29:\7f\2"+
		"\2:\f\3\2\2\2;<\7q\2\2<=\7t\2\2=\16\3\2\2\2>?\7p\2\2?@\7q\2\2@A\7v\2\2"+
		"A\20\3\2\2\2BC\7h\2\2CD\7t\2\2DE\7q\2\2EF\7o\2\2F\22\3\2\2\2GH\7y\2\2"+
		"HI\7j\2\2IJ\7g\2\2JK\7t\2\2KL\7g\2\2L\24\3\2\2\2MN\7q\2\2NO\7t\2\2OP\7"+
		"f\2\2PQ\7g\2\2QR\7t\2\2R\26\3\2\2\2ST\7d\2\2TU\7{\2\2U\30\3\2\2\2VX\t"+
		"\2\2\2WV\3\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\32\3\2\2\2[_\7)\2\2\\"+
		"^\n\3\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`b\3\2\2\2a_\3\2\2\2"+
		"bc\7)\2\2c\34\3\2\2\2de\7*\2\2e\36\3\2\2\2fg\7+\2\2g \3\2\2\2hi\7v\2\2"+
		"ij\7t\2\2jk\7w\2\2kr\7g\2\2lm\7h\2\2mn\7c\2\2no\7n\2\2op\7u\2\2pr\7g\2"+
		"\2qh\3\2\2\2ql\3\2\2\2r\"\3\2\2\2sw\4\63;\2tv\4\62;\2ut\3\2\2\2vy\3\2"+
		"\2\2wu\3\2\2\2wx\3\2\2\2x\u0081\3\2\2\2yw\3\2\2\2z~\7\60\2\2{}\4\62;\2"+
		"|{\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0082\3\2\2\2\u0080"+
		"~\3\2\2\2\u0081z\3\2\2\2\u0081\u0082\3\2\2\2\u0082$\3\2\2\2\u0083\u008a"+
		"\4>?\2\u0084\u0085\7>\2\2\u0085\u008a\7?\2\2\u0086\u008a\7@\2\2\u0087"+
		"\u0088\7@\2\2\u0088\u008a\7?\2\2\u0089\u0083\3\2\2\2\u0089\u0084\3\2\2"+
		"\2\u0089\u0086\3\2\2\2\u0089\u0087\3\2\2\2\u008a&\3\2\2\2\u008b\u008d"+
		"\t\4\2\2\u008c\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008c\3\2\2\2\u008e"+
		"\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\b\24\2\2\u0091(\3\2\2\2"+
		"\13\2Y_qw~\u0081\u0089\u008e";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}