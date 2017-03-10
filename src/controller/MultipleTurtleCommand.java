package controller;

import model.command.Command;

public abstract class MultipleTurtleCommand {
	public abstract AskTellData getData();
	public abstract void execute();

}
