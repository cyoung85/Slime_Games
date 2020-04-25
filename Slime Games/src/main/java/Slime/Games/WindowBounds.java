package Slime.Games;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class WindowBounds {
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	private String game;
	public boolean initialSpleefSetup = true;
	public WindowBounds(int width, int height,String gameType) {
		setMinX(0);
		setMinY(0);
		setMaxX(width - 1);
		setMaxY(height - 1);
		game = gameType;
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
		if (game == "soccer") {
			//creates background
			Color light_blue = new Color(51,153,255);
			g.setColor(light_blue);
			g.fillRect(0, 0,1200, 700 );
			Color dark_green = new Color(0,153,0);
			g.setColor(dark_green);
			g.fillRect(0, 600,1200, 100 );
		}
		else if (game == "basketball") {
			//wall
			Color white = new Color(255,255,255);
			g.setColor(white);
			g.fillRect(0, 0,1200, 700 );
			//floor
			Color dark_brown = new Color(134,73,5);
			g.setColor(dark_brown);
			g.fillRect(0, 600,1200, 100 );
			//sky through window
			Color sky = new Color(60,165,250);
			g.setColor(sky);
			g.fillRect(140,100,120,120);
			g.fillRect(540,100,120,120);
			g.fillRect(940,100,120,120);
			Color light_grey = new Color(204,204,204);
			g.setColor(light_grey);
			//Windows
			g.drawLine(140,100,140,220);
			g.drawLine(141,100,141,220);
			g.drawLine(139,100,139,220);
			g.drawLine(138,100,138,220);
			g.drawLine(137,100,137,220);
			g.drawLine(260,100,260,220);
			g.drawLine(137,220,260,220);
			g.drawLine(137,219,260,219);
			g.drawLine(137,218,260,218);
			g.drawLine(137,221,260,221);
			g.drawLine(140,160,260,160);
			g.drawLine(140,100,260,100);
			g.drawLine(140,101,260,101);
			g.drawLine(540,160,660,160);  
			g.drawLine(940,160,1060,160);
			g.drawLine(540,100,660,100);
			g.drawLine(540,100,540,220);
			g.drawLine(660,100,660,220);
			g.drawLine(940,100,940,220);
			g.drawLine(1060,100,1060,220);
			g.drawLine(1061,100,1061,220);
			g.drawLine(1059,100,1059,220);
			g.drawLine(1062,100,1062,220);
			g.drawLine(1063,100,1063,220);
			g.drawLine(540,101,660,101);
			g.drawLine(540,220,660,220);
			g.drawLine(540,221,660,221);
			g.drawLine(540,219,660,219);
			g.drawLine(540,218,660,218);
			g.drawLine(940,220,1060,220);
			g.drawLine(940,219,1063,219);
			g.drawLine(940,218,1063,218);
			g.drawLine(940,100,1063,100);
			g.drawLine(940,101,1063,101);
		}
		else if (game == "volleyball") {
			Color light_blue = new Color(51,153,255);
			g.setColor(light_blue);
			g.fillRect(0, 0,1200, 700 );
			Color sand = new Color(244,204,99);
			g.setColor(sand);
			g.fillRect(0, 600,1200, 100 );

			Color light_grey = new Color(204,204,204);
			g.setColor(light_grey);
			//volleyball net
			g.drawLine(598, 500, 598, 600);
			g.drawLine(599, 500, 599, 600);
			g.drawLine(600, 500, 600, 600);
			g.drawLine(601, 500, 601, 600);
		}
		else if (game == "spleef") {
			Color light_blue = new Color(51,153,255);
			Color dark_green = new Color(0,153,0); //used for the not hit spaces
			Color dark_red = new Color(204,0,0); // used for the hit spaces
			Color light_green = new Color(0,255,100);
			Color white = new Color(255,255,255);
			Color light_grey = new Color(204,204,204);
			
			if(initialSpleefSetup= true) {
				
				g.setColor(light_blue);
				g.fillRect(0, 0,1200, 700 );
				g.setColor(dark_green); // default set to normal grass color
				g.fillRect(0, 600,199, 100 ); //left side left section 
				g.fillRect(200, 600,399, 100 ); //left side middle section
				g.fillRect(400, 600,599, 100 ); //left side right section

				//right 3 sections for spleef 
				g.fillRect(600, 600,799, 100 ); //right side left section
				g.fillRect(800, 600,999, 100 ); //right side middle section
				g.fillRect(1000, 600,1200, 100 ); //right side right section
				initialSpleefSetup = false;
			}
			

			g.setColor(light_green);
			//middle divider on ground
			g.drawLine(599, 600, 599, 700);
			g.drawLine(600, 600, 600, 700);

			g.setColor(white);
			//left side section dividers
			g.drawLine(199, 600, 199, 700);
			g.drawLine(200, 600, 200, 700);
			g.drawLine(399, 600, 399, 700);
			g.drawLine(400, 600, 400, 700);

			//right side section dividers
			g.drawLine(799, 600, 799, 700);
			g.drawLine(800, 600, 800, 700);
			g.drawLine(999, 600, 999, 700);
			g.drawLine(1000, 600, 1000, 700);

			g.setColor(light_grey);
			//volleyball net
			g.drawLine(598, 500, 598, 600);
			g.drawLine(599, 500, 599, 600);
			g.drawLine(600, 500, 600, 600);
			g.drawLine(601, 500, 601, 600);
		}




	}

}
