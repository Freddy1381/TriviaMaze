package view;

import java.awt.GridLayout;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Direction;
import model.Door;
import model.Location;

import model.Maze;
import model.Room;
import res.R;

public class MazePanel extends JPanel {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;
	
	private final Set<Room> myRooms;
	
	private final Room myStartRoom;
	
	private final Room myEndRoom;
	
	private final Room myCurrentRoom;
	
	private final Map<Room, List<Door>> myMap;
	
	public MazePanel(Maze theMaze) {
		super();
		
		myRooms = theMaze.getRoomSet();
		myStartRoom = theMaze.getStartRoom();
		myEndRoom = theMaze.getEndRoom();
		myCurrentRoom = new Room(new Location(0, 0));
		myMap = theMaze.getMap();
		
		createPanel();
	}
	
	private void createPanel() {
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				JLabel lbl = new JLabel("");
				if (x % 2 == 0 && y % 2 == 0) {
					Room r = findActualRoom(new Room(new Location(x / 2, y / 2)));
					if (r.equals(myCurrentRoom)) {
						lbl.setIcon(R.ImageIcons.CURRENT_ROOM);
					} else if (r.equals(myStartRoom)) {
						lbl.setIcon(R.ImageIcons.START_ROOM);
					} else if (r.equals(myEndRoom)) {
						lbl.setIcon(R.ImageIcons.EXIT_ROOM);
					} else {
						lbl.setIcon(R.ImageIcons.EMPTY_ROOM);
					}
				} else if (x % 2 == 1 && y % 2 == 1){
					lbl.setIcon(R.ImageIcons.BLANK);
				} else if ((x % 2 == 1 || y % 2 == 1) && y % 2 == 1) {
					Room source = new Room(new Location(x / 2, (y - 1) / 2));
					Room dest = new Room(new Location(x / 2, (y + 1) / 2));
					Door d = findActualDoor(source, new Door(source, dest, Direction.UP));
					if (d.isLocked()) {
						lbl.setIcon(R.ImageIcons.LOCKED_DOOR_H);
					} else if (d.isOpen()) {
						lbl.setIcon(R.ImageIcons.UNLOCK_DOOR_H);
					}
				} else if ((x % 2 == 1 || y % 2 == 1) && y % 2 == 0) {
					Room source = new Room(new Location((x - 1) / 2, y / 2));
					Room dest = new Room(new Location((x + 1) / 2, y / 2));
					Door d = findActualDoor(source, new Door(source, dest, Direction.DOWN));
					if (d.isLocked()) {
						lbl.setIcon(R.ImageIcons.LOCKED_DOOR_V);
					} else if (d.isOpen()) {
						lbl.setIcon(R.ImageIcons.UNLOCK_DOOR_V);
					}
				}
				this.add(lbl);
			}
		}
		
		this.setLayout(new GridLayout(7, 7));
		this.setVisible(true);
		this.setBackground(R.Colors.MMF_BG);
	}
	
	private Room findActualRoom(final Room theRoom) {
		Room result = null;
		for (Iterator<Room> it = myRooms.iterator(); it.hasNext();) {
			Room r = it.next();
			if (r.equals(theRoom)) {
				result = r;
			}
		}
		return result;
	}
	
	private Door findActualDoor(final Room theRoom, final Door theDoor) {
		Door result = theDoor;
		List<Door> temp = myMap.get(theRoom);
		for (int i = 0; i < temp.size(); i ++) {
			Door d = temp.get(i);
			if (d.equals(theDoor)) {
				result.setStatus(d.getStatus());
			}
		}
		return result;
	}
}
