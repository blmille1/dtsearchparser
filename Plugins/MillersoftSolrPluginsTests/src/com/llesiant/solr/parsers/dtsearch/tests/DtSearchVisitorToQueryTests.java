package com.millersoft.solr.parsers.dtsearch.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.spans.SpanNearQuery;
import org.apache.lucene.search.spans.SpanQuery;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.junit.Test;

import com.millersoft.solr.parsers.dtsearch.DtSearchErrorStrategy;
import com.millersoft.solr.parsers.dtsearch.DtSearchLexer;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser;
import com.millersoft.solr.parsers.dtsearch.DtSearchVisitorToQuery;

public class DtSearchVisitorToQueryTests {
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
	
	private DtSearchVisitorToQuery getVisitor(){
		DtSearchVisitorToQuery visitor = new DtSearchVisitorToQuery();
		visitor.setDefaultFieldName("text");
		return visitor;
	}
	
	private Query getParseResultQuery(String queryString){
		DtSearchParser parser = getParser(queryString);
		ParseTree tree = parser.query();		
		DtSearchVisitorToQuery visitor = getVisitor();
		Query result = visitor.visit(tree);
		return result;
	}
	
	@Test
	public void CanParseSimpleTerm() {
		Query result = getParseResultQuery("samsung");
		assertTrue(result instanceof TermQuery);
		TermQuery tq = (TermQuery)result;
		assertEquals("text",tq.getTerm().field());
		assertEquals("samsung",tq.getTerm().text());
	}
	
	@Test
	public void CanParseSimpleWildcardTerm() {
		Query result = getParseResultQuery("test*");
		assertTrue(result instanceof WildcardQuery);
		WildcardQuery wq = (WildcardQuery)result;
		assertEquals("text",wq.getTerm().field());
		assertEquals("test*",wq.getTerm().text());
	}
	
	@Test
	public void CanParseSimpleAndCaseInsensitive() {
		Query result = getParseResultQuery("a AnD \"broad way\"");
		assertTrue(result instanceof BooleanQuery);
		
		BooleanQuery boolQuery = (BooleanQuery)result;
		
		assertEquals(2, boolQuery.clauses().size());
		BooleanClause clause1 = boolQuery.clauses().get(0);
		BooleanClause clause2 = boolQuery.clauses().get(1);
		
		assertTrue(clause1.isRequired());
		assertTrue(clause2.isRequired());
		assertEquals(Occur.MUST, clause1.getOccur());
		assertEquals(Occur.MUST, clause2.getOccur());
		
		assertTrue(clause1.getQuery() instanceof TermQuery);
		assertTrue(clause2.getQuery() instanceof PhraseQuery);
		TermQuery tq1 = (TermQuery)clause1.getQuery();
		PhraseQuery tq2 = (PhraseQuery)clause2.getQuery();
		assertEquals("text",tq1.getTerm().field());
		assertEquals("a",tq1.getTerm().text());
		
		assertEquals("text",tq2.getTerms()[0].field());
		assertEquals("broad",tq2.getTerms()[0].text());
		
		assertEquals("text",tq2.getTerms()[1].field());
		assertEquals("way",tq2.getTerms()[1].text());
	}
	
	@Test
	public void CanParseSimpleOrCaseInsensitive() {
		Query result = getParseResultQuery("a oR \"broad \t \r\n way\"");
		assertTrue(result instanceof BooleanQuery);
		
		BooleanQuery boolQuery = (BooleanQuery)result;
		
		assertEquals(2, boolQuery.clauses().size());
		BooleanClause clause1 = boolQuery.clauses().get(0);
		BooleanClause clause2 = boolQuery.clauses().get(1);
		
		assertEquals(Occur.SHOULD, clause1.getOccur());
		assertEquals(Occur.SHOULD, clause2.getOccur());
		
		assertTrue(clause1.getQuery() instanceof TermQuery);
		assertTrue(clause2.getQuery() instanceof PhraseQuery);
		TermQuery tq1 = (TermQuery)clause1.getQuery();
		PhraseQuery tq2 = (PhraseQuery)clause2.getQuery();
		assertEquals("text",tq1.getTerm().field());
		assertEquals("a",tq1.getTerm().text());
		
		assertEquals("text",tq2.getTerms()[0].field());
		assertEquals("broad",tq2.getTerms()[0].text());
		
		assertEquals("text",tq2.getTerms()[1].field());
		assertEquals("way",tq2.getTerms()[1].text());
	}
	
	@Test
	public void CanParseSimpleNotCaseInsensitive() {
		Query result = getParseResultQuery("a and NoT \"broad-\tway\"");
		assertTrue(result instanceof BooleanQuery);
		
		BooleanQuery boolQuery = (BooleanQuery)result;
		
		assertEquals(2, boolQuery.clauses().size());
		BooleanClause clause1 = boolQuery.clauses().get(0); // a
		BooleanClause clause2 = boolQuery.clauses().get(1); // (NOT "broad way")
		BooleanQuery notQuery = (BooleanQuery)clause2.getQuery();
		BooleanClause notClause = notQuery.clauses().get(0);
		
		assertEquals(Occur.MUST, clause1.getOccur());
		assertEquals(Occur.MUST, clause2.getOccur());
		assertEquals(Occur.MUST_NOT, notClause.getOccur());
		
		assertTrue(clause1.getQuery() instanceof TermQuery);
		assertTrue(clause2.getQuery() instanceof BooleanQuery);
		assertTrue(notClause.getQuery() instanceof PhraseQuery);
		TermQuery tq1 = (TermQuery)clause1.getQuery();
		PhraseQuery notQ = (PhraseQuery)notClause.getQuery();
		assertEquals("text",tq1.getTerm().field());
		assertEquals("a",tq1.getTerm().text());
		
		assertEquals("text",notQ.getTerms()[0].field());
		assertEquals("broad",notQ.getTerms()[0].text());
		
		assertEquals("text",notQ.getTerms()[1].field());
		assertEquals("way",notQ.getTerms()[1].text());
	}
	
	@Test
	public void CanParseSimpleSubQueryCaseInsensitive() {
		Query result = getParseResultQuery("a and (b or c)");
		assertTrue(result instanceof BooleanQuery);
		
		BooleanQuery boolQuery = (BooleanQuery)result;
		
		assertEquals(2, boolQuery.clauses().size());
		BooleanClause clause1 = boolQuery.clauses().get(0); // a
		BooleanClause clause2 = boolQuery.clauses().get(1); // (b or c)
		BooleanQuery subexprQuery = (BooleanQuery)clause2.getQuery(); // b or c
		BooleanClause subexprClause1 = subexprQuery.clauses().get(0); // b
		BooleanClause subexprClause2 = subexprQuery.clauses().get(1); // c
		
		
		assertEquals(Occur.MUST, clause1.getOccur());
		assertEquals(Occur.MUST, clause2.getOccur());
		assertEquals(Occur.SHOULD, subexprClause1.getOccur());
		assertEquals(Occur.SHOULD, subexprClause2.getOccur());
		
		assertTrue(clause1.getQuery() instanceof TermQuery);
		assertTrue(clause2.getQuery() instanceof BooleanQuery);
		assertTrue(subexprClause1.getQuery() instanceof TermQuery);
		assertTrue(subexprClause2.getQuery() instanceof TermQuery);
		TermQuery tq1 = (TermQuery)clause1.getQuery();
		TermQuery subExprTq1 = (TermQuery)subexprClause1.getQuery();
		TermQuery subExprTq2 = (TermQuery)subexprClause2.getQuery();
		assertEquals("text",tq1.getTerm().field());
		assertEquals("a",tq1.getTerm().text());
		
		assertEquals("text",subExprTq1.getTerm().field());
		assertEquals("b",subExprTq1.getTerm().text());
		
		assertEquals("text",subExprTq2.getTerm().field());
		assertEquals("c",subExprTq2.getTerm().text());
	}
	
	@Test
	public void CanParseSimpleFieldNameContainsCaseInsensitive() {
		Query result = getParseResultQuery("a and headline cOnTaInS (b or c)");
		assertTrue(result instanceof BooleanQuery);
		
		BooleanQuery boolQuery = (BooleanQuery)result;
		
		assertEquals(2, boolQuery.clauses().size());
		BooleanClause clause1 = boolQuery.clauses().get(0); // a
		BooleanClause clause2 = boolQuery.clauses().get(1); // (b or c)
		BooleanQuery subexprQuery = (BooleanQuery)clause2.getQuery(); // b or c
		BooleanClause subexprClause1 = subexprQuery.clauses().get(0); // b
		BooleanClause subexprClause2 = subexprQuery.clauses().get(1); // c
		
		
		assertEquals(Occur.MUST, clause1.getOccur());
		assertEquals(Occur.MUST, clause2.getOccur());
		assertEquals(Occur.SHOULD, subexprClause1.getOccur());
		assertEquals(Occur.SHOULD, subexprClause2.getOccur());
		
		assertTrue(clause1.getQuery() instanceof TermQuery);
		assertTrue(clause2.getQuery() instanceof BooleanQuery);
		assertTrue(subexprClause1.getQuery() instanceof TermQuery);
		assertTrue(subexprClause2.getQuery() instanceof TermQuery);
		TermQuery tq1 = (TermQuery)clause1.getQuery();
		TermQuery subExprTq1 = (TermQuery)subexprClause1.getQuery();
		TermQuery subExprTq2 = (TermQuery)subexprClause2.getQuery();
		assertEquals("text",tq1.getTerm().field());
		assertEquals("a",tq1.getTerm().text());
		
		assertEquals("headline",subExprTq1.getTerm().field());
		assertEquals("b",subExprTq1.getTerm().text());
		
		assertEquals("headline",subExprTq2.getTerm().field());
		assertEquals("c",subExprTq2.getTerm().text());
	}

	@Test
	public void CanParseSimplePreCaseInsensitive() {
		Query result = getParseResultQuery("a PrE/33 \"broad way\"");
		assertTrue(result instanceof SpanNearQuery);
		
		SpanNearQuery spanNearQuery = (SpanNearQuery)result;
		
		assertEquals(33, spanNearQuery.getSlop());
		assertTrue(spanNearQuery.isInOrder());
		
		assertEquals(2, spanNearQuery.getClauses().length);
		SpanQuery clause1 = spanNearQuery.getClauses()[0];
//		SpanQuery clause2 = spanNearQuery.getClauses()[1];
		
		assertTrue(clause1 instanceof SpanTermQuery);
//		assertTrue(clause2 instanceof SpanTermQuery);
//		
//		SpanTermQuery tq1 = (SpanTermQuery)clause1;
//		SpanTermQuery tq2 = (SpanTermQuery)clause2;
//		assertEquals("text",tq1.getTerm().field());
//		assertEquals("a",tq1.getTerm().text());
//		
//		assertEquals("text",tq2.getTerm().field());
//		assertEquals("broad way",tq2.getTerm().text());
	}
	
	@Test
	public void CanParseSimpleWCaseInsensitive() {
		Query result = getParseResultQuery("a w/33 \"broad \tway\"");
		assertTrue(result instanceof SpanNearQuery);
		
		SpanNearQuery spanNearQuery = (SpanNearQuery)result;
		
		assertEquals(33, spanNearQuery.getSlop());
		assertFalse(spanNearQuery.isInOrder());
		
		assertEquals(2, spanNearQuery.getClauses().length);
		SpanQuery clause1 = spanNearQuery.getClauses()[0];
//		SpanQuery clause2 = spanNearQuery.getClauses()[1];
		
		assertTrue(clause1 instanceof SpanTermQuery);
//		assertTrue(clause2 instanceof SpanTermQuery);
//		
//		SpanTermQuery tq1 = (SpanTermQuery)clause1;
//		SpanTermQuery tq2 = (SpanTermQuery)clause2;
//		assertEquals("text",tq1.getTerm().field());
//		assertEquals("a",tq1.getTerm().text());
//		
//		assertEquals("text",tq2.getTerm().field());
//		assertEquals("broad way",tq2.getTerm().text());
	}

}
