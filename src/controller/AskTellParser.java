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
import parser.tokenizer.TokenIdentifier;
import parser.tokenizer.TokenList;
import parser.tokenizer.Tokenizer;

public class AskTellParser {
	
	private HashMap<Double, ProtectedTokenList> turtlesToCommands;
	private ModelController currentModel; 
	private UIMain myView; 
	
	public AskTellParser(ModelController myModel, UIMain myViewController){
		turtlesToCommands = new HashMap<Double, ProtectedTokenList>(); 
		currentModel = myModel;
		myView = myViewController; 
	}
	
	public Map<Double, ProtectedTokenList> getParsedCommands(){
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
		ArrayList<Double> activeTurtleIds = new ArrayList<Double>(); 
		ArrayList<String> applyToActives = new ArrayList<String>(); 
		
		while (!commands.isEmpty()){
			String input = commands.poll(); 
			
			if (input.equals("ask")){
				Ask a =  handleAsk(commands);
			
				//System.out.println("ask turtles" + a.getTurtles());
				
				ArrayList<Double> turtleIds = getTurtleIds(a.getTurtles());				
				List<String> commandsPerTurtle = a.getCommands();
				TokenList TL = createTokenList(commandsPerTurtle);
				// would also get the logo of that here 
				// make a new protected token list out of the two 
				
				for (Double id: turtleIds){
					if (!turtlesToCommands.containsKey(id)){
						turtlesToCommands.put(id, new ProtectedTokenList(TL));
						currentModel.makeNewTurtle(id).getState().addObserver(myView.addTurtle(id));;
						
					}
					else{
						
						turtlesToCommands.get(id).add(TL);
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
					TokenList actives = createTokenList(applyToActives);

					if (!turtlesToCommands.containsKey(turtle)){
						turtlesToCommands.put(turtle, new ProtectedTokenList(actives));
						currentModel.makeNewTurtle(turtle).getState().addObserver(myView.addTurtle(turtle));;

					}
					else{
						turtlesToCommands.get(turtle).add(actives);
					}
				}
				
			}
		}
		System.out.println("active commands" + applyToActives);
		
	
		System.out.print("command processed map" + turtlesToCommands); 
	}



	

	private TokenList createTokenList(List<String> commandsPerTurtle) {
		// commands per turtle = literal 
		List<String> logoPerTurtle = new ArrayList<String>(); 
		for (String literal: commandsPerTurtle){
			Tokenizer t = new Tokenizer(literal, "English");
			TokenIdentifier tID = t.getToken(); 
			String finalT = tID.getToken();
			logoPerTurtle.add(finalT);
		}
		return new TokenList(commandsPerTurtle, logoPerTurtle);
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
