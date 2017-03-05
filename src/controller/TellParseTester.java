package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ModelController;
import model.turtle.Turtle;
import parser.main.NewParser;
import parser.tokenizer.ProtectedTokenList;

public class TellParseTester {

	private static Map<Double, List<String>> parseList(ProtectedTokenList list) {

		ArrayList<Double> activeTurtleIndexList = new ArrayList<Double>();
		activeTurtleIndexList.add(1.0);

		// configure the map for all currently active turtles 
		Map<Double, List<String>> turtleMap =configMap(); // map of turtle to logo commands to be applied
		List<String> literals= list.getLiterals();
		List<String> logo = list.getLogo();

		// up until a Tell 
		for(int i=0; i<list.getLogo().size	();i++){

			if (logo.get(i).equals("Ask")){
				List<String> toParse = literals.subList(i+1, logo.size()); // logo.size()?a tell right after an ask
				List<String> ids = parseIds(toParse);
				List<String> commands = parseIds(literals.subList(i+ids.size()+3, logo.size()));


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
				i += commands.size()+ids.size();

			}

			if (logo.get(i).equals("Tell")){
				// get the IDS of the turtles + cast to doubles 
				List<String> ids = parseIds(literals.subList(i+1, logo.size()));
				List<Double> usableIds = new ArrayList<Double>(); 
				for (String id: ids){
					usableIds.add(Double.parseDouble(id));
				}
				i += ids.size();

				// iterate through the ids and set the active turtles
				activeTurtleIndexList.clear();

				for (Double turtleID: usableIds){
					if (!activeTurtleIndexList.contains(turtleID)){
						//activeTurtles.add(new Turtle(turtleID));
						activeTurtleIndexList.add(new Double(turtleID));
						//myModel.makeNewTurtle(turtleID).getState().addObserver(myViewController.addTurtle(turtleID));
					}


				}


			}


			for (Double activeID: activeTurtleIndexList){
				if(!turtleMap.containsKey(activeID)){
					turtleMap.put(activeID, new ArrayList<String>());

				}
				//System.out.println(literals.get(i));
				turtleMap.get(activeID).add(literals.get(i));

			}


		}

		// TODO Auto-generated method stub
		return turtleMap;

	}




	private static List<String> parseIds(List<String> toParse) {
		List<String> ids = new ArrayList<String>(); 
		int turtleStart = 0; 
		int turtleEnd = 0; 
		for (int i=0; i<toParse.size(); i++){
			if (toParse.get(i).equals("[")){
				turtleStart= i+1; 
			}
			if (toParse.get(i).equals("]")){
				turtleEnd = i; 
				break; 
			}
		}
		ids = toParse.subList(turtleStart, turtleEnd);
		return ids; 
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
		String testCode = "tell [ 100 20 40 90 ] [ fd 50 rt 90 ] fd 50 rt 40";
		String test1 = " tell [ 1 2 ] fd 30 ask [ 1 ] [ rt 30 ] tell [ 2 ] bk 20 ";
		System.out.println(p.parse(testCode).getLiterals());
		System.out.println(parseList(p.parse(test1))); 
	}
}
