package gui;

import java.util.ArrayList;
import java.util.List;

import gui.API.UIDisplayAPI;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UITurtleAttributes;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import model.turtle.TurtleState;

public class UITurtleDisplayView extends Pane implements UIDisplayAPI{
	Frame _bounds;
	List<UITurtle> _turtles;
	List<Line> _lines = new ArrayList<Line>();
	Color _lineColor = MyColors.GREEN;
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
		turtle.setTurtleState(newState, GUITools.turtleCoordinateToPixelCoordinate(newState, _bounds));
		UITurtleAttributes old = turtle.getPriorAttributes();
		UITurtleAttributes curr = turtle.getNewAttributes();

		if(old != null && turtle.getTurtleState().getPen()){
			double ins = turtle.getWidth()/2.;
			Line line = new Line(old.x + ins, old.y + ins, curr.x + ins, curr.y + ins);
			line.setStroke(_lineColor);
			line.setStrokeWidth(_strokeWidth);
			_lines.add(line);
			this.getChildren().add(1,line);
		}
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
