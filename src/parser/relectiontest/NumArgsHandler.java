package parser.relectiontest;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class NumArgsHandler {
	private ResourceBundle bundle;
	private static final String NUM_ARGS = "resources/languages/NumArgs";

	public NumArgsHandler(){
		definePattern();
	}

	private void definePattern(){
		bundle = ResourceBundle.getBundle(NUM_ARGS);
	}
	
	public int getNumArgs(String logoKey) {
		Enumeration<String> iter = bundle.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			if(logoKey.equals(key)){
				return Integer.parseInt(bundle.getString(key));
			}
		}
		return -1;
	}

}
