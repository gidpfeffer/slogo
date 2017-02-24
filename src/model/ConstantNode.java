package model;

public class ConstantNode extends TreeNode{
	double myConstant; 
	
	public ConstantNode(double num){
		myConstant = num; 
	}
	
	public double getValue(){
		return myConstant; 
	}
	
}
