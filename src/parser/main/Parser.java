package parser.main;

import java.util.Queue;

import model.command.TreeNode;
import model.turtle.TurtleState;
import parser.interpreter.FixVars;
import parser.interpreter.Interpreter;
import parser.queue_splitter.QueueSplitter;
import parser.reflectiontest.TreeGenerator;
import parser.storage.VariableStorage;
import parser.tokenizer.TokenList;
import parser.tokenizer.TokenListGenerator;

public class Parser {
	private String str;
	private TokenListGenerator TLG;
	private TokenList TL;
	private Interpreter IT;
	private TreeGenerator TG;
	private TurtleState t;
	private VariableStorage vars;
	private QueueSplitter QS;
	
	public Parser(TurtleState t){
		this.t = t;
		vars = new VariableStorage();
	}
	
	private void initialize(){
		TLG = new TokenListGenerator(str);
		FixVars FV = new FixVars(vars, TLG.getList());
		IT = new Interpreter(TLG.getList());
		TL = IT.getTokenList();
		TG = new TreeGenerator(TL, t);
		QS = new QueueSplitter(TG.getQueue());
	}
	
	public TokenList getTokenList(){
		return new TokenList(TL.getLiterals(), TL.getLogo());
	}
	
	public Queue<TreeNode> getTreeQueue(){
		return QS.getQueue();
	}

	
	public void parse(String toParse){		
		str = toParse;
		initialize();
	}
}
