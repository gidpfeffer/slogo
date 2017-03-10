package controller;

import java.util.List;

import model.ModelController;
import java.util.Map;

import controller.CommandMap;

import parser.main.NewParser;
import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MapMaker implements CommandMap<Double, List<String>, ProtectedTokenList>{
	Map <Double, List<String>> literalMap;
	Map <Double, ProtectedTokenList> ptlMap;

	private ModelController currentModel; 

	public MapMaker(List<Object> commandObjects, List<String> precedingCommands, List<String> remainingCommands,
			Map<Double, List<String>> currentLiterals, ModelController myModel){
		
		literalMap = currentLiterals; 
		ptlMap = new HashMap<Double, ProtectedTokenList>();
		
		currentModel = myModel; 

		buildLiteralMap(commandObjects, precedingCommands, remainingCommands);
		buildPTLMap();
	}

/*	// for testing only
	public MapMaker(List<Object> commandObjects, List<String> precedingCommands, List<String> remainingCommands){
		literalMap = new HashMap<Double, List<String>>();
		ptlMap = new HashMap<Double, ProtectedTokenList>();
		
		activeTurtles = new ArrayList<Double>();
		activeTurtles.add(1.0);

		buildLiteralMap(commandObjects, precedingCommands, remainingCommands);
		buildPTLMap();
	}
*/
	private void buildPTLMap() {
		for (Double tt : literalMap.keySet()){
			if (!ptlMap.containsKey(tt)){
				ProtectedTokenList p = createTokenList(literalMap.get(tt));
				ptlMap.put(tt, p);

			}
			else{
				ProtectedTokenList ptl = createTokenList(literalMap.get(tt));
				ptlMap.get(tt).add(new TokenList(ptl.getLiterals(), ptl.getLogo()));
			}
		}

	}


	private ProtectedTokenList createTokenList(List<String> commandsPerTurtle) {
		// commands per turtle = literal 
		String bigString = " ";
		for (String literal: commandsPerTurtle){
			bigString = bigString + literal + " ";
		}

		NewParser p = new NewParser("resources.languages/English");

		return p.parse(bigString);
	}

	private void buildLiteralMap(List<Object> commandObjects, List<String> precedingCommands, List<String> remainingCommands) {
		
		if (!precedingCommands.isEmpty()){
		for (String p : precedingCommands){
		
			for (Double t: currentModel.getActiveTurtleIDs()){
				if(!literalMap.containsKey(t)){
					literalMap.put(t, new ArrayList<String>());
				}
				literalMap.get(t).add(p);
			}
		}
		}
		
		
		
		for (Object c : commandObjects){
			
			if (c instanceof MultipleTurtleCommandClass ){

				MultipleTurtleCommandClass comm = (MultipleTurtleCommandClass) c; 
				comm.execute();
				AskTellData data = comm.getData(); 
				
				

				List<Double> turtleIDs = data.getTurtleIDS();
				List<String> commands = clean(data.getCommands());
				
				if (commands.isEmpty() && c instanceof Tell){
					
					for (Double i : turtleIDs){
					currentModel.setActiveTurtles(turtleIDs);
					
					if (!(currentModel.getTurtleIDs().contains(i))){
						currentModel.makeNewTurtle(i);						
					}
					}
					continue; 
				}
				
								
				for(Double id: turtleIDs){
		
					if (!(currentModel.getTurtleIDs().contains(id))){
						currentModel.makeNewTurtle(id);
						//literalMap.put(id, new ArrayList<String>(commands));
						
					}
					
					
					
					if (!(literalMap.containsKey(id))){
						literalMap.put(id, new ArrayList<String>(commands));

					}
					
					else{
						List<String> current = literalMap.get(id);
						ArrayList<String> newVal = new ArrayList<String>(current);
						newVal.addAll(commands);
						literalMap.put(id,  newVal);
					}

					if(c instanceof Tell){
						currentModel.setActiveTurtles(turtleIDs);
					}
				}

			}	
		}
		if (!remainingCommands.isEmpty()){
		for (Double t : currentModel.getActiveTurtleIDs()){
			if (!literalMap.containsKey(t)){
				literalMap.put(t, new ArrayList<String>());
			}
			literalMap.get(t).addAll(remainingCommands);

		}
		}
	}




	private List<String> clean(List<String> commands) {
		List<String> cleaned = new ArrayList<String>();
		for (String c: commands){
			if ((c.equals("[")) || (c.equals("]"))){
				continue; 
			}
			else{
				cleaned.add(c);
			}
		}
		
		return cleaned; 
	}

	public Map<Double, ProtectedTokenList> getPTLMap(){
		return Collections.unmodifiableMap(ptlMap);
	}	

}
