package hw8;

/**
 * <b>Location</b> is an <b> immutable </b> data type that represents the location of a point on a map.
 * It includes x, which is the x coordinate of the location
 * and y, which is the y coordinate of the location.
 */

public class Location implements Comparable<Location>{
	private final Double x;
	private final Double y;
	
	// Abstract Function:
	// the x coordinate of a location is mapped to x
	// the y coordinate of a location is mapped to y
			
	// Representation invariant:
	// x != null &&
	// y != null
	
	/**
	 * @param x the x coordinate of a location
	 * @param y the y coordinate of a location
	 * @effects constructs a Location with x and y
	 */
	public Location(double x, double y) {
		this.x = x;
		this.y = y;
		checkRep();
	}
	
	/** 
	 * check whether the representation invariant holds (if any)
	 */
	private void checkRep() {
		assert(x != null);
		assert(y != null);
	}
	
	/**
	 * Compares two Location
	 * @requires the compared Location is not null
	 * @param location the other position to compare
	 * @return positive if this is greater than the other location
	 * negative if this is less than the other location
	 * zero if this is equal to the other location
	 */
	@Override
	public int compareTo(Location location) {
		if (x - location.getX() > 0) {
			return 1;
		} else if (x - location.getX() < 0) {
			return -1;
		} else {
			return (int) (y - location.getY());
		}
	}
	
	/**
	 * Returns x coordinate of Location
	 * @return x
	 */
	public Double getX() {
		return this.x;
	}
	
	/**
	 * Returns y coordinate of Location
	 * @return y
	 */
	public Double getY() {
		return this.y;
	}
	
	/** Standard hashCode function.
	 * @return an integer that all objects equal to this will also return.
	 */
	@Override
	public int hashCode() {
		return x.hashCode() + y.hashCode();
	}
	
	/** Standard equality operation.
	 * @param obj The object to be compared for equality.
	 * @return true if and only if 'obj' is an instance of a Edge
	 * and 'this' and 'obj' represent the same Edge.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Location) {
			Location location = (Location) obj;
			if (this.hashCode() != location.hashCode()) {
				return false;
			}
			return location.getX().equals(this.getX()) && location.getY().equals(this.getY());
		}
		return false;
	}
	
	/**
	 * Returns a String that represents this Location
	 * @return a String representation of this Location that contains its
	 * x coordinate and y coordinate rounding to nearest integer
	 */
	@Override
	public String toString() {
		return "(" + (int) Math.round(x) + ", " + (int) Math.round(y) + ")";
	}
}
