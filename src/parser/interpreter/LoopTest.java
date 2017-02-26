package parser.interpreter;

import java.util.List;

import parser.tokenizer.TokenList;
import parser.tokenizer.TokenListGenerator;

public class LoopTest {

	public static void main(String args[]){
		String testCode = "repeat 2 [ repeat 2 [ fd 50 ] repeat 5 [ 10 ] ]";
		
		TokenListGenerator t = new TokenListGenerator(testCode);
		TokenList TL = t.getList();
		Interpreter IT = new Interpreter(TL);
		
		List<String> literals = IT.getTokenList().getLiterals();
		List<String> logo = IT.getTokenList().getLogo();
		
		System.out.println(testCode + "\n====================\n");
		
		for(int i = 0; i < literals.size(); i++){
			System.out.format("%s %s\n", literals.get(i), logo.get(i));
		}
	}
	
}
