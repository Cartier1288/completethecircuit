import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Class which defines the characteristics of a component, and carries a static array of predefined components.
public class Component {
	//Static array of type Component
	public static Component[] components = null;
	
	//Static method to initialize the predefined components
	public static void init() {
		try {
			BufferedImage sheet = ImageIO.read(new File("res/symbolsheet.png")); //Load a sheet containing the symbols' images into a BufferedImage

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
				new Component("Ohmmeter", 'O', sheet, 100, 150),
				new Component("Wire UDR", sheet, 150, 150),
				new Component("Wire UDL", sheet, 0, 200)
			};
		} catch(IOException e) {
			System.out.println("Unable to load symbol sheet.");
		}
	}
	
	public Component(String componentName, char componentCharacter, BufferedImage symbolSheet, int x, int y) { //Constructor taking the component's name, character, symbol-sheet, and sheet coordinates.
		this.componentName = componentName;
		this.componentCharacter = componentCharacter;
		
		symbol = symbolSheet.getSubimage(x, y, COMPONENT_SIZE, COMPONENT_SIZE); //Extract the component's symbol from the provided symbol-sheet
	}
	
	public Component(String componentName, BufferedImage symbolSheet, int x, int y) { //Constructor which takes the component's name, symbol-sheet, and sheet coordinates.
		this.componentName = componentName;
		
		symbol = symbolSheet.getSubimage(x, y, COMPONENT_SIZE, COMPONENT_SIZE); //Extract the component's symbol from the provided symbol-sheet
	}

	//Function to get the component's name
	public String getName() {
		return componentName;
	}
	
	//Function to get the component's character
	public char getCharacter() {
		return componentCharacter;
	}
	
	//Function to get the component's symbol
	public BufferedImage getSymbol() {
		return symbol;
	}
	
	
	//Constant containing the size of the components
	public static final int COMPONENT_SIZE = 50;
	
	private String componentName = "";
	private char componentCharacter = ' ';
	private BufferedImage symbol = null;
}
