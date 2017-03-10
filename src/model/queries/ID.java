package model.queries;

import java.util.List;

import controller.BackEndHandler;
import model.command.Command;
import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.State;

public class ID extends TurtleCommand implements Command {
	private State myTurtleState;


	public ID(List<TreeNode> args, State st){
		children = args;
		myTurtleState = st;
	}
	

	public double getValue(){
		return myTurtleState.getID();
	}

	@Override
	public void execute(BackEndHandler myHandler) {
		// TODO Auto-generated method stub
		
	}


}
