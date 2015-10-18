package hw7.test;

import hw5.Edge;
import hw5.Graph;
import hw5.Node;
import hw7.MarvelPaths2;
import hw6.MarvelParser.MalformedDataException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;


/**
 * This class implements a testing driver which reads test scripts
 * from files for your graph ADT and improved MarvelPaths application
 * using Dijkstra's algorithm.
 **/
public class HW7TestDriver {

	public static void main(String args[]) {
        try {
            if (args.length > 1) {
                printUsage();
                return;
            }

            HW7TestDriver td;

            if (args.length == 0) {
                td = new HW7TestDriver(new InputStreamReader(System.in),
                                       new OutputStreamWriter(System.out));
            } else {

                String fileName = args[0];
                File tests = new File (fileName);

                if (tests.exists() || tests.canRead()) {
                    td = new HW7TestDriver(new FileReader(tests),
                                           new OutputStreamWriter(System.out));
                } else {
                    System.err.println("Cannot read from " + tests.toString());
                    printUsage();
                    return;
                }
            }

            td.runTests();

        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace(System.err);
        }
    }

    private static void printUsage() {
        System.err.println("Usage:");
        System.err.println("to read from a file: java hw5.test.HW5TestDriver <name of input script>");
        System.err.println("to read from standard in: java hw5.test.HW5TestDriver");
    }

    /** String -> Graph: maps the names of graphs to the actual graph **/
    //TODO for the student: Parameterize the next line correctly.
    private final Map<String, Graph<String, Double>> graphs = new HashMap<String, Graph<String, Double>>();
    private final PrintWriter output;
    private final BufferedReader input;

    /**
     * @requires r != null && w != null
     *
     * @effects Creates a new HW5TestDriver which reads command from
     * <tt>r</tt> and writes results to <tt>w</tt>.
     **/
    public HW7TestDriver(Reader r, Writer w) {
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    /**
     * @effects Executes the commands read from the input and writes results to the output
     * @throws IOException if the input or output sources encounter an IOException
     **/
    public void runTests()
        throws IOException
    {
        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            if ((inputLine.trim().length() == 0) ||
                (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            }
            else
            {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if (st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<String>();
                    while (st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    private void executeCommand(String command, List<String> arguments) {
        try {
            if (command.equals("CreateGraph")) {
                createGraph(arguments);
            } else if (command.equals("AddNode")) {
                addNode(arguments);
            } else if (command.equals("AddEdge")) {
                addEdge(arguments);
            } else if (command.equals("ListNodes")) {
                listNodes(arguments);
            } else if (command.equals("ListChildren")) {
                listChildren(arguments);
            } else if (command.equals("LoadGraph")){
            	loadGraph(arguments);
            } else if (command.equals("FindPath")){
            	findPath(arguments);
            } else {
                output.println("Unrecognized command: " + command);
            }
        } catch (Exception e) {
            output.println("Exception: " + e.toString());
        }
    }
    
	private void findPath(List<String> arguments) {
		if (arguments.size() != 3) {
			throw new CommandException("Bad arguments to FindPath: " + arguments);
		}
		String graphName = arguments.get(0);
		String start = arguments.get(1).replace("_", " ");
		String dest = arguments.get(2).replace("_", " ");
		findPath(graphName, start, dest);
	}
	
	private void findPath(String graphName, String start, String dest) {
		Graph<String, Double> graph = graphs.get(graphName);
		List<Edge<String, Double>> edges = MarvelPaths2.findPath(start, dest, graph);
		if (!graph.containNode(new Node<String>(start)) || !graph.containNode(new Node<String>(dest))){
			if (!graph.containNode(new Node<String>(start))) {
				output.println("unknown character " + start);
			}
			if (!graph.containNode(new Node<String>(dest))) {
				output.println("unknown character " + dest);
			}
		} else if (start.equals(dest)) {
			output.println("path from " + start + " to " + dest + ":");
			output.println("total cost: 0.000");
		} else if (edges.isEmpty()) {
			output.println("path from " + start + " to " + dest + ":");
			output.println("no path found");
		} else {
			output.println("path from " + start + " to " + dest + ":");
			double cost = 0;
			for (Edge<String, Double> edge: edges) {
				if (!edge.getParent().equals(edge.getChildren())) {
					String weightString = String.format("%.3f", edge.getLabel());
					output.println(edge.getParent().getData() + " to " + edge.getChildren().getData() + 
							" with weight " + weightString);
					cost = cost + edge.getLabel();
				}
			}
			String totalCost =String.format("%.3f", cost);
			output.println("total cost: " + totalCost);
		}
	}

	private void loadGraph(List<String> arguments) throws MalformedDataException {
		if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to LoadGraph: " + arguments);
        }
		String graphName = arguments.get(0);
		String fileName = arguments.get(1);
		loadGraph(graphName, fileName);	
	}

	private void loadGraph(String graphName, String fileName) throws MalformedDataException {
		Graph<String, Double> graph = MarvelPaths2.loadGraph("src/hw7/data/" + fileName);
		graphs.put(graphName, graph);
		output.println("loaded graph " + graphName);
	}

    private void createGraph(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
    	graphs.put(graphName, new Graph<String, Double>());
        output.println("created graph " + graphName);
    }

    private void addNode(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to addNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
    	Graph<String, Double> graph = graphs.get(graphName);
        if (graph.addNode(new Node<String>(nodeName))) {
        	output.println("added node " + nodeName + " to " + graphName);
        }
    }

    private void addEdge(List<String> arguments) {
        if (arguments.size() != 4) {
            throw new CommandException("Bad arguments to addEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        String edgeLabel = arguments.get(3);
        Double weight = Double.parseDouble(edgeLabel);

        addEdge(graphName, parentName, childName, weight);
    }

    private void addEdge(String graphName, String parentName, String childName,
            Double weight) {
    	Graph<String, Double> graph = graphs.get(graphName);
        Edge<String, Double> edge = new Edge<String, Double>(weight, new Node<String>(parentName), new Node<String>(childName));
        if (graph.addEdge(edge)) {
        	String weightString = String.format("%.3f", weight);
        	output.println("added edge " + weightString + " from " + parentName + " to " + childName + " in "
        		+ graphName);
        }
    }

    private void listNodes(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to listNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
    	Graph<String, Double> graph = graphs.get(graphName);
    	Set<Node<String>> nodes = graph.getNodeSet();
    	Set<Node<String>> treeNodes = new TreeSet<Node<String>>(nodes);
        output.print(graphName + " contains:");
        for (Node<String> node: treeNodes) {
        	output.print(" " + node.getData());
        }
        output.println();
    }

    private void listChildren(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to listChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {
    	Graph<String, Double> graph = graphs.get(graphName);
        Set<Edge<String, Double>> edges = graph.getEdgeSet(new Node<String>(parentName));
        Set<Edge<String, Double>> treeEdges = new TreeSet<Edge<String, Double>>(edges);
        output.print("the children of " + parentName + " in " + graphName + " are:");
        for (Edge<String, Double> edge: treeEdges) {
        	String weightString = String.format("%.3f", edge.getLabel());
        	output.print(" "+ edge.getChildren().getData() + "(" + weightString + ")");
        }
        output.println();
    }

    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }
        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }
}
