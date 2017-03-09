package parser.helpers;

import java.util.ArrayList;
import java.util.Queue;

import model.command.TreeNode;
import model.turtle.State;
import parser.tokenizer.TokenList;

public class forRange extends RangeHandler{

	public forRange(){
	}

	@Override
	public void handle(State t, TokenList TL) {
		list = new ArrayList<>();
		Queue<TreeNode> q = getQueue(t, TL);
		makeList(q.remove().getValue(), q.remove().getValue(), q.remove().getValue());	
	}
}
