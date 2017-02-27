package model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


	public void update(List<TreeNode> commandsToExecute){
		output = "";

		for(TreeNode command : commandsToExecute){ 
			// order of these calls matters!!
			output = ((Double)command.getValue()).toString();
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
