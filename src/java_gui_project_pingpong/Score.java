package java_GUI_Project_PINGPONG;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
/* The Rectangle class specifies an area in 
 * a coordinate space that is enclosed by 
 * the Rectangle object's upper-left 
 * point (x,y) in the coordinate space, 
 * its width, and its height
 */

public class Score extends Rectangle{
	//properties, private access specifier
	private static int GAME_WIDTH, //Width of the Game
	                   GAME_HEIGHT;//Height of the Game
	private int Player1,           //Holds current score of player 1
	            Player2;           //Holds current score of player 2
	
	
	//public access specifier
	public Score(int GAME_WIDTH,int GAME_HEIGHT)
	{
		//Assign the GAME_WIDTH and GAME_HEIGHT to
		//the static variables
		Score.GAME_HEIGHT=GAME_HEIGHT;
		Score.GAME_WIDTH=GAME_WIDTH;
	}
	
	//Methods
	public void draw(Graphics g)
	{
		//The Score is only to be displayed or drawn on the Screen
		
		//Set the Color of the Score
		g.setColor(Color.red);
		//Set the Font of the text
		g.setFont(new Font("Consolas",Font.BOLD,60));
		//Draw a line right down the middle of the game
		g.drawLine(Score.GAME_WIDTH/2,0,Score.GAME_WIDTH/2,Score.GAME_HEIGHT);
		//Now Draw the Score for Player1
		g.drawString(String.format("%02d",this.GetPlayer1()),Score.GAME_WIDTH/2-85,50);//subtract the offset 85 so that it fits nicely on top of screen
		//Now Draw the Score for Player2
		g.drawString(String.format("%02d",this.GetPlayer2()),Score.GAME_WIDTH/2+20,50);
	}
	
	//Return the Player 1 point
	public int GetPlayer1()
	{
		return this.Player1;
	}
	
	//Return the Player 2 Point
	public int GetPlayer2()
	{
		return this.Player2;
	}
	
	//Set the Player 2 point
	public void SetPlayer2(int Player2)
	{
		this.Player2=Player2;
	}
	
	//Set the Player 1 point
	public void SetPlayer1(int Player1)
	{
		this.Player1=Player1;
	}
}
