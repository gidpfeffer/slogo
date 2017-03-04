package parser.tests;

import parser.tokenizer.CommentRemover;

public class CommentRemoverTest {
	
	public static void main(String args[]){
		String testCode = "to blah [ :x :y ] # fd 50 \n fd 30";
		
		CommentRemover CR = new CommentRemover(testCode);
		
		System.out.println(CR.getString());
	}

}
