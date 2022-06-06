package model;

import java.io.Serializable;

public class Door implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Location myLocation;
	private DoorStatus myDoorStatus;
	private Question myQuestion;
	
	public Door(final Location theLocation) {
		this.myLocation = theLocation;
		this.myDoorStatus = DoorStatus.LOCKED;
	}

	public DoorStatus getStatus() {
		return myDoorStatus;
	}
	
	public Location getDoorLocation() {
		return myLocation;
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
	
	public void setQuestion(final Question theQuestion) {
		this.myQuestion = theQuestion;
	}
	
	public Question getQuestion() {
		return myQuestion;
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
		return (obj.myLocation.equals(this.myLocation));
	}
	
	@Override
	public int hashCode() {
		int result = 0;
		result =  this.myLocation.hashCode();
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Door at: ");
		builder.append(myLocation.toString());
		builder.append(". ");
		builder.append("Question is: ");
		builder.append(this.getQuestion().toString());
		return builder.toString();
	}
}
