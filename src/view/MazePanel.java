package view;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Door;
import model.Location;
import model.Maze;
import model.Room;
import res.R;

public class MazePanel extends JPanel implements PropertyChangeListener{

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;
	
	private final Maze myMaze;
	
	private final Room myStartRoom;
	
	private final Room myEndRoom;
	
	private final Room myCurrentRoom;
	
	private final Map<Room, List<Door>> myMap;
	
	public MazePanel(Maze theMaze) {
		super();
		
		myMaze = theMaze;
		myStartRoom = theMaze.getStartRoom();
		myEndRoom = theMaze.getEndRoom();
		myCurrentRoom = theMaze.getCurrentRoom();
		myMap = theMaze.getMap();
		
		createPanel();
	}
	
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
				} else if (x % 2 == 1 && y % 2 == 1){
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
		this.setBackground(R.Colors.MMF_BG);
	}
	
	private Door findActualDoor(final Room theRoom, final Location theLocation) {
		Door result = null;
		List<Door> temp = myMap.get(theRoom);
		for (int i = 0; i < temp.size(); i++) {
			Door d = temp.get(i);
			if (d.equals(new Door(theLocation))) {
				result = d;
			}
		}
		return result;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
