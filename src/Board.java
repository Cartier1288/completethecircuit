
public class Board {
	public static Component[] components;
	
	public Board(String boardPath) {
		/* 
		Layout of board format:
		
		 -Board Name-
		 
		 (3) (1) (5, 0) (1) (4) ; (New line)
		 (2) (-1) (-1) (-1) (2) ;
		 (5) (1) (6, 0) (1) (6) | (End file)
		
		*/
	}
	
	public String getBoardName() {
		return boardName;
	}
	
	
	private String boardName = "";
	
	private ComponentIndex[][] boardComponents;
}
