package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import controller.ControlHandler;
import general_data_structures.UserVariables;
import general_data_structures.Vocabulary;
import gui.API.UIMainAPI;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import model.turtle.TurtleState;

public class UIMain implements UIMainAPI, Observer {
	
	//define the location of UI Components here
	public static final double SCREEN_WIDTH = 700;
	public static final double SCREEN_HEIGHT = 650;
	public static final double TOP_INSET = 70;
	public static final Frame DISPLAY_FRAME = new Frame(8,TOP_INSET, SCREEN_WIDTH*3/4 - 16,SCREEN_HEIGHT*3/4 - 16);
	public static final Frame TERMINAL_FRAME = new Frame(8, DISPLAY_FRAME.getMaxY() + 8, DISPLAY_FRAME.getWidth() - 50, SCREEN_HEIGHT - DISPLAY_FRAME.getMaxY() - 16 );
	public static final Frame BUTTONS_FRAME = new Frame(TERMINAL_FRAME.getMaxX(), TERMINAL_FRAME.getY(), 50, TERMINAL_FRAME.getHeight());
	public static final Frame VOCAB_FRAME = new Frame(DISPLAY_FRAME.getMaxX() + 8,DISPLAY_FRAME.getY(), SCREEN_WIDTH - DISPLAY_FRAME.getMaxX() - 16,SCREEN_HEIGHT*2/3 - 16);
	public static final Frame VARS_FRAME = new Frame(DISPLAY_FRAME.getMaxX() + 8,VOCAB_FRAME.getMaxY() + 8, VOCAB_FRAME.getWidth(),SCREEN_HEIGHT - VOCAB_FRAME.getMaxY() - 16);
	
	Pane _root;
	Scene _scene;
	ControlHandler _handler;
	UITerminalView _terminalView;
	UITurtleDisplayView _displayView;
	UIVariablesView _varBoxView;
	UIVocabTable _vocabTableView;
	List<UITurtle> _turtlesOnDisplay;
	
	public UIMain(ControlHandler handler, List<UITurtle> turtles){
		super();
		_handler = handler;
		_turtlesOnDisplay = turtles;
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
		setupTitleAndMenuButton();
		setupMenu();
		setupTerminal();
		setupDisplay();
		setupVocabTable();
		setupVarsBox();
		setupTerminalButtons();
		setupTurtleImagePicker();
	}
	
	private void setupRoot(){
		_root = new Pane();
		_root.backgroundProperty().set(GUITools.getBackgroundWithColor(MyColors.GREEN));
		_scene = new Scene(_root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE);
	}
	private void setupTitleAndMenuButton(){
		
		
		ImageButton menu = new ImageButton();
		menu.updateImages(new Image("menu.png"), new Image("menu.png"));
		menu.setLayoutX(10);
		menu.setLayoutY(20);
		menu.setPrefSize(32, 32);
		
		Label title = GUITools.plainLabelBoldHelvetica("SLOGO", 28, MyColors.LIGHT_GREEN);
		title.setLayoutX(64);
		title.setLayoutY(24);
		
		_root.getChildren().add(title);
		_root.getChildren().add(menu);

	
	}
	private void setupMenu(){
		//TODO
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
		_displayView = new UITurtleDisplayView(DISPLAY_FRAME.toLocalBounds(), _turtlesOnDisplay);
		_displayView.setLayoutX(DISPLAY_FRAME.getX());
		_displayView.setLayoutY(DISPLAY_FRAME.getY());
		_displayView.prefHeight(DISPLAY_FRAME.getHeight());
		_displayView.prefWidth(DISPLAY_FRAME.getWidth());
		_root.getChildren().add(_displayView);
	}
	private void setupVocabTable(){
		System.out.println(VOCAB_FRAME);
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
		exec.setLayoutX(BUTTONS_FRAME.getX());
		exec.setLayoutY(BUTTONS_FRAME.getY() + 4);
		exec.setPrefSize(32,32);
		exec.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				didPressExecute();
			}
		});
		
		ImageButton reset = new ImageButton();
		reset.updateImages(new Image("reset.png"), new Image("reset.png"));
		reset.setLayoutX(BUTTONS_FRAME.getX());
		reset.setLayoutY(BUTTONS_FRAME.getY() + BUTTONS_FRAME.getHeight()/2);
		reset.setPrefSize(32,32);
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
		_terminalView.clear();

	}
	
	private void didPressReset(){
		_handler.handleReset();
	}
	
	public Scene getScene(){
		return _scene;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		UITurtle modifiedTurtle = getTurtleFromListWithState((TurtleState) o);
		System.out.println("turtle updated:\t" + modifiedTurtle.getTurtleState().getX());
	}
	private UITurtle getTurtleFromListWithState(TurtleState s){
		for(UITurtle t: this._turtlesOnDisplay){
			if(t.getTurtleState() == s){
				return t;
			}
		}
		throw new RuntimeException();
	}
}
