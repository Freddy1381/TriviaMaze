/*
 * TCSS 360 Trivia Maze
 */

package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import res.R;

/**
 * Maze class stores information about the overall maze.
 *
 * @author Zhaoyu Xu
 * @version 3.1
 */
public class Maze implements Serializable {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

	/** The private final room that indicates the start of the maze. */
	private final Room myStartRoom;

	/** The private final room that indicates the end of the maze. */
	private final Room myEndRoom;

	/** The private room that indicates the current room. */
	private Room myCurrentRoom;

	/** A private queue that is used to store questions. */
	private Queue<Question> myQuestionList;

	/** A private hash set that is used to store used questions. */
	private HashSet<Question> myUsedQuestions;

	/** A private final map for all rooms and all their adjacent doors. */
	private final Map<Room, List<Door>> myAdjacentRooms;

	/**
	 * Constructor that takes a queue list of questions.
	 *
	 * @param theList question list
	 */
	public Maze(final Queue<Question> theList) {
		if (theList == null) {
			throw new NullPointerException();
		}
		myQuestionList = theList;
		myAdjacentRooms = new HashMap<>();
		myUsedQuestions = new HashSet<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				final Room r = new Room(new Location(i * 2, j * 2));
				myAdjacentRooms.putIfAbsent(r, new LinkedList<Door>());
			}
		}
		setupRooms();

		myStartRoom = new Room(new Location(0, 0));
		myCurrentRoom = myStartRoom;
		myEndRoom = new Room(new Location(6, 6));
	}

	/**
	 * Helper method for adding a specified door to a room.
	 *
	 * @param theSource   source room.
	 * @param theLocation door location
	 * @param theDest     destination room
	 */
	private void addDoors(final Room theSource, final Location theLocation, final Room theDest) {
		Door r = new Door(theLocation);
		final Question q = getQuestionFromGetter();
		r.setQuestion(q);
		myAdjacentRooms.get(theSource).add(r);
	}

	/**
	 * Breadth first algorithm to check if the play can traverse the maze to the
	 * end.
	 *
	 * @param theRoom room that the player is in
	 * @return a boolean for whether the player can traverse to the end
	 */
	public boolean canTraverseMaze(final Room theRoom) {
		Set<Room> visited = new LinkedHashSet<>();
		Stack<Room> stack = new Stack<>();
		stack.push(theRoom);
		while (!stack.isEmpty()) {
			Room vertex = stack.pop();
			if (!visited.contains(vertex)) {
				visited.add(vertex);
				for (Door r : getDoorList(vertex)) {
					if (!r.isDEAD()) {
						int doorX = r.getDoorLocation().getX();
						int doorY = r.getDoorLocation().getY();

						int roomX = vertex.getRoomLocation().getX();
						int roomY = vertex.getRoomLocation().getY();

						Room temp = null;

						// Up
						if (roomX == doorX && roomY - 1 == doorY) {
							temp = new Room(new Location(roomX, roomY - 2));
						}
						// Down
						if (roomX == doorX && roomY + 1 == doorY) {
							temp = new Room(new Location(roomX, roomY + 2));
						}
						// Left
						if (roomX - 1 == doorX && roomY == doorY) {
							temp = new Room(new Location(roomX - 2, roomY));
						}
						// Right
						if (roomX + 1 == doorX && roomY == doorY) {
							temp = new Room(new Location(roomX + 2, roomY));
						}
						Room destRoom = findActualRoom(temp);
						stack.push(destRoom);
					}
				}
			}
		}
		if (visited.contains(myEndRoom)) {
			return true;
		}
		return false;
	}

	/**
	 * Helper method to find the actual room inside the maze object.
	 *
	 * @param theRoom the reference room. Only using it's location information.
	 * @return the actual room inside the maze object
	 */
	public Room findActualRoom(final Room theRoom) {
		if (theRoom == null) {
			throw new NullPointerException();
		}
		Room result = null;
		for (Room r : this.getRoomSet()) {
			if (r.equals(theRoom)) {
				result = r;
			}
		}
		return result;
	}

	/**
	 * Helper method for moving player. Finds the door that the player wants to move
	 * to.
	 *
	 * @param theDirection player's direction
	 * @return a corresponding door of the player's direction
	 */
	public Door findDoor(final Direction theDirection) {
		if (theDirection == null) {
			throw new NullPointerException();
		}
		Door result = null;
		Door temp = null;
		int roomX = myCurrentRoom.getRoomLocation().getX();
		int roomY = myCurrentRoom.getRoomLocation().getY();

		if (theDirection == Direction.UP) {
			temp = new Door(new Location(roomX, roomY - 1));
		}
		if (theDirection == Direction.DOWN) {
			temp = new Door(new Location(roomX, roomY + 1));
		}
		if (theDirection == Direction.LEFT) {
			temp = new Door(new Location(roomX - 1, roomY));
		}
		if (theDirection == Direction.RIGHT) {
			temp = new Door(new Location(roomX + 1, roomY));
		}
		List<Door> tempList = this.getMap().get(myCurrentRoom);
		for (Door d : tempList) {
			if (d.equals(temp)) {
				result = d;
			}
		}
		return result;
	}

	/**
	 * Finds the duplicate door. Since this graph object of the maze is an
	 * undirected graph, there will be two doors between each room.
	 *
	 * @param theDirection the direction that the player wants to move to
	 * @return the other door between the current room and the destination room
	 */
	public Door findDuplicateDoor(final Direction theDirection) {
		if (theDirection == null) {
			throw new NullPointerException();
		}
		Door result = null;
		Door temp = null;
		int roomX = myCurrentRoom.getRoomLocation().getX();
		int roomY = myCurrentRoom.getRoomLocation().getY();

		if (theDirection == Direction.UP) {
			roomY -= 2;
			temp = new Door(new Location(roomX, roomY + 1));
		}
		if (theDirection == Direction.DOWN) {
			roomY += 2;
			temp = new Door(new Location(roomX, roomY - 1));
		}
		if (theDirection == Direction.LEFT) {
			roomX -= 2;
			temp = new Door(new Location(roomX + 1, roomY));
		}
		if (theDirection == Direction.RIGHT) {
			roomX += 2;
			temp = new Door(new Location(roomX - 1, roomY));
		}
		List<Door> tempList = this.getMap().get(new Room(new Location(roomX, roomY)));
		for (Door d : tempList) {
			if (d.equals(temp)) {
				result = d;
			}
		}
		return result;
	}

	/**
	 * Returns the room that the player is currently in.
	 *
	 * @return myCurrentRoom
	 */
	public Room getCurrentRoom() {
		return myCurrentRoom;
	}

	/**
	 * Returns the list of adjacent doors of a specific room.
	 *
	 * @param theRoom room specified
	 * @return a list of doors adacent to the room
	 */
	public List<Door> getDoorList(final Room theRoom) {
		if (theRoom == null) {
			throw new NullPointerException();
		}
		return myAdjacentRooms.get(theRoom);
	}

	/**
	 * Returns the end room.
	 *
	 * @return a room that is the end of the maze
	 */
	public Room getEndRoom() {
		return myEndRoom;
	}

	/**
	 * Returns the map of rooms and adjacent doors.
	 *
	 * @return myAdjacentRooms
	 */
	public Map<Room, List<Door>> getMap() {
		return myAdjacentRooms;
	}

	/**
	 * Returns a question from the question list, then add it to the used question
	 * list. If all questions are used, reset the used question list.
	 *
	 * @return a question from the question list
	 */
	private Question getQuestionFromGetter() {
		if (myQuestionList.isEmpty()) {
			myQuestionList.addAll(myUsedQuestions);
			myUsedQuestions.clear();
		}
		Question temp = myQuestionList.poll();
		myUsedQuestions.add(temp);
		return temp;
	}

	/**
	 * Returns a set of all rooms in the maze.
	 *
	 * @return a set of rooms
	 */
	public Set<Room> getRoomSet() {
		return myAdjacentRooms.keySet();
	}

	/**
	 * Returns the start room of the maze.
	 *
	 * @return a room that is the start of the maze
	 */
	public Room getStartRoom() {
		return myStartRoom;
	}

	/**
	 * Returns true if the player is at the end room; false otherwise.
	 *
	 * @return true/false
	 */
	public boolean isAtExit() {
		return this.myCurrentRoom.equals(this.myEndRoom);
	}

	/**
	 * Set all doors to status DEAD.
	 */
	public void makeAllDoorsStatus(DoorStatus theStatus) {
		for (Room r : myAdjacentRooms.keySet()) {
			for (Door d : myAdjacentRooms.get(r)) {
				d.setStatus(theStatus);
			}
		}
	}

	/**
	 * Returns true if the player is at the end room; false otherwise.
	 *
	 * @return true/false
	 */
	public void setCurrentRoom(Room theRoom) {
		if (theRoom == null) {
			throw new NullPointerException();
		}
		myCurrentRoom = theRoom;
	}

	/**
	 * Helper method. For every room, this method will try to go in all four
	 * directions. If the direction leads to another room, it will add a door
	 * between the two rooms.
	 */
	private void setupRooms() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				final Room source = findActualRoom(new Room(new Location(x * 2, y * 2)));

				if (x - 1 >= 0) {
					Location l = new Location((x * 2) - 1, y * 2);
					Room dest = findActualRoom(new Room(new Location((x * 2) - 2, y * 2)));
					addDoors(source, l, dest);
				}
				if (x + 1 < 4) {
					Location l = new Location((x * 2) + 1, y * 2);
					Room dest = findActualRoom(new Room(new Location((x * 2) + 2, y * 2)));
					addDoors(source, l, dest);
				}
				if (y - 1 >= 0) {
					Location l = new Location(x * 2, (y * 2) - 1);
					Room dest = findActualRoom(new Room(new Location(x * 2, (y * 2) - 2)));
					addDoors(source, l, dest);
				}
				if (y + 1 < 4) {
					Location l = new Location(x * 2, (y * 2) + 1);
					Room dest = findActualRoom(new Room(new Location(x * 2, (y * 2) + 2)));
					addDoors(source, l, dest);
				}
			}
		}
	}

	/**
	 * Returns true if this object has been successfully saved to a txt file; false
	 * otherwise.
	 *
	 * @return true/false
	 */
	public boolean writeToFile() {
		try {
			File file = new File(R.Strings.FILE_LOCATION + R.Strings.SAVE_FILE);
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

			out.writeObject(this);

			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
