package parser.interpreter;

import java.util.List;
import java.util.Stack;

import parser.tokenizer.TokenList;

public class LoopEdit extends BracketAid{
	private static final String CONSTANT = "Constant";
	private static final String REPEAT = "Repeat";
	private int startIndex, endIndex, times;
	
	public LoopEdit(TokenList list){
		super(list, REPEAT);
	}
	
	protected void reset(){
		startIndex = endIndex = times = -1;
		checkValidity();
	}
	
	protected void findIndices(){
		startIndex = findStartBracket(getLogoLocations(indicator).get(0));
		times = Integer.parseInt(literals.get(startIndex - 1));
		endIndex = findEndBracket(startIndex);
	}
	
	protected void replace(){
		List<String> literalFiller = getSubList(literals, startIndex + 1, endIndex, times);
		List<String> logoFiller = getSubList(logo, startIndex + 1, endIndex, times);
		listMultiplier.replace(startIndex - 2, endIndex, literals, literalFiller);
		listMultiplier.replace(startIndex - 2, endIndex, logo, logoFiller);
	}
	
	protected void checkValidity(){
		int repeatIndex = getLogoLocations(REPEAT).get(0);
		if(repeatIndex + 2 >= literals.size() ||
				!literals.get(repeatIndex + 2).equals(LEFT_BRACKET) ||
				!logo.get(repeatIndex + 1).equals(CONSTANT)){
			throw new IllegalStateException("Invalid Bracket Syntax");
		}
	}

}
