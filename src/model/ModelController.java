package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Queue;
import java.util.function.Predicate;

import controller.BackEndHandler;

import model.command.Command;
import model.command.TreeNode;
import model.turtle.Turtle;

public class ModelController {
	
	List<Turtle> myTurtles; 
	List<Double> myTurtleIDs; 
	List<Double> myActiveTurtleIDs;

	String myOutput; 
	BackEndHandler myHandler; 
	
	private final Double DEFAULT_TURTLE_ID = 1.0; 

	public ModelController(BackEndHandler handler){	
		myTurtles = new ArrayList<Turtle>();
		myTurtles.add(new Turtle(DEFAULT_TURTLE_ID)); 
		
		myTurtleIDs = new ArrayList<Double>(); 
		myTurtleIDs.add(DEFAULT_TURTLE_ID);
		
		
		myActiveTurtleIDs = new ArrayList<Double>();
		myActiveTurtleIDs.add(DEFAULT_TURTLE_ID);
		
		myHandler = handler; 	
	}

	public void update(Queue<TreeNode> commandsToExecute){
		myOutput = "";

		while(!commandsToExecute.isEmpty()){ 
			TreeNode command= commandsToExecute.remove();
			((Command) command).execute(myHandler);
			myOutput = ((Double) command.getValue()).toString();
			
		}
	}
	

	public List<Turtle> getTurtles(){
		return Collections.unmodifiableList(myTurtles);  // make this list unmodifiable to anyone except the model
	}
	
	
	public List<Double> getTurtleIDs(){
		return myTurtleIDs; 
	}
	
	public List<Double> getActiveTurtleIDs(){
		return myActiveTurtleIDs;
	}
	
	public String getStringOutput(){
		return myOutput; 
	}

	public void reset() {
		for (Turtle t: myTurtles){
			t.reset();
		}
	}
	
	public Turtle makeNewTurtle(double id){
		Turtle newTurtle = new Turtle(id);
		myTurtles.add(newTurtle);
		myTurtleIDs.add(id);
		myHandler.setRelationship(id);
		return newTurtle;
	}
	
	public void setActiveTurtles(List<Double> actives){
		myActiveTurtleIDs.clear();
		myActiveTurtleIDs.addAll(actives);
	}
//	
//	private Turtle getTurtleByID(double id){
//		Predicate<? super Turtle> predicate = t -> t.getID() == id;
//		return myTurtles.stream().filter(predicate).findFirst().get();
//		}
	
}
