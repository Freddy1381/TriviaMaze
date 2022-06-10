/**
 * TCSS 360 Trivia Maze
 */
package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Door;
import model.DoorStatus;
import model.Location;
import model.Question;

/**
 * Unit test class for Door class
 *
 * @author Zhaoyu Xu
 * @version 1.0
 */
class DoorTest {

	/**
	 * myDoor1 is a door at location(1, 0).
	 */
	private final Door myDoor1 = new Door(new Location(1, 0));

	/**
	 * myDoor2 is a door at location(1, 0) but locked.
	 */
	private static Door myDoor2 = new Door(new Location(1, 0));

	/**
	 * myDoor3 is a door at location(1, 0) but dead.
	 */
	private static Door myDoor3 = new Door(new Location(1, 0));

	/**
	 * myDoor4 is a door at location(0, 1).
	 */
	private final Door myDoor4 = new Door(new Location(0, 1));

	/**
	 * myQuestion is a question that can be set to a door.
	 */
	private static final Question myQuestion = new Question("Short Answer",
			"Who patened a steam engine that produced continuous rotary motion?", "James Watt",
			"His first name starts with J and his last name is a unit of power.");

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		myDoor2.setQuestion(myQuestion);
		myDoor2.setStatus(DoorStatus.OPEN);
		myDoor3.setStatus(DoorStatus.DEAD);
	}

	/**
	 * Test method for {@link model.Door#hashCode()}.
	 */
	@Test
	void testHashCode() {
		assertEquals(myDoor1.hashCode(), myDoor2.hashCode(), "Chech hashCode for door 1 & 2");
		assertEquals(myDoor2.hashCode(), myDoor3.hashCode(), "Check hashCode for door 2 & 3");
		assertNotEquals(myDoor1.hashCode(), myDoor4.hashCode(), "Check hashCode for door 1 & 4");
	}

	/**
	 * Test method for {@link model.Door#Door(model.Location)}.
	 */
	@Test
	void testDoor() {
		assertThrows(NullPointerException.class, () -> new Door(null));
	}

	/**
	 * Test method for {@link model.Door#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		assertEquals(myDoor1.equals(myDoor2), true, "Test equals with two identical doors");
		assertEquals(myDoor2.equals(myDoor3), true, "Test equals with two identical doors with different door status");
		assertEquals(myDoor1.equals(myDoor4), false, "Test equals with two different doors");
		assertThrows(NullPointerException.class, () -> myDoor1.equals(null));
	}

	/**
	 * Test method for {@link model.Door#getDoorLocation()}.
	 */
	@Test
	void testGetDoorLocation() {
		assertEquals(new Location(1, 0), myDoor1.getDoorLocation(), "Get door location at 1, 0");
		assertEquals(new Location(0, 1), myDoor4.getDoorLocation(), "Get door location at 0, 1");

	}

	/**
	 * Test method for {@link model.Door#getQuestion()}.
	 */
	@Test
	void testGetQuestion() {
		assertEquals(myQuestion, myDoor2.getQuestion(), "Get question of door 2");
		assertEquals(null, myDoor1.getQuestion(), "Get question of door 1");
		assertEquals(null, myDoor4.getQuestion(), "Get question of door 4");
	}

	/**
	 * Test method for {@link model.Door#getStatus()}.
	 */
	@Test
	void testGetStatus() {
		assertEquals(DoorStatus.LOCKED, myDoor1.getStatus(), "Get status of door 1");
		assertEquals(DoorStatus.OPEN, myDoor2.getStatus(), "Get status of door 2");
		assertEquals(DoorStatus.DEAD, myDoor3.getStatus(), "Get status of door 3");
		assertEquals(DoorStatus.LOCKED, myDoor4.getStatus(), "Get status of door 4");

	}

	/**
	 * Test method for {@link model.Door#isDEAD()}.
	 */
	@Test
	void testIsDEAD() {
		assertEquals(false, myDoor1.isDEAD(), "Is door 1 dead?");
		assertEquals(false, myDoor2.isDEAD(), "Is door 2 dead?");
		assertEquals(true, myDoor3.isDEAD(), "Is door 3 dead?");
		assertEquals(false, myDoor4.isDEAD(), "Is door 4 dead?");
	}

	/**
	 * Test method for {@link model.Door#isLocked()}.
	 */
	@Test
	void testIsLocked() {
		assertEquals(true, myDoor1.isLocked(), "Is door 1 locked?");
		assertEquals(false, myDoor2.isLocked(), "Is door 2 locked?");
		assertEquals(false, myDoor3.isLocked(), "Is door 3 locked?");
		assertEquals(true, myDoor4.isLocked(), "Is door 4 locked?");
	}

	/**
	 * Test method for {@link model.Door#isOpen()}.
	 */
	@Test
	void testIsOpen() {
		assertEquals(false, myDoor1.isOpen(), "Is door 1 open?");
		assertEquals(true, myDoor2.isOpen(), "Is door 2 open?");
		assertEquals(false, myDoor3.isOpen(), "Is door 3 open?");
		assertEquals(false, myDoor4.isOpen(), "Is door 4 open?");
	}

	/**
	 * Test method for {@link model.Door#setQuestion(model.Question)}.
	 */
	@Test
	void testSetQuestion() {
		assertThrows(NullPointerException.class, () -> myDoor3.setQuestion(null));
		myDoor3.setQuestion(myQuestion);
		assertEquals(myQuestion, myDoor3.getQuestion(), "Test when door 3 has been assigned a question");
	}

	/**
	 * Test method for {@link model.Door#setStatus(model.DoorStatus)}.
	 */
	@Test
	void testSetStatus() {
		assertThrows(NullPointerException.class, () -> myDoor4.setStatus(null));
		myDoor4.setStatus(DoorStatus.DEAD);
		assertEquals(DoorStatus.DEAD, myDoor4.getStatus(), "Test when door 4 is set to Dead");
		myDoor4.setStatus(DoorStatus.OPEN);
		assertEquals(DoorStatus.OPEN, myDoor4.getStatus(), "Test when door 4 is set to Open");
		myDoor4.setStatus(DoorStatus.LOCKED);
		assertEquals(DoorStatus.LOCKED, myDoor4.getStatus(), "Test when door 4 is set to Locked");

	}

}
