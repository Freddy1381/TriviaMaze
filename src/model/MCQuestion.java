/*
 * TCSS 360 Trivia Maze
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A child class of Question.
 *
 * @author Zhaoyu Xu
 * @version 1.0
 */
public class MCQuestion extends Question implements Serializable {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

	/** An ArrayList of all options of a multiple choice question. */
	private ArrayList<String> myOptions;

	/**
	 * Constructor of multiple choice question.
	 *
	 * @param theType       type of the question
	 * @param theQuestion   name of the question
	 * @param theCorrectAns correct answer of the question
	 * @param theHint       hint of the question
	 * @param theOptions    options of the question
	 */
	public MCQuestion(final String theType, final String theQuestion, final String theCorrectAns, final String theHint,
			final String theOptions) {
		super(theType, theQuestion, theCorrectAns, theHint);
		if (theOptions.isEmpty()) {
			throw new IllegalArgumentException("Options can't be empty.");
		}
		this.myOptions = super.convertStringToArray(Objects.requireNonNull(theOptions));
	}

	/**
	 * Returns an ArrayList of all options to a question.
	 *
	 * @return an ArrayList of options
	 */
	public ArrayList<String> getOptions() {
		return myOptions;
	}

}
