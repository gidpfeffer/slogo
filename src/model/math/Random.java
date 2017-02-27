package model.math;

import java.util.List;

import model.command.LogicCommand;
import model.command.TreeNode;


public class Random extends LogicCommand {
	private double max;
	private double randomValue; 

	
	public Random(List<TreeNode> args){
		children = args;
		max = children.get(0).getValue();
		randomValue = getRandomValue();
	}
	
	private double getRandomValue() {
		double val = -1;
		java.util.Random rand = new java.util.Random();
		while (val <0 || val == max ){
			val = 0 + (max - 0) * rand.nextDouble(); //http://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
		}
		return val;
	}

	public void execute(){
		
	}
	
	public double getValue(){
		return randomValue;
	}
	
}
