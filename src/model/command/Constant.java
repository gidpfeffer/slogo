package model.command;

import model.turtle.TurtleState;

public class Constant extends TreeNode{
	double myConstant; 
	
	public Constant(double num, TurtleState t){
		myConstant = num; 
	}
	
	public double getValue(){
		return myConstant; 
	}
	
}
