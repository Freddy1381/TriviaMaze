package model;

public class Room {
    private int row;
    private int col;
    private boolean explored;
    Door[] doors;

    public Room(int r, int c) {
      row = r;
      col = c;
      doors = new Door[4];
      explored = false;
    }

    public int getRow() {
      return row;
    }

    public int getCol() {
      return col;
    }

    public boolean equals(Object o) {
      Room r = (Room)o;
      return (row == r.row && col == r.col);
    }

    public void addDoor(Direction direction, Door door) {
      int index = direction.getIndex();
      doors[index] = door;
    }

    public door getDoor(Direction direction) {
      return doors[direction.getIndex()];
    }

    public boolean getExplored() {
      return explored;
    }

    public void setExplored(boolean b) {
      explored = b;
    }

}
