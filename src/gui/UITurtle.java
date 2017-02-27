package gui;

import general_data_structures.Tuple;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.turtle.TurtleState;

public class UITurtle extends Rectangle {
	private TurtleState _turtleState;
	public UITurtle(TurtleState s, Image image){
		_turtleState = s;
		setImageView(image);
	}
	public UITurtle(TurtleState t){
		this(t,new Image("turtle.png"));
	}
	
	public void setImageView(Image image){
		setFill(new ImagePattern(image));
		this.setRotate(90);
	}
	
	public void setTurtleState(TurtleState s, Tuple<Double, Double> widthHeight){
		_turtleState = s;
		this.setLayoutX(widthHeight.x);
		this.setLayoutY(widthHeight.y);
		this.setWidth(32);
		this.setHeight(32);
		this.setRotate(s.getHeadAngle() + 90);
	}
	public TurtleState getTurtleState(){
		return _turtleState;
	}
}
