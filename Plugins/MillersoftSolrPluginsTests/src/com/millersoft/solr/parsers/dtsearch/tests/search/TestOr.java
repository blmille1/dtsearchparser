package com.millersoft.solr.parsers.dtsearch.tests.search;

import java.util.Set;
import java.util.TreeSet;

import org.apache.lucene.search.Query;
import org.junit.Test;

import com.millersoft.solr.parsers.tests.millersoftTestCase;

public class TestOr extends millersoftTestCase {
	@Test
	public void testThreeWithResults() throws Exception {
		Query query = getParseResultQuery("field", "seventy or seven or seventeen");
		Set<Integer> actualSet = new TreeSet<Integer>();
		numbers:
		for(int i=0;i<2000;i++) {
			String iStr = Integer.toString(i);
			for(int j = 0;j<iStr.length();j++){
				if(iStr.charAt(j) == '7') {
					actualSet.add(i);
					continue numbers;
				}
			}
		}
		
		int[] actual = integerSetToIntArray(actualSet);
	    checkHits(query, actual);
	  }

	  @Test
	  public void testThreeWithoutResults() throws Exception {
		Query query = getParseResultQuery("field", "sevento OR sevenly Or seventeenly");
	    
	    checkHits(query, new int[] {});
	  }
	
	/*
	  public void testSpanTermQuery() throws Exception {
	    checkHits(spanTermQuery("field", "seventy"), 
	              new int[] { 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 170,
	        171, 172, 173, 174, 175, 176, 177, 178, 179, 270, 271, 272, 273, 274,
	        275, 276, 277, 278, 279, 370, 371, 372, 373, 374, 375, 376, 377, 378,
	        379, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 570, 571, 572,
	        573, 574, 575, 576, 577, 578, 579, 670, 671, 672, 673, 674, 675, 676,
	        677, 678, 679, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 870,
	        871, 872, 873, 874, 875, 876, 877, 878, 879, 970, 971, 972, 973, 974,
	        975, 976, 977, 978, 979, 1070, 1071, 1072, 1073, 1074, 1075, 1076,
	        1077, 1078, 1079, 1170, 1270, 1370, 1470, 1570, 1670, 1770, 1870, 1970,
	        1171, 1172, 1173, 1174, 1175, 1176, 1177, 1178, 1179, 1271, 1272, 1273,
	        1274, 1275, 1276, 1277, 1278, 1279, 1371, 1372, 1373, 1374, 1375, 1376,
	        1377, 1378, 1379, 1471, 1472, 1473, 1474, 1475, 1476, 1477, 1478, 1479,
	        1571, 1572, 1573, 1574, 1575, 1576, 1577, 1578, 1579, 1671, 1672, 1673,
	        1674, 1675, 1676, 1677, 1678, 1679, 1771, 1772, 1773, 1774, 1775, 1776,
	        1777, 1778, 1779, 1871, 1872, 1873, 1874, 1875, 1876, 1877, 1878, 1879,
	        1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979 });
	  }
	  */

	  


	  

	  
	  
}
