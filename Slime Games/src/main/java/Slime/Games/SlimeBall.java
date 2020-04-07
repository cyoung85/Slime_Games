package Slime.Games;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Formatter;

public class SlimeBall {
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
	
	public void moveWithColision(WindowBounds window) {
		//gets the bounds of the ball subtracted by the radius to prevent clipping into the wall
		float ballMinX = window.getMinX() + radius;
	    float ballMinY = window.getMinY() + radius;
	    float ballMaxX = window.getMaxX() - radius;
	    float ballMaxY = window.getMaxY() - radius;
	    
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
	      } else if (y > ballMaxY) {
	         ySpeed = -ySpeed;
	         y = ballMaxY;
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
