package parser.tests;

import java.util.Arrays;

import parser.storage.CommandStorage;
import parser.storage.FunctionObj;
import parser.tokenizer.TokenListGenerator;

public class CommandTester {
	
	public static void main(String args[]){
		String language = "resources/languages/English";
		
		CommandStorage CS = new CommandStorage();
		
		FunctionObj F1 = new FunctionObj("Test", Arrays.asList(new String[] {":x", ":y"}));
		TokenListGenerator TLG = new TokenListGenerator("test", language);
		CS.setValue(F1, TLG.getList());
		
		System.out.println(CS.getMap().containsKey(F1));
		
		FunctionObj F2 = new FunctionObj("Test", Arrays.asList(new String[] {":z", ":y"}));
		TLG = new TokenListGenerator("testing", language);
		CS.setValue(F2, TLG.getList());
		
		System.out.println(CS.getMap().containsKey(F2));
		System.out.println(CS.getMap().size());
	}
}
