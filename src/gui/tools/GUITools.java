package gui.tools;

import general_data_structures.Tuple;
import gui.Frame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.turtle.TurtleState;

public class GUITools {
	public static Background getBackgroundWithColor(Color color){
		return getBackgroundWithColor(color, 0);
	}
	public static Background getBackgroundWithColor(Color color, double radii){
		BackgroundFill[] fills = {new BackgroundFill(color, new CornerRadii(radii), Insets.EMPTY)};
		Background b = new Background(fills);
		return b;
	}
	
	public static void addBackgroundWithColor(Pane self, Color color, Frame frame){
		Rectangle rect = new Rectangle(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
		rect.setFill(color);
		rect.setArcHeight(3);
		rect.setArcWidth(3);
		self.getChildren().add(0, rect);
	}
	public static Label plainLabelBoldHelvetica(String text, int fontSize, Color color) {
		Label label = new Label();
		label.setText(text);
		label.setFont(Font.font("HelveticaNeue", FontWeight.BOLD, fontSize));
		label.setAlignment(Pos.CENTER);
		label.setTextFill(color);
		return label;
	}
	
	public static Tuple<Double, Double> turtleCoordinateToPixelCoordinate(TurtleState t, Frame bounds){
		double x=t.getX() + bounds.getWidth()/2.0;
		double y=t.getY() + bounds.getHeight()/2.0;
		return new Tuple<Double, Double>(x,y);
	}
}
