package parser.storage;

import parser.tokenizer.TokenList;

public abstract class StorageHandler {
	protected String indicator;
	protected TokenList list;
	protected int location;
	protected boolean madeNew;
	
	public StorageHandler(String indicator){
		this.indicator = indicator;
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
	
	protected abstract int update();
	
	protected abstract void replace(int location);

}
