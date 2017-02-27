package model.math;

import java.util.List;

import model.command.LogicCommand;
import model.command.TreeNode;

public class ArcTangent extends LogicCommand{

	private double degrees;

	
	public ArcTangent(List<TreeNode> args){
		children = args;
		degrees = children.get(0).getValue();
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		return Math.toDegrees(Math.atan(degrees)); 
		
	}
	
}
