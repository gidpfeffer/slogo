package parser.main;

import java.util.Queue;

import model.command.TreeNode;
import model.turtle.State;
import parser.interpreter.Interpreter;
import parser.queue_splitter.QueueSplitter;
import parser.reflection.TreeGenerator;
import parser.storage.FixVars;
import parser.storage.VariableStorage;
import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenList;

public class Compiler {
	private VariableStorage vars;

	public Compiler(){
		vars = new VariableStorage();
	}
	
	private Queue<TreeNode> interpret(ProtectedTokenList list, State state){
		Interpreter IT = new Interpreter(list.getTokenList(), state, vars.keySet());
		TokenList TL = IT.getTokenList();
		IT.handleVarLoops();
		FixVars FV = new FixVars(vars, TL);
		IT.handleRegLoops();
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
}
