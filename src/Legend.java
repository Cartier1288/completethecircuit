import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//Class which displays the information for a board's components
public class Legend extends JPanel {
	private static final long serialVersionUID = 6338423046230304703L;

	public Legend(Board board) { //Constructor which takes a Board as its parameter
		this.setBackground(Color.darkGray);
		
		this.setLayout(new GridBagLayout()); //Set the layout a new GridBagLayout
		GridBagConstraints gbc = new GridBagConstraints();
	
		//Define the constraints
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.05;
		gbc.insets = new Insets(10, 10, 10, 10);
		
		//Initialize the legend's label
		JLabel legendLabel = new JLabel("Legend:");
		legendLabel.setFont(new CustomFont("/fonts/bneuebold.ttf", Font.PLAIN, 32).getFont());
		legendLabel.setForeground(Color.white);
		
		this.add(legendLabel, gbc);
		
		//Initialize the legend's panel which contains the component information
		JPanel components = new JPanel();
		components.setLayout(new BoxLayout(components, BoxLayout.Y_AXIS)); //Set the components' layout to a new vertical BoxLayout
		components.setBackground(Color.darkGray);
		
		//Get the default components of the provided board
		ComponentIndex[][] defaultComponents = board.getDefaultBoardComponents();
		Font legendFont = new CustomFont("Helvetica", Font.PLAIN, 20).getFont();
		
		//Loop through the components, checking if they have a count, and, if so, create a new JLabel with their information and add them to the components JPanel.
		for(int j = 0; j < defaultComponents[0].length; j++)
			for(int i = 0; i < defaultComponents.length; i++)
				if(defaultComponents[i][j].count != -1) {
					String componentText = Component.components[defaultComponents[i][j].index].getCharacter() + "" + defaultComponents[i][j].count + ": " + Component.components[defaultComponents[i][j].index].getName();
					if(defaultComponents[i][j].value != -1) 
						componentText += " (" + defaultComponents[i][j].value + ")";
					
					JLabel componentLabel = new JLabel(componentText);
					componentLabel.setFont(legendFont);
					componentLabel.setForeground(Color.white);
					
					components.add(componentLabel);
				}
		
		gbc.gridy = 1;
		gbc.weighty = 0.95;
		
		//Add the components JPanel to a new ScrollPane, in case the contained information overflows its dimensions.
		JScrollPane scrollpane = new JScrollPane(components);
		
		this.add(scrollpane, gbc);
	}
}
