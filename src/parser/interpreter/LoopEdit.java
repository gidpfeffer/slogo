package parser.interpreter;

import java.util.List;
import java.util.Queue;

import model.command.TreeNode;
import parser.reflectiontest.TreeGenerator;
import parser.tokenizer.TokenList;

public class LoopEdit extends BracketAid{
	private static final String CONSTANT = "Constant";
	private static final String REPEAT = "Repeat";
	private int startIndex, endIndex, times;
	
	public LoopEdit(TokenList list){
		super(list, REPEAT);
		correctList();
	}
	
	protected void reset(){
		startIndex = endIndex = times = -1;
		checkValidity();
	}
	
	protected void findIndices(){
		startIndex = findStartBracket(getLogoLocations(indicator).get(0));
		times = evaluateExpression();
		endIndex = findEndBracket(startIndex);
	}
	
	private int evaluateExpression(){
		int loc = getLogoLocations(indicator).get(0);
		if(logo.get(loc + 1).equals(CONSTANT)){
			return (int) Double.parseDouble(literals.get(loc + 1));
		}
		TreeGenerator TG = new TreeGenerator(
				list.newSubList(loc + 1, startIndex), turtle);
		if(TG.getCommandQueue().size() == 0) 
			throw new IllegalStateException("invalid if/else syntax");
		return (int) TG.getLast();
	}
	
	protected void replace(){
		List<String> literalFiller = getSubList(literals, startIndex + 1, endIndex, times);
		List<String> logoFiller = getSubList(logo, startIndex + 1, endIndex, times);
		listMultiplier.replace(getLogoLocations(indicator).get(0), endIndex, literals, literalFiller);
		listMultiplier.replace(getLogoLocations(indicator).get(0), endIndex, logo, logoFiller);
	}
	
	protected void checkValidity(){
		int repeatIndex = getLogoLocations(REPEAT).get(0);
		if(repeatIndex + 2 >= literals.size()){
			throw new IllegalStateException("Invalid Bracket Syntax");
		}
	}

}
