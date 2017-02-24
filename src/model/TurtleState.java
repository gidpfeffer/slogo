package model;


import java.util.Observable; 

public class TurtleState extends Observable{
	double myX; 
	double myY; 
	double myAngle; 
	boolean myPen; 
	public TurtleState(double x, double y, double headAngle, boolean pen){
		myX = x; 
		myY = y; 
		myAngle = headAngle; 
		myPen = pen;
	}
	public void setX(double num){
		myX = num; 
		setChanged();
		notifyObservers();
	}
	
	public void setY(double num){
		myY = num; 
		setChanged();
		notifyObservers();

	}
	
	public void setHeadAngle(double num){
		myAngle = num; 
		setChanged();
		notifyObservers();
		
	}
	public void setPen(boolean p){
		myPen = p; 
		setChanged();
		notifyObservers();
	}
	
	public double getX(){
		return myX ;
		
		
	}
	
	public double getY(){
		return myY; 
	}
	
	public double getHeadAngle(){
		return myAngle;
	}
	
	public boolean getPen(){
		return myPen; 
	}

	
}
