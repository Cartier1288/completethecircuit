import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

//Class which graphically displays some of a board's information and prompts the user to attempt it
public class BoardButton extends JPanel {
	private static final long serialVersionUID = -62007869634871971L;

	public BoardButton(Board board, String imagePath, BoardList boardList) { //Constructor takes a Board, a path to an image, and the parent BoardList.
		this.setLayout(new GridBagLayout()); //Set the layout to a new GridBagLayout
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.board = board;
		
		CustomFont largeFont = new CustomFont("/fonts/bneuebold.ttf", Font.PLAIN, 32); //Instantiate a new CustomFont, with Bebas Neue Bold and size 32px.
		
		//Name of the board
		JLabel title = new JLabel(board.getName()); //new JLabel(board.getBoardName());
		title.setForeground(Color.black);
		title.setFont(largeFont.getFont());
		
		//Image for the board
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon(imagePath));
		
		//Check if the board has been completed
		if(board.getCompletion()) { //Modify the label to inform the user that the board has been completed
			completion.setText("Completed");
			completion.setForeground(new Color(39, 230, 127));
		}
		else { //Modify the label to inform the user that the board has not been completed
			completion.setText("Incomplete");
			completion.setForeground(new Color(255, 0, 0));
		}
		completion.setFont(new CustomFont("/fonts/bneueregular.ttf", Font.PLAIN, 26).getFont());
		
		//Number of recorded misplaced components for the board
		misplaced = new JLabel(board.getMisplaced() + " Misplaced");
		misplaced.setForeground(Color.black);
		
		//Button prompting the user to attempt to complete the board
		JButton attempt = new JButton("Attempt");
		//Add a new SwitchPanel as the attempt button's ActionListener, to switch from the board list to the board editor for the desired board.
		attempt.addActionListener(new SwitchPanel(CompleteTheCircuit.frame, new BoardEditor(board, this, boardList), "Complete the Circuit - " + board.getName()));
		attempt.setFont(largeFont.getFont());
		attempt.setForeground(Color.white);
		attempt.setBackground(new Color(46, 52, 68));
		attempt.setBorder(new LineBorder(new Color(32, 38, 48), 3));
		
		
		//Define the desired constraint values for the BoardButton
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		this.add(title, gbc);
		
		gbc.gridy++;
		this.add(image, gbc);
		
		gbc.gridy++;
		this.add(completion, gbc);
		
		gbc.gridy++;
		this.add(misplaced, gbc);
		
		gbc.gridy++;
		this.add(attempt, gbc);
	}
	
	//Method called to refresh the label containing the status of a board's completion as well as number of misplaced components
	public void refreshCompletion() {
		if(board.getCompletion()) {
			completion.setText("Completed");
			completion.setForeground(new Color(39, 230, 127));
			
			misplaced.setText(board.getMisplaced() + " Misplaced");
		}
		else {
			completion.setText("Incomplete");
			completion.setForeground(new Color(255, 0, 0));
		
			misplaced.setText(board.getMisplaced() + " Misplaced");
		}
	}
	
	Board board = null;
	JLabel misplaced = null;
	JLabel completion = new JLabel();
}
