# TriviaMaze
You must create a maze that user must navigate through from entrance to exit.  The maze is composed of rooms.  Each room has 1 or more doors (the design is up to you).  In order for the user to pass through a door, they must correctly answer a question.  The categories of questions asked are up to you, but you should have multiple choice, true/false, and short answer (one or two words/ one or two numbers).  The questions (and their corresponding answers) must be stored in a SQLite database.  The format of the database is up to you, but you might want to categorize your questions into the different formats you must display them in. NOTE: Basic SQL examples and discussion will be delivered in lecture.

If the user is unable to answer a question, that door is then locked permanently.  If the user is unable to make it from the entrance to the exit (due to locked doors), the game is lost.

You may display one room at a time, the entire maze, or the current room and the entire maze.

You are welcome to implement variations on this theme, but run them by me first.  You might place items in the room that can help the user (magic key that gets you through one door, a hint (ala "Who Wants to be a Millionaire") that reduces the multiple choice options or gives you the first letter/digit of an answer that must be typed.

The maximum size of the maze is up to you, but should be at least four by four rooms.  

The classes you create are up to you, but it seems you should have something like these: Maze, Room, Question_Answer (this could be an inheritance hierarchy), TriviaMaze (which is the entry point to your game and runs the program). 

Your program should have the ability to save the current state (you can employ the Memento design pattern to help with this).  You should do this using serialization, if possible (serialization will be demonstrated in class).  Otherwise, you'll need to write info about where the user is, how many questions answered, which rooms have been processed, etc. to a text file, which can be very tedious.

Your program must incorporate the MVC design pattern

Include an interface  that contains the following items (interface can be console or GUI):

A menu system that has the following menus/choices at the very least
File (Save Game, Load Game, Exit)
Help (About, Game Play Instructions)
Something that displays info about the current room.
Something that allows user navigation through the maze.  Only options that are valid for a room should be active/displayed.
A section that displays the current question.  This area should be updated dynamically based on the type of question (multiple choice, T/F, short answer, etc.)
REMINDER: Your program must read from a SQLite database file for the trivia questions and answers. 
