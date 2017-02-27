package gui;

import java.util.ArrayList;
import java.util.List;

import controller.ControlHandler;
import general_data_structures.UserVariables;
import general_data_structures.Vocabulary;
import gui.API.UIMainAPI;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UIMain extends Application implements UIMainAPI {
	
	public static final double SCREEN_WIDTH = 700;
	public static final double SCREEN_HEIGHT = 500;
	public static final Frame TERMINAL_FRAME = new Frame(8, SCREEN_HEIGHT*2/3, SCREEN_WIDTH*2/3 - 16, SCREEN_HEIGHT/3 - 16);
	public static final Frame DISPLAY_FRAME = new Frame(8,8, SCREEN_HEIGHT*2/3 - 16,SCREEN_HEIGHT*2/3 - 16);
	public static final Frame VARS_FRAME = new Frame(DISPLAY_FRAME.getMaxX() + 8,8, SCREEN_HEIGHT/3 - 16,SCREEN_HEIGHT*3 - 16);
	public static final Frame VOCAB_FRAME = new Frame(DISPLAY_FRAME.getMaxX() + 8,VARS_FRAME.getMaxY() + 8,SCREEN_HEIGHT*2/3 - 16,SCREEN_HEIGHT/3 - 16);
	
	Group _root;
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
	public void updateTerminalWithNewLines(List<String> lines){
		_terminalView.addNewLines(lines);
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
		_root = new Group();
		_scene = new Scene(_root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE);
	}
	private void setupTerminal(){
		_terminalView = new UITerminalView(TERMINAL_FRAME.toLocalBounds(),_handler);
		_terminalView.setLayoutX(TERMINAL_FRAME.getX());
		_terminalView.setLayoutY(TERMINAL_FRAME.getY());
		_terminalView.prefHeight(TERMINAL_FRAME.getHeight());
		_terminalView.prefWidth(TERMINAL_FRAME.getWidth());
	}
	private void setupDisplay(){
		_displayView = new UITurtleDisplayView(DISPLAY_FRAME.toLocalBounds());
		_displayView.setLayoutX(DISPLAY_FRAME.getX());
		_displayView.setLayoutY(DISPLAY_FRAME.getY());
		_displayView.prefHeight(DISPLAY_FRAME.getHeight());
		_displayView.prefWidth(DISPLAY_FRAME.getWidth());
	}
	private void setupVocabTable(){
		_vocabTableView = new UIVocabTable(VOCAB_FRAME);
		_vocabTableView.setLayoutX(VARS_FRAME.getX());
		_vocabTableView.setLayoutY(VARS_FRAME.getMaxY());
		_vocabTableView.prefHeight(VARS_FRAME.getHeight());
		_vocabTableView.prefWidth(VARS_FRAME.getWidth());
	}
	private void setupVarsBox(){
		_varBoxView = new UIVariablesView(VARS_FRAME);
		_varBoxView.setLayoutX(VARS_FRAME.getX());
		_varBoxView.setLayoutY(VARS_FRAME.getY());
		_varBoxView.prefHeight(VARS_FRAME.getHeight());
		_varBoxView.prefWidth(VARS_FRAME.getWidth());
	}
	private void setupTerminalButtons(){
		//TODO
	}
	private void setupTurtleImagePicker(){
		//TODO
	}
	
	private void resetProgram(){
		_handler.handleReset();
	}
	private void clearTerminal(){
		//TODO
	}
	private void setTurtleImage(){
		//TODO
	}
	
	
	
	@Override
	public void start(Stage s) throws Exception {
		s.setScene(_scene);
		s.setTitle("SLOGO");
		s.show();
	}
	@Override
	public void clearScreen() {
		// TODO Auto-generated method stub
		
	}
}
