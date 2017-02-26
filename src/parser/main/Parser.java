package parser.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
	
	public Parser(){
	}
	
	private void initialize(){
		TLG = new TokenListGenerator(str);
		IT = new Interpreter(TLG.getList());
		TL = IT.getTokenList();
		TG = new TreeGenerator(TL);
	}
	
	public TokenList getTokenList(){
		return new TokenList(TL.getLiterals(), TL.getLogo());
	}
	
	public Queue<TreeNode> getTreeQueue(){
		return new LinkedList<>(TG.getQueue());
	}
	
	public List<TreeNode> getOrderedTreeList(){
		List<TreeNode> list = new ArrayList<>();
		Queue<TreeNode> copy = new LinkedList<>(TG.getQueue());;
		while(!copy.isEmpty()){
			list.add(0, copy.remove());
		}
		return list;
	}
	
	public void parse(String toParse){		
		str = toParse;
		initialize();
	}
}
