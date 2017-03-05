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
	
	
	private double ANIMATION_SPEED=400;//100 pixels per second
	
	public UITurtle(Tuple<TranslateTransition, RotateTransition> animators, 
			double id,TurtleDisplayHandler handler, Frame displayBounds){
		this(animators,new Image("turtle.png"), id, handler,displayBounds);
	}
	
	public UITurtle(Tuple<TranslateTransition, RotateTransition> animators,
			Image image, double id,TurtleDisplayHandler handler,Frame displayBounds){
		this(animators,image, new TurtleState(), id, handler,displayBounds);
	}
	
	public UITurtle(Tuple<TranslateTransition, RotateTransition> animators,
			Image image, TurtleState state, double id, TurtleDisplayHandler handler,Frame displayBounds){
		_turtleState = state;
		_id = id;
		_handler = handler;
		_displayBounds = displayBounds;
		setImageView(new ImageView(image));
		_animators = animators;
		_animators.x.setOnFinished(new EventHandler<ActionEvent>() {
		      @Override
		      public void handle(ActionEvent event) {
		    	  //System.out.println("translation finished");
		    	  playNextAnimation();
		      }
		});
		_animators.y.setOnFinished(new EventHandler<ActionEvent>() {
		     
		      @Override
		      public void handle(ActionEvent event) {
		    	  //System.out.println("rotation finished");
		    	  playNextAnimation();
		      }
		});
	}
	
	public void setImageView(ImageView imageView){
		if(_imageView != null)
			this.getChildren().remove(_imageView);
		_imageView = imageView;
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(32);
		imageView.setFitWidth(32);
		this.getChildren().add(imageView);
	}
	public void setLineColor(Color color){
		this._lineColor = color;
	}
	public void addAnimationToQueue(TurtleState s, Tuple<Double, Double> pos){
		addAnimationToQueue(s,pos,null);
	}
	public void addAnimationToQueue(TurtleState s, Tuple<Double, Double> pos, Line line){
		_queue.add(new TurtleAnimationData(s,pos,line));
		if(!Animation.Status.RUNNING.equals(_animators.x.getStatus()) && 
				!Animation.Status.RUNNING.equals(_animators.y.getStatus())){
			//System.out.println("running first animation");
			playNextAnimation();
		}
	}
	private void playNextAnimation(){
		TurtleAnimationData next = _queue.poll();
		if(next != null){
			//System.out.println("playNextAnimation");
			play(next.getTurtleState(), next.getPos(), next.getLine());
		}
	}

	private void play(TurtleState s, Tuple<Double, Double> pos, Line line){
		_priorTurtleAtt = _turtleAtt;
		_turtleAtt = new UITurtleAttributes(pos.x, pos.y, (-s.getHeadAngle() + 90));
		double deltaX = _turtleAtt.x - _priorTurtleAtt.x;
		double deltaY = _turtleAtt.y - _priorTurtleAtt.y;
		double deltaAngle = Math.min(
				_turtleAtt.angle - _priorTurtleAtt.angle, 
				360 + _priorTurtleAtt.angle - _turtleAtt.angle);
		if (Math.abs(deltaY) + Math.abs(deltaX) != 0){
			_animators.x.setByX(deltaX);
			_animators.x.setByY(deltaY);
			_animators.x.setDuration( //1 pixels per 10 millisecond
					Duration.millis(
							1000/ANIMATION_SPEED * (Math.abs(deltaX) + Math.abs(deltaY)) + 1
							)
					); 
			_animators.x.play();
		}
		if(deltaAngle != 0) 
		{
			
			_animators.y.setByAngle(deltaAngle);
			 //1 ms per degree
			_animators.y.setDuration(Duration.millis(
						Math.abs(0)));
			_animators.y.setDuration(Duration.millis(
						Math.abs(deltaAngle)));	
			_animators.y.play();
			
		}
		if(line != null && 
				getPriorAttributes() != null &&
				getTurtleState().getPen()){
			expandLine(line);
		}
	}
	private void expandLine(Line line){
		UITurtleAttributes old = getPriorAttributes();
		UITurtleAttributes curr = getNewAttributes();
		if(old != null && getTurtleState().getPen()){
			//TODO animate this
			double ins = getWidth()/2.;
			line.setStartX(old.x + ins);
			line.setStartY(old.y + ins);
			line.setEndX(curr.x + ins);
			line.setEndY(curr.y + ins);
			line.setStroke(_lineColor);
			line.setStrokeWidth(_strokeWidth);
			line.setOpacity(0.5);
			//line.endXProperty().bind(this.layoutXProperty().add(ins));
			//line.endYProperty().bind(this.layoutYProperty().add(ins));
		}
	}
	public void setTurtleState(TurtleState s, Tuple<Double, Double> widthHeight){
		_priorTurtleAtt = _turtleAtt;
		_turtleAtt = new UITurtleAttributes(widthHeight.x, widthHeight.y, (-s.getHeadAngle() + 90)%360);
		System.out.println(_turtleAtt.x + "\t" + _turtleAtt.y);
		this.setLayoutX(_turtleAtt.x);
		this.setLayoutY(_turtleAtt.y);
		this.setWidth(32);
		this.setHeight(32);
		this.setRotate(_turtleAtt.angle);
	}
	public void reset(TurtleState s, Tuple<Double, Double> widthHeight){
		_priorTurtleAtt = null;
		_turtleAtt = null;
		setTurtleState(s, widthHeight);
	}
	public TurtleState getTurtleState(){
		return _turtleState;
	}
	public UITurtleAttributes getPriorAttributes() {
		return _priorTurtleAtt;
	}
	public UITurtleAttributes getNewAttributes() {
		return _turtleAtt;
	}
	public void setTurtleImage(Image img){
		_imageView.setImage(img);
	}
	public double getTurtleId(){
		return _id;
	}

	@Override
	public void update(Observable o, Object arg) {
		TurtleState newState = new TurtleState((TurtleState) o);
		addAnimationToQueue(newState, 
				GUITools.turtleCoordinateToPixelCoordinate(newState, _displayBounds));
	}

}
