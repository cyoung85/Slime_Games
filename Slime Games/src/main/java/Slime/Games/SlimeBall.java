/*
 * This is to set up the ball along with it's collision detection.
 * to use, SlimeBall (float for starting x cordinate, float for starting y cordinate, Float values for the radius, float for the speed, Float for the angle of impact)
 */


package Slime.Games;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Formatter;

import javax.swing.JFrame;

public class SlimeBall extends Component {
	private static float x, y;
	private static float xSpeed, ySpeed;
	private static float radius;
	
	public SlimeBall(float x, float y, float radius, float speed, float angleImpact) {
		 SlimeBall.x = x;
	     SlimeBall.y = y;
	     // Convert (speed, angle) to (x, y), with y-axis inverted
	     SlimeBall.xSpeed = (float)(speed * Math.cos(Math.toRadians(angleImpact)));
	     SlimeBall.ySpeed = (float)(-speed * (float)Math.sin(Math.toRadians(angleImpact)));
	     SlimeBall.radius = radius;
	}
	
	// creates a magenta ball (color just for testing)
	public void draw(Graphics g)
	{
		g.setColor(Color.MAGENTA);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
	}
	
	public void moveWithColision(WindowBounds window, Player_1 p1) {
		//gets the bounds of the ball subtracted by the radius to prevent clipping into the wall
		float ballMinX = window.getMinX() + radius;
	    float ballMinY = window.getMinY() + radius;
	    float ballMaxX = window.getMaxX() - radius;
	    float ballMaxY = window.getMaxY() - radius;
	    
	    //gets the location of player 1
	    int p1X = p1.getX();
	    int p1Y = p1.getY();
	    
	    // gets the new position for the ball after each frame
	    x += xSpeed;
	    y += ySpeed;
	    
	    
	    //checking for collision and then reflecting the ball off of that wall
	    if (x < ballMinX) {
	         xSpeed = -xSpeed; // reflect along normal
	         x = ballMinX;     // re-position the ball at the edge
	      } else if (x > ballMaxX) {
	         xSpeed = -xSpeed;
	         x = ballMaxX;
	      }
	    if (y < ballMinY) {
	         ySpeed = -ySpeed;
	         y = ballMinY;
	      } else if (y > ballMaxY-100) {
	         ySpeed = -ySpeed;
	         y = ballMaxY-100;
	      }
	}
	 // Return the magnitude of speed. 
	   public float getSpeed() {
	      return (float)Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2));
	   }
	   // Return the direction of movement in degrees (counter-clockwise). 
	   public float getMoveAngle() {
	      return (float)Math.toDegrees(Math.atan2(-ySpeed, xSpeed));
	   }
}
