package parser.main;

import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenListGenerator;

public class NewParser {
	private String language;
	
	public NewParser(String language){
		this.language = language;
	}
	
	public ProtectedTokenList parse(String toParse){	
		TokenListGenerator TLG = new TokenListGenerator(toParse, language);
		return new ProtectedTokenList(TLG.getList());
	}
}
