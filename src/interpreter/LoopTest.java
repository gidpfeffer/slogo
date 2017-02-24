package interpreter;

import java.util.List;

import tokenizer.TokenList;
import tokenizer.TokenListGenerator;

public class LoopTest {

	public static void main(String args[]){
		String testCode = "#fdsflkdls \n repeat 2 [ fd rt 20 pick [ 10 20 30 ] repeat 5 [ 50 ] ] ";
		
		TokenListGenerator t = new TokenListGenerator(testCode);
		TokenList TL = t.getList();
		Interpreter IT = new Interpreter(TL);
		
		List<String> literals = TL.getLiterals();
		List<String> logo = TL.getLogo();
		
		System.out.println(testCode + "\n====================\n");
		
		for(int i = 0; i < literals.size(); i++){
			System.out.format("%s %s\n", literals.get(i), logo.get(i));
		}
	}
	
}
