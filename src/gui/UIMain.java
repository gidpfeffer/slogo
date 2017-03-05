package gui;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import controller.ControlHandler;
import general_data_structures.Tuple;
import gui.API.UIMainAPI;
import gui.API.UIMainHandler;
import gui.menu.UIMenuView;
import gui.tableviews.UIVariablesView;
import gui.tableviews.UIVocabTable;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.ImageButton;
import gui.tools.MyColors;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import model.turtle.TurtleState;

public class UIMain implements UIMainAPI, Observer {

	public static final double SCREEN_WIDTH = 800;
	public static final double SCREEN_HEIGHT = 700;
	public static final double TOP_INSET = 70;
	public static final Frame DISPLAY_FRAME = new Frame(8, TOP_INSET, SCREEN_WIDTH * 0.7 - 16,
			SCREEN_HEIGHT * 2 / 3 - 16);
	public static final Frame HISTORY_FRAME = new Frame(8, DISPLAY_FRAME.getMaxY() + 8, DISPLAY_FRAME.getWidth() - 50,
			(SCREEN_HEIGHT - DISPLAY_FRAME.getMaxY()) / 2 - 16);
	public static final Frame TERMINAL_FRAME = new Frame(8, HISTORY_FRAME.getMaxY() + 8, HISTORY_FRAME.getWidth(),
			HISTORY_FRAME.getHeight());
	public static final Frame VOCAB_FRAME = new Frame(DISPLAY_FRAME.getMaxX() + 8, DISPLAY_FRAME.getY(),
			SCREEN_WIDTH - DISPLAY_FRAME.getMaxX() - 16, SCREEN_HEIGHT * 2 / 3 - 16);
	public static final Frame VARS_FRAME = new Frame(DISPLAY_FRAME.getMaxX() + 8, VOCAB_FRAME.getMaxY() + 8,
			VOCAB_FRAME.getWidth(), SCREEN_HEIGHT - VOCAB_FRAME.getMaxY() - 16);
	public static final Frame MENU_FRAME = new Frame(-SCREEN_WIDTH * 2 / 5, 0, SCREEN_WIDTH * 2 / 5, SCREEN_HEIGHT);

	private Pane _root;
	private Scene _scene;
	private ControlHandler _handler;
	private UITerminalView _terminalView;
	private UITurtleDisplayView _displayView;
	private UIVariablesView _varBoxView;
	private UIVocabTable _vocabTableView;
	private UIHistoryView _historyView;
	private UIMenuView _menuView;
	private ImageButton _menuButton;
	private List<UITurtle> _turtlesOnDisplay;

	public UIMain(ControlHandler handler) {
		super();
		_handler = handler;
		setupTurtleMap(1);
		setupViews();
	}

	public class myHandler implements UIMainHandler {
		@Override
		public void setLineColor(Color color) {
			for (UITurtle t : _turtlesOnDisplay) {
				UIMain.this.setLineColor(color, t);
			}
		}

		@Override
		public void addFunctionToTerminal(String s) {
			_terminalView.addText(s);
		}

		@Override
		public void setTurtleImage(Image image) {
			for (UITurtle t : _turtlesOnDisplay) {
				t.setImageView(new ImageView(image));
			}
		}

	}

	@Override
	public void displayErrorWithMessage(String error) {
		_historyView.addNewCommand("ERROR: " + error);
		Alert alert = new Alert(AlertType.CONFIRMATION, error, ButtonType.CLOSE);
		alert.showAndWait();
	}

	@Override
	public void clearScreen() {
		System.out.println("clearing screen");
		_displayView.resetDisplay();
		_historyView.clear();
		_terminalView.clear();
		// TODO: stop animation _displayView.stopAnimation();
		// also add pauseAnimation() and playAnimation()
	}

	public void addTurtle() {
		TranslateTransition tran = new TranslateTransition();
		RotateTransition rot = new RotateTransition();
		Tuple<TranslateTransition, RotateTransition> animators = new Tuple<TranslateTransition, RotateTransition>(tran,
				rot);

		UITurtle t = new UITurtle(animators, 0);

		tran.setNode(t);
		tran.setDuration(Duration.millis(200));

		rot.setNode(t);
		rot.setDuration(Duration.millis(200));

		_turtlesOnDisplay.add(t);
	}

	@Override
	public void addNewOutput(String output) {
		_historyView.addNewCommand(" > " + output);
	}

	private void setupTurtleMap(double numberOfTurtles) {
		_turtlesOnDisplay = new ArrayList<UITurtle>();
		for (int i = 0; i < numberOfTurtles; i++) {
			addTurtle();
		}
	}

	private void setupViews() {
		setupRoot();
		setupTitleAndMenuButton();
		setupTerminal();
		setupHistory();
		setupDisplay();
		setupVocabTable();
		setupVarsBox();
		setupTerminalButtons();
		setupMenu();
	}

	private void setupRoot() {
		_root = new Pane();
		_root.backgroundProperty().set(GUITools.getBackgroundWithColor(MyColors.GREEN));
		_scene = new Scene(_root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE);
	}

	private void setupTitleAndMenuButton() {
		_menuButton = new ImageButton();
		_menuButton.updateImages(new Image("menu.png"), new Image("menu.png"));
		_menuButton.setLayoutX(10);
		_menuButton.setLayoutY(20);
		_menuButton.setPrefSize(32, 32);
		_menuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				slideMenuIn();
			}
		});

		Label title = GUITools.plainLabel("SLOGO", 28, MyColors.GREEN_100, FontWeight.BLACK);
		title.setLayoutX(64);
		title.setLayoutY(24);

		_root.getChildren().add(title);
		_root.getChildren().add(_menuButton);
	}

	private void setupMenu() {
		_menuView = new UIMenuView(MENU_FRAME, new myHandler());
		_root.getChildren().add(_menuView);
	}

	private void setupTerminal() {
		_terminalView = new UITerminalView(TERMINAL_FRAME);
		_root.getChildren().add(_terminalView);
	}

	private void setupHistory() {
		_historyView = new UIHistoryView(HISTORY_FRAME);
		_root.getChildren().add(_historyView);
	}

	private void setupDisplay() {
		_displayView = new UITurtleDisplayView(DISPLAY_FRAME, _turtlesOnDisplay);
		_root.getChildren().add(_displayView);
	}

	private void setupVocabTable() {
		_vocabTableView = new UIVocabTable(VOCAB_FRAME, new myHandler());
		_root.getChildren().add(_vocabTableView);
	}

	private void setupVarsBox() {
		_varBoxView = new UIVariablesView(VARS_FRAME);
		_root.getChildren().add(_varBoxView);
	}

	private void setupTerminalButtons() {
		ImageButton exec = new ImageButton();
		exec.updateImages(new Image("execute.png"), new Image("execute.png"));
		exec.setPrefSize(32, 32);
		exec.setLayoutX(TERMINAL_FRAME.getMaxX() + 4);
		exec.setLayoutY(TERMINAL_FRAME.getY() + (TERMINAL_FRAME.getHeight() - exec.getPrefHeight()) / 2);
		exec.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				didPressExecute();
			}
		});

		ImageButton reset = new ImageButton();
		reset.updateImages(new Image("reset.png"), new Image("reset.png"));
		reset.setPrefSize(32, 32);
		reset.setLayoutX(HISTORY_FRAME.getMaxX() + 4);
		reset.setLayoutY(HISTORY_FRAME.getY() + (HISTORY_FRAME.getHeight() - reset.getPrefHeight()) / 2);
		reset.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				didPressReset();
			}
		});

		_root.getChildren().add(exec);
		_root.getChildren().add(reset);
	}

	private void setLineColor(Color color, UITurtle t) {
		t.setLineColor(color);
	}

	private void slideMenuIn() {

		TranslateTransition t = new TranslateTransition();
		t.setNode(_menuView);
		t.setDuration(Duration.millis(500));
		t.setToX(_menuView.getPrefWidth());
		t.play();

	}

	private void didPressExecute() {
		_historyView.addNewCommand(_terminalView.getTextInput());
		_handler.handleTextInput(_terminalView.getTextInput());
		_terminalView.clear();

	}

	private void didPressReset() {
		_handler.handleReset();
	}

	public Scene getScene() {
		return _scene;
	}

	public UIVariablesView getVariableView() {
		return _varBoxView;
	}

	public UIVocabTable getVocabTable() {
		return _vocabTableView;
	}

	@Override
	public void addNewPallete(double index, double red, double blue, double green) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPenColor(double index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBackgroundColor(double index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPenWidth(double width) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable o, Object arg) {

		TurtleState newState = new TurtleState((TurtleState) o);
		UITurtle t = getTurtleFromListWithState(newState);
		_displayView.updateTurtleState(t, newState);
		// System.out.println("x: "+newState.getX() + " y: " + newState.getY()+"
		// angle: " + newState.getHeadAngle()); // for testing

	}

	private UITurtle getTurtleFromListWithState(TurtleState s) {
		// for(UITurtle t: _turtlesOnDisplay){
		// if(t.getId() == s.getID()){
		// return t;
		// }
		// }
		return _turtlesOnDisplay.get(0);
		// throw new RuntimeException("turtle not found");
	}
}
