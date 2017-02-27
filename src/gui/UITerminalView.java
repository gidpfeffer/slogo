package gui;

import gui.API.UITerminalAPI;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UITerminalView extends Pane implements UITerminalAPI {
	
	//private TextField _textField;
	private TextArea _textArea;
	private Frame _bounds;
	
	public UITerminalView(Frame bounds){
		super();
		_bounds = bounds;
		setupViews();
	}
	
	
	private void setupViews(){
		this.setBackground(GUITools.getBackgroundWithColor(Color.WHITE, 12));
		_textArea = new TextArea("Sample Text. Please Ignore.");
		_textArea.setPrefWidth(_bounds.getWidth());
		_textArea.setPrefHeight(_bounds.getHeight());
	}

	@Override
	public String getTextInput(){
		System.out.println(_textArea.textProperty().getValue());
		return _textArea.textProperty().getValue();
	}
}
