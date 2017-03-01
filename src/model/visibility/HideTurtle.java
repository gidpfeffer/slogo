package model.visibility;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.State;
import model.turtle.TurtleState;

public class HideTurtle extends TurtleCommand{

	private State myTurtleState;

	public HideTurtle(List<TreeNode> args, State st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(){
		TurtleState st = (TurtleState) myTurtleState;
		st.setVisibility(false);
	}
	
	public double getValue(){
		return 0.0;
	}
}
