package parser.storage;

import java.util.HashMap;
import java.util.Map;

public class VariableStorage {
	
	private Map<String, Double> map;
	
	public VariableStorage(){
		map = new HashMap<>();
	}
	
	public Map<String, Double> getMap(){
		return new HashMap<>(map);
	}
	
	public void setValue(String key, double val){
		map.put(key, val);
	}
	
	public void clearMap(){
		map.clear();
	}
}
