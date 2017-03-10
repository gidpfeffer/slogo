# API Changes

## Gideon

###  Parser

####  Tokenizer

|       Classes      |                                 What It's For                                 |                                                                                                                                   Public Methods                                                                                                                                  |
|:------------------:|:-----------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|    TokenPatterns   |                         Storing a Pattern/String Entry                        |                                                                                                                   Pattern getPattern(),  String getTokenTypes()                                                                                                                   |
| TokenListGenerator |                       Turning a String into a TokenList                       |                                                                                                                                TokenList getList()                                                                                                                                |
|      TokenList     | A data structure for Literal/Logo pairings after parsing in the form of lists |                             String List getLiterals(), String List getLogo(), TokenList newSubList(int start, int end), TokenList oldSubList(int start, int end), void removeAll(int start, int end), void addAll(TokenList list, int loc), int size()                            |
|      Tokenizer     |          Breaks the String into readable parts and gets the Logo keys         |                                                                                                                   TokenIdentifier getToken(), boolean isEmpty()                                                                                                                   |
|   TokenIdentifier  |     A data structure for Literal/Logo pairings after parsing (like Entry)     |                                                                                                                        String getToken(), String getType()                                                                                                                        |
| ProtectedTokenList |  A More protected version of the Token List for passing outside of the parser | Unmodifiable String List getLiterals(), Unmodifiable String List getLogo(), void add(TokenList list, int start), void add(TokenList list), void remove(int start, int stop), ProtectedTokenList oldSubList(int start, int end), ProtectedTokenList newSubList(int start, int end) |
|   CommentRemover   |           Removes the comments from a String so as to not be parsed           |                                                                                                                                 String getString()                                                                                                                                |

**Class Descriptions**

	TokenPatterns: Can be used to store a pattern with an associated String. Can be extended to any program which involves parsing with respect to patterns. Only has getters not setters. 

	TokenListGenerator: This class is integral to the SLogo parser. Can be extended to any program/facet of the program that needs a <String List, String List> structure generated given a String. Is dependent on the other Tokenizer classes to help create the list. 

	TokenList: The primary data structure used for all interpretation of Logo commands. Was intended to be used with similar calls to a List in Java (In retrospect it should have implemented List. Will be discussed further in Analysis). Can be extended by anyone needing access to two simultaneous String Lists. 

	Tokenizer: Is responsible for breaking a String into its respective parts. This class could be a valuable asset to any parsing program. Can be continuously called until all parsed items have been parsed (isEmpty).

	TokenIdentifier: Another form of an Entry like class. Stores two Strings, one literal and one logo. Could be used by anyone needing a <String, String> Entry. Only has getters, not setters to make sure Strings are maintained after generation. 

	ProtectedTokenList: Intended to be a more secure form of a TokenList. Returns unmodifiable and checks to make sure that all added lists are the same size for example. Intended for primary use outside of the parser to make sure that the integrity of the data structure is maintained. Would have been implemented in the Parser with more time (more in Analysis). 

	CommentRemover: A simple class which takes a String and returns a copy of that String with the comments removed. Currently uses "#" as the delimiter, but could be extended in the future to handle other delimiters.

####  Storage

|       Classes      |                                                              What It's For                                                              |                                         Public Methods                                        |
|:------------------:|:---------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------:|
|   VariableStorage  |                                            Maps the String variable name to the double value                                            |    String Set keySet(), void setValue(String key, double val), String, Double Map getMap()    |
|    TotalStorage    |                                      Holds the variable and command storage classes for the program                                     |                    VariableStorage getVars(), CommandStorage getCommands()                    |
|     FunctionObj    |                                 Holds the name of a function and the list of arguments for that command                                 | int numArgs(), String name(), String List getArgs(), boolean equals(Object o), int hashCode() |
| FunctionBracketAid |                                     Helps to handle the interpretation of functions for the program                                     |                               void handle(TokenList TL, State t)                              |
|       FixVars      |                         Replaces variables with their respective numbers or throws an error if they don't exist.                        |                                    void fix(TokenList list)                                   |
|   CommandStorage   |                                  Maps the functionObj objects to their respective TokenList functions.                                  |       FunctionObj, TokenList Map getMap(), void setValue(FunctionObj key, TokenList val)      |
|   CommandHandler   | Runs through the code making new functions when necessary, otherwise filling in predefined functions with their respective TokenLists.  |                                    void fix(TokenList list)                                   |

**Class Descriptions**

	VariableStorage: Stores the String variableName to the associated double value. Has getters and stress. Is a pretty standard Map. Could be extended to any String to double map. 

	TotalStorage: Houses the total storage elements. The data structure is pretty specific to SLogo because variable Storage and Command Storage are fine tuned to SLogo. Having said that, it is a pretty vanilla map storage class. 
	
	FunctionObj: A really useful class for storing the name and arguments of a function. Used for parsing to make the commands and to create the display for the front end to show existing commands. Pretty vanilla map class. Implements HashCode and .equals so that multiple functions with different arguments could be made later on down the road (not implemented yet though). 

	FunctionBracketAid: Is an instance of the BracketHandler interface. Provides the .handle method for handling particular SLogo structures. In this case, commands. 

	FixVars: implements the StorageFIxer interface which requires a fix() method to handle old and new commands/variables. In this case, variables.

	CommandStorage: Maps FunctionObj's to their respective TokenList functions. Very useful for storing functions. Pretty vanilla Map storage class. 

	CommandHandler: Also implements StorageFixer interface. Send in the TokenList to fix() and Command Handler will make new commands when necessary and fill in predefined commands when necessary. Is an integral class to the way our functions are parsed. 

#### Reflection

|         Classes        |                                        What It's For                                        |                                                   Public Methods                                                  |
|:----------------------:|:-------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------:|
| TurtleCommandHandler   | Used for determining if a command is a TurtleCommand                                        | boolean isTurtleCommand(String logoKey)                                                                           |
| TreeGenerator          | Makes the Command Tree to run all of the commands in the form of a queue                    | TreeNode Queue getCommandQueue(), TreeNode Queue getAllQueue(), double getLast()                                  |
| PackageLocationHandler | Used to determine where a class is for reflection to use when making a class                | String getLoc(String logoKey)                                                                                     |
| NumArgsHandler         | Used to get the number of arguments for every  command class                                | int getNumArgs(String logoKey)                                                                                    |
| ClassGenerator         | The factory for the SLogo program to make all of the commands                               | TreeNode generate(String className, Double num, State t), TreeNode generate(String className, List list, State t) |
| AllQueueHelper         | Helps to reorder the queue based on what the command ordering should be for nested commands | TreeNode Queue getQueue()                                                                                         |

**Class Descriptions**

	TurtleCommandHandler: Is currently used to return whether or not a command is a turtle command. Is dependent on the TurtleCommands.properties file. All turtle commands are encoded with "yes".

	TreeGenerator: The heart and soul of the compiler. All commands for the program are made in this class. Supports getting multiple types of queues depending on whether or not you want all commands or just the turtle ones. 

	PackageLocationHandler: Is currently used to return the location of the command class for reflection. Could be refactored. Dependent on the Locations property file.

	NumArgsHandler: Is used to determine the number of arguments for every command. Is integral in making sure that every command class is given the proper information. 

	ClassGenerator: The factory for the program. Is responsible for making all of the command classes. Either makes a constant (which acts as th leaf nodes for the parse tree), or makes a command with a list as the constructor. 

	AllQueueHelper: Helps to render the origin queue based off of which commands were nested and which command should operate in order.  

#### Main

|  Classes  |                                                               What It's For                                                               |                                                     Public Methods                                                     |
|:---------:|:-----------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------:|
| Compiler  | Compiling a ProtectedTokenList into a command queue to be popped() in the command class. One of the two integral classes for the parser.  | TreeNode Queue compile(State state, ProtectedTokenList list), VariableStorage getVars(), String List getFunctionList() |
| NewParser | Responsible for turning a String input into a ProtectedTokenList.                                                                         | ProtectedTokenList parse(String toParse)                                                                               |

**Class Descriptions**

	Compiler: The compiler is responsible for turning all ProtectedTokenLists (originally generated by the Parser) into a command queue. The compiler is also responsible for holding the variables and functions that the user has defined up until that point. It can return these variables and functions in a displayable format for the GUI.

	Parser: Implements ProtectedTokenListParser. The Parser is responsible for breaking the String passed to it up into a ProtectedTokenList (PTL). This PTL can then be used to determine which turtles to apply the ask and tell commands to and can be ready by anyone who passes something in to be parsed. 

#### Interpreter

|         Classes        |                                                                                                                                 What It's For                                                                                                                                 |                                         Public Methods                                         |
|:----------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------:|
| Interpreter            | Interpreting the TokenList into strictly logo commands and constants. Handles all loops, control structures, variables, and commands.                                                                                                                                         | void handleRegLoops(TokenList TL, State state), void handleVarLoops(TokenList TL, State state) |
| AbstractBracketHandler | Implements the BracketHandler interface which requires that .handle(TokenList, State) be defined. Handle() is responsible for fixing the TokenList with respect to the subclass. For example, a repeat handler replaces the repeat statement with literal repeated commands.  | TokenList getList(), abstract void handle(TokenList TL, State t)                               |
| AbstractBracketAid     | This is where the handle command actually lives. I did;t think that the AbstractBracketHandler should be responsible for holding the State of the turtle and it seemed more appropriate to put it in the BracketAid. All control structure classes implement this class.      | void handle(TokenList TL, State t)                                                             |

**Class Descriptions**

	Interpreter: Responsible for calling all of the handlers for all of the control structures. This class is very important. Without it, none of the repeats, doTimes, for would work. By having all of the handlers extend the BracketHandler interface, it makes looping though the handles() very clean.

	AbstractBracketAid: The only public methods is the .handle() method mentioned above. This tells all classes using the BracketAid that they have to de handling something. 

	AbstractBracketHandler: Anything that implements this class has to extend .handle(). All control structures implement this class to help sift through the TokenList. 

#### Helpers

|     Classes    |                                    What It's For                                   |                                                                                         Public Methods                                                                                        |
|:--------------:|:----------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| Replacer       | Replacing certain keys with integer values in a tokenList                          | void replace(TokenList TL, String key, int value)                                                                                                                                             |
| RangeHandler   | Implements RangeHandler to help get Integer lists for iterating control structures | abstract void handleRange(State t, TokenList TL), Integer List getList()                                                                                                                      |
| QueueSplitter  | Helps to reorder the queue into non nested components                              | TreenNode Queue getQueue()                                                                                                                                                                    |
| ListMultiplier | Help to multiply TokenLists for commands such as repeat                            | void replace(int start, int end, TokenList list, TokenList filler), void replace(int start, int end, TokenList list, List filler), void replace(int start, int end, String List toFill, String List filler) |

**Class Descriptions**

	Replacer: Helps to replace certain keys in a TokenList with constants. Has a wide range of uses. Can be used for variables, or commands like doTimes. 

	RangeHandler: Acts as a superclass for different rangeHandler configurations like doTimes and for. Helps to make a list of Integers to iterate over and replace accordingly. 

	QueueSplitter: Helps to reorder the queue in a the more proper order. Checks for isRoot() to reorder but could be refactored to support reordering based on other characteristics. 

	ListMultiplier: Was a bit of a dirty fix for a complex problem with repeating loops. Could have been done in a more proper fashion looking back (will go into further detail in Analysis). 

#### Function_Seperator

|        Classes        |                     What It's For                     |                   Public Methods                   |
|:---------------------:|:-----------------------------------------------------:|:--------------------------------------------------:|
| FunctionReconstructor | Making a String display of the commands for the GUI.  | String List getCommandList(CommandStorage command) |

**Class Descriptions**

	FunctionReconstructor: Helps to turn all commands into a String representation which can be displayed on the GUI. Is a pretty small/niche class. 


## Tahia

### Multiple Turtle Command API  - interface MultipleTurtleCommand

Classes that implement this API: Ask, Tell, Askwith

Public AskTellData getData() 
	This method returns the AskTellData object created by executing the multiple turtle command. 

Public void execute()
	This method executes the given command. Executing a multiple turtle command generates an AskTellData object that is then used to break every multiple turtle command down into single turtle commands the Parser and Compiler can understand, associated with the appropriate turtle ID. 


Back End Internal API - interface BackEndHandler

Classes that implement this API: ModelController, Controller 

Public Double getNumTurtle()
	This method returns the number of turtles in the current model. 

Public void handleReset()
	This method is used to reset the back end’s data structures when the user resets. 

Public void setBackground(Double int)
	This method is used to tell the Controller to set the background color of the GUI to the color that corresponds to the number on the palette. 

Public void setRelationship(double turtleID)
	This method is used to set the Observer/ Observable relationship between the back end and front end turtle States when a new turtle is created. 


Controller API - class Controller, ModelController
Controller coordinates all the communication between the front end and the back end (parsing, updating)
ModelController controls the commands and turtles that are to be represented on the front end 
Controller Public Methods:

Public String getStringOutput()
	This method is used to get String output from the execution of any given command
Public String getViewController()
	This method is used to get the View Controller being used for the current program 

ModelController Public Methods:

Public List<Double> getActiveTurtleIDs()
	This method returns an unmodifiable list of the turtle IDs that are currently active. 

Public List<Double> getTurtleIDs()
	This method returns an unmodifiable list of all the turtle IDs that have ever been created (in the session), whether active or not. 

Public String getStringOutput() 
	This method returns string output as associated with executing a command. 

Public void makeNewTurtle(Double id)
	This method allows for new turtles to be created by id number. 

Public List<Turtle> getTurtles()
	This method returns an unmodifiable list of all the turtles that have been created during the current session, whether active or not. 

Public void reset()
	This method resets the model. 

Public void setActiveTurtles(List<Double>)
	This method sets every turtle id in the list to be active. 

Public void update() 
	This method is used to update the model by executing turtle commands. 
	



### Multiple Turtle Command Parsing API

Interface CommandMap (implemented by MapMaker)

Public Map<K,V> getPTLMap();
	Returns the unmodifiable map of fully parsed and broken down commands for execution by the compiler. 


Class StringListCreator - takes a originally parsed command, and creates sublists that can be used by the SubListProcessor and the MapMaker; these lists represent blocks of commands that correspond to different sets of active turtles. The StringListCreator creates sublists at every Ask, Tell and AskWith, as well as all the commands preceding and trailing such statements, if applicable. 

Public <List<List<String>>> getSublists
	Returns a list of sublists, each of which represents a broken down command, for use by the SubListProcessor. 

Class SubListProcessor - takes a list of sublists, and creates command objects for all the Ask, Tell and AskWith sublists, as well as lists of trailing and preceding commands. 

Public List<Object> getCommandObjects
	Returns an unmodifiable list of the created command objects. 

Public List<String> getRemainingCommands
	Returns an unmodifiable list of the trailing commands. 

Public List<String> getPrecedingCommands
	Returns an unmodifiable list of the preceding commands. 

Class QueueConstructor  - used to create a Queue given a list of strings 

Public Queue<String> getParsedQ
	Returns a queue of the list of strings (passed in the constructor), without any Ask, Tell or Askwith  objects 

Public Queue<String> qetQ
	Returns a queue of the list of string (passed in the constructor) without making any modifications to the list



