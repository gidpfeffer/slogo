#Part 1 
Question 1:  What about your API Design is designed to be flexible?
	
Tahia:
	
	The API is designed to flexible by being able to easily accommodate multiple turtles, store user defined functions and variables and theoretically also having more than one window running at the same time. 

Vishnu: 
	
	The API is designed to handle extensions such as adding more than one turtle, adding commands 
	
Question 2: How is your API design encapsulating all of your implementation decisions?
	
Tahia: 
		
		The API is designed so that the Turtle does not have to be handed from the front-end to the back-end and then back to the front end. Instead we plan to use a Controller class as the middleman to hand the front-end the data it needs and the back end the data it needs. 

Vishnu:

	The parts are very separate from each other, each with its own intelligence. Therefore, they are very independent of the other parts. 
		
Question 3:  What exceptions might occur in your part and how will you handle them?

Tahia:

	Because the Parser is responsible for syntax errors, the rest of the back end will be responsible for throwing errors if and only if the turtle does something we deem illegal. 

Vishnu:

	The turtle would throw an exception only if the command parser hands it a command it doesn't know how to do. 

Question 4: Why do you think your API design is good (also define what your measure of good is?

Definition of good:
	
	good (adj) - encapsulated/ allows for additions 

Tahia:

	We think my API design is good because it does a good job of encapsulating implementation details and also keeps the model and the view pretty distinct from each other. 
	
Vishnu: 

	This design is very encapsulated and it also is pretty extendable in terms of adding new features like the capability to handle more than one turtle. 

#Part 2 

Question 1: How do you think Design Patterns are currently represented in the design/ could be used to improve the design?

	The Factory Method seems applicable to both of our APIs in terms of creating instances of various commands that need to be applied to the turtle. The Chain of Responsibility seems like it would be useful in updating the turtle after the back end makes changes to it. The Command chain could also be implemented with the Command parsing, as could Strategy. The Observer could also be used to facilitate communication between the front and the back end. Finally, the Null object could potentially be of use in error situations. 

Question 2: How do you think the advanced Java features will help you implement your design. 

	We think that reflection and enums will help with creating command objects and making that process more robust. Regular expressions could also come in handy with parsing commands. We may also use generics to create Tuple classes to handle our data. 

Question 3: What feature/ design problem are you most excited to work on?

Tahia: 

	I'm most excited to work on the Controller and coordinate the two sides of the program. I feel like it will be a challenge but it will also be rewarding. 

Vishnu:

	I am most excited to work on creating all of the Command objects because I feel like it will be cool to actually implement them and figure out how they all will work specifically. 

Question 4: What feature/ design problem are you most concerned about working on?

	We are both concerned about passing the updated turtle to the view etc. (aka what Tahia is also most excited about) because we think it will be fairly challenging. It also could break the whole program, so that's always scary. 

Question 5: Use Cases 

	1. User tells turtle to move forward 50 
	2. User changes background color to green
	3. User clicks a command previously run in the environment in order to run it again
	4. User defines a function and calls it to run 
	5. User resets the turtle to its original position and state 
	6. User tells the turtle not to draw a line (penup) 