package controller;
import java.util.*;
import model.*;
import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import model.turtle.TurtleState;
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
	private String output; 
	

	public Controller(){
		myModel = new ModelController(); 
		myTurtle = myModel.getTurtle();

		//TurtleState turtleState = new TurtleState(myTurtle.getState()); //pass a copy of turtle state into the parser for safety
		
		myObserver = new TurtleObserver();
		myTurtle.getState().addObserver(myObserver);
		myParser = new Parser(myTurtle.getState());
		
	}
	

	public void processInput(String input){
		// get Map from parser 
		myParser.parse(input);
		myModel.update(myParser.getOrderedTreeList());
		output = myModel.getStringOutput();
		// call process commands
	}
	
	public String getStringOutput(){
		return output; 
	}

	private void reset(){
		myModel.reset(); 
	}

}
