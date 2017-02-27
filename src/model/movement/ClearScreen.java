package model.movement;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;

public class ClearScreen extends TurtleCommand {
	private TurtleState myTurtleState;
	private double distance;
	

	public ClearScreen(List<TreeNode> args, TurtleState st){
		children = args;
		myTurtleState = st;
		distance = Math.sqrt(Math.pow(myTurtleState.getX(), 2) + Math.pow(myTurtleState.getY(), 2));
	}
	
	public void execute(){
		myTurtleState.setPosition(0,0);
	}
	
	public double getValue(){
		return distance;
	}
}