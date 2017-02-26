package controller;
import java.util.*;
import model.*;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import parser.main.Parser;

public class Controller {
	private ModelController myModel; 
	private Turtle myTurtle;
	private Parser myParser;
	private TurtleObserver myObserver;
	

	public Controller(){
		myModel = new ModelController(); 
		myTurtle = myModel.getTurtle();
		myObserver = new TurtleObserver();
		myTurtle.getState().addObserver(myObserver);
		myParser = new Parser("fd 50");
	}
	
	
	// handle input 
	private void processCommands(Map<Integer, Queue<TurtleCommand>> commandMap){
		for (Integer key: commandMap.keySet()){
			//myModel.update(commandMap.get(key));
			//turtleStates = myModel.getTurtleMap();
			// GUI.update(turtle, turtle.getState) 
		}
	}
	
}
