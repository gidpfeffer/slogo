package parser.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class VariableStorage extends Observable{
	
	private Map<String, Double> map;
	
	public VariableStorage(){
		map = new HashMap<>();
	}
	
	public Map<String, Double> getMap(){
		return new HashMap<>(map);
	}
	
	public void setValue(String key, double val){
		map.put(key, val);
		setChanged();
		notifyObservers();
	}
	
	public void clearMap(){
		map.clear();
		setChanged();
		notifyObservers();
	}
}
