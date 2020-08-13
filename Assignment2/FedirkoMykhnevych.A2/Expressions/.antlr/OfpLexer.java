// Generated from c:\Users\Mykhailo\Desktop\lnu\compilers\tania\ExpressionGrammar\Assignment2\FedirkoMykhnevych.A2\Expressions\Ofp.g4 by ANTLR 4.7.1
    // Define output package.
    package FedirkoMykhnevych.A2;

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
		T__0=1, T__1=2, T__2=3, BOOLEAN=4, CHAR=5, FLOAT=6, INT=7, STRING=8, VOID=9, 
		ELSE=10, IF=11, RETURN=12, WHILE=13, NEW=14, PRINT=15, PRINT_LINE=16, 
		LPAREN=17, RPAREN=18, LBRACE=19, RBRACE=20, LBRACK=21, RBRACK=22, SEMI=23, 
		COMMA=24, DOT=25, ASSIGN=26, GT=27, LT=28, EQUAL=29, ADD=30, SUB=31, MUL=32, 
		DIV=33, INTEGER_LITERAL=34, FLOAT_LITERAL=35, BOOL_LITERAL=36, CHAR_LITERAL=37, 
		STRING_LITERAL=38, WS=39, COMMENT=40, IDENTIFIER=41;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "BOOLEAN", "CHAR", "FLOAT", "INT", "STRING", "VOID", 
		"ELSE", "IF", "RETURN", "WHILE", "NEW", "PRINT", "PRINT_LINE", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", 
		"ASSIGN", "GT", "LT", "EQUAL", "ADD", "SUB", "MUL", "DIV", "INTEGER_LITERAL", 
		"FLOAT_LITERAL", "BOOL_LITERAL", "CHAR_LITERAL", "STRING_LITERAL", "WS", 
		"COMMENT", "IDENTIFIER", "Digits", "Letter"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'void main()'", "'[]'", "':'", "'bool'", "'char'", "'float'", "'int'", 
		"'string'", "'void'", "'else'", "'if'", "'return'", "'while'", "'new'", 
		"'print'", "'println'", "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", 
		"','", "'.'", "'='", "'>'", "'<'", "'=='", "'+'", "'-'", "'*'", "'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "BOOLEAN", "CHAR", "FLOAT", "INT", "STRING", "VOID", 
		"ELSE", "IF", "RETURN", "WHILE", "NEW", "PRINT", "PRINT_LINE", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", 
		"ASSIGN", "GT", "LT", "EQUAL", "ADD", "SUB", "MUL", "DIV", "INTEGER_LITERAL", 
		"FLOAT_LITERAL", "BOOL_LITERAL", "CHAR_LITERAL", "STRING_LITERAL", "WS", 
		"COMMENT", "IDENTIFIER"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2+\u011a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34"+
		"\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3#"+
		"\5#\u00d8\n#\5#\u00da\n#\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\5%\u00e9"+
		"\n%\3&\3&\3&\3&\3\'\3\'\6\'\u00f1\n\'\r\'\16\'\u00f2\3\'\3\'\3(\6(\u00f8"+
		"\n(\r(\16(\u00f9\3(\3(\3)\3)\7)\u0100\n)\f)\16)\u0103\13)\3)\3)\3*\3*"+
		"\3*\7*\u010a\n*\f*\16*\u010d\13*\3+\3+\7+\u0111\n+\f+\16+\u0114\13+\3"+
		"+\5+\u0117\n+\3,\3,\2\2-\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63"+
		"\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U\2W\2\3\2\b\3\2\63;"+
		"\13\2\"#*+..\60\60<<??AAC\\c|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\3\2\62"+
		";\4\2C\\c|\2\u0121\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\3Y\3\2\2\2\5e\3\2\2\2\7h\3\2\2\2\tj\3\2\2\2\13o\3\2\2"+
		"\2\rt\3\2\2\2\17z\3\2\2\2\21~\3\2\2\2\23\u0085\3\2\2\2\25\u008a\3\2\2"+
		"\2\27\u008f\3\2\2\2\31\u0092\3\2\2\2\33\u0099\3\2\2\2\35\u009f\3\2\2\2"+
		"\37\u00a3\3\2\2\2!\u00a9\3\2\2\2#\u00b1\3\2\2\2%\u00b3\3\2\2\2\'\u00b5"+
		"\3\2\2\2)\u00b7\3\2\2\2+\u00b9\3\2\2\2-\u00bb\3\2\2\2/\u00bd\3\2\2\2\61"+
		"\u00bf\3\2\2\2\63\u00c1\3\2\2\2\65\u00c3\3\2\2\2\67\u00c5\3\2\2\29\u00c7"+
		"\3\2\2\2;\u00c9\3\2\2\2=\u00cc\3\2\2\2?\u00ce\3\2\2\2A\u00d0\3\2\2\2C"+
		"\u00d2\3\2\2\2E\u00d9\3\2\2\2G\u00db\3\2\2\2I\u00e8\3\2\2\2K\u00ea\3\2"+
		"\2\2M\u00ee\3\2\2\2O\u00f7\3\2\2\2Q\u00fd\3\2\2\2S\u0106\3\2\2\2U\u010e"+
		"\3\2\2\2W\u0118\3\2\2\2YZ\7x\2\2Z[\7q\2\2[\\\7k\2\2\\]\7f\2\2]^\7\"\2"+
		"\2^_\7o\2\2_`\7c\2\2`a\7k\2\2ab\7p\2\2bc\7*\2\2cd\7+\2\2d\4\3\2\2\2ef"+
		"\7]\2\2fg\7_\2\2g\6\3\2\2\2hi\7<\2\2i\b\3\2\2\2jk\7d\2\2kl\7q\2\2lm\7"+
		"q\2\2mn\7n\2\2n\n\3\2\2\2op\7e\2\2pq\7j\2\2qr\7c\2\2rs\7t\2\2s\f\3\2\2"+
		"\2tu\7h\2\2uv\7n\2\2vw\7q\2\2wx\7c\2\2xy\7v\2\2y\16\3\2\2\2z{\7k\2\2{"+
		"|\7p\2\2|}\7v\2\2}\20\3\2\2\2~\177\7u\2\2\177\u0080\7v\2\2\u0080\u0081"+
		"\7t\2\2\u0081\u0082\7k\2\2\u0082\u0083\7p\2\2\u0083\u0084\7i\2\2\u0084"+
		"\22\3\2\2\2\u0085\u0086\7x\2\2\u0086\u0087\7q\2\2\u0087\u0088\7k\2\2\u0088"+
		"\u0089\7f\2\2\u0089\24\3\2\2\2\u008a\u008b\7g\2\2\u008b\u008c\7n\2\2\u008c"+
		"\u008d\7u\2\2\u008d\u008e\7g\2\2\u008e\26\3\2\2\2\u008f\u0090\7k\2\2\u0090"+
		"\u0091\7h\2\2\u0091\30\3\2\2\2\u0092\u0093\7t\2\2\u0093\u0094\7g\2\2\u0094"+
		"\u0095\7v\2\2\u0095\u0096\7w\2\2\u0096\u0097\7t\2\2\u0097\u0098\7p\2\2"+
		"\u0098\32\3\2\2\2\u0099\u009a\7y\2\2\u009a\u009b\7j\2\2\u009b\u009c\7"+
		"k\2\2\u009c\u009d\7n\2\2\u009d\u009e\7g\2\2\u009e\34\3\2\2\2\u009f\u00a0"+
		"\7p\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7y\2\2\u00a2\36\3\2\2\2\u00a3\u00a4"+
		"\7r\2\2\u00a4\u00a5\7t\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7\7p\2\2\u00a7"+
		"\u00a8\7v\2\2\u00a8 \3\2\2\2\u00a9\u00aa\7r\2\2\u00aa\u00ab\7t\2\2\u00ab"+
		"\u00ac\7k\2\2\u00ac\u00ad\7p\2\2\u00ad\u00ae\7v\2\2\u00ae\u00af\7n\2\2"+
		"\u00af\u00b0\7p\2\2\u00b0\"\3\2\2\2\u00b1\u00b2\7*\2\2\u00b2$\3\2\2\2"+
		"\u00b3\u00b4\7+\2\2\u00b4&\3\2\2\2\u00b5\u00b6\7}\2\2\u00b6(\3\2\2\2\u00b7"+
		"\u00b8\7\177\2\2\u00b8*\3\2\2\2\u00b9\u00ba\7]\2\2\u00ba,\3\2\2\2\u00bb"+
		"\u00bc\7_\2\2\u00bc.\3\2\2\2\u00bd\u00be\7=\2\2\u00be\60\3\2\2\2\u00bf"+
		"\u00c0\7.\2\2\u00c0\62\3\2\2\2\u00c1\u00c2\7\60\2\2\u00c2\64\3\2\2\2\u00c3"+
		"\u00c4\7?\2\2\u00c4\66\3\2\2\2\u00c5\u00c6\7@\2\2\u00c68\3\2\2\2\u00c7"+
		"\u00c8\7>\2\2\u00c8:\3\2\2\2\u00c9\u00ca\7?\2\2\u00ca\u00cb\7?\2\2\u00cb"+
		"<\3\2\2\2\u00cc\u00cd\7-\2\2\u00cd>\3\2\2\2\u00ce\u00cf\7/\2\2\u00cf@"+
		"\3\2\2\2\u00d0\u00d1\7,\2\2\u00d1B\3\2\2\2\u00d2\u00d3\7\61\2\2\u00d3"+
		"D\3\2\2\2\u00d4\u00da\7\62\2\2\u00d5\u00d7\t\2\2\2\u00d6\u00d8\5U+\2\u00d7"+
		"\u00d6\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00da\3\2\2\2\u00d9\u00d4\3\2"+
		"\2\2\u00d9\u00d5\3\2\2\2\u00daF\3\2\2\2\u00db\u00dc\5U+\2\u00dc\u00dd"+
		"\7\60\2\2\u00dd\u00de\5U+\2\u00deH\3\2\2\2\u00df\u00e0\7v\2\2\u00e0\u00e1"+
		"\7t\2\2\u00e1\u00e2\7w\2\2\u00e2\u00e9\7g\2\2\u00e3\u00e4\7h\2\2\u00e4"+
		"\u00e5\7c\2\2\u00e5\u00e6\7n\2\2\u00e6\u00e7\7u\2\2\u00e7\u00e9\7g\2\2"+
		"\u00e8\u00df\3\2\2\2\u00e8\u00e3\3\2\2\2\u00e9J\3\2\2\2\u00ea\u00eb\7"+
		")\2\2\u00eb\u00ec\t\3\2\2\u00ec\u00ed\7)\2\2\u00edL\3\2\2\2\u00ee\u00f0"+
		"\7$\2\2\u00ef\u00f1\t\3\2\2\u00f0\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2"+
		"\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\7$"+
		"\2\2\u00f5N\3\2\2\2\u00f6\u00f8\t\4\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00f9"+
		"\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb"+
		"\u00fc\b(\2\2\u00fcP\3\2\2\2\u00fd\u0101\7%\2\2\u00fe\u0100\n\5\2\2\u00ff"+
		"\u00fe\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2"+
		"\2\2\u0102\u0104\3\2\2\2\u0103\u0101\3\2\2\2\u0104\u0105\b)\2\2\u0105"+
		"R\3\2\2\2\u0106\u010b\5W,\2\u0107\u010a\5W,\2\u0108\u010a\5U+\2\u0109"+
		"\u0107\3\2\2\2\u0109\u0108\3\2\2\2\u010a\u010d\3\2\2\2\u010b\u0109\3\2"+
		"\2\2\u010b\u010c\3\2\2\2\u010cT\3\2\2\2\u010d\u010b\3\2\2\2\u010e\u0116"+
		"\t\6\2\2\u010f\u0111\t\6\2\2\u0110\u010f\3\2\2\2\u0111\u0114\3\2\2\2\u0112"+
		"\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0115\3\2\2\2\u0114\u0112\3\2"+
		"\2\2\u0115\u0117\t\6\2\2\u0116\u0112\3\2\2\2\u0116\u0117\3\2\2\2\u0117"+
		"V\3\2\2\2\u0118\u0119\t\7\2\2\u0119X\3\2\2\2\r\2\u00d7\u00d9\u00e8\u00f2"+
		"\u00f9\u0101\u0109\u010b\u0112\u0116\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}