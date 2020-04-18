package Slime.Games;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 * The control logic and main display panel for game.
 */
public class SlimeSoccer extends JPanel {
	private static final int UPDATE_RATE = 60; // Frames per second (fps)

	private SlimeBall ball; // A single bouncing SlimeBall instance
	private WindowBounds window; // The container rectangular box
	private Player_1 p1; // Player 1's variable
	private Player_2 p2;

	private DrawCanvas canvas; // Custom canvas for drawing the box/SlimeBall
	private int canvasWidth;
	private int canvasHeight;

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

	public SlimeSoccer(int width, int height) {

		canvasWidth = width;
		canvasHeight = height;

		Random rand = new Random();
		int radius = 50;
		int speed = 10;
		int angleInDegree = rand.nextInt(360);
		ball = new SlimeBall(ballx, bally, radius, speed, angleInDegree);

		// Init the Container Box to fill the screen
		window = new WindowBounds(canvasWidth, canvasHeight);

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
		ball.moveWithColision(window, p1);
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

			// creates goals
			Color light_grey = new Color(204, 204, 204);
			// left goal

			// horizontal lines
			g.setColor(light_grey);
			g.drawLine(0, 500, 100, 500);
			g.drawLine(0, 499, 100, 499);
			g.drawLine(0, 498, 100, 498);
			g.drawLine(0, 525, 100, 525);
			g.drawLine(0, 550, 100, 550);
			g.drawLine(0, 575, 100, 575);
			g.drawLine(0, 600, 100, 600);
			// vertical lines
			g.drawLine(0, 500, 0, 600);
			g.drawLine(25, 500, 25, 600);
			g.drawLine(50, 500, 50, 600);
			g.drawLine(75, 500, 75, 600);
			g.drawLine(100, 500, 100, 600);
			g.drawLine(99, 500, 99, 600);
			g.drawLine(98, 500, 98, 600);
			// right goal
			// horizontal lines
			g.setColor(light_grey);
			g.drawLine(1100, 500, 1200, 500);
			g.drawLine(1100, 499, 1200, 499);
			g.drawLine(1100, 498, 1200, 498);
			g.drawLine(1100, 525, 1200, 525);
			g.drawLine(1100, 550, 1200, 550);
			g.drawLine(1100, 575, 1200, 575);
			g.drawLine(1100, 600, 1200, 600);
			// vertical lines
			g.drawLine(1100, 500, 1100, 600);
			g.drawLine(1125, 500, 1125, 600);
			g.drawLine(1150, 500, 1150, 600);
			g.drawLine(1175, 500, 1175, 600);
			g.drawLine(1200, 500, 1200, 600);
			g.drawLine(1101, 500, 1101, 600);
			g.drawLine(1102, 500, 1102, 600);

			// displays scores
			g.setColor(Color.WHITE);
			g.setFont(new Font("Helvetica", Font.BOLD, 40));
			g.drawString("" + SlimeGames.p1score, 50, 100);
			g.drawString("" + SlimeGames.p2score, 1200 - 80, 100);

		}

	}
}