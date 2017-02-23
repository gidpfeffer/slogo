package syntax_tokenizer;

import java.util.ArrayList;
import java.util.List;

public class TokenTest {

	public static void main(String args[]){
		String test = " repeat 50  [ fd 50 rt 90 BACK :distance Left :angle ]";
		
		Tokenizer t = new Tokenizer(test);
		List<String> literal = new ArrayList<>();
		List<String> logo = new ArrayList<>();
		
		while(!t.isEmpty()){
			TokenIdentifier TI = t.getToken();
			literal.add(TI.getToken());
			logo.add(TI.getType());
		}
		
		TokenList tokens = new TokenList(literal, logo);
		
		for(String s : tokens.getLogo()){
			System.out.println(s);
		}
	}
	
}
