package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import model.command.TreeNode;
import model.turtle.Turtle;

public class ModelController {

	ArrayList<Turtle> turtles;
	HashMap<Turtle, TurtleObserver> turtleMap; 

	Turtle myTurtle;
	String output; 

	public ModelController(){

		// GUI can hand these to controller and controller can hand them down if needed -  x and y should be based on GUI size 

		myTurtle = new Turtle();

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
			output = ((Double) command.getValue()).toString();
			command.execute();
		}
	}

	public String getStringOutput(){
		return output; 
	}


	public void reset() {
		myTurtle = new Turtle(); 
	}




}
