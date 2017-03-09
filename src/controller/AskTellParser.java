package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import controller.AskTellData;
import gui.UIMain;
import model.ModelController;
import model.turtle.Turtle;
import parser.main.NewParser;
import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenList;

public class AskTellParser {

	private HashMap<Double, ProtectedTokenList> commandMap;
	private Map<Double, List<String>> literalMap;
	private Queue<String> commandQ; 
	private List<Double> activeTurtles; 

	private ModelController currentModel; 
	private UIMain myView; 

	public AskTellParser(ModelController myModel, UIMain myViewController){
		currentModel = myModel;
		myView = myViewController; 

		commandQ = new LinkedList<String>(); 
		commandMap = new HashMap<Double, ProtectedTokenList>(); 
		literalMap = new HashMap<Double, List<String>>(); 

		activeTurtles = new ArrayList<Double>(); 
		activeTurtles.add(1.0);
	}

	public Map<Double, ProtectedTokenList> getParsedCommands(){
		return commandMap; 
	}


	private void constructQ (ProtectedTokenList p){
		List<String> literalInput = p.getLiterals(); 
		for (String s : literalInput){
			commandQ.add(s);
		}
	}

	public void parseCommands(ProtectedTokenList p){ 
		constructQ(p);

		while (!commandQ.isEmpty()){

			String input = commandQ.poll(); 

			if (input.equals("ask")){
				AskTellData askData =  handleAsk();				
				buildLiteralMap(askData);
			}


			if (input.equals("tell")){
				AskTellData tellData = handleTell();
				buildLiteralMap(tellData);
				activeTurtles.clear();
				activeTurtles.addAll(tellData.getTurtleIDS());
			}

			else{
				// need to associate commands w the active Turtles
				for (Double i : activeTurtles){
					if (!literalMap.containsKey(i)){
						List<String> val = new ArrayList<String>(); 
						val.add(input);
						literalMap.put(i, val);
					}
					else{
						literalMap.get(i).add(input);
					}
				}
			}

		}

		buildCommandMap();		

	}



	private void buildLiteralMap(AskTellData data) {
		for (Double t : data.getTurtleIDS()){
			if (!(literalMap.containsKey(t))){
				literalMap.put(t, new ArrayList<String>());
				Turtle turtle = currentModel.makeNewTurtle(t);
				turtle.getState().addObserver(myView.addTurtle(turtle.getState()));
			}
			literalMap.get(t).addAll(data.getCommands());
		}
	}

	private void buildCommandMap() {
		for (Double kk : literalMap.keySet()){
			if (!commandMap.containsKey(kk)){
				ProtectedTokenList t = createTokenList(literalMap.get(kk));
				commandMap.put(kk, t);
			}
			else{
				ProtectedTokenList ptl = createTokenList(literalMap.get(kk));
				commandMap.get(kk).add(new TokenList(ptl.getLiterals(), ptl.getLogo()));
			}
		}
	}


	private ProtectedTokenList createTokenList(List<String> commandsPerTurtle) {
		String bigString = " ";
		for (String literal: commandsPerTurtle){
			bigString = bigString + literal + " ";
		}

		NewParser p = new NewParser("resources.languages/English");
		return p.parse(bigString);
	}


	private AskTellData handleTell() {
		ArrayList<String> turtles = new ArrayList<String>(); 
		ArrayList<String> commandsToApply = new ArrayList<String>();

		int bracketCount =0; 
		while (bracketCount<2 && !(commandQ.isEmpty())){
			String st = commandQ.poll();
			if ((st.equals("[")) || (st.equals("]"))){
				bracketCount++; 
			}
			else{
				turtles.add(st);
			}
		}

		while (!commandQ.isEmpty()){
			String ch = commandQ.peek();
			if (ch.equals("tell") || ch.equals("ask")){
				break; 
			}
			else{
				commandsToApply.add(commandQ.poll());
			}	
		}
		return new AskTellData(turtles, commandsToApply); 

	}

	private AskTellData handleAsk() {
		ArrayList<String> t = new ArrayList<String>(); 
		ArrayList<String> askCommands = new ArrayList<String>();
		int bracketCount = 0; 
		while (bracketCount<2 && !(commandQ.isEmpty())){
			String st = commandQ.poll(); 
			if ((st.equals("[")) || (st.equals("]"))){
				bracketCount++; 
			}
			else{
				t.add(st);
			}
		}
		if (bracketCount==2){
			while (bracketCount<4 && !(commandQ.isEmpty())){
				String st1 = commandQ.poll(); 
				if(st1.equals("tell")){
					break;
				}
				if ((st1.equals("[")) || (st1.equals("]"))){
					bracketCount++; 
				}
				else{
					askCommands.add(st1);
				}
			}
		}
		AskTellData newAsk = new AskTellData(t, askCommands);
		return newAsk; 
	}

	public void clearParser(){
		commandQ = new LinkedList<String>(); 
		commandMap = new HashMap<Double, ProtectedTokenList>(); 
		literalMap = new HashMap<Double, List<String>>(); 
	}


}
