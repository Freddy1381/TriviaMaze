package view;

import javax.swing.JButton;
import javax.swing.JPanel;

import res.R;

import model.Maze;

public class MazePanel extends JPanel {
	
	public MazePanel() {
		super();
		this.add(new JButton("Maze Panel Test"));
		this.setBackground(R.Colors.MMF_BG);
	}

//	public MazePanel(Maze myMaze) {
//		// TODO Auto-generated constructor stub
//		super();
//		this.add(new JButton("Maze Panel Test"));
//	}

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

}
