package gui;

import java.util.ArrayList;
import java.util.List;

import general_data_structures.Tuple;
import gui.API.UIDisplayAPI;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.TurtleViewTransition;
import gui.tools.UITurtleAttributes;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import model.turtle.TurtleState;

public class UITurtleDisplayView extends Pane implements UIDisplayAPI{
	private Frame _bounds;
	private List<UITurtle> _turtles;
	private List<Line> _lines = new ArrayList<Line>();
	double _strokeWidth = 2.5;
	
	public UITurtleDisplayView(Frame bounds, List<UITurtle> turtles) {
		_bounds = bounds;
		_turtles = turtles;
		setupViews();
	}
	
	/**
	 * use this method to change the attributes of a turtle on screen
	 * @param turtle to be moved or animated
	 */
	public void updateTurtleState(UITurtle turtle, TurtleState newState){
		//turtle.setTurtleState(newState, GUITools.turtleCoordinateToPixelCoordinate(newState, _bounds));
		if(newState.getPen()){
			Line line = new Line();
			_lines.add(line);
			this.getChildren().add(1,line);
			turtle.addAnimationToQueue(newState, 
					GUITools.turtleCoordinateToPixelCoordinate(newState, _bounds), line);
		}else{
			turtle.addAnimationToQueue(newState, 
					GUITools.turtleCoordinateToPixelCoordinate(newState, _bounds));	
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
	private void clearLines(){
		this.getChildren().removeAll(_lines);
		_lines.clear();
		System.out.println("\t*\tCleared Screen\t*");
	}

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.LIGHT_GREEN, _bounds);
		this.setClip(new Rectangle(_bounds.getWidth(), _bounds.getHeight()));
		for(UITurtle t: _turtles){
			t.setTurtleState(t.getTurtleState(), GUITools.turtleCoordinateToPixelCoordinate(t.getTurtleState(), _bounds));
			this.getChildren().add(t);
		}
	}
	

}
