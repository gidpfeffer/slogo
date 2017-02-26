package gui.API;

import java.util.List;

public interface UIMainAPI {
	
	public abstract void displayErrorWithMessage(String e);
	public abstract void sendTextOutput(String output);
	public abstract void updateVocabBox(List<String> words);
	public abstract void updateVarBox(List<String> variables);
	
}
