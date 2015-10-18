package hw7;

import java.util.*;
import hw5.Edge;
import hw5.Graph;
import hw5.Node;
import hw6.MarvelParser.MalformedDataException;
import hw6.MarvelParser;
import hw6.MarvelPaths;

/**
 * This class contains a method to load a weighted graph form tsv files and 
 * a method to find the least cost path from a specified start node to a specified dest node. 
 * The cost is measured by how well-connected two characters are. 
 * Specifically, it is the inverse of the number of books two characters share.
 */

public class MarvelPaths2 {
	// This class is not an abstract data type, so there are no
	// abstract functions or representation invariance
	
	private static final int CAPACITY = 20;
	
	/**
	 * Reads a user's input to determine the start node and the dest node of the path to find.
	 * 
	 * @throws MalformedDataException if the file is not well-formed:
	 * each line contains exactly two tokens separated by a tab,
     * or else starting with a # symbol to indicate a comment line.
	 */
	public static void main(String[] args) throws MalformedDataException {
		Scanner console = new Scanner(System.in);
		Graph<String, Double> marvelGraph = loadGraph("src/hw7/data/marvel.tsv");
		// reads the start node and dest node to find path
		System.out.println("From which character to start: ");
		String start = console.nextLine();
		System.out.println("To which character to end: ");
		String dest = console.nextLine();
		List<Edge<String, Double>> edges = findPath(start, dest, marvelGraph);
		System.out.println("path from " + start + " to " + dest + ":");
		if (!marvelGraph.containNode(new Node<String>(start)) || !marvelGraph.containNode
				(new Node<String>(dest))){
			if (!marvelGraph.containNode(new Node<String>(start))) {
				System.out.println("unknown character " + start);
			}
			if (!marvelGraph.containNode(new Node<String>(dest))) {
				System.out.println("unknown character " + dest);
			}
		} else if (start.equals(dest)) {
			System.out.println("path from " + start + " to " + dest + ":");
			System.out.println("total cost: 0.000");
		} else if (edges.isEmpty()) {
			System.out.println("path from " + start + " to " + dest + ":");
			System.out.println("no path found");
		} else {
			System.out.println("path from " + start + " to " + dest + ":");
			double total = 0;
			for (Edge<String, Double> edge: edges) {
				if (!edge.getParent().equals(edge.getChildren())) {
					String weightString = String.format("%.3f", edge.getLabel());
					System.out.println(edge.getParent().getData() + " to " + 
					edge.getChildren().getData() + " with weight " + weightString);
					total = total + edge.getLabel();
				}
			}
			String totalCost =String.format("%.3f", total);
			System.out.println("total cost: " + totalCost);
		}
		console.close();
	}
	
	/**
	 * Returns a graph that loads the data in a specified file
	 * 
	 * @requires fileName is a valid file path
	 * @param fileName the name of the file to be loaded
	 * @return a Graph that loads nodes and edges in a specified file
	 * @throws MalformedDataException if the file is not well-formed:
	 * each line contains exactly two tokens separated by a tab,
     * or else starting with a # symbol to indicate a comment line.
	 */
	public static Graph<String, Double> loadGraph(String fileName) throws MalformedDataException {
		Graph<String, Double> MarvelGraph2 = new Graph<String, Double>();
		Set<String> characters = new HashSet<String>();
		Map<String, List<String>> books = new HashMap<String, List<String>>();
		MarvelParser.parseData(fileName, characters, books);
		// add all the characters to the graph
		for (String name: characters) {
			MarvelGraph2.addNode(new Node<String>(name));
		} 
		Graph<String, String> marvelGraph = MarvelPaths.loadGraph(fileName);
		for (Node<String> node: marvelGraph.getNodeSet()) {
			List<Edge<String, String>> edgeList = new ArrayList<Edge<String, String>>
			(marvelGraph.getEdgeSet(node));
			while (!edgeList.isEmpty()) {
				Edge<String, String> edge = edgeList.remove(0);
				Node<String> dest = edge.getChildren();
				double count = 1.0;
				// count the number of the book
				for (int j = 0; j < edgeList.size(); j++) {
					if (edgeList.get(j).getChildren().equals(dest)) {
						edgeList.remove(j);
						count++;
						j--;
					} 
				}
				Double weight = 1 / count;
				MarvelGraph2.addEdge(new Edge<String, Double>(weight, node, dest));
			}
		}
		return MarvelGraph2;
	}
	
	/**
	 * Find a path between specified start node and dest node and return the path with least cost
	 * 
	 * @requires graph cannot be null
	 * @param start the start node of the path
	 * @param end the end node of the path
	 * @param graph the graph in which paths from start node to end node are searched
	 * @return a list of edges that indicate the path from start node to end node
	 * returns an empty list if start or dest node is not is the specified graph 
	 * return an empty list if the path from start node to dest node is not found
	 */
	public static List<Edge<String, Double>> findPath(String start, String dest, 
			Graph<String, Double> graph) {
		return FindWeightedPath.findWeightedPath(start, dest, graph, CAPACITY);
	}
}