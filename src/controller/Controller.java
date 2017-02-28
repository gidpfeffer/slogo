package controller;

import gui.UIMain;
import model.*;

import model.turtle.Turtle;
import parser.main.Parser;

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
	}


	private ModelController myModel; 
	private Turtle myTurtle;
	private Parser myParser;
	private UIMain myViewController;
	private String output; 


	public Controller(){
		myModel = new ModelController(new myHandler()); 
		myTurtle = myModel.getTurtle();
		myViewController = new UIMain(new myHandler());
		myTurtle.getState().addObserver(myViewController);
		myParser = new Parser(myTurtle.getState());

	}


	public void processInput(String input){
		// get Map from parser 
		myParser.parse(input);
			
		// if error isnt thrown update on the queue, else discard the queue
		myModel.update(myParser.getTreeQueue());
		output = myModel.getStringOutput();
	}

	public String getStringOutput(){
		return output; 
	}

	private void reset(){
		myModel.reset(); 
	}


	public UIMain getViewController() {
		return myViewController;
	}

}
