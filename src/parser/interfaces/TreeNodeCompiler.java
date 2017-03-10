package parser.interfaces;

import java.util.List;
import java.util.Queue;

import model.command.TreeNode;
import model.turtle.State;
import parser.storage.VariableStorage;
import parser.tokenizer.ProtectedTokenList;

public interface TreeNodeCompiler {
	
	Queue<TreeNode> compile(State state, ProtectedTokenList list);
	
	VariableStorage getVars();
	
	List<String> getFunctionList();

}
