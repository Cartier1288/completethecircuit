import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

//Class which displays the board graphically, and handles the game mechanics.
public class BoardContent extends JPanel implements MouseListener {
	private static final long serialVersionUID = -5438052798440144942L;

	public BoardContent(Board board, ComponentNavigation navigation, BoardEditor boardEditor) { //Constructor takes a board, the editor's navigation, and the editor.
		this.addMouseListener(this); //Set this object as its own MouseListener
		
		this.board = board;
		this.navigation = navigation;
		this.boardEditor = boardEditor;
		
		ComponentIndex[][] defaultBoard = board.getDefaultBoardComponents();
		
		//Fill the currentBoard--the one which is displayed--with the default components.
		currentBoard = new ComponentIndex[defaultBoard.length][defaultBoard[0].length];
		
		for(int i = 0; i < defaultBoard.length; i++)
			for(int j = 0; j < defaultBoard[0].length; j++)
				currentBoard[i][j] = defaultBoard[i][j];
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); //Call the paintComponent method from the superclass

		updateBoardPosition(); //Update the board position
		
		//Get the width and height
		int width = this.getWidth();
		int height = this.getHeight();

		//Draw the grid
		for (int i = 0; i < width; i += Component.COMPONENT_SIZE)
			g.drawLine(i, 0, i, height);

		for (int i = 0; i < height; i += Component.COMPONENT_SIZE)
			g.drawLine(0, i, width, i);
		
		
		//Draw the components, and, if they have one, their count.
		for(int i = 0; i < currentBoard[0].length; i++)
			for(int j = 0; j < currentBoard.length; j++) {
				ComponentIndex currentIndex = currentBoard[j][i]; //Reference the component at the board's current index
				if(currentIndex.index != -1) { //Ensure the component is not empty space
					//Calculate the current x and y positions of the components, relative to the board position.
					int x = (i * Component.COMPONENT_SIZE) + startingXPosition;
					int y = (j * Component.COMPONENT_SIZE) + startingYPosition;
					//Draw the component
					g.drawImage(Component.components[currentIndex.index].getSymbol(), x, y, null);
					if(currentIndex.count != -1) //Check if the component has a count, and, if so, draw it relative to the component's position.
						g.drawString(Component.components[currentIndex.index].getCharacter() + "" + currentIndex.count, x + 2, y + 12);
				}
			}
	}
	
	//Method which calculates the current position of the board, in the panel (centered).
	private void updateBoardPosition() {
		//Calculate half of the panel's width and height, in terms of component size.
		float width = ((float)(this.getWidth() / 2.0f) / Component.COMPONENT_SIZE);
		float height = ((float)(this.getHeight() / 2.0f) / Component.COMPONENT_SIZE);
		
		/*
		 * Calculate the position where the board starts and ends, 
		 * by dividing the dimensions of the boards (relative to 
		 * their component size) by two, finding the difference, 
		 * and multiplying by the size of the components, to return 
		 * to pixel dimensions.
		 */
		startingXPosition = (int)Math.floor(width - (float)(currentBoard[0].length / 2.0)) * Component.COMPONENT_SIZE;
		endingXPosition = startingXPosition + (currentBoard[0].length * Component.COMPONENT_SIZE);
		
		startingYPosition = (int)Math.floor(height - (float)(currentBoard.length / 2.0)) * Component.COMPONENT_SIZE;
		endingYPosition = startingYPosition + (currentBoard.length * Component.COMPONENT_SIZE);
	}

	//Method to reset the current board, to its default state.
	public void reset() {
		ComponentIndex[][] defaultBoard = board.getDefaultBoardComponents();
		
		for(int i = 0; i < defaultBoard.length; i++)
			for(int j = 0; j < defaultBoard[0].length; j++)
				currentBoard[i][j] = defaultBoard[i][j];
	}
	
	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	//Calculate where the mouse was clicked, relative to the grid, and test whether the component was correctly placed.
	public void mouseReleased(MouseEvent e) {
		if(ComponentNavigation.currentlySelected != null) { //Ensure the user has chosen a component
			int x = e.getX() - startingXPosition,
				y = e.getY() - startingYPosition;
			
			boolean misplaced = false; //Flag used to determine if the component was misplaced, and, in turn, increment the number of misplaced components.
			
			//Check if the mouse was released inside the board
			if(x >= 0 && x <= (endingXPosition - startingXPosition) && y >= 0 && y <= (endingYPosition - startingYPosition)) {
				//Calculate the coordinates of the click, relative to the grid.
				int xSquare = x / Component.COMPONENT_SIZE;
				int ySquare = y / Component.COMPONENT_SIZE;
				
				//Check if the currently selected component matches the correct component
				if(board.match(ComponentNavigation.currentlySelected, xSquare, ySquare)) {
					navigation.incrementSuccessfullyPlaced();
					
					//Check if the user has completed the board
					if(navigation.getSuccessfullyPlaced() == board.getNumberOfChanges()) {
						//Pass the board the gathered information
						board.setMisplaced(navigation.getMisplaced());
						board.setComplete();
						
						//Write a progress file for the board
						try {
							PrintWriter writer = new PrintWriter("res/progress/" + board.getName().replaceAll(" ", "").toLowerCase() + "-progress.pgs", "UTF-8");
							writer.print(board.getMisplaced());
							writer.close();
						} catch (FileNotFoundException | UnsupportedEncodingException e1) {
							JOptionPane.showMessageDialog(null, "Unable to save board progress");
						}
						
						//Refresh the parent board editor
						boardEditor.refresh();
						return;
					}
					
					//Update the currently placed components board and repaint the panel
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
				if((navigation.getMisplaced() % 3) == 0) { //Check if the number of misplaced components is a multiple of three; if so, reset the current board and repaint the panel.
					reset();
					navigation.resetSuccessfullyPlaced();
					this.repaint();
				}
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

	private ComponentIndex[][] currentBoard; //Two-dimensional ComponentIndex array containing the current components for the displayed board
	
	private Board board = null;
	private BoardEditor boardEditor = null;
	private ComponentNavigation navigation = null;
}
