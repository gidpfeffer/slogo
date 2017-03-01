package parser.reflectiontest;

import model.command.TreeNode;
import model.turtle.TurtleState;
import parser.main.Parser;

public class TreeTester {
	
	public static void main(String args[]){
		String language = "resources/languages/English";
		String testCode1 = "dotimes [ :x 10 ] [ fd :x ]";
		
		Parser p = new Parser(new TurtleState(), language);
		p.parse(testCode1);
		
		for(TreeNode node : p.getTreeQueue()){
			System.out.print(node.getValue() + "\n");
		}
		
		System.out.println("\n++++++++++++++++++++++++++++++++\n");
		
		String testCode2 = "fd :x";
		
		p.parse(testCode2);
		
		for(TreeNode node : p.getTreeQueue()){
			System.out.print(node.getValue() + "\n");
		}
	}
}
