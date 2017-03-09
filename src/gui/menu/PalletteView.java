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

import java.util.HashMap;
import java.util.Map;

public class PalletteView extends UIView {

	private Map<Double, Color> _palettes = new HashMap<Double, Color>();

	public PalletteView(Frame frame) {
		super(frame);
		setupViews();
	}

	public void addNewPalette(Double index, Color color) {
		//TODO:
		_palettes.put(index, color);
		
	}

	public void setPalleteAtIndex(Double index, Color color) {
		//TODO
		_palettes.put(index, color);
	}

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, _bounds);
		setupTitle();
		addNewPalette(1.0, MyColors.GREEN_900);
		addNewPalette(2.0, MyColors.GREEN_100);
		addNewPalette(3.0, MyColors.AMBER);
		addNewPalette(4.0, MyColors.INDIGO);
		addNewPalette(5.0, MyColors.GREEN_200);
	}
	
	private void setupTitle(){
		//TODO resources file
		Label l = GUITools.plainLabel("Palette", 18, MyColors.INDIGO, FontWeight.BOLD); //TODO
		l.setPrefHeight(40);
		l.setPrefWidth(_bounds.getWidth());
		l.setAlignment(Pos.CENTER);
		this.getChildren().add(l);
	}

	public Color getPalette(double index) {
		return this._palettes.get(index);
	}
	
	

}
