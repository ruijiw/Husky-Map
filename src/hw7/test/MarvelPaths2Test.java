package hw7.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import hw5.Edge;
import hw5.Graph;
import hw5.Node;
import hw6.MarvelParser.MalformedDataException;
import hw7.MarvelPaths2;

import org.junit.Test;

public class MarvelPaths2Test {

	///////////////////////////////////////////////////////////////////////////////////////
	////	loadGraph
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEmptyCreate() throws MalformedDataException {
		Graph<String, Double> graph = MarvelPaths2.loadGraph("src/hw7/data/testEmpty.tsv");
		assertEquals(graph.size(), 0);
		assertEquals(graph.getEdgeSets().size(), 0);
	}
	
	@Test
	public void testOneNodeCreate() throws MalformedDataException {
		Graph<String, Double> graph = MarvelPaths2.loadGraph("src/hw7/data/testOneNode.tsv");
		assertTrue(graph.containNode(new Node<String>("a")));
		assertEquals(graph.getEdgeSets().size(), 0);
	}
	
	@Test
	public void testOneEdgeCreate() throws MalformedDataException {
		Graph<String, Double> graph = MarvelPaths2.loadGraph("src/hw7/data/testOneEdge.tsv");
		assertTrue(graph.containNode(new Node<String>("a")));
		assertTrue(graph.containNode(new Node<String>("b")));
		assertTrue(graph.containEdge(new Edge<String, Double>(0.5, new Node<String>("a"), new Node<String>("b"))));
		assertTrue(graph.containEdge(new Edge<String, Double>(0.5, new Node<String>("b"), new Node<String>("a"))));
		assertEquals(graph.getEdgeSets().size(), 2);
	}
	
	@Test
	public void testMultipleNodesCreate() throws MalformedDataException {
		Graph<String, Double> graph = MarvelPaths2.loadGraph("src/hw7/data/testMultipleNodes.tsv");
		assertEquals(graph.size(), 3);
		assertEquals(graph.getEdgeSets().size(), 2);
	}
	
	@Test(expected=MalformedDataException.class)
	public void testExceptionCreate() throws MalformedDataException {
		MarvelPaths2.loadGraph("src/hw7/data/testException.tsv");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	findPath
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEmptyFind() throws MalformedDataException {
		Graph<String, Double> graph = MarvelPaths2.loadGraph("src/hw7/data/testEmpty.tsv");
		List<Edge<String, Double>> edges = MarvelPaths2.findPath("a", "a", graph);
		assertEquals(edges.size(), 0);
	}
	
	@Test
	public void testOneNodeFind() throws MalformedDataException {
		Graph<String, Double> graph = MarvelPaths2.loadGraph("src/hw7/data/testOneNode.tsv");
		List<Edge<String, Double>> edges = MarvelPaths2.findPath("a", "a", graph);
		assertEquals(edges.size(), 1);
	}
	
	@Test
	public void testOneEdgeFind() throws MalformedDataException {
		Graph<String, Double> graph = MarvelPaths2.loadGraph("src/hw7/data/testOneEdge.tsv");
		List<Edge<String, Double>> edges1 = MarvelPaths2.findPath("a", "b", graph);
		assertEquals(edges1.size(), 2);
		List<Edge<String, Double>> edges2 = MarvelPaths2.findPath("b", "a", graph);
		assertEquals(edges2.size(), 2);
	}
	
	@Test
	public void testPathNotFound() throws MalformedDataException {
		Graph<String, Double> graph = MarvelPaths2.loadGraph("src/hw7/data/testMultipleNodes.tsv");
		List<Edge<String, Double>> edges = MarvelPaths2.findPath("a", "c", graph);
		assertEquals(edges.size(), 0);
	}
	
	@Test
	public void testPathFoundAlphabetically() throws MalformedDataException {
		Graph<String, Double> graph = MarvelPaths2.loadGraph("src/hw7/data/testMultipleNodes.tsv");
		List<Edge<String, Double>> edges1 = MarvelPaths2.findPath("b", "a", graph);
		assertEquals(edges1.size(), 2);
		List<Edge<String, Double>> edges2 = new ArrayList<Edge<String, Double>>();
		edges2.add(new Edge<String, Double>(0.0, new Node<String>("b"), new Node<String>("b")));
		edges2.add(new Edge<String, Double>(0.5, new Node<String>("b"), new Node<String>("a")));
		assertEquals(edges1, edges2);
	}
}
