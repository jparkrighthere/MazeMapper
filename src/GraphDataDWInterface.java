
import java.util.List;

public interface GraphDataDWInterface<NodeType, EdgeType extends Number> {

    /**
     * Return the start node of the graph.
     * 
     * @param graph the graph to retrieve the start node from
     * @return the start node
     * 
     */
    public NodeType getStartNode(BaseGraph<NodeType, EdgeType> graph);

    /**
     * Return the end node of the graph.
     * 
     * @param graph the graph to retrieve the end node from
     * @return the end node
     * 
     */
    public NodeType getEndNode(BaseGraph<NodeType, EdgeType> graph);

    /**
     * Return the names of all nodes in the graph.
     * 
     * @param graph the graph to retrieve the node names from
     * @return an array of node names
     * 
     */
    public NodeType[] getNodeNames(BaseGraph<NodeType, EdgeType> graph);

    /**
     * Return the edges of the graph, including their source and target nodes and
     * weights.
     * 
     * @param graph the graph to retrieve the edges from
     * @return an array of edges, where each edge is represented as an array of
     *         length 3 containing the source node, target node, and edge weight
     * 
     */
    public List<BaseGraph<NodeType, EdgeType>.Edge> getEdges(BaseGraph<NodeType, EdgeType> graph);

    /**
     * Return the nodes of the graph as strings.
     * 
     * @param graph the graph to retrieve the nodes from
     * @return a list of node names as strings
     * 
     */
    public List<String> getNodesAsString(BaseGraph<NodeType, EdgeType> graph);

    /**
     * Return the edges of the graph as strings.
     * 
     * @param graph the graph to retrieve the edges from
     * @return a list of edges as strings
     * 
     */
    public List<String> getEdgesAsString(BaseGraph<NodeType, EdgeType> graph);
}
