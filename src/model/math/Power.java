package model.math;

import java.util.List;

import model.command.LogicCommand;
import model.command.TreeNode;

public class Power extends LogicCommand{

	
	
	public Power(List<TreeNode> args){
		children = args;
	
		
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		return Math.pow(children.get(0).getValue(),children.get(1).getValue()); 
	}
	
}
