package parser.control_structures;

import java.util.Set;

import model.turtle.State;
import parser.helpers.forRange;
import parser.tokenizer.TokenList;

public class ForHandler extends IteratingControls{
	private static final String INDICATOR = "For";

	public ForHandler(Set<String> keys) {
		super(keys, INDICATOR);
		range = new forRange();
	}
	
	public void handle(TokenList TL, State t){
		list = TL;
		turtle = t;
		checkSyntax();
		correctList();
	}
}
