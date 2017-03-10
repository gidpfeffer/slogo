package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class AskWith extends MultipleTurtleCommand{
	AskTellData data; 
	
	List<String> toParse; 
	List<Double> myTurtles; 
	
	private final int TURTLE_BRACKET_COUNT = 2; 
	private final int COMMANDS_BRACKET_COUNT = 4;
	
	private final String LEFT_BRACKET = "[";
	private final String RIGHT_BRACKET = "]";
	
	public AskWith(List<String> commands, List<Double> turtle){
		toParse = new ArrayList<String>(commands);
		myTurtles = new ArrayList<Double>(turtle);
		
	}

	@Override
	public AskTellData getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() {
		QueueConstructor QC = new QueueConstructor(toParse);
		Queue<String> commandQ = QC.getParsedQ();
		
		StringBuilder conditionString = new StringBuilder(); 
		
		// build condition string 
		conditionString.append("IF");
		
		
		// add to commands 
		ArrayList<String> legitCommands = new ArrayList<String>();
		ArrayList<String> condition = new ArrayList<String>(); 
		ArrayList<String> ifcommands = new ArrayList<String>();
		
		
		int bracketCount = 0; 
		while (bracketCount< TURTLE_BRACKET_COUNT){
			String st = commandQ.poll(); 
			
			if ((st.equals("[")) || (st.equals("]"))){
				bracketCount++; 
				continue; 
				
			}
			else{
				conditionString.append(" " );
				conditionString.append(st);
			}
		}
		
		if (bracketCount==TURTLE_BRACKET_COUNT){
			while (bracketCount<= COMMANDS_BRACKET_COUNT && !commandQ.isEmpty()){
				String st1 = commandQ.poll(); 
			
				if ((st1.equals(LEFT_BRACKET)) || (st1.equals(RIGHT_BRACKET))){
					bracketCount++; 
				}
				
				
				if (bracketCount == COMMANDS_BRACKET_COUNT){
					ifcommands.add(st1);
					break;
				}
				else{
				
					ifcommands.add(st1);
				}
			}
		}
		
		//dataObject =  new AskTellData(turtles,  commands);
		//System.out.println(conditionString.toString());
		System.out.println(ifcommands);
		legitCommands.add(conditionString.toString());
		legitCommands.addAll(ifcommands);
		System.out.println(legitCommands);
		
		
	}
	
	
	public static void main (String [] args){
		//List<String> commands, List<Double> turtle
		
		List<Double> turtles = new ArrayList<Double>();
		turtles.add(1.0);
		
		String testCode = "askwith [ equals xcor 50 ] [ fd 100 ] ";
		List<String> commands = Arrays.asList(testCode.split(" " ));
		
		AskWith aw = new AskWith(commands, turtles);
		aw.execute();
		
		
	}

}
