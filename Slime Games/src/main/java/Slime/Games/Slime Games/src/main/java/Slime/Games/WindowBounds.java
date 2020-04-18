package Slime.Games;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class WindowBounds {
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	
	
	public WindowBounds(int width, int height) {
		setMinX(0);
		setMinY(0);
		setMaxX(width - 1);
		setMaxY(height - 1);
	}
	public void set(int x, int y, int width, int height) {
		setMinX(x);
		setMinY(y);
	    setMaxX(x + width - 1);
	    setMaxY(y + height - 1);
	 }
	public int getMinX() {
		return minX;
	}
	public void setMinX(int minX) {
		this.minX = minX;
	}
	public int getMinY() {
		return minY;
	}
	public void setMinY(int minY) {
		this.minY = minY;
	}
	public int getMaxX() {
		return maxX;
	}
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}
	public int getMaxY() {
		return maxY;
	}
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
	public void draw(Graphics g) {
		//creates background
    	Color light_blue = new Color(51,153,255);
    	g.setColor(light_blue);
        g.fillRect(0, 0,1200, 700 );
        Color dark_green = new Color(0,153,0);
        g.setColor(dark_green);
        g.fillRect(0, 600,1200, 100 );
        
        /*
        //creates goals
        Color light_grey = new Color(204,204,204);
        //left goal
        //horizontal lines
        g.setColor(light_grey);
        g.drawLine(0, 500, 100, 500);
        g.drawLine(0, 499, 100, 499);
        g.drawLine(0, 498, 100, 498);
        g.drawLine(0, 525, 100, 525);
        g.drawLine(0, 550, 100, 550);
        g.drawLine(0, 575, 100, 575);
        g.drawLine(0, 600, 100, 600);
        //vertical lines
        g.drawLine(0, 500, 0, 600);
        g.drawLine(25, 500, 25, 600);
        g.drawLine(50, 500, 50, 600);
        g.drawLine(75, 500, 75, 600);
        g.drawLine(100, 500, 100, 600);
        g.drawLine(99, 500, 99, 600);
        g.drawLine(98, 500, 98, 600);
        //right goal
        //horizontal lines
        g.setColor(light_grey);
        g.drawLine(1100, 500, 1200, 500);
        g.drawLine(1100, 499, 1200, 499);
        g.drawLine(1100, 498, 1200, 498);
        g.drawLine(1100, 525, 1200, 525);
        g.drawLine(1100, 550, 1200, 550);
        g.drawLine(1100, 575, 1200, 575);
        g.drawLine(1100, 600, 1200, 600);
        //vertical lines
        g.drawLine(1100, 500, 1100, 600);
        g.drawLine(1125, 500, 1125, 600);
        g.drawLine(1150, 500, 1150, 600);
        g.drawLine(1175, 500, 1175, 600);
        g.drawLine(1200, 500, 1200, 600);
        g.drawLine(1101, 500, 1101, 600);
        g.drawLine(1102, 500, 1102, 600);
        */
        
        
	}
     
}
