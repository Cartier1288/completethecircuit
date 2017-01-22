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
				new Component("Resistorx1", 'R', sheet, 1, 1)
			}; //Variability can potentially be added by passing the same sheet coordinates, and a different name
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
	
	public String getComponentName() {
		return componentName;
	}
	
	public char getComponentCharacter() {
		return componentCharacter;
	}
	
	public BufferedImage getSymbol() {
		return symbol;
	}
	
	public final int ComponentSize = 75;
	
	private String componentName = "";
	private char componentCharacter = ' ';
	private BufferedImage symbol = null;
}
