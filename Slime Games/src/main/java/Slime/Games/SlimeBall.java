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

import javax.swing.JComponent;
import javax.swing.JFrame;

public class SlimeBall extends JComponent {
	private static float x, y;
	private static float xSpeed, ySpeed;
	private static float radius;
	private static float gravity;
	private String game;
	
	public SlimeBall(float x, float y, float radius, float speed, float angleImpact, String gameType) {
		 SlimeBall.x = x;
	     SlimeBall.y = y;
	     // Convert (speed, angle) to (x, y), with y-axis inverted
	     SlimeBall.xSpeed = (float)(speed * Math.cos(Math.toRadians(angleImpact)));
	     SlimeBall.ySpeed = (float)(-speed * (float)Math.sin(Math.toRadians(angleImpact)));
	     SlimeBall.radius = radius;
	     game = gameType;
	     if (game=="basketball") {
	    	 gravity = (float) 0.5;
	     }
	     else if (game =="soccer"){
	    	 gravity = 1;
	     }
	     else {
	    	 gravity = (float) 0.75;
	     }
	}
	
	// creates a magenta ball (color just for testing)
	public void draw(Graphics g)
	{
		g.setColor(Color.MAGENTA);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
	} 
	
	public void moveWithColision(WindowBounds window, Player_1 p1, Player_2 p2) {
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
	    	  	if(game=="volleyball"&&x<600) {
	    	  		SlimeGames.p2score++;
	    	  		x = window.getMaxX()/2 -radius;
			    	y = 100;
	    	  	} else if(game=="volleyball"&&x>600) {
	    	  		SlimeGames.p1score++;
	    	  		x = window.getMaxX()/2 -radius;
			    	y = 100;
	    	  	}
	    	  	else {
	    	  		ySpeed *= -0.85;//friction;
	    	  		y = ballMaxY-100;
	    	  		xSpeed *= 0.90; //friction
	    	  	}
	    	  
	        
	      } else if (y<ballMaxY-101){
	    	  ySpeed+=gravity;
	    	  
	      }
	    //goal interactions
	    if (game == "soccer") {
	    	if(x>0&&x<105&&y<500&&y>475) {//left goal top
		    	ySpeed *= -0.95;
		    }	
		    if(x>1095&&x<1200&&y<500&&y>475) {//right goal top
		    	ySpeed *= -0.95;
		    }	
		    if(x<100&&y>500&&y<600) { //left goal scoring
		    	SlimeGames.p2score++;
		    	x = window.getMaxX()/2 -radius;
		    	y = 100;
		    	p1.setX(150);
		    	p1.setY(550);
		    	p2.setX(950);
		    	p2.setY(550);
		    }
		    if(x>1100&&x<1200&&y>500&&y<600) { //right goal scoring
		    	SlimeGames.p1score++;
		    	x = window.getMaxX()/2 -radius;
		    	y = 100;
		    	p1.setX(150);
		    	p1.setY(550);
		    	p2.setX(950);
		    	p2.setY(550);
		    }
	    }
	    else if(game == "basketball") {
	    	if (x>25&&x<100&&y>390&&y<420&&ySpeed>0) { //left hoop scoring
	    		SlimeGames.p2score++;
		    	x = window.getMaxX()/2 -radius;
		    	y = 100;
		    	p1.setX(150);
		    	p1.setY(550);
		    	p2.setX(950);
		    	p2.setY(550);
	    	}
	    	if (x>25&&x<110&&y>390&&y<420&&ySpeed<0) { //left hoop bounce back under rim
	    		ySpeed *= -1;
	    	}
	    	if (x>10&&x<26&&y<420&&y>350) { //left hoop back board
	    		xSpeed *= -1;
	    	}
	    	if (x>1100&&x<1175&&y>390&&y<420&&ySpeed>0) { //right hoop scoring
	    		SlimeGames.p1score++;
		    	x = window.getMaxX()/2 -radius;
		    	y = 100;
		    	p1.setX(150);
		    	p1.setY(550);
		    	p2.setX(950);
		    	p2.setY(550);
	    	}
	    	if (x>1090&&x<1175&&y>390&&y<420&&ySpeed<0) { //right hoop bounce back under rim
	    		ySpeed *= -1;
	    	}
	    	if (x>1174&&x<1190&&y<420&&y>350) {  //right hoop back board
	    		xSpeed *= -1;
	    	}
	    }
	    else if(game == "volleyball") {
	    	
	    }
	    else if(game == "spleef") {
	    	
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
