package model.movement;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import model.turtle.TurtleState;

public class SetPosition extends TurtleCommand {
	
	private double distance;
	private double x;
	private double y; 

	private TurtleState myTurtleState;
	

	
	public SetPosition(List<TreeNode> args, TurtleState st){
		children = args;
		x = children.get(1).getValue();
		y = children.get(0).getValue();
		myTurtleState = st;
		distance = Math.sqrt(Math.pow(x-myTurtleState.getX(), 2) + Math.pow(y-myTurtleState.getY(), 2));
		
	}
	
	public void execute(){
		myTurtleState.setPosition(x, y);
	}
	
	public double getValue(){
		return distance;
	}
}
