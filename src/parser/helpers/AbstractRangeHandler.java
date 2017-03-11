package parser.helpers;

import java.util.List;
import java.util.Stack;

import controller.SLogoException;
import model.command.TreeNode;
import model.turtle.State;
import parser.interfaces.RangeHandler;
import parser.reflection.TreeGenerator;
import parser.tokenizer.TokenList;

public abstract class AbstractRangeHandler implements RangeHandler{
	protected List<Integer> list;
	
	public AbstractRangeHandler(){
		
	}
	
	public abstract void handleRange(State t, TokenList TL);
	
	protected Stack<TreeNode> getQueue(State t, TokenList TL){
		TreeGenerator TG = new TreeGenerator(TL, t);
		if(TG.getCommandStack().size() == 0) 
			throw new SLogoException("invalid syntax");
		return TG.getCommandStack();
	}
	
	protected void makeList(double increment, double end, double start){
		list.clear();
		for(double i = start; i <= end; i += increment){
			list.add((int) i);
		}
	}
	
	public List<Integer> getList(){
		return list;
	}
	
}
