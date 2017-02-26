package gui.API;

import java.util.List;
import general_data_structures.*;

public interface UIMainAPI {
	
	public abstract void displayErrorWithMessage(String e);
	public abstract void updateVocabBox(Vocabulary words);
	public abstract void updateVarBox(UserVariables variables);
	public abstract void updateTerminalWithNewLines(List<String> lines);
	
}
