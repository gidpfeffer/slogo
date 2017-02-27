package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import model.turtle.TurtleState;

public class UITurtle extends Rectangle {
	TurtleState _turtleState;
	ImageView _imageView;
	public UITurtle(TurtleState t, ImageView imageView){
		_turtleState = t;
		_imageView = imageView;
	}
}
