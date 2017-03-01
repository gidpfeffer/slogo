package controller;

import gui.UIMain;
import model.*;

import model.turtle.Turtle;
import parser.main.Parser;

public class Controller {


	public class myHandler implements ControlHandler{
	
		String lang; 
		private final String languageExtension = ".properties";
		public myHandler(){
			lang = "English";
		}
		@Override
		public void handleTextInput(String input){
			processInput(input);
		}
		@Override
		public void handleReset(){
			reset();
		}
		
		public void setLanguage(String language){
			lang = language; 
		}
		
		public String getLanguage(){
			return lang + languageExtension; 
		}
	}


	private ModelController myModel; 
	private Turtle myTurtle;
	private Parser myParser;
	private UIMain myViewController;
	private String output; 
	private myHandler handler; 
	private String language; 
	private final String languageLocation = "resources.languages/";

	public Controller(){
		myModel = new ModelController(new myHandler()); 
		myTurtle = myModel.getTurtle();
		handler = new myHandler(); 
		myViewController = new UIMain(handler);
		myTurtle.getState().addObserver(myViewController);
		language = languageLocation + handler.getLanguage();
		myParser = new Parser(myTurtle.getReadOnlyState());  // safe way to hand turtle state
		//myParser = new Parser(myTurtle.getState(), language);
	}

	

	public void processInput(String input){
		
		
		//Queue<TreeNode> commandsQueue = new LinkedList();
		try{
			myParser.parse(input);
			//commandsQueue = myParser.getTreeQueue();
			myModel.update(myParser.getTreeQueue());
			output = myModel.getStringOutput();
			myViewController.addNewOutput(output);
			System.out.println("String to print " + output);
			
		}
		catch (Exception e){
			myViewController.displayErrorWithMessage(e.getMessage());
			//commandsQueue.clear();
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
