
import java.io.FileNotFoundException;
import java.util.List;

/**
 * This MazeMapperBD class is the backend class for MazeMapper application.
 * 
 */ 
public class MazeMapperBD <NodeType, EdgeType extends Number> implements MazeMapperBDInterface<NodeType, EdgeType> {
    // Field
    MazeGraphAE<NodeType, EdgeType> graph;
    
    private GraphDataDW<NodeType, EdgeType> graphData;
    private NodeType startNode;
    private NodeType middleNode;
    private NodeType endNode;
    
    // Constructor
    public MazeMapperBD(MazeGraphAE<NodeType, EdgeType> graph) {
        this.graph = graph;
    }
    
    /**
     * Returns the list of data values from nodes along the shortest path from the
     * node with the provided start value through the node with the provided end
     * value. This list of data values starts with the start value, ends with the
     * end value, and contains intermediary values in the order they are encountered
     * while traversing this shorteset path. 
     * This method uses shortestPathData method of MazeGraph class which uses 
     * Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> findShortestPath(NodeType start, NodeType middle, NodeType end) {
        List<NodeType> shortestPath = graph.shortestPathWithNodeData(start, middle, end);
        
        return shortestPath;
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest path
     * from the node containing the start data to the node containing the end data.
     * This method uses shortestPathCost method of MazeGraph class which uses
     * Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    @Override
    public double findShortestPathCost(NodeType start, NodeType middle, NodeType end) {
        return graph.shortestPathWithNodeCost(start, middle, end);
    }

    /**
     * This method load the maze selected by user like 'maze1', 'maze2', or 'maze3'
     * to allow the frontend to access the graph utilized by the backend.
     * 
     * @return the data of maze selected by user
     * @throws FileNotFoundException 
     */
    //@SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public MazeGraphAE<NodeType, EdgeType> getMaze (String filename) throws FileNotFoundException {
        MazeReaderDW<NodeType, EdgeType> reader = new MazeReaderDW<>();
        graph = reader.loadMaze(filename);
        return graph;
    }

    /**
     * This method get names of nodes from selected graph
     * 
     * @return the name of nodes 
     */
    public NodeType[] getNodeNames(MazeGraphInterfaceAE<NodeType, EdgeType> graph) {
        this.graphData = new GraphDataDW<>((MazeGraphAE<NodeType, EdgeType>)graph, this.startNode, this.endNode);
        return graphData.getNodeNames((BaseGraph<NodeType, EdgeType>)graph);
    }

    /**
     * This method get startNode
     * 
     * @return startNode
     */
    public NodeType getStartNode() {
        this.startNode = (NodeType) "A";
        return startNode;
    }

    /**
     * This method get middleNode
     * 
     * @return middleNode
     */
    public NodeType getMiddleNode() {
        this.middleNode = (NodeType) "C";
        return middleNode;
    }

    /**
     * This method get endNode
     * 
     * @return endNode
     */
    public NodeType getEndNode() {
        this.endNode = (NodeType) "N";
        return endNode;
    }
}


