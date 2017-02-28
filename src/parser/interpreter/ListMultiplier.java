package parser.interpreter;

import java.util.ArrayList;
import java.util.List;

import parser.tokenizer.TokenList;

public class ListMultiplier {
	
	public ListMultiplier(){
		
	}
	
	public List<String> multiplyList(List<String> toMultiply, int times){
		List<String> multiplied = new ArrayList<>();
		for(int i = 0; i < times; i++){
			multiplied.addAll(toMultiply);
		}
		return multiplied;
	}
	
	public void replace(int start, int end, List<String> toFill, List<String> filler){
		if(start != end){
			for(int i = start; i <= end; i++){
				toFill.remove(start);
			}
		}
		toFill.addAll(start, filler);
	}
	
	public void replace(int start, int end, TokenList list, List<String> filler){
		replace(start, end, list.getLiterals(), filler);
		replace(start, end, list.getLogo(), filler);
	}
	
	public void replace(int start, int end, TokenList list, TokenList filler){
		replace(start, end, list.getLiterals(), filler.getLiterals());
		replace(start, end, list.getLogo(), filler.getLogo());
	}

}
