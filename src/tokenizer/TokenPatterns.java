package tokenizer;

import java.util.regex.Pattern;

import syntax_tokenizer.enums.SyntaxTypes;

public class TokenPatterns {
	Pattern pattern;
	String token;
	
	public TokenPatterns(Pattern p, String t){
		pattern = p;
		token = t;
	}

	public Pattern getPattern(){
		return pattern;
	}
	
	public String getTokenTypes(){
		return token;
	}
	
}
