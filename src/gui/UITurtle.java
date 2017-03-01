package gui;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import general_data_structures.Tuple;
import gui.tools.MyColors;
import gui.tools.TurtleAnimationData;
import gui.tools.UITurtleAttributes;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.turtle.TurtleState;

public class UITurtle extends Rectangle {
	private TurtleState _turtleState;
	private UITurtleAttributes _turtleAtt;
	private UITurtleAttributes _priorTurtleAtt;
	private SequentialTransition sequencer = new SequentialTransition();
	private Tuple<TranslateTransition, RotateTransition> _animators;
	private LinkedList<TurtleAnimationData> _queue = new LinkedList<TurtleAnimationData>();
	private Color _lineColor = MyColors.DARK_GREEN;
	public UITurtle(Tuple<TranslateTransition, RotateTransition> animators){
		this(animators,new Image("turtle.png"));
	}
	
	public UITurtle(Tuple<TranslateTransition, RotateTransition> animators,Image image){
		this(animators,image, new TurtleState());
	}
	
	public UITurtle(Tuple<TranslateTransition, RotateTransition> animators,Image image, TurtleState state){
		_turtleState = state;
		setImageView(image);
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
	
	public void setImageView(Image image){
		setFill(new ImagePattern(image));
	}
	public void addAnimationToQueue(TurtleState s, Tuple<Double, Double> pos){
		addAnimationToQueue(s,pos,null);
	}
	public void addAnimationToQueue(TurtleState s, Tuple<Double, Double> pos, Line line){
		_queue.add(new TurtleAnimationData(s,pos,line));
		if(!Animation.Status.RUNNING.equals(_animators.x.getStatus())){
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
		
		if (_turtleAtt.angle - _priorTurtleAtt.angle == 0){
			double deltaX = _turtleAtt.x - _priorTurtleAtt.x;
			double deltaY = _turtleAtt.y - _priorTurtleAtt.y;
			_animators.x.setByX(deltaX);
			_animators.x.setByY(deltaY);
			_animators.x.setDuration( //1 pixels per 10 millisecond
					Duration.millis(
							5 * (Math.abs(deltaX) + Math.abs(deltaY)) + 10
							)
					); 
			_animators.x.play();
		}else{
			double deltaAngle = Math.min(
					_turtleAtt.angle - _priorTurtleAtt.angle, 
					360 + _priorTurtleAtt.angle - _turtleAtt.angle);
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
	public void expandLine(Line line){
		UITurtleAttributes old = getPriorAttributes();
		UITurtleAttributes curr = getNewAttributes();
		if(old != null && getTurtleState().getPen()){
			//TODO animate this
			double ins = getWidth()/2.;
			//Line line = new Line(old.x + ins, old.y + ins, curr.x + ins, curr.y + ins);
			line.setStartX(old.x + ins);
			line.setStartY(old.y + ins);
			line.setEndX(curr.x + ins);
			line.setEndY(curr.y + ins);
			line.setStroke(_lineColor);
			line.setStrokeWidth(4);
//			line.endXProperty().bind(xProperty());
//			line.endYProperty().bind(yProperty());
		}
	}
	public void setTurtleState(TurtleState s, Tuple<Double, Double> widthHeight){
		_priorTurtleAtt = _turtleAtt;
		_turtleAtt = new UITurtleAttributes(widthHeight.x, widthHeight.y, (-s.getHeadAngle() + 90)%360);
		this.setLayoutX(_turtleAtt.x);
		this.setLayoutY(_turtleAtt.y);
		this.setWidth(32);
		this.setHeight(32);
		this.setRotate(_turtleAtt.angle);
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
		//TODO
	}
}
