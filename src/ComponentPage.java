import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ExternalResources.WrapLayout;

public class ComponentPage extends JPanel {
	private static final long serialVersionUID = 1437334919246607073L;

	public ComponentPage(String name, ComponentButton[] buttons) {
		this.setLayout(new GridLayout(1, 1));
		
		this.name = name;
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new WrapLayout(WrapLayout.LEFT, 20, 10));
		
		for(int i = 0; i < buttons.length; i++)
			buttonsPanel.add(buttons[i]);
		
		JScrollPane scrollpane = new JScrollPane(buttonsPanel);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(scrollpane);
	}
	
	public String getName() {
		return name;
	}
	
	private String name = "";
}
