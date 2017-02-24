package parser.interpreter;

import parser.tokenizer.TokenList;

public class Interpreter {
	private TokenList tokens;

	public Interpreter(TokenList TL) {
		tokens = TL;
		checkValidity();
		interpret();
	}
	
	private void checkValidity(){
		if(tokens.getLiterals().size() == tokens.getLogo().size()) return;
		throw new IllegalStateException("Invalid Token List");
	}
	
	private void interpret(){
		handleLoops();
	}
	
	private void handleLoops(){
		LoopHandler lh = new LoopEdit(tokens);
	}
	
	public TokenList getTokenList(){
		return tokens;
	}
}
