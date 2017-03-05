package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import controller.BackEndHandler;
import controller.Controller.myHandler;
import model.aesthetic.SetBackGround;
import model.aesthetic.SetPalette;
import model.command.Command;
import model.command.TreeNode;
import model.movement.ClearScreen;
import model.turtle.State;
import model.turtle.Turtle;

public class ModelController {
	
	ArrayList<Turtle> turtles; 	
	ArrayList<State> turtleStates;

	String output; 
	BackEndHandler handler; 

	public ModelController(BackEndHandler myHandler){

		// GUI can hand these to controller and controller can hand them down if needed -  x and y should be based on GUI size 
		
		turtles = new ArrayList<Turtle>();
		turtles.add(new Turtle());
		handler = myHandler; 
		turtleStates = new ArrayList<State>(); // may not even need this	
	}
	
	public List<Turtle> getTurtles(){
		return turtles; 
	}

	public void update(Queue<TreeNode> commandsToExecute){
		output = "";

		while(!commandsToExecute.isEmpty()){ 
			// order of these calls matters!!
			TreeNode command= commandsToExecute.remove();
			if (command instanceof ClearScreen){
				handler.handleReset(); 
			}
			
			if (command instanceof SetBackGround){
				handler.setBackground(command.getValue());
			}
			
			if (command instanceof SetPalette){
				handler.setPalette(command.getValue(),command.getChildren().get(1).getValue(),command.getChildren().get(2).getValue(),command.getChildren().get(3).getValue());
			}

			output = ((Double) command.getValue()).toString();
			((Command) command).execute();
		}
	}

	public String getStringOutput(){
		return output; 
	}

	public void reset() {
		for (Turtle t: turtles){
			t.reset();
		}
	}
	
	public void makeNewTurtle(double id){
		turtles.add(new Turtle(id));
	}
}
