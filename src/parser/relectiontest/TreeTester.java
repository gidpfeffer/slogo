package parser.relectiontest;

import java.util.List;

import model.ListEnd;
import model.ListStart;
import model.TreeNode;
import parser.interpreter.Interpreter;
import parser.tokenizer.TokenList;
import parser.tokenizer.TokenListGenerator;

public class TreeTester {
	
	public static void main(String args[]){
		String testCode = "product [ fd 20 ] 40";
		
		TokenListGenerator t = new TokenListGenerator(testCode);
		TokenList TL = t.getList();
		Interpreter IT = new Interpreter(TL);
		
		TreeGenerator TG = new TreeGenerator(TL);
		
		List<TreeNode> list = TG.getList();
		
		for(TreeNode node : list){
			if(node instanceof ListStart){
				System.out.println("[");
			}
			else if(node instanceof ListEnd){
				System.out.println("]");
			}
			else System.out.println(node.getValue());
		}
	}

}
