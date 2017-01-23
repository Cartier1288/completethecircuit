import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BoardList extends JPanel {
	private static final long serialVersionUID = 1502525386916984962L;

	public BoardList(JFrame parent, JPanel mainMenu) {
		this.setLayout(new GridBagLayout()); //Set the layout of the panel to a new GridBagLayout
		this.setBackground(new Color(30, 30, 50)); //Set the background to a dark blue
		
		GridBagConstraints gbc = new GridBagConstraints(); //Create a new GridBagConstraints object
		
		gbc.fill = GridBagConstraints.BOTH; //Set the fill of the element to both horizontal and vertical
		gbc.ipadx = 0; //Set the padding to 0
		gbc.ipady = 0;
		gbc.gridwidth = 1; //Set the grid width to 1
		gbc.weightx = 0.05; //Set the x-weight of the element to 0.05 (only miniscule to allow for a minimum width).
		gbc.weighty = 0.01; //Set the y-weight of the element to 0.01 (only miniscule to allow for a minimum height).
		gbc.gridx = 0; //Set the location of the element to (0, 0).
		gbc.gridy = 0;
		gbc.insets = new Insets(30, 30, 30, 30); //Set the insets to 30px
		gbc.anchor = GridBagConstraints.FIRST_LINE_START; //Set the anchor to the top of the document
		
		JButton backToLessons = new JButton("Back to Main Menu"); //Instantiate the button which will take the user back to the list of lessons
		backToLessons.addActionListener(new SwitchPanel(parent, mainMenu, "Compete the Circuit - Main Menu"));
		backToLessons.setFont(new CustomFont("/fonts/bneuebold.ttf", Font.PLAIN, 30).getFont());
		backToLessons.setBackground(new Color(86, 110, 122));
		backToLessons.setForeground(Color.white);
		backToLessons.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		this.add(backToLessons, gbc); //Add the backToLessons button to the panel, using the current gridbag constraints.
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2, 1));
		infoPanel.setOpaque(false);
		
		JLabel titleLabel = new JLabel("Board List");
		titleLabel.setFont(new CustomFont("/fonts/bneuebold.ttf", Font.PLAIN, 40).getFont());
		titleLabel.setForeground(Color.white);
		infoPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
		
		JLabel titleInfoLabel = new JLabel("Choose a board and attempt to complete it without misplacing any components");
		titleInfoLabel.setForeground(Color.white);
		
		infoPanel.add(titleLabel);
		infoPanel.add(titleInfoLabel);
		
		gbc.gridx = 1; //Set the location of the element to (1, 0).
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.weightx = 0.95; //Set the x-weight of the element to the majority of the width
		gbc.insets = new Insets(0, 0, 0, 0); //Remove the insets, by setting them to 0.
		
		this.add(infoPanel, gbc); //Add the title to the panel, using the current gridbag constraints.
		
		//Instantiate the panel which will hold the lesson content, as well as its layout.
		JPanel contentPanel = new JPanel();
		CardLayout contentLayout = new CardLayout();
		
		contentPanel.setLayout(contentLayout); //Set the layout of the contentPanel to contentLayout, a CardLayout which provides functionality for looping through the panels added.
		contentPanel.add(new BoardPage(new BoardButton(new Board(""), "res/images/bulb.png", this), new BoardButton(new Board(""), "res/images/bulb.png", this), new BoardButton(new Board(""), "res/images/bulb.png", this)));
//		for(int i = 0; i < content.length; i++) { //Loop through the content array passed to the constructor
//			contentPanel.add(content[i]); //Add the elements to the contentPanel
//		}
		
		gbc.gridwidth = 2; //Set the grid width to 2, to allow the content panel to fill the entire horizontal width.
		gbc.gridx = 0; //Set the location of the element to (0, 1).
		gbc.gridy = 1;
		gbc.weightx = 1; //Set the x-weight of the element to 1, so that it stretches fully across the horizontal width.
		gbc.weighty = 0.7; //Set the y-weight of the element to 0.7, to take up 70% of the vertical space of the panel.
		
		this.add(contentPanel, gbc); //Add contentPanel to the panel, using the current gridbag constraints.
		
		Navigation navigation = new Navigation(contentPanel, contentLayout, 4); //Create a new Navigation object, passing the contentPanel and its layout (to shift through the elements), as well as the length of the content.
		
		gbc.weighty = 0.05;
		gbc.gridy = 3;
		
		this.add(navigation, gbc); //Add the navigation to the panel, using the current gridbag constraints.
	}
}
