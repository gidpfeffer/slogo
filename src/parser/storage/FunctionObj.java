package parser.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FunctionObj{
	private String name;
	private List<String> args;
	private int numArgs;
	
	public FunctionObj(String name, List<String> args){
		this.name = name;
		this.args = new ArrayList<>(args);
		numArgs = args.size();
	}
	
	public int numArgs(){
		return numArgs;
	}
	
	public String name(){
		return name;
	}
	
	public List<String> getArgs(){
		return Collections.unmodifiableList(args);
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof FunctionObj)) return false;
		return this.hashCode() == o.hashCode();
	}
	
	@Override
	public int hashCode(){
		return (name + numArgs()).hashCode();
	}
}
