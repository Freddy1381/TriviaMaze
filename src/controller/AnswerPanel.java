package controller;

import javax.swing.JButton;
import javax.swing.JPanel;

import res.R;

public class AnswerPanel extends JPanel {

	/** The serialization ID. */
	private static final long serialVersionUID = 1L;

	public AnswerPanel() {
		super();
		this.add(new JButton("Answer Panel Test"));
		this.setBackground(R.Colors.MMF_BG);
	}
}
