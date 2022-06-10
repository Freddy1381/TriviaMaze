/**
 * TCSS 360 Trivia Maze
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.PlayerController;
import controller.QuestionGetter;
import model.Direction;
import model.Door;
import model.DoorStatus;
import model.Location;
import model.Maze;
import model.Question;
import model.Room;
import model.Sound;
import res.R;

/**
 * NewGameFrame provides the user interface for the game.
 *
 * @author Zhaoyu Xu
 * @author Gurleen Sandhu
 * @version 1.3
 */
public class GameFrame extends JFrame {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

	/** A ToolKit. */
	private static final Toolkit KIT = Toolkit.getDefaultToolkit();

	/** The Dimension of the screen. */
	private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

	/** A private final object that holds the maze object. */
	private final Maze myMaze;

	/** The panel that will display the Maze. */
	private MazePanel myMazePanel;

	/** The panel that will display the Question. */
	private QuestionPanel myQuestionPanel;

	/** The Direction that will store the movement direction. */
	private Direction myCurrentDirection = null;

	/** The question of a specific door. */
	private Question myDoorQuestion = null;

	/** The player controller object. */
	private PlayerController myPC;

	/** The group of movement buttons. */
	private JButton myUpBtn, myDownBtn, myLeftBtn, myRightBtn;

	/** The submit button. */
	private JButton mySubmitBtn;

	/** The group of utility buttons. */
	private JButton mySaveBtn, myHintBtn, myExitBtn;

	/**
	 * Initializes the Game GUI.
	 * 
	 * @param theMaze the maze object
	 */
	public GameFrame(final Maze theMaze) {
		super();

		myMaze = theMaze;
		myPC = new PlayerController(theMaze);
		myMazePanel = new MazePanel(myMaze);
		myQuestionPanel = new QuestionPanel();

		setupGUI();
	}

	/**
	 * Setup the various parts of the GUI.
	 */
	private void setupGUI() {
		layoutComponents();
		finalizeFrame();
		assignActions();

		enableMoveButtons();
	}

	/**
	 * Helper method to create a movement button. 
	 * 
	 * @param theDirection direction of the movement button
	 * @return a movement button
	 */
	private JButton createButtons(final Direction theDirection) {
		final JButton button = new JButton(theDirection.toString());
		button.setFont(R.Fonts.BUTTON_FONT);
		button.setForeground(R.Colors.TEXT_COLOR);
		button.setBackground(R.Colors.BUTTON_BG);
		button.setEnabled(false);

		return button;
	}

	/**
	 * Layout the Swing components.
	 */
	private void layoutComponents() {
		JPanel utilPane = setUpUtilPanel();
		JPanel btnPane = setUpBtnPanel();
		JPanel ansPane = setUpAnsPanel();

		add(utilPane, BorderLayout.NORTH);
		add(myMazePanel, BorderLayout.CENTER);
		add(btnPane, BorderLayout.WEST);
		add(myQuestionPanel, BorderLayout.EAST);
		add(ansPane, BorderLayout.SOUTH);

		setBackground(R.Colors.BG);
		getRootPane().setBorder(BorderFactory.createEmptyBorder(R.Dimensions.V_PADDING, R.Dimensions.H_PADDING,
				R.Dimensions.V_PADDING, R.Dimensions.H_PADDING));
	}

	/**
	 * Finalize this JFrame before making visible.
	 */
	private void finalizeFrame() {
		setTitle(R.Strings.GAME_TITLE);
		pack();
		setResizable(false);
		setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2, SCREEN_SIZE.height / 2 - getHeight() / 2);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Returns the JPanel containing movement buttons.
	 *
	 * @return the JPanel of movement buttons
	 */
	private JPanel setUpBtnPanel() {
		JPanel btnPane = new JPanel();

		GridLayout layout = new GridLayout(3, 3);
		layout.setHgap(R.Dimensions.H_PADDING);
		layout.setVgap(R.Dimensions.V_PADDING);
		btnPane.setLayout(layout);

		JLabel lbl1 = new JLabel("");
		lbl1.setIcon(R.ImageIcons.BLANK);
		btnPane.add(lbl1);
		myUpBtn = createButtons(Direction.UP);
		btnPane.add(myUpBtn);
		JLabel lbl2 = new JLabel("");
		lbl2.setIcon(R.ImageIcons.BLANK);
		btnPane.add(lbl2);
		myLeftBtn = createButtons(Direction.LEFT);
		btnPane.add(myLeftBtn);
		JLabel lbl3 = new JLabel("");
		lbl3.setIcon(R.ImageIcons.BLANK);
		btnPane.add(lbl3);
		myRightBtn = createButtons(Direction.RIGHT);
		btnPane.add(myRightBtn);
		JLabel lbl4 = new JLabel("");
		lbl4.setIcon(R.ImageIcons.BLANK);
		btnPane.add(lbl4);
		myDownBtn = createButtons(Direction.DOWN);
		btnPane.add(myDownBtn);
		JLabel lbl5 = new JLabel("");
		lbl5.setIcon(R.ImageIcons.BLANK);
		btnPane.add(lbl5);

		btnPane.setBackground(R.Colors.BG);
		btnPane.setSize(100, 100);
		return btnPane;
	}

	/**
	 * Returns the JPanel containing the submit button.
	 *
	 * @return the JPanel of submit button
	 */
	private JPanel setUpAnsPanel() {
		JPanel ansPane = new JPanel();

		mySubmitBtn = new JButton("Submit Answer");
		mySubmitBtn.setFont(R.Fonts.BUTTON_FONT);
		mySubmitBtn.setForeground(R.Colors.TEXT_COLOR);
		mySubmitBtn.setBackground(R.Colors.BUTTON_BG);
		mySubmitBtn.setEnabled(false);
		ansPane.add(mySubmitBtn);

		ansPane.setBackground(R.Colors.BG);
		return ansPane;
	}

	/**
	 * Returns the JPanel containing the utility buttons.
	 *
	 * @return the JPanel of utility buttons
	 */
	private JPanel setUpUtilPanel() {
		JPanel utilPane = new JPanel();

		mySaveBtn = new JButton("Save");
		mySaveBtn.setFont(R.Fonts.BUTTON_FONT);
		mySaveBtn.setForeground(R.Colors.TEXT_COLOR);
		mySaveBtn.setBackground(R.Colors.BUTTON_BG);
		mySaveBtn.setEnabled(true);
		myHintBtn = new JButton("Hint");
		myHintBtn.setFont(R.Fonts.BUTTON_FONT);
		myHintBtn.setForeground(R.Colors.TEXT_COLOR);
		myHintBtn.setBackground(R.Colors.BUTTON_BG);
		myHintBtn.setEnabled(false);
		myExitBtn = new JButton("Exit");
		myExitBtn.setFont(R.Fonts.BUTTON_FONT);
		myExitBtn.setForeground(R.Colors.TEXT_COLOR);
		myExitBtn.setBackground(R.Colors.BUTTON_BG);
		myExitBtn.setEnabled(true);

		utilPane.setLayout(new FlowLayout(FlowLayout.CENTER, R.Dimensions.UTILITY_H_GAP, R.Dimensions.UTILITY_V_GAP));
		utilPane.add(mySaveBtn);
		utilPane.add(myHintBtn);
		utilPane.add(myExitBtn);
		utilPane.setBackground(R.Colors.BG);
		return utilPane;
	}

	/**
	 * Called when the player reaches the end of the game by either winning or
	 * losing. The player can then choose to start a new game or exit the game.
	 *
	 * @param theOption decision of next action by player
	 */
	private void endOfGame(int theOption) {
		if (theOption == 0) {
			Queue<Question> questions = null;
			try {
				QuestionGetter getter = new QuestionGetter();
				questions = getter.getQuestionList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Maze maze = new Maze(questions);
			GameFrame window = new GameFrame(maze);
			window.setVisible(true);
			this.setVisible(false);
		} else if (theOption == 1) {
			JOptionPane.showMessageDialog(this, R.Strings.GOOD_BYE);
			System.exit(0);
		} else {
			return;
		}
	}

	/**
	 * Helper method to disable all movement buttons.
	 */
	private void disableMoveButtons() {
		myUpBtn.setEnabled(false);
		myDownBtn.setEnabled(false);
		myLeftBtn.setEnabled(false);
		myRightBtn.setEnabled(false);
	}

	/**
	 * Helper method to enable movement buttons that are available.
	 */
	private void enableMoveButtons() {
		final Room current = myMaze.getCurrentRoom();
		final Location currentLoc = current.getRoomLocation();
		final int x = currentLoc.getX();
		final int y = currentLoc.getY();

		// LEFT
		if (x - 1 >= 0) {
			Door r = myMaze.findDoor(Direction.LEFT);
			myLeftBtn.setEnabled(!r.isDEAD());
		}
		// RIGHT
		if (x + 1 < 7) {
			Door r = myMaze.findDoor(Direction.RIGHT);
			myRightBtn.setEnabled(!r.isDEAD());
		}
		// UP
		if (y - 1 >= 0) {
			Door r = myMaze.findDoor(Direction.UP);
			myUpBtn.setEnabled(!r.isDEAD());
		}
		// DOWN
		if (y + 1 < 7) {
			Door r = myMaze.findDoor(Direction.DOWN);
			myDownBtn.setEnabled(!r.isDEAD());
		}
	}

	/**
	 * Add listeners to any GUI components that require them.
	 */
	private void assignActions() {
		myUpBtn.addActionListener(moveUpAction);
		myDownBtn.addActionListener(moveDownAction);
		myLeftBtn.addActionListener(moveLeftAction);
		myRightBtn.addActionListener(moveRightAction);
		mySubmitBtn.addActionListener(submitAction);

		mySaveBtn.addActionListener(e -> {
			saveGame();
		});

		myHintBtn.addActionListener(e -> {
			if (e.getSource().getClass() != JButton.class) {
				throw new IllegalStateException(R.Strings.ERROR_MSG_ILLEGAL_INVOKE);
			}
			JOptionPane.showMessageDialog(this, myDoorQuestion.getHint());
		});

		myExitBtn.addActionListener(e -> {
			System.exit(0);
		});
	}

	/**
	 * Event handler for the submit Button. This method is implicitly called when
	 * the user presses the "Submit" button.
	 *
	 * @param theEvent the ActionEvent that triggered this method.
	 * @throws Exception
	 * @throws IllegalStateException when the component is not a JButton
	 */
	ActionListener submitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (myQuestionPanel.checkAnswer()) {
				myMaze.findDoor(myCurrentDirection).setStatus(DoorStatus.OPEN);
				myMaze.findDuplicateDoor(myCurrentDirection).setStatus(DoorStatus.OPEN);
				myPC.movePlayer(myCurrentDirection);
				updateMazePanel(myMaze);
				Sound.playSound(R.Files.CORRECT_SOUND);
				JOptionPane.showMessageDialog(myQuestionPanel, R.Strings.QUESTION_MSG_CORRECT);
				if (myMaze.isAtExit()) {
					Sound.playSound(R.Files.WON_SOUND);
					endOfGame(JOptionPane.showConfirmDialog(myMazePanel, R.Strings.GAME_WON_MSG));
				}
			} else {
				Sound.playSound(R.Files.WRONG_SOUND);
				JOptionPane.showMessageDialog(myQuestionPanel, R.Strings.QUESTION_MSG_WRONG);
				myMaze.findDoor(myCurrentDirection).setStatus(DoorStatus.DEAD);
				myMaze.findDuplicateDoor(myCurrentDirection).setStatus(DoorStatus.DEAD);
				if (myMaze.canTraverseMaze(myMaze.getCurrentRoom())) {
					updateMazePanel(myMaze);
				} else {
					updateMazePanel(myMaze);
					Sound.playSound(R.Files.LOST_SOUND);
					endOfGame(JOptionPane.showConfirmDialog(myMazePanel, R.Strings.GAME_LOST_MSG));
				}
			}
			remove(myQuestionPanel);
			myQuestionPanel = new QuestionPanel();
			add(myQuestionPanel, BorderLayout.EAST);
			myCurrentDirection = null;
			mySubmitBtn.setEnabled(false);
			myHintBtn.setEnabled(false);
			pack();
			revalidate();
		}
	};

	/**
	 * Helper method to move the player in the current direction. If the door is not
	 * open, display the question. If the door is not open, move to the destination
	 * room.
	 */
	private void moveAction() {
		if (myMaze.findDoor(myCurrentDirection).isOpen()) {
			myPC.movePlayer(myCurrentDirection);
			updateMazePanel(myMaze);
		} else {
			myDoorQuestion = myMaze.findDoor(myCurrentDirection).getQuestion();
			displayQuestion(myDoorQuestion);
		}
	}

	/**
	 * Event handler for the Up Button. This method is implicitly called when the
	 * user presses the "Up" button.
	 *
	 * @param theEvent the ActionEvent that triggered this method.
	 * @throws Exception
	 * @throws IllegalStateException when the component is not a JButton
	 */
	ActionListener moveUpAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			myCurrentDirection = Direction.UP;
			moveAction();
		}
	};

	/**
	 * Event handler for the Down Button. This method is implicitly called when the
	 * user presses the "Down" button.
	 *
	 * @param theEvent the ActionEvent that triggered this method.
	 * @throws Exception
	 * @throws IllegalStateException when the component is not a JButton
	 */
	ActionListener moveDownAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			myCurrentDirection = Direction.DOWN;
			moveAction();
		}
	};

	/**
	 * Event handler for the Left Button. This method is implicitly called when the
	 * user presses the "Left" button.
	 *
	 * @param theEvent the ActionEvent that triggered this method.
	 * @throws Exception
	 * @throws IllegalStateException when the component is not a JButton
	 */
	ActionListener moveLeftAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			myCurrentDirection = Direction.LEFT;
			moveAction();
		}
	};

	/**
	 * Event handler for the right Button. This method is implicitly called when the
	 * user presses the "Right" button.
	 *
	 * @param theEvent the ActionEvent that triggered this method.
	 * @throws Exception
	 * @throws IllegalStateException when the component is not a JButton
	 */
	ActionListener moveRightAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			myCurrentDirection = Direction.RIGHT;
			moveAction();
		}
	};

	/**
	 * Helper method for saving the game by calling the writeToFile method in Maze
	 * class.
	 */
	private void saveGame() {
		if (myMaze.writeToFile()) {
			JOptionPane.showMessageDialog(this, R.Strings.SAVE_MSG);
		} else {
			JOptionPane.showMessageDialog(this, R.Strings.SAVE_IO_MSG, "Alert", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Helper method for displaying question panel.
	 *
	 * @param theQuestion question assigned to the door.
	 */
	private void displayQuestion(final Question theQuestion) {
		myQuestionPanel.setUpQuestionInfo(theQuestion);
		myQuestionPanel.setVisible(true);
		mySubmitBtn.setEnabled(true);
		disableMoveButtons();
		myHintBtn.setEnabled(true);
		pack();
	}

	/**
	 * Update the game frame.
	 *
	 * @param theMaze the updated maze object
	 */
	private void updateMazePanel(final Maze theMaze) {
		remove(myMazePanel);
		myMazePanel = new MazePanel(myMaze);
		add(myMazePanel, BorderLayout.CENTER);
		pack();
		revalidate();
		enableMoveButtons();
	}
}
