package gui;

import java.util.Observable;
import java.util.Observer;

import general_data_structures.Vocabulary;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UIVocabTable extends Pane implements Observer {
	Frame _bounds;
	public UIVocabTable(Frame bounds) {
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
		Label title = GUITools.plainLabelBoldHelvetica("Vocab", 20, MyColors.DARK_GREEN);
		title.setPrefWidth(_bounds.getWidth());
		title.setPrefHeight(40);
		this.getChildren().add(title);
	}
	
	private void addNewFunction(String s){
		//TODO:
	}
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


	public void clear() {
		// TODO Auto-generated method stub
		
	}
	

}
