
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GraphDataDW<NodeType, EdgeType extends Number> implements GraphDataDWInterface<NodeType, EdgeType> {

    private NodeType startNode;
    private NodeType endNode;
    private BaseGraph<NodeType, EdgeType> graph;

    public GraphDataDW(BaseGraph<NodeType, EdgeType> graph, NodeType startNode, NodeType endNode) {
        this.graph = graph;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    @Override
    public NodeType getStartNode(BaseGraph<NodeType, EdgeType> graph) {
        return startNode;
    }

    @Override
    public NodeType getEndNode(BaseGraph<NodeType, EdgeType> graph) {
        return endNode;
    }

    @Override
    public NodeType[] getNodeNames(BaseGraph<NodeType, EdgeType> graph) {
        NodeType[] nodeArray = (NodeType[]) java.lang.reflect.Array.newInstance(
                this.graph.nodes.keySet().iterator().next().getClass(),
                this.graph.nodes.size());
        return this.graph.nodes.keySet().toArray(nodeArray);
    }

    @Override
    public List<BaseGraph<NodeType, EdgeType>.Edge> getEdges(BaseGraph<NodeType, EdgeType> graph) {
        List<BaseGraph<NodeType, EdgeType>.Edge> edges = new ArrayList<>();
        for (BaseGraph<NodeType, EdgeType>.Node node : this.graph.nodes.values()) {
            edges.addAll(node.edgesLeaving);
        }
        return edges;
    }

    @Override
    public List<String> getNodesAsString(BaseGraph<NodeType, EdgeType> graph) {
        NodeType[] nodeArray = getNodeNames(graph);
        List<String> nodesAsString = new ArrayList<>();
        for (NodeType node : nodeArray) {
            nodesAsString.add(node.toString());
        }
        return nodesAsString;
    }

    @Override
    public List<String> getEdgesAsString(BaseGraph<NodeType, EdgeType> graph) {
        List<BaseGraph<NodeType, EdgeType>.Edge> edges = getEdges(graph);
        List<String> edgesAsString = new ArrayList<>();
        for (BaseGraph<NodeType, EdgeType>.Edge edge : edges) {
            String edgeString = edge.predecessor.data.toString() + " -> " + edge.successor.data.toString() + " [label="
                    + edge.data.toString() + "]";
            edgesAsString.add(edgeString);
        }
        return edgesAsString;
    }

    public static void main(String[] args) {
        MazeReaderDW<String, Integer> mazeReader = new MazeReaderDW<>();
        try {
            BaseGraph<String, Integer> mazeGraph = mazeReader.loadMaze("maze3.dot");
            GraphDataDW<String, Integer> graphData = new GraphDataDW<>(mazeGraph, "A", "G");
            System.out.println(mazeGraph.getEdgeCount());
            System.out.println(mazeGraph.getNodeCount());
            System.out.println(graphData.getStartNode(mazeGraph));
            System.out.println(graphData.getEndNode(mazeGraph));
            System.out.println(graphData.getNodesAsString(mazeGraph));
            System.out.println(graphData.getEdgesAsString(mazeGraph));
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found. Please check the file path.");
        }
    }

}
