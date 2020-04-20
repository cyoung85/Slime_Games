package Slime.Games;


import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

import Slime.Games.SlimeSoccer.DrawCanvas;


public class SlimeBasketball extends JPanel {
	private static final int UPDATE_RATE = 60; // Frames per second (fps)

	private SlimeBall ball; // A single bouncing SlimeBall instance
	private WindowBounds window; // The container rectangular box
	private Player_1 p1; // Player 1's variable
	private Player_2 p2;

	private DrawCanvas canvas; // Custom canvas for drawing the box/SlimeBall
	private int canvasWidth;
	private int canvasHeight;
	//private String game = "soccer";
	// the starting location for the ball
	public final int ballxStart = 580;
	public final int ballyStart = 200;
	public int ballx = ballxStart;
	public int bally = ballyStart;
	
	// start for player 1
	public final int p1XStart = 150;
	public final int p1YStart = 550;

	// start for player 2
	public final int p2XStart = 950;
	public final int p2YStart = 550;

	@SuppressWarnings("exports")
	public InputMap im;
	@SuppressWarnings("exports")
	public ActionMap ap;
	
	//Frame Declarations
	public SlimeBasketball(int width, int height){
		canvasWidth = width;
		canvasHeight = height;

		Random rand = new Random();
		int radius = 20;
		int speed = 10;
		int angleInDegree = rand.nextInt(360);
		ball = new SlimeBall(ballx, bally, radius, speed, angleInDegree,"basketball");

		// Init the Container Box to fill the screen
		window = new WindowBounds(canvasWidth, canvasHeight,"basketball");

		// Init Player 1
		p1 = new Player_1(p1XStart, p1YStart);
		p2 = new Player_2(p2XStart, p2YStart);
		// Init the custom drawing panel for drawing the game
		canvas = new DrawCanvas();

		this.setLayout(new BorderLayout());
		this.add(canvas, BorderLayout.CENTER);

		im = canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

		// sets up an input map for both player 1 and player 2 to move
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "P1 Move Left");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "P1 Move Right");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "P1 Jump");

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "P2 Move Left");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "P2 Move Right");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "P2 Jump");

		ap = canvas.getActionMap();

		// make player 1 move the desired direction


		// Handling window resize.
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component) e.getSource();
				Dimension dim = c.getSize();
				canvasWidth = dim.width;
				canvasHeight = dim.height;
				// Adjust the bounds of the container to fill the window
				window.set(0, 0, canvasWidth, canvasHeight);
			}
		});

		// Start the SlimeBall bouncing
		gameStart();
    } 
	/** Start the SlimeBall bouncing. */
	public void gameStart() {
		// Run the game logic in its own thread.
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					// Execute one time-step for the game
					gameUpdate();
					// Refresh the display
					repaint();
					// Delay and give other thread a chance
					ap.put("P1 Move Left", new AbstractAction() {
						public void actionPerformed(ActionEvent e) {
							p1.moveLeft();
						}
					});

					ap.put("P1 Move Right", new AbstractAction() {
						public void actionPerformed(ActionEvent e) {
							p1.moveRight();
						}
					});
					try {
						Thread.sleep(1);
					} catch (InterruptedException ex) {
					}
					ap.put("P2 Move Left", new AbstractAction() {
						public void actionPerformed(ActionEvent e) {
							p2.moveLeft();
						}
					});

					ap.put("P2 Move Right", new AbstractAction() {
						public void actionPerformed(ActionEvent e) {
							p2.moveRight();
						}
					});
					try {
						Thread.sleep(1000 / UPDATE_RATE);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		gameThread.start(); // Invoke GaemThread.run()
	}
	/**
	 * One game time-step. Update the game objects, with proper collision detection
	 * and response.
	 */
	public void gameUpdate() {
		ball.moveWithColision(window, p1,p2);
	}

	// makes sure the window loads in properly

	public Dimension getPreferredSize() {
		return (new Dimension(canvasWidth, canvasHeight));
	}

	/** The custom drawing panel for the bouncing SlimeBall (inner class). */
	class DrawCanvas extends JPanel {
		/** Custom drawing codes */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // Paint background
			// Draw the box and the SlimeBall
			window.draw(g);
			ball.draw(g);
			p1.draw(g);
			p2.draw(g);
			Color black = new Color(0,0,0);
			Color light_grey = new Color(204,204,204);
	        g.setColor(light_grey);
	        //Nets
	        //left
	        g.drawLine(30,408,95,408);
	        g.drawLine(35,416,90,416);
	        g.drawLine(39,424,86,424);
	        g.drawLine(43,432,82,432);
	        g.drawLine(46,440,79,440);
	        g.drawLine(46,400,46,440);
	        g.drawLine(79,400,79,440);
	        g.drawLine(63,400,63,440);
	        g.drawLine(25,400,46,440);
	        g.drawLine(100,400,79,440);
	        //right
	        g.drawLine(1170,408,1105,408);
	        g.drawLine(1165,416,1110,416);
	        g.drawLine(1161,424,1114,424);
	        g.drawLine(1157,432,1118,432);
	        g.drawLine(1154,440,1121,440);
	        g.drawLine(1121,400,1121,440);
	        g.drawLine(1154,400,1154,440);
	        g.drawLine(1137,400,1137,440);
	        g.drawLine(1175,400,1154,440);
	        g.drawLine(1100,400,1121,440);
			
	        g.setColor(black);
	        //left rim
	        g.drawLine(25,400,100,400);
	        g.drawLine(25,399,100,399);
	        g.drawLine(25,401,100,401);
	        g.drawLine(25,350,25,600);
	        g.drawLine(24,350,24,600);
	        g.drawLine(26,350,26,600);
	        //right rim
	        g.drawLine(1175,400,1100,400);
	        g.drawLine(1175,399,1100,399);
	        g.drawLine(1175,401,1100,401);
	        g.drawLine(1175,350,1175,600);
	        g.drawLine(1174,350,1174,600);
	        g.drawLine(1176,350,1176,600);
			// displays scores
			g.setColor(Color.BLACK);
			g.setFont(new Font("Helvetica", Font.BOLD, 40));
			g.drawString("" + SlimeGames.p1score, 50, 100);
			g.drawString("" + SlimeGames.p2score, 1200 - 80, 100);

		}

	}
   
}