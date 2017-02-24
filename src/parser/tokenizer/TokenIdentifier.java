package parser.tokenizer;

public class TokenIdentifier {
	
	private String token;
	private String type;
	
	public TokenIdentifier(String token, String type){
		this.token = token;
		this.type = type;
	}
	
	public String getToken(){
		return token;
	}
	
	public String getType(){
		return type;
	}

}
