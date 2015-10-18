package hw6;


import java.util.*;

import hw5.*;
import hw6.MarvelParser;
import hw6.MarvelParser.MalformedDataException;

/**
 * This class contains a method to load graph from tsv files and a method to 
 * find the shortest path from a specified start node to a specified dest node. 
 * The path found is the lexicographically (alphabetically) least path.
 */
public class MarvelPaths {
	
	/**
	 * Reads a user's input to determine the file name to load, the start node
	 * and the dest node of the path to find.
	 * 
	 * @throws MalformedDataException if the file is not well-formed:
	 * each line contains exactly two tokens separated by a tab,
     * or else starting with a # symbol to indicate a comment line.
	 */
	public static void main(String[] args) throws MalformedDataException { 
		Scanner console = new Scanner(System.in);
		// read the start node and dest node from client
		Graph<String, String> marvelGraph = loadGraph("src/hw6/data/marvel.tsv");
		System.out.println("Start character: ");
		String start = console.nextLine(); 
		System.out.println("Dest character: ");
		String dest = console.nextLine();
		List<Edge<String, String>> edges = findPath(start, dest, marvelGraph);
		if (!marvelGraph.containNode(new Node<String>(start)) || !marvelGraph.containNode(new Node<String>(dest))){
			if (!marvelGraph.containNode(new Node<String>(start))) {
				System.out.println("unknown character " + start);
			}
			if (!marvelGraph.containNode(new Node<String>(dest))) {
				System.out.println("unknown character " + dest);
			}
		} else {
			if (edges.isEmpty() && !start.equals(dest)) {
				System.out.println("path from " + start + " to " + dest + ":");
				System.out.println("no path found");
			} else if (!start.equals(dest)){
				// print out the path
				for (Edge<String, String> edge: edges) {
					System.out.println(edge.getParent().getData() + " to " + edge.getChildren().getData() 
						+ " via " + edge.getLabel());
				}
			}
		}
		console.close();
	}
	
	/**
	 * Returns a graph that loads the data in a specified file
	 * 
	 * @requires fileName is a valid file path
	 * @param fileName the file that will be loaded
	 * @return a Graph that loads nodes and edges in a specified file
	 * @throws MalformedDataException if the file is not well-formed:
	 * each line contains exactly two tokens separated by a tab,
     * or else starting with a # symbol to indicate a comment line.
	 */
	public static Graph<String, String> loadGraph(String fileName) throws MalformedDataException {
		// get data from the file
		Set<String> characters = new HashSet<String>();
		Map<String, List<String>> books = new HashMap<String, List<String>>();
		MarvelParser.parseData(fileName, characters, books);
		// add all characters to the graph
		Graph<String, String> marvelGraph = new Graph<String, String>();
		for (String name: characters) {
			marvelGraph.addNode(new Node<String>(name));
		}
		Set<String> labels = books.keySet();
		// add all relationships that two chars in the same book to the graph
		for (String label: labels) {
			List<String> edges = books.get(label);
			for (String parent: edges) {
				for (String children: edges) {
					if (!parent.equals(children)) {
						marvelGraph.addEdge(new Edge<String, String>(label, new Node<String>(parent), new Node<String>(children)));
					}
				}
			}
		}
		return marvelGraph;
	}
	
	/**
	 * Find a path between specified start node and dest node and return the path
	 * 
	 * @requires marvelGraph cannot be null
	 * @param s the start node of the path
	 * @param d the dest node of the path
	 * @param marvelGraph the graph being searched
	 * @return a list of edges that indicate the shortest path from start node to dest node
	 * returns an empty list if start or dest node is not is the specified graph 
	 * returns an empty list if the path from start node to dest node is not found
	 */
	public static List<Edge<String, String>> findPath(String s, String d, Graph<String, String> marvelGraph) {
		Node<String> start = new Node<String>(s);
		Node<String> dest = new Node<String>(d);
		List<Edge<String, String>> path = new ArrayList<Edge<String, String>>();
		// return an empty list if marvelGraph doesn't contain start or dest node
		if (!marvelGraph.containNode(start) || !marvelGraph.containNode(dest)) {
			return path;
		}
		Queue<Node<String>> worklist = new LinkedList<Node<String>>();
		Map<Node<String>, List<Edge<String, String>>> nodeToPath = new HashMap<Node<String>, List<Edge<String, String>>>();
		worklist.add(start);
		nodeToPath.put(start, new ArrayList<Edge<String, String>>());
		while (!worklist.isEmpty()) {
			Node<String> next = worklist.remove();
			// if next node is the expected dest node, return the current path
			if (next.equals(dest)) {
				return nodeToPath.get(next);
			}
			Set<Edge<String, String>> edges = new TreeSet<Edge<String, String>>(marvelGraph.getEdgeSet(next));
			for (Edge<String, String> edge: edges) {
				Node<String> destNode = edge.getChildren();
				// append edge to the previous path
				if (!nodeToPath.containsKey(destNode)) {
					List<Edge<String, String>> destPath = new ArrayList<Edge<String, String>>(nodeToPath.get(next));
					destPath.add(edge);
					nodeToPath.put(destNode, destPath);
					worklist.add(destNode);
				}
			}
		}
		return path;
	}
}