package Slime.Games;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SlimeBasketball extends JFrame implements ActionListener,KeyListener{
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
	
	SlimeBasketball(){
        setSize(1200, 700);
        setTitle("Slime Basketball");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        
        timer = new Timer(12, this);
        timer.start();
        addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
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
        
        
        g.setColor(Color.orange);
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
       
        
        
        g.setColor(Color.BLACK);
		g.setFont(new Font("Helvetica",Font.BOLD,40));
		g.drawString("" + SlimeGames.p1score, 50, 100);
		g.drawString("" + SlimeGames.p2score, 1200 - 80, 100);
        
        g.setColor(Color.BLACK);
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
