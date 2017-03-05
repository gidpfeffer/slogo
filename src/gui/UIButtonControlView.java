package gui;

import gui.API.ButtonControlHandler;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.ImageButton;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class UIButtonControlView extends UIView{
	private ImageButton _pauseButton;
	private ImageButton _playButton;
	private ImageButton _stopButton;
	private ButtonControlHandler _handler;
	public UIButtonControlView(Frame frame, ButtonControlHandler handler){
		_handler = handler;
		setupViews();
	}
	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, _bounds);
		setupButton(_playButton, 8, "play.png",new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				play();
			}
		});
	}
	private void setupButton(ImageButton b, double insetX, 
			String imgName, EventHandler<? super MouseEvent> event){
		b = new ImageButton();
		b.updateImages(new Image(imgName), new Image(imgName));
		b.setLayoutX(insetX);
		b.setLayoutY((_bounds.getHeight() - 32)/2);
		b.setOnMouseClicked(event);
		this.getChildren().add(b);
	}
	private void play(){
		System.out.println("pressed play");
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
