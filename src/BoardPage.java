import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class BoardPage extends JPanel {
	private static final long serialVersionUID = -184755243956017824L;

	public BoardPage(BoardButton board1, BoardButton board2, BoardButton board3) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.333;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 100;
		
		this.add(board1, gbc);
		
		gbc.gridx++;
		this.add(board2, gbc);
		
		gbc.gridx++;
		this.add(board3, gbc);
	}
}
