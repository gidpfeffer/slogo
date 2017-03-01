package parser.control_structures;

import model.turtle.TurtleState;
import parser.interpreter.Interpreter;
import parser.tokenizer.TokenList;
import parser.tokenizer.TokenListGenerator;

public class IfElseTest {
	public static void main(String args[]){
		String testCode = "if fd 10 [ ifelse 50 [ fd 50 ] [ rt 3 ] ]";
		
		TokenListGenerator t = new TokenListGenerator(testCode);
		TokenList TL = t.getList();
		
		TurtleState TS = new TurtleState();
		
		Interpreter IT = new Interpreter(TL, TS);
		
		TokenList list = IT.getTokenList();
		
		System.out.println(testCode + "\n====================\n");
		
		for(int i = 0; i < list.size(); i++){
			System.out.format("%s %s\n", list.getLiterals().get(i), list.getLogo().get(i));
		}
	}
}
