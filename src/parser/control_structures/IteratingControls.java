package parser.control_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import controller.SLogoException;
import parser.helpers.RangeHandler;
import parser.helpers.RegControl;
import parser.helpers.Replacer;
import parser.tokenizer.TokenList;

public abstract class IteratingControls extends RegControl{
	private static final String VARIABLE = "Variable";
	private String key;
	protected RangeHandler range;
	private Replacer replacer;
	protected Map<String, Double> varMap;

	public IteratingControls(String INDICATOR, Map<String, Double> varMap){
		super(INDICATOR);
		this.varMap = varMap;
		replacer = new Replacer();
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
		if(!list.getLogo().get(place).equals(VARIABLE)){
			throw new SLogoException("Make sure to use an undefined variable");
		}
		key = list.getLiterals().get(ifStart + 1);
		if(varMap.keySet().contains(key)){
			throw new SLogoException("Cannot use a variable name that already exists: " + key);
		}
	}


}
