package model;

import java.util.Map;

/**
 * @author tahiaemran
 *
 * @param <K>
 * @param <V>
 * 
 * This class is used to define the behavior of different types of storage the SLogo program may have 
 * 
 */

public interface StorageInterface <K,V>{
	/**
	 * @param map - the contents of this map differ according to what's being stored 
	 * for sLogo <String, List<Commands>> for functions or <String, Value> for variable definitions
	 * 
	 * method is used to add an item to storage 
	 */
	public void addItem(Map< K,V> map);

	/**
	 * @param name - the name of the item (function or variable) that's being stored
	 * 
	 * method is used to remove an item with the given name from storage
	 */
	public void removeItem(String name);

	/**
	 * @param name - the name of the item (function or variable) in storage 
	 * 
	 * method is used to check whether storage has an item 
	 * @return
	 */
	public boolean hasItem(String name);


	/**
	 * @param name - the name of the item (function or variable) in storage
	 * 
	 * method is used to get the value of an item from storage 
	 * @return
	 */
	public Object getItem(String name);
}
