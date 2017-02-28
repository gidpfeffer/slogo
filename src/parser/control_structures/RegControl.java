package parser.control_structures;

import model.turtle.TurtleState;
import parser.interpreter.BracketAid;
import parser.reflectiontest.TreeGenerator;
import parser.tokenizer.TokenList;

public abstract class RegControl extends BracketAid{
	protected static final String CONSTANT = "Constant";
	protected int ifStart, ifEnd, elseStart, elseEnd;
	
	public RegControl(TokenList list, String indicator, TurtleState t){
		super(list, indicator, t);
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
	protected void replace() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void checkValidity() {
		if(!literals.get(ifEnd + 1).equals(LEFT_BRACKET)){
			throw new IllegalStateException("Else bracket doesnt follow if bracket");
		}
	}
	
	protected boolean evaulateExpression(){
		int loc = getLogoLocations(indicator).get(0);
		if(logo.get(loc + 1).equals(CONSTANT)){
			return Double.parseDouble(literals.get(loc + 1)) != 0;
		}
		TreeGenerator TG = new TreeGenerator(
				list.newSubList(getLogoLocations(indicator).get(0) + 1, ifStart), turtle);
		if(TG.getCommandQueue().size() == 0) 
			throw new IllegalStateException("invalid if/else syntax");
		return TG.getLast() != 0;
	}

}
