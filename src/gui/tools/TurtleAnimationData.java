package gui.tools;

import general_data_structures.Tuple;
import javafx.scene.shape.Line;
import model.turtle.TurtleState;

public class TurtleAnimationData {
	private TurtleState state;
	private Tuple<Double, Double> pos;
	private Line line;
	public TurtleAnimationData(TurtleState s, Tuple<Double, Double> p, Line l){
		state = s;
		pos = p;
		line = l;
	}
	public Line getLine(){
		return line;
	}
	public Tuple<Double, Double> getPos(){
		return pos;
	}
	public TurtleState getTurtleState(){
		return state;
	}
	
}
