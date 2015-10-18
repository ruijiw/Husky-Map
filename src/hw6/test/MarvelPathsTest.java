package hw6.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;
























import hw5.Edge;
import hw5.Graph;
import hw5.Node;
import hw6.MarvelParser.MalformedDataException;
import hw6.MarvelPaths;

public class MarvelPathsTest {
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	loadGraph
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEmptyCreate() throws MalformedDataException {
		Graph<String, String> graph = MarvelPaths.loadGraph("src/hw6/data/testEmpty.tsv");
		assertEquals(graph.size(), 0);
		assertEquals(graph.getEdgeSets().size(), 0);
	}
	
	@Test
	public void testOneNodeCreate() throws MalformedDataException {
		Graph<String, String> graph = MarvelPaths.loadGraph("src/hw6/data/testOneNode.tsv");
		assertTrue(graph.containNode(new Node<String>("a")));
		assertEquals(graph.getEdgeSets().size(), 0);
	}
	
	@Test
	public void testOneEdgeCreate() throws MalformedDataException {
		Graph<String, String> graph = MarvelPaths.loadGraph("src/hw6/data/testOneEdge.tsv");
		assertTrue(graph.containNode(new Node<String>("a")));
		assertTrue(graph.containNode(new Node<String>("b")));
		assertTrue(graph.containEdge(new Edge<String, String>("1", new Node<String>("a"), new Node<String>("b"))));
		assertTrue(graph.containEdge(new Edge<String, String>("1", new Node<String>("b"), new Node<String>("a"))));
	}
	
	@Test
	public void testMultipleNodesCreate() throws MalformedDataException {
		Graph<String, String> graph = MarvelPaths.loadGraph("src/hw6/data/testMultipleNodes.tsv");
		assertEquals(graph.size(), 3);
		assertEquals(graph.getEdgeSets().size(), 4);
	}
	
	@Test(expected=MalformedDataException.class)
	public void testExceptionCreate() throws MalformedDataException {
		MarvelPaths.loadGraph("src/hw6/data/testException.tsv");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	findPath
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEmptyFind() throws MalformedDataException {
		Graph<String, String> graph = MarvelPaths.loadGraph("src/hw6/data/testEmpty.tsv");
		List<Edge<String, String>> edges = MarvelPaths.findPath("a", "a", graph);
		assertEquals(edges.size(), 0);
	}
	
	@Test
	public void testOneNodeFind() throws MalformedDataException {
		Graph<String, String> graph = MarvelPaths.loadGraph("src/hw6/data/testOneNode.tsv");
		List<Edge<String, String>> edges = MarvelPaths.findPath("a", "a", graph);
		assertEquals(edges.size(), 0);
	}
	
	@Test
	public void testOneEdgeFind() throws MalformedDataException {
		Graph<String, String> graph = MarvelPaths.loadGraph("src/hw6/data/testOneEdge.tsv");
		List<Edge<String, String>> edges1 = MarvelPaths.findPath("a", "b", graph);
		assertEquals(edges1.size(), 1);
		List<Edge<String, String>> edges2 = MarvelPaths.findPath("b", "a", graph);
		assertEquals(edges2.size(), 1);
	}
	
	@Test
	public void testPathNotFound() throws MalformedDataException {
		Graph<String, String> graph = MarvelPaths.loadGraph("src/hw6/data/testMultipleNodes.tsv");
		List<Edge<String, String>> edges = MarvelPaths.findPath("a", "c", graph);
		assertEquals(edges.size(), 0);
	}
	
	@Test
	public void testPathFoundAlphabetically() throws MalformedDataException {
		Graph<String, String> graph = MarvelPaths.loadGraph("src/hw6/data/testMultipleNodes.tsv");
		List<Edge<String, String>> edges1 = MarvelPaths.findPath("b", "a", graph);
		assertEquals(edges1.size(), 1);
		List<Edge<String, String>> edges2 = new ArrayList<Edge<String, String>>();
		edges2.add(new Edge<String, String>("1", new Node<String>("b"), new Node<String>("a")));
		assertEquals(edges1, edges2);
	}
}