package com.millersoft.solr.parsers.dtsearch.tests.search;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.junit.Test;

import com.millersoft.solr.parsers.tests.millersoftTestCase;

public class TestPhrase extends millersoftTestCase {
	@Test
	public void testTerm() throws Exception {
	    Query query = getParseResultQuery("field", "seventy");
	    checkHits(query, new int[]
	      {70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 170, 171, 172, 173, 174, 175,
	              176, 177, 178, 179, 270, 271, 272, 273, 274, 275, 276, 277, 278,
	              279, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 470, 471,
	              472, 473, 474, 475, 476, 477, 478, 479, 570, 571, 572, 573, 574,
	              575, 576, 577, 578, 579, 670, 671, 672, 673, 674, 675, 676, 677,
	              678, 679, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 870,
	              871, 872, 873, 874, 875, 876, 877, 878, 879, 970, 971, 972, 973,
	              974, 975, 976, 977, 978, 979, 1070, 1071, 1072, 1073, 1074, 1075,
	              1076, 1077, 1078, 1079, 1170, 1171, 1172, 1173, 1174, 1175, 1176,
	              1177, 1178, 1179, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277,
	              1278, 1279, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378,
	              1379, 1470, 1471, 1472, 1473, 1474, 1475, 1476, 1477, 1478, 1479,
	              1570, 1571, 1572, 1573, 1574, 1575, 1576, 1577, 1578, 1579, 1670,
	              1671, 1672, 1673, 1674, 1675, 1676, 1677, 1678, 1679, 1770, 1771,
	              1772, 1773, 1774, 1775, 1776, 1777, 1778, 1779, 1870, 1871, 1872,
	              1873, 1874, 1875, 1876, 1877,
	              1878, 1879, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978,
	              1979});
	}
	
	@Test
	public void testTermWithPostWildcardStar() throws Exception {
	    Query query = getParseResultQuery("field", "seventy*");
	    checkHits(query, new int[]
	      {70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 170, 171, 172, 173, 174, 175,
	              176, 177, 178, 179, 270, 271, 272, 273, 274, 275, 276, 277, 278,
	              279, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 470, 471,
	              472, 473, 474, 475, 476, 477, 478, 479, 570, 571, 572, 573, 574,
	              575, 576, 577, 578, 579, 670, 671, 672, 673, 674, 675, 676, 677,
	              678, 679, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 870,
	              871, 872, 873, 874, 875, 876, 877, 878, 879, 970, 971, 972, 973,
	              974, 975, 976, 977, 978, 979, 1070, 1071, 1072, 1073, 1074, 1075,
	              1076, 1077, 1078, 1079, 1170, 1171, 1172, 1173, 1174, 1175, 1176,
	              1177, 1178, 1179, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277,
	              1278, 1279, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378,
	              1379, 1470, 1471, 1472, 1473, 1474, 1475, 1476, 1477, 1478, 1479,
	              1570, 1571, 1572, 1573, 1574, 1575, 1576, 1577, 1578, 1579, 1670,
	              1671, 1672, 1673, 1674, 1675, 1676, 1677, 1678, 1679, 1770, 1771,
	              1772, 1773, 1774, 1775, 1776, 1777, 1778, 1779, 1870, 1871, 1872,
	              1873, 1874, 1875, 1876, 1877,
	              1878, 1879, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978,
	              1979});
	}
	
	@Test
	public void testTermWithPreWildcardStar() throws Exception {
	    Query query = getParseResultQuery("field", "*eventy");
	    checkHits(query, new int[]
	      {70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 170, 171, 172, 173, 174, 175,
	              176, 177, 178, 179, 270, 271, 272, 273, 274, 275, 276, 277, 278,
	              279, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 470, 471,
	              472, 473, 474, 475, 476, 477, 478, 479, 570, 571, 572, 573, 574,
	              575, 576, 577, 578, 579, 670, 671, 672, 673, 674, 675, 676, 677,
	              678, 679, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 870,
	              871, 872, 873, 874, 875, 876, 877, 878, 879, 970, 971, 972, 973,
	              974, 975, 976, 977, 978, 979, 1070, 1071, 1072, 1073, 1074, 1075,
	              1076, 1077, 1078, 1079, 1170, 1171, 1172, 1173, 1174, 1175, 1176,
	              1177, 1178, 1179, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277,
	              1278, 1279, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378,
	              1379, 1470, 1471, 1472, 1473, 1474, 1475, 1476, 1477, 1478, 1479,
	              1570, 1571, 1572, 1573, 1574, 1575, 1576, 1577, 1578, 1579, 1670,
	              1671, 1672, 1673, 1674, 1675, 1676, 1677, 1678, 1679, 1770, 1771,
	              1772, 1773, 1774, 1775, 1776, 1777, 1778, 1779, 1870, 1871, 1872,
	              1873, 1874, 1875, 1876, 1877,
	              1878, 1879, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978,
	              1979});
	}
	
	@Test
	public void testTermWithPostWildcardQuestionMark() throws Exception {
	    Query query = getParseResultQuery("field", "sevent?");
	    checkHits(query, new int[]
	      {70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 170, 171, 172, 173, 174, 175,
	              176, 177, 178, 179, 270, 271, 272, 273, 274, 275, 276, 277, 278,
	              279, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 470, 471,
	              472, 473, 474, 475, 476, 477, 478, 479, 570, 571, 572, 573, 574,
	              575, 576, 577, 578, 579, 670, 671, 672, 673, 674, 675, 676, 677,
	              678, 679, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 870,
	              871, 872, 873, 874, 875, 876, 877, 878, 879, 970, 971, 972, 973,
	              974, 975, 976, 977, 978, 979, 1070, 1071, 1072, 1073, 1074, 1075,
	              1076, 1077, 1078, 1079, 1170, 1171, 1172, 1173, 1174, 1175, 1176,
	              1177, 1178, 1179, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277,
	              1278, 1279, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378,
	              1379, 1470, 1471, 1472, 1473, 1474, 1475, 1476, 1477, 1478, 1479,
	              1570, 1571, 1572, 1573, 1574, 1575, 1576, 1577, 1578, 1579, 1670,
	              1671, 1672, 1673, 1674, 1675, 1676, 1677, 1678, 1679, 1770, 1771,
	              1772, 1773, 1774, 1775, 1776, 1777, 1778, 1779, 1870, 1871, 1872,
	              1873, 1874, 1875, 1876, 1877,
	              1878, 1879, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978,
	              1979});
	}
	
	@Test
	public void testTermWithPreWildcardQuestionMark() throws Exception {
	    Query query = getParseResultQuery("field", "?eventy");
	    checkHits(query, new int[]
	      {70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 170, 171, 172, 173, 174, 175,
	              176, 177, 178, 179, 270, 271, 272, 273, 274, 275, 276, 277, 278,
	              279, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 470, 471,
	              472, 473, 474, 475, 476, 477, 478, 479, 570, 571, 572, 573, 574,
	              575, 576, 577, 578, 579, 670, 671, 672, 673, 674, 675, 676, 677,
	              678, 679, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 870,
	              871, 872, 873, 874, 875, 876, 877, 878, 879, 970, 971, 972, 973,
	              974, 975, 976, 977, 978, 979, 1070, 1071, 1072, 1073, 1074, 1075,
	              1076, 1077, 1078, 1079, 1170, 1171, 1172, 1173, 1174, 1175, 1176,
	              1177, 1178, 1179, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277,
	              1278, 1279, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378,
	              1379, 1470, 1471, 1472, 1473, 1474, 1475, 1476, 1477, 1478, 1479,
	              1570, 1571, 1572, 1573, 1574, 1575, 1576, 1577, 1578, 1579, 1670,
	              1671, 1672, 1673, 1674, 1675, 1676, 1677, 1678, 1679, 1770, 1771,
	              1772, 1773, 1774, 1775, 1776, 1777, 1778, 1779, 1870, 1871, 1872,
	              1873, 1874, 1875, 1876, 1877,
	              1878, 1879, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978,
	              1979});
	}
	
	  @Test
	  public void testTerm2() throws Exception {
	    Query query = getParseResultQuery("field", "seventish");
	    checkHits(query, new int[] {});
	  }

	  @Test
	  public void testPhraseNormalQuotes() throws Exception {
	    Query query = getParseResultQuery("field", "\"seventy seven\"");
	    checkHits(query, new int[]
	      {77, 177, 277, 377, 477, 577, 677, 777, 877,
	              977, 1077, 1177, 1277, 1377, 1477, 1577, 1677, 1777, 1877, 1977});
	  }
	  
	  @Test
	  public void testPhraseNormalQuotesWithHyphen() throws Exception {
	    Query query = getParseResultQuery("field", "\"seventy-seven\"");
	    checkHits(query, new int[]
	      {77, 177, 277, 377, 477, 577, 677, 777, 877,
	              977, 1077, 1177, 1277, 1377, 1477, 1577, 1677, 1777, 1877, 1977});
	  }
	  
	  @Test
	  public void testPhraseNoQuotesWithHyphen() throws Exception {
	    Query query = getParseResultQuery("field", "seventy-seven");
	    checkHits(query, new int[]
	      {77, 177, 277, 377, 477, 577, 677, 777, 877,
	              977, 1077, 1177, 1277, 1377, 1477, 1577, 1677, 1777, 1877, 1977});
	  }
	  
	  @Test
	  public void testPhraseNoQuotesWithHyphenWildcard() throws Exception {
	    Query query = getParseResultQuery("field", "sevent*-seven");
	    checkHits(query, new int[]
	      {77, 177, 277, 377, 477, 577, 677, 777, 877,
	              977, 1077, 1177, 1277, 1377, 1477, 1577, 1677, 1777, 1877, 1977});
	  }
	  
	  @Test
	  public void testPhraseQuotesWithHyphenWildcard() throws Exception {
	    Query query = getParseResultQuery("field", "\"o*-thousand-two-hundred-forty-???\"");
	    checkHits(query, new int[]
	      {1241, 1242, 1246});
	  }
	  
	  @Test
	  public void testPhraseQuotesWithWildcard() throws Exception {
	    Query query = getParseResultQuery("field", "\"o* thousand two hundred forty ???\"");
	    checkHits(query, new int[]
	      {1241, 1242, 1246});
	  }
	  
	  @Test
	  public void testPhraseCrazyQuotes() throws Exception {
	    Query query = getParseResultQuery("field", "“seventy seven”");
	    checkHits(query, new int[]
	      {77, 177, 277, 377, 477, 577, 677, 777, 877,
	              977, 1077, 1177, 1277, 1377, 1477, 1577, 1677, 1777, 1877, 1977});
	  }
	  
	  @Test
	  public void testPhraseCrazyQuotesWildcard() throws Exception {
	    Query query = getParseResultQuery("field", "“sevent* seven”");
	    checkHits(query, new int[]
	      {77, 177, 277, 377, 477, 577, 677, 777, 877,
	              977, 1077, 1177, 1277, 1377, 1477, 1577, 1677, 1777, 1877, 1977});
	  }


	  @Test
	  public void testPhrase2() throws Exception {
	    Query query = getParseResultQuery("field", "\"seventish sevenon\"");
	    checkHits(query, new int[] {});
	  }
	  
	  @Test
	  public void testCrazyQuotesPhrase() throws Exception {
	    Query query = getParseResultQuery("field", "“seventish sevenon”");
	    checkHits(query, new int[] {});
	  }
	  
	  @Test
	  public void testPhraseWithBoost() throws Exception {
	    Query query = getParseResultQuery("field", "\"one thousand two hundred thirty three\":10.1 OR \"one thousand two hundred thirty two\" Or \"one thousand three hundred thirty\":5");
	    
	    int[] expected = new int[] {1233,    1330,1331,1332,1333,1334,1335,1336,1337,1338,1339,    1232};
	    checkHits(query, expected);
	    
	 // ensure the weighting order
	    checkHitsOrder(query, expected);
	  }
	  
	  @Test
	  public void testPrePhraseWithBoost() throws Exception {
	    Query query = getParseResultQuery("field", "\"one thousand two hundred thirty\" pre/5 (seven:5.32 or three:3 or two:2.9)");
	    
	    int[] expected = new int[] {1237,1233, 1232};
	    checkHits(query, expected);
	    
	    // ensure the weighting order
	    checkHitsOrder(query, expected);
	  }
}
