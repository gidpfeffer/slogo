package controller;
import java.util.*;
import model.*;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import parser.main.Parser;

public class Controller {
	
	Map<Turtle, TurtleObserver> turtleStates;
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
		myObserver = new TurtleObserver();
		myTurtle.getState().addObserver(myObserver);
		myParser = new Parser();
		myParser.parse("fd 50");
	}
	

	public void processInput(String input){
		// get Map from parser 
		// call process commands
	}

	private void processCommands(Map<Integer, TreeNode> commandMap){
		myModel.update(commandMap);
		turtleStates = myModel.getTurtleMap();
		/*		
		 * for (Turtle t : turtleStates){
		 *  GUI.update(turtle, turtle.getState);
		 *  }
		 *   */
		
		
	}
	private void reset(){
		//TODO:
	}

}
