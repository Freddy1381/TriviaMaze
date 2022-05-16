package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Display{

//	private JCheckBox checkbox1;
	private JButton mybutton1;
	private JButton mybutton2;
	private JButton mybutton3;
	private JButton mybutton4;


	public Display() {
		
		JFrame frame = new JFrame();
		
		final ImageIcon img1 = new ImageIcon("icon\\open.gif");
        final ImageIcon img2 = new ImageIcon("icon\\save.gif");
        final ImageIcon img3 = new ImageIcon("icon\\close.gif");
        final ImageIcon img4 = new ImageIcon("icon\\smile.jpg");
//        Image img5 = Toolkit.getDefaultToolkit().getImage("icon\\\\smile.jpg");
        
		mybutton1 = new JButton("Start New Game" , img1);
		//		button.addActionListener(this);
		mybutton2 = new JButton("Load a Game" , img2);
		mybutton3 = new JButton(" ?  Help and Options");
		mybutton4 = new JButton("Close", img3);
		
//		ImageIcon img = new ImageIcon("s.jpg") ;
//		JLabel pic = new JLabel(img);
		

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));
		panel.setLayout(new GridLayout(1,2));
		panel.add(mybutton1);
		panel.add(mybutton2);
		panel.add(mybutton3);
		panel.add(mybutton4);
//		panel.add(img5);
		
//		panel.add(img);
		final ImageIcon img = new ImageIcon("icon\\smile.jpg");
        frame.setIconImage(img.getImage());

		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Trivia Maze");
		frame.pack();
		frame.setVisible(true);

	

	mybutton1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent theEvent) {

		}
	});

	mybutton2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent theEvent) {

		}
	});
	
	 mybutton3.addActionListener(new ActionListener() {
         public void actionPerformed(final ActionEvent theEvent) {
             JOptionPane.showMessageDialog(null, "Click 'Close' to close the game. Click 'Start new game' for new game. If you want to load a game click 'Load a Game.'");
         }
     });
	
	
	 mybutton4.addActionListener(new ActionListener() {
         public void actionPerformed(final ActionEvent theEvent) {
             JOptionPane.showMessageDialog(null, "You have closed the game");
         }
     });
	
	

}