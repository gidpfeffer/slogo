package tokenizer;

public class CommentRemover {
	String str;
	public CommentRemover(String s){
		str = s;
		removeComments();
	}
	
	private void removeComments(){
		while(str.indexOf('#') >= 0){
			int comIndex = str.indexOf('#');
			int newLineIndex = str.indexOf("\n", comIndex);
			String start = str.substring(0, comIndex);
			str = start + str.substring(newLineIndex + 2);
		}
	}
	
	public String getString(){
		return str;
	}

}
