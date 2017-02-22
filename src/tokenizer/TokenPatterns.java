package tokenizer;

import java.util.regex.Pattern;

public class TokenPatterns {
	Pattern pattern;
	TokenTypes token;
	
	public TokenPatterns(Pattern p, TokenTypes t){
		pattern = p;
		token = t;
	}

	
	public Pattern getPattern(){
		return pattern;
	}
	
	public TokenTypes getTokenTypes(){
		return token;
	}
}
