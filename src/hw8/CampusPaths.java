package hw8;

import hw5.Edge;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * The controller of Campus Path that controls models and views
 */

public class CampusPaths {
	private static CampusPathsModel model;
	private static CampusPathsView view;
	
	// This class is not an abstract data type, so there are no
	// abstract functions or representation invariance
		
	/**
	 * The main method that listens to users' input and prints output 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		model = new CampusPathsModel();
		view = new CampusPathsView();
		model.createMap();
		view.printMenu();
		boolean quit = false;
		while (!quit) {
			System.out.print("\nEnter an option ('m' to see the menu): ");
			String command = input.nextLine();
			// echo empty lines or lines beginning with #
			while (command.startsWith("#") || command.length() == 0) {
				System.out.println(command);
				command = input.nextLine();
			} 
			// lists all buildings in the form abbreviated name: long name. 
			if (command.equals("b")) {
				Set<Building> buildings = model.listBuildings();
				view.printBuildings(buildings); 
			// prompts the user for the abbreviated names of two buildings 
			// and prints directions for the shortest route between them.
			} else if (command.equals("r")) {
				System.out.print("Abbreviated name of starting building: ");
				String start = input.nextLine();
				System.out.print("Abbreviated name of ending building: ");
				String end = input.nextLine();
				List<Edge<Location, Double>> paths = model.findPath(start, end);
				view.printPaths(paths, start, end, model.listBuildings());
			// quits the program
			} else if (command.equals("q")) {
				quit = true; 
			// prints a menu of all commands.
			} else if (command.equals("m")) {
				view.printMenu();
			// an unknown command is received
			} else {
				System.out.println("Unknown option");
			}
		}
		input.close();
	}
}
