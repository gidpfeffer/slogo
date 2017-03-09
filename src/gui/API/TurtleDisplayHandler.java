package gui.API;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.turtle.TurtleState;;

public interface TurtleDisplayHandler {
	public abstract void addLineToScreen(Line l);
	public abstract Color getColorPalette(double index);
}
