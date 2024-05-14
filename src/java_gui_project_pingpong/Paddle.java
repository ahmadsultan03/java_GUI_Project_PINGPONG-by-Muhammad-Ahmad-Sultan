package java_GUI_Project_PINGPONG;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle{
	//properties, private access specifier
	private int Id,        //Identifies the Paddle, 1 for Paddle1 and 2 for Paddle2
	            YVelocity, //how fast the Paddle moves up or down when we press a button
	            Speed=10;
	
	//public access specifier
	public Paddle(int x,int y, int PADDLE_WIDTH,int PADDLE_HEIGHT,int id)
	{
		//Call the Contructor of Rectangle
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.Id=id;
	}
	
	//Methods
	public void keyPressed(KeyEvent e)
	{
		//make a switch that examines the content of id
		switch(this.Id)
		{
		case 1: //Paddle1
			if(e.getKeyCode()==KeyEvent.VK_W) //Keyboard: W
			{
				this.setYDirection(-this.Speed); //move up on y-axis
				this.move();
			}
			if(e.getKeyCode()==KeyEvent.VK_S) //Keyboard S
			{
				this.setYDirection(this.Speed); //move up on y-axis
				this.move();
			}
			break;
		case 2: //Paddle 2
			if(e.getKeyCode()==KeyEvent.VK_UP) //Up on arrow Key
			{
				this.setYDirection(-this.Speed); //move up on y-axis
				this.move();
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN)//Down on Arrow Key
			{
				this.setYDirection(this.Speed); //move up on y-axis
				this.move();
			}
			break;
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		//make a switch that examines the content of id
				switch(this.Id)
				{
				case 1: //Paddle1
					if(e.getKeyCode()==KeyEvent.VK_W) //Keyboard: W
					{
						this.setYDirection(0); //stop on y-axis
						this.move();
					}
					if(e.getKeyCode()==KeyEvent.VK_S) //Keyboard S
					{
						this.setYDirection(0); //stop on y-axis
						this.move();
					}
					break;
				case 2: //Paddle 2
					if(e.getKeyCode()==KeyEvent.VK_UP) //Up on arrow Key
					{
						this.setYDirection(0); //stop on y-axis
						this.move();
					}
					if(e.getKeyCode()==KeyEvent.VK_DOWN)//Down on Arrow Key
					{
						this.setYDirection(0); //stop on y-axis
						this.move();
					}
					break;
				}
	}
	
	public void setYDirection(int YDirection)
	{
		//This Method Sets the Y-Coordinate of
		//Paddle, Paddles only move in the Y-Direction
		
		//Set YVelicity equal to YDirection
		this.YVelocity=YDirection;
	}
	
	public void move()
	{
		//Allows movement of Paddle
		
		//Add YVelocity to y and update
		this.y=this.y+this.YVelocity;
	}
	
	public void draw(Graphics g)
	{
		//Draws objects
		if(this.Id==1)
		{
			//Player 1, set color to red
			g.setColor(new Color(227, 36, 43));
		}
		else
		{
			//Player 2, set color to mustard
			g.setColor(new Color(255, 219, 88));
		}
		//Fill the Rectangle
		g.fillRect(this.x,this.y,this.width,this.height);//x,y,width and height are from Rectangle
	}

}
