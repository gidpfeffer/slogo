package model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tahiaemran
 * 
 *this class allows for the storage of user defined variables
 *
 * @param <K>
 * @param <V>
 */
public class VariableStorage<K,V> implements StorageInterface<K,V>{
	
	private HashMap<String, Object> variableMap = new HashMap<String, Object>();
	
	public VariableStorage(){
		variableMap = new HashMap<String, Object>();
	}
	
	public void addItem(Map<K, V> map) {
		for (K key :map.keySet()){
			if (map.get(key) instanceof Object){
			variableMap.put((String) key, (V) map.get(key));
			}
		}
	}

	public void removeItem(String name) {
		variableMap.remove(name);
		
	}

	public boolean hasItem(String name) {
		return variableMap.containsKey(name);
	}


	public Object getItem(String name) {
		// Throw an error here 
		if(this.hasItem(name)){
			return variableMap.get(name);
		}
		return null; 
	}

}
