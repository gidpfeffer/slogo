package parser.interpreter;

import java.util.ArrayList;

import parser.control_structures.IfElseHandler;
import parser.control_structures.IfHandler;
import parser.tokenizer.TokenList;

public class Interpreter {
	private TokenList tokens;

	public Interpreter(TokenList TL) {
		tokens = new TokenList(new ArrayList<String>(TL.getLiterals()), new ArrayList<String>(TL.getLogo()));
		checkValidity();
		interpret();
	}
	
	private void checkValidity(){
		if(tokens.getLiterals().size() == tokens.getLogo().size()) return;
		throw new IllegalStateException("Invalid Token List");
	}
	
	private void interpret(){
		handleLoops();
	}
	
	private void handleLoops(){
		BracketHandler le = new LoopEdit(tokens);
		BracketHandler i = new IfHandler(tokens);
		BracketHandler ie = new IfElseHandler(tokens);
	}
	
	public TokenList getTokenList(){
		return tokens;
	}
}
