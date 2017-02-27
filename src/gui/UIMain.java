package gui;

import java.util.ArrayList;

import controller.ControlHandler;
import general_data_structures.UserVariables;
import general_data_structures.Vocabulary;
import gui.API.UIMainAPI;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class UIMain implements UIMainAPI {
	
	//define the location of UI Components here
	public static final double SCREEN_WIDTH = 700;
	public static final double SCREEN_HEIGHT = 500;
	public static final double TOP_INSET = 50;
	public static final Frame TERMINAL_FRAME = new Frame(8, SCREEN_HEIGHT*2/3, SCREEN_WIDTH*2/3 - 16 - 50, SCREEN_HEIGHT/3 - 16);
	public static final Frame BUTTONS_FRAME = new Frame(TERMINAL_FRAME.getMaxX(), TERMINAL_FRAME.getY(), 50, TERMINAL_FRAME.getHeight());
	public static final Frame DISPLAY_FRAME = new Frame(8,TOP_INSET, SCREEN_HEIGHT*2/3 - 16 - TOP_INSET,SCREEN_HEIGHT*2/3 - 16);
	public static final Frame VARS_FRAME = new Frame(DISPLAY_FRAME.getMaxX() + 8,TOP_INSET, SCREEN_HEIGHT/3 - 16,SCREEN_HEIGHT*3 - 16);
	public static final Frame VOCAB_FRAME = new Frame(DISPLAY_FRAME.getMaxX() + 8,VARS_FRAME.getMaxY() + 8,SCREEN_HEIGHT*2/3 - 16 - TOP_INSET,SCREEN_HEIGHT/3 - 16);
	
	Pane _root;
	Scene _scene;
	ControlHandler _handler;
	UITerminalView _terminalView;
	UITurtleDisplayView _displayView;
	UIVariablesView _varBoxView;
	UIVocabTable _vocabTableView;
	ArrayList<UITurtle> _turtlesOnDisplay = new ArrayList<UITurtle>();
	
	public UIMain(ControlHandler handler){
		super();
		this._handler = handler;
		setupViews();
	}
	
	@Override
	public void displayErrorWithMessage(String error){
		Alert alert = new Alert(AlertType.CONFIRMATION, error, ButtonType.CLOSE);
		alert.showAndWait();
	}

	@Override
	public void updateVocabBox(Vocabulary vocab) {
		_vocabTableView.update(vocab);
	}
	
	@Override
	public void updateVarBox(UserVariables vars) {
		_varBoxView.update(vars);
	}
	
	@Override
	public void clearScreen() {
		// TODO Auto-generated method stub
		
	}
	

	private void setupViews(){
		setupRoot();
		setupTerminal();
		setupDisplay();
		setupVocabTable();
		setupVarsBox();
		setupTerminalButtons();
		setupTurtleImagePicker();
	}
	
	private void setupRoot(){
		_root = new Pane();
		_root.backgroundProperty().set(GUITools.getBackgroundWithColor(Color.GRAY));
		_scene = new Scene(_root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE);
	}
	private void setupTerminal(){
		_terminalView = new UITerminalView(TERMINAL_FRAME.toLocalBounds());
		_terminalView.setLayoutX(TERMINAL_FRAME.getX());
		_terminalView.setLayoutY(TERMINAL_FRAME.getY());
		_terminalView.prefHeight(TERMINAL_FRAME.getHeight());
		_terminalView.prefWidth(TERMINAL_FRAME.getWidth());
		_root.getChildren().add(_terminalView);
	}
	private void setupDisplay(){
		_displayView = new UITurtleDisplayView(DISPLAY_FRAME.toLocalBounds());
		_displayView.setLayoutX(DISPLAY_FRAME.getX());
		_displayView.setLayoutY(DISPLAY_FRAME.getY());
		_displayView.prefHeight(DISPLAY_FRAME.getHeight());
		_displayView.prefWidth(DISPLAY_FRAME.getWidth());
		_root.getChildren().add(_displayView);
	}
	private void setupVocabTable(){
		_vocabTableView = new UIVocabTable(VOCAB_FRAME);
		_vocabTableView.setLayoutX(VARS_FRAME.getX());
		_vocabTableView.setLayoutY(VARS_FRAME.getMaxY());
		_vocabTableView.prefHeight(VARS_FRAME.getHeight());
		_vocabTableView.prefWidth(VARS_FRAME.getWidth());
		_root.getChildren().add(_vocabTableView);
	}
	private void setupVarsBox(){
		_varBoxView = new UIVariablesView(VARS_FRAME);
		_varBoxView.setLayoutX(VARS_FRAME.getX());
		_varBoxView.setLayoutY(VARS_FRAME.getY());
		_varBoxView.prefHeight(VARS_FRAME.getHeight());
		_varBoxView.prefWidth(VARS_FRAME.getWidth());
		_root.getChildren().add(_varBoxView);
	}
	private void setupTerminalButtons(){
		Button exec = new Button("Execute"); //TODO add image
		exec.setLayoutX(BUTTONS_FRAME.getX());
		exec.setLayoutY(BUTTONS_FRAME.getY());
		exec.setPrefWidth(BUTTONS_FRAME.getWidth());
		exec.setPrefHeight(BUTTONS_FRAME.getHeight()/2);
		exec.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				didPressExecute();
			}
		});
		
		Button reset = new Button("Reset");
		reset.setLayoutX(BUTTONS_FRAME.getX());
		reset.setLayoutY(BUTTONS_FRAME.getY() + BUTTONS_FRAME.getHeight()/2);
		reset.setPrefWidth(BUTTONS_FRAME.getWidth());
		reset.setPrefHeight(BUTTONS_FRAME.getHeight()/2);
		reset.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				didPressReset();
			}
		});
		
		_root.getChildren().add(exec);
		_root.getChildren().add(reset);
	}
	
	private void setupTurtleImagePicker(){
		//TODO
	}

	private void setTurtleImage(){
		//TODO
	}
	
	private void didPressExecute(){
		_handler.handleTextInput(_terminalView.getTextInput());
	}
	
	private void didPressReset(){
		_handler.handleReset();
	}
	
	public Scene getScene(){
		return _scene;
	}
}
