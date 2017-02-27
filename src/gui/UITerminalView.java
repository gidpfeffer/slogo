package gui;

import gui.API.UITerminalAPI;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UITerminalView extends Pane implements UITerminalAPI {
	
	private TextArea _textArea;
	private Frame _bounds;
	
	public UITerminalView(Frame bounds){
		super();
		_bounds = bounds;
		setupViews();
	}
	
	
	private void setupViews(){
		GUITools.addBackgroundWithColor(this, Color.WHITE, _bounds);
		_textArea = new TextArea();
		_textArea.setPrefWidth(_bounds.getWidth());
		_textArea.setPrefHeight(_bounds.getHeight());
		_textArea.setBorder(Border.EMPTY);
		_textArea.setBackground(Background.EMPTY);
		this.getChildren().add(_textArea);
	}

	@Override
	public String getTextInput(){
		System.out.println(_textArea.textProperty().getValue());
		return _textArea.textProperty().getValue();
	}
	public void clear(){
		_textArea.clear();
	}
}
