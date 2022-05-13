package model;

public class Door {
    private Question myQuestion;
    private DoorStatus doorStatus;

    public Door(Question question) {
      myQuestion = question;
      doorStatus = DoorStatus.LOCKED;
    }

    public DoorStatus getStatus() {
      return doorStatus;
    }

    public boolean isOpen() {
      return doorStatus == DoorStatus.OPEN;
    }

    public boolean isLocked() {
      return doorStatus == DoorStatus.LOCKED;
    }

    public boolean isGone() {
      return doorStatus == DoorStatus.GONE;
    }

    public void open(String userAnswer) {
      if (doorStatus == DoorStatus.LOCKED) {
        if (myQuestion.validate(userAnswer)) {
          doorStatus = DoorStatus.OPEN;
        }
        else {
          doorStatus = DoorStatus.GONE;
        }
      }
    }

    public Question getQuestion() {
      return myQuestion;
    }

    public cheatOpen() {
      doorStatus = DoorStatus.OPEN;
    }
}
