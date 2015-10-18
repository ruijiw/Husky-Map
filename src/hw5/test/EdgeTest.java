package hw5.test;

import static org.junit.Assert.*;
import hw5.Edge;
import hw5.Node;

import org.junit.Test;

public class EdgeTest {
	
	// simple base Edges
	private Edge<String, String> empty = new Edge<String, String>("", new Node<String>(""), new Node<String>(""));
	private Edge<String, String> space = new Edge<String, String>(" ", new Node<String>(" "), new Node<String>(" "));
	private Edge<String, String> string = new Edge<String, String>("I", new Node<String>("am"), new Node<String>("beautiful"));
	private Edge<String, String> longEdge = new Edge<String, String>(longString, new Node<String>(longString), new Node<String>(longString));
	private Edge<String, String> integer = new Edge<String, String>("" + 1, new Node<String>("" + 1), new Node<String>("" + 1));
	private Edge<String, String> doubleEdge = new Edge<String, String>("" + 1.11, new Node<String>("" + 1.11), new Node<String>("" + 1.11));
	
	private static String longString = "11111111111111111111111111111111111111111111";
	
	// test whether a String representation of a Node is equal to a specified String
	private void eq(Node<String> node, String rep) {
	    assertEquals(rep, node.toString());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Constructor
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testThreeArgConstructor() {
		// test constructor with two parameters
		new Edge<String, String>("", new Node<String>(""), new Node<String>(""));
		new Edge<String, String>(" ", new Node<String>(" "), new Node<String>(" "));
		new Edge<String, String>("I", new Node<String>("am"), new Node<String>("beautiful"));
		new Edge<String, String>(longString, new Node<String>(longString), new Node<String>(longString));
		new Edge<String, String>("" + 1, new Node<String>("" + 1), new Node<String>("" + 1));
		new Edge<String, String>("" + 1.11, new Node<String>("" + 1.11), new Node<String>("" + 1.11));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	getLabel
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testGetLabel() {
		assertEquals(empty.getLabel(),"");
		assertEquals(integer.getLabel(), "1");
		assertEquals(longEdge.getLabel(), "11111111111111111111111111111111111111111111");
		assertEquals(string.getLabel(), "I");
		assertEquals(space.getLabel(), " ");
		assertEquals(doubleEdge.getLabel(), "1.11");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	getParent
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testGetParent() {
		eq(empty.getParent(), "Node: ");
		eq(integer.getParent(), "Node: 1");
		eq(longEdge.getParent(), "Node: 11111111111111111111111111111111111111111111");
		eq(string.getParent(), "Node: am");
		eq(space.getParent(), "Node:  ");
		eq(doubleEdge.getParent(), "Node: 1.11");
	}

	///////////////////////////////////////////////////////////////////////////////////////
	////	getChildren
	///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testGetChildren() {
		eq(empty.getChildren(), "Node: ");
		eq(integer.getChildren(), "Node: 1");
		eq(longEdge.getChildren(), "Node: 11111111111111111111111111111111111111111111");
		eq(string.getChildren(), "Node: beautiful");
		eq(space.getChildren(), "Node:  ");
		eq(doubleEdge.getChildren(), "Node: 1.11");
	}

	// test whether the String representation of an Edge is equal to the specified String
	private void eq2(Edge<String, String> edge, String rep) {
		assertEquals(rep, edge.toString());
	}

	///////////////////////////////////////////////////////////////////////////////////////
	////	toString
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testToString() {
		eq2(empty, "Edge:<, >");
		eq2(integer, "Edge:1<1, 1>");
		eq2(longEdge, "Edge:11111111111111111111111111111111111111111111<111111111111111111"
				+ "11111111111111111111111111, 11111111111111111111111111111111111111111111>");
		eq2(string, "Edge:I<am, beautiful>");
		eq2(space, "Edge: < ,  >");
		eq2(doubleEdge, "Edge:1.11<1.11, 1.11>");
	}

	///////////////////////////////////////////////////////////////////////////////////////
	////	equals
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEqualsSymmetric() {
		// test the symmetric property of equality
		Edge<String, String> duplicateEdge = new Edge<String, String>(empty.getLabel(), empty.getParent(), empty.getChildren());
		assertEquals(duplicateEdge, empty);
		assertEquals(empty, duplicateEdge);
		
		Edge<String, String> duplicateEdge1 = new Edge<String, String>(integer.getLabel(), integer.getParent(), integer.getChildren());
		assertEquals(duplicateEdge1, integer);
		assertEquals(integer, duplicateEdge1);
		
		Edge<String, String> duplicateEdge2 = new Edge<String, String>(longEdge.getLabel(), longEdge.getParent(), longEdge.getChildren());
		assertEquals(duplicateEdge2, longEdge);
		assertEquals(longEdge, duplicateEdge2);
		
		Edge<String, String> duplicateEdge3 = new Edge<String, String>(string.getLabel(), string.getParent(), string.getChildren());
		assertEquals(duplicateEdge3, string);
		assertEquals(string, duplicateEdge3);
		
		Edge<String, String> duplicateEdge4 = new Edge<String, String>(space.getLabel(), space.getParent(), space.getChildren());
		assertEquals(duplicateEdge4, space);
		assertEquals(space, duplicateEdge4);
		
		Edge<String, String> duplicateEdge5 = new Edge<String, String>(doubleEdge.getLabel(), doubleEdge.getParent(), doubleEdge.getChildren());
		assertEquals(duplicateEdge5, doubleEdge);
		assertEquals(doubleEdge, duplicateEdge5);
	}

	@Test
	public void testEqualsTransitive() {
		// test the transitive property of equality
		Edge<String, String> duplicateEdge = new Edge<String, String>(empty.getLabel(), empty.getParent(), empty.getChildren());
		Edge<String, String> copyDuplicate = new Edge<String, String>(duplicateEdge.getLabel(), duplicateEdge.getParent(), duplicateEdge.getChildren());
		assertEquals(duplicateEdge, empty);
		assertEquals(duplicateEdge, copyDuplicate);
		assertEquals(empty, copyDuplicate);
		
		Edge<String, String> duplicateEdge1 = new Edge<String, String>(integer.getLabel(), integer.getParent(), integer.getChildren());
		Edge<String, String> copyDuplicate1 = new Edge<String, String>(duplicateEdge1.getLabel(), duplicateEdge1.getParent(), duplicateEdge1.getChildren());
		assertEquals(duplicateEdge1, integer);
		assertEquals(duplicateEdge1, copyDuplicate1);
		assertEquals(integer, copyDuplicate1);
		
		Edge<String, String> duplicateEdge2 = new Edge<String, String>(longEdge.getLabel(), longEdge.getParent(), longEdge.getChildren());
		Edge<String, String> copyDuplicate2 = new Edge<String, String>(duplicateEdge2.getLabel(), duplicateEdge2.getParent(), duplicateEdge2.getChildren());
		assertEquals(duplicateEdge2, longEdge);
		assertEquals(duplicateEdge2, copyDuplicate2);
		assertEquals(longEdge, copyDuplicate2);
		
		Edge<String, String> duplicateEdge3 = new Edge<String, String>(string.getLabel(), string.getParent(), string.getChildren());
		Edge<String, String> copyDuplicate3 = new Edge<String, String>(duplicateEdge3.getLabel(), duplicateEdge3.getParent(), duplicateEdge3.getChildren());
		assertEquals(duplicateEdge3, string);
		assertEquals(duplicateEdge3, copyDuplicate3);
		assertEquals(string, copyDuplicate3);	
		
		Edge<String, String> duplicateEdge4 = new Edge<String, String>(space.getLabel(), space.getParent(), space.getChildren());
		Edge<String, String> copyDuplicate4 = new Edge<String, String>(duplicateEdge4.getLabel(), duplicateEdge4.getParent(), duplicateEdge4.getChildren());
		assertEquals(duplicateEdge4, space);
		assertEquals(duplicateEdge4, copyDuplicate4);
		assertEquals(space, copyDuplicate4);
		
		Edge<String, String> duplicateEdge5 = new Edge<String, String>(doubleEdge.getLabel(), doubleEdge.getParent(), doubleEdge.getChildren());
		Edge<String, String> copyDuplicate5 = new Edge<String, String>(duplicateEdge5.getLabel(), duplicateEdge5.getParent(), duplicateEdge5.getChildren());
		assertEquals(duplicateEdge5, doubleEdge);
		assertEquals(duplicateEdge5, copyDuplicate5);
		assertEquals(doubleEdge, copyDuplicate5);
	}
	
	@Test
	public void testEqualsReflexive() {
		// test the reflexive property of equality
		assertEquals(empty, empty);
		assertEquals(integer, integer);
		assertEquals(longEdge, longEdge);
		assertEquals(string, string);
		assertEquals(space, space);
		assertEquals(doubleEdge, doubleEdge);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////  hashCode
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testHashCodeSelfConsistent() {
		assertEquals(integer.hashCode(), integer.hashCode());
		assertEquals(string.hashCode(), string.hashCode());
		assertEquals(space.hashCode(), space.hashCode());
		assertEquals(longEdge.hashCode(), longEdge.hashCode());
		assertEquals(empty.hashCode(), empty.hashCode());
		assertEquals(doubleEdge.hashCode(), doubleEdge.hashCode());
	}
	
	@Test
	public void testHashCodeConsistentWithEquality() {
		Edge<String, String> duplicateEdge = new Edge<String, String>(empty.getLabel(), empty.getParent(), empty.getChildren());
		assertEquals(empty.equals(duplicateEdge), empty.hashCode() == duplicateEdge.hashCode());
		
		Edge<String, String> duplicateEdge1 = new Edge<String, String>(integer.getLabel(), integer.getParent(), integer.getChildren());
		assertEquals(integer.equals(duplicateEdge1), integer.hashCode() == duplicateEdge1.hashCode());
		
		Edge<String, String> duplicateEdge2 = new Edge<String, String>(longEdge.getLabel(), longEdge.getParent(), longEdge.getChildren());
		assertEquals(longEdge.equals(duplicateEdge2), longEdge.hashCode() == duplicateEdge2.hashCode());
		
		Edge<String, String> duplicateEdge3 = new Edge<String, String>(string.getLabel(), string.getParent(), string.getChildren());
		assertEquals(string.equals(duplicateEdge3), string.hashCode() == duplicateEdge3.hashCode());
		
		Edge<String, String> duplicateEdge4 = new Edge<String, String>(space.getLabel(), space.getParent(), space.getChildren());
		assertEquals(space.equals(duplicateEdge4), space.hashCode() == duplicateEdge4.hashCode());
		
		Edge<String, String> duplicateEdge5 = new Edge<String, String>(doubleEdge.getLabel(), doubleEdge.getParent(), doubleEdge.getChildren());
		assertEquals(doubleEdge.equals(duplicateEdge5), doubleEdge.hashCode() == duplicateEdge5.hashCode());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////  Compare
	///////////////////////////////////////////////////////////////////////////////////////		
	
	@Test
	public void testCompareTo() {
		assertEquals(integer.compareTo(string), integer.getParent().compareTo(string.getParent()));
		Edge<String, String> string2 = new Edge<String, String>("You", new Node<String>("am"), new Node<String>("beautiful"));
		assertEquals(string.compareTo(string2), string.getLabel().compareTo(string2.getLabel()));
		Edge<String, String> string3 = new Edge<String, String>("I", new Node<String>("am"), new Node<String>("ugly"));
		assertEquals(string.compareTo(string3), string.getChildren().compareTo(string3.getChildren()));
	}
}
