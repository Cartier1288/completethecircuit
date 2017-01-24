import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ComponentInputButton extends ComponentButton {
	private static final long serialVersionUID = 7150847273213932542L;

	public ComponentInputButton(int componentIndex) {
			super(componentIndex);
			
			field.setText("-1");
			
			this.add(field);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		String value = this.field.getText();
		if(isInteger(value)) {
			this.component.value = Integer.parseInt(value);
			super.mouseReleased(e);
		}
		else {
			JOptionPane.showMessageDialog(null, "Unable to process component value. Make sure the value you entered into the component button is a valid integer (no decimals).");
			this.setBackground(Color.white);
		}
	}
	
	private boolean isInteger(String str) {
		return isInteger(str, 10);
	}
	
	private boolean isInteger(String str, int radix) {
		if(str.isEmpty()) return false;
		
		if(str.charAt(0) == '-') {
			if(str.length() == 1) return false;
		}
		else {
			if(Character.digit(str.charAt(0), radix) < 0) return false;
		}
		for(int i = 1; i < str.length(); i++)
			if(Character.digit(str.charAt(i), radix) < 0) return false;
		
		return true;
	}
	
	private JTextField field = new JTextField();
}
