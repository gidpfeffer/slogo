package model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModelController {
	ArrayList<Turtle> turtles;
	HashMap<Turtle, TurtleState> turtleMap; 
	public ModelController(){
		turtles = new ArrayList<Turtle>();
		turtleMap = new HashMap<Turtle, TurtleState>(); 
		// need blank constructor for turtle - set to default value to initialize
		// GUI can hand these to controller and controller can hand them down if needed -  x and y should be based on GUI size 
		Turtle firstTurtle = new Turtle(0,0,0,true); 
//		TurtleState firstState = new TurtleState(); 
		turtleMap.put(firstTurtle, firstTurtle.getState()); 
		turtles.add(firstTurtle);
									
	}
	
	public List<Turtle> getTurtles(){ 
		return turtles; 
	}
	public Map<Turtle, TurtleState> getTurtleMap(){
		return turtleMap; 
	}
	public void update(Map<Integer,TreeNode> commandMap){
//		while (!commands.isEmpty()){
//		Command c = commands.poll();
//		c.execute(); 
		
		for (Integer turtleIndex: commandMap.keySet()){
			
			TreeNode commandNode = commandMap.get(turtleIndex); 
			if (commandNode instanceof TurtleCommand){
				((TurtleCommand) commandNode).execute(turtles.get(turtleIndex));
			}
			
		}
	}
	
}
