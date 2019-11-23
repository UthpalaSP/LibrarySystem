# LibrarySystem

### This application is created for an assignment. Specifications are mentioned below.

A program that implements a Library Management System where the frontend is developed using type script and any other frameworks on top of it and backend developed using play framework.

In the library, there is space to store only 100 books and 50 DVDs (150 items in total).

In this assignment, it is required to create necessary classes to implement the following functionality:

Design and implement a class LibraryItem (abstract) and the subclasses Book and DVD. The classes should hold information about the ISBN, the Title, the sector, the Publication Date, the Date/Time it has been borrowed and the current reader (in case the book has been borrowed), and include appropriate methods.

In particular:

The Book class should include appropriate methods and hold information about the authors (note that it could be also more than one author), the publisher and the total number of pages.

The DVD class should include methods and information about the available languages, the available subtitles, the producer and the actors.

The class Reader should have methods and information about the ID, Name, mobile number and email.

You should implement a class DateTime to represent the time/date of when the item has been borrowed. Do not use any predefined library for date and time and you can refer as example to the class that has been provided during the tutorials.

Design and implement a class called WestminsterLibraryManager, which implements the interface LibraryManager. WestminsterLibraryManager maintains the list of the items in the library and provides all the methods for the library manager actor to manage the library.

The system should have necessary User Interface with following options (a single menu from which you can activate the actions) that can be used to perform the following management actions from which the manager can select one.

Add a new item in the library and display the number of free spaces (remember that the library can store max 150 items: 100 for books and 50 for DVD). Display a message in case there are no spaces available. The user can select if they would like to add a book or a DVD and enter the corresponding information.

Delete an item, given the ISBN, from the library and display the number of free spaces left. Display the type of the item that has been deleted (if it is a book or a DVD).

Display the list of the items in the library. For each item display the ISBN, the type of item (if it is a book or a DVD) and the title.

Borrow an item in the library given his ISBN. Consider that you need to save the name of the Reader (the person who borrows the item) and the date/time that the reader borrowed it. Also, each book can be borrowed for 7 days maximum and each DVD for 3 days maximum. In cases where the item is currently borrowed by another reader, display a message saying it is not available at the moment and when it will be available again.

Return an item that has been borrowed. Modify the relative current information in the item that has been returned and that now can be available again. If the item has been returned after the period allowed, then a fee will be applied: the reader will pay £0.2 any extra hour for the first 3 days, then £0.5 per hour. A message will say how much the reader has to pay to the library if the item is returned late.

Generate a report with all the items overdue and the corresponding fee. Order the list from the item that has been borrowed for the longest period.

When create a User Interface from where it is possible to see all the information for each item. The UI can be opened selecting an option from the menu console. In your UI application you should be able to resize your application keeping the component in the same “relative” position (Material Design).


##### Specification below for the implementation of the UI and the program.

You need to show the list of items in the library. You can use a Table to visualise them.
The user can search a specific item by title.
For each item display a green or red flag if it is currently available or borrowed respectively.

Some challenges:

A user should be able to make a reservation if a book is currently not available. Add in this option in to the system. The system should calculate an estimate of when the book will be available and display it to the user. In order to attempt this task, notice that you might change the previous implementation: you need to have saved the history (when it has been borrowed and for how many hours) for each item in order to compute how long it has been borrowed on average. Consider also that more than one user can make a reservation and so the time to wait for the item will be summed up! Write in comments how you ended up with your solution!
