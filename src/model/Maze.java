package model;

import java.util.*;
import java.util.Map;
import java.io.Serializable;

public class Maze implements Serializable {
	
	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;
	private final Room myStartRoom;
	private final Room myEndRoom;
	private Room myCurrentRoom;
	
	private Map<Room, List<Door>> myAdjacentRooms;
	
	public Maze() {
		myAdjacentRooms = new HashMap<>();
		setupRooms();
		
		myStartRoom = new Room(new Location(0, 0));
		myEndRoom = new Room(new Location(3, 3));
	}
	
	private void addDoors(Room theSource, Room theDest) {
		myAdjacentRooms.get(theSource).add(new Door(theSource, theDest));
	}
	
	private void setupRooms() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				final Room source = new Room(new Location(x, y));
				
				myAdjacentRooms.put(source, new LinkedList<Door>());
				
				if (x - 1 >= 0) {
					addDoors(source, new Room(new Location(x - 1, y)));
				}
				if (x + 1 < 4) {
					addDoors(source, new Room(new Location(x + 1, y)));
				}
				if (y - 1 >= 0) {
					addDoors(source, new Room(new Location(x, y - 1)));
				}
				if (y + 1 < 4) {
					addDoors(source, new Room(new Location(x, y + 1)));
				}
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Room r : myAdjacentRooms.keySet()) {
			builder.append(r.toString() + "-> ");
			for (Door d : myAdjacentRooms.get(r)) {
				builder.append(d.toString() + " ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	public boolean gameWon() {
		return myCurrentRoom.equals(myEndRoom);
	}

	public Room getStartRoom() {
		return myStartRoom;
	}

	public Room getEndRoom() {
		return myEndRoom;
	}

	public Room getCurrentRoom() {
		return myCurrentRoom;
	}

	public void setCurrentRoom(Room theRoom) {
		myCurrentRoom = theRoom;
	}

	public void cheatWin() {
		myCurrentRoom = myEndRoom;
	}

	public static void main(String args[]) {
		Maze maze = new Maze();
		System.out.println(maze.toString());
	}
}
