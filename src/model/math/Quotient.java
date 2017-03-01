package model.math;

import java.util.List;

import model.command.Command;
import model.command.LogicCommand;
import model.command.TreeNode;

public class Quotient extends LogicCommand implements Command {
	

	
	public Quotient(List<TreeNode> args){
		children = args;
		
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		return children.get(0).getValue()/children.get(1).getValue();  // divide by 0.0 throw error
	}
	
}
