/*
 * This class is used to create player 1's slime (AKA the left slime)
 * This also handles the movement for the slime and is implemneted in the SlimeSoccer class
 * to set up, Player_1(float for starting x cordinate, float for starting y cordinate)
 */


package Slime.Games;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.InputMap;
import javax.swing.JComponent;


public class Player_2 extends JComponent{
	private static int x;
	private static int y;
	public int dx = 10;
	public int dy;
	
	public InputMap am;
	
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
	public void moveLeft(){
		x -= dx;
	}
	
	public void moveRight() {
		x += dx;
	}
	
}

