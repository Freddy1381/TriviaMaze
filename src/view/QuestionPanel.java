package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.MCQuestion;
import model.Question;
import res.R;

public class QuestionPanel extends JPanel {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;
	
	JTextPane myQuestionTextPane;
	
	private String myQuestionType;
	
	private String myQuestionName;
	
	private String myCorrectAnswer;
	
	private JTextField mySAInput;
	
	private LinkedList<JRadioButton> myRadioBtns;
	
	private final ButtonGroup myBtnGroup = new ButtonGroup();

	public QuestionPanel() {
		super();
		setLayout(new BorderLayout(R.Dimensions.UTILITY_V_GAP, R.Dimensions.UTILITY_H_GAP));
		myQuestionTextPane = new JTextPane();
		myQuestionTextPane.setText(R.Strings.QUESTION_TEXT);
		myQuestionTextPane.setFont(R.Fonts.QUESTION_FONT);
		myQuestionTextPane.setForeground(R.Colors.TEXT_COLOR);
		myQuestionTextPane.setBackground(R.Colors.MMF_BUTTON_BG);
		myQuestionTextPane.setEditable(false);
		myQuestionTextPane.setVisible(false);
		myRadioBtns = new LinkedList<JRadioButton>();
		add(myQuestionTextPane, BorderLayout.NORTH);
		setBackground(R.Colors.MMF_BG);
	}

	public void setUpQuestionInfo(final Question theQuestion) {
		// TODO Auto-generated method stub
		if (mySAInput != null) {
			mySAInput.removeAll();
		}
		myQuestionName = theQuestion.getQuestion();
		myQuestionTextPane.setText(myQuestionName);
		myQuestionTextPane.setVisible(true);
		myCorrectAnswer = theQuestion.getCorrectAnswer();
		myQuestionType = theQuestion.getType();
		switch(myQuestionType) {
		case ("Short Answer"):
			setUpSATextbox();
			break;
		default:
			ArrayList<String> options = ((MCQuestion) theQuestion).getOptions();
			setUpRadioBox(options);
			break;
		}
	}
	
	private void setUpRadioBox(ArrayList<String> theOptions) {
		// TODO Auto-generated method stub
		GridLayout layout = new GridLayout(theOptions.size() / 2, theOptions.size() / 2);
		layout.setHgap(R.Dimensions.H_PADDING);
		layout.setVgap(R.Dimensions.V_PADDING);
		final JPanel pane = new JPanel();
		pane.setLayout(layout);
		for (int i = 0; i < theOptions.size(); i++) {
			JRadioButton btn = createButton(theOptions.get(i));
			pane.add(btn);
		}
		pane.setBackground(R.Colors.MMF_BG);
		add(pane, BorderLayout.CENTER);
	}

	private JRadioButton createButton(final String theAnswer) {
		// TODO Auto-generated method stub
		final JRadioButton radio = new JRadioButton();
		radio.setText(theAnswer);
		radio.setFont(R.Fonts.BUTTON_FONT);
		myBtnGroup.add(radio);
		radio.setForeground(R.Colors.TEXT_COLOR);
		radio.setBackground(R.Colors.MMF_BUTTON_BG);
		radio.setPreferredSize(R.Dimensions.BUTTON_SIZE);
		radio.setVisible(true);
		radio.setSelected(false);
		myRadioBtns.add(radio);
		return radio;
	}

	private void setUpSATextbox() {
		GridLayout layout = new GridLayout(2, 1);
		layout.setHgap(R.Dimensions.H_PADDING);
		layout.setVgap(R.Dimensions.V_PADDING);
		final JPanel pane = new JPanel();
		pane.setLayout(layout);
		final JLabel ansLbl = new JLabel("Type your answer below: ");
		ansLbl.setFont(R.Fonts.QUESTION_FONT);
		ansLbl.setBackground(R.Colors.MMF_BG);
		ansLbl.setForeground(R.Colors.TEXT_COLOR);
		pane.add(ansLbl);
		mySAInput = new JTextField();
		mySAInput.setPreferredSize(R.Dimensions.LABEL_SIZE);
		pane.add(mySAInput);
		pane.setBackground(R.Colors.MMF_BG);
		add(pane, BorderLayout.CENTER);
	}
	
	public boolean checkAnswer() {
		switch (myQuestionType) {
		case ("Short Answer"):
			return myCorrectAnswer.toLowerCase().replaceAll(" ", "").equals(mySAInput.getText()
										  .toLowerCase().replaceAll(" ", ""));
		default:
			return myCorrectAnswer.equals(getSelectedAnswer());
		}
	}
	
	private String getSelectedAnswer() {
		for (JRadioButton btn : myRadioBtns) {
			if (btn.isSelected()) {
				return btn.getText();
			}
		}
		return null;
	}
}
