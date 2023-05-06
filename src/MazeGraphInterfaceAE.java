
import java.util.List;
import java.util.NoSuchElementException;

public interface MazeGraphInterfaceAE<NodeType,EdgeType extends Number> extends GraphADT<NodeType,EdgeType>{
    public List<NodeType> shortestPathWithNodeData(NodeType start, NodeType middle, NodeType end) throws NoSuchElementException;
    public double shortestPathWithNodeCost(NodeType start, NodeType middle, NodeType end) throws NoSuchElementException;
}

