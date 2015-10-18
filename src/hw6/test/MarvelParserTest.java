package hw6.test;

import static org.junit.Assert.*;
import hw6.MarvelParser;
import hw6.MarvelParser.MalformedDataException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MarvelParserTest {
	
	@Test
	public void testEmptyParse() throws MalformedDataException { 
		Set<String> characters = new HashSet<String>();
		Map<String, List<String>> books = new HashMap<String, List<String>>();
		MarvelParser.parseData("src/hw6/data/testEmpty.tsv", characters, books);
		assertEquals(characters.size(), 0);
		assertEquals(books.values().size(), 0);
	}
	
	@Test
	public void testOneNodeParse() throws MalformedDataException {
		Set<String> characters = new HashSet<String>();
		Map<String, List<String>> books = new HashMap<String, List<String>>();
		MarvelParser.parseData("src/hw6/data/testOneNode.tsv", characters, books);
		assertTrue(characters.contains("a"));
		assertEquals(characters.size(), 1);
		assertEquals(books.values().size(), 1);
	}
	
	@Test
	public void testOneEdgeParse() throws MalformedDataException {
		Set<String> characters = new HashSet<String>();
		Map<String, List<String>> books = new HashMap<String, List<String>>();
		MarvelParser.parseData("src/hw6/data/testOneEdge.tsv", characters, books);
		assertTrue(characters.contains("a"));
		assertTrue(characters.contains("b"));
		assertEquals(characters.size(), 2);
		assertEquals(books.values().size(), 1);
	}
	
	@Test
	public void testMultipleNodesParse() throws MalformedDataException {
		Set<String> characters = new HashSet<String>();
		Map<String, List<String>> books = new HashMap<String, List<String>>();
		MarvelParser.parseData("src/hw6/data/testMultipleNodes.tsv", characters, books);
		assertEquals(characters.size(), 3);
		assertEquals(books.values().size(),3);
	}
	
	@Test(expected=MalformedDataException.class)
	public void testExceptionParse() throws MalformedDataException {
		Set<String> characters = new HashSet<String>();
		Map<String, List<String>> books = new HashMap<String, List<String>>();
		MarvelParser.parseData("src/hw6/data/testException.tsv", characters, books);
	}
}