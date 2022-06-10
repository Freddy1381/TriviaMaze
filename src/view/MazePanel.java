/*
 * TCSS 360 Trivia Maze
 */

package view;

import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Door;
import model.Location;
import model.Maze;
import model.Room;
import res.R;

/**
 * MazePanel provide the display panel for maze object.
 *
 * @author Zhaoyu Xu
 * @author Gurleen Sandhu
 * @version 2.1
 */
public class MazePanel extends JPanel {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

	/** The private final variable that will hold the maze object. */
	private final Maze myMaze;

	/** The private final variable that will hold the start room of the maze. */
	private final Room myStartRoom;

	/** The private final variable that will hold the end room of the maze. */
	private final Room myEndRoom;

	/** The private final variable that will hold the current room of the maze. */
	private final Room myCurrentRoom;

	/**
	 * The private final variable that will hold the map with all rooms and their
	 * adjacent doors.
	 */
	private final Map<Room, List<Door>> myMap;

	/**
	 * Constructor for maze panel.
	 *
	 * @param theMaze the maze object
	 */
	public MazePanel(Maze theMaze) {
		super();

		myMaze = theMaze;
		myStartRoom = theMaze.getStartRoom();
		myEndRoom = theMaze.getEndRoom();
		myCurrentRoom = theMaze.getCurrentRoom();
		myMap = theMaze.getMap();

		createPanel();
	}

	/**
	 * Setup the maze panel.
	 */
	private void createPanel() {
		for (int y = 0; y < 7; y++) {
			for (int x = 0; x < 7; x++) {
				JLabel lbl = new JLabel("");
				if (x % 2 == 0 && y % 2 == 0) {
					Room r = myMaze.findActualRoom(new Room(new Location(x, y)));
					if (r.equals(myCurrentRoom)) {
						lbl.setIcon(R.ImageIcons.CURRENT_ROOM);
					} else if (r.equals(myStartRoom)) {
						lbl.setIcon(R.ImageIcons.START_ROOM);
					} else if (r.equals(myEndRoom)) {
						lbl.setIcon(R.ImageIcons.EXIT_ROOM);
					} else {
						lbl.setIcon(R.ImageIcons.EMPTY_ROOM);
					}
				} else if (x % 2 == 1 && y % 2 == 1) {
					lbl.setIcon(R.ImageIcons.BLANK);
				} else if ((x % 2 == 1 || y % 2 == 1) && y % 2 == 1) {
					final Room adjRoom = new Room(new Location(x, y - 1));
					final Location roomLocation = new Location(x, y);
					Door d = findActualDoor(adjRoom, roomLocation);
					if (d.isLocked()) {
						lbl.setIcon(R.ImageIcons.LOCKED_DOOR_V);
					} else if (d.isOpen()) {
						lbl.setIcon(R.ImageIcons.UNLOCK_DOOR_V);
					} else if (d.isDEAD()) {
						lbl.setIcon(R.ImageIcons.DEAD_DOOR_V);
					}
				} else if ((x % 2 == 1 || y % 2 == 1) && y % 2 == 0) {
					final Room adjRoom = new Room(new Location(x - 1, y));
					final Location roomLocation = new Location(x, y);
					Door d = findActualDoor(adjRoom, roomLocation);
					if (d.isLocked()) {
						lbl.setIcon(R.ImageIcons.LOCKED_DOOR_H);
					} else if (d.isOpen()) {
						lbl.setIcon(R.ImageIcons.UNLOCK_DOOR_H);
					} else if (d.isDEAD()) {
						lbl.setIcon(R.ImageIcons.DEAD_DOOR_H);
					}
				}
				this.add(lbl);
			}
		}

		this.setLayout(new GridLayout(7, 7));
		this.setVisible(true);
		this.setBackground(R.Colors.BG);
	}

	/**
	 * Returns the actual door inside the maze.
	 *
	 * @param theRoom     the source room
	 * @param theLocation the door location
	 * @return the actual door in the maze
	 */
	private Door findActualDoor(final Room theRoom, final Location theLocation) {
		Door result = null;
		List<Door> temp = myMap.get(theRoom);
		for (Door d : temp) {
			if (d.equals(new Door(theLocation))) {
				result = d;
			}
		}
		return result;
	}
}
