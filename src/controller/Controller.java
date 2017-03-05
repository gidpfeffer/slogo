package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gui.UIMain;
import gui.tableviews.UIVariablesView;
import model.*;

import model.turtle.Turtle;
import parser.main.NewParser;
import parser.main.Parser;
import parser.storage.*;
import parser.storage.VariableStorage;
import parser.tokenizer.ProtectedTokenList;
import parser.main.Compiler;

public class Controller {


	public class myHandler implements ControlHandler{ // front end to controller 
		@Override
		public void handleTextInput(String input){
			processInput(input);
		}
		@Override
		public void handleReset(){
			reset();
		}

		public void setLanguage(String language){

			changeLanguage(language);
		}

	}
	
	public class modelHandler implements BackEndHandler{ // controller to model 

		@Override
		public void setBackground(double index) {
			changeBackground(index); 
			
		}

		@Override
		public void setPalette(double index, double r, double g, double b) {
			changePalette(index, r,g,b);	
		}
		
		public void handleReset(){
			reset(); 
		}
		
	}


	private ModelController myModel; 
	private List<Turtle> myTurtles;
	private List<Double> activeTurtleIndexList;
	
	private NewParser myParser;
	private Compiler compiler;
	
	private UIMain myViewController;
	private String output; 
	private final String languageLocation = "resources.languages/";
	private StringBuilder currentLang; 


	public Controller(){
		myModel = new ModelController(new modelHandler()); 
		myTurtles = myModel.getTurtles();
		activeTurtleIndexList = new ArrayList<Double>();
		activeTurtleIndexList.add(new Double(1));
		myViewController = new UIMain(new myHandler()); // handler currently Front to Back
		//myTurtle.getState().addObserver(myViewController);
		
		
		// set the observable/observer relationship for the first turtle - we can make this into a method. 
		myTurtles.get(0).getState().addObserver(myViewController.addTurtle(myTurtles.get(0).getState().getID())); 
		
		
		changeLanguage("English");
		//myParser = new Parser(myTurtle.getReadOnlyState(), currentLang.toString());  // safe way to hand turtle state
		myParser = new NewParser(currentLang.toString());
		compiler = new Compiler(); 
		configureVariableStorage(); 

	}


	private void configureVariableStorage() {
		VariableStorage vars = myParser.getVars(); 
		UIVariablesView variableBox = myViewController.getVariableView();
		vars.addObserver(variableBox);
		
	}

	public void changeBackground(double index){
		myViewController.setBackgroundColor(index);
	}
	

	public void changePalette(double index, double r, double g, double b){
		myViewController.setPalleteAtIndex(index,r,g,b);
	}

	public void changeLanguage(String language) {
		currentLang = new StringBuilder(); 
		currentLang.append(languageLocation);
		currentLang.append(language);
	}


	public void processInput(String input){
		try{
			ProtectedTokenList list = myParser.parse(input);
			Map<Double, List<String>> turtlesToCommands = parseList(list);
			/*myModel.update(myParser.getTreeQueue());
			output = myModel.getStringOutput();
			myViewController.addNewOutput(output);
			System.out.println("String to print " + output);
*/
		}
		catch (SLogoException e){ 
			myViewController.displayErrorWithMessage(e.getMessage());
		}

		//		myParser.parse(input);
		//		myModel.update(myParser.getTreeQueue());
		//		output = myModel.getStringOutput();
	}

	private Map<Double, List<String>> parseList(ProtectedTokenList list) {
		//List<Turtle> activeTurtleIndexList = myModel.getTurtles();
		// configure the map for all currently active turtles 
		Map<Double, List<String>> turtleMap =configMap(activeTurtleIndexList); // map of turtle to logo commands to be applied
		List<String> literals= list.getLiterals();
		List<String> logo = list.getLogo();
		// up until a Tell 
		for(int i=0; i<list.getLogo().size	();i++){
			if (logo.get(i).equals("Ask")){
				List<String> toParse = literals.subList(i+1, logo.size()); // logo.size()?a tell right after an ask
				List<String> ids = parseIds(toParse);
				//System.out.println(ids);
				List<String> commands = parseIds(literals.subList(i+ids.size()+3, logo.size()));
				//System.out.println(commands);
				i += commands.size()+ids.size();
			}
			else if (logo.get(i).equals("Tell")){
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
						myModel.makeNewTurtle(turtleID).getState().addObserver(myViewController.addTurtle(turtleID));
						
					}
					
				}
				
			}
			else{
				// add commands to the active turtles' command lists 
			}
		}
		// TODO Auto-generated method stub
		return null;
	}


	private List<String> parseIds(List<String> toParse) {
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


	private Map<Double, List<String>> configMap(List<Turtle> activeTurtles) {
		HashMap<Double,List<String> > turtlesToCommands = new HashMap<Double, List<String>>();
		for (Turtle t: activeTurtles){
			if (!turtlesToCommands.containsKey(t.getReadOnlyState().getID())){
				turtlesToCommands.put(t.getReadOnlyState().getID(), new ArrayList<String>());
			}
		}
		return turtlesToCommands; 
	}


	public String getStringOutput(){
		return output; 
	}

	private void reset(){
		myTurtle.reset();
		myViewController.clearScreen();
		myModel.reset(); 
	}


	public UIMain getViewController() {
		return myViewController;
	}

}
