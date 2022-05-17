/**
 * TCSS 360 Trivia Maze
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AnswerPanel;
//import model.Maze;
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
	
//	private final Maze myMaze;
	
	/** The panel that will display the Maze. */
	private final MazePanel myMazePanel;
	
	/** The panel that will display the Movement Buttons. */
	private final MoveBtnPanel myMoveBtnPanel;
	
	/** The panel that will display the Question. */
	private final QuestionPanel myQuestionPanel;
	
	/** The panel that will display the Answer. */
	private final AnswerPanel myAnswerPanel;
	
	/** The panel that will display the Utility Bar. */
	private final UtilityBarPanel myUtilityBar;

	/**
	 * initializes the Game GUI.
	 */
	public GameFrame() {
		super();
		
//		myMaze = new Maze();
		myMazePanel = new MazePanel();
		myMoveBtnPanel = new MoveBtnPanel();
		myQuestionPanel = new QuestionPanel();
		myAnswerPanel = new AnswerPanel();
		myUtilityBar = new UtilityBarPanel();
		
		setupGUI();
	}
	
	/**
     * Setup the various parts of the GUI.
     */
	private void setupGUI() {
		layoutComponents();
		finalizeFrame();
	}
	
	/**
     * Layout the Swing components.
     */
	private void layoutComponents() {
		final JPanel pane = new JPanel(new BorderLayout(40, 30));
		
		pane.add(myUtilityBar, BorderLayout.NORTH);
		pane.add(myMazePanel, BorderLayout.CENTER);
		pane.add(myMoveBtnPanel, BorderLayout.WEST);
		pane.add(myQuestionPanel, BorderLayout.EAST);
		pane.add(myAnswerPanel, BorderLayout.SOUTH);
		
		pane.setBackground(R.Colors.MMF_BG);
		pane.setBorder(BorderFactory.createEmptyBorder(R.Dimensions.V_PADDING, R.Dimensions.H_PADDING,
				R.Dimensions.V_PADDING, R.Dimensions.H_PADDING));
		
		setContentPane(pane);
	}
	
	/**
     * Finalize this JFrame before making visible. 
     */
	private void finalizeFrame() {
		setTitle(R.Strings.GAME_TITLE);
		setResizable(false);
		setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2, SCREEN_SIZE.height / 2 - getHeight() / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
