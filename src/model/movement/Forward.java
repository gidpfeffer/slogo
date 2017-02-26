package model.movement;

import java.util.*;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;


public class Forward extends TurtleCommand {
	
	private double pixels;
	private TurtleState myTurtleState;
	
	
	
	public Forward(List<TreeNode> args, TurtleState st){
		children = args;
		pixels = children.get(0).getValue();
		myTurtleState = st;
	}
	
	public void execute(){
		
		double newX = myTurtleState.getX()+Math.cos(myTurtleState.getHeadAngle())*pixels;		
		double newY = myTurtleState.getY()+Math.sin(myTurtleState.getHeadAngle())*pixels;
		myTurtleState.setPosition(newX, newY);
		 
	}
	
	public double getValue(){
		return pixels;
	}
	
}

