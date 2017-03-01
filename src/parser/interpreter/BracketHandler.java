package parser.interpreter;

import java.util.ArrayList;
import java.util.List;

import model.turtle.Turtle;
import model.turtle.State;
import parser.tokenizer.TokenList;

public abstract class BracketHandler {
	protected TokenList list;
	protected List<String> literals, logo;
	protected String indicator;
	protected static final String LEFT_BRACKET 	= "[";
	protected static final String RIGHT_BRACKET = "]";
	protected State turtle;
	
	public BracketHandler(TokenList list, String indicator, State t){
		this.indicator = indicator;
		this.list = list;
		this.turtle = t;
		literals = list.getLiterals();
		logo = list.getLogo();
		checkSyntax();
	}
	
	private void checkSyntax(){
		if(getLiteralLocations(LEFT_BRACKET).size() !=
				getLiteralLocations(RIGHT_BRACKET).size()){
			throw new IllegalStateException("Invalid Syntax, uneven number of brackets");
		}
	}
	
	protected boolean isDone(){
		return !list.getLogo().contains(indicator);
	}
	
	protected List<Integer> getLogoLocations(String str){
		List<Integer> indices = new ArrayList<>();
		for(int i = 0; i < list.getLogo().size(); i++){
			if(list.getLogo().get(i).equals(str)){
				indices.add(i);
			}
		}
		return indices;
	}
	
	protected List<Integer> getLiteralLocations(String str){
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
