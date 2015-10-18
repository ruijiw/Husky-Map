package hw8.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import hw5.Edge;
import hw8.Building;
import hw8.CampusPathsModel;
import hw8.Location;

import org.junit.Before;
import org.junit.Test;

public class CampusPathsModelTest {
	
	private CampusPathsModel model = new CampusPathsModel();
	
	@Before
	public void createMap() {
		model.createMap();
	}

	@Test
	public void testListBuildings() {
		String[] reps = new String[] {
						"BAG: Bagley Hall (East Entrance)" ,
						"BAG (NE): Bagley Hall (Northeast Entrance)" ,
						"BGR: By George" ,
						"CHL: Chemistry Library (West Entrance)" ,
						"CHL (NE): Chemistry Library (Northeast Entrance)" ,
						"CHL (SE): Chemistry Library (Southeast Entrance)" ,
						"CMU: Communications Building" ,
						"CSE: Paul G. Allen Center for Computer Science & Engineering" ,
						"DEN: Denny Hall" ,
						"EEB: Electrical Engineering Building (North Entrance)" ,
						"EEB (S): Electrical Engineering Building (South Entrance)" ,
						"FSH: Fishery Sciences Building" ,
						"GWN: Gowen Hall" ,
						"HUB: Student Union Building (Main Entrance)" ,
						"HUB (Food, S): Student Union Building (South Food Entrance)" ,
						"HUB (Food, W): Student Union Building (West Food Entrance)" ,
						"IMA: Intramural Activities Building" ,
						"KNE: Kane Hall (North Entrance)" ,
						"KNE (E): Kane Hall (East Entrance)" ,
						"KNE (S): Kane Hall (South Entrance)" ,
						"KNE (SE): Kane Hall (Southeast Entrance)" ,
						"KNE (SW): Kane Hall (Southwest Entrance)" ,
						"LOW: Loew Hall" ,
						"MCC: McCarty Hall (Main Entrance)" ,
						"MCC (S): McCarty Hall (South Entrance)" ,
						"MCM: McMahon Hall (Northwest Entrance)" ,
						"MCM (SW): McMahon Hall (Southwest Entrance)" ,
						"MGH: Mary Gates Hall (North Entrance)" ,
						"MGH (E): Mary Gates Hall (East Entrance)" ,
						"MGH (S): Mary Gates Hall (South Entrance)" ,
						"MGH (SW): Mary Gates Hall (Southwest Entrance)" ,
						"MLR: Miller Hall" ,
						"MNY: Meany Hall (Northeast Entrance)" ,
						"MNY (NW): Meany Hall (Northwest Entrance)" ,
						"MOR: Moore Hall" ,
						"MUS: Music Building (Northwest Entrance)" ,
						"MUS (E): Music Building (East Entrance)" ,
						"MUS (S): Music Building (South Entrance)" ,
						"MUS (SW): Music Building (Southwest Entrance)" ,
						"OUG: Odegaard Undergraduate Library" ,
						"PAA: Physics/Astronomy Building A" ,
						"PAB: Physics/Astronomy Building" ,
						"PAR: Parrington Hall" ,
						"RAI: Raitt Hall (West Entrance)" ,
						"RAI (E): Raitt Hall (East Entrance)" ,
						"ROB: Roberts Hall" ,
						"SAV: Savery Hall" ,
						"SUZ: Suzzallo Library" ,
						"T65: Thai 65" ,
						"UBS: University Bookstore" ,
						"UBS (Secret): University Bookstore (Secret Entrance)"
			};
		Set<Building> buildings = model.listBuildings();
		int count = 0;
		for (Building building: buildings) {
			assertEquals(building.toString(), reps[count]);
			count++;
		}
	}
	
	@Test
	public void testFindPath() {
		String[] reps = new String[] {
						"Walk 0 feet to (2260, 1716)",
						"Walk 18 feet to (2261, 1707)" ,
						"Walk 90 feet to (2218, 1696)" ,
						"Walk 134 feet to (2157, 1675)" ,
						"Walk 200 feet to (2067, 1714)" ,
						"Walk 21 feet to (2055, 1718)" ,
						"Walk 134 feet to (1996, 1743)" ,
						"Walk 103 feet to (1952, 1766)" ,
						"Walk 151 feet to (1889, 1797)" ,
						"Walk 84 feet to (1852, 1813)" ,
						"Walk 136 feet to (1821, 1756)" ,
						"Walk 45 feet to (1801, 1765)" ,
						"Walk 79 feet to (1766, 1779)" ,
						"Walk 41 feet to (1759, 1760)" ,
						"Walk 59 feet to (1749, 1733)" ,
						"Walk 27 feet to (1737, 1740)" ,
						"Walk 72 feet to (1703, 1731)" ,
						"Walk 27 feet to (1691, 1737)" ,
						"Walk 35 feet to (1675, 1743)" ,
						"Walk 141 feet to (1677, 1676)" ,
						"Walk 43 feet to (1676, 1655)" ,
						"Walk 22 feet to (1666, 1656)" ,
						"Walk 43 feet to (1666, 1677)" ,
						"Walk 166 feet to (1586, 1678)" ,
						"Walk 51 feet to (1561, 1682)" ,
						"Walk 35 feet to (1561, 1698)"
				};
		List<Edge<Location, Double>> paths = model.findPath("CSE", "PAB");
		int count = 0;
		for (Edge<Location, Double> path: paths) {
			assertEquals("Walk " + (int) Math.round(path.getLabel()) + " feet to " + 
					path.getChildren().getData(), reps[count]);
			count++;
		}
	}
	
	@Test
	public void testFindBuilding() {
		Building Cse = CampusPathsModel.findBuilding("CSE");
		assertEquals(Cse, new Building("Paul G. Allen Center for Computer Science & Engineering", 
				"CSE", new Location(2259.7112, 1715.5273)));
		
		Building Eeb = CampusPathsModel.findBuilding("EEB");
		assertEquals(Eeb, new Building("Electrical Engineering Building (North Entrance)", 
				"EEB", new Location(2159.9587, 1694.8192)));
	}

}
