package parser.storage;

import parser.tokenizer.TokenList;

public class StorageHandler {
	protected String indicator;
	protected TokenList list;
	
	public StorageHandler(String indicator){
		this.indicator = indicator;
	}
	
	protected boolean isDone(){
		return !list.getLogo().contains(indicator);
	}

}
