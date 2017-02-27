package gui;

import gui.API.UIDisplayAPI;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UITurtleDisplayView extends Pane implements UIDisplayAPI{

	public UITurtleDisplayView(Frame localBounds) {
		// TODO Auto-generated constructor stub
		setupViews();
	}

	private void setupViews() {
		this.setBackground(GUITools.getBackgroundWithColor(Color.BLUE, 8));
		
	}

}
