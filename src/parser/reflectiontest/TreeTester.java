package parser.reflectiontest;

import model.ListEnd;
import model.ListStart;
import model.TreeNode;
import parser.main.Parser;

public class TreeTester {
	
	public static void main(String args[]){
		String testCode1 = "repeat 5 [ [ fd 20 ] [ fd 40 ] ]";
		
		Parser p = new Parser();
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
		
		String testCode2 = "repeat 2 [ if fd 20 [ fd 5 ] ifelse 5 [ fd 1 ] [ fd 3 ] ]";
		
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
