package model.turtle;



public class Turtle {
	
	private TurtleState myState; 

	public Turtle(double id, double x, double y, double angle, boolean p, boolean v, double pensize, double pencolindex, double shapeindex){
		myState = new TurtleState(id,x,y,angle,p,v,pensize,pencolindex,shapeindex);
	}
	public Turtle(TurtleState state){
		myState = new TurtleState(state);
	}
	public Turtle(){
		myState = new TurtleState(1,0,0,0,true,true,3,0,0);
	}

	public State getReadOnlyState(){ //safe way to hand turtle state 
		return (State) myState;
	}
	
	public TurtleState getState(){ 
		return myState;
	}
	public void reset(){
		myState.reset();
	}
	
}
