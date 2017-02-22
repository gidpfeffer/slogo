package parsing.errors;

public class InvalidSyntax extends Exception{
	private static final String INVALID = "Invalid Syntax";
	
	public InvalidSyntax(){
		super(INVALID);
	}

}
