package gui;

import gui.API.UITerminalAPI;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.UIView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UITerminalView extends UIView implements UITerminalAPI {
	
	private TextArea _textArea;
	private String _lastCommand = "";
	public UITerminalView(Frame frame){
		super(frame);
		setupViews();
	}
	
	
	private void setupViews(){
		GUITools.addBackgroundWithColor(this, Color.WHITE, _bounds);
		_textArea = new TextArea();
		_textArea.setPrefWidth(_bounds.getWidth());
		_textArea.setPrefHeight(_bounds.getHeight());
		_textArea.setBorder(Border.EMPTY);
		_textArea.setBackground(Background.EMPTY);
		_textArea.setWrapText(true);
		this.getChildren().add(_textArea);
	}

	@Override
	public String getTextInput(){
		return _lastCommand = _textArea.getText();
	}
	public void clear(){
		_textArea.clear();
	}
	public String getPreviousCommand(){
		return _lastCommand;
	}

	public void addText(String s) {
		clear();
		_textArea.setText(s);
	}
}
