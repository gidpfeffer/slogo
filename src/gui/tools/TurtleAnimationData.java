package gui.tools;

import general_data_structures.Tuple;
import javafx.scene.shape.Line;
import model.turtle.TurtleState;

public class TurtleAnimationData {
	private double angle;
	private Tuple<Double, Double> position;
	public TurtleAnimationData(double angle, Tuple<Double, Double> position){
		this.angle = angle;
		this.position = position;
	}
	public Tuple<Double, Double> getPosition(){
		return position;
	}
	public double getAngle(){
		return angle;
	}
	
}
