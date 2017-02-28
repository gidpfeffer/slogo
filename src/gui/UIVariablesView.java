package gui;

import java.util.Observable;
import java.util.Observer;

import general_data_structures.UserVariables;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UIVariablesView extends Pane implements Observer {
	Frame _bounds;
	public UIVariablesView(Frame bounds) {
		super();
		_bounds = bounds;
		setupViews();
	}
	

	private void setupViews() {
		// TODO Auto-generated method stub
		GUITools.addBackgroundWithColor(this, MyColors.LIGHT_GREEN, _bounds);
		setupTitle();
	}
	private void setupTitle(){
		Label title = GUITools.plainLabelBoldHelvetica("Variables", 20, MyColors.DARK_GREEN);
		title.setPrefWidth(_bounds.getWidth());
		title.setPrefHeight(40);
		this.getChildren().add(title);
	}


	@Override
	public void update(Observable o, Object arg) {
		
		System.out.println(o);
	}

}
