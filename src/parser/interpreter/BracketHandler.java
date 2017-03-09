package parser.interpreter;
import java.util.ArrayList;
import java.util.List;
import controller.SLogoException;
import model.turtle.State;
import parser.tokenizer.TokenList;
public abstract class BracketHandler {
	protected TokenList list;
	protected String indicator;
	protected static final String LEFT_BRACKET 	= "[";
	protected static final String RIGHT_BRACKET = "]";
	
	public BracketHandler(String indicator){
		this.indicator = indicator;
	}
	
	protected void checkSyntax(){
		if(getLiteralLocations(LEFT_BRACKET).size() !=
				getLiteralLocations(RIGHT_BRACKET).size()){
			throw new SLogoException("Invalid Syntax, uneven number of brackets");
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
	
	protected abstract void handle(TokenList TL, State t);
}