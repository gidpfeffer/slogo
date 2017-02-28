package model.command;

import java.util.*;


public abstract class TreeNode {

	
	protected List<TreeNode> children  = new ArrayList<TreeNode>();
	protected boolean root = true;
	
	public double getValue(){
		return 0.0; 
	}
	public List<TreeNode> getChildren(){
		return children;
	}

	public int getNumChildrenRequired(){
		return 0;
	}
	public void addChild(TreeNode childToAdd){
		children.add(childToAdd);
	}
	
	public void execute(){};
	
	public boolean isRoot(){
		return root;
	}
	
	public void deRoot(){
		root = false;
	}
	
	public void deRootAllChild(){
		for(TreeNode tn: children){
			tn.deRoot();
		}
	}
}
