package com.millersoft.solr.parsers.dtsearch.tests.search;

import org.apache.lucene.search.Query;
import org.junit.Test;

import com.millersoft.solr.parsers.tests.millersoftTestCase;

public class TestAdvancedProximity extends millersoftTestCase {
	@Test
	public void testSpanComplex1() throws Exception {
		Query query = getParseResultQuery("field", "(six pre/0 hundred OR seven pre/0 hundred) pre/100 (seven or six)");
		/*
	    SpanQuery tt1 = spanNearOrderedQuery("field", 0, "six", "hundred");
	    SpanQuery tt2 = spanNearOrderedQuery("field", 0, "seven", "hundred");

	    SpanQuery to1 = spanOrQuery(tt1, tt2);
	    SpanQuery to2 = spanOrQuery("field", "seven", "six");
	    SpanQuery query = spanNearOrderedQuery(100, to1, to2);
	    */
	    checkHits(query, new int[]
	      {606, 607, 626, 627, 636, 637, 646, 647, 656, 657, 666, 667, 676, 677, 686, 687, 696,
	              697, 706, 707, 726, 727, 736, 737, 746, 747, 756, 757,
	              766, 767, 776, 777, 786, 787, 796, 797, 1606, 1607, 1626, 1627, 1636, 1637, 1646,
	              1647, 1656, 1657,
	              1666, 1667, 1676, 1677, 1686, 1687, 1696, 1697, 1706, 1707, 1726, 1727, 1736, 1737,
	              1746, 1747, 1756, 1757, 1766, 1767, 1776, 1777, 1786, 1787, 1796, 1797});
	  }
	  
/* THESE ARE TESTS THAT DTSEARCH EITHER DOESN'T SUPPORT OR WE HAVEN'T IMPLEMENTED YET

@Test
	  public void testSpanWithMultipleNotSingle() throws Exception {
	    SpanQuery near = spanNearOrderedQuery("field", 4, "eight", "one");
	    SpanQuery or = spanOrQuery("field", "forty");
	    SpanQuery query = spanNotQuery(near, or);

	    checkHits(query, new int[]
	      {801, 821, 831, 851, 861, 871, 881, 891,
	              1801, 1821, 1831, 1851, 1861, 1871, 1881, 1891});

	    assertTrue(searcher.explain(query, 801).getValue() > 0.0f);
	    assertTrue(searcher.explain(query, 891).getValue() > 0.0f);
	  }

@Test
	  public void testSpanWithMultipleNotMany() throws Exception {
	    SpanQuery near = spanNearOrderedQuery("field", 4, "eight", "one");
	    SpanQuery or = spanOrQuery("field", "forty", "sixty", "eighty");
	    SpanQuery query = spanNotQuery(near, or);

	    checkHits(query, new int[]
	      {801, 821, 831, 851, 871, 891, 1801, 1821, 1831, 1851, 1871, 1891});

	    assertTrue(searcher.explain(query, 801).getValue() > 0.0f);
	    assertTrue(searcher.explain(query, 891).getValue() > 0.0f);
	  }

@Test
	  public void testNpeInSpanNearWithSpanNot() throws Exception {
	    SpanQuery near = spanNearOrderedQuery("field", 4, "eight", "one");
	    SpanQuery exclude = spanNearOrderedQuery("field", 1, "hundred", "forty");
	    SpanQuery query = spanNotQuery(near, exclude);

	    checkHits(query, new int[]
	      {801, 821, 831, 851, 861, 871, 881, 891,
	              1801, 1821, 1831, 1851, 1861, 1871, 1881, 1891});

	    assertTrue(searcher.explain(query, 801).getValue() > 0.0f);
	    assertTrue(searcher.explain(query, 891).getValue() > 0.0f);
	  }

@Test
	  public void testNpeInSpanNearInSpanFirstInSpanNot() throws Exception {
	    final int n = 5;
	    SpanQuery include = spanFirstQuery(spanTermQuery("field", "forty"), n);
	    SpanQuery near = spanNearOrderedQuery("field", n-1, "hundred", "forty");
	    SpanQuery exclude = spanFirstQuery(near, n-1);
	    SpanQuery q = spanNotQuery(include, exclude);
	    
	    checkHits(q, new int[]{40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 1040, 1041, 1042, 1043, 1044, 1045, 1046, 1047, 1048,
	            1049, 1140, 1141, 1142, 1143, 1144, 1145, 1146, 1147, 1148, 1149, 1240, 1241, 1242, 1243, 1244,
	            1245, 1246, 1247, 1248, 1249, 1340, 1341, 1342, 1343, 1344, 1345, 1346, 1347, 1348, 1349, 1440, 1441, 1442,
	            1443, 1444, 1445, 1446, 1447, 1448, 1449, 1540, 1541, 1542, 1543, 1544, 1545, 1546, 1547, 1548, 1549, 1640,
	            1641, 1642, 1643, 1644, 1645, 1646, 1647,
	            1648, 1649, 1740, 1741, 1742, 1743, 1744, 1745, 1746, 1747, 1748, 1749, 1840, 1841, 1842, 1843, 1844, 1845, 1846,
	            1847, 1848, 1849, 1940, 1941, 1942, 1943, 1944, 1945, 1946, 1947, 1948, 1949});
	  }
	  
@Test
	  public void testSpanNotWindowOne() throws Exception {
	    SpanQuery near = spanNearOrderedQuery("field", 4, "eight", "forty");
	    SpanQuery query = spanNotQuery(near, spanTermQuery("field", "one"), 1, 1);

	    checkHits(query, new int[]
	      {840, 842, 843, 844, 845, 846, 847, 848, 849,
	          1840, 1842, 1843, 1844, 1845, 1846, 1847, 1848, 1849});

	    assertTrue(searcher.explain(query, 840).getValue() > 0.0f);
	    assertTrue(searcher.explain(query, 1842).getValue() > 0.0f);
	  }
	  
@Test
	  public void testSpanNotWindowTwoBefore() throws Exception {
	    SpanQuery near = spanNearOrderedQuery("field", 4, "eight", "forty");
	    SpanQuery query = spanNotQuery(near, spanTermQuery("field", "one"), 2, 0);

	    checkHits(query, new int[]
	      {840, 841, 842, 843, 844, 845, 846, 847, 848, 849});

	    assertTrue(searcher.explain(query, 840).getValue() > 0.0f);
	    assertTrue(searcher.explain(query, 849).getValue() > 0.0f);
	  }

@Test
	  public void testSpanNotWindowNeg() throws Exception {
	    //test handling of invalid window < 0
	    SpanQuery near = spanNearOrderedQuery("field", 4, "eight", "one");
	    SpanQuery or = spanOrQuery("field", "forty");
	    SpanQuery query = spanNotQuery(near, or);

	    checkHits(query, new int[]
	       {801, 821, 831, 851, 861, 871, 881, 891,
	               1801, 1821, 1831, 1851, 1861, 1871, 1881, 1891});

	    assertTrue(searcher.explain(query, 801).getValue() > 0.0f);
	    assertTrue(searcher.explain(query, 891).getValue() > 0.0f);
	  }
	  
@Test
	  public void testSpanNotWindowDoubleExcludesBefore() throws Exception {
	    //test hitting two excludes before an include
	    SpanQuery near = spanNearOrderedQuery("field", 2, "forty", "two");
	    SpanQuery exclude = spanTermQuery("field", "one");
	    SpanQuery query = spanNotQuery(near, exclude, 4, 1);

	    checkHits(query, new int[]
	       {42, 242, 342, 442, 542, 642, 742, 842, 942});

	    assertTrue(searcher.explain(query, 242).getValue() > 0.0f);
	    assertTrue(searcher.explain(query, 942).getValue() > 0.0f);
	  }

@Test
	  public void testSpanFirst() throws Exception {
	    SpanQuery query = spanFirstQuery(spanTermQuery("field", "five"), 1);

	    checkHits(query, new int[]
	      {5, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513,
	       514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527,
	       528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541,
	       542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555,
	       556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569,
	       570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583,
	       584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597,
	       598, 599});

	    assertTrue(searcher.explain(query, 5).getValue() > 0.0f);
	    assertTrue(searcher.explain(query, 599).getValue() > 0.0f);

	  }

@Test
	  public void testSpanPositionRange() throws Exception {
	    SpanQuery term1 = spanTermQuery("field", "five");
	    SpanQuery query = spanPositionRangeQuery(term1, 1, 2);
	    
	    checkHits(query, new int[]
	      {25,35, 45, 55, 65, 75, 85, 95});
	    assertTrue(searcher.explain(query, 25).getValue() > 0.0f);
	    assertTrue(searcher.explain(query, 95).getValue() > 0.0f);

	    query = spanPositionRangeQuery(term1, 0, 1);
	    checkHits(query, new int[]
	      {5, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512,
	              513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525,
	              526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538,
	              539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551,
	              552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564,
	              565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577,
	              578, 579, 580, 581, 582, 583, 584,
	              585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597,
	              598, 599});

	    query = spanPositionRangeQuery(term1, 6, 7);
	    checkHits(query, new int[]{});
	  }	  
	  */
}
