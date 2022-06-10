/**
 * TCSS 360 Trivia Maze
 */
package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Question;

/**
 * Unit test class for Question class.
 * 
 * @author Zhaoyu Xu
 * @version 1.0
 */
class QuestionTest {

	/**
	 * myQuestion1 is a question example.
	 */
	private final Question myQuestion1 = new Question("Short Answer",
			"Who patented a steam engine that produced continuous rotary motion?", "James Watt",
			"His first name starts with J and his last name is a unit of power.");

	/**
	 * myQuestion2 is a question example.
	 */
	private final Question myQuestion2 = new Question("Short Answer", "What language does Node.js use?", "JavaScript",
			"Dude, it's .js. What does that mean?");

	/**
	 * myString1 is a string of strings.
	 */
	private final String myString1 = "Microsoft Amazon Google Facebook";

	/**
	 * Test method for
	 * {@link model.Question#Question(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testQuestion() {
		assertThrows(NullPointerException.class, () -> new Question(null, "String", "String", "String"));
		assertThrows(NullPointerException.class, () -> new Question("String", null, "String", "String"));
		assertThrows(NullPointerException.class, () -> new Question("String", "String", null, "String"));
		assertThrows(NullPointerException.class, () -> new Question("String", "String", "String", null));
		assertThrows(IllegalArgumentException.class, () -> new Question("", "String", "String", "String"));
		assertThrows(IllegalArgumentException.class, () -> new Question("String", "", "String", "String"));
		assertThrows(IllegalArgumentException.class, () -> new Question("String", "String", "", "String"));
		assertThrows(IllegalArgumentException.class, () -> new Question("String", "String", "String", ""));
	}

	/**
	 * Test method for
	 * {@link model.Question#convertStringToArray(java.lang.String)}.
	 */
	@Test
	void testConvertStringToArray() {
		assertThrows(NullPointerException.class, () -> myQuestion1.convertStringToArray(null));
		ArrayList<String> test = new ArrayList<>();
		test.add("Microsoft");
		test.add("Amazon");
		test.add("Google");
		test.add("Facebook");
		assertEquals(test, myQuestion1.convertStringToArray(myString1), "Test convert string to array");
	}

	/**
	 * Test method for {@link model.Question#getCorrectAnswer()}.
	 */
	@Test
	void testGetCorrectAnswer() {
		assertEquals("James Watt", myQuestion1.getCorrectAnswer(), "Get correct answer for question 1");
		assertEquals("JavaScript", myQuestion2.getCorrectAnswer(), "Get correct answer for question 2");
	}

	/**
	 * Test method for {@link model.Question#getHint()}.
	 */
	@Test
	void testGetHint() {
		String expected1 = "His first name starts with J and his last name is a unit of power.";
		String expected2 = "Dude, it's .js. What does that mean?";
		assertEquals(expected1, myQuestion1.getHint(), "Get hint for question 1");
		assertEquals(expected2, myQuestion2.getHint(), "Get hint for question 2");
	}

	/**
	 * Test method for {@link model.Question#getQuestion()}.
	 */
	@Test
	void testGetQuestion() {
		String expected1 = "Who patented a steam engine that produced continuous rotary motion?";
		String expected2 = "What language does Node.js use?";
		assertEquals(expected1, myQuestion1.getQuestion(), "Get question name for question 1");
		assertEquals(expected2, myQuestion2.getQuestion(), "Get question name for question 2");

	}

	/**
	 * Test method for {@link model.Question#getType()}.
	 */
	@Test
	void testGetType() {
		assertEquals("Short Answer", myQuestion1.getType(), "Get type for question 1");
		assertEquals("Short Answer", myQuestion2.getType(), "Get type answer for question 2");
	}

}
