/*
 * This class is used to create player 2's slime (AKA the right slime)
 * This also handles the movement for the slime and is implemneted in the SlimeSoccer class
 * to set up, Player_2(float for starting x cordinate, float for starting y cordinate)
 */


package Slime.Games;

import java.awt.Color;
import java.awt.Graphics;
//import javax.swing.InputMap;
import javax.swing.JComponent;


public class Player_2 extends JComponent{
	private static int x;
	private static int y;
	public int dx = 10;
	public int dy = 10;
	private boolean touchedground = true;
	
	//public InputMap am;
	
	public Player_2(int x, int y) {
	Player_2.x = x;
	Player_2.y = y;
	}
	
	//creates the slime in the window
	public void draw(Graphics g){
        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        Color yellow = new Color(255,255,0);
        g.setColor(yellow);
        g.fillArc(x, y, 100, 100, 0, 180);
        g.setColor(white);
        g.fillOval(x+35, y+10, 15, 15);
        g.setColor(black);
        g.fillOval(x+35, y+10, 10, 10);
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

