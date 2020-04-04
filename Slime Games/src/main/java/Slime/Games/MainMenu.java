 package Slime.Games;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;
public class MainMenu implements ActionListener {

	
	private JFrame frame;
	private MenuContent content;
	private JButton soccer;
	private JButton bball;
	private JLabel title;
	private Font titleFont;
	private Font soccerFont;
	private Font bballFont;
	private int titleFSize;
	private int soccerFSize;
	private int bballFSize;
	@SuppressWarnings("deprecation")
	public MainMenu(){
		
		//Initialize Components
		Color bgColor = new Color(50, 168, 82);
		frame = new JFrame("Slime Games");
		content = new MenuContent();
		title = new JLabel("Slime Games");
		soccer =  new JButton("Play Soccer");
		bball = new JButton("Play Basketball");
		
		//JFrame Config
		Dimension screenSize = new Dimension(1500,800);
		frame.setContentPane(content);
		frame.setPreferredSize(screenSize);
		frame.setResizable(false);
		frame.setBackground(bgColor);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//MenuContent Config
		content.setLayout(null);
		content.setVisible(true);
		
		//JLabel Config
		title.setBounds(75, 50, 1000, 100);
		titleFont = title.getFont();
		titleFSize = titleFont.getSize();
		titleFSize *= 10;
		title.setFont(new Font(titleFont.getName(), Font.PLAIN, titleFSize));



		//Soccer Button Config
		soccer.addActionListener(this);
		soccer.setBounds(75, 200, 300, 75);
		soccer.setMargin(null);
		soccer.setBackground(bgColor);
		soccer.setBorderPainted(false);
		
		soccerFont = soccer.getFont();
		soccerFSize = soccerFont.getSize();
		soccerFSize *= 3;
		soccer.setFont(new Font(soccerFont.getName(), Font.PLAIN, soccerFSize));

		//Basketball Button Config
		bball.addActionListener(this);
		bball.setBounds(75, 300, 300, 75);
		bball.setMargin(null);
		bball.setBackground(bgColor);
		bball.setBorderPainted(false);
		
		bballFont = bball.getFont();
		bballFSize = soccerFont.getSize();
		bballFSize *= 3;
		bball.setFont(new Font(bballFont.getName(), Font.PLAIN, bballFSize));
		
		//Frame components
		content.add(soccer);
		content.add(title);
		content.add(bball);
		
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SlimeGames.resetScore();
		frame.setVisible(false);
		
		String action = e.getActionCommand();

		switch(action) {
		
		case "Play Soccer":
			SlimeSoccer s = new SlimeSoccer();
			break;
			
		case "Play Basketball":
			//SlimeBasketball s = new SlimeBasketball;
			break;
		
		default:
			break;
		}
		
	}
	
	class MenuContent extends JPanel {
		
		@Override
		protected void paintComponent(Graphics g){
			g.setColor(Color.WHITE);
			g.fillOval(1200, 200, 65, 65);
			g.setColor(Color.ORANGE);
			g.fillOval(950, 100, 65, 65);
			g.setColor(Color.BLACK);
			g.drawOval(1200, 200, 65, 65);
			g.drawOval(950, 100, 65, 65);
			g.drawArc(993, 100, 65, 65, 130, 102 );
			g.drawLine(983, 165, 983, 100);
			g.drawLine(950, 132, 1015, 132);
			g.drawArc(909, 100, 65, 65, 310, 100 );
			
		}
	}
}
