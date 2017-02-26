package gui;

import java.util.ArrayList;
import java.util.List;

import controller.ControlHandler;
import gui.API.UIMainAPI;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
		
	}
	@Override
	public void sendTextOutput(String output){
		
	}
	@Override
	public void updateVocabBox(List<String> words) {
		
		
	}
	@Override
	public void updateVarBox(List<String> variables) {
		// TODO Auto-generated method stub
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
		//TODO
	}
	private void setupDisplay(){
		//TODO
	}
	private void setupVocabTable(){
		//TODO
	}
	private void setupVarsBox(){
		//TODO
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
