package parser.control_structures;


import java.util.Set;

import model.turtle.State;
import parser.helpers.doTimesRange;
import parser.tokenizer.TokenList;

public class DoTimesHandler extends IteratingControls{
	private static final String INDICATOR = "DoTimes";

	public DoTimesHandler(TokenList TL, State t, Set<String> keys) {
		super(TL, t, keys, INDICATOR);
		range = new doTimesRange();
		correctList();
	}

}
