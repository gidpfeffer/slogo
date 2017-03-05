package model.queries;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.State;

public class GetPenColor extends TurtleCommand {
	private State myTurtleState;


	public GetPenColor(List<TreeNode> args, State st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(){
		 
	}
	
	public double getValue(){
		return myTurtleState.getPenColorIndex();
	}

}
