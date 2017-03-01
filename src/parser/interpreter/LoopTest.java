package parser.interpreter;

import java.util.List;

import model.turtle.TurtleState;
import parser.control_structures.DoTimes;
import parser.tokenizer.TokenList;
import parser.tokenizer.TokenListGenerator;

public class LoopTest {

	public static void main(String args[]){
		String testCode = "repeat 2 [ dotimes [ :x fd 3 ] [ fd :x ] ]";
		
		TokenListGenerator t = new TokenListGenerator(testCode);
		TokenList TL = t.getList();
		TurtleState TS = new TurtleState();
		
		DoTimes DT = new DoTimes(TL, TS);
		
		Interpreter IT = new Interpreter(TL, TS);
		
		List<String> literals = IT.getTokenList().getLiterals();
		List<String> logo = IT.getTokenList().getLogo();
		
		System.out.println(testCode + "\n====================\n");
		
		for(int i = 0; i < literals.size(); i++){
			System.out.format("%s %s\n", literals.get(i), logo.get(i));
		}
	}
	
}
