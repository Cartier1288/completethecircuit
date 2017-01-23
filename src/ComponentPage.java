import javax.swing.JPanel;

import ExternalResources.WrapLayout;

public class ComponentPage extends JPanel {
	private static final long serialVersionUID = 1437334919246607073L;

	public ComponentPage(BoardButton[] buttons) {
		this.setLayout(new WrapLayout(WrapLayout.LEFT, 20, 10));
		
		for(int i = 0; i < buttons.length; i++)
			this.add(buttons[i]);
	}
}
