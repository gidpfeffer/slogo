package parser.storage;

import controller.SLogoException;
import parser.tokenizer.TokenList;

public class FixVars {
	private static final String VARIABLE = "Variable";
	private static final String MAKE_VAR = "MakeVariable";
	private static final String CONSTANT = "Constant";
	private VariableStorage VS;
	private TokenList TL;
	private boolean madeNew;
	private int location;
	
	public FixVars(VariableStorage VS){
		this.VS = VS;
	}
	
	public void fix(TokenList TL){
		this.TL = TL;
		madeNew = false;
		fix();
	}
	
	private void fix(){
		while(!isDone()){
			location = update();
			replace(location);
			madeNew = false;
			location = -1;
		}
	}
	
	private boolean isDone(){
		return !TL.getLogo().contains(VARIABLE);
	}
	
	private int update(){
		int loc = TL.getLogo().indexOf(VARIABLE);
		if(loc != 0 && TL.getLogo().get(loc - 1).equals(MAKE_VAR)){
			makeVar(loc);
		}
		handleUndefined(loc);
		return loc;
	}
	
	private void replace(int loc){
		if(madeNew){
			trim(loc);
		}
		String key = TL.getLiterals().get(location);
		TL.getLiterals().set(location, VS.getMap().get(key).toString());
		TL.getLogo().set(location, CONSTANT);
	}
	
	private void trim(int loc){
		TL.getLiterals().remove(loc - 1);
		TL.getLogo().remove(loc - 1);
		TL.getLiterals().remove(loc);
		TL.getLogo().remove(loc);
		location = loc - 1;
	}
	
	private void makeVar(int loc){
		handleErrors(loc);
		String key = TL.getLiterals().get(loc);
		double val = Double.parseDouble(TL.getLiterals().get(loc + 1));
		VS.setValue(key, val);
		madeNew = true;
	}
	
	private void handleErrors(int loc){
		if(loc == TL.getLiterals().size() - 1 || !TL.getLogo().get(loc + 1).equals(CONSTANT)){
			throw new SLogoException("Invalid variable deifnition");
		}
	}
	
	private void handleUndefined(int loc){
		if(!VS.getMap().containsKey(TL.getLiterals().get(loc))){
			throw new SLogoException("Variable never defined");
		}
	}

}
