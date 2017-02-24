package model;

import java.util.*;

public class Forward extends TurtleCommand {
	
	private double pixels;

	
	public Forward(List<TreeNode> args){
		children = args;
	}
	
	public void execute(Turtle turtle){
		TurtleState turtleState = turtle.getState(); 
		
	}
	
	public double getValue(){
		return children.get(0).getValue();
	}
	
}

