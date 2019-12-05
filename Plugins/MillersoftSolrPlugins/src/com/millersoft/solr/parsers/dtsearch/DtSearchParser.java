// Generated from DtSearch.g4 by ANTLR 4.5.3

package com.millersoft.solr.parsers.dtsearch;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DtSearchParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, INT=4, AND=5, OR=6, NOT=7, CONTAINS=8, W=9, PRE=10, 
		COLON=11, REGEXP=12, FIELD_NAME=13, SEARCH_STRING=14, STRING_LITERAL=15, 
		WS=16;
	public static final int
		RULE_query = 0, RULE_expr = 1, RULE_regexp = 2, RULE_phrase = 3, RULE_number = 4, 
		RULE_floatingPoint = 5;
	public static final String[] ruleNames = {
		"query", "expr", "regexp", "phrase", "number", "floatingPoint"
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

	@Override
	public String getGrammarFileName() { return "DtSearch.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DtSearchParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			expr(0);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Not_exprContext extends ExprContext {
		public TerminalNode NOT() { return getToken(DtSearchParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Not_exprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterNot_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitNot_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitNot_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Sub_exprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Sub_exprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterSub_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitSub_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitSub_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class And_exprContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode AND() { return getToken(DtSearchParser.AND, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public And_exprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterAnd_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitAnd_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitAnd_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class W_exprContext extends ExprContext {
		public ExprContext left;
		public NumberContext slop;
		public ExprContext right;
		public TerminalNode W() { return getToken(DtSearchParser.W, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode NOT() { return getToken(DtSearchParser.NOT, 0); }
		public W_exprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterW_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitW_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitW_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Field_contains_exprContext extends ExprContext {
		public TerminalNode FIELD_NAME() { return getToken(DtSearchParser.FIELD_NAME, 0); }
		public TerminalNode CONTAINS() { return getToken(DtSearchParser.CONTAINS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Field_contains_exprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterField_contains_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitField_contains_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitField_contains_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Or_exprContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode OR() { return getToken(DtSearchParser.OR, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Or_exprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterOr_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitOr_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitOr_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Phrase_exprContext extends ExprContext {
		public FloatingPointContext termWeight;
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode COLON() { return getToken(DtSearchParser.COLON, 0); }
		public FloatingPointContext floatingPoint() {
			return getRuleContext(FloatingPointContext.class,0);
		}
		public Phrase_exprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterPhrase_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitPhrase_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitPhrase_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Pre_exprContext extends ExprContext {
		public ExprContext left;
		public NumberContext slop;
		public ExprContext right;
		public TerminalNode PRE() { return getToken(DtSearchParser.PRE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode NOT() { return getToken(DtSearchParser.NOT, 0); }
		public Pre_exprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterPre_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitPre_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitPre_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Regexp_exprContext extends ExprContext {
		public FloatingPointContext termWeight;
		public RegexpContext regexp() {
			return getRuleContext(RegexpContext.class,0);
		}
		public TerminalNode COLON() { return getToken(DtSearchParser.COLON, 0); }
		public FloatingPointContext floatingPoint() {
			return getRuleContext(FloatingPointContext.class,0);
		}
		public Regexp_exprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterRegexp_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitRegexp_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitRegexp_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				_localctx = new Not_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(15);
				match(NOT);
				setState(16);
				expr(9);
				}
				break;
			case 2:
				{
				_localctx = new Regexp_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(17);
				regexp();
				setState(20);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(18);
					match(COLON);
					setState(19);
					((Regexp_exprContext)_localctx).termWeight = floatingPoint();
					}
					break;
				}
				}
				break;
			case 3:
				{
				_localctx = new Phrase_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(22);
				phrase();
				setState(25);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(23);
					match(COLON);
					setState(24);
					((Phrase_exprContext)_localctx).termWeight = floatingPoint();
					}
					break;
				}
				}
				break;
			case 4:
				{
				_localctx = new Field_contains_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(27);
				match(FIELD_NAME);
				setState(28);
				match(CONTAINS);
				setState(29);
				expr(4);
				}
				break;
			case 5:
				{
				_localctx = new Sub_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(30);
				match(T__0);
				setState(31);
				expr(0);
				setState(32);
				match(T__1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(60);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(58);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new W_exprContext(new ExprContext(_parentctx, _parentState));
						((W_exprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(36);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(38);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(37);
							match(NOT);
							}
						}

						setState(40);
						match(W);
						setState(41);
						((W_exprContext)_localctx).slop = number();
						setState(42);
						((W_exprContext)_localctx).right = expr(9);
						}
						break;
					case 2:
						{
						_localctx = new Pre_exprContext(new ExprContext(_parentctx, _parentState));
						((Pre_exprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(44);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(46);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(45);
							match(NOT);
							}
						}

						setState(48);
						match(PRE);
						setState(49);
						((Pre_exprContext)_localctx).slop = number();
						setState(50);
						((Pre_exprContext)_localctx).right = expr(8);
						}
						break;
					case 3:
						{
						_localctx = new And_exprContext(new ExprContext(_parentctx, _parentState));
						((And_exprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(52);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(53);
						match(AND);
						setState(54);
						((And_exprContext)_localctx).right = expr(4);
						}
						break;
					case 4:
						{
						_localctx = new Or_exprContext(new ExprContext(_parentctx, _parentState));
						((Or_exprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(55);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(56);
						match(OR);
						setState(57);
						((Or_exprContext)_localctx).right = expr(3);
						}
						break;
					}
					} 
				}
				setState(62);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class RegexpContext extends ParserRuleContext {
		public TerminalNode REGEXP() { return getToken(DtSearchParser.REGEXP, 0); }
		public RegexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterRegexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitRegexp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitRegexp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegexpContext regexp() throws RecognitionException {
		RegexpContext _localctx = new RegexpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_regexp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(REGEXP);
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

	public static class PhraseContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(DtSearchParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(DtSearchParser.INT, i);
		}
		public List<TerminalNode> STRING_LITERAL() { return getTokens(DtSearchParser.STRING_LITERAL); }
		public TerminalNode STRING_LITERAL(int i) {
			return getToken(DtSearchParser.STRING_LITERAL, i);
		}
		public List<TerminalNode> FIELD_NAME() { return getTokens(DtSearchParser.FIELD_NAME); }
		public TerminalNode FIELD_NAME(int i) {
			return getToken(DtSearchParser.FIELD_NAME, i);
		}
		public List<TerminalNode> SEARCH_STRING() { return getTokens(DtSearchParser.SEARCH_STRING); }
		public TerminalNode SEARCH_STRING(int i) {
			return getToken(DtSearchParser.SEARCH_STRING, i);
		}
		public PhraseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phrase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterPhrase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitPhrase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitPhrase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PhraseContext phrase() throws RecognitionException {
		PhraseContext _localctx = new PhraseContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_phrase);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(66); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(65);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << FIELD_NAME) | (1L << SEARCH_STRING) | (1L << STRING_LITERAL))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(68); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(DtSearchParser.INT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(INT);
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

	public static class FloatingPointContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(DtSearchParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(DtSearchParser.INT, i);
		}
		public FloatingPointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatingPoint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).enterFloatingPoint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DtSearchListener ) ((DtSearchListener)listener).exitFloatingPoint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DtSearchVisitor ) return ((DtSearchVisitor<? extends T>)visitor).visitFloatingPoint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatingPointContext floatingPoint() throws RecognitionException {
		FloatingPointContext _localctx = new FloatingPointContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_floatingPoint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(INT);
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(73);
				match(T__2);
				setState(74);
				match(INT);
				}
				break;
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\22P\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3"+
		"\27\n\3\3\3\3\3\3\3\5\3\34\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3%\n\3\3"+
		"\3\3\3\5\3)\n\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\61\n\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\7\3=\n\3\f\3\16\3@\13\3\3\4\3\4\3\5\6\5E\n\5\r\5"+
		"\16\5F\3\6\3\6\3\7\3\7\3\7\5\7N\n\7\3\7\2\3\4\b\2\4\6\b\n\f\2\3\4\2\6"+
		"\6\17\21W\2\16\3\2\2\2\4$\3\2\2\2\6A\3\2\2\2\bD\3\2\2\2\nH\3\2\2\2\fJ"+
		"\3\2\2\2\16\17\5\4\3\2\17\3\3\2\2\2\20\21\b\3\1\2\21\22\7\t\2\2\22%\5"+
		"\4\3\13\23\26\5\6\4\2\24\25\7\r\2\2\25\27\5\f\7\2\26\24\3\2\2\2\26\27"+
		"\3\2\2\2\27%\3\2\2\2\30\33\5\b\5\2\31\32\7\r\2\2\32\34\5\f\7\2\33\31\3"+
		"\2\2\2\33\34\3\2\2\2\34%\3\2\2\2\35\36\7\17\2\2\36\37\7\n\2\2\37%\5\4"+
		"\3\6 !\7\3\2\2!\"\5\4\3\2\"#\7\4\2\2#%\3\2\2\2$\20\3\2\2\2$\23\3\2\2\2"+
		"$\30\3\2\2\2$\35\3\2\2\2$ \3\2\2\2%>\3\2\2\2&(\f\n\2\2\')\7\t\2\2(\'\3"+
		"\2\2\2()\3\2\2\2)*\3\2\2\2*+\7\13\2\2+,\5\n\6\2,-\5\4\3\13-=\3\2\2\2."+
		"\60\f\t\2\2/\61\7\t\2\2\60/\3\2\2\2\60\61\3\2\2\2\61\62\3\2\2\2\62\63"+
		"\7\f\2\2\63\64\5\n\6\2\64\65\5\4\3\n\65=\3\2\2\2\66\67\f\5\2\2\678\7\7"+
		"\2\28=\5\4\3\69:\f\4\2\2:;\7\b\2\2;=\5\4\3\5<&\3\2\2\2<.\3\2\2\2<\66\3"+
		"\2\2\2<9\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?\5\3\2\2\2@>\3\2\2\2AB"+
		"\7\16\2\2B\7\3\2\2\2CE\t\2\2\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2"+
		"G\t\3\2\2\2HI\7\6\2\2I\13\3\2\2\2JM\7\6\2\2KL\7\5\2\2LN\7\6\2\2MK\3\2"+
		"\2\2MN\3\2\2\2N\r\3\2\2\2\13\26\33$(\60<>FM";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}