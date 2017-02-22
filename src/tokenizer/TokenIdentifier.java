package tokenizer;

public class TokenIdentifier {
	
	private String token;
	private TokenTypes type;
	
	public TokenIdentifier(String token, TokenTypes type){
		this.token = token;
		this.type = type;
	}
	
	public String getToken(){
		return token;
	}
	
	public TokenTypes getType(){
		return type;
	}

}
