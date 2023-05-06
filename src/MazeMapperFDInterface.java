import java.io.FileNotFoundException;

/**
 * The MazeMapperFDInterface interface defines the methods required to interact with a maze mapping program.
 */
public interface MazeMapperFDInterface {
    /**
     * Starts the command loop for the maze mapping program.
     */
    public void runCommandLoop();
    /**
     * Prompts the user with the main menu options and returns the selected option.
     * @return the selected option character.
     */
    public char mainMenuPrompt();
    /**
     * Finds and returns the shortest path between the start and end nodes of the maze.
     * @return the shortest path as a string.
     */
    public String findShortestPath();
    /**
     * Prompts the user to input the maze.
     * @return the maze as a string.
     */
    public String mazeInput();
    /**
     * Finds and returns the shortest cost between the start and end nodes of the maze.
     * @return the shortest cost as a string.
     */
    public String findShortestCost();
    /**
     * Allows the user to choose one of the 3 mazes.
     * @throws FileNotFoundException
     */
    public void chooseMaze() throws FileNotFoundException;

}
