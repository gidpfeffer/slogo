package parser.interpreter;

import java.util.ArrayList;

import model.turtle.State;
import parser.control_structures.IfElseHandler;
import parser.control_structures.IfHandler;
import parser.tokenizer.TokenList;

public class Interpreter {
	private TokenList tokens;
	private State turtle;

	public Interpreter(TokenList TL, State t) {
		tokens = new TokenList(new ArrayList<String>(TL.getLiterals()), new ArrayList<String>(TL.getLogo()));
		turtle = t;
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
		BracketHandler le = new LoopEdit(tokens, turtle);
		BracketHandler i = new IfHandler(tokens, turtle);
		BracketHandler ie = new IfElseHandler(tokens, turtle);
	}
	
	public TokenList getTokenList(){
		return tokens;
	}
}
