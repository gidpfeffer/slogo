package model.movement;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import model.turtle.TurtleState;

public class Backward extends TurtleCommand {
	private double pixels;
	private TurtleState myTurtleState;

	
	public Backward (List<TreeNode> args, Turtle t){
		children = args;
		pixels = children.get(0).getValue();
		myTurtleState = t.getState();
	}
	
	public void execute(){
	
		myTurtleState.setX(myTurtleState.getX()-Math.cos(myTurtleState.getHeadAngle())*pixels);
		myTurtleState.setY(myTurtleState.getY()-Math.sin(myTurtleState.getHeadAngle())*pixels);
	}
	
	public double getValue(){
		return pixels;
	}
}
