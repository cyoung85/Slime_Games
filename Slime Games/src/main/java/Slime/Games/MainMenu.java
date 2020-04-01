package Slime.Games;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class MainMenu implements ActionListener {
	
	private JFrame frame;
	private JButton play;
	private JTextArea test;
	

	@SuppressWarnings("deprecation")
	public MainMenu(){
		
		//JFrame Config
		frame = new JFrame("Slime Games");
		Dimension screenSize = new Dimension(600,800);
		frame.setPreferredSize(screenSize);
		frame.setResizable(false);
		
		//JButton Config
		play =  new JButton("Play");
		play.addActionListener(this);
		
		// Test Text area
		test = new JTextArea();
		
		frame.setLayout(new BorderLayout());
		frame.add(play,BorderLayout.SOUTH);
		frame.add(test,BorderLayout.NORTH);
	    frame.pack();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SlimeGames.resetScore();
		test.setText(test.getText().concat("You have clicked the button\n"));
	}
	
}
