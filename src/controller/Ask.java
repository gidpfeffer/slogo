package controller;

import java.util.ArrayList;
import java.util.List;

public class Ask {
	private ArrayList<String> turtles; 
	private ArrayList<String> commands; 
	public Ask(ArrayList<String> t, ArrayList<String> c){
		turtles = t; 
		commands = c; 
	}
	public List<String> getTurtles(){
		return turtles; 
	}
	public List<String> getCommands(){
		return commands; 
	}
}
