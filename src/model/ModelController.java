package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class ModelController {
	ArrayList<Turtle> turtles;
	HashMap<Turtle, State> turtleMap; 
	public ModelController(){
		turtles = new ArrayList<Turtle>();
		turtleMap = new HashMap<Turtle, State>(); 
		// need blank constructor for turtle - set to default value to initialize
		// GUI can hand these to controller and controller can hand them down if needed -  x and y should be based on GUI size 
		Turtle firstTurtle = newTurtle(); 
		State firstState = new State(); 
		turtleMap.put(firstTurtle, firstState); 
		turtles.add(firstTurtle);
									
	}
	
	public Collection<Turtle> getTurtles(){ 
		return turtles; 
	}
	public Map<Turtle, State> getTurtleMap(){
		return turtleMap; 
	}
	public void update(Queue commands){
		while (!commands.isEmpty()){
		Command c = commands.poll();
		c.execute(); 
		}
	}
	
}
