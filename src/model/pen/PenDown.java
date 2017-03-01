package model.pen;

import java.util.List;

import model.command.Command;
import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.State;
import model.turtle.TurtleState;

public class PenDown extends TurtleCommand implements Command{

	private State myTurtleState;

	public PenDown(List<TreeNode> args, State st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(){
		TurtleState st = (TurtleState) myTurtleState;
		st.setPen(true);
	}
	
	public double getValue(){
		return 1.0;
	}


}
