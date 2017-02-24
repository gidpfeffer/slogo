package parser.interpreter;

import java.util.ArrayList;
import java.util.List;

import parser.tokenizer.TokenList;

public abstract class LoopHandler {
	private TokenList list;
	protected static final String REPEAT = "repeat";
	protected static final String LEFT_BRACKET 	= "[";
	protected static final String RIGHT_BRACKET = "]";
	
	
	
	public LoopHandler(TokenList list){
		this.list = list;
		checkSyntax();
	}
	
	private void checkSyntax(){
		if(getLocations(LEFT_BRACKET).size() !=
				getLocations(RIGHT_BRACKET).size()){
			throw new IllegalStateException("Invalid Syntax, uneven number of brackets");
		}
	}
	
	protected boolean isDone(){
		return !list.getLiterals().contains(REPEAT);
	}
	
	protected List<Integer> getLocations(String str){
		List<Integer> indices = new ArrayList<>();
		for(int i = 0; i < list.getLiterals().size(); i++){
			if(list.getLiterals().get(i).equals(str)){
				indices.add(i);
			}
		}
		return indices;
	}
	
	public TokenList getList(){
		return list;
	}
	
}
