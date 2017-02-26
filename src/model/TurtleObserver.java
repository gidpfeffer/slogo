package model;
import java.util.Observable;
import java.util.Observer;

import model.turtle.Turtle;
import model.turtle.TurtleState;



public class TurtleObserver implements Observer {
	private TurtleState observerTurtleState;


	public TurtleObserver(){
		observerTurtleState = new TurtleState(0, 0, 0, false, false);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		observerTurtleState = new TurtleState((TurtleState) o); 
		System.out.println("new x is " + observerTurtleState.getX()); // for testing
		System.out.println("new y is " + observerTurtleState.getY());
		
	}

	
	
/*	public static void main(String[] args){
		Turtle t = new Turtle(0,0,0,true);
		TurtleObserver o = new TurtleObserver();
		t.getState().addObserver(o);
		t.getState().setX(3);
	}*/
	
	
	
/*
	public static void main(String[] args){
		Turtle t = new Turtle(0,0,0,true,true);
		TurtleObserver o = new TurtleObserver();
		t.getState().addObserver(o);
		
		
		t.getState().setX(3);
	*/

}

