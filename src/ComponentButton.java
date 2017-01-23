import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ComponentButton extends JButton implements ActionListener {
	private static final long serialVersionUID = -4216380991781019395L;

	public ComponentButton(int componentIndex) {
		super();
		component = new ComponentIndex(componentIndex);
		
		this.addActionListener(this);
		
		this.setText(Component.components[componentIndex].getName());
		this.setIcon(new ImageIcon(Component.components[componentIndex].getSymbol()));
		
		this.setBackground(Color.white);
		this.setBorder(new LineBorder(Color.darkGray, 1));
	}

	public void actionPerformed(ActionEvent e) {
		BoardNavigation.currentlySelected = component;
	}
	
	private ComponentIndex component = null;
}
