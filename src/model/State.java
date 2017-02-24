package model;

public class State {
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
	
}
