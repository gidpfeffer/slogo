package tokenizer;

public class TokenTest {

	public static void main(String args[]){
		String test = " repeat 50  [ fd 50 rt 90 BACK :distance Left :angle ]";
		
		Tokenizer t = new Tokenizer(test);
		
		while(!t.isEmpty()){
			TokenIdentifier TI = t.getToken();
			System.out.format("[%s , %s]\n", TI.getToken(), TI.getType().toString());
		}
	}
	
}
