package gui;

import java.util.ResourceBundle;

import controller.ControlHandler;
import general_data_structures.Tuple;
import gui.API.ButtonControlHandler;
import gui.API.UIMainAPI;
import gui.API.UIMainHandler;
import gui.API.UITurtleHandler;
import gui.menu.UIMenuView;
import gui.tableviews.UIVariablesView;
import gui.tableviews.UIVocabTable;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.ImageButton;
import gui.tools.MyColors;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
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

public class UIMain implements UIMainAPI {
	
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
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
	public static final Frame MENU_FRAME = new Frame(-SCREEN_WIDTH * 2 / 5, 0, 
			SCREEN_WIDTH * 2 / 5, SCREEN_HEIGHT);
	public static final Frame BTN_CONTROL_FRAME = new Frame(100, 8, 
			SCREEN_WIDTH-112, DISPLAY_FRAME.getMaxY() - 16);
	
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
	private UIButtonControlView _buttonControlView;
	private ResourceBundle _resources;

	public UIMain(ControlHandler handler, String language) {
		super();
		_handler = handler;
		_resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		setupViews();
	}

	public class myHandler implements UIMainHandler {
		@Override
		public void setLineColor(Color color) {
			_displayView.setPenColor(color);
		}

		@Override
		public void addFunctionToTerminal(String s) {
			_terminalView.addText(s);
		}

		@Override
		public void setTurtleImage(Image image) {
			
			_displayView.setTurtleImage(image);
		}

	}
	
	public class ButtonHandler implements ButtonControlHandler{

		@Override
		public void handlePause() {
			_displayView.pause();
		}

		@Override
		public void handlePlay() {
			_displayView.play();
		}

		@Override
		public void handleStop() {
			_displayView.stop();
		}

		@Override
		public void handleNewWorkspace() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class TurtleHandler implements UITurtleHandler{

		@Override
		public void turtleStateDidChange(TurtleState s) {
			turtleStateChanged(s);
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
	@Override
	public UITurtle addTurtle(double id) {
		UITurtle t = _displayView.addTurtle(id);
		t.boundsInParentProperty().addListener( e -> {
			Tuple<Double, Bounds> state = new Tuple<Double, Bounds>(t.getRotate(), t.getBoundsInParent());
			turtleStateChanged(GUITools.guiTurtleToTurtleState(state, _displayView.getBounds()));
		});
		return t;
	}

	@Override
	public void addNewOutput(String output) {
		_historyView.addNewCommand(" > " + output);
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
		setupButtonControlsView();
	}

	private void setupRoot() {
		_root = new Pane();
		_root.backgroundProperty().set(GUITools.getBackgroundWithColor(MyColors.GREEN));
		_scene = new Scene(_root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE);
	}

	private void setupTitleAndMenuButton() {
		Frame frame = new Frame(10,20,32,32);
		_menuButton = GUITools.makeButton(new Image("menu.png"), frame, new EventHandler<MouseEvent>() {
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
		_menuView = new UIMenuView(MENU_FRAME, new myHandler(), _resources);
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
		_displayView = new UITurtleDisplayView(DISPLAY_FRAME);
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
	
	private void setupButtonControlsView(){
		_buttonControlView = new UIButtonControlView(BTN_CONTROL_FRAME, new ButtonHandler());
		_root.getChildren().add(_buttonControlView);
	}

	private void setupTerminalButtons() {
		Frame exeFrame = new Frame(
				TERMINAL_FRAME.getMaxX() + 4,
				TERMINAL_FRAME.getY() + (TERMINAL_FRAME.getHeight() - 32) / 2,
				32, 32);
		ImageButton exec = GUITools.makeButton(new Image("execute.png"), exeFrame, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				didPressExecute();
			}
		});

		Frame resFrame = new Frame(
				HISTORY_FRAME.getMaxX() + 4,
				HISTORY_FRAME.getY() + (HISTORY_FRAME.getHeight() - 32) / 2,
				32,32);
		ImageButton reset = GUITools.makeButton(new Image("reset.png"), resFrame, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				didPressReset();
			}
		});

		_root.getChildren().add(exec);
		_root.getChildren().add(reset);
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
	
	private void turtleStateChanged(TurtleState s){
		_menuView.updateTurtleState(s);
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
	public void setPalleteAtIndex(double index, double r, double g, double b) {
		_menuView.getPaletteView().addNewPallete(index, Color.rgb((int)r, (int)g, (int)b));
	}

	@Override
	public void setPenColor(double index) {
		Color c = _menuView.getPaletteView().getPalette(index);
		_displayView.setPenColor(c);
	}

	@Override
	public void setBackgroundColor(double index) {
		Color c = _menuView.getPaletteView().getPalette(index);
		_displayView.setBackgroundColor(c);
	}

	@Override
	public void setPenStrokeWidth(double width) {
		_displayView.setPenWidth(width);
	}
	
	public UITurtle getTurtle(){
		return _displayView.getTurtle();
	}
	public UITurtle getTurtle(Double id){
		return _displayView.getTurtle(id);
	}
	
}
