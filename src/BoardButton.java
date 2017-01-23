import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BoardButton extends JPanel {
	private static final long serialVersionUID = -62007869634871971L;

	public BoardButton(Board board, String imagePath, BoardList boardList) {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		CustomFont largeFont = new CustomFont("/fonts/bneuebold.ttf", Font.PLAIN, 32);
		
		JLabel title = new JLabel(board.getName()); //new JLabel(board.getBoardName());
		title.setForeground(Color.black);
		title.setFont(largeFont.getFont());
		
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon(imagePath));
		
		if(board.getCompletion()) {
			completion.setText("Completed");
			completion.setForeground(new Color(39, 230, 127));
		}
		else {
			completion.setText("Incomplete");
			completion.setForeground(new Color(255, 0, 0));
		}
		completion.setFont(new CustomFont("/fonts/bneueregular.ttf", Font.PLAIN, 22).getFont());
		
		JLabel misplaced = new JLabel(board.getMisplaced() + " Misplaced");
		misplaced.setForeground(Color.black);
		
		JButton attempt = new JButton("Attempt");
		attempt.addActionListener(new SwitchPanel(CompleteTheCircuit.frame, new BoardEditor(board, this, boardList), "Complete the Circuit - " + board.getName()));
		attempt.setFont(largeFont.getFont());
		attempt.setForeground(Color.white);
		attempt.setBackground(new Color(46, 52, 68));
		attempt.setBorder(new LineBorder(new Color(32, 38, 48), 3));
		
		
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
	
	public void refreshCompletion() {
		if(board.getCompletion()) {
			completion.setText("Completed");
			completion.setForeground(new Color(39, 230, 127));
		}
		else {
			completion.setText("Incomplete");
			completion.setForeground(new Color(255, 0, 0));
		}
		completion.setFont(new CustomFont("/fonts/bneueregular.ttf", Font.PLAIN, 22).getFont());
	}
	
	Board board = null;
	JLabel completion = new JLabel();
}
