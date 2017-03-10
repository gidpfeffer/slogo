package gui.menu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import gui.API.UIMainHandler;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.ImageButton;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.animation.TranslateTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import model.turtle.TurtleState;

public class UIMenuView extends UIView {
	
	private final double TOP_INSET = 60;
	private final Frame LCOLOR_FRAME;
	private final Frame IMAGE_FRAME;
	private final Frame PALLETTE_FRAME;
	private final Frame ATTRIBUTES_FRAME;
	private final Frame BCOLOR_FRAME;
	
	private UIMainHandler _handler;
	private ImageView _turtleImageView;
	private ResourceBundle _resources;
	private UIAttributesView _turtleAttributesView;
	private PalletteView _paletteView;

	public UIMenuView(Frame frame, UIMainHandler handler, ResourceBundle resources) {
		super(frame);
		_handler = handler;
		_resources = resources;
		double rightInset = 80 + 32;
		LCOLOR_FRAME = new Frame(16, TOP_INSET, getBounds().getWidth() - rightInset, 56);
		BCOLOR_FRAME = new Frame(16, LCOLOR_FRAME.getMaxY() + 16, getBounds().getWidth() - rightInset, 56);
		IMAGE_FRAME = new Frame(16, BCOLOR_FRAME.getMaxY() + 16, getBounds().getWidth() - rightInset, 56);
		PALLETTE_FRAME = new Frame(LCOLOR_FRAME.getMaxX() + 8, TOP_INSET, rightInset - 32, IMAGE_FRAME.getMaxY() - TOP_INSET );
		ATTRIBUTES_FRAME = new Frame(16, PALLETTE_FRAME.getMaxY() + 16, getBounds().getWidth() - 32, 150);
		
		setupViews();
	}

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_900, getBounds());
		setupBackButton();
		ColorPicker lColorPicker = new ColorPicker(MyColors.GREEN_900);
		ColorPicker bColorPicker = new ColorPicker(MyColors.GREEN_900);
		setupColorPicker(LCOLOR_FRAME, _resources.getString("LineColorTitle"), lColorPicker, t -> _handler.setLineColor(lColorPicker.getValue())); //TODO resources
		setupColorPicker(BCOLOR_FRAME, _resources.getString("BackgroundTitle"), bColorPicker, t -> _handler.setBackgroundColor(bColorPicker.getValue()));
		setupImagePicker();
		setupPalletteView();
		setupTurtleAttributesView();
	}

	private void setupTurtleAttributesView() {
		_turtleAttributesView = new UIAttributesView(ATTRIBUTES_FRAME);
		this.getChildren().add(_turtleAttributesView);
	}

	private void setupPalletteView() {
		_paletteView = new PalletteView(PALLETTE_FRAME, _resources);
		this.getChildren().add(_paletteView);
	}

	private void setupColorPicker(Frame frame, String text, ColorPicker colorPicker, EventHandler<ActionEvent> event) {
		
		
		colorPicker.setLayoutX(12);
		colorPicker.setLayoutY(0);
		colorPicker.setPrefWidth(48);
		colorPicker.setPrefHeight(frame.getHeight());
		colorPicker.setOnAction(event);
		colorPicker.setBackground(
				new Background(new BackgroundFill[] { new BackgroundFill(MyColors.GREEN_100, null, null) }));
		Label l = GUITools.plainLabel(text, 14, Color.BLACK, FontWeight.THIN);
		l.setLayoutX(64);
		l.setAlignment(Pos.CENTER_LEFT);
		l.setPrefHeight(frame.getHeight());
		
		UIView container = new UIView(frame);
		GUITools.addBackgroundWithColor(container, MyColors.GREEN_100, IMAGE_FRAME.toLocalBounds());
		container.getChildren().add(colorPicker);
		container.getChildren().add(l);
		
		this.getChildren().add(container);
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
		
		Label text =  GUITools.plainLabel(_resources.getString("TurtleImageTitle"), 14, Color.BLACK, FontWeight.LIGHT);//TODO
		text.setLayoutX(64);
		text.setPrefHeight(IMAGE_FRAME.getHeight());
		text.setAlignment(Pos.CENTER_LEFT);
		imagePicker.getChildren().add(text);
		
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
		b.setLayoutX(getBounds().getWidth() - 64);
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

	public void slideMenuOut() {
		TranslateTransition t = new TranslateTransition();
		t.setNode(this);
		t.setDuration(Duration.millis(500));
		t.setToX(-getBounds().getWidth());
		t.play();
	}

	public void updateTurtleState(TurtleState s) {
		_turtleAttributesView.update(s);
	}
	public PalletteView getPaletteView(){
		return _paletteView;
	}
	public UIAttributesView getAttributesView(){
		return this._turtleAttributesView;
	} 
}
