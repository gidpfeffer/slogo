package parser.storage;

import java.util.List;

public class FunctionObj{
	private String name;
	private List<String> args;
	
	public FunctionObj(String name, List<String> args){
		this.name = name;
		this.args = args;
	}
	
	public int numArgs(){
		return args.size();
	}
	
	public String name(){
		return name;
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
