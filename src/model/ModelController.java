package model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.Turtle;
import model.turtle.TurtleState;

public class ModelController {
	//List<Turtle> turtles;  
	
	Turtle myTurtle;
	
	public ModelController(){
		//turtles = new ArrayList<Turtle>();

		// GUI can hand these to controller and controller can hand them down if needed -  x and y should be based on GUI size 
		
		myTurtle = new Turtle();
		
		//turtles.add(firstTurtle);
									
	}
	
	public Turtle getTurtle(){ 
		return myTurtle; 
	}

	public void update(TreeNode commandToExecute){	
		if (commandToExecute instanceof TurtleCommand){
			((TurtleCommand) commandToExecute).execute(myTurtle);
		}
	}
		
		
	/* for multiple turtle extension
	public void update(Map<Integer,TreeNode> commandMap){
		for (Integer turtleIndex: commandMap.keySet()){
			TreeNode commandNode = commandMap.get(turtleIndex); 
			if (commandNode instanceof TurtleCommand){
				((TurtleCommand) commandNode).execute(turtles.get(turtleIndex));
			}
		}
	}
	 */
	
}
