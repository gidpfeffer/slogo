package gui;

import general_data_structures.UserVariables;
import gui.API.UIVariablesAPI;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UIVariablesView extends Pane implements UIVariablesAPI {
	Frame _bounds;
	public UIVariablesView(Frame bounds) {
		super();
		_bounds = bounds;
		setupViews();
	}


	@Override
	public void update(UserVariables vars) {
		// TODO Auto-generated method stub
		
	}
	

	private void setupViews() {
		// TODO Auto-generated method stub
		GUITools.addBackgroundWithColor(this, MyColors.LIGHT_GREEN, _bounds);
		
	}

}
