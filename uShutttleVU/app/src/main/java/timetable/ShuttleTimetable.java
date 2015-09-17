package timetable;
final class	ShuttleTimetable {
	
static int Arrival;
static String Arrival_String;
static int Departure;
static String Departure_String;
static String Result_String;

int loc0[], loc1[], loc2[], loc3[], loc4[], loc5[], loc6[];
String s0, s1, s2, s3, s4, s5, s6;

			
			//Location Name Strings
			String southCampusString = "South Campus";
			String northIthanString = "North Ithan Gate/VSB";
			String falveyLibString = "Falvey Library";
			String tolentineHallString = "Tolentine Hall";
			String lawSchoolString = "Garey Hall/Law School";
			String westCampusString = "West Campus";
			String pikeLotString = "Pike Lot";
			String cosiString = "Cosi/761 Lancaster Ave.";
			String suburbanSqString = "Suburban Square";
			String giantString = "Giant";
			String minellasString = "Minella's Diner";
			String wayneTheatreString = "Wayne Movie Theatre";
			String anthropologieString = "Anthropologie";
			String homePropsString = "Home Properties";
			String hospitalString = "Bryn Mawr Hospital";
			String rosemontPharmacyString = "Rosemont Pharmacy";
			String kopMallString = "Friday's Resaurant at King of Prussia Mall";
			
			
//*********************************ON CAMPUS WEEKDAY**************************************************** FLAG = (0)
				static int[] southCampus0 = {810,830,850,910,930,950,1010,1030,1050,1110,1130,1150,
							1230,1250,1310,1330,1350,1410,1430,1450,1510,1530,1550,1610,1630,1650,
							1710,1730,1750,1810,1830,1850,1910,1930,1950,2010,2030,2050,
							2110,2130,2150,2210,2230,2250,2310,2330,2350,2410,2430,2450,2510,2530};		
					
					
					
				static int[] northIthan0 = {806,815,826,835,846,855,906,915,926,935,946,955,1006,1015,1026,
							1035,1046,1055,1106,1115,1126,1135,1146,1155,1235,1235,1246,1255,1306,
							1315,1326,1335,1346,1355,1406,1415,1426,1435,1446,1455,1506,1515,1526,1535,1546,
							1555,1606,1615,1626,1635,1646,1655,1706,1715,1726,1735,1746,1755,1806,1815,1826,1835,1846,
							1855,1906,1915,1926,1935,1946,1955,2006,2015,2026,2035,2046,2055,2106,2115,2126,2135,2155,
							2206, 2215,2226,2235,2246,2255,2306,2315,2326,2335,2346,2355,2406,2415,2426,2435,2446,2455,
							2506,2515,2526};
					
				
					
					int[] falveyLib0 = {817,837,857,917,937,1017,1037,1057,1117,1137,1157,1237,1257,1317,1337,
							1357,1417,1437,1457,1517,1537,1557,1617,1637,1657,1717,1737,1757,1817,1837,1857,1917,1937,
							1957,2017,2037,2057,2117,2137,2157,2217,2237,2257,2317,2337,2357,2417,2437,2457,2517};
					
					
					int[] tolentineHall0  = {819,839,859,919,939,1019,1039,1059,1119,1139,1159,1239,1259,1319,1339,
							1359,1419,1439,1459,1519,1539,1559,1619,1639,1659,1719,1739,1759,1819,1839,1859,1919,1939,
							1959,2019,2039,2059,2119,2139,2159,2219,2239,2259,2319,2339,2359,2419,2439,2459,2519};
					
					
					int[] lawSchool0 = {802,822,842,902,922,942,1002,1022,1042,1102,1122,1142,1242,1302,1322,1342
							,1402,1422,1442,1502,1522,1542,1602,1622,1642,1702,1722,1742,1802,1822,1842
							,1902,1922,1942,2002,2022,2042,2102,2122,2142,2202,2222,2242,2302,2322,2342
							,2402,2422,2442,2502,2522};
					
					
					int[] westCampus0 = {803,823,843,903,923,943,1003,1023,1043,1103,1123,1143,1243,1303,1323,1343
							,1403,1423,1443,1503,1523,1543,1603,1623,1643,1703,1723,1743,1803,1823,1843
							,1903,1923,1943,2003,2023,2043,2103,2123,2143,2203,2223,2243,2303,2323,2343
							,2403,2423,2443,2503,2523};
					
			
//**************************************ON CAMPUS SATURDAY NIGHT******************************************* FLAG = (1)
					int[] pikeLot1 = {2230,2251,2330,2351,2430,2451,2530};
					
					
					int[] southCampus1 = {2232,2253,2332,2353,2432,2453};
					
					int[] northIthan1 = {2237,2249,2258,2310,2337,2349,2358,2410,2437,
							2449,2458,2510};
					
					int[] falveyLib1 = {2239,2300,2339,2400,2439,2510};
					
					int [] tolentineHall1= {2241,2302,2341,2402,2441,2503};
					
					
					int [] lawSchool1 = {2242,2303,2342,2403,2442,2503};
					
					int [] westCampus1 = {2243,2304,2343,2404,2443,2504};
				

					
			
//********************************************MAIN LINE WEEKDAY ******************************************************* FLAG = (2)
					int [] northIthan2 = {800,820,840,900,920,940,1000,1020,1040,1100,1120,1140,1240,1300,1320,
							1340,1400,1420,1440,1500,1545,1600,1645,1700,1745,1800,1845,1900,1945,2000,2018,2034,2118,2134};
					
					int [] homeProps2 = {806,826,846,906,926,946,1006,1026,1046,1106,1126,1146,1246,1306,1326,1346,1406,1426,1446,
							1508,1540,1608,1640,1708,1740,1808,1840,1908,1940,2008,2026,2042,2108,2126,2142};
					
					
					
					int [] hospital2 = {808,828,848,908,928,948,1008,1028,1048,1108,1128,1148,1248,1308,1328,1348,1408,1428,1448,
							1510,1538,1610,1638,1710,1738,1810,1838,1910,1938};
					
					
					int [] rosemontPharmacy2 = {814,834,854,914,934,954,1014,1034,1054,1114,1134,1154,1248,1308,1328,1348,1408,1428,1448,1510,1538};
					
					
					
					int [] suburbanSq2 = {1525,1625,1725,1825,1925};

//**********************************OFF CAMPUS WEEKEND******************************************************FLAG = (3)	
					int[] pikeLot3 = {1200,1245,1330,1415,1500,1545,1700,1745,
							1830,1915,1955,2040,2120,2205};
					
					
					int[] cosi3 = {1213,1343,1513,1713,1843,2008,2133};
					
					
					int[] suburbanSq3 = {1250,1325,1425,1455,1555,1625,1755,1825,
							1925,1950,2050,2115,2215};
					
					
					int[] giant3 = {1250,1325,1425,1455,1555,1625,1755,1825,1925,1950,
							2050,2115,2215};
					
					int[] minellas3= {1255,1430,1600,1800,1930,2055,2220};
					
					
					int[] wayneTheatre3 = {1305,1440,1610,1810,1940,2105,2225};
					
					
					int[] anthropologie3 = {1315,1450,1620,1820};					
						
					
//****************************************KOP MALL WEEKEND*****************************************************FLAG=(4)
					int [] southCampus4 = {1800,1900,2000,2100,2200,2300,2400,2420};
					
					
					int[] kopMall4 = {1830,1930,2030,2130,2230,2330,2410};
					
					ShuttleTimetable(int tripTypeFlag){
						switch(tripTypeFlag){
						case 0: //On campus weekday
							loc0 = southCampus0;
							loc1 = northIthan0;
							loc2 = falveyLib0;
							loc3 = tolentineHall0;
							loc4 = lawSchool0;
							loc5 = westCampus0;
							
							s0 = southCampusString;
							s1 = northIthanString;
							s2 = falveyLibString;
							s3 = tolentineHallString;
							s4 = lawSchoolString;
							s5 = westCampusString;
							break;
						case 1: //On Campus Saturday Night
							loc0 = pikeLot1;
							loc1 = southCampus1;
							loc2 = northIthan1;
							loc3 = falveyLib1;
							loc4 = tolentineHall1;
							loc5 = lawSchool1;
							loc6 = westCampus1;
							
							s0 = pikeLotString;
							s1 = southCampusString;
							s2 = northIthanString;
							s3 = falveyLibString;
							s4 = tolentineHallString;
							s5 = lawSchoolString;
							s6 = westCampusString;
							break;
						case 2: //Main line weekday
							loc0 = northIthan2;
							loc1 = homeProps2;
							loc2 = hospital2;
							loc3 = rosemontPharmacy2;
							loc4 = suburbanSq2;
							
							s0 = northIthanString;
							s1 = homePropsString;
							s2 = hospitalString;
							s3 = rosemontPharmacyString;
							s4 = suburbanSqString;;
							break;
						case 3:  //Off Campus weekend
							loc0 = pikeLot3;
							loc1 = cosi3;
							loc2 = suburbanSq3;
							loc3 = giant3;
							loc4 = minellas3;
							loc5 = wayneTheatre3;
							loc6 = anthropologie3;
							
							s0 = pikeLotString;
							s1 = cosiString;
							s2 = suburbanSqString;
							s3 = giantString;
							s4 = minellasString;
							s5 = wayneTheatreString;
							s6 = anthropologieString;
							break;
						case 4: //KOP Mall
							loc0 = southCampus4;
							loc1 = kopMall4;
							
							s0 = southCampusString;
							s1 = kopMallString;
							break;
							
							
						}//End of switch
						
					}//End of constructor

					
					
				}
				
				
				
			