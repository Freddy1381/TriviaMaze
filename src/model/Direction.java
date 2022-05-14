package model;

public enum Direction {
  NORTH(0, Direction.SOUTH),
  EAST(1, Direction.WEST),
  SOUTH(2, Direction.NORTH),
  WEST(3, Direction.EAST);

  private int index;
  private Direction opposite;

  Direction(int i, Direction d) {
    index = i;
    opposite = d;
  }

  public int getIndex() {
    return index;
  }

  public Direction getOpposite() {
    return opposite;
  }

}
