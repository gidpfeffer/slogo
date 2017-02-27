package model.visibility;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;

public class ShowTurtle extends TurtleCommand {
	private TurtleState myTurtleState;

	public ShowTurtle(List<TreeNode> args, TurtleState st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(){
		 
		myTurtleState.setVisibility(true);
	}
	
	public double getValue(){
		return 1.0;
	}
}
