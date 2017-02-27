package gui;

import gui.API.UIDisplayAPI;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UITurtleDisplayView extends Pane implements UIDisplayAPI{
	Frame _bounds;
	public UITurtleDisplayView(Frame bounds) {
		// TODO Auto-generated constructor stub
		_bounds = bounds;
		setupViews();
	}

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, Color.BLUE, _bounds);
		
	}

}
