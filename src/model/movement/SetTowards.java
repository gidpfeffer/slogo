package model.movement;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import model.turtle.TurtleState;

public class SetTowards extends TurtleCommand {
	
	private double x;
	private double y; 
	private double degrees;

	private TurtleState myTurtleState;
	

	
	public SetTowards(List<TreeNode> args, TurtleState st){
		children = args;
		y = children.get(0).getValue();
		x = children.get(1).getValue();
		myTurtleState = st;
		degrees = Math.toDegrees(Math.atan2(y - myTurtleState.getY() , x - myTurtleState.getX()));
	}
	
	public void execute(){
		 
		myTurtleState.setHeadAngle(degrees % 360);
	}
	
	public double getValue(){
		return Math.abs(degrees - myTurtleState.getHeadAngle());
	}
}