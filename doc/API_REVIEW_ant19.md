##Part 1

**What about your API/design is intended to be flexible?**

You can have multiple turtles because of the model interface that manages and executes commands on any specified turtle.

Command being an interface so that you can implement it to provide implementation of each command. 

**How is your API/design encapsulating your implementation decisions?**

The API design conforms to the MVC design pattern. The model classes encapsulate all program's logic and hide the implementation details from the front end and vice versa. The controller serves as the communicator between the two parts. 


**What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?**

invalid arguments for commands, unrecognizable commands, error on executing commands on the turtle. 

**Why do you think your API/design is good (also define what your measure of good is)?**

encapsulation because it is easy to classify each class to either Model , View, or Controller component. 

interface and subclasses will allow for inheritance design which is extensible. 


##Part 2

**How do you think Design Patterns are currently represented in the design or could be used to help improve the design?**

MVC is a design pattern for the program. 
We can also implement the Observer pattern as a way to update the front end by changes from the back end. Specifically, the turtle at the backend will be an Observable object and that at the front end is an Observer, which listens to any changes from the observable. 

**How do you think the "advanced" Java features will help you implement your design?**

Java reflection from the parser will create an instance of a command object based on a string as the command name. So I can make a class for each command (say Forward, Backward). 

**What feature/design problem are you most excited to work on?**

I think when it comes to nested command (fd fd 50), I will have to execute commands in a tree structure, so I may need to make each command class as a tree node class. This is exciting since I never work on tree structure before.

**What feature/design problem are you most worried about working on?**

The logic commands such as for loop, if else don't necessarily work on the turtle so I'm not sure how to subclass these command classes from the command interface. 

**Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).**

1) the user input a nested command.

the nested command is parsed and reflection returns the command and constant objects as tree nodes into an expression tree. Each child node will be an argument of the parent node. After that, I will call execute and return value as I go from the bottom of the tree up to its root. 

2) the x, y coordinates of the turtle is changed as a result of a command executed in the back end. 

the backend will pass to the notifyObserver method in the turtle class the changed turtle properties, so that the turtle at the front end as the observer can call update.

3) the user provides commands on multiple turtles. 

the controller passes a map of turtle to command to execute, so I can loop through the map and call execute on that turtle as an argument.

4) the user want to remove a variable. 

the parser can call the instance of the storage class that contains a map of variables to values. It can calls the method to add and remove variables from that instance. 

> Written with [StackEdit](https://stackedit.io/).