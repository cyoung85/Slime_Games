package Slime.Games;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
public class SlimeSoccer implements ActionListener{
	//Frame Declarations
	private JFrame frame;
	private JButton play;
	private JTextArea test;
	
	@SuppressWarnings("deprecation")
	public SlimeSoccer() {
		//sets up frame
		frame = new JFrame("Slime Soccer");
		Dimension screenSize = new Dimension(1200,700);
		frame.setPreferredSize(screenSize);
		frame.setResizable(false);
		
		//Sets Border layout and visibility
		frame.setLayout(new BorderLayout());
	    frame.pack();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		SlimeGames.resetScore();
		//test.setText(test.getText().concat("You have clicked the button\n"));
	}
}
