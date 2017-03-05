package model.aesthetic;

import java.util.List;

import model.command.Command;
import model.command.LogicCommand;
import model.command.TreeNode;

public class SetPalette extends LogicCommand implements Command {
	
	public SetPalette(List<TreeNode> args){
		children = args;
	}
	
	
	public double getValue(){
		return children.get(0).getValue(); 
		
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
