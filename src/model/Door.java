package model;

public class Door {
	
	private Room mySource;
	private DoorStatus myDoorStatus;
	private Direction myDoorDirection;
	
	public Door(Room theSource, Direction theDirection) {
		mySource = theSource;
		myDoorStatus = DoorStatus.LOCKED;
		myDoorDirection = theDirection;
	}

	public Door(Room theSource, Room theDest, Direction theDirection) {
		// TODO Auto-generated constructor stub
		mySource = theSource;
		myDoorStatus = DoorStatus.LOCKED;
		myDoorDirection = theDirection;
	}

	public DoorStatus getStatus() {
		return myDoorStatus;
	}
	
	public Room getSource() {
		return mySource;
	}
	
	public Direction getDirection() {
		return myDoorDirection;
	}
	
	public void setStatus(DoorStatus theStatus) {
		myDoorStatus = theStatus;
	}

	public boolean isOpen() {
		return myDoorStatus == DoorStatus.OPEN;
	}

	public boolean isLocked() {
		return myDoorStatus == DoorStatus.LOCKED;
	}

	public boolean isDEAD() {
		return myDoorStatus == DoorStatus.DEAD;
	}
	
	@Override
	public boolean equals(final Object theObject) {
		if (this == theObject) {
			return true;
		}
		if (theObject == null || theObject.getClass() != this.getClass()) {
			return false;
		}
		Door obj = (Door) theObject;
		return (obj.mySource.equals(this.mySource) && obj.myDoorDirection.equals(this.myDoorDirection));
	}
	
	@Override
	public int hashCode() {
		int result = 0;
		result =  this.mySource.hashCode();
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Door from: ");
		builder.append(mySource.toString());
		builder.append(". ");
		return builder.toString();
	}

	public Room getDest() {
		// TODO Auto-generated method stub
		return null;
	}
}
