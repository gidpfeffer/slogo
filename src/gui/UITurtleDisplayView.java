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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.turtle.TurtleState;

public class UITurtleDisplayView extends UIView implements UIDisplayAPI{
	
	private List<UITurtle> _turtles;
	private List<Line> _lines = new ArrayList<Line>();
	double _strokeWidth = 2.5;
	
	public UITurtleDisplayView(Frame frame, List<UITurtle> turtles) {
		super(frame);
		_turtles = turtles;
		setupViews();
	}
	
	class Handler implements TurtleDisplayHandler{
		@Override
		public void addLineToScreen(Line l) {
			addLine(l);
		}
	}
	
	/**
	 * use this method to change the attributes of a turtle on screen
	 * @param turtle to be moved or animated
	 */
//	public void updateTurtleState(UITurtle turtle, TurtleState newState){
//		//turtle.setTurtleState(newState, GUITools.turtleCoordinateToPixelCoordinate(newState, _bounds));
//		if(newState.getPen()){
//			Line line = new Line();
//			_lines.add(line);
//			this.getChildren().add(1,line);
//			turtle.addAnimationToQueue(newState, 
//					GUITools.turtleCoordinateToPixelCoordinate(newState, _bounds), line);
//		}else{
//			turtle.addAnimationToQueue(newState, 
//					GUITools.turtleCoordinateToPixelCoordinate(newState, _bounds));	
//		}
//	}
	
	public void resetDisplay(){
		clearLines();
		for(UITurtle t : _turtles){
			TurtleState reset = new TurtleState();
			System.out.println(reset.getHeadAngle()+"\t"+ reset.getX() +"\t"+reset.getY());
			t.reset(reset, GUITools.turtleCoordinateToPixelCoordinate(reset, _bounds));
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
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, _bounds);
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
	

}
