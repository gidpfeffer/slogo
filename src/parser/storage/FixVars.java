package parser.storage;

import controller.SLogoException;
import parser.interfaces.StorageFixer;
import parser.tokenizer.TokenList;

public class FixVars extends AbstractStorageHandler implements StorageFixer{
	private static final String VARIABLE = "Variable";
	private static final String MAKE_VAR = "MakeVariable";
	private VariableStorage VS;
	
	public FixVars(VariableStorage VS){
		super(VARIABLE, MAKE_VAR);
		this.VS = VS;
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
	
	protected void make(int loc){
		handleErrors(loc);
		String key = list.getLiterals().get(loc);
		double val = Double.parseDouble(list.getLiterals().get(loc + 1));
		VS.setValue(key, val);
		madeNew = true;
	}
	
	public void fix(TokenList list){
		this.list = list;
		madeNew = false;
		fix();
	}
	
	private void handleErrors(int loc){
		if(loc == list.getLiterals().size() - 1 || !list.getLogo().get(loc + 1).equals(CONSTANT)){
			throw new SLogoException("Invalid variable deifnition");
		}
	}
	
	protected void handleUndefined(int loc){
		if(!VS.getMap().containsKey(list.getLiterals().get(loc))){
			throw new SLogoException("Variable never defined");
		}
	}
}
