// Generated from DtSearch.g4 by ANTLR 4.5.3

package com.millersoft.solr.parsers.dtsearch;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DtSearchLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, INT=4, AND=5, OR=6, NOT=7, CONTAINS=8, W=9, PRE=10, 
		COLON=11, REGEXP=12, FIELD_NAME=13, SEARCH_STRING=14, STRING_LITERAL=15, 
		WS=16;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "INT", "AND", "OR", "NOT", "CONTAINS", "W", "PRE", 
		"COLON", "REGEXP", "FIELD_NAME", "SEARCH_STRING", "SEARCH_STRING_WORD", 
		"SEARCH_STRING_ALLOWED_SPECIAL_CHARACTERS", "STRING_LITERAL", "ALPHABET", 
		"DIGIT", "WS", "WILDCARD"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'.'", null, null, null, null, null, null, null, "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "INT", "AND", "OR", "NOT", "CONTAINS", "W", "PRE", 
		"COLON", "REGEXP", "FIELD_NAME", "SEARCH_STRING", "STRING_LITERAL", "WS"
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


	public DtSearchLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DtSearch.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\22\u0091\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\6\5\65\n\5\r\5\16\5\66\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tN\n\t\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\6\r_\n\r\r\r\16\r`\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\7\16i\n\16\f\16\16\16l\13\16\3\17\6\17o\n\17"+
		"\r\17\16\17p\3\20\3\20\3\20\3\20\6\20w\n\20\r\20\16\20x\3\21\3\21\3\22"+
		"\3\22\6\22\177\n\22\r\22\16\22\u0080\3\22\3\22\3\23\3\23\3\24\3\24\3\25"+
		"\6\25\u008a\n\25\r\25\16\25\u008b\3\25\3\25\3\26\3\26\2\2\27\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\2!\2#\21"+
		"%\2\'\2)\22+\2\3\2\26\4\2CCcc\4\2PPpp\4\2FFff\4\2QQqq\4\2TTtt\4\2VVvv"+
		"\4\2EEee\4\2KKkk\4\2UUuu\4\2YYyy\4\2RRrr\4\2GGgg\4\2$$\u201e\u201e\4\2"+
		"$$\u201f\u201f\3\2aa\4\2((//\4\2C\\c|\3\2\62;\5\2\13\f\17\17\"\"\7\2%"+
		"%\'(,,AA\u0080\u0080\u0098\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2#\3\2"+
		"\2\2\2)\3\2\2\2\3-\3\2\2\2\5/\3\2\2\2\7\61\3\2\2\2\t\64\3\2\2\2\138\3"+
		"\2\2\2\r<\3\2\2\2\17?\3\2\2\2\21M\3\2\2\2\23O\3\2\2\2\25R\3\2\2\2\27W"+
		"\3\2\2\2\31Y\3\2\2\2\33d\3\2\2\2\35n\3\2\2\2\37v\3\2\2\2!z\3\2\2\2#|\3"+
		"\2\2\2%\u0084\3\2\2\2\'\u0086\3\2\2\2)\u0089\3\2\2\2+\u008f\3\2\2\2-."+
		"\7*\2\2.\4\3\2\2\2/\60\7+\2\2\60\6\3\2\2\2\61\62\7\60\2\2\62\b\3\2\2\2"+
		"\63\65\5\'\24\2\64\63\3\2\2\2\65\66\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2"+
		"\2\67\n\3\2\2\289\t\2\2\29:\t\3\2\2:;\t\4\2\2;\f\3\2\2\2<=\t\5\2\2=>\t"+
		"\6\2\2>\16\3\2\2\2?@\t\3\2\2@A\t\5\2\2AB\t\7\2\2B\20\3\2\2\2CD\t\b\2\2"+
		"DE\t\5\2\2EF\t\3\2\2FG\t\7\2\2GH\t\2\2\2HI\t\t\2\2IJ\t\3\2\2JN\t\n\2\2"+
		"KL\7<\2\2LN\7<\2\2MC\3\2\2\2MK\3\2\2\2N\22\3\2\2\2OP\t\13\2\2PQ\7\61\2"+
		"\2Q\24\3\2\2\2RS\t\f\2\2ST\t\6\2\2TU\t\r\2\2UV\7\61\2\2V\26\3\2\2\2WX"+
		"\7<\2\2X\30\3\2\2\2YZ\t\16\2\2Z[\7%\2\2[\\\7%\2\2\\^\3\2\2\2]_\n\17\2"+
		"\2^]\3\2\2\2_`\3\2\2\2`^\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc\t\17\2\2c\32\3"+
		"\2\2\2dj\5%\23\2ei\5%\23\2fi\5\'\24\2gi\t\20\2\2he\3\2\2\2hf\3\2\2\2h"+
		"g\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2k\34\3\2\2\2lj\3\2\2\2mo\5\37\20"+
		"\2nm\3\2\2\2op\3\2\2\2pn\3\2\2\2pq\3\2\2\2q\36\3\2\2\2rw\5%\23\2sw\5\'"+
		"\24\2tw\5+\26\2uw\5!\21\2vr\3\2\2\2vs\3\2\2\2vt\3\2\2\2vu\3\2\2\2wx\3"+
		"\2\2\2xv\3\2\2\2xy\3\2\2\2y \3\2\2\2z{\t\21\2\2{\"\3\2\2\2|~\t\16\2\2"+
		"}\177\n\17\2\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081"+
		"\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\t\17\2\2\u0083$\3\2\2\2\u0084"+
		"\u0085\t\22\2\2\u0085&\3\2\2\2\u0086\u0087\t\23\2\2\u0087(\3\2\2\2\u0088"+
		"\u008a\t\24\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3"+
		"\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\b\25\2\2\u008e"+
		"*\3\2\2\2\u008f\u0090\t\25\2\2\u0090,\3\2\2\2\r\2\66M`hjpvx\u0080\u008b"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}