package parser.control_structures;

import java.util.List;

import model.turtle.State;
import parser.helpers.RegControl;
import parser.interpreter.BracketAid;
import parser.reflectiontest.TreeGenerator;
import parser.tokenizer.TokenList;

public class IfElseHandler extends RegControl{
	private static final String INDICATOR = "IfElse";
	
	public IfElseHandler(TokenList list, State t){
		super(list, INDICATOR, t);
		correctList();
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
}
