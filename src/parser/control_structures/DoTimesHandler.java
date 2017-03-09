package parser.control_structures;


import java.util.Map;

import model.turtle.State;
import parser.helpers.doTimesRange;
import parser.tokenizer.TokenList;

public class DoTimesHandler extends IteratingControls{
	private static final String INDICATOR = "DoTimes";

	public DoTimesHandler(Map<String, Double> varMap) {
		super(INDICATOR, varMap);
		range = new doTimesRange();
	}
	
	public void handle(TokenList TL, State t){
		list = TL;
		turtle = t;
		checkSyntax();
		correctList();
	}

}
