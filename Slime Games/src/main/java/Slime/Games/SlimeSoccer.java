package Slime.Games;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;

import javax.swing.*;

public class SlimeSoccer extends JFrame implements ActionListener,KeyListener{
	public final int ballxStart = 580;
	public final int ballyStart = 200;
	public int ballx = ballxStart;
	public int bally = ballyStart;
	public int balldx = 0;
	public int balldy = 0;
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
	public boolean lFalling = false;
	public boolean rFalling = false;
	public final int gravity = 1;
	
	
	
	private double defaultRoundTime = 10;
	private double roundTime = defaultRoundTime;
	private static Timer timer;
	public static final int TIMER_SPEED = 12;
	//private KeyListener key = null;
	
	//Frame Declarations
	SlimeSoccer(){
        setSize(1200, 700);
        setTitle("Slime Soccer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        
        timer = new Timer(12, this);
        timer.start();
        
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        //Slimes sl = new Slimes();
        
        //add(sl);
    }

    public static void main(String[] args) {
         SlimeSoccer m = new SlimeSoccer();
         
         m.repaint(); 
         
         
    }

    //@Override
    public void paint(Graphics g) 
    {
    	
    	Color light_blue = new Color(51,153,255);
    	g.setColor(light_blue);
        g.fillRect(0, 0,1200, 700 );
        Color dark_green = new Color(0,153,0);
        g.setColor(dark_green);
        g.fillRect(0, 600,1200, 100 );
        
        g.setColor(Color.white);
    	g.fillOval(ballx, bally, 40, 40);
    	
    	//left slime
        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
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
        
        
        
        g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica",Font.BOLD,40));
		g.drawString("" + SlimeGames.p1score, 50, 100);
		g.drawString("" + SlimeGames.p2score, 1200 - 80, 100);
        
        
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Helvetica",Font.BOLD,40));
		g.drawString("" + Math.round(roundTime), 1200/2 - 20, 80);
        
    }   
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SlimeGames.resetScore();
		move();
		falling();
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
				bally = ballyStart;
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
        	lFalling = false;
        }
        else {
        	
        	lCanJump = false;
        }
        if(righty==550) {
        	rCanJump= true;
        	rFalling = false;
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
        	rCanJump = true;
        	
        	rightdy = 0;
        }
        else {
        	rCanJump = false;
        }
        if(lefty>550) {
        	lefty = 550;
        	lCanJump = true;
        	
        	leftdy = 0;
        }
        else {
        	lCanJump = false;
        }
        if(bally>560) {
        	bally = 560;
        	balldy *= -.95;
        }
       
		leftx += leftdx;
        lefty += leftdy;
        rightx += rightdx;
        righty += rightdy;
        bally+=balldy;
        
    }
	
	public void falling() {
		if(lefty<550) {
			leftdy+=gravity;
		}
		if(righty<550) {
			rightdy+=gravity;
		}
		if(bally<560) {
			balldy+=gravity;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_A) {
			leftdx= -5;
		}
		if(key == KeyEvent.VK_W && lefty == 550) {
			
			leftdy= -20;
			lFalling = true;
			lCanJump = false;
		}
		if(key == KeyEvent.VK_D) {
			leftdx= 5;
		}
		if(key == KeyEvent.VK_LEFT) {
			rightdx= -5;
		}
		if(key == KeyEvent.VK_UP && righty == 550) {
			rightdy= -20;
			rFalling = true;
			rCanJump = false;
		}
		if(key == KeyEvent.VK_RIGHT) {
			rightdx= 5;
		}
		//repaint();
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
		//repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
