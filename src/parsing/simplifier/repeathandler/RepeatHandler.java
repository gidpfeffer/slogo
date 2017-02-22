package parsing.simplifier.repeathandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RepeatHandler{
	private static final String LEFT = "[";
	private static final String RIGHT = "]";
	
	List<String> newLit;
	List<String> newType;
	
	public RepeatHandler(List<String> literals, List<String> types){
		newLit = new ArrayList<>();
		newType = new ArrayList<>();
		handle(literals, types);
	}
	
	private void handle(List<String> literals, List<String> types){
		int[] place = getIndicesofOuterBrackets(literals, types);
		System.out.println("Results of finding brackets:");
		System.out.format("Found %s at %d and %s at %d", literals.get(place[0]), place[0], literals.get(place[1]), place[1]);
	}
	
	private int[] getIndicesofOuterBrackets(List<String> literals, List<String> types){
		int[] place = new int[2];
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < literals.size(); i++){
			if(literals.get(i).equals(LEFT)){
				if(stack.isEmpty()) place[0] = i;
				stack.push(i);
			}
			if(literals.get(i).equals(RIGHT)){
				if(stack.size() == 1) place[1] = i;
				stack.pop();
			}
		}
		return place;
	}

}
