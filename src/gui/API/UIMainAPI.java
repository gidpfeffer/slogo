package gui.API;

import general_data_structures.*;

public interface UIMainAPI {
	
	public abstract void displayErrorWithMessage(String e);
	public abstract void clearScreen();
	public abstract void addNewOutput(String output);
	public abstract void addTurtle();
}
