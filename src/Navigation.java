import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Navigation extends JPanel implements ActionListener {
	private static final long serialVersionUID = -5772970337227689429L;

	public Navigation(JPanel content, CardLayout contentManager, int numPages) {
		this.content = content;
		this.contentManager = contentManager;
		
		this.numPages = numPages;
		pageNumber.setText("Page 1 of " + numPages); //Set the pageNumber label to the proper index
		pageNumber.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		this.setBackground(Color.lightGray); //Set the background color of the navigation to white
		
		CustomFont buttonFont = new CustomFont("/fonts/bneueregular.ttf", Font.PLAIN, 40);
		
		previous.setEnabled(false); //Disable the previous button (as the index is the first page).
		previous.addActionListener(this); //Add this instance as the action listener
		previous.setBackground(new Color(54, 54, 54));
		previous.setForeground(Color.white);
		previous.setFont(buttonFont.getFont());
		
		next.addActionListener(this);
		next.setBackground(new Color(54, 54, 54));
		next.setFont(buttonFont.getFont());
		
		if(numPages <= 1) {
			next.setEnabled(false); //If there is only page, disable the next button. 
			next.setForeground(new Color(95, 95, 95));
		}
		else next.setForeground(Color.white);
		
		this.setLayout(new GridBagLayout()); //Add a new GridBagLayout to the panel
		GridBagConstraints gbc = new GridBagConstraints(); //Initialize new GridBagConstraints
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.2;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		
		this.add(previous, gbc); //Add the previous button the panel, using the gridbag constraints.
		
		gbc.weightx = 0.6;
		gbc.gridx = 1;
		
		this.add(pageNumber, gbc); //Add the number label to the panel, using the gridbag constraints.
		
		gbc.weightx = 0.2;
		gbc.gridx = 2;
		
		this.add(next, gbc); //Add the next button to the panel, using the gridbag constraints.
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Previous")) { //Check if the previous button was pressed, judging by the action command passed to the ActionEvent.
			if(page > 1) { //Ensure the page is greater than the first; otherwise, the page will be set to 0, which does not exist.
				contentManager.previous(content); //Go to the previous panel, by calling the previous method of the CardLayout passed.
				if(--page == 1) { //Decrement the page, and check to see if the page is 1, and, if so, disable the previous button.
					previous.setEnabled(false);
				}
				next.setEnabled(true); //Ensure the next button is enabled
				
				pageNumber.setText("Page " + page + " of " + numPages); //Update the pageNumber label
			}
		}
		else { //If the previous button was not pressed, than the next button must have been.
			if(page < numPages) { //Ensure the page is lesser than the last; otherwise, the page will be set to a page greater than the last, which does not exist.
				contentManager.next(content); //Go to the next panel, by calling the next method of the CardLayout passed.
				if(++page == numPages) { //Increment the page, and check to see if the page is the last, and, if so, disable the next button.
					next.setEnabled(false);
				}
				previous.setEnabled(true); //Ensure the previous button is enabled
				
				pageNumber.setText("Page " + page + " of " + numPages); //Update the pageNumber label
			}
		}
	}
	
	//Variables which will contain references to the desired content to be changed, and its associated layout manager.
	private JPanel content;
	private CardLayout contentManager;
	
	private int page = 1; //Set the default page to the first
	private int numPages = 0; //Variables which will contain the number of pages
	
	//Instantiate the buttons for the navigation, and the label containing the page index.
	private JButton previous = new JButton("Previous");
	private JLabel pageNumber = new JLabel("Page 1", JLabel.CENTER);
	private JButton next = new JButton("Next");
}
