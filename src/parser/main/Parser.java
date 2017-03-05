package parser.main;

import java.util.Queue;

import model.command.TreeNode;
import model.turtle.State;
import parser.interpreter.Interpreter;
import parser.queue_splitter.QueueSplitter;
import parser.reflection.TreeGenerator;
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
	private String language;
	private FixVars FV;
	
	public Parser(State state, String language){
		this.state = state;
		this.language = language;
		vars = new VariableStorage();
		FV = new FixVars(vars);
	}
	
	private void makeInterpreter(){
		IT = new Interpreter(TL, state, vars.keySet());
		TL = IT.getTokenList();
	}
	
	private void makeTree(){
		TG = new TreeGenerator(TL, state);
		QS = new QueueSplitter(TG.getCommandQueue());
	}
	
	private void handleVars(TokenList list){
		FV.fix(list);
	}
	
	private void generateTokens(){
		TLG = new TokenListGenerator(str, language);
		TL = TLG.getList();
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
		generateTokens();
		makeInterpreter();
		IT.handleVarLoops();
		handleVars(IT.getTokenList());
		IT.handleRegLoops();
		makeTree();
	}
}
