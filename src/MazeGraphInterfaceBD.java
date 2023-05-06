import java.util.List;

public interface MazeGraphInterfaceBD<NodeType, EdgeType extends Number>  extends GraphADT<NodeType, EdgeType> {
	public boolean insertNode(NodeType data);
	public boolean removeNode(NodeType data);
	public boolean containsNode(NodeType data);
	public int getNodeCount();
	public List<NodeType> shortestPathData(NodeType start, NodeType end);
	public double shortestPathCost(NodeType start, NodeType end);
	
}
