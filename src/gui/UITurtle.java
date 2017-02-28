package gui;

import general_data_structures.Tuple;
import gui.tools.UITurtleAttributes;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
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
	
	public void setTurtleState(TurtleState s, Tuple<Double, Double> widthHeight){
		_priorTurtleAtt = _turtleAtt;
		_turtleAtt = new UITurtleAttributes(widthHeight.x, widthHeight.y, s.getHeadAngle() + 90);
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
		// TODO Auto-generated method stub
		return _priorTurtleAtt;
	}
	public UITurtleAttributes getNewAttributes() {
		// TODO Auto-generated method stub
		return _turtleAtt;
	}
}
