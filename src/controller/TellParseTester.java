package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import parser.main.NewParser;
import parser.tokenizer.ProtectedTokenList;

public class TellParseTester  {


	private static Map<Double, List<String>> parseList(ProtectedTokenList list) {



		ArrayList<Double> activeTurtleIndexList = new ArrayList<Double>();
		activeTurtleIndexList.add(1.0);

		// configure the map for all currently active turtles 
		Map<Double, List<String>> turtleMap =configMap(); // map of turtle to logo commands to be applied
		List<String> literals= list.getLiterals();
		ArrayList<String> modifiableLiterals = new ArrayList<String>(literals);
		List<String> logo = list.getLogo();

		// up until a Tell 
		for(int i=0; i<modifiableLiterals.size();i++){

			if (logo.get(i).equals("Ask")){
				List<String> toParse = modifiableLiterals.subList(i+1, logo.size()); // logo.size()?a tell right after an ask
				// bracket is i+1
				List<String> ids = parseIds(toParse);
				System.out.println("ids are" + ids);
				modifiableLiterals.removeAll(ids);
				i+= ids.size(); 
				List<String> commands = parseIds(modifiableLiterals.subList(i+ids.size()+3, logo.size())); // this is the current problem 
				modifiableLiterals.removeAll(commands);
				i+= commands.size(); 
				System.out.println("commands are" + commands);


				// give all the turtles the specified commands
				for (String id: ids){
					Double turtleId = Double.parseDouble(id);
					if (!turtleMap.containsKey(turtleId)){
						turtleMap.put(turtleId, commands);
					}
					else{
						turtleMap.get(turtleId).addAll(commands);
					}
				}

				// increment loop variable

			}

			
			// PARSING IS GOOD - MAKE THIS INTO A METHOD THAT GETS AN ARRAY OF TURTLES TO ACTIVATE 
			// DOES IT NEED TO GET THE ARRAY
			if (logo.get(i).equals("Tell")){
				// get the IDS of the turtles + cast to doubles 
				// find first bracket, use helper function to find second bracket 
				List<String> ids = parseIds(literals.subList(i+1, logo.size()));
				List<Double> usableIds = new ArrayList<Double>(); 
				for (String id: ids){
					usableIds.add(Double.parseDouble(id));
				}

				// iterate through the ids and set the active turtles
				activeTurtleIndexList.clear();

				for (Double turtleID: usableIds){
					if (!activeTurtleIndexList.contains(turtleID)){
						activeTurtleIndexList.add(new Double(turtleID));
						//myModel.makeNewTurtle(turtleID).getState().addObserver(myViewController.addTurtle(turtleID));
					}

				}
				System.out.println(activeTurtleIndexList);
				i+= 1+logo.size();

			}

			for (Double activeID: activeTurtleIndexList){
				if(!turtleMap.containsKey(activeID)){
					turtleMap.put(activeID, new ArrayList<String>());

				}
				//System.out.println(literals.get(i));

			}


		}

		// TODO Auto-generated method stub
		return turtleMap;

	}




	private static List<String> parseIds(List<String> toParse) {
		List<String> ids = new ArrayList<String>(); 
		int subListStart = 0; 
		int subListEnd = 0; 
		boolean iterated = true; 
		for (int i=0; i<toParse.size(); i++){
			if (toParse.get(i).equals("[") && iterated){
				subListStart = i; 
				subListEnd = findEnd(i, toParse);
				System.out.print(subListStart);
				System.out.println(subListEnd);
				iterated=false; 
				break;
			}
		}
		
		ids.addAll(toParse.subList(subListStart+1, subListEnd));
		System.out.println("new ids" + ids);
		return ids; 
	}


	private static int findEnd(int i, List<String> toParse) throws SLogoException{
		for (int j=i; j<toParse.size(); j++){
			try{
				if (toParse.get(j).equals("]")){
					return j; 
				}
			}
			catch(SLogoException e){
				throw new SLogoException("Invalid bracket syntax");
			}

		}
		return i;
	}




	private static Map<Double, List<String>> configMap() {
		ArrayList<Double> activeTurtleIndexList = new ArrayList<Double>();
		HashMap<Double,List<String> > turtlesToCommands = new HashMap<Double, List<String>>();
		for (Double t: activeTurtleIndexList){
			if (!turtlesToCommands.containsKey(t)){
				turtlesToCommands.put(t, new ArrayList<String>());
			}
		}
		return turtlesToCommands; 
	}

	public static void main(String[] args){
		NewParser p = new NewParser("resources/languages/English"); 
		String testCode = "ask [ 100 20 40 90 ] [ fd 50 rt 90 ] tell [ 1 20 ] fd 50 rt 40";
		String test1 = " tell [ 1 2 ] fd 30 ask [ 1 ] [ rt 30 ] tell [ 2 ] bk 20 ";
		//System.out.println(p.parse(testCode).getLiterals());
		System.out.println(parseList(p.parse(testCode))); 
	}




}
