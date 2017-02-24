package model;

import java.util.List;

public class Product extends LogicCommand{
	

	
	public Product(List<TreeNode> args){
		children = args;
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		return children.get(0).getValue() * children.get(1).getValue(); 
		
	}
	
}

