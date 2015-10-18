package hw8;

import hw5.Edge;
import hw5.Graph;
import hw7.FindWeightedPath;

import java.util.*;

/**
 * The model of Campus Paths
 */
public class CampusPathsModel {
	private Graph<Location, Double> graph = new Graph<Location, Double>();
	private static Set<Building> buildings = new HashSet<Building>();
	
	/**
	 * Creates a map for the campus
	 * It includes all the buildings and all the locations on campus
	 * 
	 * @modifies graph
	 * @modifies buildings
	 * @effects fill with all the buildings and paths
	 */
	public void createMap() {
		try {
			BuildingsParser.parseData("src/hw8/data/campus_buildings.dat", buildings);
			PathsParser.parseData("src/hw8/data/campus_paths.dat", graph);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Find the path between a specified start building and a specified end building
	 * and returns the path
	 * 
	 * @requires buildings, graph cannot be null
	 * @param start the short name of start building of the path
	 * @param end the short name of end building of the path
	 * @return a list of edges that indicate the path from start building to end building
	 * return an empty list if the building is not found
	 * return an empty list if the path from start node to end node is not found
	 */
	public List<Edge<Location, Double>> findPath(String start, String end) {
		Building startB = findBuilding(start);
		Building endB = findBuilding(end);
		if (startB == null || endB == null) {
			return new ArrayList<Edge<Location, Double>>();
		}
		return FindWeightedPath.findWeightedPath(startB.getLocation(), endB.getLocation(), graph, 20);
	}
	
	/**
	 * Lists all the buildings on campus
	 * The list includes the short name and long name of the building
	 * @return a sorted set of all the buildings on campus
	 */
	public Set<Building> listBuildings() {
		return new TreeSet<Building>(buildings);
	}
	
	/**
	 * Find the Building given a specified name
	 * @param shortName the short name of the building
	 * @return the building that matches the given short name
	 * return null is the building is not found
	 */
	public static Building findBuilding(String shortName) {
		for (Building building: buildings) {
			if (building.getShortName().equals(shortName)) {
				return building;
			}
		}
		return null;
	}
	
	/**
	 * Helper method to get directions between two positions
	 * @param start the start position
	 * @param end the end position
	 * @return the direction from start to end
	 */
	public static String getDirection(Location start, Location end) {
		double x = end.getX() - start.getX();
		double y = end.getY() - start.getY();
		double angle = -Math.atan2(y, x);
		if ( Math.abs(angle) <= Math.PI/8 ) {
			return "E";
		}
		if ( angle > Math.PI/8 && angle < 3 * Math.PI/8) {
			return "NE";
		}
		if ( angle >= 3 * Math.PI/8 && angle <= 5 * Math.PI/8) {
			return "N";
		}
		if ( angle > 5 * Math.PI/8 && angle < 7 * Math.PI/8 ) {
			return "NW";
		}
		if ( Math.abs(angle) >= 7*Math.PI/8 ) {
			return "W";
		}
		if ( angle > -7 * Math.PI/8 && angle < -5 * Math.PI/8 ) {
			return "SW";
		}
		if ( angle >= -5 * Math.PI/8 && angle <= -3 * Math.PI/8) {
			return "S";
		}
		if ( angle > -3 * Math.PI/8 && angle < -Math.PI/8 ) {
			return "SE";
		}
		return null;
	}
}
