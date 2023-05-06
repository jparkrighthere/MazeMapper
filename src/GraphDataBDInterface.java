import java.util.List;

public interface GraphDataBDInterface<NodeType, EdgeType> {
	
	public List<NodeType> getNodes();
	
	public List<List<String>> getEdges();
	
	public double getEdgeWeight(String source, String target);

}
