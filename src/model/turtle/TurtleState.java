package model.turtle;
import java.util.Observable; 

public class TurtleState extends Observable{
	private double myX; 
	private double myY; 
	private double myAngle; 
	private boolean myPen; 
	private boolean myVisibility;
	
	public TurtleState(double x, double y, double headAngle, boolean pen,boolean v){
		myX = x; 
		myY = y; 
		myAngle = headAngle; 
		myPen = pen;
		myVisibility = v;
	}
	
	
	public TurtleState(){
		this(0,0,0, true, true);
	}
	
	public TurtleState(TurtleState o){
		myX = o.getX();
		myY = o.getY();
		myAngle = o.getHeadAngle();
		myPen = o.getPen();
		myVisibility = o.getVisibility();
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
	
	public void setPosition(double x, double y){
		myX = x;
		myY = y;
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
	
	public void setVisibility(boolean v){
		myVisibility = v; 
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

	public boolean getVisibility(){
		return myVisibility; 
	}
	public void setState(double x, double y, double headAngle, boolean pen,boolean v){
		myX = x; 
		myY = y; 
		myAngle = headAngle; 
		myPen = pen;
		myVisibility = v;
		setChanged(); 
		notifyObservers(); 
	}
	
}
