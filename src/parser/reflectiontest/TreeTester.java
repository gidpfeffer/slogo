package parser.reflectiontest;

import model.ListEnd;
import model.ListStart;
import model.TreeNode;
import parser.main.Parser;

public class TreeTester {
	
	public static void main(String args[]){
		String testCode = "repeat 5 [ [ fd 20 ] [ fd 40 ] ]";
		
		Parser p = new Parser(testCode);
		
		for(TreeNode node : p.getTreeList()){
			if(node instanceof ListStart){
				System.out.print("[");
			}
			else if(node instanceof ListEnd){
				System.out.print("]");
			}
			else System.out.print(node.getValue());
		}
	}

}
