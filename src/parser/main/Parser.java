package parser.main;

import java.util.Queue;

import model.command.TreeNode;
import model.turtle.State;
import parser.interpreter.Interpreter;
import parser.queue_splitter.QueueSplitter;
import parser.reflectiontest.TreeGenerator;
import parser.storage.FixVars;
import parser.storage.VariableStorage;
import parser.tokenizer.TokenList;
import parser.tokenizer.TokenListGenerator;

public class Parser {
	private String str;
	private TokenListGenerator TLG;
	private TokenList TL;
	private Interpreter IT;
	private TreeGenerator TG;
	private State state;
	private VariableStorage vars;
	private QueueSplitter QS;
	
	public Parser(State state){
		this.state = state;
		vars = new VariableStorage();
	}
	
	private void initialize(){
		TLG = new TokenListGenerator(str);
		FixVars FV = new FixVars(vars, TLG.getList());
		IT = new Interpreter(TLG.getList(), state);
		TL = IT.getTokenList();
		TG = new TreeGenerator(TL, state);
		QS = new QueueSplitter(TG.getCommandQueue());
	}
	
	public TokenList getTokenList(){
		return new TokenList(TL.getLiterals(), TL.getLogo());
	}
	
	public Queue<TreeNode> getTreeQueue(){
		return QS.getQueue();
	}
	
	public VariableStorage getVars(){
		return vars;
	}

	public void parse(String toParse){		
		str = toParse;
		initialize();
	}
}
