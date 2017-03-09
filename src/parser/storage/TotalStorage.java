package parser.storage;

public class TotalStorage {
	private VariableStorage vars;
	private CommandStorage commands;
	
	public TotalStorage(){
		vars = new VariableStorage();
		commands = new CommandStorage();
	}
	
	public VariableStorage getVars(){
		return vars;
	}
	
	public CommandStorage getCommands(){
		return commands;
	}

}
