package parser.control_structures;

import java.util.List;

import model.turtle.TurtleState;
import parser.interpreter.BracketAid;
import parser.reflectiontest.TreeGenerator;
import parser.tokenizer.TokenList;

public class IfElseHandler extends BracketAid{
	private static final String INDICATOR = "IfElse";
	private static final String CONSTANT = "Constant";
	private int ifStart, ifEnd, elseStart, elseEnd;
	
	
	public IfElseHandler(TokenList list, TurtleState t){
		super(list, INDICATOR, t);
	}

	@Override
	protected void checkValidity() {
		if(!literals.get(ifEnd + 1).equals(LEFT_BRACKET)){
			throw new IllegalStateException("Else bracket doesnt follow if bracket");
		}	
	}
	
	@Override
	protected void reset() {
		ifStart = ifEnd = elseStart = elseEnd = -1;	
	}

	@Override
	protected void findIndices() {
		ifStart = findStartBracket(getLogoLocations(indicator).get(0));
		ifEnd = findEndBracket(ifStart);
		checkValidity();
		elseStart = findStartBracket(ifEnd);
		elseEnd = findEndBracket(elseStart);
	}
	

	@Override
	protected void replace(){
		int place = getLogoLocations(indicator).get(0);
		List<String> literalFiller = getSubList(literals, elseStart + 1, elseEnd);
		List<String> logoFiller = getSubList(logo, elseStart + 1, elseEnd);
		if(evaulateExpression()){
			literalFiller = getSubList(literals, ifStart + 1, ifEnd);
			logoFiller = getSubList(logo, ifStart + 1, ifEnd);
		}
		listMultiplier.replace(place, elseEnd, list.getLiterals(), literalFiller);
		listMultiplier.replace(place, elseEnd, list.getLogo(), logoFiller);
	}

	private boolean evaulateExpression(){
		int loc = getLogoLocations(indicator).get(0);
		if(logo.get(loc + 1).equals(CONSTANT)){
			return Double.parseDouble(literals.get(loc + 1)) != 0;
		}
		TreeGenerator TG = new TreeGenerator(
				list.newSubList(getLogoLocations(indicator).get(0) + 1, ifStart), turtle);
		if(TG.getQueue().size() == 0) 
			throw new IllegalStateException("invalid if/else syntax");
		return TG.getLast() != 0;
	}
}
