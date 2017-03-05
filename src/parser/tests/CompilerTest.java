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
		String testCode = "fd fd fd 50 rt 30 repeat fd 50 [ rt 30 fd 10 ]";
		
		NewParser p = new NewParser(language);
		Compiler c = new Compiler();
		
		ProtectedTokenList PTL = p.parse(testCode);
		
		Queue<TreeNode> q = c.compile(new TurtleState(), PTL);
		
		while(!q.isEmpty()){
			System.out.println(q.remove().getValue());
		}
	}

}