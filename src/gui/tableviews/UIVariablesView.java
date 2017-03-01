package gui.tableviews;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import general_data_structures.UserVariables;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import parser.storage.VariableStorage;

public class UIVariablesView extends Pane implements Observer {
	
	Frame _bounds;
	FlowPane _flowPane;
	
	public UIVariablesView(Frame bounds) {
		super();
		_bounds = bounds;
		setupViews();

	}
	

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.LIGHT_GREEN, _bounds);
		setupTitle();
//		ScrollView scrollView = new ScrollView(_bounds.getHeight()/4, _bounds);
//		Label l = new Label("x\t:\t5");
//		UIView view = new UIView();
//		view.getChildren().add(l);
//		scrollView.addItem(view);
//		
//		this.getChildren().add(scrollView);
		_flowPane = new FlowPane(Orientation.VERTICAL);
		_flowPane.setPadding(new Insets(40, 16, 10, 16));
		_flowPane.setVgap(8);

		//scroll.setPrefWrapLength(300); // preferred width = 300


		//_scroll.setLayoutY(40);
		
		_flowPane.setBackground(Background.EMPTY);
		_flowPane.setAlignment(Pos.TOP_CENTER);
		_flowPane.setColumnHalignment(HPos.CENTER);
		//_scroll.setPrefWrapLength(1000); // preferred height = 200
//
//		for(int i=0; i<4; i++){
//			addLabel("testing\t:\t"+i);
//		}
		ScrollPane scroll = new ScrollPane(_flowPane);
		//scroll.setLayoutY(36);
		scroll.setBackground(Background.EMPTY);
		scroll.setBorder(Border.EMPTY);
		scroll.setBlendMode(BlendMode.DARKEN);
		scroll.setPrefWidth(_bounds.getWidth());
		scroll.setPrefHeight(_bounds.getHeight());
		this.getChildren().add(scroll);
	}
	private void setupTitle(){
		Label title = GUITools.plainLabelBoldHelvetica("Variables", 20, MyColors.DARK_GREEN);
		title.setPrefWidth(_bounds.getWidth());
		title.setPrefHeight(40);
		this.getChildren().add(title);
	}
	private void addLabel(String s){
		Label l = new Label(s);
		_flowPane.getChildren().add(l);
	}
	
	public void clear(){
		_flowPane.getChildren().clear();
	}

	@Override
	public void update(Observable o, Object arg) {
		VariableStorage map = (VariableStorage) o;
		clear();
		for(Entry<String, Double> e: map.getMap().entrySet()){
			addLabel(e.getKey().replaceFirst(":", "") + "\t:\t" + e.getValue());
		}
		System.out.println("updated variables view!");
	}

}
