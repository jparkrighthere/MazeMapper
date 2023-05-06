
import java.io.FileNotFoundException;

public interface MazeReaderDWInterface<NodeType, EdgeType extends Number> {

    /*
     * Load a DOT file containing a maze data and return a GraphADT representation
     * of the maze.
     * 
     * @param filename the name of the DOT file
     * 
     * @return a GraphADT representation of the maze
     * 
     * @throws FileNotFoundException if the file is not found
     */

    public MazeGraphAE<NodeType, EdgeType> loadMaze(String filename) throws FileNotFoundException;

    /*
     * Return the start node of the maze.
     */
    public NodeType getStartNode(BaseGraph<NodeType, EdgeType> graph);

    /*
     * Return the end node of the maze.
     */
    public NodeType getEndNode(BaseGraph<NodeType, EdgeType> graph);

    /*
     * Return the middle node of the maze.
     */
    public NodeType getMiddleNode(BaseGraph<NodeType, EdgeType> graph);

}
