/*
 * TCSS 360 Trivia Maze
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.QuestionGetter;
import controller.QuestionSaver;
import model.Maze;
import model.Question;
import model.Sound;
import res.R;

/**
 * MainMenuFrame provide the user interface for starting game or help.
 * 
 * @author Zhaoyu Xu
 * @author Gurleen Sandhu
 * @version 2.1
 */
public class MainMenuFrame extends JFrame {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

	/** A ToolKit. */
	private static final Toolkit KIT = Toolkit.getDefaultToolkit();

	/** The Dimension of the screen. */
	private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

	/** The label used to store game title. */
	private final JLabel myGameLbl;

	/** The button used to trigger new game setup. */
	private final JButton myPlayGameBtn;

	/** The button used to trigger saved game setup. */
	private final JButton myLoadGameBtn;

	/** The button used to trigger help menu. */
	private final JButton myHelpBtn;

	/** The button used to trigger about menu. */
	private final JButton myAboutBtn;

	/** The button used to add questions to database. */
	private final JButton myAddBtn;

	/** The panel that holds the buttons. */
	private final JPanel myButtonPanel;

	/**
	 * Initializes the Trivia Maze GUI.
	 */
	public MainMenuFrame() {
		super();

		myGameLbl = new JLabel(R.Strings.GAME_TITLE);

		myPlayGameBtn = new JButton(R.Strings.MMF_PLAY_GAME_BTN);
		myLoadGameBtn = new JButton(R.Strings.MMF_LOAD_GAME_BTN);
		myHelpBtn = new JButton(R.Strings.MMF_HELP_BTN);
		myAboutBtn = new JButton(R.Strings.MMF_ABOUT_BTN);
		myAddBtn = new JButton(R.Strings.MMF_ADD_BTN);

		myButtonPanel = new JPanel();

		setupGUI();
	}

	/**
	 * Setup the various parts of the GUI.
	 */
	private void setupGUI() {
		layoutComponents();
		assignActions();
		finalizeFrame();
	}

	/**
	 * Layout the Swing components.
	 */
	private void layoutComponents() {
		final JPanel pane = new JPanel(new BorderLayout());

		final JPanel header = new JPanel();
		header.setBackground(R.Colors.BG);
		myGameLbl.setFont(R.Fonts.GAME_TITLE_FONT);
		myGameLbl.setBounds((R.Dimensions.FRAME_SIZE.width / 2) - (R.Dimensions.FRAME_SIZE.height / 2), 20,
				R.Dimensions.LABEL_SIZE.width, R.Dimensions.LABEL_SIZE.height);
		myGameLbl.setForeground(R.Colors.TEXT_COLOR);
		header.add(myGameLbl);
		pane.add(header, BorderLayout.NORTH);

		myButtonPanel.setBorder(BorderFactory.createEmptyBorder(R.Dimensions.V_PADDING, R.Dimensions.H_PADDING,
				R.Dimensions.V_PADDING, R.Dimensions.H_PADDING));
		myButtonPanel.setBackground(R.Colors.BG);

		myPlayGameBtn.setFont(R.Fonts.BUTTON_FONT);
		myPlayGameBtn.setForeground(R.Colors.TEXT_COLOR);
		myPlayGameBtn.setBackground(R.Colors.BUTTON_BG);
		myButtonPanel.add(myPlayGameBtn);

		myLoadGameBtn.setFont(R.Fonts.BUTTON_FONT);
		myLoadGameBtn.setForeground(R.Colors.TEXT_COLOR);
		myLoadGameBtn.setBackground(R.Colors.BUTTON_BG);
		myButtonPanel.add(myLoadGameBtn);

		myHelpBtn.setFont(R.Fonts.BUTTON_FONT);
		myHelpBtn.setForeground(R.Colors.TEXT_COLOR);
		myHelpBtn.setBackground(R.Colors.BUTTON_BG);
		myButtonPanel.add(myHelpBtn);

		myAboutBtn.setFont(R.Fonts.BUTTON_FONT);
		myAboutBtn.setForeground(R.Colors.TEXT_COLOR);
		myAboutBtn.setBackground(R.Colors.BUTTON_BG);
		myButtonPanel.add(myAboutBtn);

		myAddBtn.setFont(R.Fonts.BUTTON_FONT);
		myAddBtn.setForeground(R.Colors.TEXT_COLOR);
		myAddBtn.setBackground(R.Colors.BUTTON_BG);
		myButtonPanel.add(myAddBtn);

		pane.add(myButtonPanel, BorderLayout.CENTER);

		setContentPane(pane);
	}

	/**
	 * Add Listeners to any GUI components that require them.
	 */
	private void assignActions() {
		myPlayGameBtn.addActionListener(this::playGameBtnAction);
		myLoadGameBtn.addActionListener(this::loadGameBtnAction);
		myHelpBtn.addActionListener(this::helpBtnAction);
		myAboutBtn.addActionListener(this::aboutBtnAction);
		myAddBtn.addActionListener(this::addBtnAction);
	}

	/**
	 * Finalize this JFrame before making visible.
	 */
	private void finalizeFrame() {
		setTitle(R.Strings.GAME_TITLE);
		pack();
		setResizable(false);
		setLocation(SCREEN_SIZE.width / 2 - getSize().width / 2, SCREEN_SIZE.height / 2 - getSize().height / 2);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Event handler for the New Game Button. This method is implicitly called when
	 * the user presses the "New Game" button.
	 *
	 * @param theEvent the ActionEvent that triggered this method.
	 * @throws Exception
	 * @throws IllegalStateException when the component is not a JButton
	 */
	private void playGameBtnAction(final ActionEvent theEvent) {
		if (theEvent.getSource().getClass() != JButton.class) {
			throw new IllegalStateException(R.Strings.ERROR_MSG_ILLEGAL_INVOKE);
		}
		Sound.playSound(R.Files.START_SOUND);
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

	/**
	 * Event handler for the Load Game Button. This method is implicitly called when
	 * the user presses the "Load Game" button.
	 *
	 * @param theEvent the ActionEvent that triggered this method.
	 * @throws IllegalStateException when the component is not a JButton
	 */
	private void loadGameBtnAction(final ActionEvent theEvent) {
		if (theEvent.getSource().getClass() != JButton.class) {
			throw new IllegalStateException(R.Strings.ERROR_MSG_ILLEGAL_INVOKE);
		}
		Sound.playSound(R.Files.START_SOUND);
		GameFrame window = startSavedGame();
		window.setVisible(true);
		this.setVisible(false);
	}

	/**
	 * Event handler for the Help Button. This method is implicitly called when the
	 * user presses the "Help" button.
	 *
	 * @param theEvent the ActionEvent that triggered this method.
	 * @throws IllegalStateException when the component is not a JButton
	 */
	private void helpBtnAction(final ActionEvent theEvent) {
		if (theEvent.getSource().getClass() != JButton.class) {
			throw new IllegalStateException(R.Strings.ERROR_MSG_ILLEGAL_INVOKE);
		}
		JOptionPane.showMessageDialog(this, R.Strings.MMF_HELP_MSG);
	}

	/**
	 * Event handler for the Help Button. This method is implicitly called when the
	 * user presses the "Help" button.
	 *
	 * @param theEvent the ActionEvent that triggered this method.
	 * @throws IllegalStateException when the component is not a JButton
	 */
	private void aboutBtnAction(final ActionEvent theEvent) {
		if (theEvent.getSource().getClass() != JButton.class) {
			throw new IllegalStateException(R.Strings.ERROR_MSG_ILLEGAL_INVOKE);
		}
		JOptionPane.showMessageDialog(this, R.Strings.MMF_ABOUT_MSG);
	}

	/**
	 * Event handler for the Add Button. This method is implicitly called when the
	 * user presses the "Add" button.
	 *
	 * @param theEvent the ActionEvent that triggered this method.
	 * @throws IllegalStateException when the component is not a JButton
	 */
	private void addBtnAction(final ActionEvent theEvent) {
		if (theEvent.getSource().getClass() != JButton.class) {
			throw new IllegalStateException(R.Strings.ERROR_MSG_ILLEGAL_INVOKE);
		}
		try {
			new QuestionSaver();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns a new GameFrame using a saved Maze object.
	 * 
	 * @return the GameFrame of saved Maze object
	 */
	private GameFrame startSavedGame() {
		Maze maze = null;
		try {
			File file = new File(R.Strings.FILE_LOCATION + R.Strings.SAVE_FILE);
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			maze = (Maze) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, R.Strings.LOAD_IO_MSG, "Alert", JOptionPane.WARNING_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, R.Strings.LOAD_CLASS_MSG, "Alert", JOptionPane.WARNING_MESSAGE);
		}

		GameFrame window = new GameFrame(maze);
		return window;
	}
}
