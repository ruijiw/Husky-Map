package hw8.test;

import static org.junit.Assert.*;
import hw5.Graph;
import hw8.Location;
import hw8.PathsParser;
import hw8.PathsParser.MalformedDataException;

import org.junit.Test;

public class PathsParserTest {

	@Test
	// test if the file to load is empty
	public void testEmptyParse() throws MalformedDataException {
		Graph<Location, Double> graph = new Graph<Location, Double>();
		PathsParser.parseData("src/hw8/data/emptyLocation.dat", graph);
		assertEquals(graph.size(), 0);
		assertEquals(graph.getEdgeSets().size(), 0);
	}
	
	@Test
	// test if the file to load has one location
	public void tesOneParse() throws MalformedDataException {
		Graph<Location, Double> graph = new Graph<Location, Double>();
		PathsParser.parseData("src/hw8/data/oneLocation.dat", graph);
		assertEquals(graph.size(), 4);
		assertEquals(graph.getEdgeSets().size(), 3);
	}
	
	@Test
	// test if the file to load has two locations
	public void tesTwoParse() throws MalformedDataException {
		Graph<Location, Double> graph = new Graph<Location, Double>();
		PathsParser.parseData("src/hw8/data/twoLocations.dat", graph);
		assertEquals(graph.size(), 8);
		assertEquals(graph.getEdgeSets().size(), 6);
	}
}
