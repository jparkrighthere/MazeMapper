
import java.io.FileNotFoundException;

/**
 * This MazeReaderBD class is placeholder DW class for BD.
 * 
 */ 
public class MazeReaderBD implements MazeReaderBDInterface {
	// instance
	public static MazeGraphAE<String, Integer> fooGraph;

	/**
	 * Generate fooGraph to check the constructor of MazeMapperBD works well
	 * and check for loading graph well from DW
	 */
	public void loadMaze(String file) throws FileNotFoundException {
		if (file == "maze1") {
			
			// create fake graph
			fooGraph = new MazeGraphAE<String, Integer>();
			// insert nodes
			fooGraph.insertNode("Start");
			fooGraph.insertNode("B");
			fooGraph.insertNode("C");
			fooGraph.insertNode("D");
			fooGraph.insertNode("E");
			fooGraph.insertNode("End");

			// insert edges
			fooGraph.insertEdge("Start", "B", 7);
			fooGraph.insertEdge("Start", "C", 2);
			fooGraph.insertEdge("Start", "D", 5);
			fooGraph.insertEdge("B", "E", 1);
			fooGraph.insertEdge("B", "C", 2);
			fooGraph.insertEdge("C", "B", 3);
			fooGraph.insertEdge("C", "End", 1);
			fooGraph.insertEdge("D", "E", 4);
			fooGraph.insertEdge("E", "A", 4);
			fooGraph.insertEdge("End", "A", 2);
			fooGraph.insertEdge("End", "D", 1);
		}
		else if (file == "maze2") {
			// create fake graph
			fooGraph = new MazeGraphAE<String, Integer>();
			fooGraph.insertNode("Start");
			fooGraph.insertNode("Middle");
			fooGraph.insertNode("End");
			
			fooGraph.insertEdge("Start", "Middle", 1);
			fooGraph.insertEdge("Middle", "End", 1);
		}
		
		else if (file ==  "maze3") {
			// create fake graph
			fooGraph = new MazeGraphAE<String, Integer>();
			fooGraph.insertNode("Start");
			fooGraph.insertNode("End");
			fooGraph.insertNode("Treaser");
			fooGraph.insertNode("B");

			fooGraph.insertEdge("Start", "Treaser", 1);
			fooGraph.insertEdge("Treaser", "End", 1);
			fooGraph.insertEdge("Treaser", "B", 1);
		}
		else {
			throw new FileNotFoundException("Could not find file");
		}
		
	}

}

