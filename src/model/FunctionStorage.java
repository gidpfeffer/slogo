package model;

import java.util.List;
import java.util.Map;
import java.util.Collection;


import model.command.TurtleCommand;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author tahiaemran
 * this class allows for the storage of user defined functions and the commands they correspond to 
 * @param <K> 
 * @param <V>
 */
public class FunctionStorage<K,V> implements StorageInterface<K,V>{
	
	private HashMap<String, List<TurtleCommand>> functionMap; 
	
	public FunctionStorage(){
		functionMap = new HashMap<String, List<TurtleCommand>>();
	}


	public void removeItem(String name) {
		functionMap.remove(name);
	}
	

	public boolean hasItem(String name) {
		return functionMap.containsKey(name);

	}

	
	public Object getItem(String name) {
		/// THROW AN EXCEPTION HERE 
		if (this.hasItem(name)){
			return functionMap.get(name);
		}
		return null; 
	}

 
	@Override
	public void addItem(Map<K, V> map) {
		// K = String name 
		// V = list of commands 
		for (K key: map.keySet()){
			List<TurtleCommand> commandsList = new ArrayList<TurtleCommand>(); 
			if(map.get(key) instanceof Collection<?>){
			Collection<?> list = (Collection<?>) map.get(key);
			for (Object o: list){
				commandsList.add((TurtleCommand) o);
			}
			functionMap.put((String) key, commandsList);
			}

		}

	}


}



