package model.movement;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import model.turtle.TurtleState;

public class SetHeading extends TurtleCommand {
	
	private double degrees;
	
	private Turtle turtle;
	//private List<Turtle> turtleList; // need to get this somehow from the model
	

	
	public SetHeading(List<TreeNode> args){
		children = args;
		degrees = children.get(0).getValue();
	}
	
	public void execute(Turtle turtle){
		TurtleState turtleState = turtle.getState(); 
		turtleState.setHeadAngle(degrees);
	}
	
	public double getValue(){
		return degrees - turtle.getState().getHeadAngle();
	}
}
