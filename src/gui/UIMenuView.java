package gui;

import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.ImageButton;
import gui.tools.MyColors;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class UIMenuView extends Pane {
	Frame _bounds;
	public UIMenuView(Frame bounds){
		_bounds = bounds;
		GUITools.addBackgroundWithColor(this, MyColors.DARK_GREEN, _bounds);
		setupViews();
	}
	private void setupViews() {
		// TODO Auto-generated method stub
		setupBackButton();
		
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
