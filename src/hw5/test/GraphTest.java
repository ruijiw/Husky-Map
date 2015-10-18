package hw5.test;

import static org.junit.Assert.*;

import java.util.Set;

import hw5.Edge;
import hw5.Graph;
import hw5.Node;

import org.junit.Test;

/**
 * This class contains a set of test cases that can be used to test the
 * implementation of the Graph class.
 */

public class GraphTest {	
	
	// simple case Nodes that are frequently used in the test
	private Node<String> a = new Node<String>("a");
	private Node<String> b = new Node<String>("b");
	private Node<String> c = new Node<String>("c");

	// simple case Edges that are frequently used in the test
    private Edge<String, String> mab = new Edge<String, String>("m", a, b);
	private Edge<String, String> nab = new Edge<String, String>("n", a, b);
	private Edge<String, String> mac = new Edge<String, String>("m", a, c);
	private Edge<String, String> mbc = new Edge<String, String>("m", b, c);
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Constructor
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testConstructor() {
		new Graph<String, String>();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Size
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testSizeZero() {
		Graph<String, String> graph = new Graph<String, String>();
		assertEquals(0, graph.size());
	}
	
	@Test
	public void testSizeOne() {
		Graph<String, String> graph = new Graph<String, String>();
		graph.addNode(a);
		assertEquals(1, graph.size());
	}
	
	@Test
	public void testSizeAddedDuplicated() {
		Graph<String, String> graph = new Graph<String, String>();
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(a);
		assertEquals(2, graph.size());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	IsEmpty
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testIsEmptyTrue() {
		Graph<String, String> graph = new Graph<String, String>();
		assertTrue(graph.isEmpty());
	}
	
	@Test
	public void testIsEmptyFalse() {
		Graph<String, String> graph = new Graph<String, String>();
		graph.addNode(a);
		assertFalse(graph.isEmpty());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	AddNode
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testAddNodeOne() {
		Graph<String, String> graph = new Graph<String, String>();
		assertTrue(graph.addNode(a));
		assertTrue(graph.containNode(a));
	}
	
	@Test
	public void testAddNodeOneDuplicate() {
		Graph<String, String> graph = new Graph<String, String>();
		assertTrue(graph.addNode(a));
		assertFalse(graph.addNode(a));
	}

	@Test
	public void testAddNodeMultiple() {
		Graph<String, String> graph = new Graph<String, String>();
		assertTrue(graph.addNode(a));
		assertTrue(graph.addNode(b));
		assertTrue(graph.addNode(c));
		assertTrue(graph.containNode(a));
		assertTrue(graph.containNode(b));
		assertTrue(graph.containNode(c));
		assertFalse(graph.addNode(a));
		assertFalse(graph.addNode(b));
		assertFalse(graph.addNode(c));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	AddEdge
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testAddEdgeWithoutTwoNode() {	
		Graph<String, String> graph = new Graph<String, String>();
		assertFalse(graph.addEdge(mab));
		assertFalse(graph.containEdge(mab));
	}
	
	@Test
	public void testAddEdgeWithoutChildrenNode() {
		Graph<String, String> graph = new Graph<String, String>();
		graph.addNode(a);
		assertFalse(graph.addEdge(mab));
		assertFalse(graph.containEdge(mab));
	}
	
	@Test
	public void testAddEdgeWithoutParentNode() {
		Graph<String, String> graph = new Graph<String, String>();
		graph.addNode(b);
		assertFalse(graph.addEdge(mab));
		assertFalse(graph.containEdge(mab));
	}
	
	@Test
	public void testAddEdgeOne() {
		Graph<String, String> graph = new Graph<String, String>();
		graph.addNode(a);
		graph.addNode(b);
		assertTrue(graph.addEdge(mab));
		assertTrue(graph.containEdge(mab));
	}
	
	@Test
	public void testAddEdgeWithSameNodes() {
		Graph<String, String> graph = new Graph<String, String>();
		graph.addNode(a);
		graph.addNode(b);
		assertTrue(graph.addEdge(mab));
		assertTrue(graph.addEdge(nab));
		assertTrue(graph.containEdge(nab));
	}
	
	@Test
	public void testAddEdgeOneDuplicate() {
		Graph<String, String> graph = new Graph<String, String>();
		graph.addNode(a);
		graph.addNode(b);
		assertTrue(graph.addEdge(mab));
		assertFalse(graph.addEdge(mab));
	}
	
	@Test
	public void testAddEdgeMultiple() {
		Graph<String, String> graph = new Graph<String, String>();
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		assertTrue(graph.addEdge(mab));
		assertTrue(graph.addEdge(mac));
		assertTrue(graph.addEdge(mbc));
		assertTrue(graph.containEdge(mab));
		assertTrue(graph.containEdge(mac));
		assertTrue(graph.containEdge(mbc));
		assertFalse(graph.addEdge(mab));
		assertFalse(graph.addEdge(mac));
		assertFalse(graph.addEdge(mbc));
	}
	
	/**
	 * @return a Graph containing all the nodes in the field
	 */
	private Graph<String, String> addNodes() {
		Graph<String, String> graph = new Graph<String, String>();
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		return graph;
	}
	
	/**
	 * @return a Graph containing all the nodes and edges in the field
	 */
	private Graph<String, String> addEdges() {
		Graph<String, String> graph = addNodes();
		assertTrue(graph.addEdge(mab));
		assertTrue(graph.addEdge(mac));
		assertTrue(graph.addEdge(mbc));
		return graph;
	}	
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	RemoveNode
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testRemoveNodeOne() {
		Graph<String, String> graph = addEdges();
		assertTrue(graph.removeNode(a)); 
		assertFalse(graph.containNode(a)); 
	}
	
	@Test
	public void testRemoveNodeDuplicate() {
		Graph<String, String> graph = addEdges();
		assertTrue(graph.removeNode(a)); 
		assertFalse(graph.removeNode(a));
	}
	
	@Test
	public void testRemoveNodeAsParentOfEdge() {
		Graph<String, String> graph = addEdges();
		assertTrue(graph.removeNode(a)); 
		assertFalse(graph.containEdge(mab));
		assertFalse(graph.containEdge(mac));
	}
	
	@Test
	public void testRemoveNodeAsChildrenOfEdge() {
		Graph<String, String> graph = addEdges();
		assertTrue(graph.removeNode(c)); 
		assertFalse(graph.containEdge(mac)); 
		assertFalse(graph.containEdge(mbc)); 
	}
	
	@Test
	public void testRemoveNodeMultiple() {
		Graph<String, String> graph = addEdges();
		assertTrue(graph.removeNode(a)); 
		assertTrue(graph.removeNode(b)); 
		assertTrue(graph.removeNode(c)); 
		assertFalse(graph.containNode(a)); 
		assertFalse(graph.containNode(b)); 
		assertFalse(graph.containNode(c)); 
		assertFalse(graph.containEdge(mab));
		assertFalse(graph.containEdge(mac));
		assertFalse(graph.containEdge(mbc));
		assertFalse(graph.removeNode(a)); 
		assertFalse(graph.removeNode(b)); 
		assertFalse(graph.removeNode(c)); 
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	RemoveEdge
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testRemoveEdgeOne() {
		Graph<String, String> graph = addEdges();
		assertTrue(graph.removeEdge(mab));
		assertFalse(graph.containEdge(mab));
	}
	
	@Test
	public void testRemoveEdgeDuplicate() {
		Graph<String, String> graph = addEdges();
		assertTrue(graph.removeEdge(mab));
		assertFalse(graph.removeEdge(mab));
	}
	
	@Test
	public void testRemoveEdgeWithSameNodes() {
		Graph<String, String> graph = addEdges();
		graph.addEdge(nab);
		assertTrue(graph.removeEdge(mab));
		assertTrue(graph.removeEdge(nab));
		assertFalse(graph.containEdge(mab));
		assertFalse(graph.containEdge(nab));
	}
	
	@Test
	public void testRemoveEdgeMultiple() {
		Graph<String, String> graph = addEdges();
		assertTrue(graph.removeEdge(mab));
		assertTrue(graph.removeEdge(mac));
		assertTrue(graph.removeEdge(mbc));
		assertFalse(graph.containEdge(mab));
		assertFalse(graph.containEdge(mac));
		assertFalse(graph.containEdge(mbc));
		assertFalse(graph.removeEdge(mab));
		assertFalse(graph.removeEdge(mbc));
		assertFalse(graph.removeEdge(mac));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	GetNodeSet
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testGetNodeSet() {
		Graph<String, String> graph = addEdges();
		Set<Node<String>> nodeSet = graph.getNodeSet();
		assertTrue(nodeSet.contains(a));
		assertTrue(nodeSet.contains(b));
		assertTrue(nodeSet.contains(c));
		assertEquals(3, nodeSet.size());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	GetEdgeSet
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testGetEdgeSet() {
		Graph<String, String> graph = addEdges();
		Set<Edge<String, String>> edgeSet = graph.getEdgeSet(a);
		assertTrue(edgeSet.contains(mab));
		assertTrue(edgeSet.contains(mac));
		assertEquals(2, edgeSet.size());
	}
	
	///////////////;////////////////////////////////////////////////////////////////////////
	////	GetEdgeSets
	///////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void testGetEdgeSets() {
		Graph<String, String> graph = addEdges();
		Set<Edge<String, String>> edgeSet = graph.getEdgeSets();
		assertTrue(edgeSet.contains(mab));
		assertTrue(edgeSet.contains(mac));
		assertTrue(edgeSet.contains(mbc));
		assertEquals(3, edgeSet.size());
	}
}
