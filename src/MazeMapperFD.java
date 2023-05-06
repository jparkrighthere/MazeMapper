
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class MazeMapperFD<NodeType,EdgeType extends Number> implements MazeMapperFDInterface {
    private MazeMapperBDInterface<NodeType, EdgeType> backend;
    private Scanner user;
    
    
    public MazeMapperFD(Scanner userInput, MazeMapperBDInterface<NodeType, EdgeType> backend) {
      this.user = userInput;
      this.backend = backend;
    }
    
    /**
     * Helper method to display a 79 column wide row of dashes: a horizontal rule.
     */
    private void hr() {
        System.out.println("-------------------------------------------------------------------------------");
    }

    /**
     * This loop repeated prompts the user for commands and displays appropriate
     * feedback for each. This continues until the user enters 'q' to quit.
     */
    public void runCommandLoop() {
        hr(); // display welcome message
        System.out.println("Welcome to the Maze Mapper.");
        hr();

        char command = '\0';
        while (command != 'Q') { // main loop continues until user chooses to quit

            command = this.mainMenuPrompt();

            switch (command) {

                case 'F': // System.out.println(" find shortest path in maze");
                    System.out.println(findShortestPath());
                    break;

                case 'S': // System.out.println(" find shortest cost of the path");
                    System.out.println(findShortestCost());
                    break;

                case 'C': // System.out.println(" chooses a a maze to display");
                    try {
                        chooseMaze();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;

                case 'Q': // System.out.println(" [Q]uit");
                    // do nothing, containing loop condition will fail
                    break;

                default:
                    System.out.println(
                            "Didn't recognize that command.  Please type one of the letters presented within []s to identify the command you would like to choose.");
                    break;

            }
            System.out.println();
        }

        hr(); // thank user before ending this application
        System.out.println("Thank you for using the Maze Mapper!");
        hr();
    }
    
    /**
     * Prints the command options to System.out and reads user's choice through
     * userInput scanner.
     */
    public char mainMenuPrompt() {
        // display menu of choices
        System.out.println("Choose a command from the list below:");
        System.out.println("    [F]ind shortest path");
        System.out.println("    Find [S]hortest cost");
        System.out.println("    [C]hoose maze to display");
        System.out.println("    [Q]uit");

        // read in user's choice, and trim away any leading or trailing whitespace
        System.out.print("Choose command: ");
        String input = user.nextLine().trim();
        if (input.length() == 0) // if user's choice is blank, return null character
            return '\0';
        // otherwise, return an uppercase version of the first character in input
        return Character.toUpperCase(input.charAt(0));
    }
    
    /**
     * This method gives takes user input when they choose a maze
     */
    public String mazeInput() {
          while(true) {
            String word = "";
            String input = user.nextLine().trim();
            if (input.length() == 0 || (!input.equals("1") && !input.equals("2") && !input.equals("3"))) { // if input is empty, prompt for another input
                System.out.println("Cannot take empty input, or input was not number 1-3.");
            }
            else {
                // otherwise return the word entered
                word = input;
                return word;
            }
        }
    }

    /**
     * returns the Shortest Path as a string format
     */
    public String findShortestPath() {
        List<String> shortestPath = (List<String>)backend.findShortestPath(backend.getStartNode(), backend.getMiddleNode(), backend.getEndNode());
        return "Shortest Path: " + String.join(" -> ", shortestPath);
    }

    /**
     * returns the Shortest Cost as a string format
     */
    public String findShortestCost() {
        double shortestCost = backend.findShortestPathCost(backend.getStartNode(), backend.getMiddleNode(), backend.getEndNode());
        return "Shortest Cost: " + shortestCost;
    }

    /**
     * Based on the user input (1,2, or 3), update the maze and show users all rooms in the maze.
     * @throws FileNotFoundException
     */
    public void chooseMaze() throws FileNotFoundException {
        System.out.println("Please input a number 1, 2, or 3 to choose one of the three mazes.");
        String maze = mazeInput();

        if(maze.equals("1")) {
            MazeGraphInterfaceAE<NodeType, EdgeType>selectedMaze = backend.getMaze("maze1.dot");
            System.out.print("The rooms in the mazes are: ");
            NodeType[] list = (NodeType []) backend.getNodeNames(selectedMaze);

            for (NodeType room : list) {
                System.out.print("Room " + room.toString() + " ");
            }
            
            System.out.print("(They are not in the order)");
            System.out.println();
        }

        else if (maze.equals("2")) {
            MazeGraphInterfaceAE<NodeType, EdgeType> selectedMaze = backend.getMaze("maze2.dot");
            System.out.print("The rooms in the mazes are: ");
            NodeType[] list = (NodeType []) backend.getNodeNames(selectedMaze);

            for (NodeType room : list) {
                System.out.print("Room " + room.toString() + " ");
            }

            System.out.print("(They are not in the order)");
            System.out.println();
        }

        else if (maze.equals("3")) {
            MazeGraphInterfaceAE<NodeType, EdgeType> selectedMaze = backend.getMaze("maze3.dot");
            System.out.print("The rooms in the mazes are: ");
            NodeType[] list = (NodeType []) backend.getNodeNames(selectedMaze);

            for (NodeType room : list) {
                System.out.print("Room " + room.toString() + " ");
            }
            
            System.out.print("(They are not in the order)");
            System.out.println();
        }

    }
    public static void main(String[] args) {
      
        Scanner scanner = new Scanner(System.in);
        MazeGraphAE graph = new MazeGraphAE<>();
        MazeMapperBD bd = new MazeMapperBD<>(graph);
        MazeMapperFD testFD= new MazeMapperFD(scanner, bd);
        
        testFD.runCommandLoop();
    }
}


