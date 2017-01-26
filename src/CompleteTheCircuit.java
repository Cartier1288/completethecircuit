/*
 * Isaac McQuaide, ICS 3U, Culminating Task - Complete the Circuit, Educational game designed to provide practice for circuit-building and electricity.
 */

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CompleteTheCircuit {
	public static JFrame frame = new JFrame("Complete the Circuit - Main Menu");
	
	public static void main(String[] args) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set the default close operation for the frame to JFrame.EXIT_ON_CLOSE, to terminate the program, when the frame is closed.
		frame.setSize(1280, 720); //Set the frame's dimensions to 720p
		frame.setLocationRelativeTo(null); //Center the frame
		
		Component.init(); //Load the default components
		
		JPanel mainMenu = new JPanel(); //Instantiate the panel for the mainMenu
		mainMenu.setLayout(new GridLayout(2, 1)); //Set the layout for the mainMenu to a new GridLayout with dimensions 1x2
		
		BoardList boardList = new BoardList(frame, mainMenu); //Instantiate a new BoardList, to hold the BoardButtons
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(1, 1));
		
		JLabel title = new JLabel("Complete the Circuit", JLabel.CENTER);
		title.setFont(new CustomFont("/fonts/adamcg.ttf", Font.BOLD, 80).getFont()); //Set the title font to a new CustomFont, with Adam.CG PRO, and size 80px.
		
		titlePanel.add(title); //Add the title to the panel
		
		
		JPanel buttonsPanel = new JPanel(); //Create a panel to hold the main menu button 
		buttonsPanel.setLayout(new GridLayout(2, 1, 0, 50)); //Set the GridLayout to dimensions 1x2 with a vertical gap of 50px
		buttonsPanel.setBorder(new EmptyBorder(0, 300, 100, 300)); //Space the panel from the title, and the bottom of the frame.
		
		CustomFont buttonFont = new CustomFont("/fonts/bneuebold.ttf", Font.PLAIN, 42); //Create a new CustomFont for the buttons, with Bebas Neue Bold, and size 42px.
		Color buttonColor = new Color(46, 52, 68);
		LineBorder buttonBorder = new LineBorder(new Color(32, 38, 48), 3);
		
		JButton boardListButton = new JButton("Board List"); //Create the button to open the BoardList
		boardListButton.addActionListener(new SwitchPanel(frame, boardList, "Complete the Circuit - Board List")); //Add a new SwitchPanel as the button's ActionListener, to switch from the main menu to the BoardList
		boardListButton.setFont(buttonFont.getFont());
		boardListButton.setBackground(buttonColor);
		boardListButton.setForeground(Color.white);
		boardListButton.setBorder(buttonBorder);
		
		JButton helpDocumentationButton = new JButton("Help and Documentation"); //Create a button to open the program documentation
		helpDocumentationButton.addActionListener(new ActionListener() { //Add an ActionListener to the button
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new File("res/documentation/index.html").toURI()); //Use the Desktop functionality to open a browser with the documentation
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Unable to open documentation");
				}
			}
		});
		helpDocumentationButton.setFont(buttonFont.getFont());
		helpDocumentationButton.setBackground(buttonColor);
		helpDocumentationButton.setForeground(Color.white);
		helpDocumentationButton.setBorder(buttonBorder);
		
		//Add the buttons to the buttonsPanel
		buttonsPanel.add(boardListButton);
		buttonsPanel.add(helpDocumentationButton);
		
		//Add the title and buttons to the main menu
		mainMenu.add(titlePanel);
		mainMenu.add(buttonsPanel);
		
		frame.setContentPane(mainMenu); //Set the content-pane of the frame to the main menu
		frame.setVisible(true); //Make the frame visible to the user
	}
}
