package res;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

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
	}
	
	public static class Strings {
		public static final String GAME_TITLE = "Trivia Maze";
		public static final String MMF_PLAY_GAME_BTN = "New Game";
		public static final String MMF_LOAD_GAME_BTN = "Load Game";
		public static final String MMF_HELP_BTN = "Help";
		public static final String MMF_ABOUT_BTN = "About";
		public static final String MMF_HELP_TITLE = "Help";
		public static final String MMF_HELP_MSG = "To move the player, use the movement buttons. \n"
												+ "When you attempt to enter a new room, you will need to answer a question. \n"
												+ "If you answer the question incorrectly, that doorway will be locked. \n"
												+ "You win by reaching the exit. If you are unable to make it to the exit "
												+ "due to locked doors, the game is lost.";
		public static final String MMF_ABOUT_MSG = "This game is made by Fred, Gurleen, "
												+ "and Chris to test our Teamwork abilities. "
												+ "\nWe hope you have fun while playing our work!";
		public static final String MBP_MOVE_BTN_UP = "Up";
		public static final String MBP_MOVE_BTN_DOWN = "Down";
		public static final String MBP_MOVE_BTN_LEFT = "Left";
		public static final String MBP_MOVE_BTN_RIGHT = "Right";
		public static final String QP_MOVE_BTN_CONFIRM = "Confirm";
		public static final String QP_MOVE_BTN_SAVE = "Save";
		
		public static final String ERROR_MSG_ILLEGAL_INVOKE = 
                "Only invoke when used as handler for JButton.";
	}
	
	public static class Dimensions {
		public static final Dimension FRAME_SIZE = new Dimension(600, 500);
		public static final Dimension BUTTON_SIZE = new Dimension(140, 80);
		public static final Dimension LABEL_SIZE = new Dimension(225, 40);
		public static final int H_PADDING = 10;
		public static final int V_PADDING = 5;
		
	}
	
	public static class ImageIcons {
		public static final ImageIcon CURRENT_ROOM = new ImageIcon(R.class.getResource("/images/CurrentRoom.png"));
	}
	
	public static class URLs {
		
	}
}
