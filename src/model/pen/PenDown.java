package model.pen;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;

public class PenDown extends TurtleCommand{

	private TurtleState myTurtleState;

	public PenDown(List<TreeNode> args, TurtleState st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(){
		 
		myTurtleState.setPen(true);
	}
	
	public double getValue(){
		return 1.0;
	}


}
