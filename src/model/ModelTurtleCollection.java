package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.turtle.Turtle;

public class ModelTurtleCollection extends Observable{
	protected List<Turtle> currentTurtles; 
	protected List<Double> currentTurtleIds; 
	
	public ModelTurtleCollection(){
		currentTurtles = new ArrayList<Turtle>();
		configCurrentIds(); 	
	}
	
	public ModelTurtleCollection(List<Turtle> turtles){
		currentTurtles = new ArrayList<Turtle>(turtles);
		configCurrentIds(); 
	}
	
	private void configCurrentIds() {
		currentTurtleIds = new ArrayList<Double>(); 
		for (Turtle t: currentTurtles){
			currentTurtleIds.add(t.getID());
		}
	}
	
	public void addTurtle(Double id){
		currentTurtles.add(new Turtle(id));
		currentTurtleIds.add(id);
		notifyObservers(id);
	}
	
	public List<Double> getIDList(){
		return currentTurtleIds; 
	}
	
	public List<Turtle> getTurtleList(){
		return currentTurtles; 
	}
}
