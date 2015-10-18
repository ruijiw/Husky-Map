package hw8;

/**
 * <b>Building</b> is an <b> immutable </b> data type that stores information of a building on campus.
 * It includes a long name, a short name, and a Location of a campus building. 
 */
public class Building implements Comparable<Building>{
	private final String longName;
	private final String shortName;
	private final Location location;
	
	// Abstract Function:
	// the long name of a Building is mapped to longName
	// the short name of a Building is mapped to shortName
	// the Location of a Building is mapped to location
		
	// Representation invariant:
	// longName != null &&
	// shortName != null &&
	// location != null
	
	/**
	 * @param longName the long name of a building
	 * @param shortName the short name of a building
	 * @param location the position of the a building located
	 * @effects constructs a new Building with longName, shortName and location
	 */
	public Building(String longName, String shortName, Location location) {
		this.longName = longName;
		this.shortName = shortName;
		this.location = location;
		checkRep();
	}
	
	/** 
	 * check whether the representation invariant holds (if any)
	 */
	private void checkRep() {
		assert(longName != null);
		assert(shortName != null);
		assert(location != null);
	}
	
	/**
	 * Compares two buildings 
	 * @requires the compared building is not null
	 * @param building the other building to compare
	 * @return positive if this is greater than the other building
	 * negative if this is less than the other building
	 * zero if this is equal to the other building
	 */
	@Override
	public int compareTo(Building building) {
		return this.getShortName().compareTo(building.getShortName());
	}
	
	/**
	 * Returns the long name of a building
	 * @return longName
	 */
	public String getLongName() {
		return this.longName;
	}
	
	/**
	 * Returns the short name of a building
	 * @return shortName
	 */
	public String getShortName() {
		return this.shortName;
	}
	
	/**
	 * Returns the location of a building
	 * @return location
	 */
	public Location getLocation() {
		return this.location;
	}
	
	/** Standard hashCode function.
	 * @return an integer that all objects equal to this will also return.
	 */
	public int hashCode() {
		return shortName.hashCode() + longName.hashCode() + location.hashCode();
	}
	
	/** Standard equality operation.
	 * @param obj The object to be compared for equality.
	 * @return true if and only if 'obj' is an instance of a Edge
	 * and 'this' and 'obj' represent the same Edge.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Building) {
			Building building = (Building) obj;
			if (this.hashCode() != building.hashCode()) {
				return false;
			}
			return building.getLongName().equals(this.getLongName()) && 
					building.getShortName().equals(this.getShortName()) &&
					building.getLocation().equals(this.getLocation());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.shortName + ": " + this.longName;
	}	
}
