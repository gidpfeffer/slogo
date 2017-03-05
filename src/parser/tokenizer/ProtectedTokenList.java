package parser.tokenizer;

import java.util.Collections;
import java.util.List;

public class ProtectedTokenList {
	TokenList list;
	
	public ProtectedTokenList(TokenList list){
		if(list.getLogo().size() != list.getLiterals().size()){
			throw new IllegalStateException("Invalid TokenList");
		}
		this.list = list;
	}
	
	public List<String> getLiterals(){
		return Collections.unmodifiableList(list.getLiterals());
	}
	
	public List<String> getLogo(){
		return Collections.unmodifiableList(list.getLogo());
	}
	
	public void add(TokenList list, int start){
		if(list.getLiterals().size() != list.getLogo().size()){
			throw new IllegalStateException("Invalid TokenList");
		}
		this.list.getLiterals().addAll(start, list.getLiterals());
		this.list.getLogo().addAll(start, list.getLogo());	
	}
	
	public void add(TokenList list){
		if(list.getLiterals().size() != list.getLogo().size()){
			throw new IllegalStateException("Invalid TokenList");
		}
		this.list.getLiterals().addAll(list.getLiterals());
		this.list.getLogo().addAll(list.getLogo());	
	}
	
	public void remove(int start, int stop){
		for(int i = start; i < stop; i++){
			list.getLiterals().remove(start);
			list.getLogo().remove(start);
		}
	}
	
	public TokenList getTokenList(){
		return list;
	}
}
