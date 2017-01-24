import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Component {
	public static Component[] components = null;
	
	public static void init() {
		try {
			BufferedImage sheet = ImageIO.read(new File("res/symbolsheet.png"));

			components = new Component[] {
				new Component("Wire LR", sheet, 0, 0),
				new Component("Wire TB", sheet, 50, 0),
				new Component("Wire TL", sheet, 100, 0),
				new Component("Wire TR", sheet, 150, 0),
				new Component("Wire BL", sheet, 0, 50),
				new Component("Wire BR", sheet, 50, 50),
				new Component("Bulb", 'b', sheet, 100, 50),
				new Component("LED", 'D', sheet, 150, 50),
				new Component("Resistor", 'R', sheet, 0, 100),
				new Component("Push Button", 'S', sheet, 50, 100),
				new Component("Switch ", 'S', sheet, 100, 100),
				new Component("Battery", 'B', sheet, 150, 100),
				new Component("Voltmeter", 'V', sheet, 0, 150),
				new Component("Ammeter", 'A', sheet, 50, 150),
				new Component("Ohmeter", 'O', sheet, 100, 150)
			}; //Variability can potentially be added by creating a component with the same symbol, and sheet coordinates, with a different name.
		} catch(IOException e) {
			System.out.println("Unable to load symbol sheet.");
		}
	}
	
	public Component(String componentName, char componentCharacter, BufferedImage symbolSheet, int x, int y) {
		this.componentName = componentName;
		this.componentCharacter = componentCharacter;
		
		symbol = symbolSheet.getSubimage(x, y, ComponentSize, ComponentSize);
	}
	
	public Component(String componentName, BufferedImage symbolSheet, int x, int y) {
		this.componentName = componentName;
		
		symbol = symbolSheet.getSubimage(x, y, ComponentSize, ComponentSize);
	}

	public String getName() {
		return componentName;
	}
	
	public char getCharacter() {
		return componentCharacter;
	}
	
	public BufferedImage getSymbol() {
		return symbol;
	}
	
	
	public static final int ComponentSize = 50;
	
	private String componentName = "";
	private char componentCharacter = ' ';
	private BufferedImage symbol = null;
}
