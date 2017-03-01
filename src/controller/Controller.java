package controller;

import gui.UIMain;
import gui.tableviews.UIVariablesView;
import model.*;

import model.turtle.Turtle;
import parser.main.Parser;
import parser.storage.*;
import parser.storage.VariableStorage;

public class Controller {


	public class myHandler implements ControlHandler{
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


	private ModelController myModel; 
	private Turtle myTurtle;
	private Parser myParser;
	private UIMain myViewController;
	private String output; 
	private final String languageLocation = "resources.languages/";
	private StringBuilder currentLang; 

	public Controller(){
		myModel = new ModelController(new myHandler()); 
		myTurtle = myModel.getTurtle();
		myHandler handler = new myHandler(); 
		myViewController = new UIMain(handler);
		myTurtle.getState().addObserver(myViewController);
//		myParser = new Parser(myTurtle.getReadOnlyState());
		changeLanguage("English");
		myParser = new Parser(myTurtle.getReadOnlyState(), currentLang.toString());  // safe way to hand turtle state
		configureVariableStorage(); 

	}


	private void configureVariableStorage() {
		VariableStorage vars = myParser.getVars(); 
		UIVariablesView variableBox = myViewController.getVariableView();
		vars.addObserver(variableBox);
		
	}


	public void changeLanguage(String language) {
		currentLang = new StringBuilder(); 
		currentLang.append(languageLocation);
		currentLang.append(language);
	}


	public void processInput(String input){
		try{
			myParser.parse(input);
			myModel.update(myParser.getTreeQueue());
			output = myModel.getStringOutput();
			myViewController.addNewOutput(output);
			System.out.println("String to print " + output);

		}
		catch (Exception e){ // change this 
			myViewController.displayErrorWithMessage(e.getMessage());
		}

		//		myParser.parse(input);
		//		myModel.update(myParser.getTreeQueue());
		//		output = myModel.getStringOutput();
	}

	public String getStringOutput(){
		return output; 
	}

	private void reset(){
		myTurtle.reset();
		myViewController.clearScreen();  
	}


	public UIMain getViewController() {
		return myViewController;
	}

}
