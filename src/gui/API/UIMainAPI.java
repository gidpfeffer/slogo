package gui.API;

import general_data_structures.*;
import gui.UITurtle;

public interface UIMainAPI {
	
	public abstract void displayErrorWithMessage(String e);
	public abstract void clearScreen();
	public abstract void addNewOutput(String output);
	public abstract UITurtle addTurtle(double id);
	public abstract void setPalleteAtIndex(double index, double red, double blue, double green);
	public abstract void setPenColor(double index);
	public abstract void setBackgroundColor(double index);
	public abstract void setPenStrokeWidth(double width);
	
}
