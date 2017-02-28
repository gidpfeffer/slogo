package parser.reflectiontest;

import model.ListEnd;
import model.ListStart;
import model.command.TreeNode;
import model.turtle.TurtleState;
import parser.main.Parser;

public class TreeTester {
	
	public static void main(String args[]){
		String testCode1 = "product fd fd 50 100";
		
		Parser p = new Parser(new TurtleState());
		p.parse(testCode1);
		
		for(TreeNode node : p.getOrderedTreeList()){
			if(node instanceof ListStart){
				System.out.print("[");
			}
			else if(node instanceof ListEnd){
				System.out.print("]");
			}
			else System.out.print(node.getValue());
			System.out.print(" ");
		}
		
		System.out.println("\n++++++++++++++++++++++++++++++++");
		
		String testCode2 = "make :x 5 fd :x";
		
		p.parse(testCode2);
		
		for(TreeNode node : p.getOrderedTreeList()){
			if(node instanceof ListStart){
				System.out.print("[");
			}
			else if(node instanceof ListEnd){
				System.out.print("]");
			}
			else System.out.print(node.getValue());
			System.out.print(" ");
		}
	}
}
