
import java.util.ArrayList;
import java.util.NoSuchElementException;

// imports to take care of junit tests
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class AlgorithmEngineerTests {
    
    MazeGraphAE<String, Integer> graph;

    /*
     * This method creates the "maze" graph that will be used in the different
     * test conditions
     */
    @BeforeEach
    public void _createGraph(){
        graph = new MazeGraphAE<String, Integer>();
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("B", "E", 6);
        graph.insertEdge("C", "B", 4);     
        graph.insertEdge("C", "D", 9);
        graph.insertEdge("C", "F", 5);   
        graph.insertEdge("D", "F", 2);
        graph.insertEdge("E", "D", 7);
        graph.insertEdge("E", "F", 3);
    }

    /*
     * This method is meant to just test the shortestPath implementation that the
     * findShortestPathWithMiddleNode() relies on
     */
    @Test
    public void testPath(){
        // create the expected path the implementation should find
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("A");
        expected.add("C");
        expected.add("F");
        // check to make sure the path and cost are correct
        assertEquals(graph.shortestPathData("A", "F"), expected);
        assertEquals(graph.shortestPathCost("A", "F"), 7);
    }

    /*
     * This method is meant to test the findShortestPathWithMiddleNode()
     * implementation with the same path as above but with the condition that it must
     * pass through node D
     */
    @Test
    public void testPathWithMiddleNode(){
        // create the expected path the implementation should find
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("A");
        expected.add("C");
        expected.add("D");
        expected.add("F");
        // check to make sure the path and cost are correct
        assertEquals(graph.shortestPathWithNodeData("A", "D", "F"), expected);
        assertEquals(graph.shortestPathWithNodeCost("A", "D", "F"), 13);
    }

    /*
     * This test method is meant to test the case where the node does not exist in the
     * graph
     */
    @Test
    public void testPathMissingNodes(){
        try{
            // call shortestPathWithNodeData() with a node that does not exist
            graph.shortestPathWithNodeData("A", "D", "G");
            // if an exception is not throw then fail
            assertEquals(1,0);
        }
        catch(NoSuchElementException e){
            // do nothing as the first test was passed
        }
        try{
            // call shortestPathWithNodeCost() with a node that does not exist
            graph.shortestPathWithNodeCost("A", "D", "G");
            // if an exception is not throw then fail
            assertEquals(1,0);
        }
        catch(NoSuchElementException e){
            // do nothing as the first test was passed
        }
        // both cases threw exceptions so pass
        assertEquals(1, 1);
    }

    /*
     * This test method is meant to test the case where no path exists from the start node to
     * the middle node
     */
    @Test
    public void testNoPathExistStartToMiddle(){
        try{
            // try to find the data from a node with no outgoing edges
            graph.shortestPathWithNodeData("F", "D", "G");
            // if an exception is not throw then fail
            assertEquals(1,0);
        }
        catch(NoSuchElementException e){
            // do nothing as the first test was passed
        }
        try{
            // try to find the cost from a node with no outgoing edges
            graph.shortestPathWithNodeCost("F", "D", "G");
            // if an exception is not throw then fail
            assertEquals(1,0);
        }
        catch(NoSuchElementException e){
            // do nothing as the first test was passed
        }
        // all the correct exceptions were thrown so pass
        assertEquals(1, 1);
    }

    /*
     * This test method is meant to test the case where no path exists from the middle node to
     * the end node
     */
    @Test
    public void testNoPathExistMiddleToEnd(){
        try{
            // try to find the data from a node with no outgoing edges
            graph.shortestPathWithNodeData("A", "F", "G");
            // if an exception is not throw then fail
            assertEquals(1,0);
        }
        catch(NoSuchElementException e){
            // do nothing as the first test was passed
        }
        try{
            // try to find the data from a node with no outgoing edges
            graph.shortestPathWithNodeCost("A", "F", "G");
            // if an exception is not throw then fail
            assertEquals(1,0);
        }
        catch(NoSuchElementException e){
            // do nothing as the first test was passed
        }
        // all the correct exceptions were thrown so pass
        assertEquals(1, 1);
    }

    @Test
    public void IntegrationTest1(){
        MazeReaderDW<String, Integer> mazeReader = new MazeReaderDW<>();
        ArrayList<String> expected = new ArrayList<String>();
        MazeGraphAE<String, Integer> graph = new MazeGraphAE<>();
        expected.add("A");
        expected.add("C");
        expected.add("N");
        try{
            graph = mazeReader.loadMaze("maze1.dot");
        }
        catch(Exception e){
            assertEquals(1, 0);
        }
        try{
            assertEquals(expected, graph.shortestPathWithNodeData("A", "C", "N"));
        }
        catch(Exception e){
            assertEquals(1, 0);
        }
        try{
            assertEquals(9, graph.shortestPathWithNodeCost("A", "C", "N"));
        }
        catch(Exception e){
            assertEquals(1, 0);
        }
    }

    @Test
    public void IntegrationTest2(){
        MazeReaderDW<String, Integer> mazeReader = new MazeReaderDW<>();
        ArrayList<String> expected = new ArrayList<String>();
        MazeGraphAE<String, Integer> graph = new MazeGraphAE<>();
        expected.add("A");
        expected.add("C");
        expected.add("G");
        expected.add("H");
        expected.add("L");
        expected.add("M");
        expected.add("N");
        try{
            graph = mazeReader.loadMaze("maze3.dot");
        }
        catch(Exception e){
            assertEquals(1, 0);
        }
        try{
            assertEquals(expected, graph.shortestPathWithNodeData("A", "C", "N"));
        }
        catch(Exception e){
            assertEquals(1, 0);
        }
        try{
            assertEquals(27, graph.shortestPathWithNodeCost("A", "C", "N"));
        }
        catch(Exception e){
            assertEquals(1, 0);
        }
    }

    @Test
    public void CodeReviewOfBackendDeveloper1(){
        MazeGraphAE<String, Integer> graph = new MazeGraphAE<>();
        MazeMapperBD<String, Integer> backend = new MazeMapperBD<>(graph);
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("A");
        expected.add("C");
        expected.add("N");
        try{
            backend.getMaze("maze1.dot");
        }
        catch(Exception e){
            assertEquals(1, 0);
        }
        try{
            assertEquals(expected, backend.findShortestPath("A", "C", "N"));
        }
        catch(Exception e){
            assertEquals(1,0);
        }
        try{
            assertEquals(9, backend.findShortestPathCost("A", "C", "N"));
        }
        catch(Exception e){
            assertEquals(1,0);
        }
    }

    @Test
    public void CodeReviewOfBackendDeveloper2(){
        MazeGraphAE<String, Integer> graph = new MazeGraphAE<>();
        MazeMapperBD<String, Integer> backend = new MazeMapperBD<>(graph);
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("A");
        expected.add("C");
        expected.add("G");
        expected.add("H");
        expected.add("L");
        expected.add("M");
        expected.add("N");
        try{
            backend.getMaze("maze3.dot");
        }
        catch(Exception e){
            assertEquals(1, 0);
        }
        try{
            assertEquals(expected, backend.findShortestPath("A", "C", "N"));
        }
        catch(Exception e){
            assertEquals(1, 0);
        }
        try{
            assertEquals(27, backend.findShortestPathCost("A", "C", "N"));
        }
        catch(Exception e){
            assertEquals(1, 0);
        }
    }
}
