package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.Queue;

import model.ModelController;
import model.TreeNode;
import model.TurtleState;
import model.Turtle;
import model.TurtleCommand;
import model.TurtleObserver;

public class Controller {
	ModelController myModel; 
	Map<Turtle, TurtleObserver> turtleStates; 

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
}



