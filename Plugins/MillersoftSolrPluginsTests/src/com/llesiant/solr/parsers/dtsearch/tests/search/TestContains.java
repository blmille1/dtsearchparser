package com.millersoft.solr.parsers.dtsearch.tests.search;

import org.apache.lucene.search.Query;
import org.junit.Test;

import com.millersoft.solr.parsers.tests.millersoftTestCase;

public class TestContains extends millersoftTestCase {
	@Test
	public void testContainsWithResults() throws Exception {
		Query query = getParseResultQuery("field", "reverse contains orez");
		
	    checkHits(query, new int[]{0});
	}
	
	@Test
	public void testContainsSubqueryWithResults() throws Exception {
		Query query = getParseResultQuery("field", "field contains(seventy aNd seven) and reverse contains(ytneves AND neves)");
		
	    checkHits(query, new int[]
	      {77, 177, 277, 377, 477, 577, 677, 770, 771, 772, 773, 774, 775, 776, 777,
	              778, 779, 877, 977, 1077, 1177, 1277, 1377, 1477, 1577, 1677,
	              1770, 1771, 1772, 1773, 1774, 1775, 1776, 1777, 1778, 1779, 1877,
	              1977});
	}
	
	@Test
	public void testDoulbeColonWithResults() throws Exception {
		Query query = getParseResultQuery("field", "reverse::orez");
		
	    checkHits(query, new int[]{0});
	}
	
	@Test
	public void testDoubleColonSubqueryWithResults() throws Exception {
		Query query = getParseResultQuery("field", "field::(seventy aNd seven) and reverse::(ytneves AND neves)");
		
	    checkHits(query, new int[]
	      {77, 177, 277, 377, 477, 577, 677, 770, 771, 772, 773, 774, 775, 776, 777,
	              778, 779, 877, 977, 1077, 1177, 1277, 1377, 1477, 1577, 1677,
	              1770, 1771, 1772, 1773, 1774, 1775, 1776, 1777, 1778, 1779, 1877,
	              1977});
	}
	
	public void testDoubleColonQuotedWithResults() throws Exception {
		Query query = getParseResultQuery("field", "field::\"one thousand two hundred fifty seven\" and reverse::\"neves ytfif derdnuh owt dnasuoht eno\"");
		
	    checkHits(query, new int[]{1257});
	}
	
	public void testDoubleColonQuotedWithoutResults() throws Exception {
		Query query = getParseResultQuery("field", "field::\"one thousand two hundred fifty seven\" and reverse::\"xx neves ytfif derdnuh owt dnasuoht eno\"");
		
	    checkHits(query, new int[]{});
	}
}
