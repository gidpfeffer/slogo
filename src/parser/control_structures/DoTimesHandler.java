package parser.control_structures;


import java.util.Map;

import parser.helpers.doTimesRange;

public class DoTimesHandler extends AbstractIteratingControls{
	private static final String INDICATOR = "DoTimes";

	public DoTimesHandler(Map<String, Double> varMap) {
		super(INDICATOR, varMap);
		range = new doTimesRange();
	}
}
