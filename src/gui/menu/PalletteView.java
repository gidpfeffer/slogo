package gui.menu;

import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;

import java.util.Map;

public class PalletteView extends UIView {

	private Map<Double, Color> _pallettes;

	public PalletteView(Frame frame) {
		super(frame);
		setupViews();
	}

	public void addNewPallete(Double index, Color color) {
		//TODO:
	}

	public void setPallete(Map<Double, Color> pallettes) {
		_pallettes = pallettes;
	}

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, _bounds);
		setupTitle();
	}
	
	private void setupTitle(){
		//TODO resources file
		Label l = GUITools.plainLabel("Pallette", 18, MyColors.INDIGO, FontWeight.BOLD);
		l.setPrefHeight(40);
		l.setPrefWidth(_bounds.getWidth());
		l.setAlignment(Pos.CENTER);
		this.getChildren().add(l);
	}

	public Color getPalette(double index) {
		return this._pallettes.get(index);
	}
	
	

}
