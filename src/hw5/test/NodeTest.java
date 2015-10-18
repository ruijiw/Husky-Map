package hw5.test;

import static org.junit.Assert.*;
import hw5.Node;

import org.junit.Test;

/**
 * This class contains a set of test cases that can be used to test the
 * implementation of the Node class.
 */

public class NodeTest {
	
	// simple base Nodes
	private Node<String> integer = new Node<String>("" + 1);
	private Node<String> empty = new Node<String>("");
	private Node<String> longNode = new Node<String>("11111111111111111111111111111111111111111111");
	private Node<String> string = new Node<String>("string");
	private Node<String> space = new Node<String>(" ");
	private Node<String> doubleNode = new Node<String>("" + 1.11);
	
	// test whether a String representation of a Node is equal to a specified String
	private void eq(Node<String> node, String rep) {
	    assertEquals(rep, node.toString());
	}

	///////////////////////////////////////////////////////////////////////////////////////
	////  Constructor
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testConstructor() {
	    new Node<String>("");
	    new Node<String>("11111111111111111111111111111111111111111111");
	    new Node<String>("string");
	    new Node<String>("" + 1);
	    new Node<String>(" ");
	    new Node<String>("1.11");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////  getData
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testgetData() {
		assertEquals(integer.getData(), "1");
		assertEquals(empty.getData(), "");
		assertEquals(longNode.getData(), "11111111111111111111111111111111111111111111");
		assertEquals(string.getData(), "string");
		assertEquals(space.getData(), " ");
		assertEquals(doubleNode.getData(), "1.11");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////  String
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testToString() {
		eq(integer, "Node: 1");
		eq(empty, "Node: ");
	    eq(longNode, "Node: " + "11111111111111111111111111111111111111111111");
	    eq(string, "Node: string");
	    eq(space, "Node:  ");
	    eq(doubleNode, "Node: 1.11");

	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////  equals
	///////////////////////////////////////////////////////////////////////////////////////	
	
	// test the reflexive property of equality
	@Test
	public void testEqualsReflexive() {
		assertEquals(integer, integer);
		assertEquals(empty, empty);
		assertEquals(longNode, longNode);
		assertEquals(string, string);
		assertEquals(space, space);
		assertEquals(doubleNode, doubleNode);
	}
	
	// test the symmetric property of equality
	@Test
	public void testEqualsSymmetric() {
		Node<String> duplicateNode = new Node<String>(integer.getData());
		assertEquals(integer, duplicateNode);
		assertEquals(duplicateNode, integer);
		
		Node<String> duplicateNode1 = new Node<String>(string.getData());
		assertEquals(string, duplicateNode1);
		assertEquals(duplicateNode1, string);
		
		Node<String> duplicateNode2 = new Node<String>(space.getData());
		assertEquals(space, duplicateNode2);
		assertEquals(duplicateNode2, space);
		
		Node<String> duplicateNode3 = new Node<String>(longNode.getData());
		assertEquals(longNode, duplicateNode3);
		assertEquals(duplicateNode3, longNode);
		
		Node<String> duplicateNode4 = new Node<String>(empty.getData());
		assertEquals(empty, duplicateNode4);
		assertEquals(duplicateNode4, empty);
		
		Node<String> duplicateNode5 = new Node<String>(doubleNode.getData());
		assertEquals(doubleNode, duplicateNode5);
		assertEquals(duplicateNode5, doubleNode);
	}
	
	// test the transitive property of equality
	@Test
	public void testEqualsTransitive() {
		Node<String> duplicateNode = new Node<String>(empty.getData());
		Node<String> copyDuplicate = new Node<String>(duplicateNode.getData());
		assertEquals(empty, duplicateNode);
		assertEquals(duplicateNode, copyDuplicate);
		assertEquals(copyDuplicate, empty);	
		
		Node<String> duplicateNode1 = new Node<String>(integer.getData());
		Node<String> copyDuplicate1 = new Node<String>(duplicateNode1.getData());
		assertEquals(integer, duplicateNode1);
		assertEquals(duplicateNode1, copyDuplicate1);
		assertEquals(copyDuplicate1, integer);	
		
		Node<String> duplicateNode2 = new Node<String>(space.getData());
		Node<String> copyDuplicate2 = new Node<String>(duplicateNode2.getData());
		assertEquals(space, duplicateNode2);
		assertEquals(duplicateNode2, copyDuplicate2);
		assertEquals(copyDuplicate2, space);
		
		Node<String> duplicateNode3 = new Node<String>(longNode.getData());
		Node<String> copyDuplicate3 = new Node<String>(duplicateNode3.getData());
		assertEquals(longNode, duplicateNode3);
		assertEquals(duplicateNode3, copyDuplicate3);
		assertEquals(copyDuplicate3, longNode);	
		
		Node<String> duplicateNode4 = new Node<String>(string.getData());
		Node<String> copyDuplicate4 = new Node<String>(duplicateNode4.getData());
		assertEquals(string, duplicateNode4);
		assertEquals(duplicateNode4, copyDuplicate4);
		assertEquals(copyDuplicate4, string);
		
		Node<String> duplicateNode5 = new Node<String>(doubleNode.getData());
		Node<String> copyDuplicate5 = new Node<String>(duplicateNode5.getData());
		assertEquals(doubleNode, duplicateNode5);
		assertEquals(duplicateNode5, copyDuplicate5);
		assertEquals(copyDuplicate5, doubleNode);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////  hashCode
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testHashCodeSelfConsistent() {
		assertEquals(integer.hashCode(), integer.hashCode());
		assertEquals(string.hashCode(), string.hashCode());
		assertEquals(space.hashCode(), space.hashCode());
		assertEquals(longNode.hashCode(), longNode.hashCode());
		assertEquals(empty.hashCode(), empty.hashCode());
		assertEquals(doubleNode.hashCode(), doubleNode.hashCode());
	}
	
	@Test
	public void testHashCodeConsistentWithEquality() {
		Node<String> duplicateNode = new Node<String>(integer.getData());
		assertEquals(integer.equals(duplicateNode), integer.hashCode() == duplicateNode.hashCode());
		
		Node<String> duplicateNode1 = new Node<String>(string.getData());
		assertEquals(string.equals(duplicateNode1), string.hashCode() == duplicateNode1.hashCode());
		
		Node<String> duplicateNode2 = new Node<String>(space.getData());
		assertEquals(space.equals(duplicateNode2), space.hashCode() == duplicateNode2.hashCode());
		
		Node<String> duplicateNode3 = new Node<String>(longNode.getData());
		assertEquals(longNode.equals(duplicateNode3), longNode.hashCode() == duplicateNode3.hashCode());
		
		Node<String> duplicateNode4 = new Node<String>(empty.getData());
		assertEquals(empty.equals(duplicateNode4), empty.hashCode() == duplicateNode4.hashCode());
		
		Node<String> duplicateNode5 = new Node<String>(doubleNode.getData());
		assertEquals(doubleNode.equals(duplicateNode5), doubleNode.hashCode() == duplicateNode5.hashCode());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////  Compare
	///////////////////////////////////////////////////////////////////////////////////////		
	
	@Test
	public void testCompareTo() {
		assertEquals(integer.compareTo(string), integer.getData().compareTo(string.getData()));
		assertEquals(space.compareTo(string), space.getData().compareTo(string.getData()));
		assertEquals(space.compareTo(longNode), space.getData().compareTo(longNode.getData()));
		assertEquals(empty.compareTo(longNode), empty.getData().compareTo(longNode.getData()));
		assertEquals(empty.compareTo(doubleNode), empty.getData().compareTo(doubleNode.getData()));
		assertEquals(integer.compareTo(doubleNode), integer.getData().compareTo(doubleNode.getData()));
		assertEquals(string.compareTo(string), string.getData().compareTo(string.getData()));
	}
}
