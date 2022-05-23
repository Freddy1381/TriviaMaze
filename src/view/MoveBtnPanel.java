package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import res.R;

public class MoveBtnPanel extends JPanel {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

	private JButton btnUp, btnDown, btnLeft, btnRight;
	
	public MoveBtnPanel() {
		super();
		
		layoutComponents();
	}
	
	private JButton createButtons(final String theMove) {
		final JButton button = new JButton(theMove);
		button.setForeground(R.Colors.TEXT_COLOR);
		button.setBackground(R.Colors.MMF_BUTTON_BG);
		button.setEnabled(true);
		
		button.addActionListener(e -> {
			new MazePanel();
		});
		
		return button;
	}

	private void layoutComponents() {
		this.setLayout(new BorderLayout(20, 15));
		
		btnUp = createButtons(R.Strings.MBP_MOVE_BTN_UP);
		this.add(btnUp, BorderLayout.NORTH);
		btnDown = createButtons(R.Strings.MBP_MOVE_BTN_DOWN);
		this.add(btnDown, BorderLayout.SOUTH);
		btnLeft = createButtons(R.Strings.MBP_MOVE_BTN_LEFT);
		this.add(btnLeft, BorderLayout.WEST);
		btnRight = createButtons(R.Strings.MBP_MOVE_BTN_RIGHT);
		this.add(btnRight, BorderLayout.EAST);
		
		this.setBackground(R.Colors.MMF_BG);
	}
	
//	private void disableMoveButtons() {
//		btnUp.setEnabled(false);
//		btnDown.setEnabled(false);
//		btnLeft.setEnabled(false);
//		btnRight.setEnabled(false);
//	}
//	
//	private void enableMoveButtons() {
//		boolean[] check = myMaze.checkSurroundings(myMaze.getMaze());
//		if (check[0]) {
//			btnUp.setEnabled(true);
//		}
//		if (check[1]) {
//			btnDown.setEnabled(true);
//		}
//		if (check[2]) {
//			btnLeft.setEnabled(true);
//		}
//		if (check[3]) {
//			btnRight.setEnabled(true);
//		}
//	}
}
