import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class CustomFont {

	public CustomFont(String fontClassPath, int style, int size) {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(fontClassPath)).deriveFont(style, size);
		} catch (FontFormatException | IOException e) {
			//e.printStackTrace();
			font = new Font("Garamond", style, size);
		}
	}
	
	public Font getFont() {
		return font;
	}
	
	Font font;
}
