
import java.util.ArrayList;
import java.util.List;

/**
 * This GraphDataBD class is placeholder DW class for BD.
 * 
 */  
public class GraphDataBD implements GraphDataBDInterface<String, Double>{

	MazeGraphBD<String, Double> fooGraph;
	private List<String> nodes;
	private List<List<String>> edges;
	private double edgeWeight;
	
	// constructor
	public GraphDataBD(MazeGraphBD<String, Double> fooGraph) {
		this.fooGraph = fooGraph;
	}
	
	/**
	 * hard coding for getNodes() methods to check 
	 * getMaze method of MazeMapper class works well
	 */
	@Override
	public List<String> getNodes() {
		this.nodes = new ArrayList<String>();
		this.nodes.add("Start");
		this.nodes.add("End");
		return this.nodes;
	}

	/**
	 * hard coding for getEdges() methods to check 
	 * getMaze method of MazeMapper class works well
	 */
	@Override
	public List<List<String>> getEdges() {
		this.edges = new ArrayList<List<String>>();
		this.edges.add(this.nodes);
		return this.edges;
	}

	/**
	 * hard coding for getEdgeWeight() methods to check 
	 * getMaze method of MazeMapper class works well
	 */
	@Override
	public double getEdgeWeight(String source, String target) {
		this.edgeWeight = 2.0;
		return this.edgeWeight;
	}
}
