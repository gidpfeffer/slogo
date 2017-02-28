package parser.reflectiontest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import model.ListEnd;
import model.ListStart;
import model.command.TreeNode;
import model.turtle.TurtleState;
import parser.tokenizer.TokenList;

public class TreeGenerator {
	private TokenList list;
	private Stack<TreeNode> parsed;
	private Stack<String> commands, logo;
	private NumArgsHandler numArgs;
	private Queue<TreeNode> commandQueue;
	private static final String CONSTANT = "Constant";
	private ClassGenerator CG;
	private TurtleState turtle;
	private static final String PI = "Pi";
	
	public TreeGenerator(TokenList t, TurtleState turtle){
		list = new TokenList(new ArrayList<String>(t.getLiterals()), new ArrayList<String>(t.getLogo()));
		CG = new ClassGenerator();
		this.turtle = turtle;
		initializeData();
		fillStack();
		generateTree();
	}
	
	private void initializeData(){
		parsed = new Stack<>();
		commands = new Stack<>();
		logo = new Stack<>();
		numArgs = new NumArgsHandler();
		commandQueue = new LinkedList<>();
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
		t.deRootAllChild();
		if(!((t instanceof ListStart)||(t instanceof ListEnd))){
			parsed.add(t);
		}
		if(!logoType.equals(CONSTANT) && !logoType.equals(PI)){
			commandQueue.add(t);
		}
	}
	
	private TreeNode makeNode(String command, String logo, int arguments){
		List<TreeNode> list = makeList(arguments);
		if(logo.equals(CONSTANT)){
			CG.generate(logo, Double.parseDouble(command), turtle);
		}
		else{
			CG.generate(logo, list, turtle);
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
	
	public double getLast(){
		Queue<TreeNode> q = new LinkedList<>(commandQueue);
		while(!q.isEmpty()){
			double val = q.remove().getValue();
			if(q.isEmpty()){
				return val;
			}
		}
		throw new IllegalStateException("empty queue");
	}
}
