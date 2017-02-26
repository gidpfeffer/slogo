package model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import model.turtle.TurtleState;

public class ModelController {
<<<<<<< HEAD
	//List<Turtle> turtles;  
	
	Turtle myTurtle;
	
	public ModelController(){
		//turtles = new ArrayList<Turtle>();

		// GUI can hand these to controller and controller can hand them down if needed -  x and y should be based on GUI size 
		
		myTurtle = new Turtle();
		
		//turtles.add(firstTurtle);
=======
	ArrayList<Turtle> turtles;
	HashMap<Turtle, TurtleObserver> turtleMap; 
	public ModelController(){
		turtles = new ArrayList<Turtle>();
		turtleMap = new HashMap<Turtle, TurtleObserver>(); 
		Turtle firstTurtle = new Turtle(0,0,0,true); 
		turtleMap.put(firstTurtle, new TurtleObserver(firstTurtle.getState())); 
		turtles.add(firstTurtle);
>>>>>>> a22cd9452395edd455d677e044428bf75502d024
									
	}
	
	public Turtle getTurtle(){ 
		return myTurtle; 
	}
<<<<<<< HEAD

	public void update(TreeNode commandToExecute){	
		if (commandToExecute instanceof TurtleCommand){
			((TurtleCommand) commandToExecute).execute(myTurtle);
		}
	}
		
		
	/* for multiple turtle extension
=======
	public Map<Turtle, TurtleObserver> getTurtleMap(){
		return turtleMap; 
	}
	
>>>>>>> a22cd9452395edd455d677e044428bf75502d024
	public void update(Map<Integer,TreeNode> commandMap){
		for (Integer turtleIndex: commandMap.keySet()){
			TreeNode commandNode = commandMap.get(turtleIndex); 
			if (commandNode instanceof TurtleCommand){
				((TurtleCommand) commandNode).execute(turtles.get(turtleIndex));
			}
		}
	}
	 */
	
}
