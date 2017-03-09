package gui;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import general_data_structures.Tuple;
import gui.API.TurtleDisplayHandler;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.TurtleAnimationData;
import gui.tools.UITurtleAttributes;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import model.turtle.TurtleState;

public class UITurtle extends Pane implements Observer {

	private TurtleState _turtleState;
	private UITurtleAttributes _turtleAtt;
	private UITurtleAttributes _priorTurtleAtt;
	private Tuple<TranslateTransition, RotateTransition> _animators;
	private LinkedList<TurtleAnimationData> _queue = new LinkedList<TurtleAnimationData>();
	private Color _lineColor = MyColors.GREEN_900;
	private double _strokeWidth = 3;
	private double _shape = 0;
	private ImageView _imageView;
	private double _id;
	private TurtleDisplayHandler _handler;
	private Frame _displayBounds;

	private double ANIMATION_SPEED = 400;// 100 pixels per second

	public UITurtle(Tuple<TranslateTransition, RotateTransition> animators, TurtleDisplayHandler handler,
			Frame displayBounds, TurtleState state) {
		this(animators, new Image("turtle.png"),state, handler, displayBounds);
	}

	public UITurtle(Tuple<TranslateTransition, RotateTransition> animators, Image image, TurtleState state,
			TurtleDisplayHandler handler, Frame displayBounds) {
		_turtleState = state;
		_handler = handler;
		_displayBounds = displayBounds;
		setImageView(new ImageView(image));
		_animators = animators;
		_animators.x.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// System.out.println("translation finished");
				playNextAnimation();
			}
		});
		_animators.y.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// System.out.println("rotation finished");
				playNextAnimation();
			}
		});
		this.setTurtleState(getTurtleState());
	}

	public void setImageView(ImageView imageView) {
		if (_imageView != null)
			this.getChildren().remove(_imageView);
		_imageView = imageView;
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(32);
		imageView.setFitWidth(32);
		this.getChildren().add(imageView);
	}

	public void addAnimationToQueue(double angle, Tuple<Double, Double> pos) {
		_queue.add(new TurtleAnimationData(angle, pos));
		if (!Animation.Status.RUNNING.equals(_animators.x.getStatus())
				&& !Animation.Status.RUNNING.equals(_animators.y.getStatus())) {
			playNextAnimation();
		}
	}

	private void playNextAnimation() {
		TurtleAnimationData next = _queue.poll();
		if (next != null) {
			play(next.getAngle(), next.getPosition());
		}
	}

	private void play(double angle, Tuple<Double, Double> pos) {
		_priorTurtleAtt = _turtleAtt;
		_turtleAtt = new UITurtleAttributes(pos.x, pos.y, angle);
		// TODO UNCOMMENT THE LINE UNDER THIS
		// this.setVisiblityTo(!this.isVisible());

		// TODO: mention this in analysis
		// setting the angle was a complicated process but i don't see any
		// easier way
		// to mathematically ensure that the turtles angle on display is in sync
		// with the
		// backend's coordinate system. RotationAnimation also adds a few
		// glitches.
		double deltaX = _turtleAtt.x - _priorTurtleAtt.x;
		double deltaY = _turtleAtt.y - _priorTurtleAtt.y;
		Tuple<Double, Double> angle1 = new Tuple<Double, Double>(_turtleAtt.angle - _priorTurtleAtt.angle,
				Math.abs(_turtleAtt.angle - _priorTurtleAtt.angle));
		Tuple<Double, Double> angle2 = new Tuple<Double, Double>(360 + _priorTurtleAtt.angle - _turtleAtt.angle,
				Math.abs(360 + _priorTurtleAtt.angle - _turtleAtt.angle));
		// This line right here sets deltaAngle to the angle with the closest
		// value
		// to zero while still preserving the angles direction (positive or
		// negative)
		double deltaAngle = angle1.y == Math.min(angle1.y, angle2.y) ? angle1.x : angle2.x;
		if (Math.abs(deltaY) + Math.abs(deltaX) != 0) {
			_animators.x.setByX(deltaX);
			_animators.x.setByY(deltaY);
			_animators.x.setDuration( // 1 pixels per 10 millisecond
					Duration.millis(1000 / ANIMATION_SPEED * (Math.abs(deltaX) + Math.abs(deltaY)) + 1));
			_animators.x.play();
		}
		if (deltaAngle != 0) {
			_animators.y.setByAngle(deltaAngle); // 1 ms per degree
			_animators.y.setDuration(Duration.millis(Math.abs(0)));
			_animators.y.setDuration(Duration.millis(Math.abs(deltaAngle)));
			_animators.y.play();
		}
		if (getPriorAttributes() != null && getTurtleState().getPen()) {
			addLine();
		}
	}

	private void addLine() {
		UITurtleAttributes old = getPriorAttributes();
		UITurtleAttributes curr = getNewAttributes();
		if (old != null && getTurtleState().getPen()) {
			double ins = getWidth() / 2.;
			Line line = new Line(old.x + ins, old.y + ins, curr.x + ins, curr.y + ins);
			line.setStroke(_lineColor);
			line.setStrokeWidth(_strokeWidth);
			line.setOpacity(0.5);
			_handler.addLineToScreen(line);
		}
	}

	public void setTurtleState(TurtleState s) {
		setTurtleAttributes(GUITools.turtleCoordinateToPixelCoordinate(s, _displayBounds),(-s.getHeadAngle() + 90));
		this._turtleState = s;
		this.setLayoutX(_turtleAtt.x);
		this.setLayoutY(_turtleAtt.y);
		this.setWidth(32);
		this.setHeight(32);
		this.setRotate(_turtleAtt.angle);
	}
	
	private void setTurtleAttributes(Tuple<Double, Double> pos, double angle){
		_priorTurtleAtt = _turtleAtt;
		_turtleAtt = new UITurtleAttributes(pos.x, pos.y,angle);
	}

	public void reset() {
		_priorTurtleAtt = null;
		_turtleAtt = null;
		setTurtleState(getTurtleState());
		emptyAnimationQueue();
	}

	public TurtleState getTurtleState() {
		StackTraceElement[] s = Thread.currentThread().getStackTrace();
		//System.out.println("getting turtle state " + s[1].getMethodName() + " " + s[2].getMethodName());
		return _turtleState;
	}

	public UITurtleAttributes getPriorAttributes() {
		return _priorTurtleAtt;
	}

	public UITurtleAttributes getNewAttributes() {
		return _turtleAtt;
	}

	public void setTurtleImage(Image img) {
		_imageView.setImage(img);
	}

	public double getTurtleId() {
		return _id;
	}

	public void setVisiblityTo(boolean b) {
		this._imageView.setVisible(b);
	}

	public void setLineColor(Color color) {
		this._lineColor = color;
	}

	public void setPenStrokeWidth(double d) {
		this._strokeWidth = d;
	}

	public void setShape(double index, ImageView image) {
		_shape = index;
		// TODO
	}

	public void setShape(double index, Shape shape) {
		_shape = index;
		// TODO
	}

	public void setPenVisibility(boolean bool) {
		this.getTurtleState().setPen(bool);
	}

	@Override
	public void update(Observable o, Object arg) {
		TurtleState newState = (TurtleState) o;
		System.out.println(newState.getX() + " " + newState.getY());
		addAnimationToQueue(-newState.getHeadAngle() + 90, GUITools.turtleCoordinateToPixelCoordinate(newState, _displayBounds));
	}

	public void stop() {
		this._animators.x.stop();
		this._animators.y.stop();
		emptyAnimationQueue();
	}

	private void emptyAnimationQueue() {
		_queue.clear();
	}

	public void pause() {
		this._animators.x.pause();
		this._animators.y.pause();
	}

	public void play() {
		if(Animation.Status.PAUSED.equals(_animators.x.getStatus())){
			this._animators.x.play();
		}
		
		this._animators.y.play();
	}

}
