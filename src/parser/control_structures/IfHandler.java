package parser.control_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.turtle.State;
import parser.interpreter.BracketAid;
import parser.tokenizer.TokenList;

public class IfHandler extends BracketAid{
	private static final String INDICATOR = "If";
	private static final String LEFT_BRACKET = "ListStart";
	private static final String RIGHT_BRACKET = "ListEnd";
	private static final String IF_ELSE = "IfElse";
	private int addLoc;
	protected State turtle;
	
	public IfHandler(TokenList tokens, State state){
		super(tokens, INDICATOR);
		this.turtle = state;
		correctList();
	}

	@Override
	protected void reset() {
		addLoc = -1;
	}

	@Override
	protected void findIndices() {
		int ifStart = findStartBracket(getLogoLocations(indicator).get(0));
		int ifEnd = findEndBracket(ifStart);
		addLoc = ifEnd + 1;
	}

	@Override
	protected void replace() {
		List<String> literalAdd = new ArrayList<>(Arrays.asList(new String[] {"[", "]"}));
		List<String> logoAdd = new ArrayList<>(Arrays.asList(new String[] {LEFT_BRACKET, RIGHT_BRACKET}));
		list.getLogo().set(getLogoLocations(indicator).get(0), IF_ELSE);
		list.getLiterals().addAll(addLoc, literalAdd);
		list.getLogo().addAll(addLoc, logoAdd);
	}

	@Override
	protected void checkValidity() {
		if(getLogoLocations(LEFT_BRACKET).size() != 
				getLogoLocations(RIGHT_BRACKET).size()){
			throw new IllegalArgumentException("Invalid bracket syntax");
		}
	}
}
