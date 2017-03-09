package parser.control_structures;

import java.util.List;

import model.turtle.State;
import parser.helpers.RegControl;
import parser.tokenizer.TokenList;

public class IfElseHandler extends RegControl{
	private static final String INDICATOR = "IfElse";
	
	public IfElseHandler(){
		super(INDICATOR);
	}
	
	public void handle(TokenList TL, State t){
		list = TL;
		turtle = t;
		checkSyntax();
		correctList();
	}

	@Override
	protected void replace(){
		int place = getLogoLocations(indicator).get(0);
		List<String> literalFiller = getSubList(list.getLiterals(), elseStart + 1, elseEnd);
		List<String> logoFiller = getSubList(list.getLogo(), elseStart + 1, elseEnd);
		if(evaulateExpression()){
			literalFiller = getSubList(list.getLiterals(), ifStart + 1, ifEnd);
			logoFiller = getSubList(list.getLogo(), ifStart + 1, ifEnd);
		}
		listMultiplier.replace(place, elseEnd, list.getLiterals(), literalFiller);
		listMultiplier.replace(place, elseEnd, list.getLogo(), logoFiller);
	}
}
