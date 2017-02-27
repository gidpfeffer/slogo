package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.ControlHandler;
import gui.API.UITerminalAPI;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class UITerminalView extends Group implements UITerminalAPI {
	
	private TextField _textField;
	private TextArea _textArea;
	private ControlHandler _handler;
	private Frame _bounds;
	
	public UITerminalView(Frame bounds, ControlHandler handler){
		super();
		_handler = handler;
		_bounds = bounds;
		setupViews();
	}
	
	@Override
	public void addNewLines(List<String> lines) {
		// TODO Auto-generated method stub
		String curr = _textArea.textProperty().getValue();
		for (String line : lines){
			_textArea.textProperty().set(curr + "\n" + line);
		}
		//scroll down
	}
	
	private void setupViews(){
		//TODO:
		double width = _bounds.getWidth();
		double topHeight = _bounds.getHeight()*4/5;
		double bottomHeight = _bounds.getHeight() - topHeight;
		
		_textField = new TextField(" > ");
		_textArea = new TextArea();
		_textField.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.ENTER){
				textFieldDidPressEnter();
			}
		});
		
		_textArea.setLayoutX(0);
		_textArea.setLayoutY(0);
		_textArea.setPrefWidth(width);
		_textArea.setPrefHeight(topHeight);
		
		_textField.setLayoutX(0);
		_textField.setLayoutY(topHeight);
		_textField.setPrefWidth(width);
		_textField.setPrefHeight(bottomHeight);
	}
	
	private void textFieldDidPressEnter(){
		String input = _textField.textProperty().getValue();
		_textField.setText(" > ");
		addNewLines(new ArrayList<String>(Arrays.asList(input)));
		System.out.println(input);
		_handler.handleTextInput(input);
	}
}
