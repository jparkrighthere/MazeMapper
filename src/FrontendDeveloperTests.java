import org.junit.Test; 

import org.junit.jupiter.api.Assertions;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Test method for the loadDataCommand method of the MazeMapperFD class.
 * It sets up an input stream to simulate user input and calls the method.
 * If the method returns successfully, the test is considered passed.
 */
public class FrontendDeveloperTests {

    /**
     * Tests if it correctly outputs the shortest path value.
     */
    @Test
    public void findShortestPathTest() {
        MazeMapperBDFD<String, Double> backend = new MazeMapperBDFD<String,Double>();
        Scanner userInput= new Scanner(System.in);
        TextUITester tester = new TextUITester("F\n");
        MazeMapperFD frontend = new MazeMapperFD(userInput, backend);
        Assertions.assertEquals("Shortest Path: a -> b -> c", frontend.findShortestPath());
    }

    /**
     * Tests if it correctly outputs the shortest cost value.
     */
    @Test
    public void findShortestCostTest() {
        Scanner userInput= new Scanner(System.in);
        MazeMapperBDFD<String, Double> backend = new MazeMapperBDFD<String,Double>();
        TextUITester tester = new TextUITester("S");
        MazeMapperFD frontend = new MazeMapperFD(userInput, backend);
        Assertions.assertEquals("Shortest Cost: 11.1", frontend.findShortestCost());
    }

    /**
     * Tests if it correctly stores the valid input value.
     */
    @Test
    public void testMainMenuPrompt_ValidInput() {
        String input = "C\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        MazeMapperFD mapper = new MazeMapperFD(scanner, null);
        char result = mapper.mainMenuPrompt();
        Assertions.assertEquals('C', result);
    }

    /**
     * tests if chooseMaze method correctly gives the output.
     */
    @Test
    public void testChooseMaze() {
        TextUITester testUI = new TextUITester("C\n1\nQ\n");
        MazeMapperBDFD<String, Double> backend = new MazeMapperBDFD<String,Double>();
        Scanner userInput= new Scanner(System.in);
        MazeMapperFD frontend = new MazeMapperFD(userInput, backend);
        frontend.runCommandLoop();

        String output = testUI.checkOutput();
        Assertions.assertEquals(true, output.contains("The rooms in the mazes are: Room a Room b Room c " +
                "(They are not in the order)"));
    }

    /**
     * tests if runCommandLoop method correctly prints the menu.
     */
    @Test
    public void testCommandLoop() {
        TextUITester testUI = new TextUITester("Q\n");
        MazeMapperBDFD<String, Double> backend = new MazeMapperBDFD<String,Double>();
        Scanner userInput= new Scanner(System.in);
        MazeMapperFD frontend = new MazeMapperFD(userInput, backend);

        frontend.runCommandLoop();

        String output = testUI.checkOutput();
        String expectedMenu = "-------------------------------------------------------------------------------\n"+
        "Welcome to the Maze Mapper.\n" +
        "-------------------------------------------------------------------------------\n" +
        "Choose a command from the list below:\n" +
        "    [F]ind shortest path\n" +
        "    Find [S]hortest cost\n" +
        "    [C]hoose maze to display\n" +
        "    [Q]uit\n" +
        "Choose command:"; 
        Assertions.assertEquals(true, output.contains(expectedMenu));
    }

    /**
     * Combined test of Frontend, Backend, AE, and DW.
     * Checks if the given input results the correct outputs without any error.
     * Test using maze1.dot file in this case.
     * Checks the functionality and outputs(getallNodes, shortestPath, and shortestCost).
     */
    @Test
    public void integrationTest1() {
        TextUITester testUI = new TextUITester("C\n1\nF\nS\nQ\n");
        MazeMapperBD<String, Double> backend = new MazeMapperBD<String,Double>(new MazeGraphAE<>());
        Scanner userInput= new Scanner(System.in);
        MazeMapperFD frontend = new MazeMapperFD(userInput, backend);

        frontend.runCommandLoop();

        String output = testUI.checkOutput().trim();
        String expected1 = "The rooms in the mazes are: Room A Room F Room E Room D Room N Room C Room B " +
                "(They are not in the order)";
        String expected2 = "Shortest Path: A -> C -> N";
        String expected3 = "Shortest Cost: 9.0";
        Assertions.assertEquals(true,output.contains(expected1.trim()));
        Assertions.assertEquals(true,output.contains(expected2.trim()));
        Assertions.assertEquals(true,output.contains(expected3.trim()));
    }

    /**
     * Combined test of Frontend, Backend, AE, and DW.
     * Checks if the given input results the correct outputs without any error.
     * Test using maze3.dot file in this case.
     * Checks the functionality and outputs(getallNodes, shortestPath, and shortestCost).
     */
    @Test
    public void integrationTest2() {
        TextUITester testUI = new TextUITester("C\n3\nF\nS\nQ\n");
        MazeMapperBD<String, Double> backend = new MazeMapperBD<String,Double>(new MazeGraphAE<>());
        Scanner userInput= new Scanner(System.in);
        MazeMapperFD frontend = new MazeMapperFD(userInput, backend);

        frontend.runCommandLoop();

        String output = testUI.checkOutput().trim();
        String expected1 = "The rooms in the mazes are: Room U Room T Room S Room R Room Q Room P Room O Room N " +
                "Room M Room L Room K Room J Room I Room H Room G Room F Room E Room D Room C Room B Room A " +
                "(They are not in the order)";
        String expected2 = "Shortest Path: A -> C -> G -> H -> L -> M -> N";
        String expected3 = "Shortest Cost: 27.0";
        Assertions.assertEquals(true,output.contains(expected1.trim()));
        Assertions.assertEquals(true,output.contains(expected2.trim()));
        Assertions.assertEquals(true,output.contains(expected3.trim()));
    }

    /**
     * This test is a code review of Data Wrangler which checks if it throws FileNotFoundException error as user
     * tries to load a file that doesn't exist.
     * Result: It works great.
     * @throws FileNotFoundException
     */
    @Test
    public void CodeReviewOfDataWrangler1() throws FileNotFoundException {
        MazeReaderDW<String, Double> mazeReader = new MazeReaderDW<>();;

        assertThrows(FileNotFoundException.class, () -> mazeReader.loadMaze("maze4.dot"));
    }

    /**
     * This test is a code review of Data Wrangler if it stores the correct data with a given file that exist.
     * Result: It works great.
     * @throws FileNotFoundException
     */
    @Test
    public void CodeReviewOfDataWrangler2() throws FileNotFoundException {
        MazeReaderDW<String, Double> mazeReader = new MazeReaderDW<>();
        BaseGraph<String, Double> mazeGraph = (BaseGraph<String, Double>) mazeReader.loadMaze("maze3.dot");
        GraphDataDW<String, Double> graphData = new GraphDataDW<>(mazeGraph, "A", "N");
        String endNode = graphData.getEndNode(mazeGraph);
        String startNode = graphData.getStartNode(mazeGraph);
        List<String> edges = graphData.getEdgesAsString(mazeGraph);
        List<String> nodes = graphData.getNodesAsString(mazeGraph);
        Assertions.assertEquals(endNode, "N");
        Assertions.assertEquals(startNode, "A");
        Assertions.assertEquals(nodes.toString(), "[U, T, S, R, Q, P, O, N, M, L, K, J, I, H, G, F, E, D, C, B, A]");
        Assertions.assertEquals(edges.toString(), "[U -> N [label=3], T -> U [label=8], S -> T [label=7], R -> S " +
                "[label=6], Q -> R [label=4], P -> Q [label=1], O -> P [label=2], M -> N [label=1], L -> M [label=8]" +
                ", L -> R [label=2], K -> N [label=3], K -> O [label=9], J -> K [label=6], J -> M [label=5], I ->" +
                " J [label=7], H -> I [label=2], H -> L [label=4], G -> H [label=5], F -> G [label=3], E -> F " +
                "[label=9], E -> G [label=4], E -> I [label=8], D -> F [label=1], C -> E [label=6], C -> B [label=4]," +
                " C -> G [label=7], B -> D [label=3], B -> E [label=7], A -> B [label=5], A -> C [label=2], A ->" +
                " D [label=6]]");
    }
}



