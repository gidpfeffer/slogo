package gui;

import gui.API.ButtonControlHandler;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.ImageButton;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

public class UIButtonControlView extends UIView{
	private ImageButton _pauseButton;
	private ImageButton _playButton;
	private ImageButton _stopButton;
	private Slider _speedSlider;
	private ButtonControlHandler _handler;
	public UIButtonControlView(Frame frame, ButtonControlHandler handler){
		super(frame);
		_handler = handler;
		setupViews();
	}
	private void setupViews() {
		//GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, _bounds);
		this.setBorder(new Border(new BorderStroke[]{ 
				new BorderStroke(MyColors.GREEN_100, BorderStrokeStyle.SOLID,
						new CornerRadii(3), new BorderWidths(1.5))
				}));
		setupButton(_playButton, 16, "play.png",new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				play();
			}
		});
		setupButton(_pauseButton, 56, "pause.png",new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				pause();
			}
		});
		setupButton(_stopButton, 96, "stop.png",new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				stop();
			}
		});
		setupSlider();
	}
	private void setupSlider() {
		_speedSlider = new Slider();
		_speedSlider.setMin(1);
		_speedSlider.setMax(11);
		_speedSlider.setValue(5);
		//_speedSlider.setShowTickLabels(true);
		//_speedSlider.setShowTickMarks(true);
		//_speedSlider.setMajorTickUnit(6);
		//_speedSlider.setMinorTickCount(5);
		_speedSlider.setPrefWidth(_bounds.getWidth()/2 - 8);
		_speedSlider.setLayoutY(16); // bad way to set this
		_speedSlider.setLayoutX(_bounds.getWidth()/2);
		_speedSlider.setBackground(Background.EMPTY);
		_speedSlider.setBorder(Border.EMPTY);
		//_speedSlider.setOnDragDropped(e -> System.out.println(_speedSlider.getValue()));
		_speedSlider.setOnMouseExited(e -> System.out.println(_speedSlider.getValue()));
		this.getChildren().add(_speedSlider);
	}
	private void setupButton(ImageButton b, double insetX, 
			String imgName, EventHandler<? super MouseEvent> event){
		b = new ImageButton();
		b.updateImages(new Image(imgName), new Image(imgName));
		b.setLayoutX(insetX);
		b.setLayoutY(2);
		b.setPrefWidth(32);
		b.setPrefHeight(32);
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
