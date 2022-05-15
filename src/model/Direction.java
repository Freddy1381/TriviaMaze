package model;

public enum Direction {
  NORTH(0),
  EAST(1),
  SOUTH(2),
  WEST(3);

  private int myIndex;

  Direction(int theIndex) {
    myIndex = theIndex;
  }

  public int getIndex() {
    return myIndex;
  }
}
