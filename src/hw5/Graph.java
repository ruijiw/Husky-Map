package hw5;

import java.util.*;

/**<b>Graph<N, E><b> represents an <b> generic </b> <b> mutable </b> collection of nodes and edges.
 * <p>
 * Each Graph is a map with nodes as keys and Sets of edges as values. 
 * Each edge connects two nodes: a parent node which is the node the edge starts from 
 * and a children which is the node the edge ends with. 
 * <p>
 */

public class Graph<N extends Comparable<N>, E extends Comparable<E>> {
	private final Map<Node<N>, Set<Edge<N, E>>> graph;
	public static final boolean DEBUG_FLAG = false;
	
	// Abstraction Function:
	// Each key of a Graph is mapped to the corresponding node of graph.
	// Each value of a Graph is mapped to corresponding set of edges of graph.
	
	// Representation Invariant:
	// graph != null &&
	// all keys of graph != null &&
	// all values of graph != null
	
	/**
	 * @effects constructs a new Graph with no node
	 */
	public Graph() {
		this.graph = new HashMap<Node<N>, Set<Edge<N, E>>>();
		checkRep();
	}

	/**
	 * Returns whether this Graph is empty.
	 * @return true if this Graph is empty and 
	 * false if this Graph is not empty.
	 */
	public boolean isEmpty() {
		return this.graph.isEmpty();
	}
	
	/**
	 * Returns the number of Nodes in this Graph.
	 * @return the size of this map.
	 */
	public int size() {
		return this.graph.size();
	}
	
	/**
	 * Returns whether the specified node is in this Graph. 
	 * @param node, the Node to check whether it is in this Graph
	 * @requires test node cannot be null
	 * @return true if the specified node is in this Graph,
	 * false if the specified node is not in this Graph.
	 */
	public boolean containNode(Node<N> node) {
		return graph.containsKey(node);
	}
	
	/**
	 * Returns whether the specified edge is in this Graph.
	 * @param edge, the edge to check whether it is in this graph.
	 * @requires test edge cannot be null
	 * @return true if the specified edge is in this Graph,
	 * false if the specified edge is not in this Graph.
	 */
	public boolean containEdge(Edge<N, E> edge) {
		if (!graph.containsKey(edge.getParent())) {
			return false;
		} 
		Set<Edge<N, E>> edgeSet = graph.get(edge.getParent());
		return edgeSet.contains(edge);
	}
	
	/**
	 * Adds the specified Node to this Graph
	 * Returns whether the specified Node is added to this Graph
	 * @param node, the specified Node to add to this Graph
	 * @requires added node cannot be null
	 * @modifies this 
	 * @effects this graph contains an additional node as key if the input node is 
	 * not in the graph before
	 * @return true if the specified Node is successfully added to this graph
	 * false if the specified Node is not added to this Graph
	 */
	public boolean addNode(Node<N> node) {
		if (!this.containNode(node)) { 
			graph.put(node, new HashSet<Edge<N, E>>());
			checkRep();
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * Adds the specified Edge to this Graph
	 * Returns whether the specified Edge is added to this Graph
	 * @param edge, the specified Edge to add to this Graph
	 * @requires added edge cannot be null
	 * @modifies this 
	 * @effects this graph contains an additional Edge object. The specified Edge
	 * would be mapped to the key that is the parent Node of the Edge.
	 * @return true if the specified Edge is successfully added to this Graph
	 * false if the specified Edge is not added to this Graph
	 */
	public boolean addEdge(Edge<N, E> edge) {
		if (!graph.containsKey(edge.getParent()) || !graph.containsKey(edge.getChildren())) {
			return false;
		}
		Set<Edge<N, E>> edgeSet = graph.get(edge.getParent());
		boolean success = edgeSet.add(edge);
		checkRep();
		return success;
	}
	
	/**
	 * Removes the specified Node from this Graph
	 * Returns whether the specified Node is removed from this Graph
	 * @param node: the specified Node to remove from this Graph
	 * @requires the removed node cannot be null
	 * @modifies this
	 * @effects this graph removes a key with a type of Node if the input node is in this Graph
	 * @return true if the specified Node is successfully removed from this Graph
	 * false if the specified Node is not removed from this Graph
	 */
	public boolean removeNode(Node<N> node) {
		if (!graph.containsKey(node)) {
			return false;
		}
		graph.remove(node);
		Set<Edge<N, E>> set = new HashSet<Edge<N, E>>();
		for (Set<Edge<N, E>> edgeSet: graph.values()) {
			for (Edge<N, E> e: edgeSet) {
				if (e.getChildren().equals(node)) {
					set.add(e);
				}
			}
		}
		for (Edge<N, E> e: set) {
			graph.get(e.getParent()).remove(e);
		}
		checkRep();
		return true;
	}
	
	/**
	 * Removes the specified Edge from this Graph
	 * Returns whether the specified Edge is removed from this Graph
	 * @param edge, the specified Edge to remove from this Graph
	 * @requires the removed Edge cannot be null
	 * @modifies this
	 * @effects this graph removes a specified Edge object if the input edge is in this Graph
	 * @return true if the specified Edge is successfully removed from this Graph
	 * false if the specified Edge is not removed from this Graph
	 */
	public boolean removeEdge(Edge<N, E> edge) {
		if (!graph.containsKey(edge.getParent())) {
			return false;
		} 
		Set<Edge<N, E>> edgeSet = graph.get(edge.getParent());
		boolean success = edgeSet.remove(edge);
		checkRep();
		return success;
	}
	
	/**
	 * Returns a set containing all Node objects in this graph
	 * @return a unmodifiable set of this graph's keys that are Node objects
	 */
	public Set<Node<N>> getNodeSet () {
		return Collections.unmodifiableSet(graph.keySet());
	}
	
	/**
	 * Returns a set containing all Node objects in this graph
	 * @return a modifiable set of this graph's keys that are Node objects
	 */
	public Set<Node<N>> getModifiableNodeSet() {
		return graph.keySet();
	}
	
	/**
	 * Returns a set containing Edge objects whose parent Node is
	 * the specified Node object. Return an empty set if no Edge
	 * object's parent Node is the specified Node.
	 * @param node: the specified Node object.
	 * @requires the node cannot be null.
	 * @return a unmodifiable set of Edge object whose key is the
	 * specified Node object. Return an empty set if this Graph doesn't
	 * contain the specified key
	 */
	public Set<Edge<N, E>> getEdgeSet(Node<N> node) {
		if (!graph.containsKey(node)) {
			return Collections.unmodifiableSet(new HashSet<Edge<N, E>>());
		}
		return Collections.unmodifiableSet(graph.get(node));
	}
	
	/**
	 * Returns a set containing Edge objects whose parent Node is
	 * the specified Node object. Return an empty set if no Edge
	 * object's parent Node is the specified Node.
	 * @param node: the specified Node object.
	 * @requires the node cannot be null.
	 * @return a modifiable set of Edge object whose key is the
	 * specified Node object. Return an empty set if this Graph doesn't
	 * contain the specified key
	 */	
	public Set<Edge<N, E>> getModifiableEdgeSet(Node<N> node) {
		if (!graph.containsKey(node)) {
			return new HashSet<Edge<N, E>>();
		}
		return graph.get(node);
	}
	
	/**
	 * Returns a set containing all the Edge objects in this Graph
	 * @return a unmodifiable set of all Edge objects in this Graph
	 */
	public Set<Edge<N, E>> getEdgeSets() {
		Set<Edge<N, E>> set = new HashSet<Edge<N, E>>();
		for (Set<Edge<N, E>> edgeSet: graph.values()) {
			for (Edge<N, E> e: edgeSet) {
				set.add(e);
			}
		}
		return set;
	}
	
	/** 
	 * check whether the representation invariant holds
	 */
	private void checkRep() {
		assert(graph != null): "graph cannot be null";
		if (DEBUG_FLAG) {
			Set<Node<N>> nodes = graph.keySet();
			for (Node<N> node: nodes) {
				assert(node != null) : "node cannot be null";
				Set<Edge<N, E>> edgeSet = graph.get(node);
				for (Edge<N, E> edge: edgeSet) {
					assert(edge != null) : "edge cannot be null";
				}
			}
		}
	}
}
