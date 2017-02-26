package model.movement;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;


public class Forward extends TurtleCommand {
	
	private double pixels;
	private TurtleState myTurtleState;
	
	
	
	public Forward(List<TreeNode> args, TurtleState t){
		children = args;
		pixels = children.get(0).getValue();
		myTurtleState = t;
	}
	
	public void execute(){
		
		myTurtleState.setX(myTurtleState.getX()+Math.cos(myTurtleState.getHeadAngle())*pixels);		
		myTurtleState.setY(myTurtleState.getY()+Math.sin(myTurtleState.getHeadAngle())*pixels);
		 
	}
	
	public double getValue(){
		return pixels;
	}
	
}

