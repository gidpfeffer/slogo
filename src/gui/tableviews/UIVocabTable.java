package gui.tableviews;

import java.util.Observable;
import java.util.Observer;

import general_data_structures.Vocabulary;
import gui.API.UIMainHandler;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UIVocabTable extends UITableView implements Observer {
	
	private UIMainHandler _handler;
	String[] _initialFunctions = {
		"set :distance 10\n\nrepeat 18 \n[ \n\tforward * 5 :distance\n\tright 150\n\tforward * 6 :distance\n\tright 100\n\tforward * 3 :distance\n\tright 90\n]",
		"dotimes [ :dist 200 ] \n[\n\tfd :dist\n\trt 89\n]"
	};
	
	public UIVocabTable(Frame bounds, UIMainHandler handler) {
		super(bounds, "Functions");
		_handler = handler;
		for(int i =0; i<_initialFunctions.length; i++){
			addText(_initialFunctions[i]);
		}
	}
	
	@Override
	protected Text addText(String s){
		super.addText(s);
		Text t = _texts.get(_texts.size() - 1); //were assuming that super added text to list
		t.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				_handler.addFunctionToTerminal(t.getText());
			}
		});
		return t;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	

}
