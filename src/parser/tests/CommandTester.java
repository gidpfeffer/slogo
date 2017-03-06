package parser.tests;

import java.util.ArrayList;
import java.util.Queue;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;
import parser.main.Compiler;
import parser.main.NewParser;
import parser.storage.CommandHandler;
import parser.storage.CommandStorage;
import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenList;

public class CommandTester {
	
	public static void main(String args[]){
		String language = "resources/languages/English";
		String testCode = "to sq [ :x ] [ product :x :x ] "
				+ "to addAll [ :x :y :z ] [ sum sum :x :y :z ]";
		String testCode2 = "fd sq 10 rt addAll 10 20";
		
		
		Compiler compiler = new Compiler();
		
		CommandStorage CS = new CommandStorage();
		
		CommandHandler CH = new CommandHandler(CS);
		
		NewParser p = new NewParser(language);
		
		ProtectedTokenList PTL = p.parse(testCode);
		
		TokenList TL = new TokenList(new ArrayList<>(PTL.getLiterals()),
				new ArrayList<>(PTL.getLogo()));
		
		CH.fix(TL);
		
		PTL = p.parse(testCode2);
		
		TL = new TokenList(new ArrayList<>(PTL.getLiterals()),
				new ArrayList<>(PTL.getLogo()));
		
		CH.fix(TL);
		
		Queue<TreeNode> q = compiler.compile(new TurtleState(), new ProtectedTokenList(TL));
		
		while(!q.isEmpty()){
			TreeNode cur = q.remove();
			if(cur instanceof TurtleCommand){
				System.out.println("Class: " + cur.getClass() + "Value: " + cur.getValue());
			}
		}
		
	}
}
