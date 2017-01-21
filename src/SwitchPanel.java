import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

//Class used to switch between panels
public class SwitchPanel implements ActionListener {
	public SwitchPanel(JFrame frame, JPanel panel) {
		//Set the data members to their respective reference
		this.frame = frame;
		this.panel = panel;
	}
	
	public SwitchPanel(JFrame frame, JPanel panel, String title) {
		//Set the data members to their respective reference
		this.frame = frame;
		this.panel = panel;
		
		this.frame.setTitle(title);
	}
	
	public void actionPerformed(ActionEvent e) { //In the case of the element which the class has been passed to as an ActionListener performing an action, switch to the desired panel.
		this.frame.setContentPane(panel); //In the case of the element which the class has been passed to as an ActionListener performing an action, switch to the desired panel.
		this.frame.revalidate(); //Re-validate the frame; repaints and updates the content.
	}
	
	private JFrame frame; //JFrame which will have its content pane changed
	private JPanel panel; //Panel to change to
}