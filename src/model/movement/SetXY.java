package model.movement;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import model.turtle.TurtleState;

public class SetXY extends TurtleCommand {
	
	private double distance;
	private double x;
	private double y; 
	
	private Turtle turtle;
	//private List<Turtle> turtleList; // need to get this somehow from the model
	

	
	public SetXY(List<TreeNode> args){
		children = args;
		x = children.get(1).getValue();
		y = children.get(0).getValue();
		distance = Math.sqrt(Math.pow(x-turtle.getState().getX(), 2) + Math.pow(y-turtle.getState().getY(), 2));
		
	}
	
	public void execute(Turtle turtle){
		turtle.getState().setPosition(x, y);
	}
	
	public double getValue(){
		return distance;
	}
}
