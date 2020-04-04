package Slime.Games;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

//import testPaint.testPaint;
public class SlimeSpleef extends JFrame implements ActionListener{
	//Frame Declarations
		SlimeSpleef(){
        setSize(1200, 700);
        setTitle("Slime Spleef");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
         SlimeSpleef m = new SlimeSpleef();
         m.repaint(); 
    }

    //@Override
    public void paint(Graphics g) 
    {
    	
    	Color light_blue = new Color(51,153,255);
    	g.setColor(light_blue);
        g.fillRect(0, 0,1200, 700 );
        
        Color dark_green = new Color(0,153,0);
        Color dark_red = new Color(204,0,0); // used for the hit spaces
        Color light_green = new Color(0,255,100);
        Color white = new Color(255,255,255);
        Color light_grey = new Color(204,204,204);
        
        //left 3 sections for spleef 
        g.setColor(dark_green); // default set to normal grass color
        g.fillRect(0, 600,199, 100 ); //left side left section
        //g.setColor(dark_red); 
        g.fillRect(200, 600,399, 100 ); //left side middle section
        g.setColor(dark_green);
        g.fillRect(400, 600,599, 100 ); //left side right section
        
        //right 3 sections for spleef
        //g.setColor(dark_red); 
        g.fillRect(600, 600,799, 100 ); //right side left section
        g.setColor(dark_green);
        g.fillRect(800, 600,999, 100 ); //right side middle section
        //g.setColor(dark_red); 
        g.fillRect(1000, 600,1200, 100 ); //right side right section
        
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SlimeGames.resetScore();
		//test.setText(test.getText().concat("You have clicked the button\n"));
	}
	
	
}
