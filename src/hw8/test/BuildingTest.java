package hw8.test;

import static org.junit.Assert.*;
import hw8.Building;
import hw8.Location;

import org.junit.Test;

public class BuildingTest {

	private Building normal = new Building("Computer Science", "CSE", new Location(1,1));
	private Building empty = new Building("", "", new Location(0,0));
	private Building longName = new Building("1111111111111111111111111111111111", "11111", 
			new Location(11111,11111));
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Constructor
	///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testConstructor() {
		new Building("Computer Science", "CSE", new Location(1,1));
		new Building("", "", new Location(0,0));
		new Building("1111111111111111111111111111111111", "11111", 
				new Location(11111,11111));
	}
	
	private void eq(String building, String rep) {
	    assertEquals(building, rep);
	}

	///////////////////////////////////////////////////////////////////////////////////////
	////	getShortName
	///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testGetShortName() {
		eq(normal.getShortName(), "CSE");
		eq(empty.getShortName(),"");
		eq(longName.getShortName(), "11111");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	getLongName
	///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testGetLongName() {
		eq(normal.getLongName(), "Computer Science");
		eq(empty.getLongName(),"");
		eq(longName.getLongName(), "1111111111111111111111111111111111");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	getLocation
	///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testGetLocation() {
		assertEquals(normal.getLocation(), new Location(1,1));
		assertEquals(empty.getLocation(), new Location(0,0));
		assertEquals(longName.getLocation(), new Location(11111,11111));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	equals
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	// test the reflexive property of equality
	public void testEqualsReflexive() {
		assertEquals(empty, empty);
		assertEquals(normal, normal);
		assertEquals(longName, longName);
	}
	
	@Test
	// test the symmetric property of equality
	public void testEqualsSymmetric() {
		Building duplicateBuilding1 = new Building(empty.getLongName(), empty.getShortName(), empty.getLocation());
		assertEquals(duplicateBuilding1, empty);
		assertEquals(empty, duplicateBuilding1);
		
		Building duplicateBuilding2 = new Building(normal.getLongName(), normal.getShortName(), normal.getLocation());
		assertEquals(duplicateBuilding2, normal);
		assertEquals(normal, duplicateBuilding2);
		
		Building duplicateBuilding3 = new Building(longName.getLongName(), longName.getShortName(), longName.getLocation());
		assertEquals(duplicateBuilding3, longName);
		assertEquals(longName, duplicateBuilding3);
	}
	
	@Test
	// test the transitive property of equality
	public void testEqualsTransitive() {
		Building duplicateBuilding = new Building(empty.getLongName(), empty.getShortName(), empty.getLocation());
		Building copyDuplicate = new Building(duplicateBuilding.getLongName(), duplicateBuilding.getShortName(), 
				duplicateBuilding.getLocation());
		assertEquals(empty, duplicateBuilding);
		assertEquals(duplicateBuilding, copyDuplicate);
		assertEquals(empty, copyDuplicate);
			
		Building duplicateBuilding1 = new Building(normal.getLongName(), normal.getShortName(), normal.getLocation());
		Building copyDuplicate1 = new Building(duplicateBuilding1.getLongName(), duplicateBuilding1.getShortName(), 
				duplicateBuilding1.getLocation());
		assertEquals(normal, duplicateBuilding1);
		assertEquals(duplicateBuilding1, copyDuplicate1);
		assertEquals(normal, copyDuplicate1);
			
		Building duplicateBuilding2 = new Building(longName.getLongName(), longName.getShortName(), longName.getLocation());
		Building copyDuplicate2 = new Building(duplicateBuilding2.getLongName(), duplicateBuilding2.getShortName(), 
				duplicateBuilding2.getLocation());
		assertEquals(longName, duplicateBuilding2);
		assertEquals(duplicateBuilding2, copyDuplicate2);
		assertEquals(longName, copyDuplicate2);		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////  hashCode
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testHashCodeSelfConsistent() {
		assertEquals(normal.hashCode(), normal.hashCode());
		assertEquals(empty.hashCode(), empty.hashCode());
		assertEquals(longName.hashCode(), longName.hashCode());
	}
	
	@Test
	public void testHashCodeConsistentWithEquality() {
		Building duplicateBuilding = new Building(empty.getLongName(), empty.getShortName(), empty.getLocation());
		assertEquals(empty.equals(duplicateBuilding), empty.hashCode() == empty.hashCode());
		
		Building duplicateBuilding1 = new Building(normal.getLongName(), normal.getShortName(), normal.getLocation());
		assertEquals(normal.equals(duplicateBuilding1), normal.hashCode() == normal.hashCode());
		
		Building duplicateBuilding2 = new Building(longName.getLongName(), longName.getShortName(), longName.getLocation());
		assertEquals(longName.equals(duplicateBuilding2), longName.hashCode() == longName.hashCode());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	toString
	///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testToString() {
		assertEquals(empty.toString(), ": ");
		assertEquals(normal.toString(), "CSE: Computer Science");
		assertEquals(longName.toString(), "11111: 1111111111111111111111111111111111");
	}

	///////////////////////////////////////////////////////////////////////////////////////
	////	compareTo
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCompareTo() {
		assertTrue(empty.compareTo(normal) < 0);
		assertTrue(normal.compareTo(empty) > 0);
		assertTrue(normal.compareTo(longName) > 0);
		assertTrue(longName.compareTo(normal) < 0);
		assertTrue(longName.compareTo(empty) > 0);
		assertTrue(empty.compareTo(longName) < 0);
	}
}
