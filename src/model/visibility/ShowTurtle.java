package model.visibility;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.State;
import model.turtle.TurtleState;

public class ShowTurtle extends TurtleCommand {
	private State myTurtleState;

	public ShowTurtle(List<TreeNode> args, State st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(){
		TurtleState st = (TurtleState) myTurtleState;
		st.setVisibility(true);
	}
	
	public double getValue(){
		return 1.0;
	}
}
