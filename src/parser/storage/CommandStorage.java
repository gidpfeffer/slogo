package parser.storage;

import java.util.HashMap;
import java.util.Map;

import parser.tokenizer.TokenList;

public class CommandStorage {
	private Map<FunctionObj, TokenList> map;
	
	public CommandStorage(){
		map = new HashMap<>();
	}
	
	public Map<FunctionObj, TokenList> getMap(){
		return map;
	}
	
	public void setValue(FunctionObj key, TokenList val){
		map.put(key, val);
	}
	
	public void clearMap(){
		map.clear();
	}
}
