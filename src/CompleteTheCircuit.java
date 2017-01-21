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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CompleteTheCircuit {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Complete the Circuit - Main Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null);
		
		JPanel mainMenu = new JPanel();
		mainMenu.setLayout(new GridLayout(2, 1));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(1, 1));
		
		JLabel title = new JLabel("Complete the Circuit", JLabel.CENTER);
		title.setFont(new CustomFont("/fonts/adamcg.ttf", Font.BOLD, 80).getFont());
		
		titlePanel.add(title);
		
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 1, 0, 50));
		buttonsPanel.setBorder(new EmptyBorder(0, 300, 100, 300));
		
		CustomFont buttonFont = new CustomFont("/fonts/bneueregular.ttf", Font.BOLD, 42);
		Color buttonColor = new Color(46, 52, 68);
		LineBorder buttonBorder = new LineBorder(new Color(32, 38, 48), 3);
		
		JButton boardListButton = new JButton("Board List");
		boardListButton.addActionListener(new SwitchPanel(frame, new BoardList(frame, mainMenu), "Complete the Circuit - Board List"));
		boardListButton.setFont(buttonFont.getFont());
		boardListButton.setBackground(buttonColor);
		boardListButton.setForeground(Color.white);
		boardListButton.setBorder(buttonBorder);
		
		JButton helpDocumentationButton = new JButton("Help and Documentation");
		helpDocumentationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new File("res/documentation/index.html").toURI());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		helpDocumentationButton.setFont(buttonFont.getFont());
		helpDocumentationButton.setBackground(buttonColor);
		helpDocumentationButton.setForeground(Color.white);
		helpDocumentationButton.setBorder(buttonBorder);
		
		buttonsPanel.add(boardListButton);
		buttonsPanel.add(helpDocumentationButton);
		
		mainMenu.add(titlePanel);
		mainMenu.add(buttonsPanel);
		
		frame.setContentPane(mainMenu);
		frame.setVisible(true);
	}
}
