package parser.main;

import java.util.Queue;

import model.TreeNode;
import parser.interpreter.Interpreter;
import parser.reflectiontest.TreeGenerator;
import parser.tokenizer.TokenList;
import parser.tokenizer.TokenListGenerator;

public class Parser {
	private String str;
	private TokenListGenerator TLG;
	private TokenList TL;
	private Interpreter IT;
	private TreeGenerator TG;
	
	public Parser(String toParse){
		str = toParse;
		initialize();
	}
	
	private void initialize(){
		TLG = new TokenListGenerator(str);
		IT = new Interpreter(TLG.getList());
		TL = IT.getTokenList();
		TG = new TreeGenerator(TL);
	}
	
	public TokenList getTokenList(){
		return TL;
	}
	
	public Queue<TreeNode> getTreeList(){
		return TG.getQueue();
	}
}
