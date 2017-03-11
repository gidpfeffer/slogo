package parser.interfaces;

import model.turtle.State;
import parser.tokenizer.TokenList;

public interface RangeHandler {
	
	void handleRange(State t, TokenList TL);

}
