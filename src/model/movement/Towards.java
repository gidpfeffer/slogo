package model.movement;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import model.turtle.TurtleState;

public class Towards extends TurtleCommand {
	
	private double x;
	private double y; 
	private double degrees;
	
	private Turtle turtle;
	//private List<Turtle> turtleList; // need to get this somehow from the model
	

	
	public Towards(List<TreeNode> args){
		children = args;
		y = children.get(0).getValue();
		x = children.get(1).getValue();
		//Turtle turtle = turtleList.get(0);
		degrees = Math.toDegrees(Math.atan2(y - turtle.getState().getY() , x - turtle.getState().getX()));
		
		
	}
	
	public void execute(Turtle turtle){
		TurtleState turtleState = turtle.getState(); 
		turtleState.setHeadAngle(degrees);
	}
	
	public double getValue(){
		return degrees - turtle.getState().getHeadAngle();
	}
}