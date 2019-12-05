package com.millersoft.solr.parsers.dtsearch.tests;


import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import static org.junit.Assert.*;

import com.millersoft.solr.parsers.dtsearch.DtSearchErrorStrategy;
import com.millersoft.solr.parsers.dtsearch.DtSearchLexer;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser;
import com.millersoft.solr.parsers.dtsearch.DtSearchVisitorToString;

public class DtSearchVisitorToStringTests {
	public static class BailDtSearchLexer extends DtSearchLexer {
	    public BailDtSearchLexer(CharStream input) { super(input); }
	    public void recover(LexerNoViableAltException e) {
	        throw new RuntimeException(e); // Bail out
	    }
	}
	
	private DtSearchParser getParser(String queryString){
		ANTLRInputStream is = new ANTLRInputStream(queryString);
		DtSearchLexer lexer = new BailDtSearchLexer(is);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		DtSearchParser parser = new DtSearchParser(tokenStream);
		parser.setErrorHandler(new DtSearchErrorStrategy()); // This allows us throw the exceptions normally
		return parser;
	}
	
	private DtSearchVisitorToString getVisitor(){
		DtSearchVisitorToString visitor = new DtSearchVisitorToString();
		return visitor;
	}
	
	private String getParseResultString(String queryString){
		DtSearchParser parser = getParser(queryString);
		ParseTree tree = parser.query();		
		DtSearchVisitorToString visitor = getVisitor();
		String result = visitor.visit(tree);
		return result;
	}
	
	@Test(expected = RuntimeException.class)
	public void CanParseError() {
		String result = getParseResultString("A AND ");
		//assertEquals("NOT \"broad way\"", result);
	}

	@Test
	public void CanParseSimpleNotCaseInsensitive() {
		String result = getParseResultString("NoT \"broad way\"");
		assertEquals("NOT \"broad way\"", result);
	}
	
	@Test
	public void CanParseSimpleAndCaseInsensitive() {
		String result = getParseResultString("a AnD \"broad way\"");
		assertEquals("a AND \"broad way\"", result);
	}
	
	@Test
	public void CanParseSimpleOrCaseInsensitive() {
		String result = getParseResultString("a oR \"broad way\"");
		assertEquals("a OR \"broad way\"", result);
	}
	
	@Test
	public void CanParseSimplePreCaseInsensitive() {
		String result = getParseResultString("a pRe/1 \"broad way\"");
		assertEquals("a PRE/1 \"broad way\"", result);
	}
	
	@Test
	public void CanParseSimpleWithin() {
		String result = getParseResultString("a w/73 \"broad way\"");
		assertEquals("a W/73 \"broad way\"", result);
	}
	
	@Test
	public void CanParseSimpleHeadlineContainsCaseInsensitive() {
		String result = getParseResultString("HeAdLiNe CONtains \"broad way\"");
		assertEquals("HeAdLiNe CONTAINS \"broad way\"", result);
	}
	
	@Test
	public void CanParseSimpleSubQueryCaseInsensitive() {
		String result = getParseResultString("(nOT a)");
		assertEquals("(NOT a)", result);
	}
	
	
	@Test
	public void CanParseCrazyNestedQuery() {
		String input = "(CEO OR \"board of directors\" OR CFO OR CTO OR COO OR \"chief executive\" OR \"general counsel\" OR \"corporate counsel\" OR \"managing director\" OR \"top executive*\" OR \"high level executive*\" OR \"board of directors\" OR \"upper management\" OR \"senior management\" OR \"management team\" OR (chief pre/2 officer) OR corporate OR corporation) AND ((backdat* w/4 option*) OR \"financial crime*\" OR \"accounting crime*\" OR \"insider trading\" OR \"wire fraud\" OR \"tax fraud\" OR ((money OR \"anti-money\") w/3 launder*) OR ((tax OR taxes OR taxation) w/3 (evasion OR evade)) OR ((falsified OR falsify OR false OR \"failure to disclose\") w/5 (account* OR \"tax return*\" OR financial)) OR kickback* OR racketeer* OR \"ponzi scheme\" OR \"pyramid scheme\" OR ((asset OR assets) w/5 (seizure OR seized)) OR ((asset OR assets) w/5 forfeit*) OR ((fraud* OR counterfeit) w/5 (\"electronic funds transfer\" OR bank OR banking OR \"automated payment\" OR \"automated teller\" OR \"credit card\" OR \"direct deposit\" OR treasury OR accounting OR securities OR financial OR check OR loan OR mortgage OR \"negotiable instrument*\"))) AND NOT (\"Form4Oracle\" OR (fraud pre/2 (examiner OR detection OR prevention OR protection)))";
		String result = getParseResultString(input);
		
		// Now use the output from the first pass and compare the outputs
		String result2 = getParseResultString(result);
		assertEquals(result, result2);
	//	System.out.println(result2);
	}
}
