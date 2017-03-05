package model.turtle;

public interface State {
	public double getID();
	public double getX();
	public double getY();
	public double getHeadAngle();
	public boolean getPen();
	public boolean getVisibility();	
	public double getPenSize();
	public double getPenColorIndex();
	public double getShapeIndex();
}
