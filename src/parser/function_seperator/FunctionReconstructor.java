package parser.function_seperator;

import java.util.ArrayList;
import java.util.List;

import parser.storage.CommandStorage;
import parser.storage.FunctionObj;

public class FunctionReconstructor {
	
	public FunctionReconstructor(){
	}
	
	
	public List<String> getCommandList(CommandStorage command){
		List<String> functionList = new ArrayList<>();
		for(FunctionObj f: command.getMap().keySet()){
			functionList.add(reconstruct(f.name(), f.getArgs()));
		}
		return functionList;
	}
	
	private String reconstruct(String name, List<String> args){
		String params = "";
		for(String s: args){
			params = params + s + " ";
		}
		return name + " [ " + params + "]";
	}
}
