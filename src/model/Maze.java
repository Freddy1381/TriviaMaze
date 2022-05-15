package model;

import java.util.ArrayList;
import java.util.Collections;

public class Maze {
    private Room myStartRoom;
    private Room myEndRoom;
    private Room myCurrentRoom;
    private ArrayList<Question> myQuestions;
    private Room[][] myMaze;

    public Maze(int rows, int cols, ArrayList<Question> theQuestions) {
      myQuestions = theQuestions;
      Collections.shuffle(myQuestions);
      myMaze = new Room[rows][cols];

      for (int i=0; i<rows; i++) {
        for (int j=0; j<cols; j++) {
          myMaze[i][j] = new Room(i, j);
        }
      }

      connectRooms();

      myStartRoom = myMaze[0][0];
      myCurrentRoom = myMaze[0][0];
      myEndRoom = myMaze[myMaze.length - 1][myMaze[0].length - 1];
    }

    public boolean gameWon() {
      return myCurrentRoom.equals(myEndRoom);
    }

    public Room getStartRoom() {
      return myStartRoom;
    }

    public Room getEndRoom() {
      return myEndRoom;
    }

    public Room getCurrentRoom() {
      return myCurrentRoom;
    }

    public void setCurrentRoom(int r, int c) {
      myCurrentRoom = myMaze[r][c];
    }

    public Room[][] getMaze() {
      return myMaze;
    }

    public boolean gameLost() {
      resetExplored();
      return !(hasPathToEnd(myCurrentRoom));
    }

    public void cheatWin() {
      myCurrentRoom = myEndRoom;
    }

    // call this method before checking canMove. Will return true if the unlock method successfully
    // unlocked this door, or else it will return false.
    public boolean unlock(Direction direction, String userAnswer) {
      Door door = myCurrentRoom.getDoor(direction);
      if (door != null && !door.isGone()) {
        if (door.isOpen())
          return true;

        door.open(userAnswer);
        return door.isOpen();
      }
      return false;
    }

    // true if this move is within bounds of the maze AND if this door is unlocked currently,
    // false otherwise
    public boolean canMove(Direction direction) {
      Door door = myCurrentRoom.getDoor(direction);
      return door != null && door.isOpen();
    }

    // to be used after canMove(direction) has already been called and returns true. Else,
    // this method will do nothing.
    public void movePlayer(Direction direction) {
      if (canMove(direction)) {
        if (direction == Direction.NORTH) {
          Room above = getRoomAbove(myCurrentRoom);
          myCurrentRoom = above;
        }
        else if (direction == Direction.EAST) {
          Room right = getRoomRight(myCurrentRoom);
          myCurrentRoom = right;
        }
        else if (direction == Direction.SOUTH) {
          Room below = getRoomBelow(myCurrentRoom);
          myCurrentRoom = below;
        }
        else {
          Room left = getRoomLeft(myCurrentRoom);
          myCurrentRoom = left;
        }
      }
    }

    private void connectRooms() {
      int questionIndex = 0;
      for (int i=0; i<rows-1; i++) {
        Door door = new Door(myQuestions[questionIndex % myQuestions.size()]);
        Room above = myMaze[i][0];
        Room below = myMaze[i+1][0];
        above.addDoor(Direction.SOUTH, door);
        below.addDoor(Direction.NORTH, door);
        questionIndex++;
      }

      for (int i=0; i<cols-1; i++) {
        Door door = new Door(myQuestions[questionIndex % myQuestions.size()]);
        Room left = myMaze[0][i];
        Room right = myMaze[0][i+1];
        left.addDoor(Direction.EAST, door);
        right.addDoor(Direction.WEST, door);
        questionIndex++;
      }

      for (int r=1; r<rows; r++)
        for (int c=1; c<cols; c++) {
          Door door1 = new Door(myQuestions[questionIndex % myQuestions.size()]);
          questionIndex++;
          Door door2 = new Door(myQuestions[questionIndex % myQuestions.size()]);
          questionIndex++;
          Room above = myMaze[r-1][c];
          Room left = myMaze[r][c-1];
          Room current = myMaze[r][c];
          current.addDoor(Direction.NORTH, door1);
          above.addDoor(Direction.SOUTH, door1);
          current.addDoor(Direction.WEST, door2);
          left.addDoor(Direction.EAST, door2);
        }
    }

    private Room getRoomAbove(Room currentRoom) {
      int row = currentRoom.getRow();
      int col = currentRoom.getCol();
      return myMaze[row-1][col];
    }

    private Room getRoomBelow(Room currentRoom) {
      int row = currentRoom.getRow();
      int col = currentRoom.getCol();
      return myMaze[row+1][col];
    }

    private Room getRoomLeft(Room currentRoom) {
      int row = currentRoom.getRow();
      int col = currentRoom.getCol();
      return myMaze[row][col-1];
    }

    private Room getRoomRight(Room currentRoom) {
      int row = currentRoom.getRow();
      int col = currentRoom.getCol();
      return myMaze[row][col+1];
    }

    private boolean hasPathToEnd(Room room) {
      if (!room.getExplored()) {
        if (room.equals(myEndRoom))
          return true;

        room.setExplored(true);

        Door door = room.getDoor(Direction.NORTH);
        if (door != null && !door.isGone()) {
          Room above = getRoomAbove(room);
          if (hasPathToEnd(above))
            return true;
        }

        door = room.getDoor(Direction.EAST);
        if (door != null && !door.isGone()) {
          Room right = getRoomRight(room);
          if (hasPathToEnd(right))
            return true;
        }

        door = room.getDoor(Direction.SOUTH);
        if (door != null && !door.isGone()) {
          Room below = getRoomBelow(room);
          if (hasPathToEnd(below))
            return true;
        }

        door = room.getDoor(Direction.WEST);
        if (door != null && !door.isGone()) {
          Room left = getRoomLeft(room);
          if (hasPathToEnd(left))
            return true;
        }
        return false;

      }
      return false;

    }

    private void resetExplored() {
      for (int i=0; i<myMaze.length; i++)
        for (int j=0; j<myMaze[0].length; j++)
          myMaze[i][j].setExplored(false);
    }


}
