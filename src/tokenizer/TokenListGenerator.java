package tokenizer;

import java.util.ArrayList;
import java.util.List;

public class TokenListGenerator {
	Tokenizer tokenizer;
	TokenList tokens;
	String input;
	String tokenizing;
	
	public TokenListGenerator(String toGenerate){
		input = toGenerate;
		tokenizing = new String(toGenerate);
		setup();
	}
	
	private void setup(){
		CommentRemover comment = new CommentRemover(tokenizing);
		tokenizer = new Tokenizer(comment.getString());
		generate();
		
	}
	
	public void generate(){
		List<String> literal = new ArrayList<>();
		List<String> logo = new ArrayList<>();
		
		while(!tokenizer.isEmpty()){
			TokenIdentifier TI = tokenizer.getToken();
			literal.add(TI.getToken());
			logo.add(TI.getType());
		}
		tokens = new TokenList(literal, logo);
	}
	
	public TokenList getList(){
		return tokens;
	}

}
