package hw8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import hw5.Edge;
import hw5.Graph;
import hw5.Node;

/**
 * Parser utility to load the Campus Paths dataset.
 */
public class PathsParser {
    /**
     * A checked exception class for bad data files
     */
    @SuppressWarnings("serial")
    public static class MalformedDataException extends Exception {
        public MalformedDataException() { }

        public MalformedDataException(String message) {
            super(message);
        }

        public MalformedDataException(Throwable cause) {
            super(cause);
        }

        public MalformedDataException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Reads the campus paths dataset.
     * Each line of the input file contains a location followed by a tab-indented line for each endpoint
     * 
     * @requires filename is a valid file path
     * @param filename the file that will be read
     * @param graph in which all paths will be stored
     *          typically empty when the routine is called
     * @modifies graph
     * @effects fills graph with paths
   	 * @throws MalformedDataException if the file is not well-formed
   	 */
    public static void parseData(String filename, Graph<Location, Double> graph) 
		  throws MalformedDataException {
    // Why does this method accept the Collections to be filled as
    // parameters rather than making them a return value? To allows us to
    // "return" two different Collections. If only one or neither Collection
    // needs to be returned to the caller, feel free to rewrite this method
    // without the parameters. Generally this is better style.
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(filename));
	
	        // Construct the collections of paths
	        String inputLine;
	        Location start = null;
	        while ((inputLine = reader.readLine()) != null) {
	
	            // Ignore comment lines.
	            if (inputLine.startsWith("#")) {
	                continue;
	            }
	
	            // Parse the data, stripping out quotation marks and throwing
	            // an exception for malformed lines.
	            if (!inputLine.startsWith("\t")) {
	            	String[] tokens = inputLine.split(",");
	            	if (tokens.length != 2) {
	                    throw new MalformedDataException("Line should contain exactly one comma: " + inputLine);
	                }
	            	Double x = Double.parseDouble(tokens[0]);
	            	Double y = Double.parseDouble(tokens[1]);
	            	start = new Location(x, y);
	            	graph.addNode(new Node<Location>(start));
	            } else {
	            	String[] tokens = inputLine.split(": ");
	            	if (tokens.length != 2) {
	                    throw new MalformedDataException("Line should contain exactly one space: " + inputLine);
	                }
	            	String endString = tokens[0];
	            	Double cost = Double.parseDouble(tokens[1]);
	            	String[] tokens2 = endString.split(",");
	            	if (tokens.length != 2) {
	                    throw new MalformedDataException("Line should contain exactly one comma: " + inputLine);
	                }
	            	Double x = Double.parseDouble(tokens2[0]);
	            	Double y = Double.parseDouble(tokens2[1]);
	            	Location end = new Location(x, y);
	            	graph.addNode(new Node<Location>(end));
	            	Edge<Location, Double> edge = new Edge<Location, Double>
	            	(cost, new Node<Location>(start), new Node<Location>(end));
	            	graph.addEdge(edge);
	            }
	        }
	    } catch (IOException e) {
	        System.err.println(e.toString());
	        e.printStackTrace(System.err);
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e) {
	                System.err.println(e.toString());
	                e.printStackTrace(System.err);
	            }
	        }
	    }
    }
}
