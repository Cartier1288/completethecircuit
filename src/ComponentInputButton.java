import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

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
		if(isDouble(value)) {
			this.component.value = Double.parseDouble(value);
			super.mouseReleased(e);
		}
		else {
			JOptionPane.showMessageDialog(null, "Unable to process component value. Make sure the value you entered into the component button is a valid number (decimals are allowed).");
			this.setBackground(Color.white);
		}
	}
	
	//Retrieved from http://hg.openjdk.java.net/jdk9/jdk9/jdk/file/4a00f31b3995/src/java.base/share/classes/java/lang/Double.java#l444
	private boolean isDouble(String str) {
		final String Digits     = "(\\p{Digit}+)";
		final String HexDigits  = "(\\p{XDigit}+)";
		
		// an exponent is 'e' or 'E' followed by an optionally
		// signed decimal integer.
		
		final String Exp        = "[eE][+-]?"+Digits;
		final String fpRegex    =
		("[\\x00-\\x20]*"+  // Optional leading "whitespace"
		 "[+-]?(" + // Optional sign character
		 "NaN|" +           // "NaN" string
		 "Infinity|" +      // "Infinity" string
		
		 // A decimal floating-point string representing a finite positive
		 // number without a leading sign has at most five basic pieces:
		 // Digits . Digits ExponentPart FloatTypeSuffix
		
		 // Since this method allows integer-only strings as input
		 // in addition to strings of floating-point literals, the
		 // two sub-patterns below are simplifications of the grammar
		 // productions from section 3.10.2 of
		 // The Java Language Specification.
		 
		 // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
		
		 "((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|"+
		
		 // . Digits ExponentPart_opt FloatTypeSuffix_opt
		
		 "(\\.("+Digits+")("+Exp+")?)|"+
		
		 // Hexadecimal strings
		
		 "((" +
		
		 // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
		
		 "(0[xX]" + HexDigits + "(\\.)?)|" +
		
		 // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
		
		 "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +
		
		 ")[pP][+-]?" + Digits + "))" +
		 "[fFdD]?))" +
		 "[\\x00-\\x20]*");// Optional trailing "whitespace"
		
		 
		if (Pattern.matches(fpRegex, str))
			return true;
		return false;
	}

	private JTextField field = new JTextField();
}
