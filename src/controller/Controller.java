package controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import gui.UIMain;
import gui.tableviews.UIVariablesView;
import model.*;
import model.command.TreeNode; 

import model.turtle.Turtle;
import model.turtle.TurtleState;
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
	//private List<Double> currentTurtleIds; 

	private NewParser myParser;
	private Compiler compiler;

	private UIMain myViewController;
	private String output; 
	private final String languageLocation = "resources.languages/";
	private final Double DEFAULT_TURTLE_ID = 1.0; 
	private StringBuilder currentLang; 


	public Controller(){
		myModel = new ModelController(new modelHandler()); 
		myTurtles = myModel.getTurtles();
		activeTurtleIndexList = new ArrayList<Double>();
		activeTurtleIndexList.add(DEFAULT_TURTLE_ID);
		
		//currentTurtleIds = new ArrayList<Double>(); 
		//currentTurtleIds.add(DEFAULT_TURTLE_ID);
		
		activeTurtleIndexList.add(DEFAULT_TURTLE_ID);
		myViewController = new UIMain(new myHandler(), "English"); // handler currently Front to Back

		// set the observable/observer relationship for the first turtle - we can make this into a method. 
		myTurtles.get(0).getState().addObserver(myViewController.addTurtle(myTurtles.get(0).getState())); 

		changeLanguage("English");
		myParser = new NewParser(currentLang.toString());
		compiler = new Compiler(); 
		configureVariableStorage(); 

	}


	private void configureVariableStorage() {
		VariableStorage vars = compiler.getVars(); 
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
			Map<Double, ProtectedTokenList> turtlesToCommands = parseList(list);
			Compiler c = new Compiler(); 

			// refactor into a method 
			
			for (Double turtleId: turtlesToCommands.keySet()){
				ProtectedTokenList commandsToApply = turtlesToCommands.get(turtleId);
				List<Turtle> currentTurtles = myModel.getTurtles(); 
				
				TurtleState t = findTurtle(turtleId, currentTurtles);
				Queue<TreeNode> Q = c.compile(t, commandsToApply); 
				
			
				
				TurtleState t = findTurtle(turtleId);
				Queue<TreeNode> Q = c.compile(t, commandsToApply); 

				myModel.update(Q);
				output = myModel.getStringOutput();
				myViewController.addNewOutput(output);
				
			}


		}
		catch (SLogoException e){ 
			myViewController.displayErrorWithMessage(e.getMessage());
		}
		output = myModel.getStringOutput();
	}

	private TurtleState findTurtle (Double turtleId, List<Turtle> currentTurtles) {
		try{
		for (Turtle t: currentTurtles){
			if (t.getID() == turtleId){
				return t.getState();
			}
		}
		}
		catch(SLogoException e){
			throw new SLogoException("turtle does not exist");
		}
		return null; 
	}


	private Map<Double, ProtectedTokenList> parseList(ProtectedTokenList list) {
		AskTellParser ap = new AskTellParser(myModel, myViewController); 
		ap.parseCommands(list);		
		return ap.getParsedCommands(); 
	}



	public String getStringOutput(){
		return output; 
	}

	private void reset(){
 		myViewController.clearScreen();
		myModel.reset(); 
	}


	public UIMain getViewController() {
		return myViewController;
	}

}