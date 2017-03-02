package parser.helpers;

import java.util.ArrayList;
import java.util.Queue;

import model.command.TreeNode;
import model.turtle.State;
import parser.tokenizer.TokenList;

public class doTimesRange extends RangeHandler{
	
	public doTimesRange(){
	}

	@Override
	public void handle(State t, TokenList TL) {
		list = new ArrayList();
		Queue<TreeNode> q = getQueue(t, TL);
		makeList(1, q.remove().getValue(), 1);	
	}

}
