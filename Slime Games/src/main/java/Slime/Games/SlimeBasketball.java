package Slime.Games;


import javax.swing.*;
import java.awt.*;


public class SlimeBasketball extends JFrame{
    SlimeBasketball(){
        setSize(1200, 700);
        setTitle("Slime Basketball");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    } // setting JFrame title etc..

    public static void main(String[] args) {
         SlimeBasketball m = new SlimeBasketball();
         m.repaint(); 
    } //main method

    @Override
    public void paint(Graphics g) 
    {
    	Color white = new Color(255,255,255);
    	g.setColor(white);
        g.fillRect(0, 0,1200, 700 );
        Color dark_brown = new Color(134,73,5);
        g.setColor(dark_brown);
        g.fillRect(0, 600,1200, 100 );
        
        Color sky = new Color(60,165,250);
        g.setColor(sky);
        g.fillRect(140,100,120,120);
        g.fillRect(540,100,120,120);
        g.fillRect(940,100,120,120);
        
        Color light_grey = new Color(204,204,204);
        g.setColor(light_grey);
        //Nets
        //left
        g.drawLine(30,408,95,408);
        g.drawLine(35,416,90,416);
        g.drawLine(39,424,86,424);
        g.drawLine(43,432,82,432);
        g.drawLine(46,440,79,440);
        g.drawLine(46,400,46,440);
        g.drawLine(79,400,79,440);
        g.drawLine(63,400,63,440);
        g.drawLine(25,400,46,440);
        g.drawLine(100,400,79,440);
        //right
        g.drawLine(1170,408,1105,408);
        g.drawLine(1165,416,1110,416);
        g.drawLine(1161,424,1114,424);
        g.drawLine(1157,432,1118,432);
        g.drawLine(1154,440,1121,440);
        g.drawLine(1121,400,1121,440);
        g.drawLine(1154,400,1154,440);
        g.drawLine(1137,400,1137,440);
        g.drawLine(1175,400,1154,440);
        g.drawLine(1100,400,1121,440);
        
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
        
        
        Color black = new Color(0,0,0);
        g.setColor(black);
        //left rim
        g.drawLine(25,400,100,400);
        g.drawLine(25,399,100,399);
        g.drawLine(25,401,100,401);
        g.drawLine(25,370,25,600);
        g.drawLine(24,370,24,600);
        g.drawLine(26,370,26,600);
        //right rim
        g.drawLine(1175,400,1100,400);
        g.drawLine(1175,399,1100,399);
        g.drawLine(1175,401,1100,401);
        g.drawLine(1175,370,1175,600);
        g.drawLine(1174,370,1174,600);
        g.drawLine(1176,370,1176,600);
       
        
    }

}
