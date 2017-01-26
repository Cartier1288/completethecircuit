import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Class which holds all of the information regarding a board and its components.
public class Board {
	public static Component[] components;
	
	public Board(String boardPath) { //Constructor takes the path, as a String, to the file containing the board information.
		/* 
		Layout of board format:
		
		 title=Board Name;
		 description=Board description;
		 
		 / (3) (1) (-1) (1) (4)
		   (2) (-1) (-1) (-1) (2) 
		   (5) (1) (8, 1, 10) (1) (6) / ;Default board
		
		 / {[0, 2](11, -1, 3)} / ;Corrections
		*/
		
		try {
			Scanner boardScanner = new Scanner(new File(boardPath)); //Create a Scanner to iterate through the board file
			/*
			 * Set the delimiters for the scanner; title= (for the title), 
			 * description= (for the description), ; (for the end of the 
			 * information), and / (for the components).
			 */
			boardScanner.useDelimiter("title=|description=|;|\\/");
			
			String title = boardScanner.next(); //Get the title
			if(title.equals("")) System.out.println("Improper formatting for board: " + boardPath); //If the title is empty, the file is improperly formatted.
			this.boardName = title;
			
			boardScanner.next(); //Go to the next section
			String description = boardScanner.next(); //Get the description
			if(description.equals("")) System.out.println("Improper formatting for board: " + boardPath); //If the description is empty, the file is improperly formatted.
			this.boardDescription = description;
			
			
			boardScanner.next(); //Go to the next section
			String defaultBoard = boardScanner.next(); ///Get the default components
			
			//Make a matcher to extract the component information, for one line.
			Matcher widthMatcher = Pattern.compile("\\((.*?)\\)").matcher(defaultBoard.substring(0, defaultBoard.indexOf('\n')));
			int columns = 0; //Set the columns to a default of 0
			
			while(widthMatcher.find()) columns++; //For every extracted string increment the columns
			
			Matcher defaultMatcher = Pattern.compile("\\((.*?)\\)").matcher(defaultBoard); //Make a matcher to extract all component information
			
			//Get the total number of components
			int totalLength = 0;
			while(defaultMatcher.find()) totalLength++;
			
			int rows = totalLength / columns; //Calculate the number of rows using the total number of components divided by columns
			
			boardComponents = new ComponentIndex[rows][columns]; //Initialize the boardComponents (default) with dimensions rows x columns
			
			defaultMatcher.reset(); //Reset the matcher, in order to go through the information
			int inc = 0;
			while(defaultMatcher.find()) { //Iterate through the component information
				String[] component = defaultMatcher.group(1).replaceAll(" ", "").split(","); //Split the component into index, count, and value.
				int index = Integer.parseInt(component[0]); //Parse the index
				int count = -1;
				double value = -1;
				
				if(component.length > 1) //If there is enough information, calculate the count.
					count = Integer.parseInt(component[1]);
				if(component.length > 2) //If there is enough information, calculate the value (double).
					value = Double.parseDouble(component[2]);
				
				boardComponents[inc / columns][inc % columns] = new ComponentIndex(index, count, value); //Calculate the coordinates of the component, and fill it with an instantiated ComponentIndex.
				
				inc++; //Increment the index
			}
			
			
			//Get the correct components 
			boardScanner.next();
			String correctBoard = boardScanner.next();
			
			//Initialize boardChanges with empty space
			boardChanges = new ComponentIndex[rows][columns];
			for(int i = 0; i < rows; i++)
				for(int j = 0; j < columns; j++)
					boardChanges[i][j] = new ComponentIndex(-1);
			
			//Create a Matcher to extract the board changes
			Matcher changeMatcher = Pattern.compile("\\{(.*?)\\}").matcher(correctBoard);
			
			//Iterate through the changes
			while(changeMatcher.find()) {
				String match = changeMatcher.group(1).replaceAll(" ", "");
				int middle = match.indexOf(']');
				String componentCoordinates = match.substring(1, middle); //Extract the component coordinates
				String componentInfo = match.substring(middle + 2, match.length() - 1); //Extract the component information
				
				String[] coordinates = componentCoordinates.split(","); //Separate the coordinates
				int x = Integer.parseInt(coordinates[0]),
					y = Integer.parseInt(coordinates[1]);
				
				String[] component = componentInfo.split(","); //Split the component information
				int index = Integer.parseInt(component[0]); //Parse the component index
				int count = -1;
				double value = -1.0;
				
				if(component.length > 1) //If there is enough information, parse the count.
					count = Integer.parseInt(component[1]);
				if(component.length > 2) //If there is enough information, parse the value.
					value = Double.parseDouble(component[2]);
				
				boardChanges[y][x] = new ComponentIndex(index, count, value); //Create a new ComponentIndex, using the gathered information, in its respective coordinate.
				
				numberOfChanges++; //Increment the number of changes, for external use (to test when the board is completed).
			}
			
			boardScanner.close(); //Close the scanner
			
			//Check if there is a progress file for the board, if so, the board has been completed, so scan it to extract the number of misplaced components.
			File progressFile = new File("res/progress/" + boardName.replaceAll(" ", "").toLowerCase() + "-progress.pgs");
			if(progressFile.exists()) {
				Scanner progressScanner = new Scanner(progressFile);
				
				completion = true;
				misplaced = Integer.parseInt(progressScanner.nextLine());
				
				progressScanner.close();
			}
		} catch (FileNotFoundException | IllegalStateException e) {
			System.out.println("Unable to read board file: " + boardPath);
		}
	}
	
	//Function to get the name of the board
	public String getName() {
		return boardName;
	}
	
	//Function to get the description of the board
	public String getDescription() {
		return boardDescription;
	}
	
	//Function to get whether the board has been completed
	public boolean getCompletion() {
		return completion;
	}
	
	//Method to set the board as complete
	public void setComplete() {
		completion = true;
	}
	
	//Function to get the number of misplaced components
	public int getMisplaced() {
		return misplaced;
	}
	
	//Function to set the number of misplaced components
	public void setMisplaced(int misplaced) {
		this.misplaced = misplaced;
	}

	//Function to get the number of changed components
	public int getNumberOfChanges() {
		return numberOfChanges;
	}
	
	//Function to test whether a ComponentIndex, at x by y, outlines the correct component.
	public boolean match(ComponentIndex component, int x, int y) {
		ComponentIndex correctComponent = boardChanges[y][x];
		return (correctComponent.index == component.index 
			 && correctComponent.count == component.count 
			 && correctComponent.value == component.value);
	}
	
	//Function to get the default board
	public ComponentIndex[][] getDefaultBoardComponents() {
		return boardComponents;
	}
	
	
	private String boardName = "This is a title";
	private String boardDescription = "This is a description";
	
	private boolean completion = false;
	private int misplaced = 0;
	
	private ComponentIndex[][] boardComponents = new ComponentIndex[5][5]; //Two-dimensional ComponentIndex array holding the default board components
	private ComponentIndex[][] boardChanges = new ComponentIndex[5][5]; //Two-dimensional ComponentIndex array holding the changes to the board components (the components needing to be placed by the user)
	
	private int numberOfChanges = 0; //Integer holding the number of correct changes which must be made to the default board, to complete the board.
}
