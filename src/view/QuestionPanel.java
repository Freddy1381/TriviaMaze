package view;

import javax.swing.JButton;
import javax.swing.JPanel;

import res.R;

public class QuestionPanel extends JPanel {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

	public QuestionPanel() {
		super();
		this.add(new JButton("Question Panel Test"));
		this.setBackground(R.Colors.MMF_BG);
	}
}
