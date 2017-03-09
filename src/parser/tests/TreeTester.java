package parser.tests;

import model.command.TreeNode;
import model.turtle.TurtleState;
import parser.main.Compiler;
import parser.main.NewParser;
import parser.tokenizer.ProtectedTokenList;

public class TreeTester {
	
	public static void main(String args[]){
		String language = "resources/languages/English";
		String testCode1 = "dotimes [ :x 10 ] [ fd :x ]";
		
		NewParser p = new NewParser(language);
		Compiler c = new Compiler();
		ProtectedTokenList PTL = p.parse(testCode1);
		
		for(TreeNode node : c.compile(new TurtleState(), PTL)){
			System.out.print(node.getValue() + "\n");
		}
	}
}
