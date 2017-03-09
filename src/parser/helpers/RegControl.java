package parser.helpers;

import controller.SLogoException;
import model.turtle.State;
import parser.interpreter.BracketAid;
import parser.reflection.TreeGenerator;

public abstract class RegControl extends BracketAid{
	protected static final String CONSTANT = "Constant";
	protected int ifStart, ifEnd, elseStart, elseEnd;
	protected State turtle;
	
	public RegControl(String indicator){
		super(indicator);
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
		if(!list.getLiterals().get(ifEnd + 1).equals(LEFT_BRACKET)){
			throw new SLogoException("Else bracket doesnt follow if bracket");
		}
	}
	
	protected boolean evaulateExpression(){
		int loc = getLogoLocations(indicator).get(0);
		if(list.getLogo().get(loc + 1).equals(CONSTANT)){
			return Double.parseDouble(list.getLiterals().get(loc + 1)) != 0;
		}
		TreeGenerator TG = new TreeGenerator(
				list.newSubList(getLogoLocations(indicator).get(0) + 1, ifStart), turtle);
		if(TG.getCommandQueue().size() == 0) 
			throw new SLogoException("invalid if/else syntax");
		return TG.getLast() != 0;
	}

}
