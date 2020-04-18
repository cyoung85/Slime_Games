/*
 * This class is used to create player 1's slime (AKA the left slime)
 * This also handles the movement for the slime and is implemneted in the SlimeSoccer class
 * to set up, Player_1(float for starting x cordinate, float for starting y cordinate)
 */


package Slime.Games;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

 
public class Player_1 extends Component implements ActionListener, KeyListener {
	private static int x;
	private static int y;
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
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
				int key = e.getKeyCode();
				
				if(key == KeyEvent.VK_A) {
					x += -5;
				}
				if(key == KeyEvent.VK_W) {
					y += -5;
				}
				if(key == KeyEvent.VK_D) {
					x += 5;
				}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
