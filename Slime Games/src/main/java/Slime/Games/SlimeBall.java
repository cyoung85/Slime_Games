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
	public static float x, y;
	public static float xSpeed, ySpeed;
	private static float radius;
	private static float gravity;
	private String game;
	private static float maxXSpeed=20,maxYSpeed=30;
	
	public static boolean sect1=false,sect2=false,sect3=false,sect4= false,sect5 = false,sect6 = false; //used for spleef



	public SlimeBall(float x, float y, float radius, float speed, float angleImpact, String gameType) {
		SlimeBall.x = x;
		SlimeBall.y = y;
		// Convert (speed, angle) to (x, y), with y-axis inverted
		SlimeBall.xSpeed = 0;//(float)(speed * Math.cos(Math.toRadians(angleImpact)));
		SlimeBall.ySpeed = 0;//(float)(-speed * (float)Math.sin(Math.toRadians(angleImpact)));
		SlimeBall.radius = radius;
		game = gameType;
		if (game=="basketball") {
			gravity = (float) 0.65;
		}
		else if (game =="soccer"){
			gravity = 1;
		}
		else {
			gravity = (float) 0.7;
			SlimeBall.x=200;
			SlimeBall.y=100;
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

		int p2X = p2.getX();
		int p2Y = p2.getY();

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
			if(game=="volleyball"&&x<600) {//volleyball scoring
				SlimeGames.p2score++;
				x = 190;//window.getMaxX()/2 -radius;
				y = 100;
				p1.setX(150);
				p1.setY(550);
				p2.setX(950);
				p2.setY(550);
				xSpeed = 0;
				ySpeed = 0;
			} else if(game=="volleyball"&&x>600) {
				SlimeGames.p1score++;
				x = 980;//window.getMaxX()/2 -radius;
				y = 100;
				p1.setX(150);
				p1.setY(550);
				p2.setX(950);
				p2.setY(550);
				xSpeed = 0;
				ySpeed = 0;
			} else if(game=="spleef"&&x<600) { //spleef scoring
				if (sect1&& x<200) {
					SlimeGames.p2score++;
					x = 190;//window.getMaxX()/2 -radius;
					y = 100;
					p1.setX(150);
					p1.setY(550);
					p2.setX(950);
					p2.setY(550);
					xSpeed = 0;
					ySpeed = 0;
				} else if(x<200) {
					x = 190;//window.getMaxX()/2 -radius;
					y = 100;
					p1.setX(150);
					p1.setY(550);
					p2.setX(950);
					p2.setY(550);
					xSpeed = 0;
					ySpeed = 0;
					sect1 = true;
				}
				else if (sect2&& x>=200&&x<400) {
					SlimeGames.p2score++;
					x = 190;//window.getMaxX()/2 -radius;
					y = 100;
					p1.setX(150);
					p1.setY(550);
					p2.setX(950);
					p2.setY(550);
					xSpeed = 0;
					ySpeed = 0;
				} else if(x>=200&&x<400) {
					x = 190;//window.getMaxX()/2 -radius;
					y = 100;
					p1.setX(150);
					p1.setY(550);
					p2.setX(950);
					p2.setY(550);
					xSpeed = 0;
					ySpeed = 0;
					sect2 = true;
				}
				else if (sect3&&x>=400&&x<600) {
					SlimeGames.p2score++;
					x = 190;//window.getMaxX()/2 -radius;
					y = 100;
					p1.setX(150);
					p1.setY(550);
					p2.setX(950);
					p2.setY(550);
					xSpeed = 0;
					ySpeed = 0;
				} else if(x>=400&&x<600) {
					x = 190;//window.getMaxX()/2 -radius;
					y = 100;
					p1.setX(150);
					p1.setY(550);
					p2.setX(950);
					p2.setY(550);
					xSpeed = 0;
					ySpeed = 0;
					sect3 = true;
				}

			} else if(game=="spleef"&&x>600) {
				if (sect4&& x>=600&&x<800) {
					SlimeGames.p1score++;
					x = 980;//window.getMaxX()/2 -radius;
					y = 100;
					p1.setX(150);
					p1.setY(550);
					p2.setX(950);
					p2.setY(550);
					xSpeed = 0;
					ySpeed = 0;
				} else if(x>=600&&x<800) {
					x = 980;//window.getMaxX()/2 -radius;
					y = 100;
					p1.setX(150);
					p1.setY(550);
					p2.setX(950);
					p2.setY(550);
					xSpeed = 0;
					ySpeed = 0;
					sect4 = true;
				}
				else if (sect5&& x>=800&&x<1000) {
					SlimeGames.p1score++;
					x = 980;//window.getMaxX()/2 -radius;
					y = 100;
					p1.setX(150);
					p1.setY(550);
					p2.setX(950);
					p2.setY(550);
					xSpeed = 0;
					ySpeed = 0;
				} else if(x>=800&&x<1000) {
					x = 980;//window.getMaxX()/2 -radius;
					y = 100;
					p1.setX(150);
					p1.setY(550);
					p2.setX(950);
					p2.setY(550);
					xSpeed = 0;
					ySpeed = 0;
					sect5 = true;
				}
				else if (sect6&&x>=1000) {
					SlimeGames.p1score++;
					x = 980;//window.getMaxX()/2 -radius;
					y = 100;
					p1.setX(150);
					p1.setY(550);
					p2.setX(950);
					p2.setY(550);
					xSpeed = 0;
					ySpeed = 0;
				} else if(x>=1000) {
					x = 980;//window.getMaxX()/2 -radius;
					y = 100;
					p1.setX(150);
					p1.setY(550);
					p2.setX(950);
					p2.setY(550);
					xSpeed = 0;
					ySpeed = 0;
					sect6 = true;
				}

			}

			else {
				ySpeed *= -0.85;//friction;
				y = ballMaxY-100;
				xSpeed *= 0.90; //friction
			}


		} else if (y<ballMaxY-101){
			if (ySpeed<=maxYSpeed)
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
				xSpeed = 0;
				ySpeed = 0;
			}
			if(x>1100&&x<1200&&y>500&&y<600) { //right goal scoring
				SlimeGames.p1score++;
				x = window.getMaxX()/2 -radius;
				y = 100;
				p1.setX(150);
				p1.setY(550);
				p2.setX(950);
				p2.setY(550);
				xSpeed = 0;
				ySpeed = 0;
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
				xSpeed = 0;
				ySpeed = 0;
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
				xSpeed = 0;
				ySpeed = 0;
			}
			if (x>1090&&x<1175&&y>390&&y<420&&ySpeed<0) { //right hoop bounce back under rim
				ySpeed *= -1;
			}
			if (x>1174&&x<1190&&y<420&&y>350) {  //right hoop back board
				xSpeed *= -1;
			}
		}
		//the middle boundary of limiting slimes
		else if(game == "volleyball") {
			if(p1X>500)
				p1.setX(500);
			if(p2X<600)
				p2.setX(600);
			if (x>=585&&x<=615&&y>500&&y<600) {
				xSpeed *= -1;
			}
		}
		else if(game == "spleef") {
			if(p1X>500)
				p1.setX(500);
			if(p2X<600)
				p2.setX(600);
			if (x>=585&&x<=615&&y>500&&y<600) {
				xSpeed *= -1;
			}
		}
		
		//collision between ball and slimes
		if ((x<=p1X+20&&x>=p1X-10)&&(y>=p1Y+20&&y<=p1Y+50)) { //left side p1
			xSpeed = (float) (Math.abs(xSpeed) * (-1.5));
			if(xSpeed>maxXSpeed)
				xSpeed = maxXSpeed;
			else if(xSpeed<(-1*maxXSpeed))
				xSpeed = -1*maxXSpeed;
			else if (xSpeed>-1&&xSpeed<1)
				xSpeed = -5;
		}
		if ((x<=p2X+20&&x>=p2X-10)&&(y>=p2Y+20&&y<=p2Y+50)) { //left side p2
			xSpeed = (float) (Math.abs(xSpeed) * (-1.5));
			if(xSpeed>maxXSpeed)
				xSpeed = maxXSpeed;
			else if(xSpeed<(-1*maxXSpeed))
				xSpeed = -1*maxXSpeed;
			else if (xSpeed>-1&&xSpeed<1)
				xSpeed = -5;
		}
		if ((x<=p1X+110&&x>=p1X+80)&&(y>=p1Y+20&&y<=p1Y+50)) {//right side p1
			xSpeed = (float) (Math.abs(xSpeed) * (1.5));
			if(xSpeed>maxXSpeed)
				xSpeed = maxXSpeed;
			else if(xSpeed<(-1*maxXSpeed))
				xSpeed = -1*maxXSpeed;
			else if (xSpeed>-1&&xSpeed<1)
				xSpeed = 5;
		}
		if ((x<=p2X+110&&x>=p2X+80)&&(y>=p2Y+20&&y<=p2Y+50)) { //right side p2
			xSpeed = (float) (Math.abs(xSpeed) * (1.5));
			if(xSpeed>maxXSpeed)
				xSpeed = maxXSpeed;
			else if(xSpeed<(-1*maxXSpeed))
				xSpeed = -1*maxXSpeed;
			else if (xSpeed>-1&&xSpeed<1)
				xSpeed = 5;
		}
		if ((x<=p1X+75&&x>=p1X+25)&&(y>=p1Y-10&&y<=p1Y+40)) {// top middle p1
			ySpeed = -20;
		}
		if ((x<=p2X+105&&x>=p2X-5)&&(y>=p2Y-10&&y<=p2Y+40)) {// top middle p2
			ySpeed = -20;
		}
		if ((x<=p1X+105&&x>=p1X+75)&&(y>=p1Y-10&&y<=p1Y+20)) {// top right p1
			ySpeed = -20;
			xSpeed = (float) (Math.abs(xSpeed) * (1.5));
			if(xSpeed>(maxXSpeed/1.1))
				xSpeed = (float) (maxXSpeed/1.1);
			else if(xSpeed<(-1*maxXSpeed/1.1))
				xSpeed = (float) (-1*maxXSpeed/1.1);
			else if (xSpeed>-1&&xSpeed<1)
				xSpeed = 10;
		}
		if ((x<=p2X+105&&x>=p2X+75)&&(y>=p2Y-10&&y<=p2Y+20)) {// top right p2
			ySpeed = -20;
			xSpeed = (float) (Math.abs(xSpeed) * (1.5));
			if(xSpeed>(maxXSpeed/1.1))
				xSpeed = (float) (maxXSpeed/1.1);
			else if(xSpeed<(-1*maxXSpeed/1.1))
				xSpeed = (float) (-1*maxXSpeed/1.1);
			else if (xSpeed>-1&&xSpeed<1)
				xSpeed = 10;
		}
		if ((x<=p1X+25&&x>=p1X-5)&&(y>=p1Y-10&&y<=p1Y+20)) {// top left p1
			ySpeed = -20;
			xSpeed = (float) (Math.abs(xSpeed) * (-1.5));
			if(xSpeed>(maxXSpeed/1.1))
				xSpeed = (float) (maxXSpeed/1.1);
			else if(xSpeed<(-1*maxXSpeed/1.1))
				xSpeed = (float) (-1*maxXSpeed/1.1);
			else if (xSpeed>-1&&xSpeed<1)
				xSpeed = -10;
		}
		if ((x<=p2X+25&&x>=p2X-5)&&(y>=p2Y-10&&y<=p2Y+20)) {// top left p2
			ySpeed = -20;
			xSpeed = (float) (Math.abs(xSpeed) * (-1.5));
			if(xSpeed>(maxXSpeed/1.1))
				xSpeed = (float) (maxXSpeed/1.1);
			else if(xSpeed<(-1*maxXSpeed/1.1))
				xSpeed = (float) (-1*maxXSpeed/1.1);
			else if (xSpeed>-1&&xSpeed<1)
				xSpeed = -10;
		}
		if ((x<=p1X+80&&x>=p1X+20)&&(y>=p1Y+40&&y<=p1Y+60)) {// bottom p1
			ySpeed = 20;
		}
		if ((x<=p2X+80&&x>=p2X+20)&&(y>=p2Y+40&&y<=p2Y+60)) {// bottom p2
			ySpeed = 20;
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
