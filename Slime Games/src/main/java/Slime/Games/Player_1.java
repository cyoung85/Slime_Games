/*
 * This class is used to create player 1's slime (AKA the left slime)
 * This also handles the movement for the slime and is implemneted in the SlimeSoccer class
 * to set up, Player_1(float for starting x cordinate, float for starting y cordinate)
 */


package Slime.Games;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;


@SuppressWarnings("serial")
public class Player_1 extends JComponent {
	private static int x;
	private static int y;
	public int dx = 9;
	public int dy = 9;
	private boolean touchedground = true;

	
	public Player_1(int x, int y) {
	Player_1.x = x;
	Player_1.y = y;
	}
	
	//creates the slime in the window
	public void draw(Graphics g){
        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        Color red = new Color(255,0,0);
        g.setColor(red);
        g.fillArc(x, y, 100, 100, 0, 180);
        g.setColor(white);
        g.fillOval(x+65, y+10, 15, 15);
        g.setColor(black);
        g.fillOval(x+70, y+10, 10, 10);
	}
	
	public int getX() {
		return x;
	}
	public int getY(){
		return y;
	}	
	public void setX(int n) {
		
		x = n;
	}
	public void setY(int n) {
		y = n;
	}
	
	public void moveLeft(){
		if(x <=0)
			return;
			x -= dx;
	}
	
	public void moveRight() {
		if (x >=1100)
			return;
		x += dx;
	}
	public void jump(boolean jumping) {
		if(y>=400 && touchedground) {
			y -= dy;
		}
		else{
			jumping = false;
			touchedground = false;
		}
	}
	
	public void inAir() {
		touchedground = false;
	}
	
	public boolean canJump(){
		if(touchedground)
			return true;
		return false;
	}
	public void touchedGround() {
		touchedground = true;
	}
	public void applyGravity() {
		y += dy;
	}
}