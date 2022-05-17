package application;

import java.awt.EventQueue;

import view.MainMenuFrame;

public final class TriviaMaze {
	
	private TriviaMaze() {
		new MainMenuFrame();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TriviaMaze();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
