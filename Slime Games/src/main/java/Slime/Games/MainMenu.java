
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
	private JButton soccer, bball, vball, spleef;
	private JLabel title;
	private Font titleFont, soccerFont, bballFont, vballFont, spleefFont;
	private int titleFSize, soccerFSize, bballFSize, vballFSize, spleefFSize;

	@SuppressWarnings("deprecation")
	public MainMenu(){

		//Initialize Components
		Color bgColor = new Color(50, 168, 82);
		frame = new JFrame("Slime Games");
		content = new MenuContent();
		title = new JLabel("Slime Games");
		soccer =  new JButton("Play Soccer");
		bball = new JButton("Play Basketball");
		vball = new JButton("Play Volleyball");
		spleef = new JButton("Play Spleef");

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
		soccer.setBounds(85, 200, 300, 75);
		soccer.setMargin(null);
		soccer.setBackground(bgColor);
		soccer.setBorderPainted(false);

		soccerFont = soccer.getFont();
		soccerFSize = soccerFont.getSize();
		soccerFSize *= 3;
		soccer.setFont(new Font(soccerFont.getName(), Font.PLAIN, soccerFSize));
		soccer.setHorizontalAlignment(SwingConstants.LEFT);

		//Basketball Button Config
		bball.addActionListener(this);
		bball.setBounds(85, 300, 300, 75);
		bball.setMargin(null);
		bball.setBackground(bgColor);
		bball.setBorderPainted(false);

		bballFont = bball.getFont();
		bballFSize = soccerFont.getSize();
		bballFSize *= 3;
		bball.setFont(new Font(bballFont.getName(), Font.PLAIN, bballFSize));
		bball.setHorizontalAlignment(SwingConstants.LEFT);

		//Volleyball Button Config
		vball.addActionListener(this);
		vball.setBounds(85, 400, 300, 75);
		vball.setMargin(null);
		vball.setBackground(bgColor);
		vball.setBorderPainted(false);

		vballFont = bball.getFont();
		vballFSize = soccerFont.getSize();
		vballFSize *= 3;
		vball.setFont(new Font(bballFont.getName(), Font.PLAIN, bballFSize));
		vball.setHorizontalAlignment(SwingConstants.LEFT);

		//Spleef Button Config
		spleef.addActionListener(this);
		spleef.setBounds(85, 500, 300, 75);
		spleef.setMargin(null);
		spleef.setBackground(bgColor);
		spleef.setBorderPainted(false);

		spleefFont = bball.getFont();
		spleefFSize = soccerFont.getSize();
		spleefFSize *= 3;
		spleef.setFont(new Font(bballFont.getName(), Font.PLAIN, bballFSize));
		spleef.setHorizontalAlignment(SwingConstants.LEFT);

		//Frame components
		content.add(soccer);
		content.add(title);
		content.add(bball);
		content.add(vball);
		content.add(spleef);

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
		      javax.swing.SwingUtilities.invokeLater(new Runnable() {
			         public void run() {
			            JFrame frame = new JFrame("Slime Soccer");
			            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			            frame.setContentPane(new SlimeSoccer(1200, 700)); 
			            frame.pack();            // Preferred size of BallWorld
			            frame.setResizable(false);
			            frame.setVisible(true);  
			            }
			      });

			break;

		case "Play Basketball":
			SlimeBasketball b = new SlimeBasketball();
			break;

		case "Play Volleyball":
			SlimeVolleyball v = new SlimeVolleyball();
			break;

		case "Play Spleef":
			SlimeSpleef p = new SlimeSpleef();

		default:
			break;
		}

	}

	class MenuContent extends JPanel {

		@Override
		protected void paintComponent(Graphics g){

			//socccer ball
			g.setColor(Color.WHITE);
			g.fillOval(1200, 200, 65, 65);

			//basketball
			g.setColor(Color.ORANGE);
			g.fillOval(950, 100, 65, 65);
			g.setColor(Color.BLACK);
			g.drawOval(1200, 200, 65, 65);
			g.drawOval(950, 100, 65, 65);
			g.drawArc(993, 100, 65, 65, 130, 102 );
			g.drawLine(983, 165, 983, 100);
			g.drawLine(950, 132, 1015, 132);
			g.drawArc(909, 100, 65, 65, 310, 100 );

			//red slime
			g.setColor(Color.RED);
			g.fillArc(450, 250, 300, 300, 0, 180);//body
			g.setColor(Color.WHITE);
			g.fillOval(645, 280, 40, 40);//eye (+65, +10)
			g.setColor(Color.BLACK);
			g.fillOval(658, 285, 25, 25);//pupil (+70, +10)

			//yellow slime
			g.setColor(Color.YELLOW);
			g.fillArc(900, 400, 300, 300, 0, 180);//body
			g.setColor(Color.WHITE);
			g.fillOval(960, 430, 40, 40);//eye (+35, +10)
			g.setColor(Color.BLACK);
			g.fillOval(963, 433, 25, 25);//pupil (+35, +10)



		}
	}
}