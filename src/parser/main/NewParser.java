package parser.main;

import parser.interfaces.ProtectedTokenListParser;
import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenListGenerator;

public class NewParser implements ProtectedTokenListParser{
	private String language;
	
	public NewParser(String language){
		this.language = language;
	}
	
	public ProtectedTokenList parse(String toParse){	
		System.out.println("here");
		TokenListGenerator TLG = new TokenListGenerator(toParse, language);
		return new ProtectedTokenList(TLG.getList());
	}
}
