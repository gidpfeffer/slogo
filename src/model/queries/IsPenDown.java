package model.queries;

import java.util.List;

import controller.BackEndHandler;
import model.command.Command;
import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.State;

/**
 * 
 * @author Anh
 *
 */
public class IsPenDown extends TurtleCommand implements Command {
	private State myTurtleState;

	public IsPenDown(List<TreeNode> args, State st){
		children = args;
		myTurtleState = st;
	}
	

	
	public double getValue(){
		return (myTurtleState.getPen()? 1.0: 0.0);
	}



	@Override
	public void execute(BackEndHandler myHandler) {
		// TODO Auto-generated method stub
		
	}


}
