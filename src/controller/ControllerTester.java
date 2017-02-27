package controller;

import java.util.List;

import model.*;
import model.turtle.Turtle;
import model.turtle.TurtleState; 

public class ControllerTester{
	private Controller myController; 
	public ControllerTester(){
		myController = new Controller(); 
	}
	
	public Controller getControl(){
		return myController;
	}
	
	public static void main (String[] args){
		ControllerTester test = new ControllerTester();
		
		test.getControl().processInput("");
		
		System.out.println("String to print " + test.getControl().getStringOutput());
	}
	
}