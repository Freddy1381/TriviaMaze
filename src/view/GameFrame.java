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
 * @author Zhaoyu Xu
 * @version 1.3
 */
public class GameFrame extends JFrame {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;
	
	/** A ToolKit. */
	private static final Toolkit KIT = Toolkit.getDefaultToolkit();
	
	/** The Dimension of the screen. */
	private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
	
	private final Maze myMaze;
	
	/** The panel that will display the Maze. */
	private MazePanel myMazePanel;
	
	/** The panel that will display the Question. */
	private QuestionPanel myQuestionPanel;
	
	private Direction myCurrentDirection = null;
	
	private Question myDoorQuestion = null;
	
	private PlayerController myPC;

	private JButton myUpBtn, myDownBtn, myLeftBtn, myRightBtn;
	
	private JButton mySubmitBtn;
	
	private JButton mySaveBtn, myHintBtn, myExitBtn;

	/**
	 * initializes the Game GUI.
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
	
	private JButton createButtons(final Direction theDirection) {
		final JButton button = new JButton(theDirection.toString());
		button.setFont(R.Fonts.BUTTON_FONT);
		button.setForeground(R.Colors.TEXT_COLOR);
		button.setBackground(R.Colors.MMF_BUTTON_BG);
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
		
		setBackground(R.Colors.MMF_BG);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
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
		
		btnPane.setBackground(R.Colors.MMF_BG);
		btnPane.setSize(100, 100);
		return btnPane;
	}
	
	private JPanel setUpAnsPanel() {
		JPanel ansPane = new JPanel();
		
		mySubmitBtn = new JButton("Submit Answer");
		mySubmitBtn.setFont(R.Fonts.BUTTON_FONT);
		mySubmitBtn.setForeground(R.Colors.TEXT_COLOR);
		mySubmitBtn.setBackground(R.Colors.MMF_BUTTON_BG);
		mySubmitBtn.setEnabled(false);
		ansPane.add(mySubmitBtn);
		
		ansPane.setBackground(R.Colors.MMF_BG);
		return ansPane;
	}
	
	private JPanel setUpUtilPanel() {
		JPanel utilPane = new JPanel();
		
		mySaveBtn = new JButton("Save");
		mySaveBtn.setFont(R.Fonts.BUTTON_FONT);
		mySaveBtn.setForeground(R.Colors.TEXT_COLOR);
		mySaveBtn.setBackground(R.Colors.MMF_BUTTON_BG);
		mySaveBtn.setEnabled(true);
		myHintBtn = new JButton("Hint");
		myHintBtn.setFont(R.Fonts.BUTTON_FONT);
		myHintBtn.setForeground(R.Colors.TEXT_COLOR);
		myHintBtn.setBackground(R.Colors.MMF_BUTTON_BG);
		myHintBtn.setEnabled(false);
		myExitBtn = new JButton("Exit");
		myExitBtn.setFont(R.Fonts.BUTTON_FONT);
		myExitBtn.setForeground(R.Colors.TEXT_COLOR);
		myExitBtn.setBackground(R.Colors.MMF_BUTTON_BG);
		myExitBtn.setEnabled(true);
		
		utilPane.setLayout(new FlowLayout(FlowLayout.CENTER, 
										  R.Dimensions.UTILITY_H_GAP, 
										  R.Dimensions.UTILITY_V_GAP));
		utilPane.add(mySaveBtn);
		utilPane.add(myHintBtn);
		utilPane.add(myExitBtn);
		utilPane.setBackground(R.Colors.MMF_BG);
		return utilPane;
	}
	
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
		}
		else if (theOption == 1) {
			JOptionPane.showMessageDialog(this, R.Strings.GOOD_BYE);
			System.exit(0);
		}
		else {
			return;
		}
	}
	
	private void disableMoveButtons() {
		myUpBtn.setEnabled(false);
		myDownBtn.setEnabled(false);
		myLeftBtn.setEnabled(false);
		myRightBtn.setEnabled(false);
	}
	
	private void enableMoveButtons() {
		final Room current = myMaze.getCurrentRoom();
		final Location currentLoc = current.getRoomLocation();
		final int x = currentLoc.getX();
		final int y = currentLoc.getY();
		
		//LEFT
		if (x - 1 >= 0) {
			Door r = myMaze.findDoor(Direction.LEFT);
			myLeftBtn.setEnabled(!r.isDEAD());
		}
		//RIGHT
		if (x + 1 < 7) {
			Door r = myMaze.findDoor(Direction.RIGHT);
			myRightBtn.setEnabled(!r.isDEAD());
		}
		//UP
		if (y - 1 >= 0) {
			Door r = myMaze.findDoor(Direction.UP);
			myUpBtn.setEnabled(!r.isDEAD());
		}
		//DOWN
		if (y + 1 < 7) {
			Door r = myMaze.findDoor(Direction.DOWN);
			myDownBtn.setEnabled(!r.isDEAD());
		}
	}
	
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
	
	private void moveAction() {
		if (myMaze.findDoor(myCurrentDirection).isOpen()) {
			myPC.movePlayer(myCurrentDirection);
			updateMazePanel(myMaze);
		} else {
			myDoorQuestion = myMaze.findDoor(myCurrentDirection).getQuestion();
			displayQuestion(myDoorQuestion);
		}
	}
	
	ActionListener moveUpAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			myCurrentDirection = Direction.UP;
			moveAction();
		}
	};
	
	ActionListener moveDownAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			myCurrentDirection = Direction.DOWN;
			moveAction();
		}
	};
	
	ActionListener moveLeftAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			myCurrentDirection = Direction.LEFT;
			moveAction();
		}
	};
	
	ActionListener moveRightAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			myCurrentDirection = Direction.RIGHT;
			moveAction();
		}
	};
	
	private void saveGame() {
		// TODO Auto-generated method stub
		if (myMaze.writeToFile()) {
			JOptionPane.showMessageDialog(this, R.Strings.SAVE_MSG);
		} else {
			JOptionPane.showMessageDialog(this, R.Strings.SAVE_IO_MSG, "Alert", JOptionPane.WARNING_MESSAGE);
		}
	}
	
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
	 * @param theMaze
	 * @return 
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
