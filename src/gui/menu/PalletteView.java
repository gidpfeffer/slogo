package gui.menu;

import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class PalletteView extends UIView {

	private Map<Double, Palette> _palettes = new HashMap<Double, Palette>();
	

	public PalletteView(Frame frame) {
		super(frame);
		System.out.println(frame);
		setupViews();
	}

	private void addNewPalette(double index, Color color, Frame frame) {
		_palettes.put(index, new Palette(color, ""+ (int) index,frame));
		this.getChildren().add(_palettes.get(index));
	}

	public void setPalleteColorAtIndex(Double index, Color color) {
		_palettes.get(index).setColor(color);
	}

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, _bounds);
		setupTitle();
		setupDefaultPalettes();
		
	}
	
	private void setupDefaultPalettes() {
		double width = _bounds.getWidth() - 12;
		double height = (_bounds.getHeight() - 40)/4 - 6;
		double y = 40;
		addNewPalette(1.0, MyColors.GREEN_900, new Frame(6,y, width, height));
		addNewPalette(2.0, MyColors.GREEN_100,new Frame(6,y + 1*(height + 6), width, height));
		addNewPalette(3.0, MyColors.AMBER,new Frame(6,y + 2*(height + 6), width, height));
		addNewPalette(4.0, MyColors.INDIGO,new Frame(6,y + 3*(height + 6), width, height));
		//addNewPalette(5.0, MyColors.GREEN_200,new Frame(6,y + 4*(height + 6), width, height));
		
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
		return this._palettes.get(index).getColor();
	}
	
}

final class Palette extends UIView{
	private Color _color;
	private ColorPicker _picker;
	private Label _label;
	
	public Palette(Color color, String text, Frame frame){
		super(frame);
		System.out.println(frame);
		_color = color;
		//8,8,frame.getWidth()/2 - 16,frame.getWidth()/2 - 16
		double half = frame.getWidth()/2;
		_picker = new ColorPicker(color);
		_picker.setLayoutX(0);
		_picker.setLayoutY(0);
		_picker.setPrefHeight(_bounds.getHeight());
		_picker.setPrefWidth(_bounds.getWidth()*2./3.);
		_picker.setBackground(Background.EMPTY);
		_picker.setOnAction(e -> setColor(_picker.getValue()));
		_label = new Label(text);
		_label.setLayoutX(_bounds.getWidth()*2./3.);
		_label.setLayoutY(0);
		_label.setPrefHeight(_bounds.getHeight());
		_label.setPrefWidth(_bounds.getWidth() /3.0);
		_label.setAlignment(Pos.CENTER);
		this.getChildren().add(_picker);
		this.getChildren().add(_label);
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_200, _bounds);
	}
	public Color getColor(){
		return _color;
	}
	public void setColor(Color color){
		_color = color;
		_picker.setValue(color);
	}

}
