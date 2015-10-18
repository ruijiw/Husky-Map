package hw7;

import hw5.Edge;
import hw5.Graph;
import hw5.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class FindWeightedPath {
	// This class is not an abstract data type, so there are no
	// abstract functions or representation invariance
	
	/**
	 * Find a path between specified start node and end node and return the path
	 * 
	 * @requires graph cannot be null
	 * @param start the start node of the path
	 * @param dest the end node of the path
	 * @param graph the graph in which paths from start node to dest node are searched
	 * @param int the capacity of the list
	 * @return a list of edges that indicate the path from start node to end node
	 * returns an empty list if start or end node is not is the specified graph 
	 * return an empty list if the path from start node to end node is not found
	 */
	public static <N extends Comparable<N>>
	List<Edge<N, Double>> findWeightedPath(N start, N dest, Graph<N, Double> graph, int cap) {
		// return an empty list if the graph doesn't contain start or dest node
		if (!graph.containNode(new Node<N>(start)) || !graph.containNode(new Node<N>(dest))) {
			return new ArrayList<Edge<N, Double>>();
		}
		PriorityQueue<List<Edge<N, Double>>> active = new PriorityQueue<List<Edge<N, Double>>>
		(cap, new ListComparator<N>());
		Set<N> finished = new HashSet<N>();
		Edge<N, Double> selfEdge = new Edge<N, Double>(0.0, new Node<N>(start), new Node<N>(start));
		List<Edge<N, Double>> selfList = new ArrayList<Edge<N, Double>>();
		selfList.add(selfEdge);
		active.add(selfList);
		while (!active.isEmpty()) {
			List<Edge<N, Double>> minPath = active.poll();
			N minDest = minPath.get(minPath.size() - 1).getChildren().getData();
			// return path if the dest node is found
			if (minDest.equals(dest)) {
				return minPath;
			}
			if (!finished.contains(minDest)) {
				// traverse the children nodes
				for (Edge<N, Double> edge: graph.getEdgeSet(new Node<N>(minDest))) {
					if (!finished.contains(edge.getChildren())) {
						List<Edge<N, Double>> newPath = new ArrayList<Edge<N, Double>>(minPath);
						newPath.add(edge);
						active.add(newPath);
					}
				}
			}
			finished.add(minDest);
		}
		// return an empty list if the path is not found
		return new ArrayList<Edge<N, Double>>();
	}

}
