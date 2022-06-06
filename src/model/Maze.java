package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import res.R;

public class Maze implements Serializable {
	
	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;
	private final Room myStartRoom;
	private final Room myEndRoom;
	private Room myCurrentRoom;
	private Queue<Question> myQuestionList;
	private HashSet<Question> myUsedQuestions;
	
	private final Map<Room, List<Door>> myAdjacentRooms;
	
	public Maze(final Queue<Question> theList) {
		myQuestionList = theList;
		myAdjacentRooms = new HashMap<>();
		myUsedQuestions = new HashSet<Question>();
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
	
	private void addDoors(final Room theSource, final Location theLocation, final Room theDest) {
		Door r = new Door(theLocation);
		final Question q = getQuestionFromGetter();
		r.setQuestion(q);
		if (!hasRoom(theSource) || !hasRoom(theDest)) {
			throw new NoSuchElementException("No Such Room!");
		}
		myAdjacentRooms.get(theSource).add(r);
	}
	
	private Question getQuestionFromGetter() {
		if (myQuestionList.isEmpty()) {
			myQuestionList.addAll(myUsedQuestions);
			myUsedQuestions.clear();
		}
		Question temp = myQuestionList.poll();
		myUsedQuestions.add(temp);
		return temp;
	}
	
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
	
	public boolean hasRoom(final Room theRoom) {
		return myAdjacentRooms.containsKey(theRoom);
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
	
	public List<Door> getDoorList(final Room theRoom) {
		return myAdjacentRooms.get(theRoom);
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
	
	public void setAllDoorsDead(final Room theRoom) {
		for (Door r : myAdjacentRooms.get(theRoom)) {
			r.setStatus(DoorStatus.DEAD);
		}
	}
	
	public boolean canTraverseMaze(final Room theRoom) {
		Set<Room> visited = new LinkedHashSet<Room>();
		Stack<Room> stack = new Stack<Room>();
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
						
						//Up
						if (roomX == doorX && roomY - 1 == doorY) {
							temp = new Room(new Location(roomX, roomY - 2));
						}
						//Down
						if (roomX == doorX && roomY + 1 == doorY) {
							temp = new Room(new Location(roomX, roomY + 2));
						}
						//Left
						if (roomX - 1 == doorX && roomY == doorY) {
							temp = new Room(new Location(roomX - 2, roomY));
						}
						//Right
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
	
	public Room findActualRoom(final Room theRoom) {
		Room result = null; 
		for (Iterator<Room> it = this.getRoomSet().iterator(); it.hasNext();) {
			Room r = it.next();
			if (r.equals(theRoom)) {
				result = r;
			}
		}
		return result;
	}
	
	public Door findDoor(final Direction theDirection) {
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
		for (int i = 0; i < tempList.size(); i++) {
			Door d = tempList.get(i);
			if (d.equals(temp)) {
				result = d;
			}
		}
		return result;
	}
	
	public Door findDuplicateDoor(final Direction theDirection) {
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
		for (int i = 0; i < tempList.size(); i++) {
			Door d = tempList.get(i);
			if (d.equals(temp)) {
				result = d;
			}
		}
		return result;
	}
	
	public boolean isAtExit() {
		return this.myCurrentRoom.equals(this.myEndRoom);
	}
	
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
