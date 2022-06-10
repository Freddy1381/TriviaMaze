/*
 * TCSS 360 Trivia Maze
 */

package application;

import java.awt.EventQueue;

import view.MainMenuFrame;

/**
 * Runs the game by instantiating and starting the MainMenuFrame.
 * 
 * @author Zhaoyu Xu
 * @version 2.1
 */
public final class TriviaMaze {

	/**
	 * Private constructor, to prevent instantiation of this class.
	 *
	 * @throws IllegalStateException when this constructor is called.
	 */
	private TriviaMaze() {
		throw new IllegalStateException();
	}

	/**
	 * The main method, prepare the questions from a txt file to a sqlite database.
	 * Invokes the MainMenuFrame.
	 *
	 * @param theArgs Command line arguments.
	 */
	public static void main(final String[] theArgs) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new MainMenuFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
