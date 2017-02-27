package gui.API;

import general_data_structures.*;

public interface UIMainAPI {
	
	public abstract void displayErrorWithMessage(String e);
	public abstract void updateVocabBox(Vocabulary newFunction);
	public abstract void updateVarBox(UserVariables variables);
	public abstract void clearScreen();
	
}
