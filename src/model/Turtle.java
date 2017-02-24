package model;

import java.util.Observable;


public class Turtle extends Observable {
	
	private TurtleState myState; 
	

	
	public Turtle(double x, double y, double angle, boolean p){
		myState = new TurtleState(x,y,angle,p);
	}
	public Turtle(TurtleState state){
		myState = state;
	}

	
	public TurtleState getState(){
		return myState;
	}
	
	public void setState(TurtleState newState){
		myState = newState;
		setChanged();
		notifyObservers();
	}
	
	
}
