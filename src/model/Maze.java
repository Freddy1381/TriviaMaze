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
	
	private void addDoors(final Room theSource, final Direction theDirection) {
		myAdjacentRooms.get(theSource).add(new Door(theSource, theDirection));
	}
	
	private void setupRooms() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				final Room source = new Room(new Location(x, y));
				
				myAdjacentRooms.put(source, new LinkedList<Door>());
				
				if (x - 1 >= 0) {
					addDoors(source, Direction.LEFT);
				}
				if (x + 1 < 4) {
					addDoors(source, Direction.RIGHT);
				}
				if (y - 1 >= 0) {
					addDoors(source, Direction.UP);
				}
				if (y + 1 < 4) {
					addDoors(source, Direction.DOWN);
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
	
	public Set<Room> getRoomSet() {
		return myAdjacentRooms.keySet();
	}
	
	public Map<Room, List<Door>> getMap() {
		return myAdjacentRooms;
	}

	public void setCurrentRoom(Room theRoom) {
		myCurrentRoom = theRoom;
	}

	public void cheatWin() {
		myCurrentRoom = myEndRoom;
	}
	
	private boolean canTraverseMaze(final Room theRoom) {
		Set<Room> visited = new LinkedHashSet<Room>();
		Stack<Room> stack = new Stack<Room>();
		stack.push(theRoom);
		while (!stack.isEmpty()) {
			Room vertex = stack.pop();
			if (!visited.contains(vertex)) {
				visited.add(vertex);
				for (Door r : this.myAdjacentRooms.get(vertex)) {
					stack.push(r.getDest());
				}
			}
		}
		if (visited.contains(myEndRoom)) {
			return true;
		}
		return false;
	}
	
	public boolean[] checkSurroundings(final Room theRoom) {
		boolean[] result = {true, true, true, true};
		
		
		
		return result;
	}

	public static void main(String args[]) {
		Maze maze = new Maze();
		System.out.println(maze.toString());
	}
}
