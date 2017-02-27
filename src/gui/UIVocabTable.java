package gui;

import general_data_structures.Vocabulary;
import gui.API.UIVocabularyAPI;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UIVocabTable extends Pane implements UIVocabularyAPI {

	public UIVocabTable(Frame vocabFrame) {
		// TODO Auto-generated constructor stub
		setupViews();
	}


	@Override
	public void update(Vocabulary newVocab) {
		// TODO Auto-generated method stub
		
	}
	

	private void setupViews() {
		// TODO Auto-generated method stub
		this.setBackground(GUITools.getBackgroundWithColor(Color.BLANCHEDALMOND, 20));
		
	}

}
