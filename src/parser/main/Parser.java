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
	private String language;
	
	public Parser(State state, String language){
		this.state = state;
		this.language = language;
		vars = new VariableStorage();
	}
	
	private void handleLogic(){
		IT = new Interpreter(TLG.getList(), state);
		TL = IT.getTokenList();
		TG = new TreeGenerator(TL, state);
		QS = new QueueSplitter(TG.getCommandQueue());
	}
	
	private void handleVars(){
		FixVars FV = new FixVars(vars, TL);
	}
	
	private void generateTokens(){
		TLG = new TokenListGenerator(str, language);
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
		handleVars();
		handleLogic();
	}
}
