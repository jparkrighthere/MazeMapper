
import java.util.PriorityQueue;

import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class is placeholder class AE for MazeMapper class:
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes. This class makes use of
 * Dijkstra's shortest path algorithm.
 * 
 */
public class MazeGraphBD<NodeType, EdgeType extends Number> extends BaseGraph<NodeType, EdgeType>
		implements GraphADT<NodeType, EdgeType> {

	/**
	 * While searching for the shortest path between two nodes, a SearchNode
	 * contains data about one specific path between the start node and another node
	 * in the graph. The final node in this path is stored in it's node field. The
	 * total cost of this path is stored in its cost field. And the predecessor
	 * SearchNode within this path is referened by the predecessor field (this field
	 * is null within the SearchNode containing the starting node in it's node
	 * field).
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
			if (cost > other.cost)
				return +1;
			if (cost < other.cost)
				return -1;
			return 0;
		}
	}

	/**
	 * This helper method creates a network of SearchNodes while computing the
	 * shortest path between the provided start and end locations. The SearchNode
	 * that is returned by this method is represents the end of the shortest path
	 * that is found: it's cost is the cost of that shortest path, and the nodes
	 * linked together through predecessor references represent all of the nodes
	 * along that shortest path (ordered from end to start).
	 *
	 * @param start the data item in the starting node for the path
	 * @param end   the data item in the destination node for the path
	 * @return SearchNode for the final end node within the shortest path
	 * @throws NoSuchElementException when no path from start to end is found or
	 *                                when either start or end data do not
	 *                                correspond to a graph node
	 */
	protected SearchNode computeShortestPath(NodeType start, NodeType end) {
		// TODO: implement in step 6
		// Get the nodes corresponding to the start and end data
		Node startNode = nodes.get(start);
		Node endNode = nodes.get(end);

		// If either start and end is not in the graph, throw exception immediately
		if (startNode == null || endNode == null) {
			throw new NoSuchElementException("Start or end node not found in graph");
		}

		// Initialize the priority queue and visited set
		PriorityQueue<SearchNode> queue = new PriorityQueue<>();
		Hashtable<Node, SearchNode> visited = new Hashtable<>();
		SearchNode startSearchNode = new SearchNode(startNode, 0, null);
		queue.add(startSearchNode);

		// Process nodes in the queue until we reach the end node
		while (!queue.isEmpty()) {
			SearchNode currSearchNode = queue.remove();
			Node currNode = currSearchNode.node;
			double currCost = currSearchNode.cost;

			// Check if we have reached the end node
			if (currNode == endNode) {
				return currSearchNode;
			}

			// Check if we have already visited this node with a lower cost
			SearchNode prevSearchNode = visited.get(currNode);
			if (prevSearchNode != null && prevSearchNode.cost <= currCost) {
				continue;
			}

			// Add this node to the visited set
			visited.put(currNode, currSearchNode);

			// Update the costs of all neighbors of this node
			for (Edge edge : currNode.edgesLeaving) {
				Node neighborNode = edge.successor;
				double neighborCost = currCost + edge.data.doubleValue();
				SearchNode neighborSearchNode = new SearchNode(neighborNode, neighborCost, currSearchNode);
				queue.add(neighborSearchNode);
			}
		}

		// If we get here, there is no path from start to end
		throw new NoSuchElementException("No path found from start to end node");
	}

	/**
	 * Returns the list of data values from nodes along the shortest path from the
	 * node with the provided start value through the node with the provided end
	 * value. This list of data values starts with the start value, ends with the
	 * end value, and contains intermediary values in the order they are encountered
	 * while traversing this shorteset path. This method uses Dijkstra's shortest
	 * path algorithm to find this solution.
	 *
	 * @param start the data item in the starting node for the path
	 * @param end   the data item in the destination node for the path
	 * @return list of data item from node along this shortest path
	 */
	public List<NodeType> shortestPathData(NodeType start, NodeType end) {
		// TODO: implement in step 7

		LinkedList<NodeType> dataSequence = new LinkedList<>();

		SearchNode resultNode = computeShortestPath(start, end);

		// Traverse the SearchNode tree to build the data sequence
		while (resultNode != null) {
			dataSequence.addFirst(resultNode.node.data);
			resultNode = resultNode.predecessor;
		}

		return dataSequence;
	}

	/**
	 * Returns the cost of the path (sum over edge weights) of the shortest path
	 * freom the node containing the start data to the node containing the end data.
	 * This method uses Dijkstra's shortest path algorithm to find this solution.
	 *
	 * @param start the data item in the starting node for the path
	 * @param end   the data item in the destination node for the path
	 * @return the cost of the shortest path between these nodes
	 */
	public double shortestPathCost(NodeType start, NodeType end) {
		// TODO: implement in step 7
		return computeShortestPath(start, end).cost;
	}
}
