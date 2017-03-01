package model.movement;

import java.util.List;

import model.command.Command;
import model.command.TreeNode;
import model.command.TurtleCommand;

import model.turtle.TurtleState;

public class SetPosition extends TurtleCommand implements Command{
	
	private double x;
	private double y; 

	private TurtleState myTurtleState;
	

	
	public SetPosition(List<TreeNode> args, TurtleState st){
		children = args;
		x = children.get(0).getValue();
		y = children.get(1).getValue();
		myTurtleState = st;
		
	}
	
	public void execute(){
		TurtleState st = (TurtleState) myTurtleState;
		st.setPosition(x, y);
	}
	
	public double getValue(){
		return 	Math.sqrt(Math.pow(x-myTurtleState.getX(), 2) + Math.pow(y-myTurtleState.getY(), 2));
	}
}
