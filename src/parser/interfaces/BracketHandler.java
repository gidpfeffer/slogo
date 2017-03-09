package parser.interfaces;

import model.turtle.State;
import parser.tokenizer.TokenList;

public interface BracketHandler {
	
	void handle(TokenList list, State state);
	
}
