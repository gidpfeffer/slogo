package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import controller.Ask;
import gui.UIMain;
import model.ModelController;
import parser.tokenizer.ProtectedTokenList;

public class AskTellParser {
	
	private HashMap<Double, List<String>> turtlesToCommands;
	private ModelController currentModel; 
	private UIMain myView; 
	
	public AskTellParser(ModelController myModel, UIMain myViewController){
		turtlesToCommands = new HashMap<Double, List<String>>(); 
		currentModel = myModel;
		myView = myViewController; 
	}
	
	public Map<Double, List<String>> getParsedCommands(){
		return turtlesToCommands; 
	}
	
	private Queue<String> constructQ (ProtectedTokenList p){
		Queue<String> inputQ = new LinkedList<String>(); 
		List<String> literalInput = p.getLiterals(); 
		for (String s : literalInput){
			inputQ.add(s);
		}
		return inputQ; 
	}

	public void parseCommands(ProtectedTokenList p){
		Queue<String> commands = constructQ(p);
		turtlesToCommands.put(0.0, new ArrayList<String>()); //default value 
		ArrayList<Double> activeTurtleIds = new ArrayList<Double>(); 
		ArrayList<String> applyToActives = new ArrayList<String>(); 
		
		while (!commands.isEmpty()){
			String input = commands.poll(); 
			
			if (input.equals("ask")){
				Ask a =  handleAsk(commands);
			
				//System.out.println("ask turtles" + a.getTurtles());
				
				ArrayList<Double> turtleIds = getTurtleIds(a.getTurtles());				
				List<String> commandsPerTurtle = a.getCommands();
				
				for (Double id: turtleIds){
					if (!turtlesToCommands.containsKey(id)){
						turtlesToCommands.put(id, new ArrayList<String>(commandsPerTurtle));
						currentModel.makeNewTurtle(id).getState().addObserver(myView.addTurtle(id));;
						
					}
					else{
						turtlesToCommands.get(id).addAll(commandsPerTurtle);
					}
				}
				
				//System.out.println("ask commands" + a.getCommands());
				System.out.println("ask handled map" + turtlesToCommands);
			}
			
			else if (input.equals("tell")){
				
				ArrayList<String> turtlesStrings = handleTell(commands);
				activeTurtleIds.clear();
				activeTurtleIds = getTurtleIds(turtlesStrings); 
				System.out.println("tell turtles" + turtlesStrings);
				
			}
			
			else{
				
				applyToActives.clear();
				applyToActives.add(input);
				
				for (Double turtle: activeTurtleIds){
					if (!turtlesToCommands.containsKey(turtle)){
						turtlesToCommands.put(turtle, new ArrayList<String>(applyToActives));
						currentModel.makeNewTurtle(turtle).getState().addObserver(myView.addTurtle(turtle));;

					}
					else{
						turtlesToCommands.get(turtle).addAll(applyToActives);
					}
				}
				
			}
		}
		System.out.println("active commands" + applyToActives);
		
	
		System.out.print("command processed map" + turtlesToCommands); 
	}



	private ArrayList<Double> getTurtleIds(List<String> turtles) throws SLogoException{
		ArrayList<Double> ids = new ArrayList<Double>(); 
		for (String t: turtles){
			try {
				ids.add(Double.parseDouble(t));
			} catch (SLogoException e) {
				throw new SLogoException("invalid turtle ID");
			}
		}
		return ids; 
	}

	private ArrayList<String> handleTell(Queue<String> commands) {
		ArrayList<String> turtles = new ArrayList<String>(); 
		int bracketCount =0; 
		while (bracketCount<2 && !(commands.isEmpty())){
			String st = commands.poll();
			if ((st.equals("[")) || (st.equals("]"))){
				bracketCount++; 
			}
			else{
				turtles.add(st);
			}
		}
		return turtles; 
		
	}

	private Ask handleAsk(Queue<String> commands) {
		ArrayList<String> turtles = new ArrayList<String>(); 
		ArrayList<String> commandsToApply = new ArrayList<String>();
		int bracketCount = 0; 
		while (bracketCount<2 && !(commands.isEmpty())){
			String st = commands.poll(); 
			
			if ((st.equals("[")) || (st.equals("]"))){
				bracketCount++; 
			}
			else{
				turtles.add(st);
			}
		}
		if (bracketCount==2){
			while (bracketCount<4 && !(commands.isEmpty())){
				String st1 = commands.poll(); 
				if ((st1.equals("[")) || (st1.equals("]"))){
					bracketCount++; 
				}
				else{
					commandsToApply.add(st1);
				}
			}
		}
		Ask newAsk = new Ask(turtles, commandsToApply);
		return newAsk; 
	}

	
	
}
