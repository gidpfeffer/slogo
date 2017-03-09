package model.command;

import controller.BackEndHandler;

public interface Command {
	public void execute(BackEndHandler myHandler);
}
