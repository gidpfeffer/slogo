package gui;

import java.util.ArrayList;
import java.util.List;

import general_data_structures.Tuple;
import gui.API.TurtleDisplayHandler;
import gui.API.UIDisplayAPI;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.turtle.TurtleState;

public class UITurtleDisplayView extends UIView implements UIDisplayAPI{
	
	private List<UITurtle> _turtles;
	private List<Line> _lines = new ArrayList<Line>();
	double _strokeWidth = 2.5;
	private Rectangle _background;
	
	public UITurtleDisplayView(Frame frame) {
		super(frame);
		setupTurtleMap(1);
		setupViews();
	}
	
	class Handler implements TurtleDisplayHandler{
		@Override
		public void addLineToScreen(Line l) {
			addLine(l);
		}
	}
	
	public void resetDisplay(){
		clearLines();
		for(UITurtle t : _turtles){
			TurtleState reset = new TurtleState();
			System.out.println(reset.getHeadAngle()+"\t"+ reset.getX() +"\t"+reset.getY());
			t.reset(reset, GUITools.turtleCoordinateToPixelCoordinate(reset, _bounds));
		}
	}
	
	private void setupTurtleMap(double numberOfTurtles) {
		_turtles = new ArrayList<UITurtle>();
		for (int i = 0; i < numberOfTurtles; i++) {
			addTurtle((double)i);
		}
	}
	
	private void addLine(Line l){
		_lines.add(l);
		this.getChildren().add(1,l);
	}
	
	private void clearLines(){
		this.getChildren().removeAll(_lines);
		_lines.clear();
		System.out.println("\t*\tCleared Screen\t*");
	}

	private void setupViews() {
		_background = GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, _bounds);
		this.setClip(new Rectangle(_bounds.getWidth(), _bounds.getHeight()));
		for(UITurtle t: _turtles){
			t.setTurtleState(t.getTurtleState(), GUITools.turtleCoordinateToPixelCoordinate(t.getTurtleState(), _bounds));
			this.getChildren().add(t);
		}
	}

	public UITurtle addTurtle(double id) {
		TranslateTransition tran = new TranslateTransition();
		RotateTransition rot = new RotateTransition();
		Tuple<TranslateTransition, RotateTransition> animators = new Tuple<TranslateTransition, RotateTransition>(tran,
				rot);

		UITurtle t = new UITurtle(animators, id, new Handler(), _bounds);

		tran.setNode(t);
		tran.setDuration(Duration.millis(200));

		rot.setNode(t);
		rot.setDuration(Duration.millis(200));
		return t;
	}

	public void setTurtleImage(Image image) {
		for (UITurtle t : _turtles) {
			//TODO check if turtle is active
			t.setImageView(new ImageView(image));
		}
	}


	public UITurtle getTurtle() {
		return _turtles.isEmpty()? null:_turtles.get(0);
	}

	public UITurtle getTurtle(Double id) {
		return _turtles.stream()
				.filter(turtle -> turtle.getTurtleId() == id).
				findFirst().orElse(null);
	}

	public void setPenWidth(double width) {
		for (UITurtle t : _turtles) {
			t.setPenStrokeWidth(width);
		}
		
	}

	public void setBackgroundColor(Color c) {
		_background.setFill(c);
	}

	public void setPenColor(Color c) {
		for (UITurtle t : _turtles) {
			t.setLineColor(c);
		}
	}

	public void stop() {
		for (UITurtle t : _turtles) {
			t.stop();
		}
	}

	public void pause() {
		for (UITurtle t : _turtles) {
			t.pause();
		}
	}

	public void play() {
		for (UITurtle t : _turtles) {
			t.play();
		}
	}
	

}
