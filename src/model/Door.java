package model;

public class Door {
    private Question myQuestion;
    private DoorStatus myDoorStatus;

    public Door(Question theQuestion) {
      myQuestion = theQuestion;
      myDoorStatus = DoorStatus.LOCKED;
    }

    public DoorStatus getStatus() {
      return myDoorStatus;
    }

    public boolean isOpen() {
      return myDoorStatus == DoorStatus.OPEN;
    }

    public boolean isLocked() {
      return myDoorStatus == DoorStatus.LOCKED;
    }

    public boolean isGone() {
      return myDoorStatus == DoorStatus.GONE;
    }

    public void open(String userAnswer) {
      if (myDoorStatus == DoorStatus.LOCKED) {
        if (myQuestion.validate(userAnswer)) {
          myDoorStatus = DoorStatus.OPEN;
        }
        else {
          myDoorStatus = DoorStatus.GONE;
        }
      }
    }

    public Question getQuestion() {
      return myQuestion;
    }

    public cheatOpen() {
      myDoorStatus = DoorStatus.OPEN;
    }
}
