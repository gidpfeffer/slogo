package model.visibility;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;

public class HideTurtle extends TurtleCommand{

	private TurtleState myTurtleState;

	public HideTurtle(List<TreeNode> args, TurtleState st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(){
		 
		myTurtleState.setVisibility(false);
	}
	
	public double getValue(){
		return 0.0;
	}
}
