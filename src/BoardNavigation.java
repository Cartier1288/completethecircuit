import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class BoardNavigation extends JPanel {
	private static final long serialVersionUID = 5066502431489298714L;

	public BoardNavigation() {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(200, 200, 200));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		MatteBorder scoreBorder = new MatteBorder(0, 0, 0, 2, Color.black);
		
		successfullyPlacedLabel.setBorder(scoreBorder);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.5;
		
		this.add(successfullyPlacedLabel, gbc);
		
		misplacedLabel.setBorder(scoreBorder);
		
		gbc.gridy = 1;
		
		this.add(misplacedLabel, gbc);
		
		gbc.gridy = 0;
		gbc.gridx = 1;
		gbc.weightx = 0.3;
		gbc.weighty = 0.2;
		
		JComboBox<String> componentTypeList = new JComboBox<String>(new String[] {"Resistors"});
		
		this.add(componentTypeList, gbc);
		
		gbc.gridy = 1;
		gbc.weightx = 0.9;
		gbc.weighty = 0.8;
		
		this.add(new ComponentButton(0));
	}
	
	public void incrementSuccessfullyPlaced() {
		successfullyPlaced++;
		successfullyPlacedLabel.setText("Successfully placed components: " + successfullyPlaced);
	}
	
	public void incrementMisplaced() {
		misplaced++;
		misplacedLabel.setText("Misplaced components: " + misplaced);
	}
	
	public int getMisplaced() {
		return misplaced;
	}
	
	private int successfullyPlaced = 0;
	private int misplaced = 0;

	private JLabel successfullyPlacedLabel = new JLabel("Successfully placed components: 0");
	private JLabel misplacedLabel = new JLabel("Misplaced components: 0");

	public static ComponentIndex currentlySelected;
}
