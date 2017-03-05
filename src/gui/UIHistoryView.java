package gui;

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
	
	public UIHistoryView(Frame frame){
		super(frame);
		setupViews();
	}

	@Override
	public void addNewCommand(String s) {
		_textArea.appendText("\n"+s);
	}
	public void clear(){
		_textArea.clear();
	}
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
