package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import controller.BackEndHandler;
import controller.Controller.myHandler;
import model.command.Command;
import model.command.TreeNode;
import model.movement.ClearScreen;
import model.turtle.State;
import model.turtle.Turtle;

public class ModelController {
	
	ArrayList<Turtle> turtles; 	
	ArrayList<State> turtleStates;

	//Turtle myTurtle;
	String output; 
	BackEndHandler handler; 

	public ModelController(BackEndHandler myHandler){

		// GUI can hand these to controller and controller can hand them down if needed -  x and y should be based on GUI size 
		
		//myTurtle = new Turtle();
		turtles = new ArrayList<Turtle>();
		turtles.add(new Turtle(1,0,0,0,true,true));

		handler = myHandler; 
		
		turtleStates = new ArrayList<State>(); 
		
	}


	public List<Turtle> getTurtles(){
		return turtles; 
	}

//	public Turtle getTurtle(){ 
//		return myTurtle; 
//	}


	public void update(Queue<TreeNode> commandsToExecute){
		output = "";

		while(!commandsToExecute.isEmpty()){ 
			// order of these calls matters!!
			TreeNode command= commandsToExecute.remove();
			if (command instanceof ClearScreen){
				handler.handleReset(); 
			}
		/*	if (command instanceof DisplayCommand){
				
			}
*/
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
		turtles.add(new Turtle(id,0,0,0,true,true));
	}

}
