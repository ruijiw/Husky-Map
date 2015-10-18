package hw8.test;

import static org.junit.Assert.*;

import java.util.*;

import hw8.BuildingsParser;
import hw8.Building;
import hw8.BuildingsParser.MalformedDataException;
import hw8.Location;

import org.junit.Test;

public class BuildingsParserTest {

	@Test
	// test if the file to load is empty
	public void testEmptyParse() throws MalformedDataException {
		Set<Building> buildings = new HashSet<Building>();
		BuildingsParser.parseData("src/hw8/data/emptyBuilding.dat", buildings);
		assertEquals(buildings.size(), 0);
	}
	
	@Test
	// test if the file contains one building
	public void testOneParse() throws MalformedDataException {
		Set<Building> buildings = new HashSet<Building>();
		BuildingsParser.parseData("src/hw8/data/oneBuilding.dat", buildings);
		assertEquals(buildings.size(), 1);
		assertTrue(buildings.contains(new Building("Paul G. Allen Center for Computer Science & Engineering", 
				"CSE", new Location(2259.7112, 1715.5273))));
	}
	
	@Test
	// test if the file contains two buildings
	public void testTwoParse() throws MalformedDataException {
		Set<Building> buildings = new HashSet<Building>();
		BuildingsParser.parseData("src/hw8/data/twoBuildings.dat", buildings);
		assertEquals(buildings.size(), 2);
		assertTrue(buildings.contains(new Building("Paul G. Allen Center for Computer Science & Engineering", 
				"CSE", new Location(2259.7112, 1715.5273))));
		assertTrue(buildings.contains(new Building("Electrical Engineering Building (North Entrance)", 
				"EEB", new Location(2159.9587, 1694.8192))));
	}
}
