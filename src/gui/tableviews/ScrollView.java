package gui.tableviews;

import java.util.ArrayList;
import java.util.List;

import gui.tools.Frame;
import gui.tools.UIView;
import javafx.scene.control.ScrollPane;

public class ScrollView extends ScrollPane{
	
	private List<UIView> _views = new ArrayList<UIView>();
	private Frame _bounds;
	private double _itemHeight=0;
	private double _currY=0;
	
	public ScrollView(double itemHeight, Frame bounds){
		_itemHeight = itemHeight;
		_bounds = bounds;
		this.setFitToWidth(true);
		this.setPrefViewportWidth(_bounds.getWidth() - 16);		
	}
	
	public void addItem(UIView view){
		_views.add(view);
		view.setFrame(new Frame(0, _currY, _bounds.getWidth(), _itemHeight ));
		this.getChildren().add(view);
		_currY += _itemHeight;
	}


	
}
