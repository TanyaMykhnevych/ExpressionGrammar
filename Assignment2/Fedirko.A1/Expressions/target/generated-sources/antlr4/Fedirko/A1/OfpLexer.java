// Generated from Ofp.g4 by ANTLR 4.7.1
    // Define output package.
    package Fedirko.A1;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class OfpLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, NEW=11, WHILE=12, IF=13, ELSE=14, RETURN=15, DOT=16, INTEGER=17, 
		BOOLEAN=18, FLOAT=19, CHARACTER=20, STRING=21, VOID=22, INTEGER_LITERAL=23, 
		BOOLEAN_LITERAL=24, FLOAT_LITERAL=25, CHARACTER_LITERAL=26, STRING_LITERAL=27, 
		ASSGN=28, PLUS=29, MINUS=30, MULT=31, DIV=32, LT=33, GT=34, EQ=35, ID=36, 
		CMNT=37, WS=38;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "NEW", "WHILE", "IF", "ELSE", "RETURN", "DOT", "INTEGER", "BOOLEAN", 
		"FLOAT", "CHARACTER", "STRING", "VOID", "INTEGER_LITERAL", "BOOLEAN_LITERAL", 
		"FLOAT_LITERAL", "CHARACTER_LITERAL", "STRING_LITERAL", "ASSGN", "PLUS", 
		"MINUS", "MULT", "DIV", "LT", "GT", "EQ", "ID", "CMNT", "WS", "Digits"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'[]'", "'{'", "','", "'}'", "'['", "']'", "'('", "')'", "';'", 
		"'ofp'", "'new'", "'while'", "'if'", "'else'", "'return'", "'.'", "'int'", 
		"'bool'", "'float'", "'char'", "'string'", "'void'", null, null, null, 
		null, null, "'='", "'+'", "'-'", "'*'", "'/'", "'<'", "'>'", "'=='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "NEW", 
		"WHILE", "IF", "ELSE", "RETURN", "DOT", "INTEGER", "BOOLEAN", "FLOAT", 
		"CHARACTER", "STRING", "VOID", "INTEGER_LITERAL", "BOOLEAN_LITERAL", "FLOAT_LITERAL", 
		"CHARACTER_LITERAL", "STRING_LITERAL", "ASSGN", "PLUS", "MINUS", "MULT", 
		"DIV", "LT", "GT", "EQ", "ID", "CMNT", "WS"
	};
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


	public OfpLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Ofp.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u0103\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\2\3\3\3\3"+
		"\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\6\30\u00a7\n\30\r\30\16\30\u00a8\5\30\u00ab"+
		"\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00b6\n\31\3\32"+
		"\3\32\3\32\3\32\5\32\u00bc\n\32\3\32\3\32\3\32\5\32\u00c1\n\32\3\32\5"+
		"\32\u00c4\n\32\5\32\u00c6\n\32\3\33\3\33\3\33\3\33\3\34\3\34\6\34\u00ce"+
		"\n\34\r\34\16\34\u00cf\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3"+
		"!\3!\3\"\3\"\3#\3#\3$\3$\3$\3%\6%\u00e6\n%\r%\16%\u00e7\3&\3&\7&\u00ec"+
		"\n&\f&\16&\u00ef\13&\3&\3&\3\'\6\'\u00f4\n\'\r\'\16\'\u00f5\3\'\3\'\3"+
		"(\3(\7(\u00fc\n(\f(\16(\u00ff\13(\3(\5(\u0102\n(\2\2)\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O\2\3\2\t\6\2FFHHffhh\13\2\"#*+..\60\60<<??AAC\\c|\4\2C\\c|\4\2\f"+
		"\f\17\17\5\2\13\f\17\17\"\"\3\2\62;\4\2\62;aa\2\u010e\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\3Q\3\2\2\2\5T\3\2\2\2\7V\3\2\2\2\tX\3\2\2\2\13Z\3"+
		"\2\2\2\r\\\3\2\2\2\17^\3\2\2\2\21`\3\2\2\2\23b\3\2\2\2\25d\3\2\2\2\27"+
		"h\3\2\2\2\31l\3\2\2\2\33r\3\2\2\2\35u\3\2\2\2\37z\3\2\2\2!\u0081\3\2\2"+
		"\2#\u0083\3\2\2\2%\u0087\3\2\2\2\'\u008c\3\2\2\2)\u0092\3\2\2\2+\u0097"+
		"\3\2\2\2-\u009e\3\2\2\2/\u00aa\3\2\2\2\61\u00b5\3\2\2\2\63\u00c5\3\2\2"+
		"\2\65\u00c7\3\2\2\2\67\u00cb\3\2\2\29\u00d3\3\2\2\2;\u00d5\3\2\2\2=\u00d7"+
		"\3\2\2\2?\u00d9\3\2\2\2A\u00db\3\2\2\2C\u00dd\3\2\2\2E\u00df\3\2\2\2G"+
		"\u00e1\3\2\2\2I\u00e5\3\2\2\2K\u00e9\3\2\2\2M\u00f3\3\2\2\2O\u00f9\3\2"+
		"\2\2QR\7]\2\2RS\7_\2\2S\4\3\2\2\2TU\7}\2\2U\6\3\2\2\2VW\7.\2\2W\b\3\2"+
		"\2\2XY\7\177\2\2Y\n\3\2\2\2Z[\7]\2\2[\f\3\2\2\2\\]\7_\2\2]\16\3\2\2\2"+
		"^_\7*\2\2_\20\3\2\2\2`a\7+\2\2a\22\3\2\2\2bc\7=\2\2c\24\3\2\2\2de\7q\2"+
		"\2ef\7h\2\2fg\7r\2\2g\26\3\2\2\2hi\7p\2\2ij\7g\2\2jk\7y\2\2k\30\3\2\2"+
		"\2lm\7y\2\2mn\7j\2\2no\7k\2\2op\7n\2\2pq\7g\2\2q\32\3\2\2\2rs\7k\2\2s"+
		"t\7h\2\2t\34\3\2\2\2uv\7g\2\2vw\7n\2\2wx\7u\2\2xy\7g\2\2y\36\3\2\2\2z"+
		"{\7t\2\2{|\7g\2\2|}\7v\2\2}~\7w\2\2~\177\7t\2\2\177\u0080\7p\2\2\u0080"+
		" \3\2\2\2\u0081\u0082\7\60\2\2\u0082\"\3\2\2\2\u0083\u0084\7k\2\2\u0084"+
		"\u0085\7p\2\2\u0085\u0086\7v\2\2\u0086$\3\2\2\2\u0087\u0088\7d\2\2\u0088"+
		"\u0089\7q\2\2\u0089\u008a\7q\2\2\u008a\u008b\7n\2\2\u008b&\3\2\2\2\u008c"+
		"\u008d\7h\2\2\u008d\u008e\7n\2\2\u008e\u008f\7q\2\2\u008f\u0090\7c\2\2"+
		"\u0090\u0091\7v\2\2\u0091(\3\2\2\2\u0092\u0093\7e\2\2\u0093\u0094\7j\2"+
		"\2\u0094\u0095\7c\2\2\u0095\u0096\7t\2\2\u0096*\3\2\2\2\u0097\u0098\7"+
		"u\2\2\u0098\u0099\7v\2\2\u0099\u009a\7t\2\2\u009a\u009b\7k\2\2\u009b\u009c"+
		"\7p\2\2\u009c\u009d\7i\2\2\u009d,\3\2\2\2\u009e\u009f\7x\2\2\u009f\u00a0"+
		"\7q\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7f\2\2\u00a2.\3\2\2\2\u00a3\u00ab"+
		"\4\62;\2\u00a4\u00a6\4\63;\2\u00a5\u00a7\4\62;\2\u00a6\u00a5\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2"+
		"\2\2\u00aa\u00a3\3\2\2\2\u00aa\u00a4\3\2\2\2\u00ab\60\3\2\2\2\u00ac\u00ad"+
		"\7v\2\2\u00ad\u00ae\7t\2\2\u00ae\u00af\7w\2\2\u00af\u00b6\7g\2\2\u00b0"+
		"\u00b1\7h\2\2\u00b1\u00b2\7c\2\2\u00b2\u00b3\7n\2\2\u00b3\u00b4\7u\2\2"+
		"\u00b4\u00b6\7g\2\2\u00b5\u00ac\3\2\2\2\u00b5\u00b0\3\2\2\2\u00b6\62\3"+
		"\2\2\2\u00b7\u00c6\5/\30\2\u00b8\u00b9\5/\30\2\u00b9\u00bb\7\60\2\2\u00ba"+
		"\u00bc\5O(\2\u00bb\u00ba\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00c1\3\2\2"+
		"\2\u00bd\u00be\7\62\2\2\u00be\u00bf\13\2\2\2\u00bf\u00c1\5O(\2\u00c0\u00b8"+
		"\3\2\2\2\u00c0\u00bd\3\2\2\2\u00c1\u00c3\3\2\2\2\u00c2\u00c4\t\2\2\2\u00c3"+
		"\u00c2\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00b7\3\2"+
		"\2\2\u00c5\u00c0\3\2\2\2\u00c6\64\3\2\2\2\u00c7\u00c8\7)\2\2\u00c8\u00c9"+
		"\t\3\2\2\u00c9\u00ca\7)\2\2\u00ca\66\3\2\2\2\u00cb\u00cd\7$\2\2\u00cc"+
		"\u00ce\t\3\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00cd\3\2"+
		"\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\7$\2\2\u00d2"+
		"8\3\2\2\2\u00d3\u00d4\7?\2\2\u00d4:\3\2\2\2\u00d5\u00d6\7-\2\2\u00d6<"+
		"\3\2\2\2\u00d7\u00d8\7/\2\2\u00d8>\3\2\2\2\u00d9\u00da\7,\2\2\u00da@\3"+
		"\2\2\2\u00db\u00dc\7\61\2\2\u00dcB\3\2\2\2\u00dd\u00de\7>\2\2\u00deD\3"+
		"\2\2\2\u00df\u00e0\7@\2\2\u00e0F\3\2\2\2\u00e1\u00e2\7?\2\2\u00e2\u00e3"+
		"\7?\2\2\u00e3H\3\2\2\2\u00e4\u00e6\t\4\2\2\u00e5\u00e4\3\2\2\2\u00e6\u00e7"+
		"\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8J\3\2\2\2\u00e9"+
		"\u00ed\7%\2\2\u00ea\u00ec\n\5\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ef\3\2"+
		"\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f0\3\2\2\2\u00ef"+
		"\u00ed\3\2\2\2\u00f0\u00f1\b&\2\2\u00f1L\3\2\2\2\u00f2\u00f4\t\6\2\2\u00f3"+
		"\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\b\'\2\2\u00f8N\3\2\2\2\u00f9\u0101"+
		"\t\7\2\2\u00fa\u00fc\t\b\2\2\u00fb\u00fa\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd"+
		"\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0100\3\2\2\2\u00ff\u00fd\3\2"+
		"\2\2\u0100\u0102\t\7\2\2\u0101\u00fd\3\2\2\2\u0101\u0102\3\2\2\2\u0102"+
		"P\3\2\2\2\20\2\u00a8\u00aa\u00b5\u00bb\u00c0\u00c3\u00c5\u00cf\u00e7\u00ed"+
		"\u00f5\u00fd\u0101\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}