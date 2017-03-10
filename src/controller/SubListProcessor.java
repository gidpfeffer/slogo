package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;



import java.lang.reflect.Constructor;
import controller.SLogoException;
import model.ModelController;

public class SubListProcessor {

	private List<AskTellData> processedCommands; 
	private List<String> remaining; 	
	private List<String> preceding; 
	private List<Object> commandObjects; 

	private List<List<String>> listsToProcess; 

	private final String BREAKPOINT_1 = "ask";
	private final String BREAKPOINT_2 = "tell";
	private final String BREAKPOINT_3 = "askwith";

	private final String location = "controller.";
	private boolean hitBreakPoint; 
	
	private ModelController myModel; 


	public SubListProcessor(List<List<String>> sublists, ModelController model) throws SLogoException{
		listsToProcess = new ArrayList<List<String>>(sublists);
		commandObjects = new ArrayList<Object>(); 
		remaining = new ArrayList<String>(); 

		preceding = new ArrayList<String>();
		myModel = model; 

		hitBreakPoint = false; 
		process(); 
	}

	private void process() throws SLogoException {

		for (List<String> current : listsToProcess){
			if (!current.isEmpty()){
				QueueConstructor QC = new QueueConstructor(current);
				Queue<String> commands = QC.getQ();

				while(!(commands.isEmpty())){

					String firstCommand = commands.poll(); 

					if ((firstCommand.equals(BREAKPOINT_1)) || (firstCommand.equals(BREAKPOINT_2)) || ((firstCommand.equals(BREAKPOINT_3)))){
						hitBreakPoint = true; 
						try{
							String name = firstCommand.substring(0, 1).toUpperCase() + firstCommand.substring(1);
							Class<?> clazz = Class.forName(location + name);
							Constructor <?> ctor = clazz.getConstructor(List.class);
							Object obj = ctor.newInstance(current, myModel.getTurtleIDs());
							commandObjects.add(obj);
							break;

						}
						catch(Exception e){
							throw new SLogoException("class not found  " + location + firstCommand.substring(0, 1).toUpperCase() + firstCommand.substring(1));
						}

					}
					else{

						if (hitBreakPoint){
							remaining.add(firstCommand); 
						} 
						else{
							preceding.add(firstCommand);
						}

					}

				}
			}
		}

	}

	public List<String> getRemainingCommands(){
		return remaining; 
	}

	public List<Object> getCommands(){
		return commandObjects;
	}

	public List<Object> getCommandObjects(){
		return commandObjects; 
	}

	public List<String> getPrecedingCommands(){
		return preceding; 
	}



}
