package parsing.simplifier;

import java.util.List;

import parsing.simplifier.repeathandler.RepeatHandler;

public class Simplifier {
	
	public Simplifier(List<String> literals, List<String> types){
		process(literals, types);
	}
	
	private void process(List<String> literals, List<String> types){
		System.out.println("Processing");
		printList(literals);
		printList(types);
		System.out.println(literals.size() == types.size());
		RepeatHandler r = new RepeatHandler(literals, types);
	}
	
	private void printList(List<String> list){
		for(String s: list){
			System.out.println(s);
		}
	}

}
