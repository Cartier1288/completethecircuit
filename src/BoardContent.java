import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardContent extends JPanel implements MouseListener {
	private static final long serialVersionUID = -5438052798440144942L;

	public BoardContent(Board board, ComponentNavigation navigation, BoardEditor boardEditor) {
		this.addMouseListener(this);
		
		this.board = board;
		this.navigation = navigation;
		this.boardEditor = boardEditor;
		
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

		for (int i = 0; i < width; i += Component.COMPONENT_SIZE)
			g.drawLine(i, 0, i, height);

		for (int i = 0; i < height; i += Component.COMPONENT_SIZE)
			g.drawLine(0, i, width, i);
		
//		g.fillOval(startingXPosition - 10, startingYPosition - 10, 20, 20);
//		g.fillOval(endingXPosition - 10, startingYPosition - 10, 20, 20);
//		g.fillOval(startingXPosition - 10, endingYPosition - 10, 20, 20);
//		g.fillOval(endingXPosition - 10, endingYPosition -10, 20, 20);
		
		for(int i = 0; i < currentBoard[0].length; i++)
			for(int j = 0; j < currentBoard.length; j++) {
				ComponentIndex currentIndex = currentBoard[j][i];
				if(currentIndex.index != -1) {
					int x = (i * Component.COMPONENT_SIZE) + startingXPosition;
					int y = (j * Component.COMPONENT_SIZE) + startingYPosition;
					g.drawImage(Component.components[currentIndex.index].getSymbol(), x, y, null);
					if(currentIndex.count != -1)
						g.drawString(Component.components[currentIndex.index].getCharacter() + "" + currentIndex.count, x + 2, y + 12);
				}
			}
	}
	
	private void updateBoardPosition() {
		float width = ((float)(this.getWidth() / 2.0f) / Component.COMPONENT_SIZE);
		float height = ((float)(this.getHeight() / 2.0f) / Component.COMPONENT_SIZE);
		
		startingXPosition = (int)Math.floor(width - (float)(currentBoard[0].length / 2.0)) * Component.COMPONENT_SIZE;
		endingXPosition = startingXPosition + (currentBoard[0].length * Component.COMPONENT_SIZE);
		
		startingYPosition = (int)Math.floor(height - (float)(currentBoard.length / 2.0)) * Component.COMPONENT_SIZE;
		endingYPosition = startingYPosition + (currentBoard.length * Component.COMPONENT_SIZE);
	}

	public void reset() {
		ComponentIndex[][] defaultBoard = board.getDefaultBoardComponents();
		
		for(int i = 0; i < defaultBoard.length; i++)
			for(int j = 0; j < defaultBoard[0].length; j++)
				currentBoard[i][j] = defaultBoard[i][j];
	}
	
	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {
		if(ComponentNavigation.currentlySelected != null) {
			int x = e.getX() - startingXPosition,
				y = e.getY() - startingYPosition;
			
			boolean misplaced = false;
			
			if(x >= 0 && x <= (endingXPosition - startingXPosition) && y >= 0 && y <= (endingYPosition - startingYPosition)) {
				int xSquare = x / Component.COMPONENT_SIZE;
				int ySquare = y / Component.COMPONENT_SIZE;
				
				if(board.match(ComponentNavigation.currentlySelected, xSquare, ySquare)) {
					navigation.incrementSuccessfullyPlaced();
					
					if(navigation.getSuccessfullyPlaced() == board.getNumberOfChanges()) {
						board.setMisplaced(navigation.getMisplaced());
						board.setComplete();
						
						try {
							PrintWriter writer = new PrintWriter("res/progress/" + board.getName().replaceAll(" ", "").toLowerCase() + "-progress.pgs", "UTF-8");
							writer.print(board.getMisplaced());
							writer.close();
						} catch (FileNotFoundException | UnsupportedEncodingException e1) {
							JOptionPane.showMessageDialog(null, "Unable to save board progress");
						}
						
						boardEditor.refresh();
						return;
					}
					
					currentBoard[ySquare][xSquare] = ComponentNavigation.currentlySelected;
					repaint();
				}
				else {
					misplaced = true;
				}
			}
			else {
				misplaced = true;
			}
			
			if(misplaced) {
				navigation.incrementMisplaced();		
			}
		}
		else JOptionPane.showMessageDialog(null, "You must select a component before you can place it on the board");
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
	
	private int startingXPosition = 0;
	private int endingXPosition = 0;
	
	private int startingYPosition = 0;
	private int endingYPosition = 0;

	private Board board = null;
	private ComponentIndex[][] currentBoard;
	private BoardEditor boardEditor = null;
		
	private ComponentNavigation navigation = null;
}
