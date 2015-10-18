package hw8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

/**
 * Parser utility to load the Campus Buildings dataset.
 */
public class BuildingsParser {
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
     * Reads the campus dataset.
     * Each line of the input file contains a short name, a long name, a location of a building
     * separated by a tab character
     * 
     * @requires filename is a valid file path
     * @param filename the file that will be read
     * @param buildings the set of all buildings on campus
     * 			typically empty when the routine is called
     * @modifies buildings 
     * @effects fills buildings with a set of all unique Buildings on campus
     * @throws MalformedDataException if the file is not well-formed:
     *          each line contains exactly four tokens separated by a tab,
     *          or else starting with a # symbol to indicate a comment line.
     */
    public static void parseData(String filename, Set<Building> buildings) 
		  throws MalformedDataException {
    // Why does this method accept the Collections to be filled as
    // parameters rather than making them a return value? To allows us to
    // "return" two different Collections. If only one or neither Collection
    // needs to be returned to the caller, feel free to rewrite this method
    // without the parameters. Generally this is better style.
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(filename));
	
	        // Construct the collections buildings
	        String inputLine;
	        while ((inputLine = reader.readLine()) != null) {
	
	            // Ignore comment lines.
	            if (inputLine.startsWith("#")) {
	                continue;
	            }
	
	            // Parse the data, stripping out quotation marks and throwing
	            // an exception for malformed lines.
	            inputLine = inputLine.replace("\"", "");
	            String[] tokens = inputLine.split("\t");
	            if (tokens.length != 4) {
	                throw new MalformedDataException("Line should contain exactly four tabs: "
	                                                 + inputLine);
	            }
	
	            String shortName = tokens[0];
	            String longName = tokens[1];
	            Double x = Double.parseDouble(tokens[2]);
	            Double y = Double.parseDouble(tokens[3]);
	            Location location = new Location(x, y);
	            // Add the parsed data to the buildings collections.
	            Building building = new Building(longName, shortName, location);
	            buildings.add(building);
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
