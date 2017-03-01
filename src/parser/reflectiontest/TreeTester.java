package parser.reflectiontest;

import model.command.TreeNode;
import model.turtle.TurtleState;
import parser.main.Parser;

public class TreeTester {
	
	public static void main(String args[]){
		String language = "resources/languages/English";
		String testCode1 = "dotimes [ :x fd 3 ] [ rt :x ]";
		
		Parser p = new Parser(new TurtleState(), language);
		p.parse(testCode1);
		
		for(TreeNode node : p.getTreeQueue()){
			System.out.print(node.getValue() + "\n");
		}
		
		System.out.println("\n++++++++++++++++++++++++++++++++");
		
		String testCode2 = "";
		
		p.parse(testCode2);
	}
}
