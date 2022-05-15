package model;

public class Room {
    private int myRow;
    private int myCol;
    private boolean myExplored;
    Door[] myDoors;

    public Room(int r, int c) {
      myRow = r;
      myCol = c;
      myDoors = new Door[4];
      myExplored = false;
    }

    public int getRow() {
      return myRow;
    }

    public int getCol() {
      return myCol;
    }

    public boolean equals(Object o) {
      Room r = (Room)o;
      return (myRow == r.myRow && myCol == r.myCol);
    }

    public void addDoor(Direction direction, Door door) {
      int index = direction.getIndex();
      myDoors[index] = door;
    }

    public door getDoor(Direction direction) {
      return myDoors[direction.getIndex()];
    }

    public boolean getExplored() {
      return myExplored;
    }

    public void setExplored(boolean theExplored) {
      myExplored = theExplored;
    }

}
