package model.queries;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;

public class XCoordinate extends TurtleCommand {
	private TurtleState myTurtleState;

	public XCoordinate(List<TreeNode> args, TurtleState st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(){
		 
		
	}
	
	public double getValue(){
		return myTurtleState.getX();
	}


}
