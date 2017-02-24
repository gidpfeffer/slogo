package parser.relectiontest;

import java.util.Queue;

import model.TreeNode;
import parser.interpreter.Interpreter;
import parser.tokenizer.TokenList;
import parser.tokenizer.TokenListGenerator;

public class TreeTester {
	
	public static void main(String args[]){
		String testCode = "product fd 10 product 10 12 product 5 10";
		
		TokenListGenerator t = new TokenListGenerator(testCode);
		TokenList TL = t.getList();
		Interpreter IT = new Interpreter(TL);
		
		TreeGenerator TG = new TreeGenerator(TL);
		
		Queue<TreeNode> queue = TG.getQueue();
		
		while(!queue.isEmpty()){
			TreeNode node = queue.remove();
			System.out.println(node.getValue());
		}
	}

}
