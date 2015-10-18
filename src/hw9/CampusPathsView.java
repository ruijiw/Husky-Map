package hw9;

import hw5.Edge;
import hw8.Building;
import hw8.CampusPathsModel;
import hw8.Location;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The user interface view of Campus Paths
 */

public class CampusPathsView {
	private JButton find;
	private JButton reset;
	private JComboBox<String> startList;
	private JComboBox<String> endList;
	private String start;
	private String end;
	private boolean findPath;
	private JPanel mapPanel;
	private JPanel panel;
	private CampusPathsModel model;
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;
	private static final Dimension DBUTTON = new Dimension(120, 32);
	private static final Dimension DLIST = new Dimension(150, 50);
	private static final float FONT_SIZE = 15.0f;
	
	/**
	 * construct the CampusPathsView
	 * @throws IOException 
	 */
	public CampusPathsView() throws IOException {
		model = new CampusPathsModel();
		model.createMap();	
		this.find = new JButton("Find Path");
		this.reset = new JButton("Reset");
		this.startList = new JComboBox<String>();
		this.endList = new JComboBox<String>();
		mapPanel = new CampusPanel(model);
		panel = new JPanel();
	}
	
	/**
	 * setup the window and add components and map to it
	 * @param model the CampusPathsModel
	 */
	public void setUp(CampusPathsModel model) {
		JFrame frame = new JFrame("UW Campus Paths");
		frame.setLayout(new BorderLayout());
		drawComponents();
		frame.add(panel, BorderLayout.SOUTH);
		frame.add(mapPanel, BorderLayout.CENTER);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(WIDTH, HEIGHT);		
		frame.setVisible(true);
	}
	
	/**
	 * Helper method to draw the components
	 */
	private void drawComponents() {
		panel.setLayout(new FlowLayout());
		// draw the find button
		find.setPreferredSize(DBUTTON);
		find.setFont(find.getFont().deriveFont(FONT_SIZE));
		// draw the reset button
		reset.setPreferredSize(DBUTTON);
		reset.setFont(find.getFont().deriveFont(FONT_SIZE));
		// create two combobox and add the data to them
		startList.setPreferredSize(DLIST);
		startList.setFont(find.getFont().deriveFont(FONT_SIZE));
		endList.setPreferredSize(DLIST);
		endList.setFont(find.getFont().deriveFont(FONT_SIZE));
		startList.addItem("Select start building:");
		endList.addItem("Select end building:");
		Set<Building> buildings = model.listBuildings();
		for (Building building : buildings) {
			startList.addItem(building.toString());
			endList.addItem(building.toString());
		}
		// add the components to the panel
		panel.add(startList);
		panel.add(endList);
		panel.add(find);
		panel.add(reset);
	}
	
	/**
	 * Set whether to find the path
	 * @param findPath
	 */
	public void setFind(Boolean findPath) {
		this.findPath = findPath;
	}

	/**
	 * Set the short name of start building
	 * @param start the short name of start building
	 */
	public void setStartBuilding(String start) {
		this.start = start;
	}

	/**
	 * Set the short name of end building
	 * @param end the short name of end building
	 */
	public void setEndBuilding(String end) {
		this.end = end;
	}
	
	/**
	 * Return the start building list
	 * @return the start building list
	 */
	public JComboBox<String> getStart() {
		return startList;
	}
	
	/**
	 * Return the end building list
	 * @return the end building list
	 */
	public JComboBox<String> getEnd() {
		return endList;
	}
	
	/**
	 * Returns the find path button
	 * @return the find path button
	 */
	public JButton getFind() {
		return find;
	}
	
	/**
	 * Returns the reset button
	 * @return the reset button
	 */
	public JButton getReset() {
		return reset;
	}
	
	/**
	 * Repaint the map
	 */
	public void repaint() {
		mapPanel.repaint();
	}
	
	private class CampusPanel extends JPanel{

		private static final long serialVersionUID = 1L;
		private Image map;
		private CampusPathsModel model;
		private int mapWidth;
		private int mapHeight;
		private static final int RADIUS = 14;
		private static final int SCALE = 5;
		
		/**
		 * Construct the CampusPanel
		 * @param campusModel the model that stores and computes data
		 * @throws IOException 
		 */
		public CampusPanel(CampusPathsModel campusModel) throws IOException {
			model = campusModel;
			model.createMap();
			// get the image width and height
			map = ImageIO.read(new File("src/hw8/data/campus_map.jpg"));
			mapWidth = map.getWidth(null);
			mapHeight = map.getHeight(null);
			Dimension size = getPreferredSize();
			size.width = mapWidth / SCALE;
			size.height = mapHeight / SCALE;
			setPreferredSize(size);
			findPath = false;
			// setLayout(new GridBagLayout());
		}
		
		/**
		 * Draw the components in the panel
		 */
		@Override
		public void paintComponent(Graphics g) {
		    // ensure any background belonging to container is painted
		    super.paintComponent(g);	    
		    // cast g to its actual class to make graphics2d methods available
		    // (even though we don't use them here, it is common to use them)
		    Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(map, 0, 0, getWidth(), getHeight(), null);
			g2.setColor(Color.RED);
			markBuilding(g2, start);
			markBuilding(g2, end);
			drawPath(g2);
		}
		
		/**
		 * Mark the starting building if user select the corresponding building
		 * @param g2 Graphics
		 * @param building the short name of the building
		 */
		private void markBuilding(Graphics g2, String building) {
			Building b = CampusPathsModel.findBuilding(building);
			if (b != null) {
				Location loc = b.getLocation(); 
				double x = loc.getX();
				double y = loc.getY();
				g2.fillOval((int) (x / mapWidth * getWidth()) - RADIUS / 2, 
						(int) (y / mapHeight * getHeight()) - RADIUS / 2, RADIUS, RADIUS);
			}
		}
		
		/**
		 * Draw the path if the user click the button find
		 * @param g2 Graphics
		 */
		private void drawPath(Graphics g2) {
			if (start != null && end != null && findPath) {
				List<Edge<Location, Double>> paths = model.findPath(start, end);
				for (Edge<Location, Double> path: paths) {
					g2.drawLine((int) (path.getParent().getData().getX() / mapWidth * getWidth()),
						(int) (path.getParent().getData().getY() / mapHeight * getHeight()),
						(int) (path.getChildren().getData().getX() / mapWidth * getWidth()),
						(int) (path.getChildren().getData().getY() / mapHeight * getHeight()));
				}
			}
		}
	}
}
