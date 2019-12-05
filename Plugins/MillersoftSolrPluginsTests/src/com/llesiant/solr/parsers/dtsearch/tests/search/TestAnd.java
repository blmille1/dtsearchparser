package com.millersoft.solr.parsers.dtsearch.tests.search;

import org.apache.lucene.search.Query;
import org.junit.Test;

import com.millersoft.solr.parsers.tests.millersoftTestCase;

public class TestAnd extends millersoftTestCase {
	@Test
	public void testTwoWithResults() throws Exception {
		Query query = getParseResultQuery("field", "seventy aNd seven");
		
	    checkHits(query, new int[]
	      {77, 177, 277, 377, 477, 577, 677, 770, 771, 772, 773, 774, 775, 776, 777,
	              778, 779, 877, 977, 1077, 1177, 1277, 1377, 1477, 1577, 1677,
	              1770, 1771, 1772, 1773, 1774, 1775, 1776, 1777, 1778, 1779, 1877,
	              1977});
	  }

	  @Test
	  public void testTwoWithoutResults() throws Exception {
		Query query = getParseResultQuery("field", "sevento anD sevenly");
	    
	    checkHits(query, new int[] {});
	  }
	  
	  @Test
	  public void testThreeWithResults() throws Exception {
			Query query = getParseResultQuery("field", "thousand AnD seventy aNd seven");
			
		    checkHits(query, new int[]
		      { 1077, 1177, 1277, 1377, 1477, 1577, 1677, 1770, 1771, 1772,
		        1773, 1774, 1775, 1776, 1777, 1778, 1779, 1877, 1977});
		  }
}
