package parser.interpreter;
import java.util.List;
import java.util.Stack;
import controller.SLogoException;
import model.turtle.State;
import parser.helpers.ListMultiplier;
import parser.tokenizer.TokenList;
public abstract class BracketAid extends BracketHandler{
	protected ListMultiplier listMultiplier;
	public BracketAid(TokenList list, String indicator) {
		super(list, indicator);
		listMultiplier = new ListMultiplier();
	}
	
	protected void correctList(){
		while(!isDone()){
			handle();
		}
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
		for(int i = startIndex + 1; i < literals.size(); i++){
			if(literals.get(i).equals(LEFT_BRACKET))
				stack.push(i);
			if(literals.get(i).equals(RIGHT_BRACKET)){
				stack.pop();
				if(stack.isEmpty()){
					return i;
				}
			}
		}
		throw new SLogoException("Invalid Bracket Syntax");
	}
	
	protected int findStartBracket(int start){
		for(int i = start; i < literals.size(); i++){
			if(literals.get(i).equals(LEFT_BRACKET)){
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
	
	private void printRemaining(){
		System.out.println("==========");
		for(String s: list.getLiterals()){
			System.out.println(s);
		}
		System.out.println("==========");
	}
	
	protected abstract void reset();
	
	protected abstract void findIndices();
	
	protected abstract void replace();
	
	protected abstract void checkValidity();
}