package controller;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import model.ModelController;
import model.TreeNode;
import model.Turtle;
import model.TurtleObserver;

public class Controller {
	ModelController myModel; 
	//Map<Turtle, TurtleObserver> turtleStates;

	public Controller(){
		myModel = new ModelController(); 
		//turtleStates = myModel.getTurtleMap();
		setTurtleRelationship(); 
	}

	private void setTurtleRelationship() {
		for (Turtle t : myModel.getTurtles()){
			t.addObserver(new TurtleObserver(t.getState())); 
		}
	}

	public void processInput(String input){
		// get Map from parser 
		// call process commands
	}
	

	private void processCommands(Map<Integer, TreeNode> commandMap){
		myModel.update(commandMap); // need to update list of turtles within my model
		//turtleStates = myModel.getTurtleMap();
		/*		
		 * for (Turtle t : turtleStates){
		 *  GUI.update(turtle, turtle.getState);
		 *  }
		 *   */
	}
	
	
	////FOR TESTING ONLY
	protected ModelController getModel(){
		return myModel; 
	}

	
	
}



