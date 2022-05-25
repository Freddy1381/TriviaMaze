package model;

public class Room {
	
	private boolean myIsRoomDead;
	private boolean myIsTraversed;
	private final Location myRoomLocation;
	
	public Room(final Location theLocation) {
		this.myRoomLocation = theLocation;
		myIsRoomDead = false;
		myIsTraversed = false;
	}
	
	public boolean isRoomDead() {
		return this.myIsRoomDead;
	}
	
	public boolean isTraversed() {
		return this.myIsTraversed;
	}

	public Location getRoomLocation() {
		return this.myRoomLocation;
	}
	
	@Override
	public boolean equals(final Object theObject) {
		if (this == theObject) {
			return true;
		}
		if (theObject == null || theObject.getClass() != this.getClass()) {
			return false;
		}
		Room obj = (Room) theObject;
		return obj.myRoomLocation.equals(this.myRoomLocation);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * this.myRoomLocation.hashCode();
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Room: ");
		builder.append(myRoomLocation.toString());
		builder.append(" ");
		return builder.toString();
	}
}
