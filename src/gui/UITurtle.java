package gui;

import general_data_structures.Tuple;
import gui.tools.UITurtleAttributes;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.turtle.TurtleState;

public class UITurtle extends Rectangle {
	private TurtleState _turtleState;
	private UITurtleAttributes _turtleAtt;
	private UITurtleAttributes _priorTurtleAtt;
	

	public UITurtle(){
		this(new Image("turtle.png"));
	}
	public UITurtle(Image image){
		this(image, new TurtleState());
	}
	public UITurtle(Image image, TurtleState state){
		_turtleState = state;
		setImageView(image);
	}
	
	public void setImageView(Image image){
		setFill(new ImagePattern(image));
	}
	
	public void setTurtleState(TurtleState s, Tuple<Double, Double> widthHeight, Tuple<TranslateTransition, RotateTransition> animators){
		_priorTurtleAtt = _turtleAtt;
		_turtleAtt = new UITurtleAttributes(widthHeight.x, widthHeight.y, (-s.getHeadAngle() + 90)%360);
		animators.x.setByX(_turtleAtt.x - _priorTurtleAtt.x);
		animators.x.setByY(_turtleAtt.y - _priorTurtleAtt.y);
		animators.y.setByAngle(_turtleAtt.angle - _priorTurtleAtt.angle);
		animators.x.play();
		animators.y.play();
	}
	public void setTurtleState(TurtleState s, Tuple<Double, Double> widthHeight){
		_priorTurtleAtt = _turtleAtt;
		_turtleAtt = new UITurtleAttributes(widthHeight.x, widthHeight.y, (-s.getHeadAngle() + 90)%360);
		this.setLayoutX(_turtleAtt.x);
		this.setLayoutY(_turtleAtt.y);
		this.setWidth(32);
		this.setHeight(32);
		this.setRotate(_turtleAtt.angle);
	}
	public TurtleState getTurtleState(){
		return _turtleState;
	}
	public UITurtleAttributes getPriorAttributes() {
		return _priorTurtleAtt;
	}
	public UITurtleAttributes getNewAttributes() {
		return _turtleAtt;
	}
	public void setTurtleImage(Image img){
		//TODO
	}
}
