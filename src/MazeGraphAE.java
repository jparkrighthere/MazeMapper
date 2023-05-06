import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MazeGraphAE<NodeType,EdgeType extends Number> extends DijkstraGraph<NodeType, EdgeType> implements MazeGraphInterfaceAE<NodeType, EdgeType>{

    /*
     * Use super implementation to insert a node
     *
     */
    @Override
    public boolean insertNode(NodeType data) {
        return super.insertNode(data);
    }

    /*
     * Use super implementation to remove a node
     */
    @Override
    public boolean removeNode(NodeType data) {
        return super.removeNode(data);
    }

    /*
     * Use super implementation to see if it contains a node
     */
    @Override
    public boolean containsNode(NodeType data) {
        return super.containsNode(data);
    }

    /*
     * Use super implementation to insert and edge
     */
    @Override
    public boolean insertEdge(NodeType pred, NodeType succ, EdgeType weight) {
        return super.insertEdge(pred, succ, weight);
    }

    /*
     * Use super implementation to remove an edge
     */
    @Override
    public boolean removeEdge(NodeType pred, NodeType succ) {
        return super.removeEdge(pred, succ);
    }

    /*
     * Use super implementation to see if it contains an edge
     */
    @Override
    public boolean containsEdge(NodeType pred, NodeType succ) {
        return super.containsEdge(pred, succ);
    }

    /*
     * Use super implementation to grab a node in the graph
     */
    @Override
    public EdgeType getEdge(NodeType pred, NodeType succ) {
        return super.getEdge(pred, succ);
    }

    /*
     * This method is meant to find the shortest path with the condition that it must pass through
     * the middle node
     */
    public List<NodeType> shortestPathWithNodeData(NodeType start, NodeType middle, NodeType end) {
        // check to make sure that all the nodes are in the graph
        if(!super.containsNode(start) || !super.containsNode(middle) || !super.containsNode(end)){
            throw new NoSuchElementException("One or more of the passed in nodes do not exist in this graph");
        }
        List<NodeType> path = new ArrayList<NodeType>();
        try{
            path = super.shortestPathData(start, middle);
        }
        catch(NoSuchElementException e){
            // throw a more specific exception than the generic one as there are now 2 steps instead of one
            throw new NoSuchElementException("A path between the start and middle does not exist");
        }
        try{
            // find the second path and remove the middle node as the first path already accounts for this node
            List<NodeType> secondPath = new ArrayList<NodeType>();
            secondPath = super.shortestPathData(middle, end);
            secondPath.remove(middle); 
            path.addAll(secondPath);
        }
        catch(NoSuchElementException e){
            // throw a more specific exception than the generic one as there are now 2 steps instead of one
            throw new NoSuchElementException("A path between the middle and end does not exist");
        }
        return path;
    }

    public double shortestPathWithNodeCost(NodeType start, NodeType middle, NodeType end){
        // check to make sure that all the nodes are in the graph
        if(!super.containsNode(start) || !super.containsNode(middle) || !super.containsNode(end)){
            throw new NoSuchElementException("One or more of the passed in nodes do not exist in this graph");
        }
        // return the 2 path lengths added together
        return computeShortestPath(start, middle).cost + computeShortestPath(middle, end).cost;
    }
}
