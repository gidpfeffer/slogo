package parser.relectiontest;

import java.util.Queue;

import model.TreeNode;
import parser.interpreter.Interpreter;
import parser.tokenizer.TokenList;
import parser.tokenizer.TokenListGenerator;

public class TreeTester {
	
	public static void main(String args[]){
		String testCode = "repeat 3 [ product ] fd fd 30 repeat 12 [ 50 ]";
		
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
