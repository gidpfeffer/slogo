package model.boolean_ops;

import java.util.List;

import model.command.LogicCommand;
import model.command.TreeNode;

public class And extends LogicCommand {
	public And(List<TreeNode> args){
		children = args;
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		return ((children.get(1).getValue() != 0) & (children.get(0).getValue() !=0)? 1: 0); 
		
	}
}
