package gui;

import java.util.List;

import gui.API.UIDisplayAPI;
import gui.tools.GUITools;
import javafx.scene.layout.Pane;

public class UITurtleDisplayView extends Pane implements UIDisplayAPI{
	Frame _bounds;
	List<UITurtle> _turtles;
	public UITurtleDisplayView(Frame bounds, List<UITurtle> turtles) {
		// TODO Auto-generated constructor stub
		_bounds = bounds;
		_turtles = turtles;
		setupViews();
	}
	/**
	 * 
	 */
	public void updateTurtleState(UITurtle turtle){
		turtle.setTurtleState(turtle.getTurtleState(), GUITools.turtleCoordinateToPixelCoordinate(turtle.getTurtleState(), _bounds));
	}

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.LIGHT_GREEN, _bounds);
		for(UITurtle t: _turtles){
			t.setTurtleState(t.getTurtleState(), GUITools.turtleCoordinateToPixelCoordinate(t.getTurtleState(), _bounds));
			this.getChildren().add(t);
		}
	}

}
