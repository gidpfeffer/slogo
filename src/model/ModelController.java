package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import controller.Controller.myHandler;
import model.command.Command;
import model.command.TreeNode;
import model.movement.ClearScreen;
import model.turtle.Turtle;

public class ModelController {

	ArrayList<Turtle> turtles;
	HashMap<Turtle, TurtleObserver> turtleMap; 

	Turtle myTurtle;
	String output; 
	myHandler handler; 

	public ModelController(myHandler myHandler){

		// GUI can hand these to controller and controller can hand them down if needed -  x and y should be based on GUI size 

		myTurtle = new Turtle();
		handler = myHandler; 

	}


	public Map<Turtle, TurtleObserver> getTurtleMap(){
		return turtleMap; 
	}


	public Turtle getTurtle(){ 
		return myTurtle; 
	}


	public void update(Queue<TreeNode> commandsToExecute){
		output = "";

		while(!commandsToExecute.isEmpty()){ 
			// order of these calls matters!!
			TreeNode command= commandsToExecute.remove();
			if (command instanceof ClearScreen){
				handler.handleReset(); 
			}

			output = ((Double) command.getValue()).toString();
			((Command) command).execute();
		}
	}

	public String getStringOutput(){
		return output; 
	}


	public void reset() {
		myTurtle.reset(); 

	}




}
