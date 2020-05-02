package Slime.Games;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

import Slime.Games.SlimeVolleyball.DrawCanvas;


/**
 * The control logic and main display panel for game.
 */
public class SlimeSpleef extends JPanel{
	private static final int UPDATE_RATE = 60; // Frames per second (fps)

	private SlimeBall ball; // A single bouncing SlimeBall instance
	private WindowBounds window; // The container rectangular box
	private Player_1 p1; // Player 1's variable
	private Player_2 p2; //Player 2's variable

	//boolean variables for movement
	private boolean p1Left = false;
	private boolean p1Right = false;
	private boolean p1Jump = false;
	private boolean p2Left = false;
	private boolean p2Right = false;
	private boolean p2Jump = false;

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

	private boolean sect1=true,sect2=true,sect3=true,sect4= true,sect5 = true,sect6 = true; //used for spleef

	@SuppressWarnings("exports")
	public InputMap im;
	@SuppressWarnings("exports")
	public ActionMap ap;

	private double defaultRoundTime = 45; //default time for each game
	private double roundTime = defaultRoundTime; //current remaining time
	private static Timer timer;
	public static final int TIMER_SPEED = 12;

	//Frame Declarations
	public SlimeSpleef(int width, int height){
		canvasWidth = width;
		canvasHeight = height;

		Random rand = new Random();
		int radius = 20;
		int speed = 10;
		int angleInDegree = rand.nextInt(360);
		ball = new SlimeBall(ballx, bally, radius, speed, angleInDegree,"spleef");

		// Init the Container Box to fill the screen
		window = new WindowBounds(canvasWidth, canvasHeight,"spleef");

		// Init Player 1
		p1 = new Player_1(p1XStart, p1YStart);
		p2 = new Player_2(p2XStart, p2YStart);
		// Init the custom drawing panel for drawing the game
		canvas = new DrawCanvas();

		this.setLayout(new BorderLayout());
		this.add(canvas, BorderLayout.CENTER);

		im = canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

		//set up for p1 to move
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "A Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "A Released");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "D Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "D Released");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "W Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "W Released");

		//set up for p2 to move 
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "Left Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "Left Released");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "Right Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "Right Released");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "Up Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "Up Released");

		ap = canvas.getActionMap();

		// make player 1 move the desired direction
		//p1 left
		ap.put("A Down", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				p1Left = true;
			}
		});

		ap.put("A Released", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				p1Left = false;
			}
		});

		//p1 right
		ap.put("D Down", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				p1Right = true;
			}
		});
		ap.put("D Released", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				p1Right = false;
			}
		});
		// p1 jump
		ap.put("W Down", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				p1Jump = true;
			}
		});
		ap.put("W Released", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				p1Jump = false;
			}
		});
		
		// make player 2 move the desired direction
		//p2 left
		ap.put("Left Down", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				p2Left = true;
			}
		});
		ap.put("Left Released", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				p2Left = false;
			}
		});

		//p2 right
		ap.put("Right Down", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				p2Right = true;
			}
		});
		ap.put("Right Released", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				p2Right = false;
			}
		});
		// p2 jump
		ap.put("Up Down", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				p2Jump = true;
			}
		});
		ap.put("Up Released", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				p2Jump = false;
			}
		});
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

					try {
						Thread.sleep(1000 / UPDATE_RATE);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		gameThread.start(); 
	}
	/**
	 * One game time-step. Update the game objects, with proper collision detection
	 * and response.
	 */
	public void gameUpdate() {
		ball.moveWithColision(window, p1,p2); //ball collisions
		//updates the player based on the movement that is true and checks if they are able to jump
		if(p1Left)
			p1.moveLeft();
		if(p1Right)
			p1.moveRight();
		if (p1.getY() < 550 && !p1.canJump()) {
			p1.inAir();
			p1.applyGravity();
		}
		if (p1Jump)
			p1.jump(p1Jump);
		else
			p1.inAir();
		if(p2Left)
			p2.moveLeft();
		if(p2Right)
			p2.moveRight();
		if (p2.getY() < 550 && !p2.canJump()) {
			p2.inAir();
			p2.applyGravity();
		}
		if (p2Jump)
			p2.jump(p2Jump);
		else
			p2.inAir();

		if (p1.getY() == 550)
			p1.touchedGround();
		if (p2.getY() == 550)
			p2.touchedGround();

		//timer running and game restart
		if ( roundTime > 0 ) {

			roundTime -= TIMER_SPEED * .001;
			repaint();
		} else {
			//checks for the winner and displays it in the panel at the end
			int winner = 0;
			if (SlimeGames.p1score > SlimeGames.p2score) {
				winner = 1;
			} else if (SlimeGames.p2score > SlimeGames.p1score){
				winner = 2;
			}
			String notification;
			if (winner == 0) {
				notification = "Tie! Play again?";
			} else {
				notification = "Player " + winner + " won. Play again?";
			}
			int input = JOptionPane.showOptionDialog( new JFrame(), notification, "Game Over", JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (input == JOptionPane.YES_OPTION){//resetting the timer, players, and ball when played again
				roundTime = defaultRoundTime;
				SlimeGames.p1score = 0;
				SlimeGames.p2score = 0;
				ball.x = 190;
				ball.y = 100;
				p1.setX(p1XStart);
				p1.setY(p1YStart);
				p2.setX(p2XStart);
				p2.setY(p2YStart);
				ball.xSpeed = 0;
				ball.ySpeed = -5;
				ball.sect1=false;
				ball.sect2=false;
				ball.sect3=false;
				ball.sect4= false;
				ball.sect5 = false;
				ball.sect6 = false;
				window.initialSpleefSetup= false;
				p1Left = false;
				p1Right = false;
				p1Jump = false;
				p2Left = false;
				p2Right = false;
				p2Jump = false;
			} else { //closes the program when not playing again
				System.exit(0);
			}
		}
	}

	// makes sure the window loads in properly

	public Dimension getPreferredSize() {
		return (new Dimension(canvasWidth, canvasHeight));
	}
	/** The custom drawing panel for the bouncing SlimeBall (inner class). */
	class DrawCanvas extends JPanel {
		private static final long serialVersionUID = 1L;
		/** Custom drawing codes */
		@Override
		public void paintComponent(Graphics g) {

			// Draw the box and the SlimeBall
			window.draw(g);
			ball.draw(g);
			p1.draw(g);
			p2.draw(g);
			Color light_green = new Color(0,255,100);
			Color white = new Color(255,255,255);
			Color dark_red = new Color(204,0,0); // used for the hit spaces
			g.setColor(dark_red);
			if(ball.sect1&&sect1) {
				g.fillRect(0, 600,199, 100 ); //left side left section
			}

			if(ball.sect2&&sect2) {
				g.fillRect(200, 600,199, 100 ); //left side middle section
			}

			if(ball.sect3&&sect3) {
				g.fillRect(400, 600,199, 100 ); //left side right section
			}

			if(ball.sect4&&sect4) {
				g.fillRect(600, 600,199, 100 ); //right side left section
			}

			if(ball.sect5&&sect5) {
				g.fillRect(800, 600,199, 100 ); //right side middle section
			}

			if(ball.sect6&&sect6) {
				g.fillRect(1000, 600,199, 100 ); //right side right section
			}

			g.setColor(light_green);
			//middle divider on ground
			g.drawLine(599, 600, 599, 700);
			g.drawLine(600, 600, 600, 700);

			g.setColor(white);
			//left side section dividers
			g.drawLine(199, 600, 199, 700);
			g.drawLine(200, 600, 200, 700);
			g.drawLine(399, 600, 399, 700);
			g.drawLine(400, 600, 400, 700);

			//right side section dividers
			g.drawLine(799, 600, 799, 700);
			g.drawLine(800, 600, 800, 700);
			g.drawLine(999, 600, 999, 700);
			g.drawLine(1000, 600, 1000, 700);
			//displays the scores
			g.setColor(Color.WHITE);
			g.setFont(new Font("Helvetica",Font.BOLD,40));
			g.drawString("" + SlimeGames.p1score, 50, 100);
			g.drawString("" + SlimeGames.p2score, 1200 - 80, 100);

			//display timer
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Helvetica",Font.BOLD,40));
			g.drawString("" + Math.round(roundTime), 1200/2 - 20, 80);
			
		}   

	}
}
