package parser.reflectiontest;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class TurtleCommandHandler {
	private ResourceBundle bundle;
	private static final String TURTLE_COMMANDS = "resources/languages/TurtleCommands";
	private static final String YES = "yes";

	public TurtleCommandHandler(){
		definePattern();
	}

	private void definePattern(){
		bundle = ResourceBundle.getBundle(TURTLE_COMMANDS);
	}
	
	public boolean isTurtleCommand(String logoKey) {
		Enumeration<String> iter = bundle.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			if(logoKey.equals(key)){
				return bundle.getString(key).equals(YES);
			}
		}
		throw new IllegalStateException("invalid read");
	}


}
