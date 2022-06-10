/*
 * TCSS 360 Trivia Maze
 */

package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Room class stores information about an individual Room
 *
 * @author Zhaoyu Xu
 * @version 2.1
 */
public class Room implements Serializable {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;
	
	/** A private final variable for the room location*/
	private final Location myRoomLocation;

	/**
	 * Constructor for the room object.
	 * 
	 * @param theLocation location of the room
	 */
	public Room(final Location theLocation) {
		this.myRoomLocation = Objects.requireNonNull(theLocation);
	}

	/**
	 * Returns true if the specified room is equivalent to this room, and false if otherwise. Two rooms are equivalent if they have exactly equivalent locations.
	 * 
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object theObject) {
		if (theObject == null) {
			throw new NullPointerException();
		}
		Room obj = (Room) theObject;
		return obj.myRoomLocation.equals(this.myRoomLocation);
	}

	/**
	 * Returns the location of this room.
	 * @return myRoomLocation
	 */
	public Location getRoomLocation() {
		return this.myRoomLocation;
	}

	/**
	 * Returns an integer hash code for this room. 
	 * 
	 * @return the hash value of this room
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * this.myRoomLocation.hashCode();
		return result;
	}
}
