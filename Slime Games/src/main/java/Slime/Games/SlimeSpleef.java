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
	private Player_2 p2;

	private boolean p1Left = false;
	private boolean p1Right = false;
	private boolean p2Left = false;
	private boolean p2Right = false;

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

		//set up for p2 to move 
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "Left Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "Left Released");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "Right Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "Right Released");

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
		gameThread.start(); // Invoke GaemThread.run()
	}
	/**
	 * One game time-step. Update the game objects, with proper collision detection
	 * and response.
	 */
	public void gameUpdate() {
		ball.moveWithColision(window, p1,p2);
		if(p1Left)
			p1.moveLeft();
		if(p1Right)
			p1.moveRight();
		if(p2Left)
			p2.moveLeft();
		if(p2Right)
			p2.moveRight();
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



			//displays the scores
			g.setColor(Color.WHITE);
			g.setFont(new Font("Helvetica",Font.BOLD,40));
			g.drawString("" + SlimeGames.p1score, 50, 100);
			g.drawString("" + SlimeGames.p2score, 1200 - 80, 100);


		}   

	}
}
