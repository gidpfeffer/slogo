package model.queries;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;

public class IsShowing extends TurtleCommand{
	private TurtleState myTurtleState;

	public IsShowing(List<TreeNode> args, TurtleState st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(){
	
	}
	
	public double getValue(){
		return (myTurtleState.getVisibility()?1.0:0.0);
	}


}
