package hw5;

/** <b>Edge<N, E></b> represents an <b> generic </b><b> immutable </b> connection between 
 * two Nodes in Graph. It starts from one node and points
 * to another node.
 * It includes a Node that represents the parent Node it 
 * starts from, a Node that represents the children Node 
 * it ends with and a E that represents the label of itself. 
 * <p>
 * Examples of Edge include "Edge:label<node1, node2>" and so on.
*/

public class Edge<N extends Comparable<N>, E extends Comparable<E>> implements Comparable<Edge<N, E>>{
	private final E label;
	private final Node<N> node1;
	private final Node<N> node2;
	
	// Abstract Function:
	// the label of an Edge is mapped to label
	// the parent Node of an Edge is mapped to node1
	// the children Node of an Edge is mapped to node2
		
	// Representation invariant:
	// data != null &&
	// node1 != null &&
	// node2 != null
	
	/**
	 * @param label the label of new Edge
	 * @param node1 the parent Node of new Edge
	 * @param node2 the children Node of new Edge
	 * @effects constructs a new Edge with a label,
	 * a parent Node and a children Node
	 */
	public Edge(E label, Node<N> node1, Node<N> node2) {
		this.label = label;
		this.node1 = node1;
		this.node2 = node2;
		checkRep();
	}
	
	/** 
	 * check whether the representation invariant holds (if any)
	 */
	private void checkRep() {
		assert(label != null) : "label of an Edge cannot be null";
		assert(node1 != null) : "parent Node of an Edge cannot be null";
		assert(node2 != null) : "children Node of an Edge cannot be null";
	}

	/** Compares two Edge.The parent node first, children node second 
	 * and label last
	 * @param edge The Edge to be compared.
	 * @requires the compared edge is not null
	 * @return a negative number if this < edge,
	 * 0 if this = edge,
	 * a positive number if this > edge.
	 */
	@Override
	public int compareTo(Edge<N, E> edge) {
		int compare1 = this.node1.compareTo(edge.node1);
		int compare2 = this.node2.compareTo(edge.node2);
		if (compare1 == 0) {
			if (compare2 == 0) {
				return this.label.compareTo(edge.label);
			}
			return compare2;
		}
		return compare1;
	}
	
	/**
	 * Returns the label of this Edge
	 * @return the label of this Edge
	 */
	public E getLabel() {
		return this.label;
	}
	
	/**
	 * Returns the parent Node of this Edge
	 * @return the parent Node of this Edge
	 */
	public Node<N> getParent() {
		return this.node1;
	}
	
	/**
	 * Returns the children Node of this Edge
	 * @return the children Node of this Edge
	 */
	public Node<N> getChildren() {
		return this.node2;
	}
	
	/** Standard hashCode function.
	 * @return an integer that all objects equal to this will also return.
	 */
	@Override
	public int hashCode() {
		return label.hashCode() + node1.hashCode() + node2.hashCode();
	}
	
	/** Standard equality operation.
	 * @param obj The object to be compared for equality.
	 * @return true if and only if 'obj' is an instance of a Edge
	 * and 'this' and 'obj' represent the same Edge.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Edge<?, ?>) {
			Edge<?, ?> edge = (Edge<?, ?>) obj;
			if (this.hashCode() != edge.hashCode()) {
		    	  return false;
		    }
			return edge.label.equals(this.label) && edge.node1.equals(this.node1)
			&& edge.node2.equals(this.node2);
		}
		return false;
	}
	
	/**
	 * Returns a String that represents this Edge
	 * @return a String representation of this Edge that contains its label,
	 * its parent Node and its children Node. 
	 */
	public String toString() {
		return "Edge:" + this.label.toString() + "<" + node1.getData().toString() 
				+ ", " + node2.getData().toString() + ">";
	}
}
