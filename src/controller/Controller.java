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
		processCommands(myParser.getOrderedTreeList());
		// call process commands
	}
	private void processCommands(List<TreeNode> commands){
		myModel.update(commands);
		String output = myModel.getStringOutput(); 
		// hand to GUI		
	
		
	}
	private void reset(){
		myModel.reset(); 
	}

}
