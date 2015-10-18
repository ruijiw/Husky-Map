package hw8.test;

import static org.junit.Assert.*;
import hw8.Location;

import org.junit.Test;

public class LocationTest {
	private Location normal = new Location(0.3, 2.5);
	private Location zero = new Location(0,0);
	private Location longNum = new Location(1000, 2000);
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Constructor
	///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testConstructor() {
		new Location(0.3, 2.5);
		new Location(0,0);
		new Location(1000, 2000);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	getX
	///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testGetX() {
		assertTrue(normal.getX() == 0.3);
		assertTrue(zero.getX() == 0);
		assertTrue(longNum.getX() == 1000);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	getY
	///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testGetY() {
		assertTrue(normal.getY() == 2.5);
		assertTrue(zero.getY() == 0);
		assertTrue(longNum.getY() == 2000);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	equals
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	// test the reflexive property of equality
	public void testEqualsReflexive() {
		assertEquals(zero, zero);
		assertEquals(normal, normal);
		assertEquals(longNum, longNum);
	}
	
	@Test
	// test the symmetric property of equality
	public void testEqualsSymmetric() {
		Location duplicateLocation1 = new Location(zero.getX(), zero.getY());
		assertEquals(duplicateLocation1, zero);
		assertEquals(zero, duplicateLocation1);
		
		Location duplicateLocation2 = new Location(normal.getX(), normal.getY());
		assertEquals(duplicateLocation2, normal);
		assertEquals(normal, duplicateLocation2);
		
		Location duplicateLocation3 = new Location(longNum.getX(), longNum.getY());
		assertEquals(duplicateLocation3, longNum);
		assertEquals(longNum, duplicateLocation3);
	}
	
	@Test
	// test the transitive property of equality
	public void testEqualsTransitive() {
		Location duplicateLocation = new Location(zero.getX(), zero.getY());
		Location copyDuplicate = new Location(duplicateLocation.getX(), duplicateLocation.getY());
		assertEquals(zero, duplicateLocation);
		assertEquals(duplicateLocation, copyDuplicate);
		assertEquals(zero, copyDuplicate);
		
		Location duplicateLocation1 = new Location(normal.getX(), normal.getY());
		Location copyDuplicate1 = new Location(duplicateLocation1.getX(), duplicateLocation1.getY());
		assertEquals(normal, duplicateLocation1);
		assertEquals(duplicateLocation1, copyDuplicate1);
		assertEquals(normal, copyDuplicate1);
		
		Location duplicateLocation2 = new Location(longNum.getX(), longNum.getY());
		Location copyDuplicate2 = new Location(duplicateLocation2.getX(), duplicateLocation2.getY());
		assertEquals(longNum, duplicateLocation2);
		assertEquals(duplicateLocation2, copyDuplicate2);
		assertEquals(longNum, copyDuplicate2);		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////  hashCode
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testHashCodeSelfConsistent() {
		assertEquals(normal.hashCode(), normal.hashCode());
		assertEquals(zero.hashCode(), zero.hashCode());
		assertEquals(longNum.hashCode(), longNum.hashCode());
	}
	
	@Test
	public void testHashCodeConsistentWithEquality() {
		Location duplicateLocation = new Location(zero.getX(), zero.getY());
		assertEquals(zero.equals(duplicateLocation), zero.hashCode() == zero.hashCode());
		
		Location duplicateLocation1 = new Location(normal.getX(), normal.getY());
		assertEquals(normal.equals(duplicateLocation1), normal.hashCode() == normal.hashCode());
		
		Location duplicateLocation2 = new Location(longNum.getX(), longNum.getY());
		assertEquals(longNum.equals(duplicateLocation2), longNum.hashCode() == longNum.hashCode());
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	toString
	///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testToString() {
		eq(zero, "(0, 0)");
		eq(normal, "(0, 3)");
		eq(longNum, "(1000, 2000)");
	}
	
	private void eq(Location location, String rep) {
		assertEquals(location.toString(), rep);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	compareTo
	///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testCompareTo() {
		assertTrue(normal.compareTo(zero) > 0);
		assertTrue(zero.compareTo(normal) < 0);
		assertTrue(longNum.compareTo(normal) > 0);
		assertTrue(normal.compareTo(longNum) < 0);
		assertTrue(longNum.compareTo(zero) > 0);
		assertTrue(zero.compareTo(longNum) < 0);
		assertTrue(zero.compareTo(zero) == 0);
	}
}
