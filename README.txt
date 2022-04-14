=================
RUNNING THE GAME
=================
To run the program there are two available commands:
make run V1=<total words> V2=<words on screen> V3=<dictionary file>"	--> This allows user specifiable params as required by the assignment brief
make run								--> This command runs the game using the default dictionary, 5 words on the screen and a total of 20 words fall

Example command for running with user specified params:
make run V1=20 V2=5 V3=example_dict.txt

For a good playing experience, the maximum words that can fall down the screen at once is capped at 10
=================
    COMPILING
=================
The source code is compiled by simply typing 'make'

Alternatively the following command may be used compile the program manually:
javac src/*.java -cp bin/ -d bin/

=================
   ADDITIONAL
=================
Cleaning bin folder 	--> make clean
Generating java docs 	--> make docs
