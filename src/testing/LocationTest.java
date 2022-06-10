/**
 * TCSS 360 Trivia Maze
 */
package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model.Location;

/**
 * Unit test class for Location class
 *
 * @author Zhaoyu Xu
 * @version 1.0
 */
class LocationTest {

	/**
	 * myLoc1 is at 0, 0.
	 */
	private final Location myLoc1 = new Location(0, 0);

	/**
	 * myLoc2 is the same as myLoc1.
	 */
	private final Location myLoc2 = new Location(0, 0);

	/**
	 * myLoc3 is at 6, 6.
	 */
	private final Location myLoc3 = new Location(6, 6);

	/**
	 * Test method for {@link model.Location#hashCode()}.
	 */
	@Test
	void testHashCode() {
		assertEquals(myLoc1.hashCode(), myLoc2.hashCode(), "Check hashCode for location 1 & 2");
		assertNotEquals(myLoc2.hashCode(), myLoc3.hashCode(), "Check hashCode for location 2 & 3");
	}

	/**
	 * Test method for {@link model.Location#Location(int, int)}.
	 */
	@Test
	void testLocation() {
		assertThrows(IllegalArgumentException.class, () -> new Location(-1, 1));
		assertThrows(IllegalArgumentException.class, () -> new Location(1, -1));
	}

	/**
	 * Test method for {@link model.Location#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		assertEquals(myLoc1.equals(myLoc2), true, "Test equals method with two identical locations");
		assertEquals(myLoc2.equals(myLoc3), false, "Test equals method with two different locations");
		assertThrows(NullPointerException.class, () -> myLoc1.equals(null));
	}

	/**
	 * Test method for {@link model.Location#getX()}.
	 */
	@Test
	void testGetX() {
		assertEquals(0, myLoc1.getX(), "Get X is 0");
		assertEquals(6, myLoc3.getX(), "Get X is 6");
	}

	/**
	 * Test method for {@link model.Location#getY()}.
	 */
	@Test
	void testGetY() {
		assertEquals(0, myLoc1.getY(), "Get Y is 0");
		assertEquals(6, myLoc3.getY(), "Get Y is 6");
	}

}
