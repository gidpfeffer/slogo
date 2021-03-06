# SLogo API Review

**Gideon Pfeffer and Juan Philippe**

## Part 1

**What about your API/design is intended to be flexible?**

It intended to easily handle new implementations of the SLogo project. I tried to reduce the number of assumptions as possible so that adding new features would be easier. Also, the API is very dependent on implementation. It gives a baseline of what you want to do, but it could be implemented in many different ways. 

**How is your API/design encapsulating your implementation decisions?**

It returns to the user the parsed string and the compiled commands, but it doesn't tell the user *how* the string was parsed or *how* the commands were compiled from the tokens generated by the parser. I would be able to change the implementation of the above mentioned aspects of the project and the APIs would still be applicable/work the same. 

**What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?**

The errors that could occur while parsing are:

	Invalid syntax in  the command entered such as:

	* uneven brackets
	* invalid arguments

	The attempted use of a variable or function that has never been instantialized
	
	Errors that may occur when tokenizing or compiling

	* improperly editing a String or List
	* mutating an object that will needed to be saved for another aspect of the project

I have been throwing IllegalStateExceptions() for the above mentioned cases but we plan on creating a separate exception which displays the exception on the GUI. 

**Why do you think your API/design is good (also define what your measure of good is)?**

I think that my API design is good because it is flexible, open to implementation, and easily understandable. I hide my implementation so that other coders can use my API without needing to worry about how to implement it. 

## Part 2

**How do you think Design Patterns are currently represented in the design or could be used to help improve the design?**

#### Interpreter 

The SLogo parser acts as an interpreter for the program. It takes in the String from the terminal and *interprets* it, turning it into command objects that can be executed on the turtle. 

#### Chain of Responsibility

There is a background chain of responsibility hierarchy in our SLogo project. First, the user enters a command. Then, the String is sent to the parser where it is then converted in to command objects. The command objects are then sent to the Model class, one by one, to be executed on the turtle. The GUI turtle, which is observing the Model turtle, then adjusts itself accordingly. 

#### Iterator

**How do you think the "advanced" Java features will help you implement your design?**

To an extent, we are implementing an iterator through out implementation of the Command object queue. The queue stores the command objects which need to be implanted on the turtle, and when prompted, can be popped to send that Command object to the model. 

#### Factory

I think that considering the factory further would be helpful in making a better design for our project. I currently have the list of String command equivalents ready to be sent t a factory, and I plan on using reflection, but I am not entirely sure about my approach yet. More planning is needed before I move forward. 

**What feature/design problem are you most excited to work on?**

I am most excited to work on the Command tree that we plan on implementing. I really like algorithmic coding and it seems like there are a ton of different ways to accomplish the tree, some better than  others.

**What feature/design problem are you most worried about working on?**

I am worried about working on error handling and function generation/variable defining. We currently plan on implementing two maps, but I can see complications arising during the process. 

**Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).**

* Parsing a basic command with no loops
* Handling loops
* Handling variable defining and function generation
* Storing variables and functions
* Removing comments from the code