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
public class SlimeVolleyball extends JFrame implements ActionListener{
	//Frame Declarations
		SlimeVolleyball(){
        setSize(1200, 700);
        setTitle("Slime Volleyball");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
         SlimeVolleyball m = new SlimeVolleyball();
         m.repaint(); 
    }

    //@Override
    public void paint(Graphics g) 
    {
    	
    	Color light_blue = new Color(51,153,255);
    	g.setColor(light_blue);
        g.fillRect(0, 0,1200, 700 );
        Color sand = new Color(244,204,99);
        //Color dark_green = new Color(0,153,0);
        g.setColor(sand);
        g.fillRect(0, 600,1200, 100 );
        
        
        Color light_grey = new Color(204,204,204);
        g.setColor(light_grey);
        
        //volleyball net
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