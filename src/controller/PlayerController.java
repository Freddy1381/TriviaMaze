package controller;

import model.Direction;
import model.Location;
import model.Maze;
import model.Room;

public class PlayerController {

	private static Maze myMaze;

	public PlayerController(Maze theMaze) {
		// TODO Auto-generated constructor stub
		myMaze = theMaze;
	}

	public void movePlayer(Direction theDirection) {
		// TODO Auto-generated method stub
		Location current = myMaze.getCurrentRoom().getRoomLocation();
		Room moveTo = null;
		Location goTo = null;
		switch (theDirection) {
		case UP:
			goTo = new Location(current.getX(), current.getY() - 2);
			break;
		case DOWN:
			goTo = new Location(current.getX(), current.getY() + 2);
			break;
		case LEFT:
			goTo = new Location(current.getX() - 2, current.getY());
			break;
		case RIGHT:
			goTo = new Location(current.getX() + 2, current.getY());
			break;
		}
		moveTo = myMaze.findActualRoom(new Room(goTo));
		myMaze.setCurrentRoom(moveTo);
	}
	
}
