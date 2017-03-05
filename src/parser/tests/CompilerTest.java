package parser.tests;

import java.util.Queue;

import model.command.TreeNode;
import model.turtle.TurtleState;
import parser.main.Compiler;
import parser.main.NewParser;
import parser.tokenizer.ProtectedTokenList;

public class CompilerTest {
	public static void main(String args[]){
		String language = "resources/languages/English";
		String testCode = "make :x 10";
		
		NewParser p = new NewParser(language);
		Compiler c = new Compiler();
		
		ProtectedTokenList PTL = p.parse(testCode);
		
		Queue<TreeNode> q = c.compile(new TurtleState(), PTL);
		
		while(!q.isEmpty()){
			System.out.println(q.remove().getValue());
		}
		
		testCode = "fd :x";
		
		PTL = p.parse(testCode);
		
		q = c.compile(new TurtleState(), PTL);
		
		while(!q.isEmpty()){
			System.out.println(q.remove().getValue());
		}
	}

}
