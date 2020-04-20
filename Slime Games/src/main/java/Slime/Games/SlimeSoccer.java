package Slime.Games;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 * The control logic and main display panel for game.
 */
public class SlimeSoccer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int UPDATE_RATE = 120; // Frames per second (fps)

	private SlimeBall ball; // A single bouncing SlimeBall instance
	private WindowBounds window; // The container rectangular box
	private Player_1 p1; // Player 1's variable
	private Player_2 p2;

	private boolean p1Left = false;
	private boolean p1Right = false;
	private boolean p1Jump = false;
	private boolean p2Left = false;
	private boolean p2Right = false;
	private boolean p2Jump = false;

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
		ball = new SlimeBall(ballx, bally, radius, speed, angleInDegree, "soccer ");

		// Init the Container Box to fill the screen
		window = new WindowBounds(canvasWidth, canvasHeight, "soccer");

		// Init Player 1
		p1 = new Player_1(p1XStart, p1YStart);
		p2 = new Player_2(p2XStart, p2YStart);
		// Init the custom drawing panel for drawing the game
		canvas = new DrawCanvas();

		this.setLayout(new BorderLayout());
		this.add(canvas, BorderLayout.CENTER);

		im = canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

		// sets up an input map for both player 1 and player 2 to move

		// set up for p1 to move
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "A Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "A Released");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "D Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "D Released");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "W Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "W Released");

		// set up for p2 to move
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "Left Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "Left Released");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "Right Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "Right Released");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "Up Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "Up Released");

		ap = canvas.getActionMap();
		// p1 left
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

		// p1 right
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

		// p2 left
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

		// p2 right
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
		ball.moveWithColision(window, p1, p2);
		if (p1Left)
			p1.moveLeft();
		if (p1Right)
			p1.moveRight();
		if (p1.getY() < 550 && !p1.canJump()) {
			p1.inAir();
			p1.applyGravity();
		}
		if (p1Jump)
			p1.jump(p1Jump);
		else
			p1.inAir();
		if (p2Left)
			p2.moveLeft();
		if (p2Right)
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
	}

	// makes sure the window loads in properly

	public Dimension getPreferredSize() {
		return (new Dimension(canvasWidth, canvasHeight));
	}

	// The custom drawing panel for the bouncing SlimeBall
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
