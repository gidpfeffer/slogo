package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.turtle.TurtleState;

public class UITurtle extends ImageView {
	TurtleState _turtleState;
	public UITurtle(TurtleState t){
		_turtleState = t;
	}
}
