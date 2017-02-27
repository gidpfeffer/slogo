package model.math;

import java.util.List;

import model.command.LogicCommand;
import model.command.TreeNode;

public class Pi extends LogicCommand {
	public Pi(List<TreeNode> args){
		children = args;
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		return Math.PI; 
		
	}
}
