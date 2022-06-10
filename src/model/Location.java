package model;

import java.io.Serializable;

public class Location implements Serializable {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

	/** The horizontal integer value of a location. */
	private final int myX;

	/** The vertical integer value of a location. */
	private final int myY;

	/**
	 * Initialize a new location object according to the given x and y values.
	 * 
	 * @param theX horizontal integer value of location.
	 * @param theY vertical integer value of location.
	 */
	public Location(final int theX, final int theY) {
		if (theX < 0) {
			throw new IllegalArgumentException("The X value of location can't be less than zero. ");
		}if (theY < 0) {
			throw new IllegalArgumentException("The Y value of location can't be less than zero. ");
		}
		this.myX = theX;
		this.myY = theY;
	}

	/**
	 * Returns true if the specified location is equivalent to this location; false
	 * otherwise. Two locations are equivalent if they have exactly equivalent X and Y
	 * values.
	 * 
	 * @param the object being compared.
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object theObject) {
		if(theObject == null) {
			throw new NullPointerException();
		}
		return this.myX == ((Location) theObject).getX() && this.myY == ((Location) theObject).getY();
	}

	/**
	 * Getter for X value
	 * 
	 * @return horizontal integer value of location.
	 */
	public int getX() {
		return this.myX;
	}

	/**
	 * Getter for Y value
	 * 
	 * @return vertical integer value of location.
	 */
	public int getY() {
		return this.myY;
	}

	/**
	 * Returns an integer hash code for this location.
	 * 
	 * @return the hash value of this location
	 */
	@Override
	public int hashCode() {
		final int xPrime = 27;
		final int yPrime = 26;
		int result = 0;
		result = xPrime * this.getX() + yPrime * this.getY();
		return result;
	}
}
