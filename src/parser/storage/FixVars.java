package parser.storage;

import controller.SLogoException;
import parser.tokenizer.TokenList;

public class FixVars extends StorageHandler{
	private static final String VARIABLE = "Variable";
	private static final String MAKE_VAR = "MakeVariable";
	private static final String CONSTANT = "Constant";
	private VariableStorage VS;
	
	public FixVars(VariableStorage VS){
		super(VARIABLE);
		this.VS = VS;
	}
	
	public void fix(TokenList list){
		this.list = list;
		madeNew = false;
		fix();
	}
	
	protected int update(){
		int loc = list.getLogo().indexOf(VARIABLE);
		if(loc != 0 && list.getLogo().get(loc - 1).equals(MAKE_VAR)){
			makeVar(loc);
		}
		handleUndefined(loc);
		return loc;
	}
	
	protected void replace(int loc){
		if(madeNew){
			trim(loc);
		}
		String key = list.getLiterals().get(location);
		list.getLiterals().set(location, VS.getMap().get(key).toString());
		list.getLogo().set(location, CONSTANT);
	}
	
	private void trim(int loc){
		list.getLiterals().remove(loc - 1);
		list.getLogo().remove(loc - 1);
		list.getLiterals().remove(loc);
		list.getLogo().remove(loc);
		location = loc - 1;
	}
	
	private void makeVar(int loc){
		handleErrors(loc);
		String key = list.getLiterals().get(loc);
		double val = Double.parseDouble(list.getLiterals().get(loc + 1));
		VS.setValue(key, val);
		madeNew = true;
	}
	
	private void handleErrors(int loc){
		if(loc == list.getLiterals().size() - 1 || !list.getLogo().get(loc + 1).equals(CONSTANT)){
			throw new SLogoException("Invalid variable deifnition");
		}
	}
	
	private void handleUndefined(int loc){
		if(!VS.getMap().containsKey(list.getLiterals().get(loc))){
			throw new SLogoException("Variable never defined");
		}
	}
}
