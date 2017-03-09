package parser.interpreter;

import java.util.Set;
import controller.SLogoException;
import model.turtle.State;
import parser.control_structures.DoTimesHandler;
import parser.control_structures.ForHandler;
import parser.control_structures.IfElseHandler;
import parser.control_structures.IfHandler;
import parser.control_structures.RepeatHandler;
import parser.tokenizer.TokenList;

public class Interpreter {
	private BracketHandler[] varLoops, regLoops;

	public Interpreter(Set<String> varKeys) {
		makeVarLoops(varKeys);
		makeRegLoops(varKeys);
	}
	
	private void makeVarLoops(Set<String> varKeys){
		varLoops = new BracketHandler[] {new DoTimesHandler(varKeys), new ForHandler(varKeys)};
	}
	
	private void makeRegLoops(Set<String> varKeys){
		regLoops = new BracketHandler[] {new RepeatHandler(), new IfHandler(), new IfElseHandler()};
	}
	
	private void checkValidity(TokenList tokens){
		if(tokens.getLiterals().size() == tokens.getLogo().size()) return;
		throw new SLogoException("Invalid Token List");
	}
	
	public void handleVarLoops(TokenList TL, State state){
		checkValidity(TL);
		for(BracketHandler b: varLoops){
			b.handle(TL, state);
		}
	}
	
	public void handleRegLoops(TokenList TL, State state){
		checkValidity(TL);
		for(BracketHandler b: regLoops){
			b.handle(TL, state);
		}
	}
}
