import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
/**
 * Tests the MazeMapper class which is the backend class of MazeMapper application.
 * 
 */
class BackendDeveloperTests {
	/**
	 * Tests for 'findShortestPathCost()' method:
	 * Checks computing maze1's path cost from 'Start' to 'End' with Dijkstra's Algorithm 
	 * @throws FileNotFoundException 
	 */
	@Test
	void BackendDevelopertest1() throws FileNotFoundException {

		MazeReaderBD reader = new MazeReaderBD();
		reader.loadMaze("maze1");
		MazeGraphAE<String, Integer> foo = reader.fooGraph;
		MazeMapperBD<String, Integer> backend = new MazeMapperBD<String, Integer>(foo);
		assertEquals(3.0, backend.findShortestPathCost("Start", backend.getMiddleNode(), "End"));

	}
	
	/**
	 * Tests for 'findShortestPath()' method:
	 * Checks maze1's the ordered sequence of data within nodes from 'Start' to 'End'.
	 */
	@Test
	void BackendDevelopertest2() throws FileNotFoundException {
		MazeReaderBD reader = new MazeReaderBD();
		reader.loadMaze("maze1");
		MazeGraphAE<String, Integer> foo = reader.fooGraph;
		MazeMapperBD<String, Integer> backend = new MazeMapperBD<String, Integer>(foo);
		String expectedPath = "[Start, C, End]";
			
		assertEquals(expectedPath, backend.findShortestPath("Start", backend.getMiddleNode(), "End").toString());
			
	}
	
	/**
	 * Tests for 'findShortestPathCost()' method:
	 * Checks computing maze2's path cost from 'Start' to 'End' with Dijkstra's Algorithm 
	 */
	@Test
	void BackendDevelopertest3() throws FileNotFoundException {
		MazeReaderBD reader = new MazeReaderBD();
		reader.loadMaze("maze2");
		MazeGraphAE<String, Integer> foo = reader.fooGraph;
		MazeMapperBD<String, Integer> backend = new MazeMapperBD<String, Integer>(foo);
		assertEquals(2.0, backend.findShortestPathCost("Start", "Middle", "End"));
	}
	
	/**
	 * Tests for 'findShortestPath()' method:
	 * Checks maze3's the ordered sequence of data within nodes from 'Start' to 'End'.
	 */
	@Test
	void BackendDevelopertest4() throws FileNotFoundException {
		MazeReaderBD reader = new MazeReaderBD();
		reader.loadMaze("maze3");
		MazeGraphAE<String, Integer> foo = reader.fooGraph;
		MazeMapperBD<String, Integer> backend = new MazeMapperBD<String, Integer>(foo);
		String expectedPath = "[Start, Treaser, End]";
		assertEquals(expectedPath, backend.findShortestPath("Start", "Treaser", "End").toString());
	}
	
	/**
	 * Tester1 for Integration with BD and DW
	 * Checks that loading the maze works well
	 */
	@Test
	void IntegrationTest1(){
		try {
		MazeReaderDW<String, Integer> reader = new MazeReaderDW<>();
		MazeGraphAE<String, Integer> graph = reader.loadMaze("maze1.dot");
		MazeMapperBD<String, Integer> backend = new MazeMapperBD<String, Integer>(graph);
		assertTrue(backend.graph.equals(graph));
		} catch (FileNotFoundException e) {
			assertTrue(1==2); //file is not loaded from DW
		}
	}
	
	/**
	 * Tester2 for Integration with BD, DW, and AE
	 * Checks for getNodeNames method in MazeMapperBD class
	 */
	@Test
	void IntegrationTest2() throws FileNotFoundException {
		MazeMapperBD<String, Double> backend = new MazeMapperBD<String,Double>(null);
		MazeGraphInterfaceAE<String, Double>selectedMaze = backend.getMaze("maze1.dot");
        System.out.print("The rooms in the mazes are: ");
        String[] list = (String []) backend.getNodeNames(selectedMaze);

        String FDList = "";
        for (String room : list) {
            FDList += "Room " + room.toString() + " ";
        }
        assertEquals("Room A Room F Room E Room D Room N Room C Room B ", FDList);
	}
	
	/**
	 * code review 1 for Algorithm Engineer's code
	 * Test for shortestPathData and shortestPathCost
	 * methods from MazeGraphAE
	 */
	@Test
	void codeReviewAE1() {
		MazeGraphAE<String, Integer> graph;
		graph = new MazeGraphAE<String, Integer>();
        graph.insertNode("Start");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("End");
        graph.insertEdge("Start", "B", 1);
        graph.insertEdge("Start", "C", 2);
        graph.insertEdge("B", "E", 6);
        graph.insertEdge("C", "B", 4);     
        graph.insertEdge("C", "D", 9);
        graph.insertEdge("C", "End", 5);   
        graph.insertEdge("D", "End", 2);
        graph.insertEdge("E", "D", 7);
        graph.insertEdge("E", "End", 3);
        
        // create the expected shortest path: Start-C-End
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Start");
        expected.add("C");
        expected.add("End");
        
        // check for the shortest path and cost
        assertEquals(graph.shortestPathData("Start", "End"), expected);
        assertEquals(graph.shortestPathCost("Start", "End"), 7);
	}
	
	/**
	 * code review 2 for Algorithm Engineer's code
	 * Test for shortestPathWithNodeData and shortestPathWithNodeCost
	 * methods from MazeGraphAE
	 */
	@Test
	void codeReviewAE2() {
		MazeGraphAE<String, Integer> graph;
		graph = new MazeGraphAE<String, Integer>();
        graph.insertNode("Start");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("Middle");
        graph.insertNode("E");
        graph.insertNode("End");
        graph.insertEdge("Start", "B", 1);
        graph.insertEdge("Start", "C", 2);
        graph.insertEdge("B", "E", 6);
        graph.insertEdge("C", "B", 4);     
        graph.insertEdge("C", "Middle", 9);
        graph.insertEdge("C", "End", 5);   
        graph.insertEdge("Middle", "End", 2);
        graph.insertEdge("E", "Middle", 7);
        graph.insertEdge("E", "End", 3);
        
        // create the expected shortest path: Start-C-Middle-End
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Start");
        expected.add("C");
        expected.add("Middle");
        expected.add("End");
        // check for the shortest path and cost
        assertEquals(graph.shortestPathWithNodeData("Start", "Middle", "End"), expected);
        assertEquals(graph.shortestPathWithNodeCost("Start", "Middle", "End"), 13);
	}
}

