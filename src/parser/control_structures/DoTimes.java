package parser.control_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.turtle.TurtleState;
import parser.tokenizer.TokenList;

public class DoTimes extends RegControl{
	private static final String INDICATOR = "DoTimes";
	private static final String VARIABLE = "Variable";
	private String key;
	private RangeHandler range;

	public DoTimes(TokenList TL, TurtleState t){
		super(TL, INDICATOR, t);
		range = new RangeHandler();
		correctList();
	}

	@Override
	protected void replace() {
		setKey();
		range.handle(turtle, list.newSubList(ifStart + 2, ifEnd));
		List<Integer> vals = range.getList();
		TokenList statement = list.newSubList(elseStart + 1, elseEnd);
		int start = getLogoLocations(indicator).get(0);
		replaceEach(vals, start, statement);
	}
	
	private void replaceEach(List<Integer> ints, int start, TokenList statement){
		listMultiplier.replace(start, elseEnd, list, Arrays.asList(new String[] {}));
		System.out.println(statement.getLiterals().size());
		List<String> literalFiller = new ArrayList<>(statement.getLiterals());
		List<String> logoFiller = new ArrayList<>(statement.getLogo());
		for(Integer i : ints){
			listMultiplier.replace(start, start, list.getLiterals(), literalFiller);
			listMultiplier.replace(start, start, list.getLogo(), logoFiller);
		}
	}
	
	private void setKey(){
		int place = ifStart + 1;
		if(!logo.get(place).equals(VARIABLE)){
			throw new IllegalStateException("invalid for syntax");
		}
		key = literals.get(ifStart + 1);
	}


}
