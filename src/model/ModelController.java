package model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModelController {
	
	ArrayList<Turtle> turtles;
	HashMap<Turtle, TurtleObserver> turtleMap; 
	
	public ModelController(){
		turtles = new ArrayList<Turtle>();
		turtleMap = new HashMap<Turtle, TurtleObserver>(); 
		Turtle firstTurtle = new Turtle(0,0,0,true); 
		turtleMap.put(firstTurtle, new TurtleObserver(firstTurtle.getState())); 
		turtles.add(firstTurtle);
									
	}
	
	
	// is this illegal
	public List<Turtle> getTurtles(){ 
		return turtles; 
	}
	
	public Map<Turtle, TurtleObserver> getTurtleMap(){
		return turtleMap; 
	}
	
	public void update(Map<Integer,TreeNode> commandMap){
		for (Integer turtleIndex: commandMap.keySet()){
			
			TreeNode commandNode = commandMap.get(turtleIndex); 
			if (commandNode instanceof TurtleCommand){
				((TurtleCommand) commandNode).execute(turtles.get(turtleIndex));
			}
			
		}
	}
	
}
