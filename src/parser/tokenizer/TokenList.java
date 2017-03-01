package parser.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class TokenList {
	List<String> literals;
	List<String> logo;
	
	public TokenList(List<String> literals, List<String> logo){
		this.literals = literals;
		this.logo = logo;
	}
	
	public List<String> getLiterals(){
		return literals;
	}
	
	public List<String> getLogo(){
		return logo;
	}
	
	public TokenList newSubList(int start, int end){
		return new TokenList(new ArrayList<>(literals.subList(start, end)),
				new ArrayList<>(logo.subList(start, end)));
	}
	
	public TokenList oldSubList(int start, int end){
		return new TokenList(literals.subList(start, end), logo.subList(start, end));
	}
	
	public void removeAll(int start, int end){
		for(int i = start; i <= end; i++){
			literals.remove(start);
			logo.remove(start);
		}
	}
	
	public int size(){
		return literals.size();
	}

}
