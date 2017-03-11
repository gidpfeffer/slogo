package parser.helpers;

import java.util.ArrayList;
import java.util.Stack;

import model.command.TreeNode;
import model.turtle.State;
import parser.tokenizer.TokenList;

public class forRange extends AbstractRangeHandler{

	public forRange(){
	}

	@Override
	public void handleRange(State t, TokenList TL) {
		list = new ArrayList<>();
		Stack<TreeNode> q = getQueue(t, TL);
		makeList(q.pop().getValue(), q.pop().getValue(), q.pop().getValue());	
	}
}
