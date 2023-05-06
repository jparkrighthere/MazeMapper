
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.List;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class DataWranglerTests {
    private MazeReaderDW<String, Integer> mazeReader; // This is to read the maze.dot files
    private BaseGraph<String, Integer> mazeGraph; // A graph representation of the maze
    private GraphDataDW<String, Integer> graphData; // This is to get the data from the graph

    private MazeMapperFD<String, Integer> mazeMapper; // This is to map the maze

    /*
     * This method is run before each test to set up the mazeGraph and graphData
     */
    @BeforeEach
    public void setUp() throws FileNotFoundException {
        mazeReader = new MazeReaderDW<>();
        mazeGraph = mazeReader.loadMaze("maze3.dot");
        graphData = new GraphDataDW<>(mazeGraph, "A", "G");
    }

    /*
     * This method tests if the mazeGraph is loaded correctly, I am only using
     * maze3.dot for testing purposes
     */
    @Test
    public void testLoadMaze() {
        assertNotEquals(0, mazeGraph.getEdgeCount(), "Failed to load edges");
        assertNotEquals(0, mazeGraph.getNodeCount(), "Failed to load nodes");
    }

    /*
     * This method tests if the edges are loaded correctly
     */
    @Test
    public void testGetEdges() {
        List<BaseGraph<String, Integer>.Edge> edges = graphData.getEdges(mazeGraph);
        assertFalse(edges.isEmpty(), "Failed to get edges");
    }

    /*
     * This method tests if the node names are loaded correctly
     */
    @Test
    public void testGetNodeNames() {
        String[] nodeNames = (String[]) graphData.getNodeNames(mazeGraph);
        assertNotEquals(0, nodeNames.length, "Failed to get node names");
    }

    /*
     * This method tests if the start node is loaded correctly
     */
    @Test
    public void testGetStartNode() {
        String startNode = graphData.getStartNode(mazeGraph);
        assertEquals("A", startNode, "Failed to get start node");
    }

    /*
     * This method tests if the end node is loaded correctly
     */
    @Test
    public void testGetEndNode() {
        String endNode = graphData.getEndNode(mazeGraph);
        assertEquals("G", endNode, "Failed to get end node");
    }

    /*
	*This method is checking the frontend quit option, this is one of the tests  I did on FD
	*/
	@Test
    public void testWelcomeMessageOutput() {
        String input = "Q";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        MazeMapperFD<String, Integer> fd = new MazeMapperFD<>(scanner, null);

        // Capture the console output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Test command loop handling
        fd.runCommandLoop();

        // Check if the output contains the expected message
        assertTrue(output.toString().contains("Welcome to the Maze Mapper."));
    }
	
	/*
	* This is a test to check what happens if we have an unknown command for FD
	*/
    @Test
    public void testUnrecognizedCommandMessageOutput() {
        String input = "Z\nQ";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        MazeMapperFD<String, Integer> fd = new MazeMapperFD<>(scanner, null);

        // Capture the console output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Test command loop handling
        fd.runCommandLoop();

        // Check if the output contains the expected message
        assertTrue(output.toString().contains(
                "Didn't recognize that command.  Please type one of the letters presented within []s to identify the command you would like to choose."));
    }
	
	/*
	* First integration test with Dijkstra checking whether its giving shortest path cost or not
	*/
	@Test
    public void testShortestPath() {
        MazeReaderDW<String, Integer> mazeReader = new MazeReaderDW<>();
        DijkstraGraph<String, Integer> dijkstraGraph;

        // Load maze from the given file
        try {
            dijkstraGraph = (DijkstraGraph<String, Integer>) mazeReader.loadMaze("maze3.dot");
            String startNode = mazeReader.getStartNode(dijkstraGraph);
            String endNode = mazeReader.getEndNode(dijkstraGraph);

            // Calculate the shortest path and its cost using Dijkstra's algorithm
            double shortestPathCost = dijkstraGraph.shortestPathCost(startNode, endNode);

            // Check if the shortest path cost is correct
            assertEquals(27.0, shortestPathCost, "Failed to calculate the shortest path cost");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            assertTrue(false);
        }

        // Retrieve the start and end nodes
    }
	
	/*
	* This test is checking whether the integration happening between the Maze Reader and Dijkstra gving all nodes in shortest path or not
	*/
	@Test
    public void testPathData(){
         MazeReaderDW<String, Integer> mazeReader = new MazeReaderDW<>();
        DijkstraGraph<String, Integer> dijkstraGraph;

        try{
            dijkstraGraph = (DijkstraGraph<String, Integer>) mazeReader.loadMaze("maze3.dot");

            // Retrieve the start and end nodes
            String startNode = mazeReader.getStartNode(dijkstraGraph);
            String endNode = mazeReader.getEndNode(dijkstraGraph);

            // Get the shortest path data using the shortestPathData() method
            List<String> shortestPathData = dijkstraGraph.shortestPathData(startNode, endNode);
            assertEquals(7, shortestPathData.size(), "Failed to get the shortest path data");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
            assertTrue(false);
        }
    }	
}
