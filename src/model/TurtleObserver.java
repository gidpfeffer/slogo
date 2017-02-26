package model;
import java.util.Observable;
import java.util.Observer;

import model.turtle.TurtleState;



public class TurtleObserver implements Observer {
	
	private TurtleState observableTurtleState;
	public TurtleObserver(TurtleState state){
		observableTurtleState = state; 
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		observableTurtleState = (TurtleState) o; 
		System.out.println("new x is " + observableTurtleState.getX()); // for testing
		System.out.println("new y is " + observableTurtleState.getY());
	}
	
	public TurtleState getNewState(){
		return observableTurtleState;
	}
//	public static void main(String[] args){
//		Turtle t = new Turtle(0,0,0,true);
//		TurtleObserver o = new TurtleObserver();
//		t.getState().addObserver(o);
//		t.getState().setX(3);
//	}

}
