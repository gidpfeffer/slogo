package parser.interpreter;
import java.util.List;
import java.util.Stack;
import controller.SLogoException;
import model.turtle.State;
import parser.helpers.ListMultiplier;
import parser.tokenizer.TokenList;

public abstract class AbstractBracketAid extends AbstractBracketHandler{
	protected ListMultiplier listMultiplier;
	protected State turtle; 
	
	public AbstractBracketAid(String indicator) {
		super(indicator);
		listMultiplier = new ListMultiplier();
	}
	
	protected void correctList(){
		while(!isDone()){
			handle();
		}
	}
	
	public void handle(TokenList TL, State t){
		list = TL;
		turtle = t;
		checkSyntax();
		correctList();
	}
	
	protected List<String> getSubList(List<String> list, int start, int end, int times){
		return listMultiplier.multiplyList(
				list.subList(start, end), times);
	}
	
	protected List<String> getSubList(List<String> list, int start, int end){
		return listMultiplier.multiplyList(list.subList(start, end), 1);
	}
	
	protected int findEndBracket(int startIndex){
		Stack<Integer> stack = new Stack<>();
		stack.push(startIndex);
		for(int i = startIndex + 1; i < list.getLiterals().size(); i++){
			if(list.getLiterals().get(i).equals(LEFT_BRACKET))
				stack.push(i);
			if(list.getLiterals().get(i).equals(RIGHT_BRACKET)){
				stack.pop();
				if(stack.isEmpty()){
					return i;
				}
			}
		}
		throw new SLogoException("Invalid Bracket Syntax");
	}
	
	protected int findStartBracket(int start){
		for(int i = start; i < list.getLiterals().size(); i++){
			if(list.getLiterals().get(i).equals(LEFT_BRACKET)){
				return i;
			}
		}
		throw new SLogoException("Invalid Bracket Syntax");
	}
	
	protected void handle(){
		reset();
		findIndices();
		replace();
	}
	
	protected void checkValidity() {
		if(getLogoLocations(LEFT_BRACKET).size() != 
				getLogoLocations(RIGHT_BRACKET).size()){
			throw new IllegalArgumentException("Invalid bracket syntax");
		}
	}
	
	protected abstract void reset();
	
	protected abstract void findIndices();
	
	protected abstract void replace();
	
}