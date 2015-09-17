Rock Paper Scissors
A TDD implementation of the popular game in Java with a GUI

The software was developed in Java using Eclipse IDE.

Run/Build instructions

Easily build the project by importing it into Eclipse.

Playing
In computer v computer mode, press the Go! button for one more round of the game
In human player v computer mode, press the ?  to choose the move. Click Next Round to move on or click your move to choose a new one and instantly go for the next round.

Testing

Unit testing: using JUnit 4

Coverage testing: using eclEmma plug-in for Eclipse - install by searching 'eclEmma' in the marketplace.

Copy-Paste Detection: using PMD’s CPD. Also a plug-in for Eclipse - install by using ‘Install new Software’ with link http://sourceforge.net/projects/pmd/files/pmd-eclipse/update-site/
The generated file is already provided in ‘reports’ folder.

Acceptance Testing

Can I play Player v Computer? Yes!
­Can I play Computer v Computer? Yes!
­Can I play a different game each time? Yes, assuming this means that the user can switch modes (Player v Computer, Computer v Computer) in runtime.

Other features

Ability to save a game when closing and resume it later.

Technical Details

UI
The UI was built with Java Swing. The goal was for the quiz to run as a desktop app without using external libraries.
Image credit: https://en.wikipedia.org/wiki/File:Rock-paper-scissors.svg

Extensibility
An example of extensibility is provided in file 'RockPaperScissorsLizardSpockTest’. Overriding the method that gives the score of the move, we can customize points given.

Design Patterns
The game logic uses the Singleton design pattern.
User moves(rock, paper, scissors) uses the Template Method design pattern, as it defers the play method to subclasses.

Author

Augusto Silva
augusto.cravo.silva@fe.up.pt
