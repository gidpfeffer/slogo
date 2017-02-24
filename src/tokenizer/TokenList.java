package tokenizer;

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

}
