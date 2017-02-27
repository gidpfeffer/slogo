package gui;

import general_data_structures.Vocabulary;
import gui.API.UIVocabularyAPI;
import gui.tools.GUITools;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UIVocabTable extends Pane implements UIVocabularyAPI {
	Frame _bounds;
	public UIVocabTable(Frame bounds) {
		super();
		_bounds = bounds;
		setupViews();
	}


	@Override
	public void update(Vocabulary newVocab) {
		// TODO Auto-generated method stub
		
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
	

}
