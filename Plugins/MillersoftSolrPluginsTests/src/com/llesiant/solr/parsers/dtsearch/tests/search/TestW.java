package com.millersoft.solr.parsers.dtsearch.tests.search;

import org.apache.lucene.search.Query;
import org.junit.Test;

import com.millersoft.solr.parsers.tests.millersoftTestCase;

public class TestW extends millersoftTestCase{
	@Test
	public void testW4() throws Exception {
		Query query = getParseResultQuery("field", "nine w/4 six");
	    checkHits(query, 
	              new int[] { 609, 629, 639, 649, 659, 669, 679, 689, 699, 906, 926, 936, 946, 956,
	              966, 976, 986, 996, 1609, 1629, 1639, 1649, 1659, 1669,
	              1679, 1689, 1699, 1906, 1926, 1936, 1946, 1956, 1966, 1976, 1986,
	              1996});
	}
	
	@Test
	public void testWQuotedLiteralWithResults() throws Exception {
		Query query = getParseResultQuery("field", "\"one thousand one hundred twenty\" w/1 (two or five or seven)");
	    
		checkHits(query, new int[] {1122, 1125, 1127});
	}
	
	@Test
	public void testWQuotedLiteralWithResults2() throws Exception {
		// NOTE: w/ span matches can overlap, like this one
		Query query = getParseResultQuery("field", "\"one thousand one hundred twenty\" w/1 (one or five or seven)");
	    // See how it matches 112[0-9]--because one on the RHS is matching on the LHS
		checkHits(query, new int[] {1120, 1121, 1122, 1123, 1124, 1125, 1126, 1127, 1128, 1129});
	}
	
	@Test
	public void testWOrWithResults() throws Exception {
		Query query = getParseResultQuery("field", "(\"one thousand one hundred twenty\" Or \"one thousand one hundred ninety\") w/1 (two or five or seven)");
	    
		checkHits(query, new int[] {1122, 1125, 1127,     1192, 1195, 1197});
	}
	
	@Test
	public void testWAndWithResults() throws Exception {
		Query query = getParseResultQuery("field", "(\"one thousand one hundred\" AND twenty) w/1 (two or five or seven)");
	    
		checkHits(query, new int[] {1122, 1125, 1127});
	}
	
	@Test
	public void testNotW5QuotedLiteralWithResults() throws Exception {
		Query query = getParseResultQuery("field", "\"two hundred\" not w/5 (zero or one or three or four or five or six or seven or eight or nine or ten or eleven or twelve or thirteen or fourteen or fifteen or sixteen or seventeen or eighteen or nineteen or twenty or thirty or forty or fifty or sixty or seventy or eighty)");
	    
		checkHits(query, new int[] {200, 202, 290, 292});
	}
	
	@Test
	public void testXFirstWordW1TwelveWithResults() throws Exception {
		Query query = getParseResultQuery("field", "XFIRSTworD w/1 twelve");
	    
		checkHits(query, new int[] {12});
	}
	
	@Test
	public void testTwelveW1XFirstWordWithResults() throws Exception {
		Query query = getParseResultQuery("field", "twelve w/1 XFIRSTworD");
	    
		checkHits(query, new int[] {12});
	}
	
	@Test
	public void testXFirstWordW2TwelveWithResults() throws Exception {
		Query query = getParseResultQuery("field", "XFIRSTworD w/2 twelve");
	    
		checkHits(query, new int[] {12});
	}
	
	@Test
	public void testTwelveW2XFirstWordWithResults() throws Exception {
		Query query = getParseResultQuery("field", "twelve w/2 XFIRSTworD");
	    
		checkHits(query, new int[] {12});
	}
	
	@Test
	public void testXFirstWordW3TwelveWithResults() throws Exception {
		Query query = getParseResultQuery("field", "XFIRSTworD w/3 twelve");
	    
		checkHits(query, new int[] {12, 112, 212, 312, 412, 512, 612, 712, 812, 912, 1012});
	}
	
	@Test
	public void testTwelveW3XFirstWordWithResults() throws Exception {
		Query query = getParseResultQuery("field", "twelve w/3 XFIRSTworD");
	    
		checkHits(query, new int[] {12, 112, 212, 312, 412, 512, 612, 712, 812, 912, 1012});
	}
	
	/* WILDCARD TESTS */
	@Test
	public void testWildcardStarNoDifferenceWithoutStarWithResults() throws Exception {
		Query query = getParseResultQuery("field", "twelve* w/3 XFIRSTworD");
	    
		checkHits(query, new int[] {12, 112, 212, 312, 412, 512, 612, 712, 812, 912, 1012});
	}
	
	@Test
	public void testWildcardStarNoDifferenceWithoutStarWithResults2() throws Exception {
		Query query = getParseResultQuery("field", "twel* w/3 XFIRSTworD");
	    
		checkHits(query, new int[] {12, 112, 212, 312, 412, 512, 612, 712, 812, 912, 1012});
	}
	
	@Test
	public void testWildcardQuestionMarkWithResults() throws Exception {
		Query query = getParseResultQuery("field", "twelv? w/3 XFIRSTworD");
	    
		checkHits(query, new int[] {12, 112, 212, 312, 412, 512, 612, 712, 812, 912, 1012});
	}
	
	@Test
	public void testWildcardQuestionMarksAndStarsWithResults() throws Exception {
		Query query = getParseResultQuery("field", "??? w/1 (\"hundred forty\" pre/0 six*)");
	    
		checkHits(query, new int[] {146, 246, 346, 446, 546, 646, 746, 846, 946, 1146, 1246, 1346, 1446, 1546, 1646, 1746, 1846, 1946});
	}
	
	/* NOT tests*/
	@Test
	public void testAndNotSubqueryWithResults() throws Exception {
		Query query = getParseResultQuery("field", "\"one hundred\" w/5 (thousand and not (two or three or four or five or six or seven or eight or nine or ten or eleven or twelve or thirteen or fourteen or fifteen or sixteen or seventeen or eighteen or nineteen or twenty or forty or fifty or sixty or seventy or eighty or ninety))");
	    
		checkHits(query, new int[] {1100, 1101, 1130, 1131});
	}
	
	@Test
	public void testOrNotSubqueryWithResults() throws Exception {
		Query query = getParseResultQuery("field", "\"twenty five\" w/2 (one w/1 (thousand pre/0 (four or not (six or three or nine)) pre/0 hundred))");
	    
		checkHits(query, new int[] {
				1125, 1225, 1425, 1525, 1725, 1825
				});
	}
}
