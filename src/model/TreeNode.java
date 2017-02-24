package model;

import java.util.*;

public abstract class TreeNode {

	
	protected List<TreeNode> children  = new ArrayList<TreeNode>();
	
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
	
}
