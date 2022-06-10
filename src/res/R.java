/*
 * TCSS 360 Trivia Maze
 */

package res;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.ImageIcon;

/**
 * Resources class.
 *
 * @author Zhaoyu Xu
 * @version 1.0
 */
public class R {

	/**
	 * Resource class for colors.
	 *
	 * @author Zhaoyu Xu
	 * @version 1.0
	 */
	public static class Colors {

		/** General background color. */
		public static final Color BG = Color.DARK_GRAY;

		/** General button color. */
		public static final Color BUTTON_BG = Color.GRAY;

		/** General text color. */
		public static final Color TEXT_COLOR = Color.WHITE;
	}

	/**
	 * Resource class for Dimensions.
	 *
	 * @author Zhaoyu Xu
	 * @version 1.0
	 */
	public static class Dimensions {

		/** General button size. */
		public static final Dimension BUTTON_SIZE = new Dimension(100, 50);

		/** Main menu frame size. */
		public static final Dimension FRAME_SIZE = new Dimension(600, 500);

		/** Main menu frame horizontal padding. */
		public static final int H_PADDING = 20;

		/** Main menu game label size. */
		public static final Dimension LABEL_SIZE = new Dimension(225, 40);

		/** Grid layout horizontal padding. */
		public static final int UTILITY_H_GAP = 30;

		/** Grid layout horizontal padding. */
		public static final int UTILITY_V_GAP = 5;

		/** Main menu frame vertical padding. */
		public static final int V_PADDING = 10;
	}

	/**
	 * Resource class for files.
	 *
	 * @author Zhaoyu Xu
	 * @version 1.0
	 */
	public static class Files {

		/** Sound file for correct answer. */
		public static final File CORRECT_SOUND = new File("src/sound/Correct.wav");

		/** Sound file for lost game. */
		public static final File LOST_SOUND = new File("src/sound/GameLost.wav");

		/** Sound file for start game. */
		public static final File START_SOUND = new File("src/sound/GameStart.wav");

		/** Sound file for game won. */
		public static final File WON_SOUND = new File("src/sound/GameWon.wav");

		/** Sound file for wrong answer. */
		public static final File WRONG_SOUND = new File("src/sound/Wrong.wav");
	}

	/**
	 * Resource class for fonts.
	 *
	 * @author Zhaoyu Xu
	 * @version 1.0
	 */
	public static class Fonts {

		/** Font for all buttons. */
		public static final Font BUTTON_FONT = new Font("Lucida Sans Typewriter", Font.BOLD | Font.ITALIC, 15);

		/** Font for game title on main menu frame. */
		public static final Font GAME_TITLE_FONT = new Font("Lucida Sans Typewriter", Font.BOLD | Font.ITALIC, 40);

		/** Font for all questions. */
		public static final Font QUESTION_FONT = new Font("Lucida Sans Typewriter", Font.BOLD | Font.ITALIC, 15);
	}

	/**
	 * Resource class for image icons.
	 *
	 * @author Zhaoyu Xu
	 * @version 1.0
	 */
	public static class ImageIcons {
		
		/** Image icon for blank space on maze panel. */
		public static final ImageIcon BLANK = new ImageIcon(R.class.getResource("/images/Blank.png"));
		
		/** Image icon for current room on maze panel. */
		public static final ImageIcon CURRENT_ROOM = new ImageIcon(R.class.getResource("/images/Player.png"));
		
		/** Image icon for dead door horizontal on maze panel. */
		public static final ImageIcon DEAD_DOOR_H = new ImageIcon(
				R.class.getResource("/images/DeadDoorHorizontal.png"));
		
		/** Image icon for dead door vertical on maze panel. */
		public static final ImageIcon DEAD_DOOR_V = new ImageIcon(R.class.getResource("/images/DeadDoorVertical.png"));
		
		/** Image icon for empty room on maze panel. */
		public static final ImageIcon EMPTY_ROOM = new ImageIcon(R.class.getResource("/images/Room.png"));
		
		/** Image icon for exit room on maze panel. */
		public static final ImageIcon EXIT_ROOM = new ImageIcon(R.class.getResource("/images/ExitSign.png"));
		
		/** Image icon for locked door horizontal on maze panel. */
		public static final ImageIcon LOCKED_DOOR_H = new ImageIcon(
				R.class.getResource("/images/ClosedDoorHorizontal.png"));
		
		/** Image icon for locked door vertical on maze panel. */
		public static final ImageIcon LOCKED_DOOR_V = new ImageIcon(
				R.class.getResource("/images/ClosedDoorVertical.png"));
		
		/** Image icon for start room on maze panel. */
		public static final ImageIcon START_ROOM = new ImageIcon(R.class.getResource("/images/StartSign.png"));
		
		/** Image icon for open door horizontal on maze panel. */
		public static final ImageIcon UNLOCK_DOOR_H = new ImageIcon(
				R.class.getResource("/images/OpenDoorHorizontal.png"));
		
		/** Image icon for open door vertical on maze panel. */
		public static final ImageIcon UNLOCK_DOOR_V = new ImageIcon(
				R.class.getResource("/images/OpenDoorVertical.png"));
	}

	/**
	 * Resource class for strings.
	 *
	 * @author Zhaoyu Xu
	 * @version 1.0
	 */
	public static class Strings {
		
        /** Error message used when event handlers are implemented with method references. */
		public static final String ERROR_MSG_ILLEGAL_INVOKE = "Only invoke when used as handler for JButton.";
		
		/** The local path of the save files. */
		public static final String FILE_LOCATION = "files/";
		
		/** Game message used when the player has lost game. */
		public static final String GAME_LOST_MSG = "Oops, you are trapped!\nDo you want to play again?";
		
		/** Text in main menu frame game label. */
		public static final String GAME_TITLE = "Trivia Maze";

		/** Game message used when the player has won game. */
		public static final String GAME_WON_MSG = "Congradulations!\nDo you want to play again?";

		/** Game message used when the player has choose to exit game. */
		public static final String GOOD_BYE = "Thank you for playing this game. Good-bye!";
		
		/** Error message used when loading a saved game file with incorrect class name. */
		public static final String LOAD_CLASS_MSG = "Class not found exception encountered while loading!";

		/** Error message used when loading a saved game file encounters IO exception. */
		public static final String LOAD_IO_MSG = "IO exception encountered while loading!";
        
        /** Message on down Button. */
		public static final String MBP_MOVE_BTN_DOWN = "Down";
        
        /** Message on left Button. */
		public static final String MBP_MOVE_BTN_LEFT = "Left";
        
        /** Message on right Button. */
		public static final String MBP_MOVE_BTN_RIGHT = "Right";
        
        /** Message on up Button. */
		public static final String MBP_MOVE_BTN_UP = "Up";
        
        /** Message on about Button. */
		public static final String MMF_ABOUT_BTN = "About";
        
        /** Message shown after about button clicked. */
		public static final String MMF_ABOUT_MSG = "This game is made by Fred, Gurleen, "
				+ "and Chris to test our Teamwork abilities. " + "\nWe hope you have fun while playing our work!";
        
        /** Message on add questions Button. */
		public static final String MMF_ADD_BTN = "Add Questions";
        
        /** Message on help Button. */
		public static final String MMF_HELP_BTN = "Help";
        
        /** Message shown after help button clicked. */
		public static final String MMF_HELP_MSG = "To move the player, use the movement buttons. \n"
				+ "When you attempt to enter a new room, you will need to answer a question. \n"
				+ "If you answer the question incorrectly, that doorway will be locked. \n"
				+ "You win by reaching the exit. If you are unable to make it to the exit "
				+ "due to locked doors, the game is lost. \n"
				+ "You can use the Hint button to get help on questions. \n"
				+ "You can use the Save button to save the current game state.";

        
        /** Message on load Button. */
		public static final String MMF_LOAD_GAME_BTN = "Load Game";
        
        /** Message on new game Button. */
		public static final String MMF_PLAY_GAME_BTN = "New Game";
        
        /** Message on save Button. */
		public static final String QP_MOVE_BTN_SAVE = "Save";
        
        /** The filename of the file containing questions. */
		public static final String QUESTION_FILE = "Questions.txt";
        
        /** Message shown after correctly answered a question. */
		public static final String QUESTION_MSG_CORRECT = "Yay! You got that one right!";
        
        /** Message shown after incorrectly answered a question. */
		public static final String QUESTION_MSG_WRONG = "Oops, that was incorrect!";
        
        /** Message on question panel question text area. */
		public static final String QUESTION_TEXT = "Question Text";
        
        /** String indicating question type is short answer. */
		public static final String QUESTION_TYPE_SA = "Short Answer";
        
        /** String indicating question type is multiple choice. */
		public static final String QUESTION_TYPE_MC = "Multiple Choice";
        
        /** String indicating question type is true false. */
		public static final String QUESTION_TYPE_TF = "True False";
		
		/** The filename of the file containing the saved object. */
		public static final String SAVE_FILE = "Saved.txt";

		/** Error message used when saving a game encounters IO exception. */
		public static final String SAVE_IO_MSG = "IO exception encountered while saving!";
		
		/** Message used when successfully saved a game. */
		public static final String SAVE_MSG = "Game saved!";
		
		/** String object of the prepared statement used to insert questions into sqlite database. */
		public static final String SQL_DATABASE_INSERT_RECORD = "INSERT INTO Questions(ID, Type, Question, CorrectAnswer, Hint, Options) VALUES (?, ?, ?, ?, ?, ?)";
		
		/** The URL used when connecting to sqlite database. */
		public static final String SQL_DATABASE_URL = "jdbc:sqlite:Questions.db";
	}
}
