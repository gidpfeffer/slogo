package parser.storage;

import java.util.Arrays;


import parser.tokenizer.TokenListGenerator;

public class CommandTester {
	
	public static void main(String args[]){
		String language = "resources/languages/English";
		
		CommandStorage CS = new CommandStorage();
		
		FunctionObj FO = new FunctionObj("Test", Arrays.asList(new String[] {":x", ":y"}));
		TokenListGenerator TLG = new TokenListGenerator("test", language);
		CS.setValue(FO, TLG.getList());
		
		System.out.println(CS.getMap().containsKey(FO));
		
		FO = new FunctionObj("Test", Arrays.asList(new String[] {":z", ":y"}));
		TLG = new TokenListGenerator("testing", language);
		
		System.out.println(CS.getMap().containsKey(FO));

	}
	
}
