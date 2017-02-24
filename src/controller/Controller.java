package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.Queue;

import model.ModelController;
import model.State;
import model.Turtle;
import model.TurtleCommand;

public class Controller {
	ModelController myModel; 
	Map<Turtle, State> turtleStates; 
	/// set turtles' observers 
	public Controller(){
		myModel = new ModelController(); 
		turtleStates = myModel.getTurtleMap();
		setTurtleRelationship(); 
	}
	private void setTurtleRelationship() {
		for (Turtle t : turtleStates.keySet()){
			t.addObserver((Observer) turtleStates.get(t)); // how to handle turtles you add??
		}
	}
	// handle input 
	private void processCommands(Map<Integer, Queue<TurtleCommand>> commandMap){
		for (Integer key: commandMap.keySet()){
			myModel.update(commandMap.get(key));
			turtleStates = myModel.getTurtleMap();
			// GUI.update(turtle, turtle.getState) 
		}
	}
	
}

