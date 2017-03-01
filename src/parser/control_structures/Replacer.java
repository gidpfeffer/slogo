package parser.control_structures;

import parser.tokenizer.TokenList;

public class Replacer {
	private static final String CONSTANT = "Constant";
	
	public Replacer(){
		
	}
	
	public void replace(TokenList TL, String key, int value){
		for(int i = 0; i < TL.getLiterals().size(); i++){
			String check = TL.getLiterals().get(i);
			if(check.contentEquals(key)){
				TL.getLiterals().set(i, "" + value);
				TL.getLogo().set(i, CONSTANT);
			}
		}
	}

}
