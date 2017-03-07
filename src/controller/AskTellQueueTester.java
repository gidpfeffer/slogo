package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import controller.AskTellData; 

import parser.main.NewParser;
import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenIdentifier;
import parser.tokenizer.TokenList;
import parser.tokenizer.Tokenizer;

public class AskTellQueueTester {
	static Queue<String> commands; 
	static Map<Double, ProtectedTokenList> commandMap; 
	public AskTellQueueTester(){
		commands = new LinkedList<String>(); 
		commandMap = new HashMap<Double, ProtectedTokenList>(); 

	}
	
	private static void constructQ (ProtectedTokenList p){
		List<String> literalInput = p.getLiterals(); 
		for (String s : literalInput){
			commands.add(s);
		}
	}
	
	

	public static void parseCommands(ProtectedTokenList p){
		
		constructQ(p);
		
		
		Map<Double, List<String>> firstTry = new HashMap<Double, List<String>>(); 
		Map<Double, TokenList> secondTry = new HashMap<Double, TokenList>(); 
		
		while (!commands.isEmpty()){
			
			String input = commands.poll(); 
			
			if (input.equals("ask")){
				AskTellData askData =  handleAsk();
				List<Double> ids = askData.getTurtleIDS();

				List<String> commandsToAdd = askData.getCommands();
				TokenList askTokens = createTokenList(commandsToAdd);
				
				for (Double id : askData.getTurtleIDS()){
					if (!(firstTry.containsKey(id))){
						firstTry.put(id, new ArrayList<String>());
					}
					firstTry.get(id).addAll(askData.getCommands());
				}
				
				//System.out.println("ask turtles " + ids + "applicable commands " + askTokens.getLiterals());
				System.out.println("after ask " + firstTry);
				
				
			}
			
			
			if (input.equals("tell")){
				AskTellData tellData = handleTell();
				List<Double> activeTurtle = tellData.getTurtleIDS();
				List<String> turtleCommands = tellData.getCommands();
				TokenList tellTokens = createTokenList(turtleCommands);	
				//System.out.println("tell turtles" + activeTurtle + " applicable commands" + tellTokens.getLiterals());
				
				for (Double t : tellData.getTurtleIDS()){
					if (!(firstTry.containsKey(t))){
						firstTry.put(t, new ArrayList<String>());
					}
					firstTry.get(t).addAll(tellData.getCommands());
				}
				System.out.println("after tell " + firstTry);

				
			}
			
		}
		
		System.out.println(firstTry);
		
		// Create Second Try: 
		
		for (Double key: firstTry.keySet()){
			if (!secondTry.containsKey(key)){
				List<String> val = firstTry.get(key); // literals 
				TokenList tl = createTokenList(val); //possible prob
				secondTry.put(key, tl);
			}
			
			else{
				List<String> val1 = firstTry.get(key);
				List<String> currents = secondTry.get(key).getLiterals();
				currents.addAll(val1);
				TokenList newTL = createTokenList(currents);
				secondTry.put(key, newTL);
			}
		}
		
		
		// print tester 
		
		for (Double key: secondTry.keySet()){
			System.out.println(" tl map turtle is " + key + " commands are " + secondTry.get(key).getLiterals());
		}
		
		
		//printCommandMap();
		
	}






	private static void printCommandMap() {
		for (Double key: commandMap.keySet()){
			ProtectedTokenList ptl = commandMap.get(key); 
			System.out.println("turtle id " + key );
			System.out.println("Literal command value " + ptl.getLiterals());
			System.out.println();
		}
		
	}

	private static void applyCommands(List<Double> activeTurtle, TokenList tellTokens) {

		for (Double t : activeTurtle){
			
			if (!commandMap.containsKey(t)){
				commandMap.put(t, new ProtectedTokenList(tellTokens));
			}
			else{
				
				commandMap.get(t).add(tellTokens);
			}
			
		}
		
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



	private static AskTellData handleTell() {
		ArrayList<String> turtles = new ArrayList<String>(); 
		ArrayList<String> commandsToApply = new ArrayList<String>();

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
		
		while (!commands.isEmpty()){
			String ch = commands.peek();
			if (ch.equals("tell") || ch.equals("ask")){
				break; 
			}
			else{
				commandsToApply.add(commands.poll());
			}	
		}
		return new AskTellData(turtles, commandsToApply); 
		
	}

	private static AskTellData handleAsk() {
		ArrayList<String> t = new ArrayList<String>(); 
		ArrayList<String> askCommands = new ArrayList<String>();
		int bracketCount = 0; 
		while (bracketCount<2){
			String st = commands.poll(); 
			
			if ((st.equals("[")) || (st.equals("]"))){
				bracketCount++; 
			}
			else{
				t.add(st);
			}
		}
		if (bracketCount==2){
			while (bracketCount<4){
				String st1 = commands.poll(); 
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
		//System.out.println("Ask data turtles" + newAsk.getTurtles() + "ask data commands " + newAsk.getCommands());
		return newAsk; 
	}



	public static void main (String[] args){
		NewParser p = new NewParser("resources/languages/English"); 
		AskTellQueueTester q = new AskTellQueueTester(); 
		String testCode = "ask [ 100 20 40 90 ] [ fd 50 rt 90 ] tell [ 1 20 ] fd 50 rt 40";
		String test1 = " tell [ 1 2 ] fd 30 ask [ 1 ] [ rt 30 ] tell [ 2 ] bk 20 ";
		q.parseCommands(p.parse(testCode)); 
		System.out.println();
		System.out.println();
		q.parseCommands(p.parse(test1));
	}

}
