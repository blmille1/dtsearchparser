package com.millersoft.solr.parsers.dtsearch;

import java.io.IOException;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.spans.SpanMultiTermQueryWrapper;
import org.apache.lucene.search.spans.SpanNotQuery;
import org.apache.lucene.search.spans.SpanOrQuery;
import org.apache.lucene.search.spans.SpanQuery;
import org.apache.lucene.search.spans.SpanWeight;

/**
 * NOTE: This is not the same as SpanNotQuery.  This is a placeholder because we need to move
 * some stuff around in order to get it to work.  This is basically enabling us to support some "syntax sugar,"
 * that the user could've worded it a different way, but more cumbersome.
 * I.e. If we're in a Span, which we will be in we're creating an instance of this thing, and we 
 * come across QueryA AND NOT QueryB, we will want to eventually rewrite it as QueryA NOT W/Integer.MAX_VALUE QueryB.
 * Also, QueryA OR NOT QueryB will be rewritten as QueryA OR * NOT W/Integer.MAX_VALUE QueryB.
 * If we get QueryA w/50 NOT QueryB, rewrite as QueryA w/50 (* w/Integer.MAX_VALUE QueryB)-- the same as OR NOT
 * @author brandon.miller
 *
 */
public class NotSpanQuery extends SpanQuery {

	private SpanQuery subquery;
	
	@Override
	public String getField() {
		return subquery.getField();
	}

	@Override
	public SpanWeight createWeight(IndexSearcher searcher, boolean needsScores) throws IOException {
		return subquery.createWeight(searcher, needsScores);
	}

	@Override
	public String toString(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	public NotSpanQuery(SpanQuery query){
		subquery = query;
	}
	
	/**
	 * Converts QueryA AND NOT QueryB within a SpanQuery to QueryA NOT W/Integer.MAX_VALUE QueryB
	 * @return Rewritten query
	 */
	public SpanQuery convertForAnd(SpanQuery queryA) {
		// I don't know why post has to be Integer.MAX_VALUE - 5, but it can't be any closer to MAX_VALUE than that or it doesn't work
		SpanNotQuery spanNotQuery = new SpanNotQuery(queryA, subquery, Integer.MAX_VALUE, Integer.MAX_VALUE-1000);
		return spanNotQuery;
	}
	
	/**
	 * Converts QueryA OR NOT QueryB within a SpanQuery to QueryA OR * NOT W/Integer.MAX_VALUE QueryB
	 * @return Rewritten query
	 */
	public SpanQuery convertForOr(SpanQuery queryA) {
		SpanQuery spanNotQuery = convertFreeStandingNot();// new SpanNotQuery(swq, subquery,Integer.MAX_VALUE, Integer.MAX_VALUE);
		return new SpanOrQuery(queryA, spanNotQuery);
	}
	
	/**
	 * Converts NOT QueryB within a SpanQuery to * NOT W/Integer.MAX_VALUE QueryB
	 * @return Rewritten query
	 */
	public SpanQuery convertFreeStandingNot() {
		WildcardQuery wq = new WildcardQuery(new Term(getField(),"*"));
	    SpanQuery swq = new SpanMultiTermQueryWrapper<>(wq);
	    
		// I don't know why post has to be Integer.MAX_VALUE - 5, but it can't be any closer to MAX_VALUE than that or it doesn't work
	    // NOTE: I found a case where it needed to be farther from MAX_VALUE, so going to subtract 1000--hopefully a safe distance
		SpanNotQuery spanNotQuery = new SpanNotQuery(swq, subquery,Integer.MAX_VALUE, Integer.MAX_VALUE-1000);
		return spanNotQuery;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}
