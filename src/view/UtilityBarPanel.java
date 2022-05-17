package view;

import javax.swing.JButton;
import javax.swing.JPanel;

import res.R;

public class UtilityBarPanel extends JPanel {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

	public UtilityBarPanel() {
		super();
		this.add(new JButton("Utility Bar Panel Test"));
		this.setBackground(R.Colors.MMF_BG);
	}
}
