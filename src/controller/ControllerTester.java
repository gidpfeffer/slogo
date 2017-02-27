package controller;



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
		
		test.getControl().processInput("setxy 1 2");
		
		System.out.println("String to print " + test.getControl().getStringOutput());
	}
	
}
