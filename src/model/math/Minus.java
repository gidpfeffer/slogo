package model.math;

import java.util.List;

import model.command.LogicCommand;
import model.command.TreeNode;

public class Minus extends LogicCommand {
	

	
	public Minus(List<TreeNode> args){
		children = args;
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		return -1 * children.get(0).getValue(); 
		
	}
	
}
