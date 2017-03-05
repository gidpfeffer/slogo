package gui;

import gui.API.ButtonControlHandler;
import gui.tools.Frame;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class UIButtonControlPane extends Pane{
	private Frame _bounds;
	private Button _pauseButton;
	private Button _playButton;
	private Button _stopButton;
	private ButtonControlHandler _handler;
	public UIButtonControlPane(Frame bounds, ButtonControlHandler handler){
		_bounds = bounds;
		_handler = handler;
		setupViews();
	}
	private void setupViews() {
		
	}
	
	private void play(){
		_handler.handlePlay();
	}
	private void pause(){
		_handler.handlePause();
	}
	private void stop(){
		_handler.handlePause();
	}
	private void createNewWorkspace(){
		_handler.handleNewWorkspace();
	}
	
}
