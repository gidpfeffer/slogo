package interpreter;

import java.util.ArrayList;
import java.util.List;

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
		for(int i = start; i < end; i++){
			toFill.remove(start);
		}
		toFill.addAll(start, filler);
	}

}
