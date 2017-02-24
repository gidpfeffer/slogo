package model;

import java.util.Observable;


public class Turtle extends Observable {
	private double xLocation;
	private double yLocation; 
	private double pen; 
	private double headAngle;
	
	public Turtle(double x, double y,double p, double angle){
		xLocation = x; 
		yLocation = y; 
		pen = p; 
		headAngle = angle; 
	}
	
	public void setX(double num){
		xLocation = num; 
		setChanged();
		notifyObservers();
	}
	
	public void setY(double num){
		yLocation = num; 
		setChanged();
		notifyObservers();
	}
	
	public void setHeadAngle(double num){
		headAngle = num; 
		setChanged();
		notifyObservers();
	}
	public void setPen(){
		pen = (pen == 0.0)? 1.0: 0.0;
		setChanged();
		notifyObservers();
	}
	
	
	public double getX(){
		return xLocation ;
		
	}
	
	public double getY(){
		return yLocation; 
	}
	
	public double getHeadAngle(){
		return headAngle;
	}
	
	public double getPen(){
		return pen; 
	}
	
	
}
