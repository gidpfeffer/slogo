package parser.reflectiontest;

import model.command.TreeNode;
import model.turtle.TurtleState;
import parser.main.Parser;

public class TreeTester {
	
	public static void main(String args[]){
		String testCode1 = "fd 111 product fd 10 product fd fd 50 100";
		
		Parser p = new Parser(new TurtleState());
		p.parse(testCode1);
		
		for(TreeNode node : p.getTreeQueue()){
			System.out.print(node.getValue() + "\n");
		}
		
		System.out.println("\n++++++++++++++++++++++++++++++++");
		
		String testCode2 = "make :x 5 fd :x";
		
		p.parse(testCode2);
	}
}
