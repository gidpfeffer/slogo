package gui.tools;

import java.util.List;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class Animator {
	
	public static void translate(List<Node> nodes, double duration, double fps, double xTrans, double yTrans){
		final double steps = duration/fps;
		final double deltaX = xTrans/steps;
		final double deltaY = yTrans/steps;
		Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep((long) (steps/duration));
                    System.out.println((long) (steps/duration));
                } catch (InterruptedException e) {
                	System.out.println(e.getLocalizedMessage());
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
            	for(Node n: nodes){
            		move(n, deltaX, deltaY);
            	}
            }
        });
        Thread t = new Thread(sleeper);
    	t.start();
	}
	
	private static void move(Node n, double deltaX, double deltaY){
		n.setLayoutX(n.getLayoutX() + deltaX);
		n.setLayoutY(n.getLayoutY() + deltaY);
	}
}
