# Marienbad
This project was carried out as part of my studies at the IUT in Vannes.
For this project, certain Java functionalities were “blocked” by the teachers (mainly Java's OOP functionalities), so that we could use Java as a simple algorithmic programming language (like Python). 
to make us use Java as a simple algorithmic programming language (like Python).
This explains the presence of the SimpleInput and Start files, which were not designed by me but by the IUT teachers,
which are respectively used to retrieve user input and launch the program, bypassing the classic Java schema (with public static void main(String[] args).

The MarienbadJvsJ file contains the Player vs Player version of the game, and the MarienbadJvsAI file contains the Player vs Computer version.
This version contains 3 difficulty levels:

Level 1: The computer only plays legal random moves ( i.e. on lines where
it's possible and by withdrawing a strictly positive number of matches less than or equal
equal to the number of matches on the line)
Level 2: The computer plays legal random moves until only
3 non-empty lines or less, then switches to winning strategy (more detailed in
level 3)
Level 3: The computer uses the winning strategy right from the start of the game.
To do this, each turn it calculates the binary values of the number of matches in each line
then sums these numbers in base 10 for each column. 
(each column is a power of 2).
If all columns are even, the computer makes a random move.
If there is at least one odd column, the computer tries all possible legal moves
until it finds a move that makes all columns even.
If it can't find one, it plays a random move.

README written in part by Elwan Yvin (@elwanyvin)
