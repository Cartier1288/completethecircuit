import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

//Class acting as a button for a component, inside a component navigation.
public class ComponentButton extends JPanel implements MouseListener {
	private static final long serialVersionUID = -4216380991781019395L;

	public ComponentButton(int componentIndex) { //Constructor takes the desired component's index
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //Set the layout of the panel to a vertical BoxLayout
		this.addMouseListener(this); //Add this object as its own MouseListener
		
		component = new ComponentIndex(componentIndex); //Create the respective ComponentIndex
		
		JLabel name = new JLabel(Component.components[componentIndex].getName(), JLabel.CENTER);
		name.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT); //Center the element
		
		JLabel icon = new JLabel(new ImageIcon(Component.components[componentIndex].getSymbol()), JLabel.CENTER);
		icon.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT); //Center the element
		
		this.setBackground(Color.white);
		this.setBorder(new LineBorder(Color.darkGray, 1));
		
		this.add(name);
		this.add(icon);
	}

	public void mouseClicked(MouseEvent e) {}

	//When the panel is clicked, change its background to a dark gray.
	public void mousePressed(MouseEvent e) {
		this.setBackground(Color.lightGray);
	}

	//When the panel encounters a mouse-release, set the currently selected component (stored inside ComponentNavigation), to the panel's component, and reset the background color.
	public void mouseReleased(MouseEvent e) {
		ComponentNavigation.currentlySelected = component;
		this.setBackground(Color.white);
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
	
	protected ComponentIndex component = null; //ComponentIndex for the panel's component
}
