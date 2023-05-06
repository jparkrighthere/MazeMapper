import java.io.FileNotFoundException;
import java.util.List;

public interface MazeMapperBDInterface <NodeType, EdgeType extends Number> {
    // public MazeMapperBD(MazeGraphBD<String, Double> graph);
    
    // displays array of nodes in order for shortest path from start and end
    public List<NodeType> findShortestPath(NodeType start, NodeType middle, NodeType end); //FD: findShortestPath()
    
    // display minimum sum of edges from start to end
    public double findShortestPathCost(NodeType start, NodeType middle, NodeType end); //FD: findShortestCost()
    
    // returns the GraphData object(Maze1,2,or 3) that is read from the DW
    public MazeGraphInterfaceAE<NodeType, EdgeType> getMaze (String filename) throws FileNotFoundException;

    // returns node names from selected graph
    public NodeType[] getNodeNames(MazeGraphInterfaceAE<NodeType, EdgeType> graph);

    // return startNode
    public NodeType getStartNode();

    //return middleNode
    public NodeType getMiddleNode();

    //return endNode
    public NodeType getEndNode();
}



