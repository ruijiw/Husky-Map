package hw9;

import java.io.IOException;

import hw8.CampusPathsModel;

/**
 * CampusPathsMain allow user to choose two buildings in UW and show the two buildings
 * and the shortest path between those two buildings on a map
 */
public class CampusPathsMain {
	private static  CampusPathsView view;
	private static CampusPathsModel model;

	public static void main(String[] args) throws IOException {
		model = new CampusPathsModel();
		model.createMap();
		view = new CampusPathsView();
		view.setUp(model);
		CampusPathsControl controller = new CampusPathsControl();
		controller.addListeners(view);
	}
}
