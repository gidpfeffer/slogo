package parser.main;

import java.util.ArrayList;
import java.util.Queue;

import model.command.TreeNode;
import model.turtle.State;
import parser.interpreter.Interpreter;
import parser.queue_splitter.QueueSplitter;
import parser.reflection.TreeGenerator;
import parser.storage.FixVars;
import parser.storage.TotalStorage;
import parser.storage.VariableStorage;
import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenList;

public class Compiler {
	private TotalStorage storage;
	private FixVars fixVars;
	private Interpreter IT;

	public Compiler(){
		storage = new TotalStorage();
		fixVars = new FixVars(storage.getVars());
		IT = new Interpreter(storage);
	}
	
	private Queue<TreeNode> interpret(ProtectedTokenList list, State state){
		TokenList TL = new TokenList(new ArrayList<>(list.getLiterals()),
				new ArrayList<>(list.getLogo()));
		IT.handleVarLoops(TL, state);
		fixVars.fix(TL);
		System.out.print("+++");
		System.out.print(storage.getVars().getMap().size());
		System.out.print("+++");
		IT.handleRegLoops(TL, state);
		return makeTree(TL, state);
	}
	
	private Queue<TreeNode> makeTree(TokenList list, State state){
		TreeGenerator TG = new TreeGenerator(list, state);
		QueueSplitter QS = new QueueSplitter(TG.getCommandQueue());
		return QS.getQueue();
	}
	
	public Queue<TreeNode> compile(State state, ProtectedTokenList list){
		return interpret(list, state);
	}
	
	public VariableStorage getVars(){
		return storage.getVars();
	}
}
