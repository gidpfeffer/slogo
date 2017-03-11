package parser.control_structures;

import java.util.List;

import parser.helpers.AbstractAdvancedControl;

public class RepeatHandler extends AbstractAdvancedControl{
	private static final String REPEAT = "Repeat";
	private int startIndex, endIndex, times;
	
	public RepeatHandler(){
		super(REPEAT);
	}
	
	protected void reset(){
		startIndex = endIndex = times = -1;
		checkValidity();
	}
	
	protected void findIndices(){
		ifStart = startIndex = findStartBracket(getLogoLocations(indicator).get(0));
		times = getTimes();
		endIndex = findEndBracket(startIndex);
	}
	
	private int getTimes(){
		return (int) evaluateExpression().pop().getValue();
	}
	
	protected void replace(){
		List<String> literalFiller = getSubList(list.getLiterals(), startIndex + 1, endIndex, times);
		List<String> logoFiller = getSubList(list.getLogo(), startIndex + 1, endIndex, times);
		listMultiplier.replace(getLogoLocations(indicator).get(0), endIndex, list.getLiterals(), literalFiller);
		listMultiplier.replace(getLogoLocations(indicator).get(0), endIndex, list.getLogo(), logoFiller);
	}

}
