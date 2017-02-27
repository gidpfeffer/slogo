package gui;

import general_data_structures.UserVariables;
import gui.API.UIVariablesAPI;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UIVariablesView extends Pane implements UIVariablesAPI {
	
	public UIVariablesView(Frame varsFrame) {
		// TODO Auto-generated constructor stub
		setupViews();
	}


	@Override
	public void update(UserVariables vars) {
		// TODO Auto-generated method stub
		
	}
	

	private void setupViews() {
		// TODO Auto-generated method stub
		this.setBackground(GUITools.getBackgroundWithColor(Color.RED, 0));
		
	}

}
