package parser.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class TokenListGenerator {
	Tokenizer tokenizer;
	TokenList tokens;
	String tokenizing;
	
	public TokenListGenerator(String toGenerate, String language){
		tokenizing = new String(toGenerate);
		tokens = new TokenList(new ArrayList<>(), new ArrayList<>());
		setup(language);
	}
	
	private void setup(String language){
		CommentRemover comment = new CommentRemover(tokenizing);
		String withoutComments = comment.getString();
		if(withoutComments.equals("")) return;
		tokenizer = new Tokenizer(withoutComments, language);
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
