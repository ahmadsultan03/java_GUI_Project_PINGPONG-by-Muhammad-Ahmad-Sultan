package java_GUI_Project_PINGPONG;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
//Runnable interface allows GamePanel to run on a thread
public class GamePanel extends JPanel implements Runnable{
	//properties, private access specifier
	
	//All instances of GamePanel will use the same 
	//game Width and Height and won't be changed.
	private static final int GAME_WIDTH=1000;
	private static final int GAME_HEIGHT=(int)(GAME_WIDTH*(5.0/9.0));
    //Dimension property, Dimension of the Table, requires a Width and Height to be passed
	private static final Dimension SCREEN_SIZE = new Dimension(GamePanel.GAME_WIDTH,GamePanel.GAME_HEIGHT);
	//Diameter of the Ball
	private static final int BALL_DIAMETER=20; //20 pixels
	//Paddle Dimension, Width
	private static final int PADDLE_WIDTH=25;
	//Paddle Dimension, Height
	private static final int PADDLE_HEIGHT=100;
	//Declare Thread instance to utilze multithreading features
	private Thread GameThread;
	//Holds Image of the PING PONG Table
	private Image image;
	private Graphics graphics;
	//Random generator instance
	private Random random;
	//Containment of Paddle
	private Paddle Paddle1,Paddle2;
	//Containment of ball
	private Ball ball;
	//Containment of Score
	private Score score;
	//public access specifier
	public GamePanel()
	{
		//Set the background color of the Panel to Black
		this.setBackground(new Color(0,25,51));
		//Call the newPaddles() method to create the Paddles
		this.newPaddles();
		//Call the NewBall() method to create the ping pong balls
		this.newBall();
		//Instantiate the score
		score = new Score(GamePanel.GAME_WIDTH,GamePanel.GAME_HEIGHT);
		//Set focusable of the Panel to true so that when you press any keys, its going to have focus then i.e. it will read the 
		//Key Presses or the Key strokes
		this.setFocusable(true);
		//add an Action Listener to respond to keystrokes
		this.addKeyListener(new ActionListener());
		//Set the prefered size of the Panel
		this.setPreferredSize(GamePanel.SCREEN_SIZE);
		//instantiate GameThread
		GameThread = new Thread(this); //pass in this object as its implementing the Runnable interface
		//Start the gameThread
		GameThread.start();
		
	}
	
	//Methods
	public void newBall()
	{
		//This method creates a new ball on screen
		
		//Finish instantiating the Random instance
		random = new Random();
		
		//The Ball will start at the very centre of the window but with a random height,
		//so we are going to pass in the coordinates of the ball
		//to our ball constructor
		ball = new Ball((GamePanel.GAME_WIDTH/2)-(GamePanel.BALL_DIAMETER/2),random.nextInt(GamePanel.GAME_HEIGHT-GamePanel.BALL_DIAMETER),GamePanel.BALL_DIAMETER,GamePanel.BALL_DIAMETER); //to accomodate ball, you must subtract radius from width and height
		
	}
	
	public void newPaddles()
	{
		//Create a new Paddle on screen
		//Instantiate Paddle1
		Paddle1 = new Paddle(0,(GamePanel.GAME_HEIGHT/2)-(GamePanel.PADDLE_HEIGHT/2),GamePanel.PADDLE_WIDTH,GamePanel.PADDLE_HEIGHT,1);
		Paddle2 = new Paddle(GamePanel.GAME_WIDTH-GamePanel.PADDLE_WIDTH,(GamePanel.GAME_HEIGHT/2)-(GamePanel.PADDLE_HEIGHT/2),GamePanel.PADDLE_WIDTH,GamePanel.PADDLE_HEIGHT,2);
	}
	
	@Override
	public void paint(Graphics g)
	{
		//This method paints stuff on screen
		//Take the image and create an image that has the dimensions of the width and height of the GamePanel
		image = this.createImage(getWidth(),getHeight()); //image of the Panel
		//take the graphics and set it equal to image.getGraphics
		graphics = image.getGraphics(); //convert the image to graphics
		//Call the draw method to draw all of the components
		this.draw(graphics); //Draw the graphics
		//Take g and draw the image
		//          image,x,y,Panel
		g.drawImage(image,0,0,this); //Draw the image
	}
	
	
	public void draw(Graphics g)
	{
		//Draw the Paddle1
		this.Paddle1.draw(g);
		//Draw the Paddle2
		this.Paddle2.draw(g);
		//Draw the ball
		this.ball.draw(g);
		//Draw the Score
		this.score.draw(g);
	}
	
	public void move()
	{
		//Method to move things after each iteration of the game loop
		//Make the movement of the paddles more smooth, call
		//each of the individual move methods after each iteration
		//of our game loop
		
		//Make the Paddles move smoothly
		this.Paddle1.move(); //moves paddles with high refreshrate
		this.Paddle2.move();
		
		//Make the Ball move Smoothly
		this.ball.move();
		
		
	}
	
	public void checkCollision()
	{
		//Used to check collision of objects
		
		//Bounce ball off top and bottom window edges, prevents the ball from
		//disappearing into window frame
		
		if(ball.y <= 0) //check if ball exceeds downleft corner
		{
			ball.setYDirection(-1*ball.YVelocity); //ball goes in opposite direction
		}
		if(ball.y >= GamePanel.GAME_HEIGHT-GamePanel.BALL_DIAMETER)
		{
			ball.setYDirection(-1*ball.YVelocity);
		}
		
		//This method ensures to bounce ball of the paddles
		
		//Check if the ball is intersecting with Paddle1
		if(this.ball.intersects(Paddle1))
		{
			//the intersects method is inherited from Rectangle class
			//this method checks if the two Ractangles i.e. Paddle and ball
			//intersect or not
			
			//Reverse the xVelocity of the ball,
			this.ball.XVelocity = Math.abs(this.ball.XVelocity); //this.ball.XVelocity=-1*this.XVelocity
			
			//[OPTIONAL CODE BELOW], increase the challenge for the players
			//Increase the Velocity of the ball after colliding with Paddle
			this.ball.XVelocity++;
			//Check if the YVelocity of your ball is greater than zero
			if(this.ball.YVelocity>0)
			{
				this.ball.YVelocity++; //optional, increase +ve YVelocity
			}
			else
			{
				this.ball.YVelocity--; //optional, increase -ve YVelocity
			}
			this.ball.setXDirection(this.ball.XVelocity);
			this.ball.setYDirection(this.ball.YVelocity);
			
		}
		
		//Check if the ball is intersecting with Paddle2
				if(this.ball.intersects(Paddle2))
				{
					//the intersects method is inherited from Rectangle class
					//this method checks if the two Ractangles i.e. Paddle and ball
					//intersect or not
					
					//Reverse the XVelocity of the ball,
					this.ball.XVelocity = Math.abs(this.ball.XVelocity); //this.ball.XVelocity=-1*this.XVelocity
					
					//[OPTIONAL CODE BELOW], increase the challenge for the players
					//Increase the Velocity of the ball after colliding with Paddle
					this.ball.XVelocity++;
					//Check if the YVelocity of your ball is greater than zero
					if(this.ball.YVelocity>0)
					{
						this.ball.YVelocity++; //optional, increase +ve YVelocity
					}
					else
					{
						this.ball.YVelocity--; //optional, increase -ve YVelocity
					}
					this.ball.setXDirection(-1*this.ball.XVelocity);
					this.ball.setYDirection(this.ball.YVelocity);
					
				}
		
		
		//Prevent the paddles from moving off screen edges
		if(this.Paddle1.y<=0) //check if y of Paddle1 <= 0 i.e. topleft corner
		{
			this.Paddle1.y=0; //stop it at the leftmost upper corner
		}
		if(this.Paddle1.y>=(GamePanel.GAME_HEIGHT-GamePanel.PADDLE_HEIGHT)) //if y of Paddle1 greater than the movable distance
		{
			this.Paddle1.y=GamePanel.GAME_HEIGHT-GamePanel.PADDLE_HEIGHT; //stop it at this distance
		}
		if(this.Paddle2.y<=0) //check if y of Paddle2 <= 0 i.e. topleft corner
		{
			this.Paddle2.y=0; //stop it at the leftmost upper corner
		}
		if(this.Paddle2.y>=(GamePanel.GAME_HEIGHT-GamePanel.PADDLE_HEIGHT)) //if y of Paddle2 greater than the movable distance
		{
			this.Paddle2.y=GamePanel.GAME_HEIGHT-GamePanel.PADDLE_HEIGHT; //stop it at this distance
		}
		
		//This Section is going to give a player one point and creates new paddles and ball
		if(this.ball.x <= 0) //this means that player 2 scored a point
		{
			//the ball has touched the left boundry
			//Give Player 2 a point
			this.score.SetPlayer2(this.score.GetPlayer2()+1);
			//Create New Paddles
			this.newPaddles();
			//Create New Ball
			this.newBall();
			//System.out.println("Player2 Score: "+this.score.GetPlayer2());
		}
		if(this.ball.x >= GamePanel.GAME_WIDTH-GamePanel.BALL_DIAMETER) //this means that player 1 scored a point
		{
			//the ball has touched the Right boundry
			//Give Player 1 a point
			this.score.SetPlayer1(this.score.GetPlayer1()+1);
			//Create New Paddles
			this.newPaddles();
			//Create New Ball
			this.newBall();
			//System.out.println("Player1 Score: "+this.score.GetPlayer1());
		}
		
	}
	
	//The Following are inner class
	//This Inner class is an Action Listener that Extends KeyAdaptor
	//Purpose of this Inner class: 
	/* The ActionListner will be added to the
	 * GamePanel class, then we are going to call
	 * the appropriate KeyPressed() method or
	 * KeyReleased() Method of our Paddle
	 */
	public class ActionListener extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			//The keyPressed() Method is called once every time a key is pressed
			//The key that was Pressed will be stored in the key variable.
			
			//Call keyPressed method of Paddle1 and Paddle2
			Paddle1.keyPressed(e);
			Paddle2.keyPressed(e);
			
		}
		
		public void keyReleased(KeyEvent e)
		{
			//The keyReleased() function is called once every time a key is released. 
			//The key that was released will be stored in the key variable
			//Call keyReleased of Paddle1 and 2
			Paddle1.keyReleased(e);
			Paddle2.keyReleased(e);
		}
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		//Create a basic game loop
				long LastTime = System.nanoTime(),Now; //fetch the elapsed time of the JVM
				double AmountOfTicks = 60.0,
					   NanoSecond = 1000000000 / AmountOfTicks,
					   Delta = 0;
				while(true) //loop infinitely
				{
					Now=System.nanoTime();
					Delta+=(Now-LastTime)/NanoSecond;
					LastTime = Now;
					if(Delta >=1)
					{
						//Move all of the components
						this.move();
						//Check for any Collisions
						this.checkCollision();
						//Re-paint EveryThing
						this.repaint();
						//Decrement Delat
						Delta--;
					}
				}
		
	}

}
