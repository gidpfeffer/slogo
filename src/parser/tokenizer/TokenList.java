package parser.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class TokenList {
	List<String> literals;
	List<String> logo;
	
	public TokenList(List<String> literals, List<String> logo){
		if(literals.size() != logo.size()){
			throw new IllegalStateException("Invalid TokenList");
		}
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
	
	public void addAll(TokenList list, int loc){
		if(list.getLiterals().size() != list.getLogo().size()){
			throw new IllegalStateException("Invalid tokenList");
		}
		literals.addAll(loc, list.getLiterals());
		logo.addAll(loc, list.getLogo());
	}
	
	public int size(){
		return literals.size();
	}

}
