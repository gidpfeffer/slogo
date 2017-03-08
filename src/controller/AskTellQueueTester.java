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
	static Map<Double, List<String>> literalCommandMap; 
	static Map<Double, ProtectedTokenList> commandMap; 
	public AskTellQueueTester(){
		commands = new LinkedList<String>(); 
		commandMap = new HashMap<Double, ProtectedTokenList>(); 
		literalCommandMap = new HashMap<Double, List<String>>(); 

	}

	private static void constructQ (ProtectedTokenList p){
		List<String> literalInput = p.getLiterals(); 
		for (String s : literalInput){
			commands.add(s);
		}
	}



	public static void parseCommands(ProtectedTokenList p){
		constructQ(p);

		while (!commands.isEmpty()){

			String input = commands.poll(); 

			if (input.equals("ask")){
				AskTellData askData =  handleAsk();				
				buildLiteralMap(askData);
			}


			if (input.equals("tell")){
				AskTellData tellData = handleTell();
				buildLiteralMap(tellData);
			}

		}
		buildCommandMap();
		printCommandMap();

	}

	private static void buildLiteralMap(AskTellData data) {
		for (Double t : data.getTurtleIDS()){
			if (!(literalCommandMap.containsKey(t))){
				literalCommandMap.put(t, new ArrayList<String>());
			}
			literalCommandMap.get(t).addAll(data.getCommands());
		}
	}

	private static void buildCommandMap() {
		for (Double kk : literalCommandMap.keySet()){
			if (!commandMap.containsKey(kk)){
				commandMap.put(kk, new ProtectedTokenList(createTokenList(literalCommandMap.get(kk))));
			}
			else{
				ProtectedTokenList ptl = commandMap.get(kk);
				ptl.add(createTokenList(literalCommandMap.get(kk)));
				commandMap.put(kk, ptl);
			}
		}
	}






	private static void printCommandMap() {
		for (Double key: commandMap.keySet()){
			ProtectedTokenList ptl = commandMap.get(key); 
			System.out.println("turtle id " + key );
			System.out.println("Literal command value " + ptl.getLiterals());
			System.out.println();
		}

	}

	private static TokenList createTokenList(List<String> commandsPerTurtle) {
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
	
	public void clearTester(){
		commands = new LinkedList<String>(); 
		commandMap = new HashMap<Double, ProtectedTokenList>(); 
		literalCommandMap = new HashMap<Double, List<String>>(); 
	}


	public static void main (String[] args){
		NewParser p = new NewParser("resources/languages/English"); 
		AskTellQueueTester q = new AskTellQueueTester(); 
		String testCode = "ask [ 100 20 40 90 ] [ fd 50 rt 90 ] tell [ 1 20 ] fd 50 rt 40";
		String test1 = " tell [ 1 2 ] fd 30 ask [ 1 ] [ rt 30 ] tell [ 2 ] bk 20 ";
		q.parseCommands(p.parse(testCode)); 
		q.clearTester();
		System.out.println();
		System.out.println();
		q.parseCommands(p.parse(test1));
	}

}
