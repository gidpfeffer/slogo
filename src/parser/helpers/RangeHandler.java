package parser.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import controller.SLogoException;
import model.command.TreeNode;
import model.turtle.State;
import parser.reflectiontest.TreeGenerator;
import parser.tokenizer.TokenList;

public abstract class RangeHandler {
	protected List<Integer> list;
	
	public RangeHandler(){
		
	}
	
	public abstract void handle(State t, TokenList TL);
	
	protected Queue<TreeNode> getQueue(State t, TokenList TL){
		TreeGenerator TG = new TreeGenerator(TL, t);
		if(TG.getAllQueue().size() == 0) 
			throw new SLogoException("invalid syntax");
		return TG.getAllQueue();
	}
	
	protected void makeList(double start, double end, double increment){
		list.clear();
		for(double i = start; i <= end; i += increment){
			System.out.println(i);
			list.add((int) i);
		}
	}
	
	public List<Integer> getList(){
		return list;
	}
	
}
