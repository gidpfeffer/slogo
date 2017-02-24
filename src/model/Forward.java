package model;

import java.util.*;


public class Forward extends TurtleCommand {
	
	private double pixels;

	
	public Forward(List<TreeNode> args){
		children = args;
		pixels = children.get(0).getValue();
	}
	
	public void execute(Turtle turtle){
		TurtleState turtleState = turtle.getState(); 
		turtleState.setX(turtleState.getX()+Math.cos(turtleState.getHeadAngle())*pixels);		
		turtleState.setY(turtleState.getY()+Math.sin(turtleState.getHeadAngle())*pixels);
		 
	}
	
	public double getValue(){
		return pixels;
	}
	
}

