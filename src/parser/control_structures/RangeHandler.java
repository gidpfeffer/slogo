package parser.control_structures;

import java.util.ArrayList;
import java.util.List;

import controller.SLogoException;
import model.turtle.State;
import parser.reflectiontest.TreeGenerator;
import parser.tokenizer.TokenList;

public class RangeHandler {
	private TokenList TL;
	protected List<Integer> list;
	
	public RangeHandler(){
		list = new ArrayList<>();
	}
	
	public void handle(State t, TokenList TL){
		double end = getEnd(t, TL);
		makeList(end);	
	}
	
	private double getEnd(State t, TokenList TL){
		TreeGenerator TG = new TreeGenerator(TL, t);
		if(TG.getAllQueue().size() == 0) 
			throw new SLogoException("invalid if/else syntax");
		return TG.getAllQueue().remove().getValue();
	}
	
	private void makeList(double end){
		list.clear();
		for(int i = 1; i < end + 1; i++){
			list.add(i);
		}
	}
	
	public List<Integer> getList(){
		return list;
	}
	
}
