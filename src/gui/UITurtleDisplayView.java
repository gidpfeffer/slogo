package gui;

import java.util.List;

import gui.API.UIDisplayAPI;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
	public void updateTurtleStates(List<UITurtle> turtles){
		_turtles = turtles;
//		for(UITurtle t: turtles){
//			t.setTurtleState(t);
//		}
	}

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.LIGHT_GREEN, _bounds);
		
	}

}
