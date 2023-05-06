import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MazeMapperBDFD<NodeType, EdgeType extends Number>  implements MazeMapperBDInterface<NodeType, EdgeType>  {

    public NodeType startNode;
    public NodeType middleNode;
    public NodeType endNode;


    // displays array of nodes in order for shortest path from start and end
    public List<NodeType> findShortestPath(NodeType start, NodeType middle, NodeType end){

        List <NodeType> ph = new ArrayList<>();
        ph.add((NodeType)"a");
        ph.add((NodeType)"b");
        ph.add((NodeType)"c");

        return ph;
    }


    // display minimum sum of edges from start to end
    public double findShortestPathCost(NodeType start, NodeType middle, NodeType end) {
        return 11.1;
    }

    // returns the GraphData object that is read from the DW
    public MazeGraphInterfaceAE <NodeType, EdgeType> getMaze (String filename) {
        // MazeReaderFD reader = new MazeReaderFD();
        MazeGraphInterfaceAE newGraph = null;
        // try {
        //  newGraph = reader.loadMaze(filename);
        // } catch (FileNotFoundException e) {
        //  e.printStackTrace();
        // }
        // graphData = new GraphDataFD<NodeType, EdgeType>(this.graph, startNode, endNode); //graph, start node(A), end node(G)
        // GraphDataDWInterface<NodeType, EdgeType> maze = new GraphDataFD(this.graph, this.startNode, this.endNode);
        return newGraph;
    }

    public NodeType[] getNodeNames(MazeGraphInterfaceAE<NodeType, EdgeType> graph) {
        NodeType[] list = (NodeType[]) new Object[3];
        list[0] = (NodeType) "a";
        list[1] = (NodeType) "b";
        list[2] = (NodeType) "c";
        return list;
    }

    public NodeType getStartNode() {
        this.startNode = (NodeType) "A";
        return startNode;
    }
    public NodeType getMiddleNode() {
        this.middleNode = (NodeType) "C";
        return middleNode;
    }
    public NodeType getEndNode() {
        this.endNode = (NodeType) "N";
        return endNode;
    }
}

