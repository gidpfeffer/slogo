package parser.control_structures;

import parser.interpreter.Interpreter;
import parser.tokenizer.TokenList;
import parser.tokenizer.TokenListGenerator;

public class IfElseTest {
	public static void main(String args[]){
		String testCode = "ifelse product 5 10 [ ifelse 50 [ fd 50 ] [ rt 3 ] ] [ fd 5 ]";
		
		TokenListGenerator t = new TokenListGenerator(testCode);
		TokenList TL = t.getList();
		Interpreter IT = new Interpreter(TL);
		
		TokenList list = IT.getTokenList();
		
		System.out.println(testCode + "\n====================\n");
		
		for(int i = 0; i < list.size(); i++){
			System.out.format("%s %s\n", list.getLiterals().get(i), list.getLogo().get(i));
		}
	}
}
