package model.queries;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.State;

public class IsShowing extends TurtleCommand{
	private State myTurtleState;

	public IsShowing(List<TreeNode> args, State st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(){
	
	}
	
	public double getValue(){
		return (myTurtleState.getVisibility()?1.0:0.0);
	}


}
