/*
 * TCSS 360 Trivia Maze
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import res.R;

/**
 * MainMenuFrame provide the user interface for starting game or help.
 * @author Zhaoyu Xu
 * @version 1.3
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
		header.setBackground(R.Colors.MMF_BG);
		myGameLbl.setFont(R.Fonts.GAME_TITLE_FONT);
		myGameLbl.setBounds((R.Dimensions.FRAME_SIZE.width / 2) - (R.Dimensions.FRAME_SIZE.height / 2), 20,
				R.Dimensions.LABEL_SIZE.width, R.Dimensions.LABEL_SIZE.height);
		myGameLbl.setForeground(R.Colors.TEXT_COLOR);
		header.add(myGameLbl);
		pane.add(header, BorderLayout.NORTH);

		myButtonPanel.setBorder(BorderFactory.createEmptyBorder(R.Dimensions.V_PADDING, R.Dimensions.H_PADDING,
				R.Dimensions.V_PADDING, R.Dimensions.H_PADDING));
		myButtonPanel.setBackground(R.Colors.MMF_BG);

		myPlayGameBtn.setForeground(R.Colors.TEXT_COLOR);
		myPlayGameBtn.setBackground(R.Colors.MMF_BUTTON_BG);
		myButtonPanel.add(myPlayGameBtn);

		myLoadGameBtn.setForeground(R.Colors.TEXT_COLOR);
		myLoadGameBtn.setBackground(R.Colors.MMF_BUTTON_BG);
		myButtonPanel.add(myLoadGameBtn);

		myHelpBtn.setForeground(R.Colors.TEXT_COLOR);
		myHelpBtn.setBackground(R.Colors.MMF_BUTTON_BG);
		myButtonPanel.add(myHelpBtn);
		
		myAboutBtn.setForeground(R.Colors.TEXT_COLOR);
		myAboutBtn.setBackground(R.Colors.MMF_BUTTON_BG);
		myButtonPanel.add(myAboutBtn);

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

	/**
     * Event handler for the New Game Button. This method is implicitly called when
     * the user presses the "New Game" button.
     * 
     * @param theEvent the ActionEvent that triggered this method.
     * @throws IllegalStateException when the component is not a JButton
     */
	private void playGameBtnAction(final ActionEvent theEvent) {
		if (theEvent.getSource().getClass() != JButton.class) {
            throw new IllegalStateException(R.Strings.ERROR_MSG_ILLEGAL_INVOKE);
        }
		GameFrame window = new GameFrame();
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
		SavedGameFrame window = new SavedGameFrame();
		window.setVisible(true);
		this.setVisible(false);
	}

	/**
     * Event handler for the Help Button. This method is implicitly called when
     * the user presses the "Help" button.
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
     * Event handler for the Help Button. This method is implicitly called when
     * the user presses the "Help" button.
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
}
