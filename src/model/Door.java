package model;

public class Door {
	
	private Room mySource, myDest;
	private DoorStatus myDoorStatus;

	public Door(Room theSource, Room theDest) {
		mySource = theSource;
		myDest = theDest;
		myDoorStatus = DoorStatus.LOCKED;
	}

	public DoorStatus getStatus() {
		return myDoorStatus;
	}
	
	public Room getSource() {
		return mySource;
	}
	
	public Room getDest() {
		return myDest;
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
		return (obj.mySource.equals(this.myDest) && obj.myDest.equals(this.mySource));
	}
	
	@Override
	public int hashCode() {
		int result = 0;
		result =  this.mySource.hashCode() + this.myDest.hashCode();
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Door to: ");
		builder.append(myDest.toString());
		builder.append(". ");
		return builder.toString();
	}
}
