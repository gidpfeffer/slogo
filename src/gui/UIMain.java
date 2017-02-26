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
	public static final double SCREEN_WIDTH = 500;
	public static final double SCREEN_HEIGHT = 500;
	
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
		double height = SCREEN_HEIGHT/3 - 16;
		double width = SCREEN_WIDTH*2/3 - 16;
		_terminalView = new UITerminalView();
		_terminalView.setLayoutX(8);
		_terminalView.setLayoutY(SCREEN_HEIGHT*2/3);
		_terminalView.prefHeight(height);
		_terminalView.prefWidth(width);
	}
	private void setupDisplay(){
		double height = SCREEN_HEIGHT*2/3 - 16;
		double width = SCREEN_WIDTH*2/3 - 16;
		_displayView = new UITurtleDisplayView();
		_displayView.setLayoutX(8);
		_displayView.setLayoutY(8);
		_displayView.prefHeight(height);
		_displayView.prefWidth(width);
	}
	private void setupVocabTable(){
		double height = SCREEN_HEIGHT*2/3 - 16;
		double width = SCREEN_WIDTH/3 - 16;
		_vocabTableView = new UIVocabTable();
		_vocabTableView.setLayoutX(SCREEN_WIDTH * 2 / 3 + 8);
		_vocabTableView.setLayoutY(SCREEN_HEIGHT / 3);
		_vocabTableView.prefHeight(height);
		_vocabTableView.prefWidth(width);
	}
	private void setupVarsBox(){
		double height = SCREEN_HEIGHT / 3 - 16;
		double width = SCREEN_WIDTH / 3 - 16;
		_varBoxView = new UIVariablesView();
		_varBoxView.setLayoutX(SCREEN_WIDTH * 2 / 3 + 8);
		_varBoxView.setLayoutY(8);
		_varBoxView.prefHeight(height);
		_varBoxView.prefWidth(width);
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
}
