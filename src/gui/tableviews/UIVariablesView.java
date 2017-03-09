package gui.tableviews;

import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import gui.tools.Frame;

import parser.storage.VariableStorage;

public class UIVariablesView extends UITableView implements Observer {
	
	public UIVariablesView(Frame bounds) {
		super(bounds, "Variables"); //string for title

	}
	

	@Override
	public void update(Observable o, Object arg) {
		VariableStorage map = (VariableStorage) o;
		clear();
		for(Entry<String, Double> e: map.getMap().entrySet()){
			addText(e.getKey().replaceFirst(":", "") + " : " + e.getValue());
		}
	}

}
