
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MazeReaderDW<NodeType, EdgeType extends Number> implements MazeReaderDWInterface<NodeType, EdgeType> {

    // Initialize the startNode, endNode, and middleNode to node A, node N, and node
    // C respectively
    private NodeType startNode = (NodeType) "A";
    private NodeType endNode = (NodeType) "N";
    private NodeType middleNode = (NodeType) "C";

    public MazeGraphAE<NodeType, EdgeType> loadMaze(String filename) throws FileNotFoundException {

        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        MazeGraphAE<NodeType, EdgeType> mazeGraph = new MazeGraphAE<>();

        // Regular expressions for parsing the DOT file
        Pattern edgePattern = Pattern.compile("^(\\S+)\\s*->\\s*(\\S+)\\s*\\[label\\s*=\\s*\"?(\\d+)\"?\\];$");

        // Add nodes and edges to the graph
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            Matcher edgeMatcher = edgePattern.matcher(line);
            if (edgeMatcher.find()) {
                NodeType source = (NodeType) edgeMatcher.group(1);
                NodeType target = (NodeType) edgeMatcher.group(2);
                EdgeType weight = (EdgeType) Integer.valueOf(edgeMatcher.group(3));

                // Insert source and target nodes if they don't exist
                mazeGraph.insertNode(source);
                mazeGraph.insertNode(target);

                // Insert the edge between source and target nodes
                mazeGraph.insertEdge(source, target, weight);
            }
        }

        scanner.close();
        return mazeGraph;
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
    public NodeType getMiddleNode(BaseGraph<NodeType, EdgeType> graph) {
        return middleNode;
    }

    public static void main(String[] args) {
        MazeReaderDW<String, Integer> mazeReader = new MazeReaderDW<>();
        try {
            BaseGraph<String, Integer> mazeGraph = mazeReader.loadMaze("maze3.dot");
            System.out.println(mazeGraph.getEdgeCount());
            System.out.println(mazeGraph.getNodeCount());
            System.out.println(mazeReader.getStartNode(mazeGraph));
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found. Please check the file path.");
        }
    }

}
