package res;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.ImageIcon;

public class R {

	public static class Colors {
		public static final Color MMF_BG = Color.DARK_GRAY;
		public static final Color MMF_BUTTON_BG = Color.GRAY;
		public static final Color TEXT_COLOR = Color.WHITE;
	}
	
	public static class Fonts {
		public static final Font GAME_TITLE_FONT = new Font("Lucida Sans Typewriter", 
															Font.BOLD | Font.ITALIC, 40);
		public static final Font QUESTION_FONT = new Font("Lucida Sans Typewriter", 
														  Font.BOLD | Font.ITALIC, 15);
		public static final Font BUTTON_FONT = new Font("Lucida Sans Typewriter", 
				  										Font.BOLD | Font.ITALIC, 15);
	}
	
	public static class Strings {
		public static final String GAME_TITLE = "Trivia Maze";
		public static final String MMF_PLAY_GAME_BTN = "New Game";
		public static final String MMF_LOAD_GAME_BTN = "Load Game";
		public static final String MMF_HELP_BTN = "Help";
		public static final String MMF_ABOUT_BTN = "About";
		public static final String MMF_ADD_BTN = "Add Questions";
		public static final String MMF_HELP_TITLE = "Help";
		public static final String MMF_HELP_MSG = "To move the player, use the movement buttons. \n"
												+ "When you attempt to enter a new room, you will need to answer a question. \n"
												+ "If you answer the question incorrectly, that doorway will be locked. \n"
												+ "You win by reaching the exit. If you are unable to make it to the exit "
												+ "due to locked doors, the game is lost. \n" 
												+ "You can use the Hint button to get help on questions. \n" 
												+ "You can use the Save button to save the current game state.";
		public static final String MMF_ABOUT_MSG = "This game is made by Fred, Gurleen, "
												+ "and Chris to test our Teamwork abilities. "
												+ "\nWe hope you have fun while playing our work!";
		public static final String MBP_MOVE_BTN_UP = "Up";
		public static final String MBP_MOVE_BTN_DOWN = "Down";
		public static final String MBP_MOVE_BTN_LEFT = "Left";
		public static final String MBP_MOVE_BTN_RIGHT = "Right";
		public static final String QP_MOVE_BTN_CONFIRM = "Confirm";
		public static final String QP_MOVE_BTN_SAVE = "Save";
		
		public static final String SQL_DATABASE_URL = "jdbc:sqlite:Questions.db";
		public static final String SQL_DATABASE_INSERT_RECORD = "INSERT INTO Questions(ID, Type, Question, CorrectAnswer, Hint, Options) VALUES (?, ?, ?, ?, ?, ?)";
		
		public static final String SAVE_FILE = "Saved.txt";
		public static final String QUESTION_FILE = "Questions.txt";
		public static final String FILE_LOCATION = "files/";
		
		public static final String ERROR_MSG_ILLEGAL_INVOKE = 
                "Only invoke when used as handler for JButton.";
		public static final String SAVE_MSG = "Game saved!";
		public static final String SAVE_IO_MSG = "IO exception encountered while saving!";
		public static final String LOAD_IO_MSG = "IO exception encountered while loading!";
		public static final String LOAD_CLASS_MSG = "Class not found exception encountered while loading!";
		public static final String QUESTION_TEXT = "Question Text";
		public static final String QUESTION_TYPE_SA = "Short Answer";
		public static final String QUESTION_MSG_CORRECT = "Yay! You got that one right!";
		public static final String QUESTION_MSG_WRONG = "Oops, that was incorrect!";
		public static final String GOOD_BYE = "Thank you for playing this game. Good-bye!";
		public static final String GAME_WON_MSG = "Congradulations!\nDo you want to play again?";
		public static final String GAME_LOST_MSG = "Oops, you are trapped!\nDo you want to play again?";
	}
	
	public static class Dimensions {
		public static final Dimension FRAME_SIZE = new Dimension(600, 500);
		public static final Dimension BUTTON_SIZE = new Dimension(100, 50);
		public static final Dimension LABEL_SIZE = new Dimension(225, 40);
		public static final int H_PADDING = 20;
		public static final int V_PADDING = 10;
		public static final int UTILITY_H_GAP = 30;
		public static final int UTILITY_V_GAP = 5;
	}
	
	public static class ImageIcons {
		public static final ImageIcon CURRENT_ROOM = new ImageIcon(R.class.getResource("/images/Player.png"));
		public static final ImageIcon EMPTY_ROOM = new ImageIcon(R.class.getResource("/images/Room.png"));
		public static final ImageIcon EXIT_ROOM = new ImageIcon(R.class.getResource("/images/ExitSign.png"));
		public static final ImageIcon LOCKED_DOOR_V = new ImageIcon(R.class.getResource("/images/ClosedDoorVertical.png"));
		public static final ImageIcon LOCKED_ROOM = new ImageIcon(R.class.getResource("/images/LockedRoom.png"));
		public static final ImageIcon START_ROOM = new ImageIcon(R.class.getResource("/images/StartSign.png"));
		public static final ImageIcon UNLOCK_DOOR_V = new ImageIcon(R.class.getResource("/images/OpenDoorVertical.png"));
		public static final ImageIcon LOCKED_DOOR_H = new ImageIcon(R.class.getResource("/images/ClosedDoorHorizontal.png"));
		public static final ImageIcon UNLOCK_DOOR_H = new ImageIcon(R.class.getResource("/images/OpenDoorHorizontal.png"));
		public static final ImageIcon BLANK = new ImageIcon(R.class.getResource("/images/Blank.png"));
		public static final ImageIcon DEAD_DOOR_H = new ImageIcon(R.class.getResource("/images/DeadDoorHorizontal.png"));
		public static final ImageIcon DEAD_DOOR_V = new ImageIcon(R.class.getResource("/images/DeadDoorVertical.png"));
	}
	
	public static class Files {
		public static final File CORRECT_SOUND = new File("src/sound/Correct.wav");
		public static final File WRONG_SOUND = new File("src/sound/Wrong.wav");
		public static final File START_SOUND = new File("src/sound/GameStart.wav");
		public static final File WON_SOUND = new File("src/sound/GameWon.wav");
		public static final File LOST_SOUND = new File("src/sound/GameLost.wav");
	}
}
