package parser.control_structures;

import java.util.Set;

import model.turtle.State;
import parser.helpers.forRange;
import parser.tokenizer.TokenList;

public class ForHandler extends IteratingControls{
	private static final String INDICATOR = "For";

	public ForHandler(TokenList TL, State t, Set<String> keys) {
		super(TL, t, keys, INDICATOR);
		range = new forRange();
		correctList();
	}

}
