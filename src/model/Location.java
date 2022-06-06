package model;

import java.io.Serializable;

public class Location implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int myX;
	private final int myY;

	public Location(final int theX, final int theY) {
		this.myX = theX;
		this.myY = theY;
	}

	public Location( final Location theLocation) {
		this.myX = theLocation.getX();
		this.myY = theLocation.getY();
	}

	public int getX() {
		return this.myX;
	}

	public int getY() {
		return this.myY;
	}

	@Override
	public boolean equals(final Object theObject) {
		if (this.myX == ((Location) theObject).getX() && this.myY == ((Location) theObject).getY()) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int xPrime = 27;
		final int yPrime = 26;
		int result = 0;
		result = xPrime * this.getX() + yPrime * this.getY();
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(myX);
		builder.append(", ");
		builder.append(myY);
		builder.append("]");
		return builder.toString();
	}
}
