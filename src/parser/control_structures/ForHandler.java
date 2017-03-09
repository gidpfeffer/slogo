package parser.control_structures;

import java.util.Map;
import parser.helpers.forRange;

public class ForHandler extends AbstractIteratingControls{
	private static final String INDICATOR = "For";

	public ForHandler(Map<String, Double> varMap) {
		super(INDICATOR, varMap);
		range = new forRange();
	}
}
