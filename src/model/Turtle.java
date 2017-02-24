package model;

import java.util.Observable;


public class Turtle extends Observable {
	private double xLocation;
	private double yLocation; 
	private boolean pen; 
	private double headAngle;
	
	public Turtle(double x, double y, double angle, boolean p){
		xLocation = x; 
		yLocation = y; 
		headAngle = angle; 
		pen = p;
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
	public void setPen(boolean p){
		pen = p; 
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
	
	public boolean getPen(){
		return pen; 
	}
	
	
}
