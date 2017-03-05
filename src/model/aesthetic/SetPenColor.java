package model.aesthetic;

import java.util.List;

import model.command.Command;
import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.State;
import model.turtle.TurtleState;

public class SetPenColor extends TurtleCommand implements Command {
	private double index;
	private State myTurtleState;

	
	public SetPenColor (List<TreeNode> args, State st){
		children = args;
		index = children.get(0).getValue();
		myTurtleState = st;
	}
	
	public void execute(){
		TurtleState st = (TurtleState) myTurtleState;
		st.setPenColorIndex(index);
	}
	
	public double getValue(){
		return index;
	}
}
