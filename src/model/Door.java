/*
 * TCSS 360 Trivia Maze
 */

package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Door class that connects all the rooms.
 *
 * @author Zhaoyu Xu
 * @version 2.1
 */
public class Door implements Serializable {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

	/** The location of the door in the maze. */
	private final Location myLocation;

	/** The status of the door. */
	private DoorStatus myDoorStatus;

	/** Each door has been assign a question. */
	private Question myQuestion;

	/**
	 * Creates a door object according to the given location. Door status is
	 * defaulted to locked.
	 *
	 * @param theLocation location of the door.
	 */
	public Door(final Location theLocation) {
		this.myLocation = Objects.requireNonNull(theLocation);
		this.myDoorStatus = DoorStatus.LOCKED;
	}

	/**
	 * Returns true if the specified door is equivalent to this door, and false
	 * if otherwise. Two doors are equivalent if they have exactly equivalent
	 * locations.
	 *
	 * @return true/false.
	 */
	@Override
	public boolean equals(final Object theObject) {
		if (theObject == null) {
			throw new NullPointerException();
		}
		Door obj = (Door) theObject;
		return (obj.myLocation.equals(this.myLocation));
	}

	/**
	 * Getter for location of the door.
	 *
	 * @return current door location.
	 */
	public Location getDoorLocation() {
		return myLocation;
	}

	/**
	 * Getter for door question.
	 *
	 * @return the assigned question of door.
	 */
	public Question getQuestion() {
		return myQuestion;
	}

	/**
	 * Getter for door status.
	 *
	 * @return current door status.
	 */
	public DoorStatus getStatus() {
		return myDoorStatus;
	}

	/**
	 * Returns an integer hash code for this door
	 *
	 * @return the hash value of this door.
	 */
	@Override
	public int hashCode() {
		int result = 0;
		result = this.myLocation.hashCode();
		return result;
	}

	/**
	 * Checks if the door is dead.
	 *
	 * @return true if door is dead, false if other wise.
	 */
	public boolean isDEAD() {
		return myDoorStatus == DoorStatus.DEAD;
	}

	/**
	 * Checks if the door is locked.
	 *
	 * @return true if door is locked, false if other wise.
	 */
	public boolean isLocked() {
		return myDoorStatus == DoorStatus.LOCKED;
	}

	/**
	 * Checks if the door is open.
	 *
	 * @return true if door is open, false if other wise.
	 */
	public boolean isOpen() {
		return myDoorStatus == DoorStatus.OPEN;
	}

	/**
	 * Assign the given question to door.
	 *
	 * @param theQuestion question being assigned to door.
	 */
	public void setQuestion(final Question theQuestion) {
		this.myQuestion = Objects.requireNonNull(theQuestion);
	}

	/**
	 * Setter for door status.
	 *
	 * @param theStatus status being set on door.
	 */
	public void setStatus(DoorStatus theStatus) {
		myDoorStatus = Objects.requireNonNull(theStatus);
	}
}
