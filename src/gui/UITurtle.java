package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.turtle.TurtleState;

public class UITurtle extends Rectangle {
	private TurtleState _turtleState;
	public UITurtle(TurtleState t, Image image){
		_turtleState = t;
		setImageView(image);
	}
	public UITurtle(TurtleState t){
		this(t,new Image("turtle.png"));
	}
	
	public void setImageView(Image image){
		setFill(new ImagePattern(image));		
	}
	
	public void setTurtleState(TurtleState s){
		_turtleState = s;
	}
	public TurtleState getTurtleState(){
		return _turtleState;
	}
}
