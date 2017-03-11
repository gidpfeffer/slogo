package parser.helpers;

import java.util.Stack;

import controller.SLogoException;
import model.command.TreeNode;
import parser.interpreter.AbstractBracketAid;
import parser.reflection.TreeGenerator;

public abstract class AbstractAdvancedControl extends AbstractBracketAid{
	protected static final String CONSTANT = "Constant";
	protected int ifStart, ifEnd, elseStart, elseEnd;
	
	public AbstractAdvancedControl(String indicator){
		super(indicator);
	}
	
	@Override
	protected void reset() {
		ifStart = ifEnd = elseStart = elseEnd = -1;	
	}

	@Override
	protected void findIndices() {
		ifStart = findStartBracket(getLogoLocations(indicator).get(0));
		ifEnd = findEndBracket(ifStart);
		checkBracketValidity();
		elseStart = findStartBracket(ifEnd);
		elseEnd = findEndBracket(elseStart);
	}

	@Override
	protected abstract void replace();

	protected void checkBracketValidity() {
		if(!list.getLiterals().get(ifEnd + 1).equals(LEFT_BRACKET)){
			throw new SLogoException("Else bracket doesnt follow if bracket");
		}
	}
	
	protected Stack<TreeNode> evaluateExpression(){
		TreeGenerator TG = new TreeGenerator(
				list.newSubList(getLogoLocations(indicator).get(0) + 1, ifStart), turtle);
		Stack<TreeNode> stack = TG.getCommandStack();
		if(stack.size() == 0) 
			throw new SLogoException("No arguments provided");
		return stack;
	}

}
