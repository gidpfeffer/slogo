package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class GUITools {
	public static Background getBackgroundWithColor(Color color){
		return getBackgroundWithColor(color, 0);
	}
	public static Background getBackgroundWithColor(Color color, double radii){
		BackgroundFill[] fills = {new BackgroundFill(color, new CornerRadii(radii), Insets.EMPTY)};
		Background b = new Background(fills);
		return b;
	}
}
