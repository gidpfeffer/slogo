package model.queries;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.State;


public class IsPenDown extends TurtleCommand {
	private State myTurtleState;

	public IsPenDown(List<TreeNode> args, State st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		return (myTurtleState.getPen()? 1.0: 0.0);
	}


}
