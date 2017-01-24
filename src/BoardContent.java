import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardContent extends JPanel {
	private static final long serialVersionUID = -5438052798440144942L;

	public BoardContent(Board board) {
		this.board = board;
		
		ComponentIndex[][] defaultBoard = board.getDefaultBoardComponents();
		
		currentBoard = new ComponentIndex[defaultBoard.length][defaultBoard[0].length];
		
		for(int i = 0; i < defaultBoard.length; i++)
			for(int j = 0; j < defaultBoard[0].length; j++)
				currentBoard[i][j] = defaultBoard[i][j];
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		updateBoardPosition();
		
		int width = this.getWidth();
		int height = this.getHeight();

		for (int i = 0; i < width; i += Component.ComponentSize)
			g.drawLine(i, 0, i, height);

		for (int i = 0; i < height; i += Component.ComponentSize)
			g.drawLine(0, i, width, i);
		
//		g.fillOval(startingXPosition - 10, startingYPosition - 10, 20, 20);
//		g.fillOval(endingXPosition - 10, startingYPosition - 10, 20, 20);
//		g.fillOval(startingXPosition - 10, endingYPosition - 10, 20, 20);
//		g.fillOval(endingXPosition - 10, endingYPosition -10, 20, 20);
		
		for(int i = 0; i < currentBoard.length; i++)
			for(int j = 0; j < currentBoard[0].length; j++) {
				ComponentIndex currentIndex = currentBoard[i][j];
				if(currentIndex.index != -1) {
					int x = (i * Component.ComponentSize) + startingXPosition;
					int y = (j * Component.ComponentSize) + startingYPosition;
					g.drawImage(Component.components[currentIndex.index].getSymbol(), x, y, null);
					if(currentIndex.count != -1)
						g.drawString(Component.components[currentIndex.index].getCharacter() + "" + currentIndex.count, x + 2, y + 12);
				}
			}
	}
	
	private void updateBoardPosition() {
		float width = ((float)(this.getWidth() / 2.0f) / Component.ComponentSize);
		float height = ((float)(this.getHeight() / 2.0f) / Component.ComponentSize);
		
		startingXPosition = (int)Math.floor(width - (float)(currentBoard.length / 2.0)) * Component.ComponentSize;
		endingXPosition = startingXPosition + (currentBoard.length * Component.ComponentSize);
		
		startingYPosition = (int)Math.floor(height - (float)(currentBoard[0].length / 2.0)) * Component.ComponentSize;
		endingYPosition = startingYPosition + (currentBoard[0].length * Component.ComponentSize);
	}

	
	private int startingXPosition = 0;
	private int endingXPosition = 0;
	
	private int startingYPosition = 0;
	private int endingYPosition = 0;

	private Board board = null;
	private ComponentIndex[][] currentBoard;
}
