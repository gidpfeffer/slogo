package gui;

import gui.API.CommandHistoryAPI;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;

public class UIHistoryView extends Pane implements CommandHistoryAPI {
	
	private TextArea _textArea;
	private Frame _bounds;
	
	public UIHistoryView(Frame bounds){
		super();
		_bounds = bounds;
		setupViews();
	}

	@Override
	public void addNewCommand(String s) {
		_textArea.setText(_textArea.getText() + s + "\n");
		_textArea.setScrollTop(1000000);
	}
	public void clear(){
		_textArea.clear();
	}
	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.LIGHT_GREEN, _bounds);
		_textArea = new TextArea();
		_textArea.setPrefWidth(_bounds.getWidth());
		_textArea.setPrefHeight(_bounds.getHeight());
		_textArea.setBorder(Border.EMPTY);
		_textArea.setBackground(Background.EMPTY);
		_textArea.setEditable(false);
		_textArea.setBlendMode(BlendMode.DARKEN);
		_textArea.setScrollTop(_bounds.getHeight());
		this.getChildren().add(_textArea);
	}


}
