package model.command;

public class Constant extends TreeNode{
	double myConstant; 
	
	public Constant(double num){
		myConstant = num; 
	}
	
	public double getValue(){
		return myConstant; 
	}
	
}
