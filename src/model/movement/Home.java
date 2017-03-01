package model.movement;

import java.util.List;

import model.command.Command;
import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;

public class Home extends TurtleCommand implements Command {

	private TurtleState myTurtleState;
		
	public Home(List<TreeNode> args, TurtleState st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(){
		TurtleState st = (TurtleState) myTurtleState;
		st.setPosition(0,0);
	}
	
	public double getValue(){
		return Math.sqrt(Math.pow(myTurtleState.getX() - 0, 2) + Math.pow(myTurtleState.getY() - 0 , 2));
	}
}
