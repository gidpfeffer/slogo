package parser.control_structures;

import java.util.List;

import controller.SLogoException;
import model.turtle.State;
import parser.interpreter.BracketAid;
import parser.reflection.TreeGenerator;
import parser.tokenizer.TokenList;

public class RepeatHandler extends BracketAid{
	private static final String CONSTANT = "Constant";
	private static final String REPEAT = "Repeat";
	private int startIndex, endIndex, times;
	private State turtle; 
	
	public RepeatHandler(){
		super(REPEAT);
	}
	
	public void handle(TokenList TL, State t){
		list = TL;
		turtle = t;
		checkSyntax();
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
		if(list.getLogo().get(loc + 1).equals(CONSTANT)){
			return (int) Double.parseDouble(list.getLiterals().get(loc + 1));
		}
		TreeGenerator TG = new TreeGenerator(
				list.newSubList(loc + 1, startIndex), turtle);
		if(TG.getCommandQueue().size() == 0) 
			throw new SLogoException("invalid if/else syntax");
		return (int) TG.getLast();
	}
	
	protected void replace(){
		List<String> literalFiller = getSubList(list.getLiterals(), startIndex + 1, endIndex, times);
		List<String> logoFiller = getSubList(list.getLogo(), startIndex + 1, endIndex, times);
		listMultiplier.replace(getLogoLocations(indicator).get(0), endIndex, list.getLiterals(), literalFiller);
		listMultiplier.replace(getLogoLocations(indicator).get(0), endIndex, list.getLogo(), logoFiller);
	}
	
	protected void checkValidity(){
		int repeatIndex = getLogoLocations(REPEAT).get(0);
		if(repeatIndex + 2 >= list.getLiterals().size()){
			throw new SLogoException("Invalid Bracket Syntax");
		}
	}

}
