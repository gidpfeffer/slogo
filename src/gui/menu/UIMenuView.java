package gui.menu;

import gui.API.UIMainHandler;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.ImageButton;
import gui.tools.MyColors;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class UIMenuView extends Pane {
	private Frame _bounds;
	private UIMainHandler _handler;
	private final double TOP_INSET = 60;
	private final Frame COLOR_FRAME;
	public UIMenuView(Frame bounds, UIMainHandler handler){
		_bounds = bounds;
		_handler = handler;
		COLOR_FRAME =  new Frame(16,TOP_INSET,_bounds.getWidth() - 32 , 40);
		GUITools.addBackgroundWithColor(this, MyColors.DARK_GREEN, _bounds);
		setupViews();
	}
	private void setupViews() {
		// TODO Auto-generated method stub
		setupBackButton();
		setupColorPicker();
	}
	private void setupColorPicker() {
		ColorPicker colorPicker = new ColorPicker(MyColors.DARK_GREEN);
		colorPicker.setLayoutX(COLOR_FRAME.getX());
		colorPicker.setLayoutY(COLOR_FRAME.getY());
		colorPicker.setPrefWidth(COLOR_FRAME.getWidth());
		colorPicker.setPrefHeight(COLOR_FRAME.getHeight());
		colorPicker.setOnAction(t -> _handler.setLineColor(colorPicker.getValue()));
		colorPicker.setBackground(new Background(new BackgroundFill[] { new BackgroundFill(MyColors.LIGHT_GREEN, null, null)}));
//		colorPicker.setBlendMode(BlendMode.COLOR_BURN);
		this.getChildren().add(colorPicker);
	}
	private void setupBackButton(){
		ImageButton b = new ImageButton();
		b.updateImages(new Image("back.png"), new Image("back.png"));
		b.setLayoutX(_bounds.getWidth() - 64);
		b.setLayoutY(10);
		b.setPrefWidth(32);
		b.setPrefHeight(32);
		b.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				slideMenuOut();
			}
		});
		this.getChildren().add(b);
	}
	public void addPaneWithFrame(Pane newPane, Frame frame){
		
	}
	public void slideMenuOut(){
		TranslateTransition t = new TranslateTransition();
		t.setNode(this);
		t.setDuration(Duration.millis(500));
		t.setToX(-_bounds.getWidth());
		t.play();
	}
}
