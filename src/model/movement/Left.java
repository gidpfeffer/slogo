package model.movement;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import model.turtle.TurtleState;

public class Left extends TurtleCommand {
	
	private double degrees;

	
	public Left(List<TreeNode> args){
		children = args;
		degrees = children.get(0).getValue();
	}
	
	public void execute(Turtle turtle){
		TurtleState turtleState = turtle.getState(); 
		turtleState.setHeadAngle(turtleState.getHeadAngle()+degrees);
		 
	}
	
	public double getValue(){
		return degrees;
	}
}
