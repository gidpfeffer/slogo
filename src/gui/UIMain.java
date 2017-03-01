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
import gui.tableviews.UIVariablesView;
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
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.turtle.TurtleState;

public class UIMain implements UIMainAPI, Observer {
	
	//define the location of UI Components here
	public static final double SCREEN_WIDTH = 700;
	public static final double SCREEN_HEIGHT = 650;
	public static final double TOP_INSET = 70;
	public static final Frame DISPLAY_FRAME = new Frame(8,TOP_INSET, SCREEN_WIDTH*3/4 - 16,SCREEN_HEIGHT*2/3 - 16);
	public static final Frame HISTORY_FRAME = new Frame(8, DISPLAY_FRAME.getMaxY() + 8, DISPLAY_FRAME.getWidth() - 50, (SCREEN_HEIGHT - DISPLAY_FRAME.getMaxY())/2 - 16 );
	public static final Frame TERMINAL_FRAME = new Frame(8, HISTORY_FRAME.getMaxY() + 8, HISTORY_FRAME.getWidth(), HISTORY_FRAME.getHeight() );
	public static final Frame VOCAB_FRAME = new Frame(DISPLAY_FRAME.getMaxX() + 8,DISPLAY_FRAME.getY(), SCREEN_WIDTH - DISPLAY_FRAME.getMaxX() - 16,SCREEN_HEIGHT*2/3 - 16);
	public static final Frame VARS_FRAME = new Frame(DISPLAY_FRAME.getMaxX() + 8,VOCAB_FRAME.getMaxY() + 8, VOCAB_FRAME.getWidth(),SCREEN_HEIGHT - VOCAB_FRAME.getMaxY() - 16);
	public static final Frame MENU_FRAME = new Frame(-SCREEN_WIDTH*2/5, 0, SCREEN_WIDTH*2/5, SCREEN_HEIGHT);

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

	
	
	public UIMain(ControlHandler handler){
		super();
		_handler = handler;
		setupTurtleMap(1);
		setupViews();
	}
	
	@Override
	public void displayErrorWithMessage(String error){
		Alert alert = new Alert(AlertType.CONFIRMATION, error, ButtonType.CLOSE);
		alert.showAndWait();
	}
	
	@Override
	public void clearScreen() {
		System.out.println("clearing screen");
		_displayView.clearLines();
		_historyView.clear();
	}
	public void addTurtle(){
		TranslateTransition tran = new TranslateTransition();
		RotateTransition rot = new RotateTransition();
		Tuple<TranslateTransition, RotateTransition> animators = new Tuple<TranslateTransition, RotateTransition>(tran, rot);

		UITurtle t = new UITurtle(animators);
		
		tran.setNode(t);
		tran.setDuration(Duration.millis(200));
		
		rot.setNode(t);
		rot.setDuration(Duration.millis(200));
		
		_turtlesOnDisplay.add(t);
	}
	@Override
	public void addNewOutput(String output){
		_historyView.addNewCommand(output);
	}
	
	private void setupTurtleMap(double numberOfTurtles){
		_turtlesOnDisplay = new ArrayList<UITurtle>();
		for(int i = 0; i < numberOfTurtles; i++){
			addTurtle();
		}
	}

	private void setupViews(){
		setupRoot();
		setupTitleAndMenuButton();
		setupTerminal();
		setupHistory();
		setupDisplay();
		setupVocabTable();
		setupVarsBox();
		setupTerminalButtons();
		setupMenu();
		setupTurtleImagePicker();

	}
	
	private void setupRoot(){
		_root = new Pane();
		_root.backgroundProperty().set(GUITools.getBackgroundWithColor(MyColors.GREEN));
		_scene = new Scene(_root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE);
	}
	private void setupTitleAndMenuButton(){
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
		
		Label title = GUITools.plainLabelBoldHelvetica("SLOGO", 28, MyColors.LIGHT_GREEN);
		title.setLayoutX(64);
		title.setLayoutY(24);
		
		_root.getChildren().add(title);
		_root.getChildren().add(_menuButton);
	}
	private void setupMenu(){
		//TODO: refactor all of these by making abstract class that gets frame
		_menuView = new UIMenuView(MENU_FRAME.toLocalBounds());
		_menuView.setLayoutX(MENU_FRAME.getX());
		_menuView.setLayoutY(MENU_FRAME.getY());
		_menuView.setPrefWidth(MENU_FRAME.getWidth());
		_menuView.setPrefHeight(MENU_FRAME.getHeight());
		_root.getChildren().add(_menuView);
	}
	private void setupTerminal(){
		_terminalView = new UITerminalView(TERMINAL_FRAME.toLocalBounds());
		_terminalView.setLayoutX(TERMINAL_FRAME.getX());
		_terminalView.setLayoutY(TERMINAL_FRAME.getY());
		_terminalView.prefHeight(TERMINAL_FRAME.getHeight());
		_terminalView.prefWidth(TERMINAL_FRAME.getWidth());
		_root.getChildren().add(_terminalView);
	}
	private void setupHistory(){
		_historyView = new UIHistoryView(TERMINAL_FRAME.toLocalBounds());
		_historyView.setLayoutX(HISTORY_FRAME.getX());
		_historyView.setLayoutY(HISTORY_FRAME.getY());
		_historyView.prefHeight(HISTORY_FRAME.getHeight());
		_historyView.prefWidth(HISTORY_FRAME.getWidth());
		_root.getChildren().add(_historyView);
	}
	private void setupDisplay(){
		_displayView = new UITurtleDisplayView(DISPLAY_FRAME.toLocalBounds(), _turtlesOnDisplay);
		_displayView.setLayoutX(DISPLAY_FRAME.getX());
		_displayView.setLayoutY(DISPLAY_FRAME.getY());
		_displayView.prefHeight(DISPLAY_FRAME.getHeight());
		_displayView.prefWidth(DISPLAY_FRAME.getWidth());
		_root.getChildren().add(_displayView);
	}
	private void setupVocabTable(){
		_vocabTableView = new UIVocabTable(VOCAB_FRAME.toLocalBounds());
		_vocabTableView.setLayoutX(VOCAB_FRAME.getX());
		_vocabTableView.setLayoutY(VOCAB_FRAME.getY());
		_vocabTableView.prefHeight(VOCAB_FRAME.getHeight());
		_vocabTableView.prefWidth(VOCAB_FRAME.getWidth());
		_root.getChildren().add(_vocabTableView);
	}
	private void setupVarsBox(){
		_varBoxView = new UIVariablesView(VARS_FRAME.toLocalBounds());
		_varBoxView.setLayoutX(VARS_FRAME.getX());
		_varBoxView.setLayoutY(VARS_FRAME.getY());
		_varBoxView.prefHeight(VARS_FRAME.getHeight());
		_varBoxView.prefWidth(VARS_FRAME.getWidth());
		_root.getChildren().add(_varBoxView);
	}
	private void setupTerminalButtons(){
		ImageButton exec = new ImageButton();
		exec.updateImages(new Image("execute.png"), new Image("execute.png"));
		exec.setPrefSize(32,32);
		exec.setLayoutX(TERMINAL_FRAME.getMaxX() + 4);
		exec.setLayoutY(TERMINAL_FRAME.getY() + (TERMINAL_FRAME.getHeight() - exec.getPrefHeight())/2);
		exec.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				didPressExecute();
			}
		});
		
		ImageButton reset = new ImageButton();
		reset.updateImages(new Image("reset.png"), new Image("reset.png"));
		reset.setPrefSize(32,32);
		reset.setLayoutX(HISTORY_FRAME.getMaxX() + 4);
		reset.setLayoutY(HISTORY_FRAME.getY() + (HISTORY_FRAME.getHeight() - reset.getPrefHeight())/2);
		reset.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				didPressReset();
			}
		});
		
		_root.getChildren().add(exec);
		_root.getChildren().add(reset);
	}
	
	private void setupTurtleImagePicker(){
		//TODO add this to menuview
		ImageButton b = new ImageButton();
		
	}

	private void setTurtleImage(){
		//TODO
		
	}
	
	private void slideMenuIn(){
		
		TranslateTransition t = new TranslateTransition();
		t.setNode(_menuView);
		t.setDuration(Duration.millis(500));
		t.setToX(_menuView.getPrefWidth());
		t.play();
		
	}

	private void didPressExecute(){
		_historyView.addNewCommand(_terminalView.getTextInput());
		_handler.handleTextInput(_terminalView.getTextInput());
		_terminalView.clear();

	}
	
	private void didPressReset(){
		_handler.handleReset();
	}
	
	public Scene getScene(){
		return _scene;
	}
	
	public UIVariablesView getVariableView(){
		return _varBoxView;
	}
	
	public UIVocabTable getVocabTable(){
		return _vocabTableView;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		TurtleState newState = new TurtleState((TurtleState) o);
		UITurtle t = getTurtleFromListWithState(newState);
		_displayView.updateTurtleState(t, newState);
		//System.out.println("x: "+newState.getX() + " y: " + newState.getY()+" angle: " + newState.getHeadAngle()); // for testing

	}
	private UITurtle getTurtleFromListWithState(TurtleState s){
		for(UITurtle t: _turtlesOnDisplay){
			if(t.getTurtleState().equals(s)){
				return t;
			}
		}
		return _turtlesOnDisplay.get(0);
		//throw new RuntimeException("turtle not found");
	}
}
