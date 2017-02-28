package model.math;

import java.util.List;

import model.command.LogicCommand;
import model.command.TreeNode;

public class NaturalLog extends LogicCommand {
	

	
	public NaturalLog(List<TreeNode> args){
		children = args;
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		
		Double val = Math.log(children.get(0).getValue()); // value of x in ln(x) cannot be negative, throw error here 
		if (val.isNaN()){
			throw new IllegalArgumentException("can't take log of a negative number");
		}
		else{
			return val;
		}
		
	
		
	}
	
}
