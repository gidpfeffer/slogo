package parser.relectiontest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import model.TreeNode;
import parser.tokenizer.TokenList;

public class TreeGenerator {
	private TokenList list;
	private Stack<TreeNode> parsed;
	private Stack<String> commands, logo;
	private NumArgsHandler numArgs;
	private Queue<TreeNode> commandQueue;
	private static final String CONSTANT = "Constant";
	
	public TreeGenerator(TokenList t){
		list = t;
		initializeData();
		fillStack();
		generateTree();
	}
	
	private void initializeData(){
		parsed = new Stack<>();
		commands = new Stack<>();
		logo = new Stack<>();
		numArgs = new NumArgsHandler();
		commandQueue = new LinkedList<TreeNode>();
	}
	
	private void fillStack(){
		if(!commands.isEmpty()|!logo.isEmpty()){
			commands.clear();
			logo.clear();
		}
		commands.addAll(list.getLiterals());
		logo.addAll(list.getLogo());
	}
	
	private void generateTree(){
		while(!commands.isEmpty()){
			grow();
		}
	}
	
	private void grow(){
		String command = commands.pop();
		String logoType = logo.pop();
		int arguments = numArgs.getNumArgs(logoType);
		TreeNode t = makeNode(command, logoType, arguments);
		parsed.add(t);
		if(!logoType.equals("Constant")){
			commandQueue.add(t);
		}
	}
	
	private TreeNode makeNode(String command, String logo, int arguments){
		ClassGenerator CG;
		List<TreeNode> list = makeList(arguments);
		if(logo.equals(CONSTANT)){
			CG = new ClassGenerator(logo, Double.parseDouble(command));
		}
		else{
			CG = new ClassGenerator(logo, list);
		}
		return CG.getGenerated();
	}
	
	private List<TreeNode> makeList(int numArgs){
		List<TreeNode> list = new ArrayList<>();
		for(int i = 0; i < numArgs; i++){
			if(parsed.isEmpty()){
				throw new IllegalStateException("Empty treeNode Stack in the Tree Generator");
			}
			list.add(parsed.pop());
		}
		return list;
	}
	
	public Queue<TreeNode> getQueue(){
		return commandQueue;
	}
}
