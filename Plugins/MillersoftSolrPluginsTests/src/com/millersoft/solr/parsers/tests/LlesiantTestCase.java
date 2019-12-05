package com.millersoft.solr.parsers.tests;

import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.lucene.analysis.MockAnalyzer;
import org.apache.lucene.analysis.MockTokenizer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.RandomIndexWriter;
import org.apache.lucene.search.CheckHits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryUtils;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.English;
import org.apache.lucene.util.LuceneTestCase;
import org.apache.lucene.util.TestUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.millersoft.solr.parsers.dtsearch.DtSearchErrorStrategy;
import com.millersoft.solr.parsers.dtsearch.DtSearchLexer;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser;
import com.millersoft.solr.parsers.dtsearch.DtSearchVisitorToQuery;

import junit.framework.Assert;

public class MillersoftTestCase extends LuceneTestCase {
	protected static IndexSearcher searcher;
	protected static IndexReader reader;
	protected static Directory directory;
	  
	@BeforeClass
	  public static void beforeClass() throws Exception {
	    directory = newDirectory();
	    RandomIndexWriter writer = new RandomIndexWriter(random(), directory,
	        newIndexWriterConfig(new MockAnalyzer(random(), MockTokenizer.SIMPLE, true))
	            .setMaxBufferedDocs(TestUtil.nextInt(random(), 100, 1000)).setMergePolicy(newLogMergePolicy()));
	    
	    for (int i = 0; i < 2000; i++) {
	      Document doc = getDocumentToIndexFromInt(i);
	      writer.addDocument(doc);
	    }
	    reader = writer.getReader();
	    searcher = newSearcher(reader);
	    writer.close();
	  }

	  @AfterClass
	  public static void afterClass() throws Exception {
	    reader.close();
	    directory.close();
	    searcher = null;
	    reader = null;
	    directory = null;
	  }
	  
	  protected static Document getDocumentToIndexFromInt(int i) {
	  	Document doc = new Document();
	  	String english = English.intToEnglish(i);
	    doc.add(newTextField("field", english, Field.Store.YES));
	    doc.add(newTextField("reverse", reverseString(english), Field.Store.YES));
	    return doc;
	  }
	  	
	  private static String reverseString(String input) {
	  	int length = input.length();
	  	String reverse = "";
	    for (int i = length - 1 ; i >= 0 ; i--)
	       reverse = reverse + input.charAt(i);
	    return reverse;
	  }
	  
	  protected void checkHits(Query query, int[] results) throws IOException {
	    checkHits(random(), query, "field", searcher, results, 2000);
	  }
	  
	  /**
	   * Tests that a query matches the an expected set of documents using Hits.
	   *
	   * <p>
	   * Note that when using the Hits API, documents will only be returned
	   * if they have a positive normalized score.
	   * </p>
	   * @param query the query to test
	   * @param searcher the searcher to test the query against
	   * @param defaultFieldName used for displaing the query in assertion messages
	   * @param results a list of documentIds that must match the query
	   * @see #checkHitCollector
	   */
	  public static void checkHits(
	        Random random,
	        Query query,
	        String defaultFieldName,
	        IndexSearcher searcher,
	        int[] results, int maxDocs)
	          throws IOException {

	    ScoreDoc[] hits = searcher.search(query, maxDocs).scoreDocs;

	    Set<Integer> correct = new TreeSet<>();
	    for (int i = 0; i < results.length; i++) {
	      correct.add(Integer.valueOf(results[i]));
	    }

	    Set<Integer> actual = new TreeSet<>();
	    for (int i = 0; i < hits.length; i++) {
	      actual.add(Integer.valueOf(hits[i].doc));
	    }

	    Assert.assertEquals(query.toString(defaultFieldName), correct, actual);

	    QueryUtils.check(random, query,searcher, LuceneTestCase.rarely(random));
	  }
	  
	  protected void checkHitsOrder(Query query, int[] expected) throws IOException {
		// ensure the weighting wins
	    ScoreDoc[] hits = searcher.search(query, 1000).scoreDocs;
	    
	    for(int i=0;i<expected.length;i++) {
	    	assertEquals(expected[i], hits[i].doc);
	    }
	  }
	  
	  protected int[] integerSetToIntArray(Set<Integer> input){
		  if(input == null)
			  return new int[0];
		  int[] result = new int[input.size()];
		  int i = 0;
		  for(Integer item: input){
			  result[i] = item;
			  i++;
		  }
		  return result;
	  }
	  
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
		
		private DtSearchVisitorToQuery getVisitor(String defaultFieldName){
			DtSearchVisitorToQuery visitor = new DtSearchVisitorToQuery();
			visitor.setDefaultFieldName(defaultFieldName);
			return visitor;
		}
		
		protected Query getParseResultQuery(String defaultFieldName, String queryString){
			DtSearchParser parser = getParser(queryString);
			ParseTree tree = parser.query();		
			DtSearchVisitorToQuery visitor = getVisitor(defaultFieldName);
			Query result = visitor.visit(tree);
			return result;
		}
}
