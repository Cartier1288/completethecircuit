import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class ComponentNavigation extends JPanel implements ItemListener {
	private static final long serialVersionUID = 5066502431489298714L;

	public ComponentNavigation() {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(200, 200, 200));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		MatteBorder scoreBorder = new MatteBorder(0, 0, 0, 2, Color.black);
		
		successfullyPlacedLabel.setBorder(scoreBorder);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.5;
		
		this.add(successfullyPlacedLabel, gbc);
		
		misplacedLabel.setBorder(scoreBorder);
		
		gbc.gridy = 1;
		
		this.add(misplacedLabel, gbc);
		
		gbc.gridy = 0;
		gbc.gridx = 1;
		gbc.weightx = 0.3;
		gbc.weighty = 0.2;
		
		pagesPanel.setLayout(pagesLayout);
		
		String[] pageNames = new String[componentPages.length];
		for(int i = 0; i < componentPages.length; i++) {
			pageNames[i] = componentPages[i].getName();
			pagesPanel.add(componentPages[i], pageNames[i]);
		}
		
		componentTypeList = new JComboBox<String>(pageNames);
		componentTypeList.addItemListener(this);
		
		this.add(componentTypeList, gbc);
		
		gbc.gridy = 1;
		gbc.weightx = 0.9;
		gbc.weighty = 0.8;
		
		this.add(pagesPanel, gbc);
	}
	
	public void incrementSuccessfullyPlaced() {
		successfullyPlaced++;
		successfullyPlacedLabel.setText("Successfully placed components: " + successfullyPlaced);
	}
	
	public void incrementMisplaced() {
		misplaced++;
		misplacedLabel.setText("Misplaced components: " + misplaced);
	}
	
	public int getMisplaced() {
		return misplaced;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			for(int i = 0; i < componentPages.length; i++)
				if(componentPages[i].getName() == (String)componentTypeList.getSelectedItem()) {
					pagesLayout.show(pagesPanel, (String)componentTypeList.getSelectedItem());
				}
		}
	}
	
	
	public static ComponentIndex currentlySelected;

	private ComponentPage[] componentPages = {
		new ComponentPage("Wires", new ComponentButton[] {
				
		}),
		new ComponentPage("Light-emitters", new ComponentButton[] {
				
		}),
		new ComponentPage("Resistors", new ComponentButton[] {
				new ComponentInputButton(0)
		}),
		new ComponentPage("Buttons and Switches", new ComponentButton[] {
				
		}),
		new ComponentPage("Batteries", new ComponentButton[] {
				
		}),
		new ComponentPage("Meters", new ComponentButton[] {
				
		})
	};
	
	private JPanel pagesPanel = new JPanel();
	private CardLayout pagesLayout = new CardLayout();
	
	JComboBox<String> componentTypeList = null;
	
	private int successfullyPlaced = 0;
	private int misplaced = 0;

	private JLabel successfullyPlacedLabel = new JLabel("Successfully placed components: 0");
	private JLabel misplacedLabel = new JLabel("Misplaced components: 0");
}
