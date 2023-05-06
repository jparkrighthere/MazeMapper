
import java.util.PriorityQueue;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

// imports to take care of junit tests
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.BeforeEach;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes.  This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
    extends BaseGraph<NodeType,EdgeType>
    implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph.  The final node in this path is stored in it's node
     * field.  The total cost of this path is stored in its cost field.  And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in it's node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;
        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }
        public int compareTo(SearchNode other) {
            if( cost > other.cost ) return +1;
            if( cost < other.cost ) return -1;
            return 0;
        }
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations.  The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *         or when either start or end data do not correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        // if the start node or end node is not in the tree throw and exception
        if(!this.containsNode(start) || !this.containsNode(end)){
            throw new NoSuchElementException("The start or end nodes are not in this graph");
        }
        // create a linked list to store the nodes we have already visited
        LinkedList<SearchNode> visitedNodes = new LinkedList<SearchNode>();
        // create the priority queue that will store the edge weights to determine which node to visit next
        PriorityQueue<SearchNode> traversalQueue = new PriorityQueue<SearchNode>(this.getNodeCount());
        // the current search node that the algorithm is at, start and the "start" node
        SearchNode currNode = new SearchNode(this.nodes.get(start), 0, null);
        // to start we "visit" the start node
        traversalQueue.add(currNode);
        // boolean to store if we ended at the end mark specified
        boolean endFound = false;
        // continue the algorithm as long as we haven't visited every node or hit the end node
        while(!traversalQueue.isEmpty()){
            // get current node from the queue
            currNode = traversalQueue.remove();
            // visit the current node
            visitedNodes.add(currNode);
            // if this node is the node that is to be found end the search, and set the boolean
            if (currNode.node.data.equals(end)) {
                endFound = true;
                break;
            }
            // find all the edges leaving the current node and calculate the weights relative to the starting node
            // as we as add them to the traversalQueue
            for(Edge edge : currNode.node.edgesLeaving){
                SearchNode nextNode = new SearchNode(edge.successor, (int) edge.data + currNode.cost, currNode);
                
                // add the node to the traversalQueue to be processes later
                if(!visitedNodes.contains(nextNode)){
                    traversalQueue.add(nextNode);
                }
            }     
        }
        // if the end was not found throw an exception
        if (!endFound) throw new NoSuchElementException("The path between the two nodes could not be found.");
        // otherwise return the currNode
        return currNode;
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value.  This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path.  This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        // get the node to start at
        SearchNode node = computeShortestPath(start, end);
        // an arraylist to store the dataPath
        ArrayList<NodeType> dataPath = new ArrayList<NodeType>();
        // put the shortest path in the arraylist in reverse order
        while(node != null){
            dataPath.add(node.node.data);
            node = node.predecessor;
        }
        // reverse the order so the list is from start to end not end to start
        ArrayList<NodeType> revDataPath = new ArrayList<NodeType>();
        for(int i = dataPath.size() - 1; i >= 0; i--){
            revDataPath.add(dataPath.get(i));
        }
        // return the list
        return revDataPath;
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path from the node containing the start data to the node containing the
     * end data.  This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        // The last node should contian the shortest path length so get the last node and return the cost
        return computeShortestPath(start, end).cost;
    }

    //    protected DijkstraGraph<Integer, Integer> graph;

    // TODO: implement 3+ tests in step 8.
    //    @BeforeEach
    //    public void createGraph(){
        // before each test create the graph to be tested
    //        graph = new DijkstraGraph<>();
    //  graph.insertNode(0);
    //  graph.insertNode(1);
    //  graph.insertNode(2);
    //  graph.insertNode(3);
    //  graph.insertNode(4);
    //  graph.insertNode(5);
    //  graph.insertNode(6);
    //  graph.insertNode(7);
    //  graph.insertNode(8);
    //  graph.insertNode(9);
    //  graph.insertNode(10);
    //  graph.insertEdge(0, 1, 1);
    //  graph.insertEdge(0, 9, 5);
    //  graph.insertEdge(0, 6, 8);
    //  graph.insertEdge(1, 9, 3);
    //  graph.insertEdge(2, 0, 7);
    //  graph.insertEdge(2, 5, 2);
    //  graph.insertEdge(4, 5, 9);
    //  graph.insertEdge(5, 8, 7);
    //  graph.insertEdge(6, 1, 6);
    //  graph.insertEdge(6, 7, 2);
    //  graph.insertEdge(7, 6, 2);
    //  graph.insertEdge(7, 2, 1);
    //  graph.insertEdge(7, 8, 5);
    //  graph.insertEdge(9, 4, 4);
    //  graph.insertEdge(9, 3, 3);
    // }

    /*
     * This test method is meant to use an example from class to test
     * the Dijkstra's implementation
     */
    // @Test
    // public void testInClassExample(){
        // create the expected path it should take
    //  ArrayList<Integer> expected = new ArrayList<>();
    //  expected.add(2);
    //  expected.add(0);
    //  expected.add(6);
    //  expected.add(7);
        // ensure path and cost are as expected
    //  assertEquals(graph.shortestPathData(2, 7), expected);
    //  assertEquals(graph.shortestPathCost(2, 7), 17);
    // }

    /*
     * This method is supposed to test a case not shown in class
     */
    // @Test
    // public void testNotInClassExample(){
        // create the expected path
    //  ArrayList<Integer> expected = new ArrayList<>();
    //  expected.add(7);
    //  expected.add(6);
    //  expected.add(1);
    //  expected.add(9);
        // ensure path and cost are as expected
    //  assertEquals(graph.shortestPathData(7, 9), expected);
    //  assertEquals(graph.shortestPathCost(7, 9), 11);
    // }

    /*
     * This test is mean to ensure the correct exception is thrown
     * if a path is requested that does not exist
     */
    //@Test
    //public void testPathDoesNotExist(){
    //  try{
            // try to find a path that is impossible
    //      graph.shortestPathCost(3, 7);
    //  }
        // ensure correct exception is thrown
    //  catch(NoSuchElementException e){
    //      assertEquals(1,1);
    //      return;
    //  }
        // if not error out
    //  assertEquals(1,0);
    // }
}
