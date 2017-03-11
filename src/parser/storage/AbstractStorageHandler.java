package parser.storage;

import parser.tokenizer.TokenList;

public abstract class AbstractStorageHandler {
	protected static final String CONSTANT = "Constant";
	protected String indicator, newIndicator;
	protected TokenList list;
	protected int location;
	protected boolean madeNew;
	
	public AbstractStorageHandler(String indicator, String newIndicator){
		this.indicator = indicator;
		this.newIndicator = newIndicator;
	}
	
	private int update(){
		int loc = list.getLogo().indexOf(indicator);
		if(loc != 0 && list.getLogo().get(loc - 1).equals(newIndicator)){
			make(loc);
		}
		handleUndefined(loc);
		return loc;
	}
	
	protected boolean isDone(){
		return !list.getLogo().contains(indicator);
	}
	
	protected void fix(){
		while(!isDone()){
			location = update();
			replace(location);
			madeNew = false;
			location = -1;
		}
	}
	
	
	protected abstract void replace(int location);
	
	protected abstract void handleUndefined(int index);
	
	protected abstract void make(int location);
}
