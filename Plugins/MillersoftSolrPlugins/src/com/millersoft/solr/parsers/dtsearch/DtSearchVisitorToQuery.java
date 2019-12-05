package com.millersoft.solr.parsers.dtsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BoostQuery;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.spans.SpanBoostQuery;
import org.apache.lucene.search.spans.SpanFirstQuery;
import org.apache.lucene.search.spans.SpanMultiTermQueryWrapper;
import org.apache.lucene.search.spans.SpanNearQuery;
import org.apache.lucene.search.spans.SpanNotQuery;
import org.apache.lucene.search.spans.SpanOrQuery;
import org.apache.lucene.search.spans.SpanQuery;
import org.apache.lucene.search.spans.SpanTermQuery;

import com.millersoft.solr.parsers.dtsearch.DtSearchParser.And_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Field_contains_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Not_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Or_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.PhraseContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Phrase_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Pre_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Regexp_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Sub_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.W_exprContext;


//TODO: Make this thing implement the interface so that we don't forget to implement something
public class DtSearchVisitorToQuery extends DtSearchBaseVisitor<Query> {
	private String defaultFieldName;
	private boolean currentlyInASpanQuery = false;
	private float currentPhraseBoost = -1; // -1 = no boost
	public String getDefaultFieldName(){ return defaultFieldName; }
	public void setDefaultFieldName(String fieldName){ this.defaultFieldName = fieldName; }
	
	@Override
	public Query visit(ParseTree tree) {
		return super.visit(tree);
	}
	
	@Override
	public Query visitAnd_expr(And_exprContext ctx) {
		Query leftQuery = visit(ctx.expr(0));
		Query rightQuery = visit(ctx.expr(1));
		Query result = null;
		if(currentlyInASpanQuery){
			if(leftQuery instanceof NotSpanQuery)
				leftQuery = ((NotSpanQuery)leftQuery).convertFreeStandingNot();
			if(rightQuery instanceof NotSpanQuery)
				// queryA AND NOT queryB
				result = ((NotSpanQuery)rightQuery).convertForAnd((SpanQuery)leftQuery);
			else {
				// AND is approximated by using a w/(really big int)
				result = new SpanNearQuery(new SpanQuery[]{(SpanQuery)leftQuery,(SpanQuery)rightQuery}, Integer.MAX_VALUE, false);
			}
		} else {
			BooleanQuery.Builder query = new BooleanQuery.Builder();
			query.add(leftQuery, BooleanClause.Occur.MUST);
			query.add(rightQuery, BooleanClause.Occur.MUST);
			result = query.build();
		}
		return result;
	}
	
	@Override
	public Query visitNot_expr(Not_exprContext ctx) {
		Query subquery = visit(ctx.expr());
		
		if(currentlyInASpanQuery){
			// NotSpanQuery is a placeholder and must be replaced higher up.
			// Since We're in a SPAN, this will have to be converted.  NotSpanQuery has methods
			// to call for different types of queries, so if you have OR NOT, or AND NOT, or ..., it should have a
			// converter for you so all you have to do is call the method that corresponds to your use--higher
			// up the tree.  This is only for syntactic sugar, as the user has other means of performing the same 
			// query, but this allows for a short-hand, which dtSearch does support.
			NotSpanQuery nsq = new NotSpanQuery((SpanQuery)subquery);
			if(ctx.parent != null && (ctx.parent instanceof And_exprContext || ctx.parent instanceof Or_exprContext)){
				// If AND or OR, they will need to rewrite the response.  If there are other binary operators for which
				// we need to support, then you may want to change this code so that it will be able to rewrite it in its
				// context.
				return nsq;
			} else {
				// We can't send a NotSpanQuery into the wild--it will break the system, so if we won't be converting it later,
				// convert it now to a free-standing NOT query.
				return nsq.convertFreeStandingNot();
			}
		} else {
			BooleanQuery.Builder query = new BooleanQuery.Builder();
			query.add(subquery, BooleanClause.Occur.MUST_NOT);
			// TODO: Consolidate this so that we don't use MatchAllDocsQuery, but using the other query, to increase performance
			query.add(new MatchAllDocsQuery(), BooleanClause.Occur.SHOULD);
			return query.build();
		}
	}
	
	@Override
	public Query visitOr_expr(Or_exprContext ctx) {
		//ProximityNotSupportedFor("OR");
		Query leftQuery = visit(ctx.expr(0));
		Query rightQuery = visit(ctx.expr(1));
		Query result = null;
		
		if(currentlyInASpanQuery){
			if(!(leftQuery instanceof SpanQuery))
				throw new UnsupportedOperationException("Left proximity query has unsupported operations: " + ctx.expr(0).getText());
			if(!(rightQuery instanceof SpanQuery))
				throw new UnsupportedOperationException("Right proximity query has unsupported operations: " + ctx.expr(1).getText());
			
			if(leftQuery instanceof NotSpanQuery)
				leftQuery = ((NotSpanQuery)leftQuery).convertFreeStandingNot();
			if(rightQuery instanceof NotSpanQuery)
				// queryA OR NOT queryB
				result = ((NotSpanQuery)rightQuery).convertForOr((SpanQuery)leftQuery);
			else {
				result = new SpanOrQuery(new SpanQuery[] {(SpanQuery) leftQuery,(SpanQuery) rightQuery});
			}
		} else {
			BooleanQuery.Builder query = new BooleanQuery.Builder();
		    query.add(leftQuery, BooleanClause.Occur.SHOULD);
		    query.add(rightQuery, BooleanClause.Occur.SHOULD);
			result = query.build();
		}
		return result;
	}

	@Override
	public Query visitField_contains_expr(Field_contains_exprContext ctx) {
		// Save current defaultFieldName
		String previousDefaultFieldName = this.defaultFieldName;
		// Change to new field name
		defaultFieldName = ctx.FIELD_NAME().getText();
		// Visit sub queries, who will pick up the updated field name
		Query subquery = visit(ctx.expr());
		// Reinstate previous default field name
		this.defaultFieldName = previousDefaultFieldName;
		return subquery;
	}
	
	@Override
	public Query visitSub_expr(Sub_exprContext ctx) {
		return visit(ctx.expr());
	}
	
	@Override
	/**
	 * If phraseWeight is null, don't bother the phrase weight.
	 * Restore the weight on the way back up the tree.
	 */
	public Query visitPhrase_expr(Phrase_exprContext ctx) {
		float oldPhraseBoost = currentPhraseBoost;
		if(ctx.termWeight != null)
			currentPhraseBoost = Float.parseFloat(ctx.termWeight.getText());
		Query subquery = visit(ctx.phrase());
		Query result = subquery;
		
		result = getBoostQuery(result);
		
		currentPhraseBoost = oldPhraseBoost;
		
		return result;
	}

	@Override
	public Query visitPhrase(PhraseContext ctx) {
		String value = ctx.getText();
		
		if(value != null && value.length() > 1 && (value.startsWith("\"") || value.startsWith("“")) && (value.endsWith("\"") || value.endsWith("”"))){
			value = value.replaceAll("^[\"“”]|[\"“”]$", "");
		}
		
		List<String> phraseWords = new ArrayList<String>();
		if(ctx.getChildCount() > 1){
			for(int i=0;i<ctx.getChildCount();i++){
				phraseWords.addAll(Arrays.asList(ctx.getChild(i).getText().split("(\\s|-|/|,|\\])+")));
			}
		} else {
			phraseWords.addAll(Arrays.asList(value.split("(\\s|-|/|,|\\])+")));			
		}
		
		// Regardless of whether we're in a SpanQuery, if we want to support multiple words with wildcards, we need to return a SpanQuery.
		// Normally, a PhraseQuery is good.  If we have multiple phrases in which any of them contains a wildcard,
		// we will need to fall into the SpanQuery section below.
		if(this.currentlyInASpanQuery || phraseWords.size() > 1 && (containsWildcardCharacters(value) || containsRegexPhrase(value))){
			SpanQuery[] terms = new SpanQuery[phraseWords.size()];
			for(int i=0;i<phraseWords.size();i++) {
				Term term = new Term(defaultFieldName, phraseWords.get(i));
				if(containsWildcardCharacters(phraseWords.get(i))){
					terms[i] = getSpanWildcardQuery(term);
				} else {
					terms[i] = new SpanTermQuery(term);					
				}
			}
			
			if(phraseWords.size() > 1)
				return new SpanNearQuery(terms, 0, true);
			else
				return terms[0];
		} else {
			// If we get here, then we know we don't have any multi-word phrases containing a wildcard.
			if(phraseWords.size() > 1){
				// Multiple words, no wildcard
				return new PhraseQuery(defaultFieldName, phraseWords.toArray(new String[0]));
			} else if(containsWildcardCharacters(value)) {
				// Single word, with a wildcard
				WildcardQuery wq =new WildcardQuery(new Term(defaultFieldName, phraseWords.get(0)));
				return wq;
			} else {
				// Single word, no wildcard
				TermQuery tq = new TermQuery(new Term(defaultFieldName, phraseWords.get(0)));
				return tq;
			}
		}
	}
	
	@Override
	/**
	 * If phraseWeight is null, don't bother the phrase weight.
	 * Restore the weight on the way back up the tree.
	 */
	public Query visitRegexp_expr(Regexp_exprContext ctx) {
		float oldPhraseBoost = currentPhraseBoost;
		if(ctx.termWeight != null)
			currentPhraseBoost = Float.parseFloat(ctx.termWeight.getText());
		Query subquery = visit(ctx.getChild(0));
		Query result = subquery;
		
		result = getBoostQuery(result);
		
		currentPhraseBoost = oldPhraseBoost;
		
		return result;
	}
	
	protected Query getBoostQuery(Query query){
		if(currentPhraseBoost != -1) {
			Query boostedQuery = null;
			if(query instanceof SpanQuery)
				boostedQuery = new SpanBoostQuery((SpanQuery)query, currentPhraseBoost);
			else
				boostedQuery = new BoostQuery(query, currentPhraseBoost);
			return boostedQuery;
		}
		return query;
	}

	
	@Override
	public Query visitRegexp(DtSearchParser.RegexpContext ctx) {
		String value = ctx.getText();
		
		if(value != null && value.length() > 1 && (value.startsWith("\"") || value.startsWith("“")) && (value.endsWith("\"") || value.endsWith("”"))){
			value = value.replaceAll("^[\"“”]|[\"“”]$", "");
		}
		
		Query result = null;
		if(currentlyInASpanQuery)
			result = getSpanRegexpQuery(value);
		else
			result = convertToRegexQuery(value);
		return result;
	}
	
	protected boolean containsWildcardCharacters(String value){
		if(value == null || value.length() == 0)
			return false;
		return value.contains("*") || value.contains("?");
	}
	
	protected boolean containsRegexPhrase(String phrase){
		if(phrase == null || phrase.length() == 0)
			return false;
		return phrase.startsWith("##") || phrase.contains(" ##");
	}
	
	protected boolean isRegexPhrase(String phrase){
		if(phrase == null || phrase.length() == 0)
			return false;
		return phrase.startsWith("##");
	}
	
	protected RegexpQuery convertToRegexQuery(String phrase) {
		String regexString = phrase.replaceAll("^##", "");
		Term term = new Term(defaultFieldName, regexString);
		
		RegexpQuery result = new RegexpQuery(term);//TODO: You can pass flags here--check them out to sniff out any cool new features we can support
		return result;
	}
	/*
	@Override
	public Query visitNumber(NumberContext ctx) {
		return ctx.getText();
	}
	
	
	private void ProximityNotSupportedFor(String operator) {
		if(this.currentlyInASpanQuery)
			throw new UnsupportedOperationException("The " + operator + " operator is not supported in a proximity query (W/PRE)");
	}
	
	private void ProximityWildcardsNotSupported(String queryString) {
		if(this.currentlyInASpanQuery && !queryString.startsWith("\"") && !queryString.endsWith("\"") && (queryString.contains("*") || queryString.contains("?")))
			throw new UnsupportedOperationException("The wildcard operators (*, ?) are not supported in a proximity query (W/PRE)");
	}
	
	*/
	
	@Override
	public Query visitPre_expr(Pre_exprContext ctx) {
		boolean inASpanBackup = this.currentlyInASpanQuery;
		this.currentlyInASpanQuery = true;
		SpanQuery leftQuery = (SpanQuery)visit(ctx.expr(0));
		SpanQuery rightQuery = (SpanQuery)visit(ctx.expr(1));
		this.currentlyInASpanQuery = inASpanBackup;
		
		int slop = Integer.parseInt(ctx.number().getText());
		boolean isNotW = ctx.NOT() != null;
		
		SpanQuery query = null;
		
		boolean leftQueryIsXFirstWord = false, rightQueryIsXFirstWord = false;
		if((leftQuery instanceof SpanTermQuery) && ((SpanTermQuery)leftQuery).getTerm().text().toLowerCase().equals("xfirstword")) {
			leftQueryIsXFirstWord = true;
		}
		
		if((rightQuery instanceof SpanTermQuery) && ((SpanTermQuery)rightQuery).getTerm().text().toLowerCase().equals("xfirstword")) {
			rightQueryIsXFirstWord = true;
		}
		
		if(isNotW) {
			if(leftQueryIsXFirstWord || rightQueryIsXFirstWord)
				throw new UnsupportedOperationException("xfirstword is not supported in NOT PRE/N query.");
			query = new SpanNotQuery(leftQuery, rightQuery, 0, slop);
		} else {
			if(leftQueryIsXFirstWord || rightQueryIsXFirstWord) {
				if(leftQueryIsXFirstWord) {
					query = new SpanFirstQuery(rightQuery, slop);
				} else {
					throw new UnsupportedOperationException("xfirstword is not supported as the second argument of PRE/N--that would mean that this word has to come before the first word, which isn't possible.");
				}
			} else {
				SpanQuery[] queries = {leftQuery, rightQuery};
				query = new SpanNearQuery(queries, slop, true);
			}
		}
		
		return query;
	}
	
	protected SpanQuery getSpanWildcardQuery(Term term) {
		WildcardQuery wq = new WildcardQuery(term);
	    SpanQuery swq = new SpanMultiTermQueryWrapper<>(wq);
	    return swq;
	}
	
	protected SpanQuery getSpanRegexpQuery(String phrase) {
		RegexpQuery wq = convertToRegexQuery(phrase);
	    SpanQuery swq = new SpanMultiTermQueryWrapper<>(wq);
	    return swq;
	}
	
	@Override
	public Query visitW_expr(W_exprContext ctx) {
		boolean inASpanBackup = this.currentlyInASpanQuery;
		this.currentlyInASpanQuery = true;
		SpanQuery leftQuery = (SpanQuery)visit(ctx.expr(0));
		SpanQuery rightQuery = (SpanQuery)visit(ctx.expr(1));
		this.currentlyInASpanQuery = inASpanBackup;
		
		int slop = Integer.parseInt(ctx.number().getText());
		boolean isNotW = ctx.NOT() != null;
		
		SpanQuery query = null;
		
		boolean leftQueryIsXFirstWord = false, rightQueryIsXFirstWord = false;
		if((leftQuery instanceof SpanTermQuery) && ((SpanTermQuery)leftQuery).getTerm().text().toLowerCase().equals("xfirstword")) {
			leftQueryIsXFirstWord = true;
		}
		
		if((rightQuery instanceof SpanTermQuery) && ((SpanTermQuery)rightQuery).getTerm().text().toLowerCase().equals("xfirstword")) {
			rightQueryIsXFirstWord = true;
		}
		
		if(isNotW) {
			if(leftQueryIsXFirstWord || rightQueryIsXFirstWord)
				throw new UnsupportedOperationException("xfirstword is not supported in NOT W/N query.");
			query = new SpanNotQuery(leftQuery, rightQuery, slop, slop);
		} else {
			if(leftQueryIsXFirstWord || rightQueryIsXFirstWord){
				if(leftQueryIsXFirstWord)
					query = new SpanFirstQuery(rightQuery, slop);
				else
					query = new SpanFirstQuery(leftQuery, slop);
			} else {
				SpanQuery[] queries = {leftQuery, rightQuery};
				query = new SpanNearQuery(queries, slop, false); // only difference between PRE besides ctx
			}
		}
		
		return query;
	}
	/*
	@Override
	public Query visitTerminal(TerminalNode node) {
		return " terminal node(" + node.getText()+")";
	}
	
	@Override
	public Query visitQuery(QueryContext ctx) {
		return super.visitQuery(ctx);
	}
	
	@Override
	public Query visitErrorNode(ErrorNode node) {
		return super.visitErrorNode(node);
	}
	
	@Override
	public Query visitChildren(RuleNode ruleNode) {
		return super.visitChildren(ruleNode);
	}
	*/
}
