package hw8;

import hw5.Edge;

import java.util.List;
import java.util.Set;

/**
 * The user interface view of Campus Paths
 */

public class CampusPathsView {
	// This class is not an abstract data type, so there are no
	// abstract functions or representation invariance
		
	/**
	 * prints a menu of all commands.
	 */
	public void printMenu() {
		System.out.println("Menu:");
		System.out.println("	r to find a route");
		System.out.println("	b to see a list of all buildings");
		System.out.println("	q to quit");
	}
	
	/**
	 * lists all buildings in the form abbreviated name: long name. 
	 * Buildings are listed in alphabetical order of abbreviated name.
	 * @param buildings the set of all buildings on campus
	 */
	public void printBuildings(Set<Building> buildings) {
		System.out.println("Buildings:");
		for (Building building: buildings) {
			System.out.println("	" + building.getShortName() + ": " + building.getLongName());
		}
	}
	
	/**
	 * Prints out a path starting from one building to another
	 * @param paths the list of paths that starts from one building to another
	 * @param start the short name of the start building
	 * @param end the short name of the end building
	 * @param buildings the set of all buildings on campus
	 */
	public void printPaths(List<Edge<Location, Double>> paths, String start, String end, Set<Building> buildings) {
		Building startB = CampusPathsModel.findBuilding(start);
		Building endB = CampusPathsModel.findBuilding(end);
		if (startB == null || endB == null) {
			if (startB == null) {
				System.out.println("Unknown building: " + start);
			}
			if (endB == null) {
				System.out.println("Unknown building: " + end);
			}
		} else {
			String startName = startB.getLongName();
			String endName = endB.getLongName();	
			if (start.equals(end)) {
				System.out.println("Path from " + startName + " to " + endName + ":");
				System.out.println("Total distance: 0 feet");
			} else {
				System.out.println("Path from " + startName + " to " + endName + ":");
				double totalCost = 0.0;
				for (Edge<Location, Double> path: paths) {
					if (path.getLabel() != 0) {
						String direction = CampusPathsModel.getDirection(path.getParent().getData(), path.getChildren().getData());
						int cost = (int) Math.round(path.getLabel());
						System.out.println("	Walk " + cost + " feet " + direction + " to " + 
						path.getChildren().getData());
						totalCost += path.getLabel();
					}
				}
				System.out.println("Total distance: " + (int) Math.round(totalCost) + " feet");
			}
		}
	}
}
