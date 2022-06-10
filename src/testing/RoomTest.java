/**
 * TCSS 360 Trivia Maze
 */
package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Location;
import model.Room;

/**
 * Unit test class for Room class. 
 * 
 * @author Zhaoyu Xu
 * @version 1.0
 */
class RoomTest {
	
	/**
	 * myRoom1 is a room at location(2, 2).
	 */
	private final Room myRoom1 = new Room(new Location(2, 2));
	
	/**
	 * myRoom2 is the same as myRoom1.
	 */
	private final Room myRoom2 = new Room(new Location(2, 2));
	
	/**
	 * myRoom3 is a room at location(4, 4).
	 */
	private final Room myRoom3 = new Room(new Location(4, 4));

	/**
	 * Test method for {@link model.Room#hashCode()}.
	 */
	@Test
	void testHashCode() {
		assertEquals(myRoom1.hashCode(), myRoom2.hashCode(), "Chech hashCode for room 1 & 2");
		assertNotEquals(myRoom2.hashCode(), myRoom3.hashCode(), "Check hashCode for room 2 & 3");
	}

	/**
	 * Test method for {@link model.Room#Room(model.Location)}.
	 */
	@Test
	void testRoom() {
		assertThrows(NullPointerException.class, () -> new Room(null));
	}

	/**
	 * Test method for {@link model.Room#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		assertEquals(myRoom1.equals(myRoom2), true, "Test equals with two identical rooms");
		assertEquals(myRoom2.equals(myRoom3), false, "Test equals with two different rooms");
		assertThrows(NullPointerException.class, () -> myRoom1.equals(null));
	}

	/**
	 * Test method for {@link model.Room#getRoomLocation()}.
	 */
	@Test
	void testGetRoomLocation() {
		assertEquals(new Location(2, 2), myRoom1.getRoomLocation(), "Get room location at 2, 2");
		assertEquals(new Location(4, 4), myRoom3.getRoomLocation(), "Get room location at 4, 4");
	}

}
