package model;
import java.util.Observable;
import java.util.Observer;



public class TurtleObserver implements Observer {
	
	private Turtle observableTurtle;
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		observableTurtle = (Turtle) o; 
		Turtle s = observableTurtle;
		System.out.println("changed property, new value is " + s.getState().getX());		
	}
	
	public static void main(String[] args){
		Turtle t = new Turtle(0,0,0,true);
		TurtleObserver o = new TurtleObserver();
		
		t.addObserver(o);
		t.getState().setX(3);
	}

}
