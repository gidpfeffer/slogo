package gui.tools;

import javafx.scene.layout.Pane;

public class UIView extends Pane{
	
	protected Frame _frame;
	protected Frame _bounds;
	
	public UIView(){
		super();
		_frame = new Frame(0,0,0,0);
		_bounds = new Frame(0,0,0,0);
	}
	
	public UIView(Frame frame){
		super();
		_bounds = frame.toLocalBounds();
		setFrame(frame);
	}
	
	public void setFrame(Frame frame){
		this.setLayoutX(frame.getX());
		this.setLayoutY(frame.getY());
		this.setPrefWidth(frame.getWidth());
		this.setPrefHeight(frame.getHeight());
	}

	public Frame getFrame() {
		return _frame;
	}
	public Frame getBounds(){
		return _bounds;
	}
	
	
}
