package parser.interpreter;

import java.util.Collections;

import controller.SLogoException;
import model.turtle.State;
import parser.control_structures.DoTimesHandler;
import parser.control_structures.ForHandler;
import parser.control_structures.IfElseHandler;
import parser.control_structures.IfHandler;
import parser.control_structures.RepeatHandler;
import parser.interfaces.BracketHandler;
import parser.storage.CommandHandler;
import parser.storage.TotalStorage;
import parser.tokenizer.TokenList;

public class Interpreter {
	private BracketHandler[] varLoops, regLoops;
	private TotalStorage storage;
	private CommandHandler commandHandler;

	public Interpreter(TotalStorage storage) {
		this.storage = storage;
		makeVarHandlers();
		makeRegHandlers();
	}
	
	private void makeVarHandlers(){
		varLoops = new BracketHandler[]
				{new DoTimesHandler(Collections.unmodifiableMap(storage.getVars().getMap())), 
				new ForHandler(Collections.unmodifiableMap(storage.getVars().getMap()))};
		commandHandler = new CommandHandler(storage.getCommands());
	}
	
	private void makeRegHandlers(){
		regLoops = new AbstractBracketHandler[] {new RepeatHandler(), new IfHandler(), new IfElseHandler()};
	}
	
	private void checkValidity(TokenList tokens){
		if(tokens.getLiterals().size() == tokens.getLogo().size()) return;
		throw new SLogoException("Invalid Token List");
	}
	
	public void handleVarLoops(TokenList TL, State state){
		checkValidity(TL);
		commandHandler.fix(TL);
		for(BracketHandler b: varLoops){
			b.handle(TL, state);
		}
	}
	
	public void handleRegLoops(TokenList TL, State state){
		checkValidity(TL);
		for(BracketHandler b: regLoops){
			b.handle(TL, state);
		}
	}
}
