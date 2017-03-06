package parser.tests;

import java.util.ArrayList;

import parser.main.NewParser;
import parser.storage.CommandHandler;
import parser.storage.CommandStorage;
import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenList;

public class CommandTester {
	
	public static void main(String args[]){
		String language = "resources/languages/English";
		String testCode = "to blah [ :x ] [ product :x 10 ]";
		String testCode2 = "blah 50";
		
		
		
		CommandStorage CS = new CommandStorage();
		
		CommandHandler CH = new CommandHandler(CS);
		
		NewParser p = new NewParser(language);
		
		ProtectedTokenList PTL = p.parse(testCode);
		
		TokenList TL = new TokenList(new ArrayList<>(PTL.getLiterals()),
				new ArrayList<>(PTL.getLogo()));
		
		CH.fix(TL);
		
		PTL = p.parse(testCode2);
		
		TL = new TokenList(new ArrayList<>(PTL.getLiterals()),
				new ArrayList<>(PTL.getLogo()));
		
		CH.fix(TL);
		
		for(int i = 0; i < TL.getLiterals().size(); i++){
			System.out.format("%s %s\n", TL.getLiterals().get(i), TL.getLogo().get(i));
		}
		
	}
}
