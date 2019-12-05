package com.millersoft.solr.parsers.dtsearch.tests;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import com.millersoft.solr.parsers.dtsearch.DtSearchLexer;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.QueryContext;

public class DtSearchParserTests {
	private DtSearchParser getParser(String queryString){
		ANTLRInputStream is = new ANTLRInputStream(queryString);
		DtSearchLexer lexer = new DtSearchLexer(is);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		DtSearchParser parser = new DtSearchParser(tokenStream);
		return parser;
	}

	@Test
	public void CanParseSimpleNotCaseInsensitive() {
		DtSearchParser parser = getParser("NoT \"broad way\"");
		QueryContext result = parser.query();
	}
	
	@Test
	public void CanParseSimpleAndCaseInsensitive() {
		DtSearchParser parser = getParser("a AnD \"broad way\"");
		QueryContext result = parser.query();
	}
	
	@Test
	public void CanParseSimpleOrCaseInsensitive() {
		DtSearchParser parser = getParser("a oR \"broad way\"");
		QueryContext result = parser.query();
	}
	
	@Test
	public void CanParseSimplePreCaseInsensitive() {
		DtSearchParser parser = getParser("a pRe/1 \"broad way\"");
		QueryContext result = parser.query();
	}
	
	@Test
	public void CanParseSimpleWithin() {
		DtSearchParser parser = getParser("a w/73 \"broad way\"");
		QueryContext result = parser.query();
	}

	
	@Test
	public void CanParseSimpleHeadlineContainsCaseInsensitive() {
		DtSearchParser parser = getParser("HeAdLiNe CONtains \"broad way\"");
		QueryContext result = parser.query();
	}
	
	@Test
	public void CanParseSimpleSubQueryCaseInsensitive() {
		DtSearchParser parser = getParser("(not a)");
		QueryContext result = parser.query();
	}
	
	@Test
	public void CanParseCrazyNestedQuery() {
		String input = "(CEO OR \"board of directors\" OR CFO OR CTO OR COO OR \"chief executive\" OR \"general counsel\" OR \"corporate counsel\" OR \"managing director\" OR \"top executive*\" OR \"high level executive*\" OR \"board of directors\" OR \"upper management\" OR \"senior management\" OR \"management team\" OR (chief pre/2 officer) OR corporate OR corporation) AND ((backdat* w/4 option*) OR \"financial crime*\" OR \"accounting crime*\" OR \"insider trading\" OR \"wire fraud\" OR \"tax fraud\" OR ((money OR \"anti-money\") w/3 launder*) OR ((tax OR taxes OR taxation) w/3 (evasion OR evade)) OR ((falsified OR falsify OR false OR \"failure to disclose\") w/5 (account* OR \"tax return*\" OR financial)) OR kickback* OR racketeer* OR \"ponzi scheme\" OR \"pyramid scheme\" OR ((asset OR assets) w/5 (seizure OR seized)) OR ((asset OR assets) w/5 forfeit*) OR ((fraud* OR counterfeit) w/5 (\"electronic funds transfer\" OR bank OR banking OR \"automated payment\" OR \"automated teller\" OR \"credit card\" OR \"direct deposit\" OR treasury OR accounting OR securities OR financial OR check OR loan OR mortgage OR \"negotiable instrument*\"))) AND NOT (\"Form4Oracle\" OR (fraud pre/2 (examiner OR detection OR prevention OR protection)))";
		DtSearchParser parser = getParser(input);
		QueryContext result = parser.query();
	}
}
