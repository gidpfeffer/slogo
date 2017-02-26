package model.movement;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;

public class Left extends TurtleCommand {
	
	private double degrees;
	private TurtleState myTurtleState;

	
	public Left(List<TreeNode> args, TurtleState st){
		children = args;
		degrees = children.get(0).getValue();
		myTurtleState = st;
	}
	
	public void execute(){
		
		myTurtleState.setHeadAngle((myTurtleState.getHeadAngle()+degrees) % 360);
		 
	}
	
	public double getValue(){
		return degrees;
	}
}
