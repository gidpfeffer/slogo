package parser.reflectiontest;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class PackageLocationHandler {
	private ResourceBundle bundle;
	private static final String LOC = "resources/languages/Locations";

	public PackageLocationHandler(){
		definePattern();
	}

	private void definePattern(){
		bundle = ResourceBundle.getBundle(LOC);
	}
	
	public String getLoc(String logoKey) {
		Enumeration<String> iter = bundle.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			if(logoKey.equals(key)){
				return bundle.getString(key);
			}
		}
		return "";
	}

}
