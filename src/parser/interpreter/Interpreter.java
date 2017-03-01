package parser.interpreter;

import java.util.ArrayList;
import java.util.Set;

import controller.SLogoException;
import model.turtle.State;
import parser.control_structures.DoTimesHandler;
import parser.control_structures.ForHandler;
import parser.control_structures.IfElseHandler;
import parser.control_structures.IfHandler;
import parser.tokenizer.TokenList;

public class Interpreter {
	private TokenList tokens;
	private State turtle;
	private Set<String> varKeys;

	public Interpreter(TokenList TL, State t, Set<String> varKeys) {
		tokens = new TokenList(new ArrayList<String>(TL.getLiterals()), new ArrayList<String>(TL.getLogo()));
		turtle = t;
		this.varKeys = varKeys;
		checkValidity();
	}
	
	private void checkValidity(){
		if(tokens.getLiterals().size() == tokens.getLogo().size()) return;
		throw new SLogoException("Invalid Token List");
	}
	
	
	public void handleVarLoops(){
		BracketHandler dt = new DoTimesHandler(tokens, turtle, varKeys);
		BracketHandler f = new ForHandler(tokens, turtle, varKeys);
	}
	
	public void handleRegLoops(){
		BracketHandler le = new LoopEdit(tokens, turtle);
		BracketHandler i = new IfHandler(tokens, turtle);
		BracketHandler ie = new IfElseHandler(tokens, turtle);
	}
	
	public TokenList getTokenList(){
		return tokens;
	}
}
