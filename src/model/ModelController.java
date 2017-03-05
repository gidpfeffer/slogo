package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Queue;

import controller.BackEndHandler;

import model.aesthetic.SetBackGround;
import model.aesthetic.SetPalette;
import model.command.Command;
import model.command.TreeNode;
import model.movement.ClearScreen;
import model.turtle.Turtle;

public class ModelController {
	
	List<Turtle> myTurtles; 	

	String myOutput; 
	BackEndHandler myHandler; 

	public ModelController(BackEndHandler handler){	
		myTurtles = new ArrayList<Turtle>();
		myTurtles.add(new Turtle());
		myHandler = handler; 	
	}

	public void update(Queue<TreeNode> commandsToExecute){
		myOutput = "";

		while(!commandsToExecute.isEmpty()){ 

			TreeNode command= commandsToExecute.remove();
			if (command instanceof ClearScreen){
				myHandler.handleReset(); 
			}
			
			if (command instanceof SetBackGround){
				myHandler.setBackground(command.getValue());
			}
			
			if (command instanceof SetPalette){
				myHandler.setPalette(command.getValue(),command.getChildren().get(1).getValue(),command.getChildren().get(2).getValue(),command.getChildren().get(3).getValue());
			}

			myOutput = ((Double) command.getValue()).toString();
			((Command) command).execute();
		}
	}
	
	public List<Turtle> getTurtles(){
		return Collections.unmodifiableList(myTurtles);  // make this list unmodifiable to anyone except the model
	}
	
	public String getStringOutput(){
		return myOutput; 
	}

	public void reset() {
		for (Turtle t: myTurtles){
			t.reset();
		}
	}
	
	public void makeNewTurtle(double id){
		myTurtles.add(new Turtle(id));
	}
}
