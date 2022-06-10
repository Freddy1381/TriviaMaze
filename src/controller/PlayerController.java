/*
 * TCSS 360 Trivia Maze
 */

package controller;

import model.Direction;
import model.Location;
import model.Maze;
import model.Room;

/**
 * PlayerController is an object that only moves the player when called by a
 * button action. It will then update the maze object.
 * 
 * @author Zhaoyu Xu
 * @version 2.1
 */
public class PlayerController {

	/** The maze object. */
	private static Maze myMaze;

	/**
	 * Initializes the playercontroller object.
	 * 
	 * @param theMaze maze that is being updated.
	 */
	public PlayerController(Maze theMaze) {
		// TODO Auto-generated constructor stub
		myMaze = theMaze;
	}

	/**
	 * Moves the player location on the maze according to the direction.
	 * 
	 * @param theDirection the movement direction
	 */
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
