package model;


public class TurtleState{
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
		
	}
	
	public void setY(double num){
		myY = num; 

	}
	
	public void setHeadAngle(double num){
		myAngle = num; 
		
	}
	public void setPen(boolean p){
		myPen = p; 
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
