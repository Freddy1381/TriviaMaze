package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import res.R;

public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String myType;
	private String myQuestionName;
	private String myCorrectAnswer;
	private String myCorrectMsg;
	private String myWrongMsg;
	private String myHint;
	
	public Question(final String theType, final String theQuestion, 
				    final String theCorrectAns, 
				    final String theHint) {
		myType = theType;
		myQuestionName = theQuestion;
		myCorrectAnswer = theCorrectAns;
		myCorrectMsg = R.Strings.QUESTION_MSG_CORRECT;
		myWrongMsg = R.Strings.QUESTION_MSG_WRONG;
		myHint = theHint;
	}
	
	public ArrayList<String> convertStringToArray(final String theString) {
		Scanner scanner = new Scanner(theString);
		scanner.useDelimiter(" ");
		ArrayList<String> temp = new ArrayList<>();
		while(scanner.hasNext()) {
			temp.add(scanner.next());
		}
		scanner.close();
		return temp;
	}

	public String getType() {
		return myType;
	}

	public String getQuestion() {
		return myQuestionName;
	}

	public String getCorrectAnswer() {
		return myCorrectAnswer;
	}

	public String getCommentWrong() {
		return myWrongMsg;
	}

	public String getCommentRight() {
		return myCorrectMsg;
	}
	
	public String getHint() {
		return myHint;
	}

	@Override
	public String toString() {
		return "Question [type=" + myType + ", question=" + myQuestionName + ", correctAnswer="
				+ myCorrectAnswer + "]";
	}
}
