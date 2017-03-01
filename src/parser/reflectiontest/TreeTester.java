package parser.reflectiontest;

import model.command.TreeNode;
import model.turtle.TurtleState;
import parser.main.Parser;

public class TreeTester {
	
	public static void main(String args[]){
		String language = "resources/languages/English";
		String testCode1 = "set :x 20 repeat :x [ fd :x repeat :x [ fd 50 rt 80 fd 20 lt 160 ] rt 90 fd 10 ] ";
		
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
