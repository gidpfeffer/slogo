package model.math;

import java.util.List;

import model.command.LogicCommand;
import model.command.TreeNode;

public class Sine extends LogicCommand{
	private double degrees;

	
	public Sine(List<TreeNode> args){
		children = args;
		degrees = children.get(0).getValue();
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		return Math.sin(Math.toRadians(degrees)); 
		
	}
}
