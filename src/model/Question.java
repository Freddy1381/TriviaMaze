/*
 * TCSS 360 Trivia Maze
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * A parent class for questions.
 *
 * @author Zhaoyu Xu
 * @version 2.1
 */
public class Question implements Serializable {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

	/** The type of the question. */
	private String myType;

	/** The name of the question. */
	private String myQuestionName;

	/** The correct answer of the question. */
	private String myCorrectAnswer;

	/** The hint of the question. */
	private String myHint;

	/**
	 * Constructs a question object.
	 *
	 * @param theType       type of the question
	 * @param theQuestion   name of the question
	 * @param theCorrectAns correct answer of the question
	 * @param theHint       hint of the question
	 */
	public Question(final String theType, final String theQuestion, final String theCorrectAns, final String theHint) {
		if (theType.isEmpty()) {
			throw new IllegalArgumentException("Question type can't be empty. ");
		}
		if (theQuestion.isEmpty()) {
			throw new IllegalArgumentException("Question name can't be empty. ");
		}
		if (theCorrectAns.isEmpty()) {
			throw new IllegalArgumentException("Correct Answer can't be empty. ");
		}
		if (theHint.isEmpty()) {
			throw new IllegalArgumentException("Question hint can't be empty. ");
		}
		myType = Objects.requireNonNull(theType);
		myQuestionName = Objects.requireNonNull(theQuestion);
		myCorrectAnswer = Objects.requireNonNull(theCorrectAns);
		myHint = Objects.requireNonNull(theHint);
	}

	/**
	 * Helper method to convert Strings separated by space into an arraylist.
	 *
	 * @param theString the string of strings
	 * @return the arraylist of strings
	 */
	public ArrayList<String> convertStringToArray(final String theString) {
		if (theString == null) {
			throw new NullPointerException();
		}
		Scanner scanner = new Scanner(theString);
		scanner.useDelimiter(" ");
		ArrayList<String> temp = new ArrayList<>();
		while (scanner.hasNext()) {
			temp.add(scanner.next());
		}
		scanner.close();
		return temp;
	}

	/**
	 * Returns the correct answer of the question.
	 *
	 * @return myCorrectAnswer
	 */
	public String getCorrectAnswer() {
		return myCorrectAnswer;
	}

	/**
	 * Returns the hint of the question.
	 *
	 * @return myHint
	 */
	public String getHint() {
		return myHint;
	}

	/**
	 * Returns the name of the question.
	 *
	 * @return myQuestionName
	 */
	public String getQuestion() {
		return myQuestionName;
	}

	/**
	 * Returns the type of the question.
	 *
	 * @return myType
	 */
	public String getType() {
		return myType;
	}
}
