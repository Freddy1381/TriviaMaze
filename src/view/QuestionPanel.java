/*
 * TCSS 360 Trivia Maze
 */

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

	/** The JTextPane containing the question. */
	private JTextPane myQuestionTextPane;

	/** The question type. */
	private String myQuestionType;

	/** The question name. */
	private String myQuestionName;

	/** The correct answer of the question. */
	private String myCorrectAnswer;

	/** The JTextField that will be used for collecting short answer input. */
	private JTextField mySAInput;

	/** The private LinkedList containing radio buttons to represent the choices. */
	private LinkedList<JRadioButton> myRadioBtns;

	/**
	 * The private final ButtonGroup that will hold the radio buttons together
	 * visially.
	 */
	private final ButtonGroup myBtnGroup = new ButtonGroup();

	/**
	 * Constructor for the question panel.
	 */
	public QuestionPanel() {
		super();
		setLayout(new BorderLayout(R.Dimensions.UTILITY_V_GAP, R.Dimensions.UTILITY_H_GAP));
		myQuestionTextPane = new JTextPane();
		myQuestionTextPane.setText(R.Strings.QUESTION_TEXT);
		myQuestionTextPane.setFont(R.Fonts.QUESTION_FONT);
		myQuestionTextPane.setForeground(R.Colors.TEXT_COLOR);
		myQuestionTextPane.setBackground(R.Colors.BUTTON_BG);
		myQuestionTextPane.setEditable(false);
		myQuestionTextPane.setVisible(false);
		myRadioBtns = new LinkedList<>();
		add(myQuestionTextPane, BorderLayout.NORTH);
		setBackground(R.Colors.BG);
	}

	/**
	 * Returns true if the player's answer is equivalent to the correct answer;
	 * false otherwise.
	 *
	 * @return true/false
	 */
	public boolean checkAnswer() {
		switch (myQuestionType) {
		case (R.Strings.QUESTION_TYPE_SA):
			return myCorrectAnswer.toLowerCase().replaceAll(" ", "")
					.equals(mySAInput.getText().toLowerCase().replaceAll(" ", ""));
		default:
			return myCorrectAnswer.equals(getSelectedAnswer());
		}
	}

	/**
	 * Returns a radio button for the specified option.
	 *
	 * @param theAnswer the option for the question
	 * @return a radio button
	 */
	private JRadioButton createButton(final String theAnswer) {
		final JRadioButton radio = new JRadioButton();
		radio.setText(theAnswer);
		radio.setFont(R.Fonts.BUTTON_FONT);
		myBtnGroup.add(radio);
		radio.setForeground(R.Colors.TEXT_COLOR);
		radio.setBackground(R.Colors.BUTTON_BG);
		radio.setPreferredSize(R.Dimensions.BUTTON_SIZE);
		radio.setVisible(true);
		radio.setSelected(false);
		myRadioBtns.add(radio);
		return radio;
	}

	/**
	 * Returns a String answer selected by the player from the group of radio
	 * buttons.
	 *
	 * @return a String answer
	 */
	private String getSelectedAnswer() {
		for (JRadioButton btn : myRadioBtns) {
			if (btn.isSelected()) {
				return btn.getText();
			}
		}
		return null;
	}

	/**
	 * Helper method to display the question and decide how to request input from
	 * player.
	 *
	 * @param theQuestion question that is being displayed
	 */
	public void setUpQuestionInfo(final Question theQuestion) {
		if (mySAInput != null) {
			mySAInput.removeAll();
		}
		myQuestionName = theQuestion.getQuestion();
		myQuestionTextPane.setText(myQuestionName);
		myQuestionTextPane.setVisible(true);
		myCorrectAnswer = theQuestion.getCorrectAnswer();
		myQuestionType = theQuestion.getType();
		switch (myQuestionType) {
		case (R.Strings.QUESTION_TYPE_SA):
			setUpSATextbox();
			break;
		default:
			ArrayList<String> options = ((MCQuestion) theQuestion).getOptions();
			setUpRadioBox(options);
			break;
		}
	}

	/**
	 * If the question is a Multiple choice or true/false question, this method will
	 * be called to setup radio buttons for choices.
	 *
	 * @param theOptions options of the question
	 */
	private void setUpRadioBox(ArrayList<String> theOptions) {
		GridLayout layout = new GridLayout(theOptions.size() / 2, theOptions.size() / 2);
		layout.setHgap(R.Dimensions.H_PADDING);
		layout.setVgap(R.Dimensions.V_PADDING);
		final JPanel pane = new JPanel();
		pane.setLayout(layout);
		for (String theOption : theOptions) {
			JRadioButton btn = createButton(theOption);
			pane.add(btn);
		}
		pane.setBackground(R.Colors.BG);
		add(pane, BorderLayout.CENTER);
	}

	/**
	 * If the question is a short answer question, this method will be called to
	 * setup the short answer input text area.
	 */
	private void setUpSATextbox() {
		GridLayout layout = new GridLayout(2, 1);
		layout.setHgap(R.Dimensions.H_PADDING);
		layout.setVgap(R.Dimensions.V_PADDING);
		final JPanel pane = new JPanel();
		pane.setLayout(layout);
		final JLabel ansLbl = new JLabel("Type your answer below: ");
		ansLbl.setFont(R.Fonts.QUESTION_FONT);
		ansLbl.setBackground(R.Colors.BG);
		ansLbl.setForeground(R.Colors.TEXT_COLOR);
		pane.add(ansLbl);
		mySAInput = new JTextField();
		mySAInput.setPreferredSize(R.Dimensions.LABEL_SIZE);
		pane.add(mySAInput);
		pane.setBackground(R.Colors.BG);
		add(pane, BorderLayout.CENTER);
	}
}
