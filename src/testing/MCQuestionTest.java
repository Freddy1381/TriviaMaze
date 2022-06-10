/**
 * TCSS 360 Trivia Maze
 */
package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.MCQuestion;

/**
 * Unit test class for MCQuestion class.
 * 
 * @author Zhaoyu Xu
 * @version 1.0
 */
class MCQuestionTest {
	
	/**
	 * myQuestion1 is a question example.
	 */
	private final MCQuestion myQuestion1 = new MCQuestion("Multiple Choice",
			"When was the iPhone released?", "2007",
			"It was released before the Great Recession.", "2007 2008 2006 2005");

	/**
	 * myQuestion2 is a question example.
	 */
	private final MCQuestion myQuestion2 = new MCQuestion("True False", "Is Oology the study of eggs?", "True",
			"What does the big O looks like?", "True False");

	/**
	 * Test method for {@link model.MCQuestion#MCQuestion(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testMCQuestion() {
		assertThrows(NullPointerException.class, () -> new MCQuestion("String", "String", "String", "String", null));
		assertThrows(IllegalArgumentException.class, () -> new MCQuestion("String", "String", "String", "String", ""));
	}

	/**
	 * Test method for {@link model.MCQuestion#getOptions()}.
	 */
	@Test
	void testGetOptions() {
		ArrayList<String> expected1 = new ArrayList<>();
		expected1.add("2007");
		expected1.add("2008");
		expected1.add("2006");
		expected1.add("2005");
		ArrayList<String> expected2 = new ArrayList<>();
		expected2.add("True");
		expected2.add("False");
		assertEquals(expected1, myQuestion1.getOptions(), "Get options of question 1");
		assertEquals(expected2, myQuestion2.getOptions(), "Get options of question 2");
	}

}
