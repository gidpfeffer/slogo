package parser.control_structures;

import java.util.ArrayList;
import java.util.List;

import model.command.TreeNode;


public class Controller {
	List<TreeNode> list;
	
	public Controller(List<TreeNode> treeList){
		list = new ArrayList<>(treeList);
		handle();
	}
	
	private void handle(){
		
	}

}
