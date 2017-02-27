package model;

import java.util.*;

import model.command.TurtleCommand;


public class StorageTester {
	static FunctionStorage<String, List<String>> functions; 
	static VariableStorage<String, Integer> variables; 
	
	public static void main(String [] args){
		functions = new FunctionStorage<String, List<String>>(); 
		variables = new VariableStorage<String, Integer>(); 
		
		HashMap<String, Integer > variableMap = new HashMap<String, Integer>(); 
		HashMap<String, List<TurtleCommand>> functionMap = new HashMap<String, List<TurtleCommand>>();
		
		variableMap.put("x", 5);
		ArrayList<String> list = new ArrayList<String>(); 
		list.add(new TurtleCommand());
		list.add("forward");
		functionMap.put("square", list );
		
		functions.addItem(functionMap);
		//variables.addItem(variables);
		 List<String> tester = (List<String>) functions.getItem("square");
		 for (Object i : tester){
			 System.out.println(i);
		 }
		
	}

}
