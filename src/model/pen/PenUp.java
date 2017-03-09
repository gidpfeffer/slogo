package model.pen;

import java.util.List;

import model.command.Command;
import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.State;
import model.turtle.TurtleState;

public class PenUp extends TurtleCommand implements Command{
	

		private State myTurtleState;

		public PenUp(List<TreeNode> args, State st){
			children = args;
			myTurtleState = st;
		}
		
		public void execute(){
			TurtleState st = (TurtleState) myTurtleState;
			st.setPen(false);;
		}
		
		public double getValue(){
			return 0.0;
		}
}
