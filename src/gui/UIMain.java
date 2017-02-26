package gui;

import java.util.List;

import controller.ControlHandler;
import gui.API.UIMainAPI;

public class UIMain implements UIMainAPI {
	
	ControlHandler handler;
	UITerminalView terminal;
	UITurtleDisplayView display;
	UIVariablesView varBox;
	UIVocabTable vocabTable;
	
	public UIMain(ControlHandler handler){
		super();
		this.handler = handler;
		setupViews();
	}
	public void displayErrorWithMessage(String error){
		
	}
	public void sendTextOutput(String output){
		
	}
	public void updateVocabBox(List<String> words) {
		// TODO Auto-generated method stub
		
	}
	public void updateVarBox(List<String> variables) {
		// TODO Auto-generated method stub
		
	}
	
	private void setupViews(){
		
	}
	
	private void setupTerminalButtons(){
		//TODO
	}
	
	private void resetProgram(){
		this.handler.handleReset();
	}
	private void clearTerminal(){
		//TODO
	}
	private void setTurtleImage(){
		//TODO
	}
}
