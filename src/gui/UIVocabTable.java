package gui;

import general_data_structures.Vocabulary;
import gui.API.UIVocabularyAPI;
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
		GUITools.addBackgroundWithColor(this, Color.BISQUE, _bounds);
		
	}

}
