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

	//List<Turtle> turtles;  

	Turtle myTurtle;
	String output; 

	public ModelController(){
		//turtles = new ArrayList<Turtle>();

		// GUI can hand these to controller and controller can hand them down if needed -  x and y should be based on GUI size 

		myTurtle = new Turtle();

		//turtles.add(firstTurtle);


	}



	// is this illegal
	public List<Turtle> getTurtles(){ 
		return turtles; 
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
			output = ((Double)command.getValue()).toString();
		}
	}

	public String getStringOutput(){
		return output; 
	}


	public void reset() {
		myTurtle = new Turtle(); 
	}


	/* for multiple turtle extension

	public void update(Map<Integer,TreeNode> commandMap){
		for (Integer turtleIndex: commandMap.keySet()){
			TreeNode commandNode = commandMap.get(turtleIndex); 
			if (commandNode instanceof TurtleCommand){
				((TurtleCommand) commandNode).execute(turtles.get(turtleIndex));
				// String output =  ((TurtleCommand) commandNode).execute(turtles.get(turtleIndex));
				// need to return this 
			}
		}
	}
	 */

}
