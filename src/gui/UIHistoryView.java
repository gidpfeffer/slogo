package gui;

import java.util.ArrayList;
import java.util.List;

import gui.API.CommandHistoryAPI;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;

public class UIHistoryView extends UIView implements CommandHistoryAPI {
	
	private TextArea _textArea;
	//private List<String> _history = new ArrayList<String>();
	private int _index = 0;
	
	public UIHistoryView(Frame frame){
		super(frame);
		setupViews();
	}

	@Override
	public void addNewCommand(String s) {
		_textArea.appendText("\n"+s);
//		_history.add(s);
//		_index = _history.size() - 1;
	}
	public void clear(){
		_textArea.clear();
	}
//	public String getCommandUpKey(){ //moves backwards in history
//		if(!_history.isEmpty() && _index >= 0 && _index < _history.size()){
//			_index--;
//			return _history.get(_index + 1);
//		}
//		
//		else {
//			_index = 0;
//			return "";
//		}
//	}
//	public String getCommandDownKey(){
//		if(!_history.isEmpty() && _index >= 0 && _index < _history.size()){
//			_index++;
//			return _history.get(_index - 1);
//		}
//		
//		else {
//			_index = _history.size() - 1;
//			return "";
//		}
//			
//	}
	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, _bounds);
		_textArea = new TextArea("\n\n");
		_textArea.setPrefWidth(_bounds.getWidth());
		_textArea.setPrefHeight(_bounds.getHeight());
		_textArea.setBorder(Border.EMPTY);
		_textArea.setBackground(Background.EMPTY);
		_textArea.setEditable(false);
		_textArea.setBlendMode(BlendMode.DARKEN);
		_textArea.setScrollTop(_bounds.getHeight());
		_textArea.setWrapText(true);
		_textArea.textProperty().addListener(e -> {_textArea.setScrollTop(Double.MAX_VALUE);});
		this.getChildren().add(_textArea);
	}


}
