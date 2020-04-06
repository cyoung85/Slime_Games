package Slime.Games;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

//import testPaint.testPaint;
public class SlimeSpleef extends JFrame implements ActionListener,KeyListener{
	public final int ballxStart = 580;
	public final int ballyStart = 200;
	public int ballx = ballxStart;
	public int bally = ballyStart;
	public final int leftxStart = 150;
	public final int leftyStart = 550;
	public final int rightxStart = 950;
	public final int rightyStart = 550;
	public int leftx = leftxStart;
	public int lefty = leftyStart;
	public int rightx = rightxStart;
	public int righty = rightyStart;
	public int leftdx=0;
	public int leftdy=0;
	public int rightdx=0;
	public int rightdy=0;
	public boolean lCanJump = true;
	public boolean rCanJump = true;
	public boolean lFalling = true;
	public boolean rFalling = true;
	public final double gravity = 0.5;
	private double defaultRoundTime = 60;
	private double roundTime = defaultRoundTime;
	private static Timer timer;
	public static final int TIMER_SPEED = 12;
	
	//Frame Declarations
		SlimeSpleef(){
        setSize(1200, 700);
        setTitle("Slime Spleef");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        
        timer = new Timer(12, this);
        timer.start();
        addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        
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
        
        
        g.setColor(Color.white);
    	g.fillOval(ballx, bally, 40, 40);
    	
    	//left slime
        Color black = new Color(0,0,0);
        
        Color red = new Color(255,0,0);
        g.setColor(red);
        g.fillArc(leftx, lefty, 100, 100, 0, 180);
        g.setColor(white);
        g.fillOval(leftx+65, lefty+10, 15, 15);
        g.setColor(black);
        g.fillOval(leftx+70, lefty+10, 10, 10);
        //right slime
        Color yellow = new Color(255,255,0);
        g.setColor(yellow);
        g.fillArc(rightx, righty, 100, 100, 0, 180);
        g.setColor(white);
        g.fillOval(rightx+35, righty+10, 15, 15);
        g.setColor(black);
        g.fillOval(rightx+35, righty+10, 10, 10);
        
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
        
        
        g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica",Font.BOLD,40));
		g.drawString("" + SlimeGames.p1score, 50, 100);
		g.drawString("" + SlimeGames.p2score, 1200 - 80, 100);
        
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Helvetica",Font.BOLD,40));
		g.drawString("" + Math.round(roundTime), 1200/2 - 20, 80);
    }   
	
	
    public void actionPerformed(ActionEvent e) {
		SlimeGames.resetScore();
		move();
		//falling();
		//timer
		if ( roundTime > 0 ) {
			
			roundTime -= TIMER_SPEED * .001;
			repaint();
		} else {
			timer.stop();
			int winner = 0;
			if (SlimeGames.p1score > SlimeGames.p2score) {
				winner = 1;
			} else if (SlimeGames.p2score > SlimeGames.p1score){
				winner = 2;
			}
			String notification;
			if (winner == 0) {
				notification = "Tie! Play again?";
			} else {
				notification = "Player " + winner + " won. Play again?";
			}
			int input = JOptionPane.showOptionDialog( new JFrame(), notification, "Game Over", JOptionPane.YES_NO_OPTION,
					                      JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (input == JOptionPane.YES_OPTION){
				roundTime = defaultRoundTime;
				leftx = leftxStart;
				lefty = leftyStart;
				rightx = rightxStart;
				righty = rightyStart;
				leftdx= 0;
				leftdy= 0;
				leftdx= 0;
				rightdx= 0;
				rightdy= 0;
				timer.start();
			} else {
				System.exit(0);
			}
		}
		//repaint();
		//test.setText(test.getText().concat("You have clicked the button\n"));
	}
	public void move() {
        if(lefty==550) {
        	lCanJump= true;
        }
        else {
        	
        	lCanJump = false;
        }
        if(righty==550) {
        	rCanJump= true;
        }
        else {
        	
        	rCanJump = false;
        }
		if(leftx<0) {
        	leftx=0;
        }
        if(rightx<0) {
        	rightx = 0;
        }
        if(righty<0) {
        	righty = 0;
        }
        if(lefty<0) {
        	lefty = 0;
        }
        if(leftx>1100) {
        	leftx=1100;
        }
        if(rightx>1100) {
        	rightx = 1100;
        }
        if(righty>550) {
        	righty = 550;
        }
        if(lefty>550) {
        	lefty = 550;
        }
        
       
		leftx += leftdx;
        lefty += leftdy;
        rightx += rightdx;
        righty += rightdy;
        
    }
	/*
	public void falling() {
		if(lFalling) {
			leftdy+=gravity;
		}
		if(rFalling) {
			rightdy+=gravity;
		}
	}
	*/
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_A) {
			leftdx= -5;
		}
		if(key == KeyEvent.VK_W) {
			leftdy= -5;
		}
		if(key == KeyEvent.VK_D) {
			leftdx= 5;
		}
		if(key == KeyEvent.VK_LEFT) {
			rightdx= -5;
		}
		if(key == KeyEvent.VK_UP) {
			rightdy= -5;
		}
		if(key == KeyEvent.VK_RIGHT) {
			rightdx= 5;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A) {
			leftdx= 0;
		}
		if(key == KeyEvent.VK_W) {
			leftdy= 0;
		}
		if(key == KeyEvent.VK_D) {
			leftdx= 0;
		}
		if(key == KeyEvent.VK_LEFT) {
			rightdx= 0;
		}
		if(key == KeyEvent.VK_UP) {
			rightdy= 0;
		}
		if(key == KeyEvent.VK_RIGHT) {
			rightdx= 0;
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
