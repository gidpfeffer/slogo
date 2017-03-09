package gui.tableviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import parser.storage.VariableStorage;

public class UITableView extends UIView {
	protected Frame _bounds;
	protected FlowPane _flowPane;
	protected List<Text> _texts = new ArrayList<Text>();
	
	public UITableView(Frame frame, String title) {
		super(frame);
		_bounds = frame.toLocalBounds();
		setupViews(title);

	}
	

	private void setupViews(String title) {
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, _bounds);
		setupTitle(title);

		_flowPane = new FlowPane(Orientation.VERTICAL);
		_flowPane.setPadding(new Insets(48, 12, 10, 12));
		_flowPane.setVgap(20);

		
		_flowPane.setBackground(Background.EMPTY);
		_flowPane.setAlignment(Pos.TOP_CENTER);
		_flowPane.setColumnHalignment(HPos.CENTER);

		ScrollPane scroll = new ScrollPane(_flowPane);
		scroll.setBackground(Background.EMPTY);
		scroll.setBorder(Border.EMPTY);
		scroll.setBlendMode(BlendMode.DARKEN);
		scroll.setPrefWidth(_bounds.getWidth());
		scroll.setPrefHeight(_bounds.getHeight());
		
		this.getChildren().add(scroll);
	}
	private void setupTitle(String t){
		Label title = GUITools.plainLabel(t, 20, MyColors.INDIGO, FontWeight.BOLD);
		title.setPrefWidth(_bounds.getWidth());
		title.setPrefHeight(40);
		title.setAlignment(Pos.CENTER);
		this.getChildren().add(title);
	}
	protected Text addText(String s){
		StackPane stack = new StackPane();
		stack.setBackground(
				new Background(
						new BackgroundFill[] {
								new BackgroundFill(
										MyColors.GREEN_200, new CornerRadii(4), new Insets(-8,-12,-8,-8))}));
		Text l = new Text(s);
		l.setWrappingWidth(_bounds.getWidth() - 48);
		l.setTextAlignment(TextAlignment.LEFT);
		_texts.add(l);
		stack.getChildren().add(l);
		_flowPane.getChildren().add(stack);
		return l;
	}
	
	public void clear(){
		_flowPane.getChildren().clear();
	}



}
