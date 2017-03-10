package parser.helpers;

import java.util.List;
import java.util.Queue;

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
	
	protected Queue<TreeNode> getQueue(State t, TokenList TL){
		TreeGenerator TG = new TreeGenerator(TL, t);
		if(TG.getAllQueue().size() == 0) 
			throw new SLogoException("invalid syntax");
		return TG.getCommandQueue();
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
