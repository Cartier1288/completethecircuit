import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Legend extends JPanel {
	private static final long serialVersionUID = 6338423046230304703L;

	public Legend(Board board) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
	
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.2;
		gbc.insets = new Insets(10, 10, 10, 10);
		
		JLabel legendLabel = new JLabel("Legend:");
		legendLabel.setFont(new CustomFont("/fonts/bneueregular.ttf", Font.PLAIN, 32).getFont());
		
		this.add(legendLabel, gbc);
		
		
		
		gbc.gridy++;
		gbc.weighty = 0.8;
	}
}
