package java_GUI_Project_PINGPONG;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//GameFrame is the window Frame that has the
//minimise, Maximize, Cross button etc, The Panel is like the canvas and Frame is the frame around the canvas

public class GameFrame extends JFrame{
	//Properties, private access specifier
	private GamePanel Panel;
	
	//public access specifier
	//Constructor
	public GameFrame()
	{
		//instantiate the Panel here
		Panel = new GamePanel();
		//Add the GamePanel to the GameFrame
		this.add(Panel);
		//Set the Title of the Frame
		this.setTitle("PING PONG GAME");
		//disable the resizeable feature of the frame
		this.setResizable(false);
		//Add a Background color to your Frame
		this.setBackground(new Color(0,25,51));
		//Set the default close operation to exit on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Use the pack() method to pack all the components in the Frame i.ie automatically resize to fit every component
		this.pack();
		//Set the Visibility of the Frame to true
		this.setVisible(true);
		//Make the JFrame appear at the centre of the Screen
		this.setLocationRelativeTo(null);
	}

}
