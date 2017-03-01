package model.math;

import java.util.List;

import controller.SLogoException;
import model.command.Command;
import model.command.LogicCommand;
import model.command.TreeNode;

public class NaturalLog extends LogicCommand implements Command{
	

	
	public NaturalLog(List<TreeNode> args){
		children = args;
	}
	
	public void execute(){
		
	}
	
	public double getValue(){
		
		Double val = Math.log(children.get(0).getValue()); // value of x in ln(x) cannot be negative, throw error here 
		if (val.isNaN()||val.isInfinite()){
			throw new SLogoException("Invalid value for natural log: " + children.get(0).getValue());
		}
		else{
			return val;
		}
		
	
		
	}
	
}
