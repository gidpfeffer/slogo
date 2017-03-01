package parser.control_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.SLogoException;
import model.turtle.State;
import parser.helpers.RegControl;
import parser.helpers.Replacer;
import parser.tokenizer.TokenList;

public class DoTimes extends RegControl{
	private static final String INDICATOR = "DoTimes";
	private static final String VARIABLE = "Variable";
	private String key;
	private RangeHandler range;
	private Replacer replacer;

	public DoTimes(TokenList TL, State t){
		super(TL, INDICATOR, t);
		range = new RangeHandler();
		replacer = new Replacer();
		correctList();
	}

	@Override
	protected void replace() {
		setKey();
		range.handle(turtle, list.newSubList(ifStart + 2, ifEnd));
		List<Integer> vals = range.getList();
		replaceEach(vals);
	}
	
	private void replaceEach(List<Integer> ints){
		TokenList genericReplacement = list.newSubList(elseStart + 1, elseEnd);
		int start = getLogoLocations(indicator).get(0);
		listMultiplier.replace(start, elseEnd, list, new ArrayList<>());
		Collections.reverse(ints);
		for(Integer i : ints){
			TokenList newRep = genericReplacement.newSubList(0, genericReplacement.getLiterals().size());
			replacer.replace(newRep, key, i);
			listMultiplier.replace(start, start, list.getLiterals(), newRep.getLiterals());
			listMultiplier.replace(start, start, list.getLogo(), newRep.getLogo());
		}
	}
	
	private void setKey(){
		int place = ifStart + 1;
		if(!logo.get(place).equals(VARIABLE)){
			throw new SLogoException("invalid syntax");
		}
		key = literals.get(ifStart + 1);
	}


}
