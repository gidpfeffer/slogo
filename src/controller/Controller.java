package controller;
import java.util.*;
import model.*;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import model.turtle.TurtleState;

public class Controller {
	private ModelController myModel; 
	private List<Turtle> myTurtleList;
	

	public Controller(){
		myModel = new ModelController(); 
		myTurtleList = myModel.getTurtles();
		setTurtleRelationship(); 
	}

	private void setTurtleRelationship() {

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
