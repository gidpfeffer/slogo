package model.math;

import java.util.List;

import model.command.LogicCommand;
import model.command.TreeNode;

public class Remainder extends LogicCommand {
	
	public Remainder(List<TreeNode> args){
		children = args;
		
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		return children.get(1).getValue()%children.get(0).getValue();  // divide by 0.0 throw error
	}
}