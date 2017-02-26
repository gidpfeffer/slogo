package model.movement;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;

public class Backward extends TurtleCommand {
	private double pixels;
	private TurtleState myTurtleState;

	
	public Backward (List<TreeNode> args, TurtleState st){
		children = args;
		pixels = children.get(0).getValue();
		myTurtleState = st;
	}
	
	public void execute(){
	
		double newX = myTurtleState.getX()-Math.cos(myTurtleState.getHeadAngle())*pixels;
		double newY = myTurtleState.getY()-Math.sin(myTurtleState.getHeadAngle())*pixels;
		myTurtleState.setPosition(newX, newY);
	}
	
	public double getValue(){
		return pixels;
	}
}
