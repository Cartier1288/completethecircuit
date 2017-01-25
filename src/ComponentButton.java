import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ComponentButton extends JPanel implements MouseListener {
	private static final long serialVersionUID = -4216380991781019395L;

	public ComponentButton(int componentIndex) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.addMouseListener(this);
		
		component = new ComponentIndex(componentIndex);
		
		JLabel name = new JLabel(Component.components[componentIndex].getName(), JLabel.CENTER);
		name.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		
		JLabel icon = new JLabel(new ImageIcon(Component.components[componentIndex].getSymbol()), JLabel.CENTER);
		icon.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		
		this.setBackground(Color.white);
		this.setBorder(new LineBorder(Color.darkGray, 1));
		
		this.add(name);
		this.add(icon);
	}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		this.setBackground(Color.lightGray);
	}

	public void mouseReleased(MouseEvent e) {
		ComponentNavigation.currentlySelected = component;
		this.setBackground(Color.white);
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
	
	protected ComponentIndex component = null;
}
