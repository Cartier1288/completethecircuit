
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
		
		 | [0, 2] (5, 0, 10) |
		 
		*/
		
		for(int i = 0; i < boardComponents.length; i++)
			for(int j = 0; j < boardComponents[0].length; j++) {
				if(i % 2 == 0)
					boardComponents[i][j] = new ComponentIndex(0);
				else
					boardComponents[i][j] = new ComponentIndex(8, i + (j * 5), (i * 10) + (j * 100));
				boardChanges[i][j] = new ComponentIndex(-1);
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
		completion = false;
	}
	
	public int getMisplaced() {
		return misplaced;
	}
	
	public void setMisplaced(int misplaced) {
		this.misplaced = misplaced;
	}
	
	public boolean match(ComponentIndex component, int x, int y) {
		ComponentIndex correctComponent = boardChanges[x][y];
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
}
