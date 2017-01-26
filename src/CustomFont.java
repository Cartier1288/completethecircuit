import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

//Class which creates a font from a provided path to a font file
public class CustomFont {
	public CustomFont(String fontClassPath, int style, int size) { //Constructor takes the path to the font file, the style of the font, and its size.
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(fontClassPath)).deriveFont(style, size); //Create a font using the provided information
		} catch (FontFormatException | IOException e) {
			//e.printStackTrace();
			font = new Font("Garamond", style, size); //If unable to load the font, create a new font with class Garamond, using the information provided.
		}
	}
	
	//Function to get the created font
	public Font getFont() {
		return font;
	}
	
	Font font = null;
}
