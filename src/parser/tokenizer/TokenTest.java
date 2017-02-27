package parser.tokenizer;

import java.util.List;

public class TokenTest {

	public static void main(String args[]){
		String testCode = "make :x 10";
		
		TokenListGenerator t = new TokenListGenerator(testCode);
		
		TokenList TL = t.getList();
		
		List<String> literals = TL.getLiterals();
		List<String> logo = TL.getLogo();
		
		System.out.println(testCode + "\n====================\n");
		
		for(int i = 0; i < literals.size(); i++){
			System.out.format("%s %s\n", literals.get(i), logo.get(i));
		}
	}
	
}
