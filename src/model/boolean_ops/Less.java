package model.boolean_ops;

import java.util.List;

import model.command.LogicCommand;
import model.command.TreeNode;

public class Less extends LogicCommand{
	

	
	public Less(List<TreeNode> args){
		children = args;
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		return (children.get(1).getValue() < children.get(0).getValue()? 1: 0); 
		
	}
	
}

