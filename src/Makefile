run:
	javac -cp .:junit5.jar *.java
	java MazeMapperFD

runTests:
	make runBackendDeveloperTests
	make runDataWranglerTests
	make runFrontendDeveloperTests
	make runAlgorithmEngineerTests

# Default target
default: runTests

# Compile the DataWranglerTests.java file and its dependencies with JUnit 5.
DataWranglerTests.class: DataWranglerTests.java MazeReaderDW.java BaseGraph.java GraphDataDW.java
	javac -cp .:junit5.jar DataWranglerTests.java MazeReaderDW.java BaseGraph.java GraphDataDW.java

# Run the tests using JUnit 5.
runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar -cp . -c DataWranglerTests

# Clean target to remove compiled files.
clean:
	rm -f *.class

#FrontendDeveloperTests target
runFrontendDeveloperTests: FrontendDeveloperTests.class	
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java MazeMapperFD.class 
	javac -cp .:junit5.jar FrontendDeveloperTests.java

MazeMapperFD.class: MazeMapperFD.java MazeMapperFDInterface.java
	javac MazeMapperFD.java MazeMapperFDInterface.java


runAlgorithmEngineerTests:
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
	java -jar junit5.jar -cp . --select-class=AlgorithmEngineerTests

# Run the BackendDevelperTests
runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java MazeMapperBD.class
	javac -cp .:junit5.jar BackendDeveloperTests.java

MazeMapperBD.class: MazeMapperBD.java MazeMapperBDInterface.java
	javac MazeMapperBD.java MazeMapperBDInterface.java
