package controller;

import java.util.Map;

public interface CommandMap<K, V, T> {
	public Map<K,T> getPTLMap(); 
}
