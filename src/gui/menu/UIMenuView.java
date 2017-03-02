package gui.menu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.API.UIMainHandler;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.ImageButton;
import gui.tools.MyColors;
import javafx.animation.TranslateTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class UIMenuView extends Pane {
	private Frame _bounds;
	private UIMainHandler _handler;
	private final double TOP_INSET = 60;
	private final Frame COLOR_FRAME;
	private final Frame IMAGE_FRAME;
	private ImageView _turtleImageView;

	public UIMenuView(Frame bounds, UIMainHandler handler) {
		_bounds = bounds;
		_handler = handler;
		COLOR_FRAME = new Frame(16, TOP_INSET, _bounds.getWidth() - 32, 56);
		IMAGE_FRAME = new Frame(16, COLOR_FRAME.getMaxY() + 16, _bounds.getWidth() - 32, 56);

		GUITools.addBackgroundWithColor(this, MyColors.GREEN_900, _bounds);
		setupViews();
	}

	private void setupViews() {
		setupBackButton();
		setupColorPicker();
		setupImagePicker();
	}

	private void setupColorPicker() {
		ColorPicker colorPicker = new ColorPicker(MyColors.GREEN_900);
		colorPicker.setLayoutX(COLOR_FRAME.getX());
		colorPicker.setLayoutY(COLOR_FRAME.getY());
		colorPicker.setPrefWidth(COLOR_FRAME.getWidth());
		colorPicker.setPrefHeight(COLOR_FRAME.getHeight());
		colorPicker.setOnAction(t -> _handler.setLineColor(colorPicker.getValue()));
		colorPicker.setBackground(
				new Background(new BackgroundFill[] { new BackgroundFill(MyColors.GREEN_100, null, null) }));
		// colorPicker.setBlendMode(BlendMode.COLOR_BURN);
		this.getChildren().add(colorPicker);
	}

	private void setupImagePicker() {

		Pane imagePicker = new Pane();
		imagePicker.setLayoutX(IMAGE_FRAME.getX());
		imagePicker.setLayoutY(IMAGE_FRAME.getY());
		imagePicker.setPrefWidth(IMAGE_FRAME.getWidth());
		imagePicker.setPrefHeight(IMAGE_FRAME.getHeight());
		GUITools.addBackgroundWithColor(imagePicker, MyColors.GREEN_100, IMAGE_FRAME.toLocalBounds());

		_turtleImageView = new ImageView(new Image("turtle.png"));
		_turtleImageView.setLayoutX(12);
		_turtleImageView.setLayoutY(12);
		_turtleImageView.setFitHeight(32);
		_turtleImageView.setFitWidth(32);
		imagePicker.getChildren().add(_turtleImageView);

		imagePicker.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)",
						"*.JPG");
				FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)",
						"*.PNG");
				fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
				fileChooser.setInitialDirectory(new File("/Users/TNK/Documents/workspace/slogo_team09/images"));
				File file = fileChooser.showOpenDialog(null);
				try {
					if (file != null) {
						BufferedImage bufferedImage = ImageIO.read(file);
						Image image = SwingFXUtils.toFXImage(bufferedImage, null);
						_turtleImageView.setImage(image);
						_handler.setTurtleImage(image);
					}

				} catch (IOException ex) {
					// TODO send error to UIMain
				}
			}
		});

		this.getChildren().add(imagePicker);
	}

	private void setupBackButton() {
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

	public void addPaneWithFrame(Pane newPane, Frame frame) {

	}

	public void slideMenuOut() {
		TranslateTransition t = new TranslateTransition();
		t.setNode(this);
		t.setDuration(Duration.millis(500));
		t.setToX(-_bounds.getWidth());
		t.play();
	}
}
