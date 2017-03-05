package gui.API;

import general_data_structures.*;

public interface UIMainAPI {
	
	public abstract void displayErrorWithMessage(String e);
	public abstract void clearScreen();
	public abstract void addNewOutput(String output);
	public abstract void addTurtle();
	public abstract void setPalleteAtIndex(double index, double red, double blue, double green);
	public abstract void setPenColor(double index);
	public abstract void setBackgroundColor(double index);
	public abstract void setPenWidth(double width);
	
}
