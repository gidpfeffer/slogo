package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
		self.getChildren().add(0, rect);
	}
}
