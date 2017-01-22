
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
	}
	
	public String getBoardName() {
		return boardName;
	}
	
	public String getBoardDescription() {
		return boardDescription;
	}
	
	public boolean getCompletion() {
		return completion;
	}
	
	public int getMisplaced() {
		return misplaced;
	}
	
//	public boolean match(int index ) {
//		
//	}
	
	
	private String boardName = "";
	private String boardDescription = "";
	
	private boolean completion = false;
	private int misplaced = 0;
	
	private ComponentIndex[][] boardComponents;
	private ComponentIndex[][] boardChanges;
}
