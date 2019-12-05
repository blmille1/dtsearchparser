package com.millersoft.solr.parsers.dtsearch.tests.search;

import org.apache.lucene.search.Query;
import org.junit.Test;

import com.millersoft.solr.parsers.tests.millersoftTestCase;

public class TestNot extends millersoftTestCase {
	@Test
	public void testAndNotWithResults() throws Exception {
		Query query = getParseResultQuery("field", "\"one thousand one hundred twenty\" and not five");
	    
		checkHits(query, new int[] {1120,1121,1122,1123,1124,1126,1127,1128,1129});
	}
	
	@Test
	public void testAndNotWithoutResults() throws Exception {
		Query query = getParseResultQuery("field", "sevento anD not sevenly");
	    
		checkHits(query, new int[] {});
	}
	
	@Test
	public void testOrNotWithResults() throws Exception {
		Query query = getParseResultQuery("field", "\"one thousand one hundred twelve\" or not (zero or one or two or three or four or five or six or seven or eight or nine or ten or eleven or thirteen or fourteen or fifteen or sixteen or seventeen or eighteen or nineteen or twenty or thirty or forty or fifty or sixty or seventy or eighty or ninety or hundred or thousand)"); // or not missing twelve ;)
		
		checkHits(query, new int[] {1112, 12});
	}
	
	@Test
	public void testOrNotWithResultsButNotFromTheOrNotPart() throws Exception {
		Query query = getParseResultQuery("field", "\"one thousand one hundred twelve\" or not (zero or one or two or three or four or five or six or seven or eight or nine or ten or eleven or twelve or thirteen or fourteen or fifteen or sixteen or seventeen or eighteen or nineteen or twenty or thirty or forty or fifty or sixty or seventy or eighty or ninety or hundred or thousand)");
	    
		checkHits(query, new int[] {1112});
	}
	
	@Test
	public void testOrNotWithoutResults() throws Exception {
		Query query = getParseResultQuery("field", "sevento or not (zero or one or two or three or four or five or six or seven or eight or nine or ten or eleven or twelve or thirteen or fourteen or fifteen or sixteen or seventeen or eighteen or nineteen or twenty or thirty or forty or fifty or sixty or seventy or eighty or ninety or hundred or thousand)");
	    
		checkHits(query, new int[] {});
	}
	
	@Test
	public void testPreNotWithResults() throws Exception {
		Query query = getParseResultQuery("field", "\"thousand three hundred twenty\" pre/50 not (two or four or nine)");
		checkHits(query, new int[] {1321,     1323,     1325,1326,1327,1328     });
	}
	
	@Test
	public void testWNotWithResults() throws Exception {
		Query query = getParseResultQuery("field", "(one pre/0 thousand pre/0 one pre/0 hundred pre/0 twenty) w/50 not (two or three or four or five or six or eight or nine)");
	    
		checkHits(query, new int[] {1120, 1121, 1127});
	}
	
	@Test
	public void testNotWWithResults() throws Exception {
		Query query = getParseResultQuery("field", "not ((thousand or hundred or ninety or eighty or seventy or sixty or fifty or forty or thirty or twenty) w/50 (one or two or three or four or five or six or seven or eight or nine))");
	    
		checkHits(query, new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40, 50, 60, 70, 80, 90});
	}
	
	@Test
	public void testNotPreWithResults() throws Exception {
		Query query = getParseResultQuery("field", "not ((thousand or hundred or ninety or eighty or seventy or sixty or fifty or forty or thirty or twenty) pre/50 (one or two or three or four or five or six or seven or eight or nine or ten or eleven or twelve or thirteen or fourteen or fifteen or sixteen or seventeen or eighteen or nineteen or twenty or thirty or forty or fifty or sixty or seventy or eighty or ninety))");
	    
		checkHits(query, new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000});
	}
	
	@Test
	public void testCrazyNotPreWithoutResults() throws Exception {
		Query query = getParseResultQuery("field", "not ((thousand or hundred or ninety or eighty or seventy or sixty or fifty or forty or thirty or twenty) pre/50 (one or two or three or four or five or six or seven or eight or nine or ten or eleven or twelve or thirteen or fourteen or fifteen or sixteen or seventeen or eighteen or nineteen or twenty or thirty or forty or fifty or sixty or seventy or eighty or ninety)) and not (zero or one or two or three or four or five or six or seven or eight or nine or ten or eleven or twelve or thirteen or fourteen or fifteen or sixteen or seventeen or eighteen or nineteen or twenty or thirty or forty or fifty or sixty or seventy or eighty or ninety or \"one hundred\" or \"two hundred\" or \"three hundred\" or \"four hundred\" or \"five hundred\" or \"six hundred\" or \"seven hundred\" or \"eight hundred\" or \"nine hundred\" or \"onen thousand\")");
	    
		checkHits(query, new int[] {});
	}
	
	
}
