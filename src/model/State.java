package model;

import java.util.Observable;
import java.util.Observer;

public class State  implements Observer{
	double myX; 
	double myY; 
	double angle; 
	boolean pen; 
	public State(double x, double y, double headAngle, boolean penUp){
		myX = x; 
		myY = y; 
		angle = headAngle; 
		pen = penUp;
	}
	public State(){ 
		// need to get default values from GUI
		myX = 100; 
		myY = 100; 
		angle = 90; 
		pen = false; 
	}
	@Override
	public void update(Observable o, Object arg) {
		State newState = ((Turtle)o).getState(); 
		this.setState(newState);
	}
	public void setState(State s){
		myX = s.getX(); 
		myY = s.getY();
		angle = s.getAngle();
		pen = s.isPenUp();
	}
	public double getX(){
		return myX; 
	}
	public double getY(){
		return myY; 
	}
	public double getAngle(){
		return angle; 
	}
	public boolean isPenUp(){
		return pen; 
	}
}
