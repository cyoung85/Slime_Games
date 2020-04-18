package Slime.Games;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
/**
 * The control logic and main display panel for game.
 */
public class SlimeSoccer extends JPanel{
	private static final int UPDATE_RATE = 30;  // Frames per second (fps)
   
   	private SlimeBall ball;         // A single bouncing SlimeBall instance
   	private WindowBounds window;  	// The container rectangular box
   	private Player_1 p1;			// Player 1's variable
  
   	private DrawCanvas canvas; // Custom canvas for drawing the box/SlimeBall
   	private int canvasWidth;
   	private int canvasHeight;
   	
   	//the starting location for the ball
   	public final int ballxStart = 580;
   	public final int ballyStart = 200;
   	public int ballx = ballxStart;
   	public int bally = ballyStart;
   
   	//start for player 1
	public final int p1XStart = 150;
	public final int p1YStart = 550;
	
	//start for player 2
	public final int rightxStart = 950;
	public final int rightyStart = 550;
	
	public int p1X = p1XStart;
	public int p1Y = p1YStart;
	public int rightx = rightxStart;
	public int righty = rightyStart;
	public int leftdx=0;
	public int leftdy=0;
	public int rightdx=0;
	public int rightdy=0;
   
   
   public SlimeSoccer(int width, int height) {
  
      canvasWidth = width;
      canvasHeight = height;
      
      
      Random rand = new Random();
      int radius = 20;
      int speed = 10;
      int angleInDegree = rand.nextInt(360);
      ball = new SlimeBall(ballx, bally, radius, speed, angleInDegree);
     
      // Init the Container Box to fill the screen
      window = new WindowBounds(canvasWidth, canvasHeight);
     
      // Init Player 1
      p1 = new Player_1(p1X,p1Y);
      
      // Init the custom drawing panel for drawing the game
      canvas = new DrawCanvas();
      
      this.setLayout(new BorderLayout());
      this.add(canvas, BorderLayout.CENTER);
      
      // Handling window resize.
      this.addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent e) {
            Component c = (Component)e.getSource();
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
               } catch (InterruptedException ex) {}
            }
         }
      };
      gameThread.start();  // Invoke GaemThread.run()
   }
   
   /** 
    * One game time-step. 
    * Update the game objects, with proper collision detection and response.
    */
   public void gameUpdate() {
      ball.moveWithColision(window,p1);
   }
   
   //makes sure the window loads in properly
   
   public Dimension getPreferredSize() {
       return (new Dimension(canvasWidth, canvasHeight));
   }
   
   
   /** The custom drawing panel for the bouncing SlimeBall (inner class). */
   class DrawCanvas extends JPanel {
      /** Custom drawing codes */
      @Override
      public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paint background
        // Draw the box and the SlimeBall
        window.draw(g);
        ball.draw(g);
        p1.draw(g);
        //displays scores
        g.setColor(Color.WHITE);
 		g.setFont(new Font("Helvetica",Font.BOLD,40));
 		g.drawString("" + SlimeGames.p1score, 50, 100);
 		g.drawString("" + SlimeGames.p2score, 1200 - 80, 100);
        
      	}
  
   }
}
