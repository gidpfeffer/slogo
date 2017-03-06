package parser.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.SLogoException;
import parser.tokenizer.TokenList;

public class CommandHandler extends StorageHandler{
	private static final String COMMAND = "Command";
	private static final String NEW = "MakeUserInstruction";
	private CommandStorage storage;
	private FunctionObj current;
	private FunctionBracketAid FBA;
	private String name;
	private List<String> arguments;
	
	
	public CommandHandler(CommandStorage storage){
		super(COMMAND, NEW);
		this.storage = storage;
	}

	@Override
	protected void replace(int location) {
		int numArgs = current.numArgs();
		fillArgs(numArgs);
		TokenList newList = getNewList();
		list.removeAll(location, location + numArgs);
		list.addAll(newList, location);
	}
	
	private TokenList getNewList(){
		Map<String, String> varMap = makeMap();
		List<String> literals = new ArrayList<>();
		List<String> logo = new ArrayList<>();
		TokenList function = storage.getMap().get(current);
		for(int i = 0; i < function.size(); i++){
			if(varMap.containsKey(function.getLiterals().get(i))){
				literals.add(varMap.get(function.getLiterals().get(i)));
				logo.add(CONSTANT);
			}
			else{
				literals.add(function.getLiterals().get(i));
				logo.add(function.getLogo().get(i));
			}
		}
		return new TokenList(literals, logo);
	}
	
	private Map<String, String> makeMap(){
		Map<String, String> map = new HashMap<>();
		for(int i = 0 ; i < current.numArgs(); i++){
			map.put(current.getArgs().get(i), arguments.get(i));
		}
		return map;
	}

	@Override
	protected void handleUndefined(int index) {
		name = list.getLiterals().get(index);
		boolean found = false;
		for(FunctionObj f: storage.getMap().keySet()){
			if(f.name().equals(name)){
				found = true;
				current = f;
			}
		}
		if(!found) throw new SLogoException("function never defined");
	}
	
	public void fix(TokenList list){
		this.list = list;
		madeNew = false;
		FBA = new FunctionBracketAid(list, NEW, storage);
		fix();
	}

	@Override
	protected void make(int location) {
		throw new IllegalStateException("FunctionBracketAid didn't work");
	}
	
	private void fillArgs(int numArgs){
		arguments = new ArrayList<>();
		if(location + numArgs >= list.getLiterals().size()){
			throw new SLogoException("Invalid number of arguments");
		}
		for(int i = 1; i <= numArgs; i++){
			arguments.add(list.getLiterals().get(i + location));
			if(!list.getLogo().get(i + location).equals(CONSTANT)){
				throw new SLogoException("Illegal number of function arguments");
			}
		}
	}
}
