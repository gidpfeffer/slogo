package interpreter;

import java.util.List;
import java.util.Stack;

import tokenizer.TokenList;

public class LoopEdit extends LoopHandler{
	private static final String CONSTANT = "Constant";
	private List<String> literals, logo;
	private int startIndex, endIndex, times;
	private Stack<Integer> stack;
	private ListMultiplier listMultiplier;
	
	
	public LoopEdit(TokenList list){
		super(list);
		literals = list.getLiterals();
		logo = list.getLogo();
		listMultiplier = new ListMultiplier();
		stack = new Stack<>();
		correctList();
	}
	
	private void correctList(){
		while(!isDone()){
			handleLoop();
		}
	}
	
	private void handleLoop(){
		reset();
		findIndices();
		replace();
	}
	
	private void reset(){
		startIndex = endIndex = times = -1;
		stack.clear();
		checkValidRepeat();
	}
	
	private void findIndices(){
		boolean found = false;
		startIndex = getLocations(REPEAT).get(0) + 2;
		times = Integer.parseInt(literals.get(startIndex - 1));
		stack.push(startIndex);
		for(int i = startIndex + 1; i < literals.size(); i++){
			if(literals.get(i).equals(LEFT_BRACKET))
				stack.push(i);
			if(literals.get(i).equals(RIGHT_BRACKET)){
				stack.pop();
				if(stack.isEmpty()){
					endIndex = i;
					found = true;
					break;
				}
			}
		}
		if(!found){
			throw new IllegalStateException("Invalid Bracket Syntax");
		}
	}
	
	private void replace(){
		List<String> literalFiller = getSubList(literals, startIndex + 1, endIndex);
		List<String> logoFiller = getSubList(logo, startIndex + 1, endIndex);
		listMultiplier.replace(startIndex - 2, endIndex + 1, literals, literalFiller);
		listMultiplier.replace(startIndex - 2, endIndex + 1, logo, logoFiller);
	}
	
	private List<String> getSubList(List<String> list, int start, int end){
		return listMultiplier.multiplyList(
				list.subList(start, end), times);
	}
	
	private void checkValidRepeat(){
		int repeatIndex = getLocations(REPEAT).get(0);
		if(repeatIndex + 2 >= literals.size() ||
				!literals.get(repeatIndex + 2).equals(LEFT_BRACKET) ||
				!logo.get(repeatIndex + 1).equals(CONSTANT)){
			throw new IllegalStateException("Invalid Bracket Syntax");
		}
	}

}
