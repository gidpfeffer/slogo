package gui.menu;

import gui.tableviews.UITableView;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;

public class UIAttributesView extends UITableView{
	
	public UIAttributesView(Frame frame){
		super(frame, "Turtle Attributes");
		setupViews();
	}

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, _bounds);
		this.addText("Turtle ID: 0\nx=0, y=0, PenIsDown=true");
	}
}
