package controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gui.UIMain;
import gui.UITurtle;
import model.*;

import model.turtle.Turtle;
import parser.main.Parser;

public class Controller {
	

	
	class myHandler implements ControlHandler{
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
	private TurtleObserver myObserver;
	private UIMain myViewController;
	private String output; 
	

	public Controller(){
		myModel = new ModelController(); 
		myTurtle = myModel.getTurtle();
		List<UITurtle> list = new ArrayList<UITurtle>();
		list.add(new UITurtle(myTurtle.getState()));
		myViewController = new UIMain(new myHandler(), list);
		myTurtle.getState().addObserver(myViewController);

		//TurtleState turtleState = new TurtleState(myTurtle.getState()); //pass a copy of turtle state into the parser for safety
		
		myObserver = new TurtleObserver();
		myTurtle.getState().addObserver(myObserver);
		myParser = new Parser(myTurtle.getState());
		
	}
	

	public void processInput(String input){
		// get Map from parser 
		myParser.parse(input);
		
		// if error isnt thrown update on the queue, else discard the queue
		myModel.update(myParser.getTreeQueue());
		output = myModel.getStringOutput();
		// call process commands
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
