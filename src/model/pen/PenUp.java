package model.pen;

import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;

public class PenUp extends TurtleCommand{
	

		private TurtleState myTurtleState;

		public PenUp(List<TreeNode> args, TurtleState st){
			children = args;
			myTurtleState = st;
		}
		
		public void execute(){
			 
			myTurtleState.setPen(false);;
		}
		
		public double getValue(){
			return 0.0;
		}
}
