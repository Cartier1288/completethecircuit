import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

//Class which contains a number of related ComponentButtons
public class ComponentPage extends JPanel {
	private static final long serialVersionUID = 1437334919246607073L;

	public ComponentPage(String name, ComponentButton[] buttons) { //Constructor takes the name of the page, and an array of type ComponentButton.
		this.setLayout(new GridLayout(1, 1)); //Set the layout to a new GridLayout, with dimensions 1x1.
		
		this.name = name;
		
		//Panel, with a horizontal FlowLayout, which actually holds the ComponentButtons.
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		
		for(int i = 0; i < buttons.length; i++) //Iterate through the provided array, adding the elements to buttonsPanel.
			buttonsPanel.add(buttons[i]);
		
		JScrollPane scrollpane = new JScrollPane(buttonsPanel); //Instantiate a scroll pane with buttonsPanel, in case the window is resized, and the panel is larger than its parent.
		
		this.add(scrollpane); //Add the scroll-pane
	}
	
	//Function to get the name of the panel
	public String getName() {
		return name;
	}
	
	private String name = "";
}
