package hw5;

/** <b>Node<N></b> represents an <b> generic </b> <b> immutable </b> vertex in a Graph. 
 * It includes a N that represents the data of itself. 
 * <p>
 * Examples of Node include "Node: abc", "Node: def" and so on.
 */

public class Node<N extends Comparable<N>> implements Comparable<Node<N>>{
	private final N data;
	
	//Abstract Function:
	// the data of an Node is mapped to data

	// Representation invariant:
	// data != null 
	
	/**
	 * @param data the data of new Node
	 * @effects constructs a Node with the specified data
	 */
	public Node(N data){
		this.data = data;
		checkRep();
	}
	
	/**
	 * Checks that the representation invariant holds (if any).
	 */
	private void checkRep() {
		assert(this.data != null): "Data of a Node cannot be null";
	}
	
	/** Compares two Node.
	 * @param node The Node to be compared.
	 * @requires the compared node is not null
	 * @return a negative number if this < node,
	 * 0 if this = node,
	 * a positive number if this > node.
	 */
	@Override
	public int compareTo(Node<N> node) {
		return (this.data).compareTo(node.data);
	}
		
	/**
	 * Returns the data of this Node
	 * @return the data of this Node
	 */
	public N getData() {
		return this.data;
	}
	
	/** Standard hashCode function.
     * @return an int that all objects equal to this will also
     * return.
     */
	@Override
	public int hashCode() {
		return data.hashCode();
	}
	
	/**
	 * Standard equality operation.
	 * @param obj The object to be compared for equality.
	 * @return true iff 'obj' is an instance of a Node and 'this' and 'obj'
	 *         represent the same Node. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Node<?>) {
			Node<?> node = (Node<?>) obj;
			if (this.hashCode() != node.hashCode()) {
				return false;
			}
			return this.data.equals(node.data);
		}
		return false;
	}
	
	/** 
	 * returns a string to represent this node.
	 * @return a String representing the data of this node.
	 */
	@Override
	public String toString() {
		return "Node: " + this.data.toString();
	}
}
