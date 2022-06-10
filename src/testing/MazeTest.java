/**
 * TCSS 360 Trivia Maze
 */
package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.QuestionGetter;
import model.Direction;
import model.Door;
import model.DoorStatus;
import model.Location;
import model.Maze;
import model.Question;
import model.Room;

/**
 * Unit test class for Maze class.
 *
 * @author Zhaoyu Xu
 * @version 1.0
 */
class MazeTest {

	private static QuestionGetter myGetter;

	private static Queue<Question> myQuestionList;

	private static Maze myMaze;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		myGetter = new QuestionGetter();
		myQuestionList = myGetter.getQuestionList();
		myMaze = new Maze(myQuestionList);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		myMaze = null;
		myQuestionList = null;
		myGetter = null;
	}

	/**
	 * Test method for {@link model.Maze#Maze(java.util.Queue)}.
	 */
	@Test
	void testMaze() {
		assertThrows(NullPointerException.class, () -> new Maze(null));
		assertEquals(myMaze.getClass().toString(), "class model.Maze", "Test for successful maze initiation");
	}

	/**
	 * Test method for {@link model.Maze#canTraverseMaze(model.Room)}.
	 */
	@Test
	void testCanTraverseMaze() {
		assertEquals(myMaze.canTraverseMaze(myMaze.getCurrentRoom()), true,
				"Test canTraverseMaze with all doors not DEAD");
		myMaze.makeAllDoorsStatus(DoorStatus.DEAD);
		assertEquals(myMaze.canTraverseMaze(myMaze.getCurrentRoom()), false,
				"Test canTraverseMaze with all doors DEAD");
		myMaze.makeAllDoorsStatus(DoorStatus.LOCKED);
		myMaze.setCurrentRoom(new Room(new Location(2, 2)));
		assertEquals(myMaze.canTraverseMaze(myMaze.getCurrentRoom()), true,
				"Test canTraverseMaze with changed current room");
	}

	/**
	 * Test method for {@link model.Maze#findActualRoom(model.Room)}.
	 */
	@Test
	void testFindActualRoom() {
		assertThrows(NullPointerException.class, () -> myMaze.findActualRoom(null));
		Room test1 = new Room(new Location(0, 0));
		Room test2 = new Room(new Location(6, 6));
		assertEquals(myMaze.getStartRoom(), myMaze.findActualRoom(test1),
				"Test for finding the actual start room with room location");
		assertEquals(myMaze.getEndRoom(), myMaze.findActualRoom(test2),
				"Test for finding the actual end room with room location");
	}

	/**
	 * Test method for {@link model.Maze#findDoor(model.Direction)}.
	 */
	@Test
	void testFindDoor() {
		myMaze.setCurrentRoom(new Room(new Location(2, 2)));
		assertThrows(NullPointerException.class, () -> myMaze.findDoor(null));
		Door testUp = new Door(new Location(2, 1));
		Door testDown = new Door(new Location(2, 3));
		Door testLeft = new Door(new Location(1, 2));
		Door testRight = new Door(new Location(3, 2));
		assertEquals(testUp, myMaze.findDoor(Direction.UP), "Test for finding the door above the current room");
		assertEquals(testDown, myMaze.findDoor(Direction.DOWN), "Test for finding the door below the current room");
		assertEquals(testLeft, myMaze.findDoor(Direction.LEFT), "Test for finding the door left of the current room");
		assertEquals(testRight, myMaze.findDoor(Direction.RIGHT),
				"Test for finding the door right of the current room");
	}

	/**
	 * Test method for {@link model.Maze#findDuplicateDoor(model.Direction)}.
	 */
	@Test
	void testFindDuplicateDoor() {
		myMaze.setCurrentRoom(new Room(new Location(2, 2)));
		assertThrows(NullPointerException.class, () -> myMaze.findDuplicateDoor(null));
		Door testUp = new Door(new Location(2, 1));
		Door testDown = new Door(new Location(2, 3));
		Door testLeft = new Door(new Location(1, 2));
		Door testRight = new Door(new Location(3, 2));
		assertEquals(testUp, myMaze.findDuplicateDoor(Direction.UP),
				"Test for finding the duplicate door above the current room");
		assertEquals(testDown, myMaze.findDuplicateDoor(Direction.DOWN),
				"Test for finding the duplicate door below the current room");
		assertEquals(testLeft, myMaze.findDuplicateDoor(Direction.LEFT),
				"Test for finding the duplicate door left of the current room");
		assertEquals(testRight, myMaze.findDuplicateDoor(Direction.RIGHT),
				"Test for finding the duplicate door right of the current room");
	}

	/**
	 * Test method for {@link model.Maze#getCurrentRoom()}.
	 */
	@Test
	void testGetCurrentRoom() {
		Room test1 = new Room(new Location(2, 2));
		myMaze.setCurrentRoom(new Room(new Location(2, 2)));
		assertEquals(test1, myMaze.getCurrentRoom(), "Test for getting current room");
		Room test2 = new Room(new Location(4, 4));
		myMaze.setCurrentRoom(new Room(new Location(4, 4)));
		assertEquals(test2, myMaze.getCurrentRoom(), "Test for getting current room");
	}

	/**
	 * Test method for {@link model.Maze#getDoorList(model.Room)}.
	 */
	@Test
	void testGetDoorList() {
		assertThrows(NullPointerException.class, () -> myMaze.getDoorList(null));
		Maze testMaze = new Maze(myQuestionList);
		List<Door> test1 = testMaze.getMap().get(testMaze.getStartRoom());
		List<Door> test2 = testMaze.getMap().get(testMaze.getEndRoom());
		assertEquals(test1, myMaze.getDoorList(myMaze.getStartRoom()), "Test for getting the door list of start room");
		assertEquals(test2, myMaze.getDoorList(myMaze.getEndRoom()), "Test for getting the door list of end room");
	}

	/**
	 * Test method for {@link model.Maze#getEndRoom()}.
	 */
	@Test
	void testGetEndRoom() {
		Room test = new Room(new Location(6, 6));
		assertEquals(test, myMaze.getEndRoom(), "Test for getting the end room");
	}

	/**
	 * Test method for {@link model.Maze#getMap()}.
	 */
	@Test
	void testGetMap() {
		Maze testMaze = new Maze(myQuestionList);
		Map<Room, List<Door>> testMap = testMaze.getMap();
		assertEquals(testMap, myMaze.getMap(), "Test for getting the map of the maze");
	}

	/**
	 * Test method for {@link model.Maze#getRoomSet()}.
	 */
	@Test
	void testGetRoomSet() {
		Maze testMaze = new Maze(myQuestionList);
		Set<Room> testSet = testMaze.getRoomSet();
		assertEquals(testSet, myMaze.getRoomSet(), "Test for getting the set of all rooms");
	}

	/**
	 * Test method for {@link model.Maze#getStartRoom()}.
	 */
	@Test
	void testGetStartRoom() {
		assertEquals(new Room(new Location(0, 0)), myMaze.getStartRoom(), "Test of getting the start room of the maze");
	}

	/**
	 * Test method for {@link model.Maze#isAtExit()}.
	 */
	@Test
	void testIsAtExit() {
		assertEquals(false, myMaze.isAtExit(), "Player is not at the end room");
		myMaze.setCurrentRoom(myMaze.getEndRoom());
		assertEquals(true, myMaze.isAtExit(), "Player is at the end room");
	}

	/**
	 * Test method for {@link model.Maze#setCurrentRoom(model.Room)}.
	 */
	@Test
	void testSetCurrentRoom() {
		assertThrows(NullPointerException.class, () -> myMaze.setCurrentRoom(null));
		myMaze.setCurrentRoom(myMaze.getEndRoom());
		assertEquals(myMaze.getEndRoom(), myMaze.getCurrentRoom(), "Test when current room is set to end room");
		Room test1 = new Room(new Location(6, 4));
		myMaze.setCurrentRoom(test1);
		assertEquals(test1, myMaze.getCurrentRoom(), "Test when current room is set to Room(6, 4");
		Room test2 = new Room(new Location(4, 6));
		myMaze.setCurrentRoom(test2);
		assertEquals(test2, myMaze.getCurrentRoom(), "Test when current room is set to Room(4, 6");
		Room test3 = new Room(new Location(4, 4));
		myMaze.setCurrentRoom(test3);
		assertEquals(test3, myMaze.getCurrentRoom(), "Test when current room is set to Room(4, 4");
	}

	/**
	 * Test method for {@link model.Maze#writeToFile()}.
	 */
	@SuppressWarnings("null")
	@Test
	void testWriteToFile() {
		assertEquals(true, myMaze.writeToFile(), "Test save a working maze");
		Maze testMaze = null;
		assertThrows(NullPointerException.class, () -> testMaze.writeToFile());
	}

}
