##Game Description
This is a two-player game where each player has a pawn that moves on a board with MxN positions. The pawns move like queens in chess: horizontally, vertically, and diagonally. However, they can only move through white squares and cannot pass through the opponent's pawn or a black square. The game is played on a board where the pawns start at specified positions, and the players take turns moving their pawn until one player has no allowed moves.

##Program Description
This program allows a user to play against the computer. The computer plays as player MAX, and the user plays as player MIN. At the beginning of the program, the user specifies the board size (MxN), the positions of the pawns, and any black squares. The program then runs the game until a player loses. The computer uses the MINIMAX algorithm to make optimal moves, ensuring that it always chooses the best move for itself while assuming that the user is making the best moves possible for them.

The program is implemented in Java and uses a 2D array to represent the game board. The program keeps track of the black squares and updates the board after each move.

##Assumptions
The allowable movements of the pawns are limited to moving through white squares and not passing through the opponent's pawn or a black square. The program assumes that the user will input valid positions for the pawns and black squares. The program also assumes that the user will follow the rules of the game and not make invalid moves.

##How to Use
To use this program, follow these steps:

Clone the repository.
Compile the program (javac Game.java).
Open the Game.java file and specify the board size, the positions of the pawns, and any black squares.
Run the program (java Game).
Follow the prompts to play the game. The program will display the current state of the game board after each move, and it will indicate when the game is over and who won.