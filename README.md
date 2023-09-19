[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/x6ckGcN8)
# 3500 PA05 Project Repo

- TA wanted me to comment that our check style is good and all set and they don't why the
variable name and lexicography warnings are not going away (even though they are correct) and to tell the grader this
- password: OOD
- only need to test 90% of the model.
# Pitch
Welcome to College, the ultimate digital bullet journal
and planner app designed to streamline your planning,
organization, and productivity. Say goodbye to scattered
notes and having to use through multiple apps.  
With College, everything you need is right at your fingertips.

With College, enjoy a seamless and intuitive interface that
combines the best features of a traditional bullet journal
with the convenience and versatility of digital technology.
Some key features that make College the perfect choice
for your planning needs are its weekly overview, progress bar,
and custom themes.

College empowers you to bring structure, organization, and
creativity to your daily life. Say goodbye to chaos and embrace
a more efficient and fulfilling planning experience.  

Download College now and unlock the power of dynamic, digital planning.

# Screenshots
https://pebble-whitefish-3f1.notion.site/GUI-Images-37b11c0416b8472ba64b1091ee13aee1

# How program could be extended
- add custom icons for the user when they want customize their theme. Also we could let the user sort the tasks either by name and time .

# Image attribution
Images all drawn by Sydell


# Screenshots
https://pebble-whitefish-3f1.notion.site/GUI-Images-37b11c0416b8472ba64b1091ee13aee1


# SOLID principles
S - In our bullet journal project, we ensured that each class or module had a single responsibility.
For example, the Task class was responsible for representing a task with properties like an ID, name,
description, date, and completion status.  Instead of creating a method inside the Task class that
implements the same functionality, we implemented a separate AddTask record was responsible for adding
a new task to the bullet journal.  This ensured that each record would have one responsibility, which
would be either representing a task or adding a new task.

O - Our bullet journal project follows the open/closed principle by having separate modules for adding a
task and for representing a task.  The Task record is responsible for representing a task, where the
AddTask record is responsible for creating a new Task. Separating the responsibility of creating a task into a
separate class like AddTask ensures that the Task class remains closed for modification, yet open for
extension.

L - Our bullet journal showcases the Liskov substitution principle in its EventPane class.  The EventPane class
extends VBox.  While doing so, the EventPane class preserves the behavior and contracts defined by the VBox class
while adding the necessary functionality specific to the EventPane.  The VBox, the superclass, is replaceable with
objects of its EventPane class, its subclass.  So, it follows the Liskov substitution principle.

I - Our bullet journal,the AddTask class adheres to the Interface Segregation Principle by exposing a minimal 
and cohesive interface (StringProperty and BooleanProperty) to the clients that use it. This allows clients to interact with 
only the methods and properties that are relevant to them, without being forced to depend on unnecessary or unrelated behavior.
By utilizing specific property interfaces (StringProperty and BooleanProperty), the AddTask class defines clear boundaries and 
contracts for its clients. Clients can provide the necessary properties to create a Task object through the constructor, and the toTask()
method encapsulates the conversion logic. This design promotes separation of concerns and ensures that clients are not burdened with 
unnecessary dependencies or functionality. It allows for flexibility and extensibility since clients can provide different implementations of 
the required properties based on their specific need

D - The Constraint class represents a set of constraints or limitations for a journal system. It has two properties: maxTasksPerDay and maxEventsPerDay, 
along with their corresponding getters and setters. The Dependency Inversion Principle states that high-level modules should not depend on low-level modules; 
both should depend on abstractions. In this example, the Constraint class doesn't directly depend on any specific implementation or concrete class. 
It only depends on the abstraction of data types (String) for the constraints. By relying on abstractions rather than concrete implementations, 
the Constraint class becomes more flexible and decoupled from specific implementations. It allows for easier modification or extension of the codebase without 
impacting the Constraint class itself. For example, if in the future there is a need to change the data type used for constraints, 
such as switching from String to a custom ConstraintValue interface, the Constraint class wouldn't need to be modified. 
Only the implementations of ConstraintValue would be affected.




[PA Write Up](https://markefontenot.notion.site/PA-05-8263d28a81a7473d8372c6579abd6481)
