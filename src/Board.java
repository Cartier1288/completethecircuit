import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board {
	public static Component[] components;
	
	public Board(String boardPath) {
		/* 
		Layout of board format:
		
		 -Board Name-
		 +Board description+
		 
		 | (3) (1) (-1) (1) (4) ; (New line)
		   (2) (-1) (-1) (-1) (2) ;
		   (5) (1) (6, 0) (1) (6) | (End file)
		
		 | [0, 2](5, 0, 10) |
		 
		*/
		
		for(int i = 0; i < boardComponents.length; i++)
			for(int j = 0; j < boardComponents[0].length; j++) {
				if(i % 2 == 0)
					boardComponents[i][j] = new ComponentIndex(0);
				else
					boardComponents[i][j] = new ComponentIndex(8, i + (j * 5), (i * 10) + (j * 100));
				boardChanges[i][j] = new ComponentIndex(-1);
			}
		
		try {
			Scanner boardScanner = new Scanner(new File(boardPath));
			boardScanner.useDelimiter("title=|description=|;|\\/");
			
			String title = boardScanner.next();
			if(title.equals("")) System.out.println("Improper formatting for board: " + boardPath);
			this.boardName = title;
			
			boardScanner.next();
			String description = boardScanner.next();
			if(description.equals("")) System.out.println("Improper formatting for board: " + boardPath);
			this.boardDescription = description;
			
			
			boardScanner.next();
			String defaultBoard = boardScanner.next();
			
			Matcher widthMatcher = Pattern.compile("\\((.*?)\\)").matcher(defaultBoard.substring(0, defaultBoard.indexOf('\n')));
			int columns = 0;
			
			while(widthMatcher.find()) columns++; 
			
			Matcher defaultMatcher = Pattern.compile("\\((.*?)\\)").matcher(defaultBoard);
			
			int totalLength = 0;
			while(defaultMatcher.find()) totalLength++;
			
			int rows = totalLength / columns;
			
			boardComponents = new ComponentIndex[rows][columns];
			
			defaultMatcher.reset();
			int inc = 0;
			while(defaultMatcher.find()) {
				String[] component = defaultMatcher.group(1).replaceAll(" ", "").split(",");
				int index = Integer.parseInt(component[0]);
				int count = -1;
				double value = -1;
				
				if(component.length > 1)
					count = Integer.parseInt(component[1]);
				if(component.length > 2)
					value = Double.parseDouble(component[2]);
				
				boardComponents[inc / columns][inc % columns] = new ComponentIndex(index, count, value);
				
				inc++;
			}
			
			
			boardScanner.next();
			String correctBoard = boardScanner.next();
			
			boardChanges = new ComponentIndex[rows][columns];
			for(int i = 0; i < rows; i++)
				for(int j = 0; j < columns; j++)
					boardChanges[i][j] = new ComponentIndex(-1);
			
			Matcher changeMatcher = Pattern.compile("\\{(.*?)\\}").matcher(correctBoard);
			
			while(changeMatcher.find()) {
				String match = changeMatcher.group(1).replaceAll(" ", "");
				int middle = match.indexOf(']');
				String componentCoordinates = match.substring(1, middle);
				String componentInfo = match.substring(middle + 2, match.length() - 1);
				
				String[] coordinates = componentCoordinates.split(",");
				int x = Integer.parseInt(coordinates[0]),
					y = Integer.parseInt(coordinates[1]);
				
				String[] component = componentInfo.split(",");
				int index = Integer.parseInt(component[0]);
				int count = -1;
				double value = -1.0;
				
				if(component.length > 1)
					count = Integer.parseInt(component[1]);
				if(component.length > 2)
					value = Double.parseDouble(component[2]);
				
				boardChanges[y][x] = new ComponentIndex(index, count, value);
				
				numberOfChanges++;
			}
			
			boardScanner.close();
			
			
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
	
	public String getName() {
		return boardName;
	}
	
	public String getDescription() {
		return boardDescription;
	}
	
	public boolean getCompletion() {
		return completion;
	}
	
	public void setComplete() {
		completion = true;
	}
	
	public int getMisplaced() {
		return misplaced;
	}
	
	public void setMisplaced(int misplaced) {
		this.misplaced = misplaced;
	}
	
	public int getNumberOfChanges() {
		return numberOfChanges;
	}
	
	public boolean match(ComponentIndex component, int x, int y) {
		ComponentIndex correctComponent = boardChanges[y][x];
		System.out.println("Index: " + correctComponent.index + " to " + component.index + ", " + correctComponent.count + " to " + component.count + ", " + correctComponent.value + " to " + component.value);
		return (correctComponent.index == component.index 
			 && correctComponent.count == component.count 
			 && correctComponent.value == component.value);
	}
	
	public ComponentIndex[][] getDefaultBoardComponents() {
		return boardComponents;
	}
	
	
	private String boardName = "This is a title";
	private String boardDescription = "This is a description";
	
	private boolean completion = false;
	private int misplaced = 0;
	
	private ComponentIndex[][] boardComponents = new ComponentIndex[5][5];
	private ComponentIndex[][] boardChanges = new ComponentIndex[5][5];
	
	private int numberOfChanges = 0;
}
