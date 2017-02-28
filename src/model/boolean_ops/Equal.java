package model.boolean_ops;

import java.util.List;

import model.command.LogicCommand;
import model.command.TreeNode;

public class Equal extends LogicCommand{
	
	public Equal(List<TreeNode> args){
		children = args;
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		return (children.get(0).getValue() == children.get(1).getValue()? 1: 0); 
		
	}
}
