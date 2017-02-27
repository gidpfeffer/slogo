package general_data_structures;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author TNK
 *	holds the values for all variables that the user has input
 *	purpose of the class is to help organize backend code and
 *	make backend-frontend communication simpler
 */
public class UserVariables {
	private Map<String, Integer> nameToValueMap;
	public UserVariables(){
		nameToValueMap = new HashMap<String, Integer>();
	}
	
	public void appendNewVariable(Tuple<String, Integer> t){
		nameToValueMap.put(t.x, t.y);
	}
	public Map<String, Integer> getMap(){
		return nameToValueMap;
	}
	
}
