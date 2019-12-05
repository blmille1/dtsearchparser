package com.millersoft.solr.parsers.dtsearch;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.lucene.search.Query;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.schema.IndexSchema;
import org.apache.solr.search.QParser;
import org.apache.solr.search.QParserPlugin;
import org.apache.solr.search.QueryParsing;


public class DtSearchQParserPlugin extends QParserPlugin {
	public static final String NAME = "dtSearch";
	
	public static class BailDtSearchLexer extends DtSearchLexer {
	    public BailDtSearchLexer(CharStream input) { super(input); }
	    public void recover(LexerNoViableAltException e) {
	        throw new RuntimeException(e); // Bail out
	    }
	}
	
	  @Override
	  public QParser createParser(String qstr, SolrParams localParams, SolrParams params, SolrQueryRequest req) {
	    return new QParser(qstr, localParams, params, req) {
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
	    	
	    	private Query getParseResultQuery(String defaultFieldName,String queryString){
	    		DtSearchParser parser = getParser(queryString);
	    		ParseTree tree = parser.query();		
	    		DtSearchVisitorToQuery visitor = getVisitor(defaultFieldName);
	    		Query result = visitor.visit(tree);
	    		return result;
	    	}
	    	
	      @Override
	      public Query parse() {
	    	  final IndexSchema schema = req.getSchema();
	          final String defaultField = QueryParsing.getDefaultField(schema, getParam(CommonParams.DF));
	          //final Analyzer analyzer = schema.getQueryAnalyzer();
	          //final SolrCoreParser solrParser = new SolrCoreParser(defaultField, analyzer, req);
	          //TODO: remove the tolowercase when we get the indexing thing figured out
	          Query query = getParseResultQuery(defaultField, qstr.toLowerCase());
	    	  return query;
	      }
	    };
	  }
}
