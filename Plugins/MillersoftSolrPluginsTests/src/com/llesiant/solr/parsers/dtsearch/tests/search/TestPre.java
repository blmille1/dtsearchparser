package com.millersoft.solr.parsers.dtsearch.tests.search;

import javax.naming.OperationNotSupportedException;

import org.apache.lucene.search.Query;
import org.junit.Test;

import com.millersoft.solr.parsers.tests.millersoftTestCase;

public class TestPre extends millersoftTestCase {
	
	@Test
	public void testPreExact() throws Exception {
		Query query = getParseResultQuery("field", "seventy pre/0 seven");
	    
	    checkHits(query, new int[]
	      {77, 177, 277, 377, 477, 577, 677, 777, 877, 977, 1077, 1177, 1277, 1377, 1477, 1577, 1677, 1777, 1877, 1977});

	    assertTrue(searcher.explain(query, 77).getValue() > 0.0f);
	    assertTrue(searcher.explain(query, 977).getValue() > 0.0f);
	  }
	
	@Test
	public void testPre() throws Exception {
		Query query = getParseResultQuery("field", "nine pre/4 six");
	    checkHits(query, 
	              new int[] { 906, 926, 936, 946, 956, 966, 976, 986, 996, 1906, 1926, 
	                          1936, 1946, 1956, 1966, 1976, 1986, 1996});
	  }
	
	@Test
	public void testPreOr() throws Exception {
		Query query = getParseResultQuery("field", "thirty pre/0 three Or forty pre/0 seven");

	    checkHits(query, new int[]
	      {33, 47, 133, 147, 233, 247, 333, 347, 433, 447, 533, 547, 633, 647, 733,
	              747, 833, 847, 933, 947, 1033, 1047, 1133, 1147, 1233, 1247, 1333,
	              1347, 1433, 1447, 1533, 1547, 1633, 1647, 1733, 1747, 1833, 1847, 1933, 1947});

	    assertTrue(searcher.explain(query, 33).getValue() > 0.0f);
	    assertTrue(searcher.explain(query, 947).getValue() > 0.0f);
	}
	
	@Test
	public void testPreAndWithResults() throws Exception {
		Query query = getParseResultQuery("field", "(\"one thousand one hundred\" AND twenty) pre/1 (two or five or seven)");
	    
		checkHits(query, new int[] {1122, 1125, 1127});
	}
	
	@Test
	public void testPreAndWithResults2() throws Exception {
		Query query = getParseResultQuery("field", "one pre/1 (\"one hundred\" AND twenty) pre/1 (two or five or seven)");
	    
		checkHits(query, new int[] {1122, 1125, 1127});
	}
	
	@Test
	public void testPreExactNested() throws Exception {
		Query query = getParseResultQuery("field", "three pre/0 hundred pre/0 thirty pre/0 three");
	    
	    checkHits(query, new int[] {333, 1333});

	    assertTrue(searcher.explain(query, 333).getValue() > 0.0f);
	}
	
	@Test
	public void testPreQuotedLiteralWithResults() throws Exception {
		Query query = getParseResultQuery("field", "\"one thousand one hundred twenty\" pre/1 (seven or twelve)");
	    
		checkHits(query, new int[] {1127});
	}

	@Test
	public void testPreOr2() throws Exception {
	  Query query = getParseResultQuery("field", "(six or seven) pre/10 (seven or six)");
	    
	  checkHits(query, new int[]
	      {606, 607, 626, 627, 636, 637, 646, 647, 656, 657, 666, 667, 676, 677,
	              686, 687, 696, 697, 706, 707, 726, 727, 736, 737, 746, 747, 756,
	              757, 766, 767, 776, 777, 786, 787, 796, 797, 1606, 1607, 1626,
	              1627, 1636, 1637, 1646, 1647, 1656, 1657, 1666, 1667, 1676, 1677,
	              1686, 1687, 1696, 1697, 1706, 1707, 1726, 1727, 1736, 1737,
	              1746, 1747, 1756, 1757, 1766, 1767, 1776, 1777, 1786, 1787, 1796,
	              1797});
	}
	
	@Test
	public void testNotPre1QuotedLiteralWithResults() throws Exception {
		Query query = getParseResultQuery("field", "\"one thousand one hundred twenty\" not pre/1 (seven or three)");
	    
		checkHits(query, new int[] {1120, 1121, 1122,      1124, 1125, 1126,       1128, 1129 });
	}
	
	@Test
	public void testNotPre5QuotedLiteralWithResults() throws Exception {
		Query query = getParseResultQuery("field", "not thousand and \"two hundred\" not pre/5 (zero or one or three or four or five or six or seven or eight or nine or ten or eleven or twelve or thirteen or fourteen or fifteen or sixteen or seventeen or eighteen or nineteen or twenty or thirty or forty or fifty or sixty or seventy or eighty)");
	    
		checkHits(query, new int[] {200, 202, 290, 292});
	}
	
	/* XFIRSTWORD */
	@Test
	public void testXFirstWordPre1TwelveWithResults() throws Exception {
		Query query = getParseResultQuery("field", "XFIRSTworD pre/1 twelve");
	    
		checkHits(query, new int[] {12});
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testTwelvePre1XFirstWordWithResults() throws Exception {
		Query query = getParseResultQuery("field", "twelve Pre/1 XFIRSTworD");
	    
		checkHits(query, new int[] {12});
	}
	
	@Test
	public void testXFirstWordPre2TwelveWithResults() throws Exception {
		Query query = getParseResultQuery("field", "XFIRSTworD PRE/2 twelve");
	    
		checkHits(query, new int[] {12});
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testTwelvePre2XFirstWordWithResults() throws Exception {
		Query query = getParseResultQuery("field", "twelve prE/2 XFIRSTworD");
	}
	
	@Test
	public void testXFirstWordPre3TwelveWithResults() throws Exception {
		Query query = getParseResultQuery("field", "XFIRSTworD pre/3 twelve");
	    
		checkHits(query, new int[] {12, 112, 212, 312, 412, 512, 612, 712, 812, 912, 1012});
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testTwelvePre3XFirstWordWithResults() throws Exception {
		Query query = getParseResultQuery("field", "twelve pre/3 XFIRSTworD");
	    
		checkHits(query, new int[] {12, 112, 212, 312, 412, 512, 612, 712, 812, 912, 1012});
	}
	
	
	/* WILDCARD TESTS */
	@Test
	public void testWildcardStarNoDifferenceWithoutStarWithResults() throws Exception {
		Query query = getParseResultQuery("field", "xfirstword pre/3 twelve*");
	    
		checkHits(query, new int[] {12, 112, 212, 312, 412, 512, 612, 712, 812, 912, 1012});
	}
	
	@Test
	public void testWildcardStarNoDifferenceWithoutStarWithResults2() throws Exception {
		Query query = getParseResultQuery("field", "xfirstword pre/3 twel*");
	    
		checkHits(query, new int[] {12, 112, 212, 312, 412, 512, 612, 712, 812, 912, 1012});
	}
	
	@Test
	public void testWildcardQuestionMarkWithResults() throws Exception {
		Query query = getParseResultQuery("field", "xfirstword pre/3 twelv?");
	    
		checkHits(query, new int[] {12, 112, 212, 312, 412, 512, 612, 712, 812, 912, 1012});
	}
	
	@Test
	public void testWildcardQuestionMarksAndStarsWithResults() throws Exception {
		Query query = getParseResultQuery("field", "??? pre/1 (\"hundred forty\" pre/0 six*)");
	    
		checkHits(query, new int[] {146, 246, 646, 1146, 1246, 1646});
	}
	
	/* NOT tests*/
	@Test
	public void testAndNotSubqueryWithResults() throws Exception {
		Query query = getParseResultQuery("field", "\"one thousand one hundred\" pre/0 (thirty and not (six or seven))");
	    
		checkHits(query, new int[] {1130, 1131, 1132, 1133, 1134, 1135,             1138, 1139});
	}
	
	@Test
	public void testOrNotSubqueryWithResults() throws Exception {
		Query query = getParseResultQuery("field", "\"one thousand one hundred\" pre/0 (thirty or not (two or three or four or five or six or seven or eight or nine or ten or eleven or twelve or thirteen or fourteen or fifteen or sixteen or seventeen or eighteen or nineteen or twenty or forty or fifty))");
	    
		checkHits(query, new int[] {
				1101,
				1130, 1131, 1132, 1133, 1134, 1135, 1136, 1137, 1138, 1139,
				1160, 1161,
				1170, 1171,
				1180, 1181,
				1190, 1191
				});
	}
	
	@Test
	public void testAndNotOrNotSubqueryWithResults() throws Exception {
		Query query = getParseResultQuery("field", "\"one thousand one hundred\" pre/0 ((thirty and not (six or seven)) or not (two or three or four or five or six or seven or eight or nine or ten or eleven or twelve or thirteen or fourteen or fifteen or sixteen or seventeen or eighteen or nineteen or twenty or forty or fifty))");
	    
		checkHits(query, new int[] {
				1101,
				1130, 1131, 1132, 1133, 1134, 1135,             1138, 1139,
				1160, 1161,
				1170, 1171,
				1180, 1181,
				1190, 1191
				});
	}
	
	/*
	  @Test
	  public void testSpanNot() throws Exception {
		Query query = getParseResultQuery("field", "(eight pre/4 one) ????");
//	    SpanQuery near = spanNearOrderedQuery("field", 4, "eight", "one");
//	    SpanQuery query = spanNotQuery(near, spanTermQuery("field", "forty"));

	    checkHits(query, new int[]
	      {801, 821, 831, 851, 861, 871, 881, 891, 1801, 1821, 1831, 1851, 1861, 1871, 1881, 1891});

	    assertTrue(searcher.explain(query, 801).getValue() > 0.0f);
	    assertTrue(searcher.explain(query, 891).getValue() > 0.0f);
	  }
	  */
}
