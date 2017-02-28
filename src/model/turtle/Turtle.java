package model.turtle;



public class Turtle {
	
	private TurtleState myState; 

	public Turtle(double x, double y, double angle, boolean p, boolean v){
		myState = new TurtleState(x,y,angle,p,v);
	}
	public Turtle(TurtleState state){
		myState = state;
	}
	
	public Turtle(){
		myState = new TurtleState(0,0,0,true,true);
	}

	public TurtleState getState(){
		return myState;
	}
	public void reset(){
		myState.setState(0, 0, 0, true, true);
	}
	
}
