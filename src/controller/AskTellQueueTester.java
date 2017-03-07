package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import controller.Ask; 

import parser.main.NewParser;
import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenIdentifier;
import parser.tokenizer.TokenList;
import parser.tokenizer.Tokenizer;

public class AskTellQueueTester {
	
	private static Queue<String> constructQ (ProtectedTokenList p){
		Queue<String> inputQ = new LinkedList<String>(); 
		List<String> literalInput = p.getLiterals(); 
		for (String s : literalInput){
			inputQ.add(s);
		}
		return inputQ; 
	}

	public static void parseCommands(ProtectedTokenList p){
		Queue<String> commands = constructQ(p);
		ArrayList<Double> activeTurtleIds = new ArrayList<Double>(); 
		ArrayList<String> applyToActives = new ArrayList<String>(); 
		Map<Double, ProtectedTokenList> turtlesToCommands = new HashMap<Double, ProtectedTokenList>();
		
		while (!commands.isEmpty()){
			String input = commands.poll(); 
			
			if (input.equals("ask")){
				Ask a =  handleAsk(commands);
			
				//System.out.println("ask turtles" + a.getTurtles());
				
				ArrayList<Double> turtleIds = getTurtleIds(a.getTurtles());				
				List<String> commandsPerTurtle = a.getCommands();
				TokenList TL = createTokenList(commandsPerTurtle); // creates a token list of the commands to be applied to the specified turtles 
				//System.out.println("TL logo IS" + TL.getLogo());
				for (Double id: turtleIds){
					if (!turtlesToCommands.containsKey(id)){
						turtlesToCommands.put(id, new ProtectedTokenList(TL));
						//currentModel.makeNewTurtle(id).getState().addObserver(myView.addTurtle(id));;
						
					}
					else{
						turtlesToCommands.get(id).add(TL);
					}
				}
				
				//System.out.println("ask commands" + a.getCommands());
				//System.out.println("ask handled map" + turtlesToCommands);
			}
			
			else if (input.equals("tell")){
				turtlesToCommands = applyActiveCommands(activeTurtleIds, turtlesToCommands, applyToActives);
				activeTurtleIds.clear();
				applyToActives.clear();
				ArrayList<String> turtlesStrings = handleTell(commands);
				activeTurtleIds = getTurtleIds(turtlesStrings); 
				System.out.println("tell turtles" + turtlesStrings);
				System.out.println(activeTurtleIds);
				// want to apply active commands to them AND THEN CLEAR THE LIST
				
			}
			
			else{

				applyToActives.add(input);
				
				
			}

			
		}
		

		//applyToActives.clear();
		
		for (Double turtle1: turtlesToCommands.keySet()){
			ProtectedTokenList PTL1 = turtlesToCommands.get(turtle1);
			System.out.println("Turtle is " + turtle1);
			System.out.println("Literals are" + PTL1.getLiterals());
			System.out.println("Logo is" + PTL1.getLogo());
			//System.out.println((turtle1.toString() + " literals are " + turtlesToCommands.get(turtle1).getLiterals()));
			//System.out.println((turtle1.toString() + " logo commands are " + turtlesToCommands.get(turtle1).getLogo()));
		}
		//System.out.print("command processed map" + turtlesToCommands); 
	}





	private static Map<Double, ProtectedTokenList> applyActiveCommands(ArrayList<Double> activeTurtleIds,
			Map<Double, ProtectedTokenList> turtlesToCommands, ArrayList<String> applyToActives) {
		
		Map<Double, ProtectedTokenList> turtls = turtlesToCommands;

		for (Double turtle: activeTurtleIds){
			TokenList actives = createTokenList(applyToActives);
			System.out.println(turtle+ "Actives are" + actives.getLiterals());
			if (!turtls.containsKey(turtle)){
				ProtectedTokenList PTL = new ProtectedTokenList(actives);
				turtls.put(turtle, PTL);
				//currentModel.makeNewTurtle(turtle).getState().addObserver(myView.addTurtle(turtle));;

			}
			else{
				turtls.get(turtle).add(actives);
			}
		}
		System.out.println("active commands" + applyToActives);
		return turtls; 
	}

	private static TokenList createTokenList(List<String> commandsPerTurtle) {
		// commands per turtle = literal 
		List<String> logoPerTurtle = new ArrayList<String>(); 
		for (String literal: commandsPerTurtle){
			Tokenizer t = new Tokenizer(literal, "resources.languages/English");
			TokenIdentifier tID = t.getToken(); 
			String finalT = tID.getType();
			logoPerTurtle.add(finalT);
		}
		
		return new TokenList(commandsPerTurtle, logoPerTurtle);
	}

	private static ArrayList<Double> getTurtleIds(List<String> turtles) throws SLogoException{
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

	private static ArrayList<String> handleTell(Queue<String> commands) {
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

	private static Ask handleAsk(Queue<String> commands) {
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



	public static void main (String[] args){
		NewParser p = new NewParser("resources/languages/English"); 
		String testCode = "ask [ 100 20 40 90 ] [ fd 50 rt 90 ] tell [ 1 20 ] fd 50 rt 40";
		String test1 = " tell [ 1 2 ] fd 30 ask [ 1 ] [ rt 30 ] tell [ 2 ] bk 20 ";
		//parseCommands(p.parse(testCode)); 
		System.out.println();
		parseCommands(p.parse(test1));
	}

}
